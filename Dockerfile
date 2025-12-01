FROM eclipse-temurin:17-jdk
EXPOSE 8080
COPY .env .
ADD target/stock-system.jar stock-system.jar
ENTRYPOINT ["java", "-jar", "/stock-system.jar"]