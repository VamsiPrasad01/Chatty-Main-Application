# **chatty - Microservices-Based Chat Application**  
A **scalable**, **secure**, and **modular** chat application built using **Spring Boot, Spring Cloud, PostgreSQL, MongoDB, and RabbitMQ**.  

## **🔹 Features**  
✅ **Microservices Architecture** - Decoupled services for authentication, messaging, and notifications.  
✅ **Secure Authentication** - JWT-based authentication with OAuth2 and refresh tokens.  
✅ **Service Discovery & Load Balancing** - Eureka for dynamic service registration.  
✅ **API Gateway** - Spring Cloud Gateway for request routing and security.  
✅ **Scalable Messaging** - MongoDB for chat storage, RabbitMQ for notifications.  
✅ **Event-Driven Communication** - RabbitMQ for inter-service messaging.  

---

## **🔹 Microservices Overview**  

### **1️⃣ Authentication Service (`auth-service`)**  
Handles user authentication, token generation, and validation.
- 🔹 **Base Endpoint:** /auth
- 🔹 **Endpoints:** `/login`, `/register`, `/refresh`, `/verify`  
- 🔹 **Tech:** Spring Security, JWT, PostgreSQL, (OAuth2 TODO)

### **2️⃣ Chat Service (`chat-service`)**  
Manages real-time messages and conversations.  
- TODO

### **3️⃣ Notification Service (`notification-service`)**  
Sends chat notifications (emails, push notifications, in-app alerts).  
- TODO

### **4️⃣ API Gateway (`api-gateway`)**  
Routes requests and secures APIs via authentication checks.  
- TODO

### **5️⃣ Service Discovery (`eureka-server`)**  
Manages dynamic service discovery and load balancing.  
- 🔹 **Tech:** Eureka  

---

## **🔹 Tech Stack**  
🔹 **Backend:** Java, Spring Boot, Spring Security, Spring Cloud  
🔹 **Database:** PostgreSQL (Users, etc), MongoDB (Messages)  
🔹 **Message Queue:** RabbitMQ  
🔹 **Security:** OAuth2, JWT  
🔹 **Deployment:** Docker, Kubernetes (Future Plan)  


---

## **🔹 Future Enhancements**  
✅ WebSockets for real-time messaging.  
✅ Kubernetes for container orchestration.  
✅ Rate limiting & monitoring with Spring Boot Actuator.  
