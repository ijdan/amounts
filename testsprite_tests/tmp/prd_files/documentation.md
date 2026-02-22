# Application Documentation: Number to Text Converter

## 1. Introduction
The **Number to Text Converter** is a Java-based backend service designed to convert numeric input (integers) into their textual representation in multiple languages. It currently supports French, English, and Spanish.

## 2. Core Functionality
- **Multi-language Support**: Converts numbers to text in:
    - French (`FR`)
    - English (`EN`)
    - Spanish (`ES`)
- **Wide Range**: Supports conversion of large numbers (up to Trillions/Billions depending on language rules implemented).
- **REST API**: Exposes the functionality via a simple HTTP GET endpoint.

## 3. Architecture
The application follows the **Hexagonal Architecture** (Ports and Adapters) pattern to ensure a clean separation of concerns and testability.

### Layers:
1.  **Domain (Core Logic)**:
    - Contains the business rules for number conversion.
    - Defined in `com.ijdan.amounts.corelogic`.
    - Key components: `NumberToTextConverter`, `LanguageFactory`, `CommonRules`, and language-specific implementations (`FrenchLanguage`, `EnglishLanguage`, `SpanishLanguage`).
    - **Ports**: Interfaces defining interactions with the outside world (`NumberToTextConverterPort`, `ParametersRetrievalPort`).

2.  **Adapters**:
    - **Driving (Left) Adapters**: REST API controller handling incoming requests (`NumberToTextConverterRestAPI`).
    - **Driven (Right) Adapters**: Implementations for retrieving parameters (e.g., `RecuperationParametresDepuisLaBase`).

3.  **Configuration**:
    - Spring Boot configuration (`DomainConfiguration`) wires the domain components together using dependency injection.

## 4. API Reference

### Convert Number to Text
**Endpoint**: `GET /T1.0/amounts/{langue}/value/{value}`

**Parameters**:
- `langue` (Path Variable): The target language code (e.g., `FR`, `EN`, `ES`). Defaults to `FR` if invalid/null handling is triggered (though path variables are mandatory).
- `value` (Path Variable): The numeric string to convert.

**Response**:
Returns a JSON object containing the converted text.

**Example Request**:
`GET /T1.0/amounts/EN/value/123`

**Example Response**:
```json
{
  "message": "One hundred and twenty-three",
  "langue": "EN",
  "input": "123"
}
```

## 5. Technology Stack
- **Language**: Java 17
- **Framework**: Spring Boot 3.4.2
- **Build Tool**: Maven
- **Testing**: JUnit 5, AssertJ, Spring Boot Test

## 6. Setup and Usage

### Prerequisites
- Java 17 JDK
- Maven (wrapper included)

### Building the Application
```bash
./mvnw clean package
```

### Running the Application
```bash
./mvnw spring-boot:run
```
The server will start on port `8081` (defined in `application.properties`).

### Running Tests
```bash
./mvnw clean test
```

## 7. Configuration
- **Port**: Configured in `src/main/resources/application.properties` (default: `8081`).
- **Domain Beans**: Configured in `com.ijdan.amounts.configuration.DomainConfiguration`.

## 8. Extensibility
To add a new language:
1.  Create a new class extending `AbstractLanguage` in `com.ijdan.amounts.corelogic.ManagerLangue.Langues`.
2.  Implement the specific rules for that language.
3.  Register the new language bean in `DomainConfiguration` and update `LanguageFactory`.
