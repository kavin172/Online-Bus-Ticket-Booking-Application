# Bus Ticket Booking Application

## Introduction
This project is a Bus Ticket Booking Application, which allows users to register, log in, search for buses, book tickets,
and generate booking confirmation PDFs. The system is designed for both regular users and admin users. Admins can manage buses, 
including adding, updating, and deleting bus details. Additionally, I have attached a Drive link to view the Frontend part.
Make sure to read the Mandatory Information.

## Table of Contents
- [Features](#features)
- [Technologies Used](#technologies-used)
- [Running the Application](#running-the-application)
- [Prerequisites](#prerequisites)
- [Usage](#usage)
- [User Registration and Login](#user-registration-and-login)
- [Admin Functionalities](#admin-functionalities)
- [Booking Tickets](#booking-tickets)
- [Mandatory Information](#mandatory-information)
- [FrontEnd](#frontend)

## Features
- User registration and login
- Admin and User roles with different access controls
- Search for buses by origin, destination, and date
- Book bus tickets and specify passenger details
- View individual booking tickets
- Generate booking confirmation PDFs
- Admin functionalities to add, update, and delete bus details

## Technologies Used
- Java
- Spring Boot
- Spring Security
- Thymeleaf
- iTextPDF for PDF generation
- Lombok
- Jakarta Servlet API

## Running the Application

### Prerequisites
- Java 17 or higher
- Maven 3.6.3 or higher
- A relational database (e.g., MySQL, PostgreSQL)

### Usage

#### User Registration and Login
- Register a new user by accessing `/registration`.
- Login with registered credentials at `/login`.

#### Admin Functionalities
- Access the admin page at `/admin` after logging in as an admin.
- Add a new bus via `/addBus`.
- View all buses at `/viewAllBuses`.
- Find a bus by ID at `/findBusById`.
- Update bus details via `/updateByBus`.
- Delete a bus via `/delete/{serialNo}`.

#### Booking Tickets
- Search for buses from the user home page.
- Book a bus via `/bookBus/{busId}`.
- View bookings at `/bookings`.
- Generate PDF of a booking at `/generatePdf/{bookingId}`.

## Mandatory Information
**Important:** When evaluating this application for the first time, you must register first, then go to the MySQL table and change your ROLE to ADMIN. 
Otherwise, it will save the user role. For security reasons, the ADMIN role can only be added manually to the respective database.
Once you update your role to ADMIN, you can log in and access the admin page.

## FrontEnd
To see the frontend part of this project, please visit the [Drive link](**https://drive.google.com/drive/folders/1MNvok68W8YImrjn0FVI3SeChRxT_EBCc?usp=drive_link**).
