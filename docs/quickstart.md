## Getting started

Below is an example of how you can install and set up your service.

1. Use the spring initalizer and create a Spring Boot application with `Spring Web`
   dependencies [https://start.spring.io](https://start.spring.io)
2. Add the digiwf-cosys-integration-starter dependency.

With Maven:

```
   <dependency>
        <groupId>io.muenchendigital.digiwf</groupId>
        <artifactId>digiwf-cosys-integration-starter</artifactId>
        <version>${digiwf.version}</version>
   </dependency>
```

With Gradle:

```
implementation group: 'io.muenchendigital.digiwf', name: 'digiwf-cosys-integration-starter', version: '${digiwf.version}'
```

3. Add your preferred binder (see [Spring Cloud Stream](https://spring.io/projects/spring-cloud-stream)). In this
   example, we use kafka.

Maven:

 ```
     <dependency>
         <groupId>org.springframework.cloud</groupId>
         <artifactId>spring-cloud-stream-binder-kafka</artifactId>
     </dependency>
```

Gradle:

```
implementation group: 'org.springframework.cloud', name: 'spring-cloud-stream-binder-kafka'
```

4. Configure your binder.<br>
   For an example on how to configure your binder,
   see [DigiWF Spring Cloudstream Utils](https://github.com/it-at-m/digiwf-spring-cloudstream-utils#getting-started)
   Note that you DO have to
   configure ```spring.cloud.function.definition=functionRouter;sendMessage;sendCorrelateMessage;```, but you don't need
   typeMappings. These are configured for you by the digiwf-cosys-integration-starter. You also have to configure the
   topics you want to read / send messages from / to.

5. Configure S3

```
io.muenchendigital.digiwf.s3.client.document-storage-url: http://s3-integration-url:port
```

See [this](https://github.com/it-at-m/digiwf-spring-cloudstream-utils) for an example.

6. Configure your application

```
io.muenchendigital.digiwf.cosys.url=localhost:800
io.muenchendigital.digiwf.cosys.merge.datafile=/root/multi
io.muenchendigital.digiwf.cosys.merge.inputLanguage=Deutsch
io.muenchendigital.digiwf.cosys.merge.outputLanguage=Deutsch
io.muenchendigital.digiwf.cosys.merge.keepFields=unresolved-ref
```

7. Define a RestTemplate. For an example, please refer to
   the [example project](https://github.com/it-at-m/digiwf-cosys-integration/tree/dev/example).