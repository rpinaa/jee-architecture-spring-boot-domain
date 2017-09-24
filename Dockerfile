FROM java:8-jre-alpine
VOLUME /tmp
ADD target/app.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar"]