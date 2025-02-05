# 1. Build JAR
FROM maven:3.8.5-openjdk-17 AS builder
WORKDIR /build
COPY pom.xml .
RUN mvn dependency:go-offline
COPY . .
RUN mvn clean package -DskipTests

# 2. Create final runtime image
FROM openjdk:17-jdk-alpine
WORKDIR /app
COPY --from=builder /build/auth_service/target/*.jar auth_service.jar
COPY --from=builder /build/api_gateway/target/*.jar api_gateway.jar
COPY --from=builder /build/discovery_service/target/*.jar discovery_service.jar

# Run as non-root user
RUN addgroup -S appgroup && adduser -S appuser -G appgroup
USER appuser

# Expose necessary ports
EXPOSE 8080 8081 8761

# Run services (example for Auth service)
CMD ["java", "-jar", "auth_service.jar"]
