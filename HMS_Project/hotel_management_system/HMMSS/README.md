                   Hotel Management System Documentation
1. Overview
     The Hotel Management System is a Java-based application designed to manage various aspects of a  hotel, including guest information, room booking, room availability, and more. The system interacts with a MySQL database to store and retrieve data.

2. Modules
  a. HMS_MENU
     The main class that serves as the entry point to the Hotel Management System.
     It provides a menu-driven interface for users to perform actions such as adding guests, viewing    rooms, booking rooms, and more.
     User authentication is implemented to ensure secure access.
  b. Guests
     Manages the addition of guest information to the system.
     Validates guest details such as ID, first name, last name, email, and phone number.
     Checks for duplicate guest IDs to ensure data integrity.  
  c. ViewGuests
     Displays a list of all guests in the system.
     Retrieves guest information from the MySQL database and prints it in a tabular format.
  d. DeleteGuest
     Allows users to delete guest records based on the guest ID.
     Checks if the guest exists before deletion to prevent errors.
  e. BookRoom
     Handles the booking of rooms by guests.
     Validates guest and room information, checks availability, and updates the booking status.
  f. ViewRoom
     Displays a list of all rooms in the hotel.
     Retrieves room information from the MySQL database and includes details such as room number, capacity, price per night, and booking status.
  g. Modifybooking
     Enables users to modify booking details, such as check-in and check-out dates.
     Validates user input and updates the booking information in the database.
  h. CancelBook
     Allows users to cancel bookings based on the booking ID.
     Checks for the existence of the booking before cancellation.
3. Database Schema
     The MySQL database named HMS contains three tables: guests, bookings, and rooms.
     Relationships between tables are maintained using foreign keys.
4. Usage
     Users interact with the system through the console-based menu provided by HMS_MENU.
     Input validation ensures the integrity of data stored in the system.
5. Additional Features
     Clear screen functionality is implemented for better user interface experience.
     Proper error handling and messages are provided to guide users and prevent unexpected behaviors.
6. Future Improvements
     Enhancements could include adding more features such as billing, reporting, and user roles.
     Improvements in user interface design for better user experience.
7. Notes
     The application uses JDBC for database connectivity.
     Ensure that the MySQL JDBC driver is available in the classpath.
8. Instructions
     Compile and run the HMS_MENU class to start the Hotel Management System.
