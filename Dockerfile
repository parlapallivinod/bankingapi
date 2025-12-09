FROM eclipse-temurin:25-jdk-alpine
VOLUME /tmp
COPY target/bankingapi-0.0.1-SNAPSHOT.jar bankingapi-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java", "-jar", "-Dspring.profiles.active=render", "/bankingapi-0.0.1-SNAPSHOT.jar"]
EXPOSE 8080