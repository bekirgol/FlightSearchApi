1. Clone the project to your computer or download the ZIP file.

2. Create a database named "flight-search-db" in your MySQL database:

   ```sql
   CREATE DATABASE flight_search_db;
3. Edit the "application.properties" file of the application. Update the required database connection settings:

    ```
   spring.datasource.url=jdbc:mysql://localhost:3306/flight_search_db
   spring.datasource.username=your_username
   spring.datasource.password=your_password