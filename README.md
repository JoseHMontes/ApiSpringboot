# API Spring Boot CRUD

REST API built with Spring Boot for managing `Person` records. The project exposes basic CRUD endpoints, persists data in MySQL, and uses DTO mapping with MapStruct.

## Tech Stack

- Java 21
- Spring Boot 4.0.6
- Spring Web MVC
- Spring Data JPA
- MySQL 8.4
- Lombok
- MapStruct
- Maven Wrapper
- Docker Compose

## Requirements

- Java 21
- Docker and Docker Compose
- Git

You do not need to install Maven globally because the project includes the Maven Wrapper.

## Database

The application expects a MySQL database with these values:

```yaml
url: jdbc:mysql://localhost:3306/Apipruebas
username: root
password: root
```

Start MySQL with Docker Compose:

```bash
docker compose up -d
```

The compose file creates:

- Container: `imagenApi`
- Database: `Apipruebas`
- Port: `3306`
- Root password: `root`

## Run the Application

On Windows:

```bash
.\mvnw.cmd spring-boot:run
```

On macOS/Linux:

```bash
./mvnw spring-boot:run
```

The API runs on the default Spring Boot port:

```text
http://localhost:8080
```

## Run Tests

```bash
.\mvnw.cmd test
```

The tests load the Spring application context, so MySQL must be running before executing them.

## API Endpoints

Base path:

```text
/person
```

| Method | Endpoint | Description |
| --- | --- | --- |
| `GET` | `/person/` | Get all people |
| `GET` | `/person/{id}` | Get a person by ID |
| `POST` | `/person/` | Create a person |
| `PUT` | `/person/{id}` | Update a person |
| `DELETE` | `/person/{id}` | Delete a person |

## Person Payload

```json
{
  "name": "Jose Montes",
  "email": "jose@example.com",
  "phone": "3001234567",
  "password": "password123"
}
```

Validation rules:

- `name` cannot be empty.
- `email` cannot be empty and must have a valid email format.
- `phone` cannot be empty.
- `password` cannot be empty and must have at least 8 characters.

## Example Requests

Create a person:

```bash
curl -X POST http://localhost:8080/person/ \
  -H "Content-Type: application/json" \
  -d '{
    "name": "Jose Montes",
    "email": "jose@example.com",
    "phone": "3001234567",
    "password": "password123"
  }'
```

Get all people:

```bash
curl http://localhost:8080/person/
```

Get one person:

```bash
curl http://localhost:8080/person/1
```

Update a person:

```bash
curl -X PUT http://localhost:8080/person/1 \
  -H "Content-Type: application/json" \
  -d '{
    "name": "Jose H. Montes",
    "email": "jose.montes@example.com",
    "phone": "3007654321",
    "password": "newpass123"
  }'
```

Delete a person:

```bash
curl -X DELETE http://localhost:8080/person/1
```

## Project Structure

```text
src/main/java/com/CrudProject/API
|-- controller
|-- dto
|-- exceptions
|-- mappers
|-- models
|-- repository
`-- service
```

## Build

```bash
.\mvnw.cmd clean package
```

The generated artifact is created under `target/`.
