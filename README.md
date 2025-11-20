# Spring Boot Concurrency Sample

A Spring Boot application demonstrating concurrency concepts using a water tank management system. This project
showcases thread-safe operations, scheduled tasks, and concurrent data access patterns.

## Overview

This application simulates a water tank management system where:

- Multiple water tanks are managed concurrently
- Each tank has a configurable leak rate that automatically drains water
- An automatic renewal system refills tanks when they run low
- REST API endpoints allow querying and adding water to tanks
- All operations are thread-safe using concurrent data structures

## Technologies

- **Spring Boot**: 3.5.8
- **Java**: 21
- **Build Tool**: Maven
- **Key Dependencies**:
    - Spring Web
    - Spring HATEOAS
    - Lombok
    - Jersey Client
    - Jakarta Servlet API
    - Jakarta Annotation API

## Features

- **Concurrent Water Tank Management**: Manages 10 water tanks simultaneously using ConcurrentHashMap
- **Automatic Water Leaks**: Each tank has a configurable leak rate that drains water at scheduled intervals
- **Auto-Renewal System**: Automatically refills tanks when capacity drops below 5%
- **Thread-Safe Operations**: All water addition/removal operations are synchronized
- **RESTful API**: Provides endpoints to query and manipulate water tanks

## Prerequisites

- Java 21 or higher
- Maven 3.6+

## Building the Application

```bash
# Clean and build the project
mvn clean package

# Build without tests
mvn clean package -DskipTests
```

## Running the Application

```bash
# Using Maven
mvn spring-boot:run

# Using the JAR file
java -jar target/spring-boot-concurrency-sample-0.0.1-SNAPSHOT.jar
```

The application will start on port **8080**.

## Configuration

Water tanks are configured in `application.properties`:

```properties
server.port=8080
# Water Tank Configuration
app.watertanks[0].id=1
app.watertanks[0].maxCapacity=50
app.watertanks[0].currentCapacity=46
app.watertanks[0].waterLeakRate=60
app.watertanks[0].literOfLeak=1
# ... (10 tanks configured)
```

Each tank configuration includes:

- `id`: Unique identifier
- `maxCapacity`: Maximum water capacity in liters
- `currentCapacity`: Initial water level in liters
- `waterLeakRate`: Time in seconds between leaks
- `literOfLeak`: Amount of water to leak each time

## API Endpoints

### 1. Get All Water Tanks

Retrieves information about all water tanks.

```bash
GET http://localhost:8080/getAllWatertanks
```

**Example:**

```bash
curl http://localhost:8080/getAllWatertanks
```

**Response:**

```json
{
  "statusType": "OK",
  "entity": [
    {
      "id": 1,
      "maxCapacity": 50.0,
      "currentCapacity": 45.0,
      "waterLeakRate": 60,
      "literOfLeak": 1.0
    },
    ...
  ],
  "status": 200
}
```

### 2. Query Max Capacity

Retrieves the maximum capacity of a specific water tank.

```bash
GET http://localhost:8080/QueryMaxCapacity?id={tankId}
```

**Example:**

```bash
curl "http://localhost:8080/QueryMaxCapacity?id=1"
```

**Response:**

```json
{
  "statusType": "OK",
  "entity": "50.0",
  "status": 200
}
```

### 3. Query Current Capacity

Retrieves the current water level of a specific water tank.

```bash
GET http://localhost:8080/QueryCurrentCapacity?id={tankId}
```

**Example:**

```bash
curl "http://localhost:8080/QueryCurrentCapacity?id=1"
```

**Response:**

```json
{
  "statusType": "OK",
  "entity": "45.0",
  "status": 200
}
```

### 4. Add Water

Adds water to a specific water tank.

```bash
GET http://localhost:8080/AddWater?liter={amount}&id={tankId}
```

**Example:**

```bash
curl "http://localhost:8080/AddWater?liter=5&id=1"
```

**Response:**

```json
{
  "statusType": "OK",
  "entity": "true",
  "status": 200
}
```

Returns `true` if water was added successfully, `false` if the tank doesn't have enough capacity.

## Concurrency Features

### 1. Water Leak Simulation

Each water tank has a scheduled task that simulates water leaking:

- Runs at configured intervals (waterLeakRate)
- Safely decrements the water level
- Prevents negative water levels

### 2. Auto-Renewal System

A background task monitors all tanks every 15 seconds:

- Checks if tank is empty or below 5% capacity
- Automatically refills to a random level between 50% and (max - 5)
- Ensures tanks remain operational

### 3. Thread-Safe Operations

- Uses `ConcurrentHashMap` for tank storage
- Synchronized access to individual tank operations
- Lazy bean initialization to resolve circular dependencies

## Project Structure

```
src/main/java/com/hendisantika/
├── SpringBootConcurrencySampleApplication.java  # Main application class
├── config/
│   ├── WatertankInMemoryDB.java                # In-memory storage and initialization
│   └── WebMvcConfiguration.java                # Web MVC configuration
├── controller/
│   └── WatertankController.java                # REST API endpoints
├── model/
│   └── Watertank.java                          # Water tank data model
├── service/
│   ├── WatertankManager.java                   # Business logic and concurrency management
│   └── WatertankService.java                   # Data access service
└── util/
    └── WatertankHttpClientUtils.java           # HTTP client utilities
```

## Key Implementation Details

### Circular Dependency Resolution

The application uses `@Lazy` annotations to resolve circular dependencies between:

- WatertankInMemoryDB
- WatertankManager
- WatertankService

### Concurrency Management

1. **ScheduledExecutorService**: Used for leak simulation and auto-renewal tasks
2. **ConcurrentHashMap**: Thread-safe storage for water tanks
3. **Synchronized Blocks**: Ensures atomic operations on individual tanks

## Testing

Run the tests using:

```bash
mvn test
```

## Known Warnings

The application may display a warning about synchronizing on a value-based class (Integer). This is expected behavior in
the demonstration code and doesn't affect functionality.

## Author

- **Name**: hendisantika
- **Email**: hendisantika@gmail.com
- **Telegram**: @hendisantika34

## License

This project is a sample demonstration application for educational purposes.

## Version

- **Version**: 0.0.1-SNAPSHOT
- **Last Updated**: 2025-11-21
