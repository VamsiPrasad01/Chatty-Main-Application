# Stage 1: Build the application
FROM maven:3.8.5-openjdk-17 AS builder
WORKDIR /build

# Copy only the parent POM first to optimize caching
COPY pom.xml .

# Copy module-specific POM files to their respective directories
COPY auth_service/pom.xml auth_service/
COPY api_gateway/pom.xml api_gateway/
COPY discovery_service/pom.xml discovery_service/

# Pre-download dependencies to leverage Docker caching
RUN mvn dependency:go-offline -B

# Copy the rest of the source code
COPY . .

# Build all modules without running tests
RUN mvn clean package -DskipTests
