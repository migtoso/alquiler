FROM eclipse-temurin:25-jdk-alpine
WORKDIR /app
COPY target/gateway-server-1.0.0.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]
