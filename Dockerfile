FROM chainguard/jdk:latest AS builder

WORKDIR /app

COPY . .

RUN ./gradlew build

FROM chainguard/jre:latest

COPY --from=builder /app/build/libs/*.jar /app/app.jar

ENTRYPOINT ["java", "-jar", "/app/app.jar"]
