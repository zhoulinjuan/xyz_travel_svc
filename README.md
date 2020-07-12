# How it works

The application uses Spring boot (Web, Mybatis).

  - Use the idea of Domian Driven Design to seperate the business term and infrastruture term.
  - Use MyBatis to implement the Data Mapper pattern for persistence
  - Use Swagger for the dynamic API documentation
 
And the code organized as this:
  - `common` is the commom pojo and uitls used by the project
  - `service` is the web layer to impmenet by Spring MVC for services
  - `service\test\java\integration` is the integration test cases
  - `service\test\resources\sql\v0.01_init.sql` is the database scehema and integration test data

# Database

It uses a PostgreSQL database, also can be changed to other database like MySQL.


# Getting started

## Enviorment prepration
- Java 8
- Maven 3.6 or above
- For integration testing [Docker env](https://www.testcontainers.org/supported_docker_environment/) required
- PostgreSQL 9.6 or above

## Build
```sh
mvn com.coveo:fmt-maven-plugin:format clean package -U
```

## Run on local
```sh
java -jar xyz-travel-service\target\xyz-travel-service-1.0-SNAPSHOT.jar
```
After the project started, you can access the live document from http://localhost:8080/swagger-ui.html, which inlcude the data models for requests and responses.


# Run test
```sh
mvn com.coveo:fmt-maven-plugin:format test verify
```
