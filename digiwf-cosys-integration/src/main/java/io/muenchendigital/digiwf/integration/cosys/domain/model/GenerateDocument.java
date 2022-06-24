/*
 * Copyright (c): it@M - Dienstleister für Informations- und Telekommunikationstechnik
 * der Landeshauptstadt München, 2020
 */
package io.muenchendigital.digiwf.integration.cosys.domain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

/**
 * Dto for generating documents.
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class GenerateDocument {


    /**
     * Client that is used in cosys
     */
    private String client;

    /**
     * Role that is used in cosys
     */
    private String role;

    /**
     * The S3 presigned url where the document is stored, including the documents name and type
     */
    private String s3PresignedUrl;

    /**
     * The GUID of the target template to be filled
     */
    private String guid;

    /**
     * All data to be filled into template
     */
    private Map<String, String> variables;
}
