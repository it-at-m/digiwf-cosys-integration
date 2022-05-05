<div id="top"></div>

<!-- PROJECT SHIELDS -->

<!-- END OF PROJECT SHIELDS -->

<!-- PROJECT LOGO -->
<br />
<div align="center">
  <a href="#">
    <img src="/images/logo.png" alt="Logo" height="200">
  </a>

<h3 align="center">DigiWF CoSys Integration</h3>

  <p align="center">
    This is a Spring Boot Starter library to generate documents in CoSys in the DigiWF environment.
    It can be used to generate documents asynchronously through an event broker and save them into a s3 path.
<br /><a href="#">Report Bug</a>
    ·
    <a href="#">Request Feature</a>
  </p>
</div>

<!-- ABOUT THE PROJECT -->

## About The Project

The goal of this library is enabling async document creation in cosys with a event broker and s3.

Features:

* Can be used to create documents in cosys and save them in s3
* Can inform the receiver through an event if the creation was successful or if there was a problem

<p align="right">(<a href="#top">back to top</a>)</p>

### Built With

This project is built with:

* [Spring Boot](https://spring.io/projects/spring-boot)
* [Spring Cloud Stream](https://spring.io/projects/spring-cloud-stream)
* [DigiWF Spring Cloudstream Utils](https://github.com/it-at-m/digiwf-spring-cloudstream-utils)
* [DigiWF S3 Integration](https://github.com/it-at-m/digiwf-s3-integration)

<p align="right">(<a href="#top">back to top</a>)</p>

<!-- ROADMAP -->

## Roadmap

See the [open issues](#) for a full list of proposed features (and known issues).

<p align="right">(<a href="#top">back to top</a>)</p>

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

<p align="right">(<a href="#top">back to top</a>)</p>

## Documentation

To create a document asynchronously, simply create a GenerateDocument object, set the TYPE-Header to generateDocument
and send it to the corresponding kafka topic. That's it!

<p align="right">(<a href="#top">back to top</a>)</p>

<!-- CONTRIBUTING -->

## Contributing

Contributions are what make the open source community such an amazing place to learn, inspire, and create. Any
contributions you make are **greatly appreciated**.

If you have a suggestion that would make this better, please open an issue with the tag "enhancement", fork the repo and
create a pull request. You can also simply open an issue with the tag "enhancement". Don't forget to give the project a
star! Thanks again!

1. Open an issue with the tag "enhancement"
2. Fork the Project
3. Create your Feature Branch (`git checkout -b feature/AmazingFeature`)
4. Commit your Changes (`git commit -m 'Add some AmazingFeature'`)
5. Push to the Branch (`git push origin feature/AmazingFeature`)
6. Open a Pull Request

More about this in the [CODE_OF_CONDUCT](/CODE_OF_CONDUCT.md) file.

<p align="right">(<a href="#top">back to top</a>)</p>


<!-- LICENSE -->

## License

Distributed under the MIT License. See `LICENSE` file for more information.

<p align="right">(<a href="#top">back to top</a>)</p>



<!-- CONTACT -->

## Contact

it@m - opensource@muenchendigital.io

[join our slack channel](https://join.slack.com/t/digiwf/shared_invite/zt-14jxazj1j-jq0WNtXp7S7HAwJA7tKgpw)

<p align="right">(<a href="#top">back to top</a>)</p>


<!-- MARKDOWN LINKS & IMAGES -->
<!-- https://www.markdownguide.org/basic-syntax/#reference-style-links -->
