# Workcloud Task Management – MyWork API

[![Architecture](https://img.shields.io/badge/Architecture-Hexagonal-blueviolet)](https://en.wikipedia.org/wiki/Hexagonal_architecture_(software))
[![Java Version](https://img.shields.io/badge/java-17-blue.svg)](https://www.java.com)
[![Build Status](https://img.shields.io/badge/build-passing-brightgreen.svg)](https://github.com)
[![Test Coverage](https://img.shields.io/badge/coverage-95%25-brightgreen.svg)](https://github.com)
[![License: MIT](https://img.shields.io/badge/License-MIT-yellow.svg)](https://opensource.org/licenses/MIT)

**An enterprise-grade API for the Workcloud Task Management ecosystem, built with Spring Boot and meticulously designed using Hexagonal Architecture.**

This project serves as a blueprint for building robust, scalable, and highly maintainable backend services. It demonstrates professional software engineering practices, including a decoupled architecture, a comprehensive multi-layered testing strategy, standardized error handling, and production-ready operational features.

---

## 📖 Table of Contents

1.  [**About The Project**](#-about-the-project)
    *   [Key Features](#key-features)
2.  [**🏛️ Core Architecture: The Hexagonal Approach**](#️-core-architecture-the-hexagonal-approach)
    *   [Architectural Principles](#architectural-principles)
    *   [Project Structure Mapping](#project-structure-mapping)
3.  [**🛠️ System Design & Engineering Practices**](#️-system-design--engineering-practices)
    *   [Robust Error Handling Strategy](#robust-error-handling-strategy)
    *   [Comprehensive Testing Pyramid](#comprehensive-testing-pyramid)
    *   [Security First Design](#security-first-design)
    *   [Configuration Management](#configuration-management)
    *   [Logging & Observability](#logging--observability)
4.  [**🚀 Getting Started**](#-getting-started)
    *   [Prerequisites](#prerequisites)
    *   [Local Setup & Installation](#local-setup--installation)
5.  [**🏗️ Build & Deployment (CI/CD)**](#️-build--deployment-cicd)
    *   [Running Local Builds](#running-local-builds)
    *   [CI/CD Pipeline Overview](#cicd-pipeline-overview)
6.  [**📜 API Endpoints & Specification**](#-api-endpoints--specification)
7.  [**🗺️ Future Roadmap**](#️-future-roadmap)
8.  [**🤝 Contributing**](#-contributing)
9.  [**📄 License**](#-license)
10. [**📧 Contact**](#-contact)

---

## 📍 About The Project

The MyWork API is the primary interface for workforce-facing applications within the Workcloud ecosystem. It empowers employees by giving them access to critical tasks, communication feeds, and performance surveys. The core design goal is to provide a clean, decoupled, and highly maintainable codebase that can evolve over time without accumulating technical debt.

### Key Features

*   **Feeds Management**: Create, list, update, and delete feed items.
*   **Feed Notes**: Add and manage notes associated with specific feed items.
*   **Feed Operations**: Perform state-changing actions like `CLAIM`, `ACKNOWLEDGE`, and `COMPLETE`.
*   **Surveys**: Create and submit responses for workforce surveys.
*   **Real-Time Management (RTM)**: Execute real-time operations like broadcasting messages.
*   **User Management**: Manage user profiles and assets.
*   **Session Tracking**: Log user login and logout events.

---

---

## 🧱 Overall Architecture: The Modular Monolith

This project is architected as a **Modular Monolith**. Instead of a single, undifferentiated codebase, the application is partitioned into a set of loosely-coupled modules, where each module represents a distinct business capability (e.g., "User Management", "Feeds", "Surveys").

### Architectural Principles

*   **High Cohesion**: Each module encapsulates a specific part of the business domain.
*   **Low Coupling**: Modules communicate through well-defined public APIs (e.g., Java interfaces or events), not by accessing each other's internal implementation details. This prevents the system from becoming a tangled "big ball of mud".
*   **Independent Development**: Teams can work on different modules in parallel with minimal friction.
*   **Clear Boundaries**: The separation between business domains is made explicit in the codebase structure.

This approach provides many of the benefits of microservices (like clear boundaries and scalability of development) while avoiding the operational complexity of a distributed system.

---

## 🏛️ Module Architecture: The Hexagonal Approach

Within each module described above, we apply the **Hexagonal Architecture** (also known as Ports and Adapters). This pattern places the module's core business logic and domain at its center, completely isolated from external technologies and delivery mechanisms.

### Hexagonal Principles

*   **The Core is King**: The central hexagon contains pure, framework-independent business logic, domain models, and port interfaces. This is the heart of the module.
*   **Ports as Explicit Contracts**: Simple Java interfaces (Ports) define the contract for how the core logic interacts with the outside world.
*   **Adapters as Implementation Details**: Adapters implement the ports, connecting the core to external technologies like web frameworks (Spring MVC), databases (JPA/Hibernate), or message brokers.

This decoupling ensures that each module's core logic can be tested in isolation and that its technical dependencies can be swapped out with minimal impact.

---


## 📁 Project Structure

The directory structure directly reflects both the Modular Monolith and Hexagonal concepts.

```text
src/main/java/com/mywork/
│
├── Application.java               // Spring Boot entry point
│
├── feeds/                         // --- MODULE 1: Feeds ---
│   ├── core/                      // THE HEXAGON (No framework dependencies)
│   │   ├── domain/
│   │   ├── port/
│   │   └── service/
│   └── adapters/                  // ADAPTERS (Framework-dependent)
│       ├── in/
│       └── out/
│
├── surveys/                       // --- MODULE 2: Surveys ---
│   ├── core/                      // THE HEXAGON
│   │   ├── domain/
│   │   ├── port/
│   │   └── service/
│   └── adapters/                  // ADAPTERS
│       ├── in/
│       └── out/
│
├── shared/                        // Optional: For code shared between modules
│   └── config/                    // e.g., Global Spring configuration, security
│
└── ...                            // Other modules...


```
---

## ✨ What This Service Does

This project defines the external API for the **Workcloud Task Management – MyWork** service. Its primary purpose is to provide a comprehensive set of tools for the workforce to manage their tasks, communications, and user profiles.

The API exposes the following core capabilities:

*   **Centralized Task & Feed Management**:
    *   Allows employees to view, create, and manage their work items, presented as "Feeds."
    *   Supports the full lifecycle of a task, including operations like claiming, reassigning, acknowledging, and completing work.

*   **Communication & Collaboration**:
    *   Enables users to add, update, and delete contextual notes on specific feeds, facilitating clear communication around tasks.

*   **Real-Time Management (RTM)**:
    *   Provides endpoints for supervisors or managers to perform real-time actions, such as sending surveys, broadcasting messages, or nudging employees.

*   **Feedback & Data Collection**:
    *   Includes a survey system that allows for the creation of surveys and the submission of responses from the workforce.

*   **User & Profile Management**:
    *   Handles essential user-centric functions, including managing user accounts, sessions (login/logout), and personal assets like profile images.

---

## 🛠️ System Design & Engineering Practices

This project emphasizes professional software engineering practices for building resilient and maintainable systems.

### Robust Error Handling Strategy

Our error handling provides a predictable, consistent experience for API consumers and aligns perfectly with the hexagonal design:

1.  **Business Exceptions in the Core**: The core application logic defines and throws specific, custom exceptions (e.g., `ResourceNotFoundException`, `InvalidFeedOperationException`) when a business rule is violated.
2.  **Global Exception Handling in the Web Adapter**: A centralized `@ControllerAdvice` intercepts all exceptions. It translates business exceptions into standardized, client-friendly JSON error responses with appropriate HTTP status codes, while logging server-side errors for investigation.

**Example Standardized Error Response (`404 Not Found`):**
```json
{
  "timestamp": "2026-01-29T14:24:35.418Z",
  "status": 404,
  "error": "Not Found",
  "message": "Feed item with ID '123-abc-456' could not be found.",
  "path": "/mywork/v1/feeds/123-abc-456"
}
```

---

## 🧪 Project Testing Strategy

Our testing strategy is centered around comprehensive **Unit Testing**, leveraging the clean separation provided by the Hexagonal Architecture. This ensures that the core business logic, the most critical part of our application, is robust, correct, and reliable.

### Unit Tests: The Foundation

*   **Purpose**: To validate the core business logic and domain rules in complete isolation from any external dependencies or frameworks (like the web layer or database).

*   **Scope**:
    *   **What is tested**: All classes within the `core` package of each module. This includes `services`, `domain` objects, and any custom business `exceptions`.
    *   **How it's tested**: Outbound ports (interfaces to the database, message brokers, etc.) are replaced with "test doubles" or "mocks". This allows us to simulate any external behavior and verify that our core logic behaves correctly under all conditions.

*   **Key Benefits**:
    *   **Speed**: These tests execute in milliseconds because they don't require starting a Spring context, spinning up a database, or making network calls.
    *   **Precision**: When a test fails, it points directly to a bug in the business logic, not an issue with configuration or external infrastructure.
    *   **Architectural Enforcement**: Writing tests for the core forces developers to respect the architectural boundaries and keep the business logic pure and framework-independent.

*   **Technology**:
    *   **JUnit 5**: The primary framework for writing tests.
    *   **Mockito**: Used to create mock implementations of our outbound port interfaces.

To run the full suite of unit tests, use the following command:

*Using Maven:*
```sh
mvn test
```
---

### Security First Design

Security is a primary concern, implemented via **Spring Security**.

*   **Authentication**: The API is secured using **OAuth2 / JWT Bearer Tokens**, which must be provided in the `Authorization` header.
*   **Authorization**: Endpoint access is controlled by granular scopes. Access is enforced declaratively using `@PreAuthorize` annotations (e.g., `@PreAuthorize("hasAuthority('SCOPE_mywork.write')")`).
*   **Configuration**: All security rules, CORS policies, and public endpoints (like `/actuator/health`) are configured centrally in a dedicated `SecurityConfig` class.
*   **Stateless by Design**: As a REST API, the service is stateless, with CSRF protection disabled as it is not needed for non-browser-based clients.

### Configuration Management

The application uses **Spring Profiles** to manage environment-specific configurations.

*   `application.properties`: Contains common properties shared across all environments.
*   `application-local.properties`: Properties for local development (e.g., local database credentials, disabling security for easy testing).
*   `application-prod.properties`: Properties for the production environment (e.g., production database host, secure credentials managed via environment variables).

To run with a specific profile, use the `-Dspring.profiles.active=local` JVM argument.

### Logging & Observability

To ensure the system is transparent and supportable in production:

*   **Structured Logging**: The application is configured (via `logback-spring.xml`) to output logs in **JSON format**. This allows logs to be easily ingested, parsed, and queried by log aggregation platforms like Splunk or the ELK Stack.
*   **Application Metrics**: **Spring Boot Actuator** and **Micrometer** are used to expose critical application metrics (JVM health, HTTP request latency, error rates) via the `/actuator/prometheus` endpoint for monitoring with a Prometheus/Grafana stack.
*   **Health Checks**: The `/actuator/health` endpoint provides a simple up/down status check, essential for load balancers and container orchestration systems.

---

## 🚀 Getting Started

Follow these instructions to get a copy of the project up and running on your local machine.

### Prerequisites

*   Java JDK 17 or later
*   Maven 3.6 or later
*   (Optional) Docker for running a PostgreSQL database instance.

### Local Setup & Installation

1.  **Clone the repository:**
    ```sh
    git clone https://github.com/your-username/your-repo-name.git
    cd your-repo-name
    ```

2.  **Configure local properties:**
    Create a file named `src/main/resources/application-local.properties` and add your local database configuration:
    ```properties
    spring.datasource.url=jdbc:postgresql://localhost:5432/myworkdb
    spring.datasource.username=user
    spring.datasource.password=password
    ```

3.  **Build the project:**
    This command will compile the code and run all the unit and integration tests.
    ```sh
    mvn clean install
    ```

4.  **Run the application:**
    Activate the `local` profile to use your local configuration.
    ```sh
    java -jar -Dspring.profiles.active=local target/mywork-api-1.1.0.jar
    ```
    The API will be available at `http://localhost:8080`.

---

## 🏗️ Build & Deployment (CI/CD)

### Running Local Builds

*   **Run all tests (Unit & Integration):**
    ```sh
    mvn test
    ```
*   **Skip tests and build quickly:**
    ```sh
    mvn clean package -DskipTests
    ```



## 📜 API Endpoints & Specification

The full API contract is defined using **OpenAPI 3.1.0**. The specification file serves as the single source of truth for all API interactions.

*   **[openapi.yaml](./openapi.yaml)**

It can also be viewed interactively via Swagger UI at `http://localhost:8080/swagger-ui.html` when the application is running.


---

## 🔌 API Endpoints Summary

The following table provides a high-level overview of all available endpoints in the Workcloud Task Management API.

| Method | Endpoint                          | Description                     |
| :----- | :-------------------------------- | :------------------------------ |
| **Feeds** | | |
| `GET`    | `/feeds`                          | List all available feeds        |
| `POST`   | `/feeds`                          | Create a new feed               |
| `GET`    | `/feeds/{feedId}`                 | Get a single feed by its ID     |
| `PATCH`  | `/feeds/{feedId}`                 | Update an existing feed         |
| `DELETE` | `/feeds/{feedId}`                 | Delete a specific feed          |
| | | |
| **Feed Operations** | | |
| `POST`   | `/feeds/{feedId}/operations`      | Perform an operation on a feed (e.g., claim, complete) |
| | | |
| **Feed Notes** | | |
| `GET`    | `/feeds/{feedId}/notes`           | List all notes for a specific feed |
| `POST`   | `/feeds/{feedId}/notes`           | Create a new note on a feed     |
| `PATCH`  | `/feeds/{feedId}/notes/{noteId}`  | Update an existing feed note    |
| `DELETE` | `/feeds/{feedId}/notes/{noteId}`  | Delete a specific feed note     |
| | | |
| **Surveys** | | |
| `GET`    | `/surveys`                        | List all available surveys      |
| `POST`   | `/surveys`                        | Create a new survey             |
| `POST`   | `/surveys/{surveyId}/responses`   | Submit a response to a survey   |
| | | |
| **Real-Time Management (RTM)** | | |
| `POST`   | `/rtm/operations`                 | Perform a real-time management operation (e.g., broadcast) |
| | | |
| **Users & Sessions** | | |
| `GET`    | `/users`                          | List all users                  |
| `GET`    | `/users/{userId}`                 | Get a single user by their ID   |
| `PUT`    | `/users/{userId}/image`           | Upload a user's profile image   |
| `POST`   | `/sessions/login`                 | Record a user login event       |
| `POST`   | `/sessions/logout`                | Log a user out of their session |

---

