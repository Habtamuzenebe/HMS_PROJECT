package hotel_management_system;

import java.sql.*;
import java.util.Scanner;

//*****************************************************************************    
// method to Modify booking
//*****************************************************************************
public class Modifybooking {
    char choice;

    public void modifyBooking(Statement stmt, Scanner scanner) {
        try {
            System.out.print(Color.bold);
            System.out.print(Color.Magenta);
            System.out.println("\n\t\t+=========================+");
            System.out.println("\t\t|\tModify Booking    |");
            System.out.println("\t\t+=========================+\n");
            System.out.print(Color.Reset);
            char loop;
            System.out.print(Color.BrightCyan);
            System.out.print("\tif you want to continue say (Y/y) else return to menu: ");
            choice = scanner.next().charAt(0);
            if (choice == 'Y' || choice == 'y') {
                System.out.print(Color.BrightCyan);
                do {
                    System.out.print("\t\tEnter Booking ID: ");
                    int bookingId = scanner.nextInt();

                    System.out.print("\t\tEnter new Check-in Date (YYYY-MM-DD): ");
                    String newCheckInDate = scanner.next();

                    System.out.print("\t\tEnter new Check-out Date (YYYY-MM-DD): ");
                    String newCheckOutDate = scanner.next();

                    String updateBookingQuery = "UPDATE bookings SET check_in_date = '" + newCheckInDate
                            + "', check_out_date = '" + newCheckOutDate + "' WHERE booking_id = " + bookingId;

                    int rowsAffected = stmt.executeUpdate(updateBookingQuery);

                    if (rowsAffected > 0) {
                        System.out.println(
                                "----------------------------------------------------------------------------------");
                        System.out.print(Color.BrightGreen);
                        System.out.println("\t\tBooking details modified successfully.");
                        System.out.print(Color.Reset);
                    } else {
                        System.out.println(
                                "---------------------------------------------------------------------------------");
                        System.out.print(Color.BrightRed);
                        System.out.println("\t\tBooking ID not found. No changes were made.");
                        System.out.print(Color.Reset);
                    }
                    System.out.print("\tDo you want to modify another booking press (Y/y) else return : ");
                    loop = scanner.next().charAt(0);
                    System.out.print(Color.BrightCyan);
                } while (loop == 'Y' || loop == 'y');
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
    }
}
