# IFM-Test-Vehicle-Service

Client test creating backend spring boot for vehicle service system

## How-To Run the Program

### Option 1 - Run directly the jar file included in the repo

- Make sure you have Java 21 installed in your computer
- Clone the repo in your local
- Open command prompt and go to the folder where you clone the repo
- Go to compiled folder (cd compiled)
- Run this command "java -jar vehicle-service-system-0.0.1-SNAPSHOT.jar"
- Don't close your command prompt and open your browser
- in browser you can go to http://localhost:8080/swagger-ui/index.html to see all the api documentation
- try to test the API through Swagger or Postman

### Option 2 - Build the Jar file using maven

- Make sure you have Java 21 and maven installed in your desktop
- Clone the repo in your local
- Open command prompt and go to the folder where you clone the repo
- Run this command "mvn clean package"
- after finish the process change directory to target folder
- Run this command "java -jar vehicle-service-system-0.0.1-SNAPSHOT.jar"
- Don't close your command prompt and open your browser
- in browser you can go to http://localhost:8080/swagger-ui/index.html to see all the api documentation
- try to test the API through Swagger or Postman