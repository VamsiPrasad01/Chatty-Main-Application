# Stage 1: Build the application
FROM maven:3.8.5-openjdk-17 AS build
WORKDIR /app

# Copy only pom.xml first for dependency caching
COPY pom.xml .
RUN mvn dependency:go-offline -B

# Copy the rest of the source files
COPY . .

# Build the project using Maven
RUN mvn clean package -DskipTests

# Stage 2: Create a lightweight runtime image
FROM openjdk:17-jdk-slim
WORKDIR /app

# Copy the built JAR from the previous stage
COPY --from=build /app/target/*.jar auth_service.jar

# Expose port 8081 for the Auth Service
EXPOSE 8081

# Run the application
ENTRYPOINT ["java", "-jar", "auth_service.jar"]
