# 📚 Bookstore Microservices Project

The **Bookstore** project is a web-based system built with a microservices architecture that simulates a modern online book-selling platform. It leverages modern technologies such as Java Spring Boot, Docker, Kafka, and ReactJS to ensure scalability, flexibility, and maintainability.

---

## 🧩 System Architecture

The system is composed of several key services:

- **API Gateway**: Serves as the single entry point to route client requests and handle cross-cutting concerns like authentication.
- **Identity Service**: Manages user authentication and authorization.
- **Profile Service**: Handles user profile management.
- **Post Service**: Manages book listings and related content.
- **File Service**: Supports file uploads and storage.
- **Notification Service**: Sends user notifications based on Kafka events.
- **Web Application**: A user-friendly frontend developed with ReactJS.

All services are containerized with Docker and orchestrated using `docker-compose`.

---

## 🛠️ Technologies Used

- **Backend**: Java 17, Spring Boot, Spring Security, Spring Data JPA  
- **Frontend**: ReactJS, Axios, Bootstrap  
- **Database**: PostgreSQL  
- **Message Broker**: Apache Kafka  
- **Containerization**: Docker, Docker Compose  
- **Version Control**: Git, GitHub  

---

## 🚀 Getting Started

1. **Install Docker and Docker Compose** on your machine.
2. **Clone the repository**:
   ```bash
   git clone https://github.com/tripm3010/bookstore.git
   cd bookstore
3. Run the services:
   docker-compose up --build
4. Access the application:

   Frontend: http://localhost:3000

   API Gateway: http://localhost:8080
5. **This project demonstrates my ability to:

   Design and implement microservices with Spring Boot

   Communicate asynchronously between services using Apache Kafka

   Develop a modern UI with ReactJS

   Deploy and orchestrate services using Docker and Docker Compose

   Secure microservices with JWT-based authentication

   Follow DevOps best practices and CI/CD principles
