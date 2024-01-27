package hotel_management_system;

import java.sql.*;
import java.util.Scanner;

//*****************************************************************************    
// method to Cancel booking
//*****************************************************************************
public class CancelBook {
    char choice;

    public void cancelBooking(Statement stmt, Scanner scanner) {
        try {
            System.out.print(Color.bold);
            System.out.print(Color.Magenta);
            System.out.println("\n\t\t+============================+");
            System.out.println("\t\t|\t Cancel Booking      |");
            System.out.println("\t\t+============================+\n");
            System.out.print(Color.Reset);
            char loop;
            System.out.print(Color.BrightCyan);
            System.out.print(Color.BrightCyan);
            System.out.print("\tif you want to continue say (Y/y) else return to menu: ");
            choice = scanner.next().charAt(0);
            if (choice == 'Y' || choice == 'y') {
                do {
                    System.out.print("\t\tEnter Booking ID to cancel: ");
                    int bookingId = scanner.nextInt();

                    String getRoomNumberQuery = "SELECT room_number FROM bookings WHERE booking_id = " + bookingId;
                    ResultSet resultSet = stmt.executeQuery(getRoomNumberQuery);

                    if (resultSet.next()) {
                        int roomNumber = resultSet.getInt("room_number");

                        String cancelBookingQuery = "DELETE FROM bookings WHERE booking_id = " + bookingId;
                        String updateRoomStatusQuery = "UPDATE rooms SET is_booked = false WHERE room_number = "
                                + roomNumber;

                        stmt.executeUpdate(cancelBookingQuery);
                        stmt.executeUpdate(updateRoomStatusQuery);
                        System.out.println(
                                "------------------------------------------------------------------------------");
                        System.out.print(Color.BrightGreen);
                        System.out.println("\t\tBooking canceled successfully. Room is now available.");
                        System.out.print(Color.Reset);
                    } else {
                        System.out.println(
                                "----------------------------------------------------------------------------");
                        System.out.print(Color.BrightRed);
                        System.out.println("\t\tBooking ID not found. No changes were made.");
                        System.out.print(Color.Reset);
                    }
                    System.out.print("\t\tDo you want to cancel another book press (Y/y) else return : ");
                    loop = scanner.next().charAt(0);
                    System.out.print(Color.BrightCyan);
                } while (loop == 'Y' || loop == 'y');
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
    }
}
