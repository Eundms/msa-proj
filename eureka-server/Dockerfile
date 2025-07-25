FROM openjdk:21-jdk-slim

WORKDIR /app

COPY gradle gradle
COPY gradlew .
COPY build.gradle .
COPY settings.gradle .
COPY src src

RUN chmod +x ./gradlew
RUN ./gradlew build -x test

EXPOSE 8761

CMD ["java", "-jar", "build/libs/eureka-server-0.0.1-SNAPSHOT.jar"]