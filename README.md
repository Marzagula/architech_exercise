
# Architech Exercise

A brief description of what this project does and who it's for

Here’s the complete setup and running instructions for both the backend and frontend of your project, taking into account that the frontend was created using Angular 18 and the backend was developed in Java 21.
Prerequisites

Java 21: Make sure Java 21 is installed on your machine. You can verify this by running:
       
       java -version
    
Angular CLI :  If you want to install it globally, use:

       npm install -g @angular/cli

If you prefer a local installation, use (this is sufficient to run the app, especially if you won’t use it in the future):

       npm install @angular/cli

Node.js: Ensure Node.js is installed. You can verify this by running:
       
       node -v

Backend Setup (Java 21)

Navigate to the Backend Directory:

you can do it by opening command line and then use this:

       cd <path to application>/backend/user-registration
Build the Project: If you are in **backend/user-registration** directory then you can use Maven to build the backend project:

Project is using Java 21 so you need at least Maven 3.9 version, you can check maven version by using:

       mvn -version

result should look like this:

       Apache Maven 3.9.9 (8e8579a9e76f7d015ee5ec7bfcdc97d260186937)
       Maven home: <path to maven>\apache-maven-3.9.9
       Java version: 21.0.4, vendor: Oracle Corporation, runtime: <path to java>\Java\jdk-21
       Default locale: pl_PL, platform encoding: UTF-8
       OS name: "windows 10", version: "10.0", arch: "amd64", family: "windows"

If version is at least 3.9 and Java 21 then you can build project by using:
        
       mvn clean install

Run the Backend: Start the backend server:
       
       mvn spring-boot:run

Frontend Setup (Angular 18)
Navigate to the Frontend Directory:

you can do it by opening command line and then use this:
       
       cd <path to application>frontend/user-registration-app

Install Dependencies: Use npm to install the required packages:
       
       npm install


Run the Frontend: Start the Angular development server:
if you installed or already have Angular globally then use:
        ng serve

if you installed Angular locally for this project then use:

        npx ng serve

This will compile the Angular application and start a local server, accessible at http://localhost:4200.

Accessing the Application

    • Backend: The backend will be accessible at http://localhost:8080 by default.
    • Frontend: The frontend should be accessible at http://localhost:4200 by default.
Notes

    • Ensure that both the backend and frontend are running simultaneously for the application to function correctly.


If you went through everything frontend application and backend application should be running and you can use it by going to http://localhost:4200

There are two endpoints:



       
       GET /api/v1/users 

Returns a paginated list of users from the database.

       POST /api/v1/users/register

Creates and persists a user from the provided **UserRegistrationDTO** in the request body.