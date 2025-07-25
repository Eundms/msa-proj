FROM openjdk:21-jdk-slim

WORKDIR /app

COPY gradle gradle
COPY gradlew .
COPY build.gradle .
COPY settings.gradle .
COPY src src

RUN chmod +x ./gradlew
RUN ./gradlew build -x test

EXPOSE 8081

CMD ["java", "-jar", "build/libs/internal-api-service-0.0.1-SNAPSHOT.jar"]