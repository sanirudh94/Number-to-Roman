# Roman Numeral Web Service
This project is a web service that converts integer values to Roman numerals. It provides an HTTP endpoint to perform conversions for single numbers as well as ranges and uses JAVA based Spring Boot Application.

## Table of Contents

- [Features](#features)
- [Technologies Used](#technologies-used)
- [Installation](#installation)
- [Project Layout](#project-layout)
- [Usage](#usage)
- [Testing](#testing)
- [Running with Docker](#running-with-docker)
- [DevOps Capabilities](#devops-capabilities)

## Features

- Convert individual integers to Roman numerals.
- Convert ranges of integers to their corresponding Roman numeral representations.
- Robust error handling for invalid input and unexpected errors.

## Technologies Used

- Java
- Spring Boot
- Maven
- JUnit 5
- Mockito
- Docker
- Prometheus (for metrics)
- Grafana (for monitoring)
- Logback (for logging)

## Installation
### Prerequisites
1. Java Installed
2. Maven installed

To run the Number To Roman locally, follow these steps:

1. Clone the repository:

   ```bash
   git clone https://github.com/yourusername/number-to-roman.git
2. Build the project using Maven:
   ```bash
   mvn clean package
3. Run the application:
   ```
   java -jar target/number-to-roman.jar
The application will start up, and you can access the web service at http://localhost:8080/romannumeral.

## Project Layout
```
  Number-To-Roman/
  ├── src/
  │   ├── main/
  │   │   ├── java/
  │   │   │   └── romannumerals/
  │   │   │       ├── NumberToRomanApplication.java
  │   │   │       ├── controller/
  │   │   │       │   └── RomanNumeralController.java
  │   │   │       ├── service/
  │   │   │       │   └── RomanNumeralService.java
  │   │   │       |── output/
  │   │   │       │   └── RomanNumeralRangeResponse.java
  |   |   |           └── RomanNumeralResponse.java
  │   │   │       ├── util/
  │   │   │       │   └── RomanNumeralConverter.java
  │   │   └── resources/
  │   │       └── application.properties
  │   ├── test/
  │   │   └── java/
  │   │       └── romannumerals/
  │   │           ├── NumberToRomanApplicationTests.java
  │   │           ├── controller/
  │   │           │   └── RomanNumeralControllerTest.java
  │   │           ├── service/
  │   │           │   └── RomanNumeralServiceTest.java
  │   │           └── util/
  │   │               └── RomanNumeralConverterTest.java
  ├── Dockerfile
  ├── README.md
  ├── pom.xml
```

## Usage
To use the Number To Roman, make HTTP requests to the following endpoints:

  ```/romannumeral?query={integer}```: Convert a single integer to its Roman numeral representation. {integer} must be any integer value in the range 1-3999.
  
  **Request**: 
  ```bash
  curl http://localhost:8080/romannumeral?query=28
  ```
  **Response**:
  ```bash
    {
      "input": "28",
      "output": "XXVIII"
    }
```

```/romannumeral?min={min}&max={max}```: Convert a range of integers from {min} to {max} (inclusive) to their corresponding Roman numerals. {min} and {max} must be provided, and both must be integers in the range 1-3999. The min value must be less than the max value.

  **Request**: 
  ```bash
  curl http://localhost:8080/romannumeral?min=1&max=3
  ```
  **Response**:
  ```bash
    {
      "conversions": [
        {
          "input": "1",
          "output": "I"
        },
        {
          "input": "2",
          "output": "II"
        },
        {
          "input": "3",
          "output": "III"
        }
      ]
    }
```

## Testing
The project includes both unit tests and integration tests to ensure the correctness and robustness of the code. To run the tests, use the following command:

```bash
mvn test
```

## Running with Docker
To run the Number To Roman within Docker, follow these steps:

1. Make sure Docker is installed on your machine. You can download and install Docker Desktop from the official website.

2. Build the Docker image for the Number To Roman:
   ```
   docker build -t number-to-roman .
   ```
3. Run the Docker container using the built image:
   ```
   docker run -p 8080:8080 number-to-roman
This command will start a Docker container from the number-to-roman image and map port 8080 of the container to port 8080 on your host machine. As a result, you can access the Number To Roman service at http://localhost:8080/romannumeral.

## DevOps Capabilities

### Logging
The project uses Logback for logging. Logback is configured via src/main/resources/logback-spring.xml to output logs to the console and a file.

### Metrics
The project uses Prometheus for metrics collection. Metrics are exposed at /actuator/prometheus and can be scraped by a Prometheus server.

### Docker
To containerize the application, a Dockerfile is provided. Follow the instructions in the "Running with Docker" section to build and run the Docker container.

### Health Checks
You can access health and metrics endpoints to monitor the application's health and performance:

**Health Check**: ```http://localhost:8080/actuator/health```

**Prometheus Metric**s: ```http://localhost:8080/actuator/prometheus```




