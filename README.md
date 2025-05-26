# Projet JEE Spring Angular JWT - Digital Banking

## Introduction
Cette application de gestion bancaire (E-Banking) permet de gérer des clients, des comptes bancaires (courants et épargnes) et des opérations bancaires (retraits, versements, virements). L'application offre une interface REST pour interagir avec ces différentes entités.

## Technologies Utilisées
- Spring Boot 3.4.5
- Spring Data JPA
- Spring MVC avec REST
- MySQL Database
- Lombok
- Swagger/OpenAPI pour la documentation
- Angular (pour la partie frontend)
- JWT pour l'authentification

## Architecture de l'application

### Architecture technique
L'application E-Banking suit une architecture en couches classique d'une application Spring Boot:

- **Couche Présentation**: Contrôleurs REST exposant les API
- **Couche Service**: Logique métier et orchestration des opérations
- **Couche DAO**: Accès aux données via les repositories Spring Data JPA
- **Couche Entités**: Modèles de données JPA
- **Couche DTO**: Objets de transfert de données pour les API
- **Couche Mappers**: Conversion entre entités et DTOs

### Modèle de données
Les principales entités sont:
- **Customer**: Représente un client de la banque
- **BankAccount**: Classe abstraite représentant un compte bancaire
- **CurrentAccount**: Compte courant avec possibilité de découvert
- **SavingAccount**: Compte épargne avec taux d'intérêt
- **AccountOperation**: Opérations effectuées sur un compte

## Key Components

### Services
- `BankAccountService`: Core business logic interface for banking operations
  - Account management
  - Customer management
  - Transaction processing
  - Account history tracking

### Controllers
- `BankAccountRestApi`: REST endpoints for account operations
  - Account queries
  - Transaction history
  - Pagination support

### Data Persistence
- JPA/Hibernate for data persistence
- Relationship mapping:
  - One-to-Many: Customer to Accounts
  - One-to-Many: Account to Operations
  - Single table inheritance for account types

## Error Handling
The application implements exception handling for various scenarios:
- `BankAccountNotFoundException`
- `CustomerNotFoundException`
- `BalanceNotSufficientException`
- Standard error responses for API failures

## Initial Data Setup
The application includes a data initialization mechanism through `CommandLineRunner` that:
1. Creates sample customers
2. Generates test accounts
3. Performs sample transactions

## Running the Application
1. Ensure Java 17 is installed
2. Configure database properties in `application.properties`
3. Run using Maven: `mvn spring-boot:run`
4. Access APIs at `http://localhost:8085`

## API Documentation
The API is documented using Swagger/OpenAPI and can be accessed at:
```
http://localhost:8085/swagger-ui.html
```

## API Endpoints

### Customer Management
- `GET /customers`: List all customers
- `GET /customers/{id}`: Get customer by ID
- `POST /customers`: Create new customer
- `PUT /customers/{id}`: Update customer
- `DELETE /customers/{id}`: Delete customer

### Account Management
- `GET /accounts`: List all accounts
- `GET /accounts/{accountId}`: Get account details
- `GET /accounts/{accountId}/operations`: Get account operations
- `GET /accounts/{accountId}/pageOperations?page=0&size=5`: Get paginated account operations

### Transaction Operations
- Credit, debit and transfer operations are available through the service layer

## Configuration
Database configuration in `application.properties`:
```properties
spring.application.name=ebanking-backend
server.port=8085
spring.datasource.url=jdbc:mysql://localhost:3306/E-BANK?createDatabaseIfNotExist=true
spring.datasource.username=root
spring.datasource.password=
spring.jpa.hibernate.ddl-auto=update
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MariaDBDialect
spring.jpa.show-sql=true
```

