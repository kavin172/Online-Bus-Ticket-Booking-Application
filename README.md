# Online-Bus-Ticket-Booking-Application

ntroduction
This project is a Bus Ticket Booking Application, which allows users to register, log in, search for buses, book tickets, and generate booking confirmation PDFs. The system is designed for both regular users and admin users. Admins can manage buses, including adding, updating, and deleting bus details. Make sure to read the Mandatory Information.

Table of Contents
Features
Technologies Used
Running the Application
Prerequisites
Usage
User Registration and Login
Admin Functionalities
Booking Tickets
Mandatory Information
FrontEnd
Features
User registration and login
Admin and User roles with different access controls
Search for buses by origin, destination, and date
Book bus tickets and specify passenger details
View individual booking tickets
Generate booking confirmation PDFs
Admin functionalities to add, update, and delete bus details
Technologies Used
Java
Spring Boot
Spring Security
Thymeleaf
iTextPDF for PDF generation
Lombok
Jakarta Servlet API
Running the Application
Prerequisites
Java 17 or higher
Maven 3.6.3 or higher
A relational database (e.g., MySQL, PostgreSQL)
Usage
User Registration and Login
Register a new user by accessing /registration.
Login with registered credentials at /login.
Admin Functionalities
Access the admin page at /admin after logging in as an admin.
Add a new bus via /addBus.
View all buses at /viewAllBuses.
Find a bus by ID at /findBusById.
Update bus details via /updateByBus.
Delete a bus via /delete/{serialNo}.
Booking Tickets
Search for buses from the user home page.
Book a bus via /bookBus/{busId}.
View bookings at /bookings.
Generate PDF of a booking at /generatePdf/{bookingId}.
