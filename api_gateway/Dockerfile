# Stage 1: Build the application
FROM maven:3.8.5-openjdk-17 AS build
WORKDIR /app

# Copy only pom.xml first to leverage caching
COPY pom.xml .
RUN mvn dependency:go-offline -B

# Copy source files
COPY . .

# Build the project using Maven
RUN mvn clean package -DskipTests

# Stage 2: Create a lightweight runtime image
FROM openjdk:17-jdk-slim
WORKDIR /app

# Copy the built JAR from the previous stage
COPY --from=build /app/target/*.jar api_gateway.jar

# Expose port for the API Gateway
EXPOSE 8080

# Run the application
ENTRYPOINT ["java", "-jar", "api_gateway.jar"]
