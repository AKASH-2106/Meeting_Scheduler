Introduction
The central objective of this initiative is to enhance the efficiency of the meeting management process, focusing on scheduling, coordination, and attendance. By providing a platform for users to define their availability, this application aims to facilitate the identification of suitable time slots for various activities such as discussions, brainstorming sessions, and project updates.
Scheduling meetings traditionally involves manual coordination, resulting in inefficiencies and potential conflicts. This project seeks to overcome these challenges by introducing an automated and user-friendly solution.

Project Significance
The scheduling of meetings remains a common challenge in organizational workflows. An effective meeting scheduling system contributes to increased productivity, efficiency, and improved time management. Automation of this process further aids in conflict reduction, providing valuable support to organizations.

Tech Stack 
Spring Boot: 
A Java-based framework for building enterprise-level applications. Spring Boot provides a simplified and rapid development environment for building RESTful web services.
MySQL:
A relational database management system. MySQL is used for storing user data, meeting/event details, availability information, and notifications.
MongoDB:
A NoSQL database which provides scalable and flexible storage for meeting scheduler data with JSON-like document structures. Its schema-less design allows for easy adaptation to evolving data requirements and efficient handling of unstructured data.
RESTful APIs: 
Used for communication between the frontend and backend components of the application. RESTful APIs facilitate the exchange of data in a stateless manner over HTTP.
JSON Web Token (JWT): 
Used for authentication and authorization. JWT is a compact and self-contained mechanism for securely transmitting information between parties as a JSON object.
Postman: 
A popular API development tool used for testing and debugging RESTful APIs. Postman is utilized for testing the endpoints of the application.

System Architecture
1.     Authorization and Authentication Microservice:
Responsible for user authentication and controlling access to resources, utilizing JSON Web Tokens (JWT) for authentication.
2.     User Management:
Manages user registration, login, and related operations, validating users through the Auth microservice. Interacts with the User table for user information.
3.     Meeting/Event Creation:
Users create events, and details are stored in the Event relation. The Auth Microservice ensures the validity of users and participants. Invitations are sent based on information in the User relation.
4.     Meeting Scheduler:
Meetings are scheduled based on participant availability stored in the invite relation. The intersection of available times confirms the meeting, analysed through the invite table.
5.  Notification Sender:
Notifies invitees about confirmed meeting details, utilizing email servers. Details are provided by the scheduler microservice.
6. Communication:
User interaction occurs via RESTful APIs, with an API Gateway serving as the entry point, directing requests to the relevant microservices. Microservices communicate using the HTTP protocol.

Application Flow
The user can register his/her login information using the Authentication microservice.
When the user logs in to create an event, the user is first authenticated.
Once the authentication is successful, the user creates an event using the event creation microservice. The user passes the event name, event description, meet ID, meet start time, meet end time, meet duration and the email IDs of the participants as arguments.
These parameters are stored in the event database, and an invite is generated and sent to all the participants using an email service.
The participants reply by making a POST request with their corresponding free time and duration. This data is stored in the invite (availability) database.
The scheduling microservice makes a GET request to the event creation microservice with the meeting ID. The response would contain all the users with that meeting ID. 
This response is processed by the scheduling microservice, to find out the common timings by intersection.The scheduling microservice makes a POST request, sending the time intersection, along with the participants' email IDs to the notification microservice. These timings are notified to the corresponding user email ID, by the notification microservice, and the meeting is thus fixed.

Microservice Implementation details
Authentication Microservice:
This ensures that the user is registered and the credentials are correct. Endpoints have been created for registering the user (signup), generating the JWT token (authentication) and verifying the JWT.
POST http://localhost:8080/api/v1/user - to signup.
POST http://localhost:8080/api/v1/auth/generate_token - to generate JWT token/authentication.
POST http://localhost:8080/api/v1/auth/verify_jwt - to verify JWT.

Event Creation Microservice:
Used by the employee who wishes to organize an event/meeting. It contains the following attributes such as id, name, description, meet_start_time and meet_end_time. End points have been created for the event creation microservice
POST http://localhost:8080/api/event
The status of the event can also be checked and tested using the postman POST and GET request.

User Management Microservice:
This microservice is used by the admin to monitor the user details, and it uses the authentication microservice to validate the user details.
It can also be used in the future, along with the authentication microservice, to change passwords by the client, or to provide various security add-ons.


Meeting Creation Microservice:
To create an event, make a POST request to the end point /api/event with the following sample data format. This is then stored in event DB.
{
 "name": "Checking the invite part lets see ",
        "description": "Let us see if the invite will be sent or not",
        "meetid": 17,
        "meet_start_time": "15:30:00",
        "meet_end_time": "16:00:00",
        "duration": 25,
        "participants_email": [
            "attadaramprasad2001@gmail.com",
            "hrushikeshj.201cs220@nitk.edu.in",
            "akashprasad.201cs205@nitk.edu.in"
        ]
}
An invite message is sent to all the participants, using an email service in the following format: 
String message = "You are invited to a meeting.\n";
message += "Description: " + event.getDescription() + "\n";
message += "Meetid: " + event.getMeetid() +"\n";
message += "Start Time: " + event.getMeet_start_time() + "\n";
message += "End Time: " + event.getMeet_end_time() + "\n";
message+= "Duration: " + event.getDuration() + "\n";
Participants can respond with their free time by making a POST request in the following sample format:
{
    "emailid": "attadaramprasad.201cs210@nitk.edu.in",
    "meetid": "20",
    "free_start_time": "13:30",
    "free_end_time": "16:00",
    "duration": 25
}

This data is stored in the invite (availability) table.

Meeting Scheduling Microservice:
The client makes a request to the scheduling microservice by passing the meeting id through the URL. The scheduling microservice makes a GET request to the event creation microservice by passing the meeting ID to get free times of all the participants with that meeting ID. The service then finds the best time to schedule the meeting which is suitable for the most number of participants. This is sent to the notification microservice, along with the participants’ email IDs using a POST request. 
The most suitable schedule is calculated using the duration of that particular meeting, and using an offset of 5 minutes(as is standard) to calculate all possible intervals and later checking for how many participants it is suitable for and the best is selected at the last. A variable called convenience is defined which represents the ratio of available participants to the total number of participants which is sent to the client asking to schedule the meeting. Based on that they can decide the status of the meeting. But in this project, a post request is made to Notification microservice which sends the final schedule to all the participants through email message.

Notification Microservice:
The Notification microservice uses the POST details from the scheduling microservice, and notifies the participants, through their email IDs, of the fixed meeting time by using an email server. The email format and call is as follows:
String message = "Meeting is scheduled.\n";
message += "Meetid: " + freeTime.get("meetid") +"\n";
message += "Start Time: " + freeTime.get("free_start_time") + "\n";
message += "End Time: " + freeTime.get("free_end_time") + "\n";
message += "Duration: " + freeTime.get("duration") + "\n";
emailService.sendEmail(freeTime.get("emailid"), "Meeting Scheduled", message);
Password for the app has to be generated from the email account and stored in applications.properties.

API Gateway:
The API gateway serves as a central entry point for all the requests coming to the client, and the API gateway routes them to the appropriate microservice. The API gateway also ensures client authorization by first verifying with authorization microservice.

Testing
For testing, we use Postman's testing capabilities to automate the validation of API responses and to ensure that each microservice behaves as expected. Debugging requests and responses are done using Postman's built-in tools, such as the console and network inspector.

Running the Project:
In each microservice folder run microservice/src/main/java/com/microserviceApplication.
Import Gateway.postman_collection to Postman app and run the requests required as per the application flow.