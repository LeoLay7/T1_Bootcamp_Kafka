# Build stage
FROM eclipse-temurin:17-jdk-alpine AS builder

WORKDIR /app
COPY pom.xml .
COPY src ./src

RUN apk add --no-cache maven && \
    mvn clean package -DskipTests

# Runtime stage
FROM eclipse-temurin:17-jre-alpine

WORKDIR /app

COPY --from=builder /app/target/classes ./target/classes
COPY --from=builder /app/target/dependency ./target/dependency

CMD ["java", "-cp", "target/classes:target/dependency/*", "org.homework.Main"]