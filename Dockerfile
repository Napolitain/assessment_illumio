FROM chainguard/jdk:latest AS builder

WORKDIR /app

COPY . .
RUN mkdir "data"

RUN ./gradlew build

FROM chainguard/jre:latest

WORKDIR /app

COPY --from=builder /app/build/libs/*.jar app.jar
COPY --from=builder /app/data data

ENTRYPOINT ["java", "-jar", "/app/app.jar"]
