This is my first application Java,Spring Boot, Maven and MySQL datebase that can be used as RESTful API webservice that will be responsible for managing and storing in database simple notes.

To run this project: 

a. To run this application you need: Spring Tool Suite and MySQL Workbench


b. I have to add configuration: application.properties. Spring Boot Spring Boot will take care of the rest.
You have to concect to user@localhost and select rows from the table: notes.


c.To build and run the project:

In Spring Tool Suite follow below steps: right click on -->FirstSpringBootRESTfulApi --> Run as --> Spring Boot App.


d. Example usages:

// get all Notes
curl https://localhost:8080/api/getNotes

// get Notes updated for more than a month
curl https://localhost:8080/api/getNotesMonth

// create a new Note
curl https://localhost:8080/api/saveNotes

// create a new Note and return a map of errors (if they occurred)
curl https://localhost:8080/api//savemapNotes

// get a single Note by id/ update or delete Note
curl https://http://localhost:8080/api/getNotesById/{id}


For testing the application I used: Advanced REST client


