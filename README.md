# Marketing Insights API

A proof of concept Java 21 Spring Boot project for demonstrating a simple marketing anylitics dashboard, for internal use.
Exposes a REST API for managing marketing campaigns and viewing basic performance metrics.  
The project uses an in-memory H2 database, JPA/Hibernate, DTO-based layering, and includes a small web UI served from the backend.

---

## Requirements

- Java 21 (e.g. Eclipse Temurin 21.0.9)
- Maven 3.9+
- Internet access to download Maven dependencies

---

## How to Run the Application

From the project root:

```bash
mvn clean package
mvn spring-boot:run

The application will start on http://localhost:8080.

The simple frontend dashboard is available at:

http://localhost:8080/

The REST API base URL is:

http://localhost:8080/api/campaigns

API Endpoints
Method	Path	Description
GET	/api/campaigns	List all campaigns
GET	/api/campaigns/{id}	Get a campaign by ID
POST	/api/campaigns	Create a new campaign
PUT	/api/campaigns/{id}	Update an existing campaign
DELETE	/api/campaigns/{id}	Delete a campaign
GET	/api/campaigns/{id}/summary	Get performance summary for a campaign

Example JSON for creating a campaign:

{
  "name": "Spring Launch 2026",
  "description": "Launch campaign for the new product line.",
  "startDate": "2026-03-01",
  "endDate": "2026-03-31",
  "budget": 50000.0,
  "status": "ACTIVE",
  "impressions": 250000,
  "clicks": 12000,
  "conversions": 800,
  "cost": 42000.0
}

H2 Database Console

The application uses an in-memory H2 database with demo data loaded at startup.

Console URL: http://localhost:8080/h2-console

JDBC URL: jdbc:h2:mem:marketingdb

Username: sa

Password: (empty)

The schema is created by Hibernate (ddl-auto: update), and data.sql is applied afterwards. This is ensured via:

spring:
  jpa:
    defer-datasource-initialization: true
  sql:
    init:
      mode: always

Running Tests

To execute all unit and controller tests:

mvn test


Test coverage includes:

Service layer unit tests using JUnit 5 and Mockito (no Spring context loaded).

Controller tests using @WebMvcTest and MockMvc.

Project Structure
marketing-insights-api/
├─ pom.xml
├─ README.md
├─ postman/
│  └─ marketing-insights-api.postman_collection.json
├─ src/
│  ├─ main/
│  │  ├─ java/
│  │  │  └─ com/github/mlwilli/marketing/
│  │  │     ├─ MarketingInsightsApiApplication.java
│  │  │     ├─ controller/
│  │  │     │  └─ CampaignController.java
│  │  │     ├─ dto/
│  │  │     │  ├─ CampaignCreateRequest.java
│  │  │     │  ├─ CampaignDto.java
│  │  │     │  ├─ CampaignSummaryDto.java
│  │  │     │  ├─ CampaignUpdateRequest.java
│  │  │     │  └─ ErrorResponse.java
│  │  │     ├─ entity/
│  │  │     │  ├─ Campaign.java
│  │  │     │  └─ CampaignStatus.java
│  │  │     ├─ exception/
│  │  │     │  ├─ GlobalExceptionHandler.java
│  │  │     │  └─ ResourceNotFoundException.java
│  │  │     ├─ mapper/
│  │  │     │  └─ CampaignMapper.java
│  │  │     ├─ repository/
│  │  │     │  └─ CampaignRepository.java
│  │  │     └─ service/
│  │  │        ├─ CampaignService.java
│  │  │        └─ impl/
│  │  │           └─ CampaignServiceImpl.java
│  │  └─ resources/
│  │     ├─ application.yml
│  │     ├─ data.sql
│  │     └─ static/
│  │        ├─ index.html
│  │        └─ js/
│  │           └─ app.js
│  └─ test/
│     └─ java/
│        └─ com/github/mlwilli/marketing/
│           ├─ controller/
│           │  └─ CampaignControllerTest.java
│           └─ service/
│              └─ CampaignServiceImplTest.java

Using the Postman Collection

Import the file:

postman/marketing-insights-api.postman_collection.json

into Postman to quickly exercise all API endpoints against http://localhost:8080.


This is ready to drop into a new repository and run as-is.
::contentReference[oaicite:0]{index=0}
