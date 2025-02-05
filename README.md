# **Chatty - Microservices-Based Chat Application**

A **scalable**, **secure**, and **modular** chat application built using **Spring Boot, Spring Cloud, PostgreSQL, MongoDB, and RabbitMQ**.

---

## **🔹 Features**

- **Microservices Architecture** - Decoupled services for authentication, messaging, and notifications.
- **Secure Authentication** - JWT-based authentication with OAuth2 and refresh tokens.
- **Service Discovery & Load Balancing** - Eureka for dynamic service registration.
- **API Gateway** - Spring Cloud Gateway for request routing and security.
- **Scalable Messaging** - MongoDB for chat storage, RabbitMQ for notifications.
- **Event-Driven Communication** - RabbitMQ for inter-service messaging.
- **Docker Support** - Run the project using Docker & Docker Compose.

---

## **🔹 Microservices Overview**

### **1. Authentication Service (`auth_service`)**
Handles user authentication, token generation, and validation.
- **Base Endpoint:** `/auth`
- **Endpoints:** `/login`, `/register`, `/refresh`, `/verify`
- **Tech:** Spring Security, JWT, PostgreSQL

### **2. API Gateway (`api_gateway`)**
Routes client requests to appropriate microservices while securing APIs.
- **Base Endpoint:** `/`
- **Tech:** Spring Cloud Gateway

### **3. Discovery Service (`discovery_service`)**
Manages dynamic service discovery and load balancing.
- **Tech:** Spring Cloud Eureka

---

## **🔹 Tech Stack**

- **Backend:** Java, Spring Boot, Spring Security, Spring Cloud
- **Database:** PostgreSQL, MongoDB
- **Message Queue:** RabbitMQ
- **Security:** OAuth2, JWT
- **Containerization:** Docker, Docker Compose

---

## **🔹 How to Run Locally**

### **1. Prerequisites**
Ensure you have the following installed:
- **Java 17+**
- **Maven**
- **Docker & Docker Compose**

### **2. Running the Services**

#### **Option 1: Using Docker Compose**
```bash
cd chatty-main
docker-compose up --build
```
This will start all microservices and dependencies like PostgreSQL and RabbitMQ.

#### **Option 2: Running Manually**
1. Start **Discovery Service**
   ```bash
   cd discovery_service
   mvn spring-boot:run
   ```
2. Start **API Gateway**
   ```bash
   cd api_gateway
   mvn spring-boot:run
   ```
3. Start **Authentication Service**
   ```bash
   cd auth_service
   mvn spring-boot:run
   ```

---

## **🔹 Future Enhancements**

- Implement WebSockets for real-time messaging.
- Deploy using Kubernetes.
- Add rate limiting and monitoring with Spring Boot Actuator.

