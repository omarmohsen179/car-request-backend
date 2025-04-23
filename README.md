# Car Buying App

A Spring Boot application that facilitates the process of buying cars through a platform where customers can request cars and suppliers can submit offers.

## Features

### Customer Requests
- Create requests for buying cars (imported or local)
- Specify inspection company for car verification
- Track request status (PENDING, IN_PROGRESS, COMPLETED, CANCELLED)
- View supplier offers for their requests

### Supplier Offers
- Submit offers for customer requests
- Include price and car condition details
- Track offer status (PENDING, ACCEPTED, REJECTED)
- View customer requests

### Inspection Integration
- Support for multiple inspection companies (AutoCheck and VehiVerify)
- Automatic car inspection upon request creation
- Inspection score tracking

## Models

### CustomerRequest
- ID
- Customer details (name, contact info)
- Car details (make, model, year, etc.)
- Request type (IMPORTED or LOCAL)
- Inspection company
- Inspection score
- Request status
- Creation timestamp
- List of supplier offers

### SupplierOffer
- ID
- Supplier details (name, contact info)
- Associated customer request
- Price
- Car condition details
- Offer status
- Creation timestamp

### InspectionCompany
- Company name
- Contact information
- Inspection capabilities

## Technical Stack

- Java 17
- Spring Boot 3.2.3
- Spring Data JPA
- MySQL 8.0
- Maven
- Docker
- Swagger/OpenAPI 3.0

## Getting Started

### Prerequisites
- Java 17
- Maven
- MySQL 8.0
- Docker (optional)

### Installation

1. Clone the repository
2. Configure database connection in `application.properties`
3. Build the project:
   ```bash
   mvn clean install
   ```
4. Run the application:
   ```bash
   mvn spring-boot:run
   ```

### Docker Deployment

1. Build the Docker image:
   ```bash
   docker build -t car-buying-app .
   ```
2. Run the container:
   ```bash
   docker run -p 8080:8080 car-buying-app
   ```

## API Documentation

The API documentation is available at:
```
http://localhost:8080/swagger-ui.html
```

## Testing

The application includes:
- Unit tests for services and controllers
- Integration tests for API endpoints
- Database migration tests

Run tests with:
```bash
mvn test
```

## Validation Rules

- Customer requests must include:
  - Valid customer information
  - Complete car details
  - Selected inspection company
- Supplier offers must include:
  - Valid supplier information
  - Price greater than 0
  - Detailed car condition information

## Database Migrations

The application uses Flyway for database migrations. Migration scripts are located in:
```
src/main/resources/db/migration
```

## Contributing

1. Fork the repository
2. Create a feature branch
3. Commit your changes
4. Push to the branch
5. Create a Pull Request

## License

This project is licensed under the MIT License.
