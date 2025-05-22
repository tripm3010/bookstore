# 📚 Bookstore Microservices Project

The **Bookstore** project is a web-based system built with a microservices architecture that simulates a modern online book-selling platform. It leverages cutting-edge technologies including Java Spring Boot, Docker, and ReactJS to ensure scalability, flexibility, and maintainability.

---

## 🧩 System Architecture

The system is composed of several key services:

- **API Gateway**: Serves as the single entry point to route client requests and handle cross-cutting concerns like authentication.
- **Identity Service**: Manages user authentication and authorization.
- **Profile Service**: Handles user profile management.
- **Post Service**: Manages book listings and related content.
- **File Service**: Supports file uploads and storage.
- **Notification Service**: Sends user notifications via email or other channels.
- **Web Application**: A user-friendly frontend developed with ReactJS.

All services are containerized with Docker and orchestrated using `docker-compose`.

---

## 🛠️ Technologies Used

- **Backend**: Java 17, Spring Boot, Spring Security, Spring Data JPA  
- **Frontend**: ReactJS, Axios, Bootstrap  
- **Database**: PostgreSQL  
- **Messaging Queue**: RabbitMQ  
- **Containerization**: Docker, Docker Compose  
- **Version Control**: Git, GitHub  

---

## 🚀 Getting Started

1. **Install Docker and Docker Compose** on your machine.
2. **Clone the repository**:
   ```bash
   git clone https://github.com/tripm3010/bookstore.git
   cd bookstore
