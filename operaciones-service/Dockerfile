FROM eclipse-temurin:25-jdk-alpine
WORKDIR /app
COPY target/operaciones-service-1.0.0.jar app.jar
EXPOSE 8082
ENTRYPOINT ["java", "-jar", "app.jar"]
