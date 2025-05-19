# Projet JEE Spring Angular JWT - Digital Banking

## partie 1: Backend

### 4. Key Components

## Running the Application
1. Ensure Java 17 is installed
2. Configure database properties in `application.properties`
3. Run using Maven: `mvn spring-boot:run`
4. Access APIs at `http://localhost:8080`


#### Services
- `BankAccountService`: Core business logic interface for banking operations
  - Account management
  - Customer management
  - Transaction processing
  - Account history tracking

#### Controllers
- `BankAccountRestApi`: REST endpoints for account operations
  - Account queries
  - Transaction history
  - Pagination support

#### Data Persistence
- JPA/Hibernate for data persistence
- Relationship mapping:
  - One-to-Many: Customer to Accounts
  - One-to-Many: Account to Operations
  - Single table inheritance for account types

## Error Handling
The application implements exception handling for various scenarios:
- `BankAccountNotFoundException`
- `CustomerNotFoundException`
- Standard error responses for API failures

## Initial Data Setup
The application includes a data initialization mechanism through `CommandLineRunner` that:
1. Creates sample customers
2. Generates test accounts
3. Performs sample transactions