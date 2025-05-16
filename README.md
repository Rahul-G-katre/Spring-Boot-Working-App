# 🧩 Spring Boot E-commerce API

This is a full-featured **Spring Boot-based REST API** built for managing an e-commerce system with CRUD operations for **Products**, **Categories**, and **Brands**. It includes complete security, documentation, monitoring, and CI/CD automation.

---

## 📚 Table of Contents

- [Features](#-features)
- [Tech Stack](#-tech-stack)
- [Project Structure](#-project-structure)
- [Getting Started](#-getting-started)
- [API Documentation (Swagger)](#-api-documentation-swagger)
- [Authentication (JWT)](#-authentication-jwt)
- [Testing (JUnit)](#-testing-junit)
- [CI/CD with Jenkins](#-cicd-with-jenkins)
- [Monitoring (Prometheus + Grafana)](#-monitoring-prometheus--grafana)
- [Logging](#-logging)
- [Deployment (Google Cloud)](#-deployment-google-cloud)
- [Extras](#-extras)
- [Author](#-author)

---

## ✨ Features

✅ CRUD for Categories, Brands, and Products  
✅ One-to-many relationships between entities  
✅ JWT Authentication  
✅ Swagger UI + Postman support  
✅ Unit Testing with JUnit 5  
✅ Logging with SLF4J  
✅ CI/CD using Jenkins  
✅ Monitoring using Prometheus + Grafana  
✅ Docker containerization  
✅ Deployment-ready for cloud (GCP)

---

## 🛠 Tech Stack

- **Backend**: Java 17, Spring Boot
- **Security**: Spring Security, JWT
- **Documentation**: Swagger 3.0
- **Build Tool**: Maven
- **Database**: MySQL
- **Monitoring**: Prometheus, Grafana
- **Testing**: JUnit 5, Mockito
- **CI/CD**: Jenkins
- **Containerization**: Docker
- **Deployment**: Google Cloud Platform (GCP)

---

## 📁 Project Structure

```plaintext
src/
├── main/
│   ├── java/com/example/
│   │   ├── controller/
│   │   ├── service/
│   │   ├── repository/
│   │   ├── model/
│   │   ├── config/          # Security and Swagger config
│   │   └── security/        # JWT and filter logic
│   └── resources/
│       └── application.yml  # DB, logging, and monitoring configs
├── test/                    # JUnit tests
