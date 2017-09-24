# jee-architecture-spring-boot-domain
JEE 7 non-blocking I/O Architecture, using Spring Boot and Domain layer

## Requirements

- JDK 1.8
- Apache Maven 3.x

## Stack

- JSE 8
- AspectJ
- Spring Boot 2.x
- Spring Boot Security 2.x
- Spring Boot Actuator 2.x
- Spring Boot Undertow 2.x
- Spring Fox Swagger 2.x
- MyBatis Spring Boot 1.x

## Plugins

- Lombok

## Contribution guide

### Remotes

The **remotes** follow the convention:

- _**origin**_: fork in the account of the developer

- _**upstream**_: main repository

### Commit Style

Please you consider de following git styles for commit messages:

http://udacity.github.io/git-styleguide/

### Building

For local environment:

```sh
$ mvn clean install -Dspring.profiles.active=local -P local
```

For development environment:

```sh
$ mvn clean install -Dspring.profiles.active=development -P development
```

For staging environment:

```sh
$ mvn clean install -Dspring.profiles.active=staging -P staging
```

For production environment:

```sh
$ mvn clean install -Dspring.profiles.active=production -P production
```

### Running

For local environment:

```sh
$ mvn clean spring-boot:run -Dspring.profiles.active=local -P local
```

For development environment:

```sh
$ mvn clean spring-boot:run -Dspring.profiles.active=development -P development
```

For staging environment:

```sh
$ mvn clean spring-boot:run -Dspring.profiles.active=staging -P staging
```

For production environment:

```sh
$ mvn clean spring-boot:run -Dspring.profiles.active=production -P production
```

You can use the new syntax: "-Dspring-boot.run.profiles=" instead of "-Dspring.profiles.active="

### Packaging

Using docker environment we have:

```sh
$ docker build -t DOCKER_ORCHESTRATOR/ORCHESTRATOR_ID/DOCKER_IMAGE_NAME:DOCKER_IMAGE_TAG .
```

Only for local environment:

```sh
$ docker build -t app:local .
```

Only for cloud environment and Google Container Registry orchestrator:

```sh
$ docker build -t gcr.io/GOOGLE_PROJECT_ID/DOCKER_IMAGE_NAME:DOCKER_IMAGE_TAG .
```

### Exposing

Only for local environment:

```sh
$ docker run -p 8080:8080 app
```

### Integrating

For Kubernetes integration we have the "deployment.yaml" file as a deployment descriptor. You can use kubectl to create or delete a deployment:

```sh
$ kubectl delete -f deployment.yaml --namespace=NAMESPACE_NAME
```

```sh
$ kubectl create -f deployment.yaml --namespace=NAMESPACE_NAME
```

### Exploring

Only for local environment:

Go to http://localhost:8080/api/swagger-ui.html to see the Swagger Explorer

![alt tag](https://raw.githubusercontent.com/rpinaa/jee-architecture-spring-boot-domain/master/swagger-api.png)

## License

MIT

**Free Software, Hell Yeah!**
