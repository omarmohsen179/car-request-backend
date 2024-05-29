
# Medical Appointment Management System

## Overview

This is a Spring Boot application for managing medical appointments. It allows the clinic admin to review appointments by date or patient, add new appointments, cancel appointments with a reason, filter appointments by date and patient name, and preview patient appointment history. The application uses PostgreSQL as the database and provides Swagger UI for API documentation.

## Features

- List today's appointments
- Add new appointments
- Cancel appointments with a reason
- Filter appointments by date (future or history)
- Filter appointments by patient name
- Preview patient appointment history

## Technologies Used

- Spring Boot
- Spring Data JPA
- Hibernate
- PostgreSQL
- Swagger (Springdoc OpenAPI)
- Docker
- Docker Compose
- Maven

## Prerequisites

- Docker
- Docker Compose

## Building and Running the Application

### Using Maven

To build the application using Maven, run:

\`\`\`bash
./mvnw clean package -DskipTests
\`\`\`

### Using Docker Compose
run command:  docker-compose -f docker-compose.yml  up --build

#### Docker Compose Setup

Ensure you have the following \`docker-compose.yml\` file:

\`\`\`yaml
version: '3.8'

services:
postgres:
image: postgres:13
container_name: postgres
environment:
POSTGRES_DB: clinic
POSTGRES_USER: user
POSTGRES_PASSWORD: password
ports:
- "5432:5432"
volumes:
- postgres-data:/var/lib/postgresql/data

app:
build: .
container_name: medical-appointment-system
environment:
SPRING_DATASOURCE_URL: jdbc:postgresql://postgres:5432/clinic
SPRING_DATASOURCE_USERNAME: user
SPRING_DATASOURCE_PASSWORD: password
SPRING_JPA_HIBERNATE_DDL_AUTO: update
ports:
- "8080:8080"
depends_on:
- postgres

volumes:
postgres-data:
\`\`\`

#### Dockerfile

Ensure you have the following \`Dockerfile\` in the root directory of your project:

\`\`\`Dockerfile
# Stage 1: Build the application
FROM maven:3.8.5-openjdk-17 AS builder

# Set the working directory
WORKDIR /app

# Copy the pom.xml file and download dependencies
COPY pom.xml .
RUN mvn dependency:go-offline

# Copy the entire project and build the application
COPY . .
RUN mvn clean package -DskipTests

# Stage 2: Create the final image
FROM openjdk:17-jdk-slim

# Set the working directory
WORKDIR /app

# Copy the JAR file from the builder stage
COPY --from=builder /app/target/medical-0.0.1-SNAPSHOT.jar /app/medical-appointment-system.jar

# Expose port 8080 to the outside world
EXPOSE 8080

# Run the JAR file
ENTRYPOINT ["java", "-jar", "medical-appointment-system.jar"]
\`\`\`

#### Build and Run Containers

To build the Docker image and run the containers, use the following command:

\`\`\`bash
docker-compose up --build
\`\`\`

### Accessing the Application

- **Swagger UI**: [http://localhost:8080/swagger-ui/index.html](http://localhost:8080/swagger-ui/index.html)

### Configuration

The application uses environment variables to configure the database connection. These are set in the \`docker-compose.yml\` file.

\`\`\`yaml
environment:
SPRING_DATASOURCE_URL: jdbc:postgresql://postgres:5432/clinic
SPRING_DATASOURCE_USERNAME: user
SPRING_DATASOURCE_PASSWORD: password
SPRING_JPA_HIBERNATE_DDL_AUTO: update
\`\`\`

## API Endpoints

### Patient Endpoints

- **GET** \`/api/patients\`: Retrieve all patients
- **GET** \`/api/patients/{id}\`: Retrieve a patient by ID
- **POST** \`/api/patients\`: Create a new patient
- **PUT** \`/api/patients/{id}\`: Update an existing patient
- **DELETE** \`/api/patients/{id}\`: Delete a patient by ID

### Appointment Endpoints

- **GET** \`/api/appointments\`: Retrieve all appointments
- **POST** \`/api/appointments\`: Create a new appointment
- **PUT** \`/api/appointments/{id}/cancel\`: Cancel an appointment

## License

This project is licensed under the MIT License.
