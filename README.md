## Prerequisites

- Java JDK 21 or later
- Maven 3.6 or later
- Git

## Setup

1. Clone the repository:
   ```
   git clone https://github.com/spacolino/taxation-service.git
   cd taxation-service
   ```

2. Build the project:
   ```
   mvn clean install
   ```

## Running the Application

1. Start the application:
   ```
   mvn spring-boot:run
   ```

   The application will start and be accessible at `http://localhost:8080`.

## Using the API


1. Send a POST request to `http://localhost:8080/api/taxation/calculate` with a JSON body. For example:

   ```
   curl -X POST -H "Content-Type: application/json" -d '{"traderId":1,"playedAmount":5,"odd":3.2}' http://localhost:8080/api/taxation/calculate
   ```

2. The response will be a JSON object containing the taxation details, including:
    - Possible return amount
    - Possible return amount before tax
    - Possible return amount after tax
    - Tax rate
    - Tax amount

## Accessing the H2 Database Console

To view the stored data:

1. While the application is running, open a web browser and go to:
   ```
   http://localhost:8080/h2-console
   ```

2. On the login page, use these settings:
    - JDBC URL: `jdbc:h2:mem:taxationdb`
    - User Name: `test`
    - Password: `test123`

3. Click "Connect" to access the database console.

4. In the console, you can run SQL queries to check the data. For example:
   ```sql
   SELECT * FROM TAXATION_ENTITY;
   ```