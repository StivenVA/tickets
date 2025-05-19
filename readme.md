# Ticket Management Project

## Prerequisites

Before running this project, ensure the following are installed and properly configured on your system:

- [Docker](https://www.docker.com/)
- SQL Server (via Docker container or installed locally)

---

## Step 1: Build the Docker Image

Navigate to the root of the project and run the following command to build the Docker image:

```
docker build -t tickets .
```

## Step 2: Run SQL Server
You have two options to run SQL Server:

### Option A: Using Docker
Execute the following command to run SQL Server as a Docker container:

```
docker run -e "ACCEPT_EULA=Y" -e "SA_PASSWORD=Password123!" -p 1433:1433 --name sqlserver -d mcr.microsoft.com/mssql/server:2022-latest
```

Once the container is running, create a database named tickets.

If you encounter an error like:

```
CREATE DATABASE failed. Some file names listed could not be created. Check related errors.

```

Restart the container using the following command:

```
docker restart sqlserver
```
Then try creating the database again.

### Option B: Installed Locally
If you have SQL Server installed locally on your machine, update the Spring configuration file:

Replace this line:

```
spring.datasource.url=jdbc:sqlserver://host.docker.internal:1433;databaseName=tickets
```

with:

```
spring.datasource.url=jdbc:sqlserver://localhost:1433;databaseName=<your_database_name>
```

You can name the database however you like, just make sure to reflect the change in the connection URL.

## Step 3: Create the Stored Procedure
Once the database is up and running (either via Docker or locally), create the following stored procedure in your database (e.g., tickets):

```sql
CREATE PROCEDURE StoreUnresolvedTickets
AS
BEGIN
    INSERT INTO unresolved_tickets (created_at, ticket_id)
    SELECT 
        -- Converts the current server time to Colombia's time zone
        SYSDATETIMEOFFSET() AT TIME ZONE 'SA Pacific Standard Time',
        ticket_id
    FROM tickets t
    WHERE ticket_status != 'RESOLVED'
      AND NOT EXISTS (
          SELECT 1 FROM unresolved_tickets u WHERE u.ticket_id = t.ticket_id
      )
END
```
This stored procedure is required by the Spring application to store unresolved tickets daily.

## (Optional) Step 4: Create a Trigger
If you want unresolved tickets to be automatically removed when a ticket is resolved, create the following trigger in your database:

```sql
CREATE TRIGGER deleteResolvedTicketsOnUnresolvedTickets
ON tickets
AFTER UPDATE
AS
BEGIN
    DELETE FROM unresolved_tickets WHERE ticket_id IN (
        SELECT ticket_id FROM tickets WHERE ticket_status = 'RESOLVED'
    )
END
```

You may customize this trigger to fit your requirements.

## Step 5: Run the application
Once everything is set up, you can run your Spring Boot application using the Docker image you built earlier:

```
docker run -p 8080:8080 tickets
```

Or for the case you are running sql server locally run the application with your IDE

Your application will be available at: http://localhost:8080

## Notes
Make sure the SQL Server instance is running before starting the Spring Boot app.

Ensure the database name and credentials match your application.properties or application.yml configuration.