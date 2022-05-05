package io.muenchendigital.digiwf.integration.cosys.domain.service;

import io.muenchendigital.digiwf.integration.cosys.configuration.CosysConfiguration;
import io.muenchendigital.digiwf.integration.cosys.domain.mapper.GenerateDocumentRequestMapper;
import io.muenchendigital.digiwf.integration.cosys.domain.model.GenerateDocument;
import io.muenchendigital.digiwf.integration.cosys.domain.model.GenerateDocumentRequest;
import io.muenchendigital.digiwf.s3.integration.client.repository.DocumentStorageFileRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.entity.mime.content.ByteArrayBody;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;

@Slf4j
@Service
@AllArgsConstructor
public class CosysService {

    private final RestTemplate restTemplate;
    private final CosysConfiguration cosysConfiguration;
    private final DocumentStorageFileRepository documentStorageFileRepository;
    private final GenerateDocumentRequestMapper generateDocumentRequestMapper;

    static final String ATTRIBUTE_CLIENT = "client";
    static final String ATTRIBUTE_ROLE = "role";
    static final String ATTRIBUTE_DATA = "data";
    static final String ATTRIBUTE_MERGE = "merge";
    static final String ATTRIBUTE_STATE_FILTER = "stateFilter";
    static final String ATTRIBUTE_VALIDITY = "validity";
    static final String COSYS_DATA_FILENAME = "data.xml";
    static final String COSYS_MERGE_FILENAME = "merge.json";

    /**
     * Generate a Document in Cosys and save it in S3 Path.
     *
     * @param generateDocument Data for generating documents
     */
    public void createDocument(final GenerateDocument generateDocument) {
        final byte[] data = this.generateDocument(generateDocument);
        this.saveDocument(generateDocument, data);
    }

    //------------------------------ helper methods ------------------------------//

    private byte[] generateDocument(final GenerateDocument generateDocument) {
        try {
            final GenerateDocumentRequest generateDocumentRequest = this.generateDocumentRequestMapper.map(generateDocument);
            final String url = this.createCosysUrl(generateDocumentRequest);
            final HttpEntity body = this.createBody(generateDocumentRequest);
            return this.restTemplate.postForObject(url, body, byte[].class);
        } catch (final Exception ex) {
            log.error("Document could not be created.", ex);
            throw new RuntimeException("Document could not be created.");
        }
    }

    private void saveDocument(final GenerateDocument generateDocument, final byte[] data) {
        try {
            this.documentStorageFileRepository.saveFile(generateDocument.getS3Path(), data, 3, null);
        } catch (final Exception ex) {
            log.error("Document could not be saved", ex);
            throw new RuntimeException("Document could not be saved.");
        }
    }

    private String createCosysUrl(final GenerateDocumentRequest request) {
        final StringBuilder url = new StringBuilder();
        url.append(this.cosysConfiguration.getUrl());
        url.append("/generation/");
        url.append(request.getGuid());
        url.append("/pdf");
        url.append("?" + ATTRIBUTE_CLIENT + "=" + request.getClient());
        url.append("&" + ATTRIBUTE_ROLE + "=" + request.getRole());

        if (!StringUtils.isEmpty(request.getStateFilter())) {
            url.append("&" + ATTRIBUTE_STATE_FILTER + "=" + request.getStateFilter());
        }

        if (!StringUtils.isEmpty(request.getStateFilter())) {
            url.append("&" + ATTRIBUTE_VALIDITY + "=" + request.getValidity());
        }

        url.append("&bridgeEntpoint=true&throwExceptionOnFailure=true&connectTimeout=1500");

        return url.toString();
    }

    private HttpEntity createBody(final GenerateDocumentRequest request) throws IOException {
        final MultipartEntityBuilder builder = MultipartEntityBuilder.create();
        builder.setMode(HttpMultipartMode.BROWSER_COMPATIBLE);
        builder.addPart(ATTRIBUTE_DATA, new ByteArrayBody(request.getData(), ContentType.APPLICATION_XML, COSYS_DATA_FILENAME));
        builder.addPart(ATTRIBUTE_MERGE, new ByteArrayBody(request.getMerge(), ContentType.APPLICATION_JSON, COSYS_MERGE_FILENAME));
        return builder.build();
    }
}
