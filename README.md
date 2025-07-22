# Job Application Tracker API

A RESTful API built with Spring Boot to track job applications during a job search. This project helps users manage company names, job titles, application dates, and statuses.

## Tech Stack
- **Java 21**
- **Spring Boot 3.3**
- **Spring Data JPA**
- **Maven**
- **H2 In-Memory Database**

## API Endpoints

| HTTP Method | Endpoint                       | Description                                |
|-------------|--------------------------------|--------------------------------------------|
| `GET`       | `/api/applications`            | Get all job applications.                  |
| `GET`       | `/api/applications?status=...` | Get applications filtered by status.       |
| `GET`       | `/api/applications/{id}`       | Get a single application by its ID.        |
| `POST`      | `/api/applications`            | Create a new job application.              |
| `PUT`       | `/api/applications/{id}`       | Update an existing application.            |
| `DELETE`    | `/api/applications/{id}`       | Delete an application by its ID.           |

## How to Run Locally
1. Clone the repository: `git clone https://github.com/your-username/job-application-tracker-api.git`
2. Navigate to the project directory: `cd job-application-tracker-api`
3. Run the application using Maven: `mvn spring-boot:run`
4. The API will be available at `http://localhost:8080`.