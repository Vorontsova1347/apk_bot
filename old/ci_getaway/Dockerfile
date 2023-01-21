FROM eclipse-temurin:17-jdk-jammy

WORKDIR /app

COPY gradle/ gradle
COPY gradlew build.gradle settings.gradle ./
RUN ./gradlew dependency:resolve

COPY src ./src

CMD ["./mvnw", "spring-boot:run"]