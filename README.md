# jee-architecture-spring-boot-domain
JEE 7 non-blocking I/O Architecture, using Spring Boot and Domain layer

## Requirements

- JDK 1.8
- Apache Maven 3.x

## Stack

- JSE 8
- Spring Boot 2.x
- Spring Boot Security 2.x
- Spring Boot Actuator 2.x
- Spring Boot Undertow 2.x
- Spring Fox Swagger 2.x
- MyBatis Spring Boot 1.x
- AspectJ
- Lombok

## Contribution guide

### Remotes

The **remotes** follow the convention:

- _**origin**_: fork in the account of the developer

- _**upstream**_: main repository

### Building

For local environment:

```sh
$ mvn clean spring-boot:run -P local
```

For development environment:

```sh
$ mvn clean spring-boot:run -P development
```

For staging environment:

```sh
$ mvn clean spring-boot:run -P staging
```

For production environment:

```sh
$ mvn clean spring-boot:run -P production
```

### Exploring

Only for local environment:

Go to http://localhost:8080/api/swagger-ui.html to see the Swagger Explorer

![alt tag](https://raw.githubusercontent.com/rpinaa/jee-architecture-spring-boot-domain/master/swagger-api.png)

## License

MIT

**Free Software, Hell Yeah!**
