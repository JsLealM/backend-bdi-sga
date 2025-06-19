# 🎯 SGA Backend – Academic Management System

## 📘 Project Description

This repository hosts the **backend of the SGA (Academic Management System)** application, built using **Java and Spring Boot**.

---

## 🚀 Technologies Used

- **Java**
- **Spring Boot**
- **Spring Data JPA**
- **PostgreSQL**
- **Maven**

---

## 🧠 Implemented Features

- **Student Module:** Full CRUD with audit tracking on update and delete.
- **Professor Module:** CRUD with custom deletion logic (e.g., course unassignment).
- **Course Module:** Course registration and retrieval.
- **Evaluation Module:** Creation, filtering by course, update, and delete.
- **Grade Evaluation Module:** Linking evaluations and students with scores.
- **Audit Logging:** `student_audit` table stores modification records for student data.
- **Error Handling and Input Validation**

---

## 🗂️ Project Structure

```
src/
└── main/
    ├── java/db/sga/backend/
    │   ├── model/         → JPA Entities
    │   ├── repository/    → JPA Repositories
    │   ├── service/       → Business logic
    │   └── rest/          → REST Controllers
    └── resources/
        └── application.properties
```
---

## 🌐 Project Links

| Module     | Repository / Deployment Link                            |
|------------|----------------------------------------------------------|
| DataBase   | [SGA DatBase GitHub Repo](https://github.com/JsLealM/academic-management-system.git) |
| Frontend   | [SGA Frontend GitHub Repo](https://github.com/JsLealM/frontend-bdi-sga.git) |

---


## 🛠️ Setup & Run

### 1. Clone the repository

```bash
git clone https://github.com/your-org/sga-backend.git
cd sga-backend
```

### 2. Configure database connection
Edit the application.properties file:
```
spring.datasource.url=jdbc:postgresql://localhost:5432/sga
spring.datasource.username=postgres
spring.datasource.password=your_password
spring.jpa.hibernate.ddl-auto=validate
```

### 3. Run the backend
```
./mvnw spring-boot:run
```

