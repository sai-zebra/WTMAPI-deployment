# Workcloud Task Management – MyWork API

[![Architecture](https://img.shields.io/badge/Architecture-Hexagonal-blueviolet)](https://en.wikipedia.org/wiki/Hexagonal_architecture_(software))
[![Java Version](https://img.shields.io/badge/java-17-blue.svg)](https://www.java.com)
[![Build Status](https://img.shields.io/badge/build-passing-brightgreen.svg)](https://github.com)
[![Test Coverage](https://img.shields.io/badge/coverage-90%25-brightgreen.svg)](https://github.com)

**An enterprise-grade API for the Workcloud Task Management ecosystem, built with Spring Boot and meticulously designed using Hexagonal Architecture.**

This project serves as a blueprint for building robust, scalable, and highly maintainable backend services. It demonstrates professional software engineering practices, including a decoupled architecture, a comprehensive multi-layered testing strategy, standardized error handling, and production-ready operational features.

---

## 📖 Table of Contents

1.  [**About The Project**](#about-the-project)
    *   [Core Capabilities](#core-capabilities)
2.  [**🏛️ Core Architecture**](#core-architecture)
    *   [Overall Architecture: The Modular Monolith](#overall-architecture-the-modular-monolith)
    *   [Module Architecture: The Hexagonal Approach](#module-architecture-the-hexagonal-approach)
    *   [Project Structure](#project-structure)
3.  [**🛠️ System Design & Engineering**](#system-design--engineering-practices)
    *   [Robust Error Handling](#robust-error-handling-strategy)
    *   [Comprehensive Testing Strategy](#project-testing-strategy)
    *   [Security First Design](#security-first-design)
    *   [Configuration Management](#configuration-management)
    *   [Logging & Observability](#logging--observability)
4.  [**🚀 Getting Started**](#getting-started)
    *   [Prerequisites](#prerequisites)
    *   [Local Setup](#local-setup--installation)
5.  [**🏗️ Build, Deployment, & CI/CD**](#deployment)
    *   [Local Builds](#running-local-builds)
    *   [CI/CD Pipeline Overview](#cicd-pipeline-overview)
    *   [Deployed Application Access](#accessing-the-deployed-application)
    *   [Workflow Diagram](#workflow-diagram-of-deployment)
6.  [**📜 API Specification**](#api-endpoints--specification)
    *   [Endpoint Summary](#api-endpoints-summary)

---

##  About The Project

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
## Core Architecture

##  Overall Architecture: The Modular Monolith

This project is architected as a **Modular Monolith**. Instead of a single, undifferentiated codebase, the application is partitioned into a set of loosely-coupled modules, where each module represents a distinct business capability (e.g., "User Management", "Feeds", "Surveys").

### Architectural Principles

*   **High Cohesion**: Each module encapsulates a specific part of the business domain.
*   **Low Coupling**: Modules communicate through well-defined public APIs (e.g., Java interfaces or events), not by accessing each other's internal implementation details. This prevents the system from becoming a tangled "big ball of mud".
*   **Independent Development**: Teams can work on different modules in parallel with minimal friction.
*   **Clear Boundaries**: The separation between business domains is made explicit in the codebase structure.

This approach provides many of the benefits of microservices (like clear boundaries and scalability of development) while avoiding the operational complexity of a distributed system.

---

##  Module Architecture: The Hexagonal Approach

Within each module described above, we apply the **Hexagonal Architecture** (also known as Ports and Adapters). This pattern places the module's core business logic and domain at its center, completely isolated from external technologies and delivery mechanisms.

### Hexagonal Principles

*   **The Core is King**: The central hexagon contains pure, framework-independent business logic, domain models, and port interfaces. This is the heart of the module.
*   **Ports as Explicit Contracts**: Simple Java interfaces (Ports) define the contract for how the core logic interacts with the outside world.
*   **Adapters as Implementation Details**: Adapters implement the ports, connecting the core to external technologies like web frameworks (Spring MVC), databases (JPA/Hibernate), or message brokers.

This decoupling ensures that each module's core logic can be tested in isolation and that its technical dependencies can be swapped out with minimal impact.

---


##  Project Structure

The directory structure directly reflects both the Modular Monolith and Hexagonal concepts.

```text
WTMMyWorkAPI-parent/
│
├── application/                   // Spring Boot entry point module
│   └── src/main/java/
│       └── com/jayzebra/application/
│           └── Application.java
│
├── feeds-module/                  // --- MODULE 1: Feeds ---
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
├── user-module/                   // --- MODULE 3: User ---
│   ├── core/                      // THE HEXAGON
│   │   ├── domain/
│   │   ├── port/
│   │   └── service/
│   └── adapters/                  // ADAPTERS
│       ├── in/
│       └── out/
│
├── rtm/                           // --- MODULE 4: RTM ---
│   └── ...
│
├── common/                        // Optional: For code shared between modules
│   └── ...
│
├── .gitignore
├── mvnw
├── mvnw.cmd
└── pom.xml                        // Parent POM for all modules



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

## System Design & Engineering Practices

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

Our testing strategy is layered, starting with a strong foundation of unit tests and enhanced by mutation testing to ensure not just coverage, but true effectiveness. This approach leverages the clean separation provided by the Hexagonal Architecture to guarantee our core business logic is robust, correct, and reliable.

### Unit Tests: The Foundation

*   **Purpose**: To validate the core business logic and domain rules in complete isolation from any external dependencies or frameworks (like the web layer or database).

*   **Scope**:
    *   **What is tested**: All classes within the `core` package of each module. This includes `services`, `domain` objects, and any custom business `exceptions`.
    *   **How it's tested**: Outbound ports (interfaces to the database, message brokers, etc.) are replaced with "test doubles" or "mocks". This allows us to simulate any external behavior and verify that our core logic behaves correctly under all conditions.

*   **Key Benefits**:
    *   **Speed**: These tests execute in milliseconds because they don't require a Spring context or external infrastructure.
    *   **Precision**: When a test fails, it points directly to a bug in the business logic, not a configuration issue.
    *   **Architectural Enforcement**: This strategy reinforces the architectural boundaries, keeping the business logic pure and framework-independent.

### Mutation Testing: Ensuring Test Effectiveness

While unit tests verify that our code works as expected, mutation testing verifies that our *tests are effective enough to detect faults*. We go beyond simple line coverage to measure the true quality of our test suite.

*   **Purpose**: To challenge our existing unit tests by introducing small, deliberate defects (mutations) into the production code and checking if the tests can find them.

*   **How it Works**:
    1.  The mutation testing engine (Pitest) modifies the compiled bytecode on the fly (e.g., changing a `>` to a `<=`).
    2.  It then runs our unit tests against this "mutated" code.
    3.  If the tests fail, the mutant is considered "killed" – a sign of a good, effective test. If the tests still pass, the mutant "survives," revealing a gap in our testing.

*   **Our Quality Standard**: We have achieved a **mutation coverage score of 88% across all modules**. This high score demonstrates that our test suite is not just comprehensive in scope, but is also highly effective at catching potential bugs before they reach production.

*   **Technology**:
    *   **JUnit 5**: The primary framework for writing tests.
    *   **Mockito**: Used to create mock implementations of our outbound port interfaces.
    *   **Pitest (PIT)**: The leading mutation testing framework for Java, used to measure the effectiveness of our tests.

---

### Running the Tests

To run the full suite of tests, use the following commands from the project root:

*   **Run All Unit Tests:**
    ```bash
    ./mvnw clean test
    ```

*   **Run Mutation Tests (Pitest):**
    ```bash
    ./mvnw org.pitest:pitest-maven:mutationCoverage
    ```
    After running, an HTML report will be generated in the `target/pit-reports` directory of each module, showing exactly which mutants were killed or survived.

---
## Mutation Testing Reports
### Feeds-module
![Feeds Module](assets/PitReportFeedsModule.png)
### RTM-module
![RTM](assets/PitReportRTM%20.png)
### Survey-module
![Survey](assets/Surveys.png)
### User-module
![User](assets/UserModule.png)
---

### Security First Design

Security is a primary concern, implemented via **Spring Security**.

*   **Authentication**: The API is secured using **HTTP Basic Authentication**. Clients must send a valid, Base64-encoded username and password in the `Authorization` header with each request.
*   **Authorization**: Endpoint access is controlled by user roles. Access is enforced declaratively using method-level annotations (e.g., `@PreAuthorize("hasRole('USER')")`) or configured centrally for URL patterns.
*   **Configuration**: All security rules, CORS policies, and public endpoints (like `/actuator/health`) are defined in a dedicated `SecurityConfig` class for clear and centralized management.
*   **Stateless by Design**: As a REST API, the service is stateless. CSRF protection is disabled, as it is not needed for the non-browser-based clients this API is intended for.

### Configuration Management

The application uses **Spring Profiles** to manage environment-specific configurations. This allows for different settings for local development and production without changing the code.

All property files are located in the `application/src/main/resources/` directory.

---

#### `application.properties` (Common Properties)

This file contains properties that are shared across all environments. It's a good place for the application name, database dialect, and connection pool settings.

```properties
# Application name
spring.application.name=application

# JPA / Hibernate Settings
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.PostgreSQLDialect

# Default Hikari Connection Pool Settings
spring.datasource.hikari.maximum-pool-size=3
spring.datasource.hikari.minimum-idle=1
spring.datasource.hikari.idle-timeout=300000

```

### Logging & Observability

To provide visibility into the application's state, especially for debugging in a live environment, the following features are configured:

*   **Standard Logging**: The application utilizes Spring Boot's default logging framework (Logback). Log output is sent to the console and can be configured in `application.properties` to adjust log levels for specific packages, helping to trace issues without restarting the application.

*   **Live Diagnostics with Spring Boot Actuator**: For real-time debugging and inspection, **Spring Boot Actuator** is configured to expose a wide range of internal endpoints. This provides a direct window into the running application to check its health, view metrics, understand the environment, and more.

*   **Exposed Endpoints for Debugging**: By setting `management.endpoints.web.exposure.include=*`, all Actuator endpoints are made available. This is intentionally done to aid in production debugging. Some of the key endpoints include:
    *   `/actuator/health`: Provides a simple up/down status of the application and its connections (e.g., to the database).
    *   `/actuator/env`: Displays the current environment properties, showing exactly what configuration the application is running with.
    *   `/actuator/metrics`: Allows you to view a list of available metrics and inspect their current values (e.g., `jvm.memory.used`, `hikaricp.connections.active`).
    *   `/actuator/httptrace`: Shows a trace of the last 100 HTTP request-response pairs, which is invaluable for debugging unexpected client or server behavior.

---

##  Getting Started

Follow these instructions to get a copy of the project up and running on your local machine.

### Prerequisites

*   Java JDK 17 or later
*   Maven 3.6 or later
*   (Optional) Docker for running a PostgreSQL database instance.

### Local Setup & Installation

1.  **Clone the repository:**
    ```sh
    https://github.com/JaykumarZebra/WTMAPI.git
    cd WTMAPI
    ```


2**Build the project:**
    This command will compile the code and run all the unit and integration tests.
    ```sh
    mvn clean install
    ```

3**Run the application:**
    Activate the `local` profile to use your local configuration.
    ```sh
    java -jar -Dspring.profiles.active=local target/mywork-api-1.1.0.jar
    ```
    The API will be available at `http://localhost:8080`.

---

##  Deployment

Our application is containerized using **Docker** and deployed in **EKS**. The deployment and CI/CD pipeline are managed by **Jenkins, and ArgoCD**, which ensures a scalability and high availabiity.

### Accessing the Deployed Application

Once the application is deployed, you can access the API documentation and interact with the endpoints through Swagger UI.

*   **Swagger UI Link:**
    ```sh
     a01d31d4152984c61bdf8d2fee4e216d-485902948.ap-south-1.elb.amazonaws.com/swagger-ui/index.html
    ```
*   **Password:** 
    ```sh
     1017eaea-63ce-4b44-8ee7-2cfd27f18fea
    ```

This interface provides a convenient way to explore and test the API in the deployed environment.

## Workflow Diagram Of Deployment
![Workflow Diagram of Deployment](assets/Recording%202026-02-02%20112057.gif)


##  API Endpoints & Specification

The full API contract is defined using **OpenAPI 3.1.0**. The specification file serves as the single source of truth for all API interactions.

*   **[openapi.yaml](assets/openapi.yaml)**

It can also be viewed interactively via Swagger UI at `http://localhost:8080/swagger-ui.html` when the application is running.


---

##  API Endpoints Summary

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


