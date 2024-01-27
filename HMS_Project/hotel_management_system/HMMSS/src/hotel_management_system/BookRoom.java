package hotel_management_system;

import java.sql.*;
import java.util.Scanner;

//*****************************************************************************    
// method to Book rooms
//*****************************************************************************
public class BookRoom {
    char choice;

    public void bookRoom(Statement stmt, Scanner scanner) {
        try {
            System.out.print(Color.bold);
            System.out.print(Color.Magenta);
            System.out.println("\n\t\t+==========================+");
            System.out.println("\t\t|\tBook Room          |");
            System.out.println("\t\t+==========================+\n");
            System.out.print(Color.Reset);
            char loop;
            System.out.print(Color.BrightCyan);
            System.out.print("\tif you want to continue say (Y/y) else return to menu: ");
            choice = scanner.next().charAt(0);

            if (choice == 'Y' || choice == 'y') {
                System.out.print(Color.bold);
                System.out.print(Color.BrightGreen);
                System.out.print("\n\t\t   Available rooms list \n");
                System.out.println("\t\t==========================");
                String Query= "select room_number, capacity,is_booked from rooms where is_booked =0 ";

                ResultSet resultSet = stmt.executeQuery(Query);
                System.out.println("\t+--------------+--------------+------------------+");
                System.out.println("\t| Room Number  |   Capacity   |     Status       |");
                System.out.println("\t+--------------+--------------+------------------+");
                while (resultSet.next()) {
                    int roomNumber = resultSet.getInt("room_number");
                    int capacity = resultSet.getInt("capacity");
                    boolean isBooked = resultSet.getBoolean("is_booked");
                    String status = isBooked ? "Not Available" : "Available"; // Determine status based on is_booked value
                    
                    // Print room details
                    System.out.println("\t| " + roomNumber + "\t---->  |  " + capacity + "\t--->  |  " + status+"       |");
                }
                System.out.println("\t+--------------+--------------+------------------+");
                System.out.print(Color.Reset);
                
                System.out.print(Color.BrightCyan);
                do {
                    System.out.print("\t\tEnter Booking ID: ");
                    int booking_id = scanner.nextInt();

                    int guestId;
                    boolean isGuestExists;

                    do {
                        System.out.print("\t\tEnter Guest ID: ");
                        guestId = scanner.nextInt();

                        // Check if the guest exists
                        ResultSet guestCheck = stmt.executeQuery("SELECT * FROM guests WHERE guest_id = " + guestId);
                        isGuestExists = guestCheck.next();

                        if (!isGuestExists) {
                            System.out.println(
                                    "\t\tGuest with ID " + guestId + " does not exist. Please add the guest first.");
                        }
                    } while (!isGuestExists);

                    // Check if the room exists
                    boolean isRoomExists;
                    int roomNumber;
                    do {
                        System.out.print("\t\tEnter Room Number: ");
                        roomNumber = scanner.nextInt();

                        ResultSet roomCheck = stmt
                                .executeQuery("SELECT * FROM rooms WHERE room_number = " + roomNumber);
                        isRoomExists = roomCheck.next();

                        if (!isRoomExists) {
                            System.out.println("\tRoom with number " + roomNumber
                                    + " does not exist.Please Select from available room");
                        }
                    } while (!isRoomExists);

                    // Check if the room is already occupied
                    boolean isRoomOccupied = true;
                    while (isRoomOccupied) {
                        ResultSet roomCheck = stmt
                                .executeQuery("SELECT * FROM rooms WHERE room_number = " + roomNumber);
                        if (roomCheck.next()) {
                            isRoomOccupied = roomCheck.getBoolean("is_booked");
                            if (isRoomOccupied) {
                                System.out.println(
                                        "\t\tRoom " + roomNumber + " is already occupied. Please choose another room.");
                                System.out.print("\t\tEnter Room Number: ");
                                roomNumber = scanner.nextInt();
                            }
                        }
                    }

                    System.out.print("\t\tEnter Check-in Date (YYYY-MM-DD): ");
                    String checkInDate = scanner.next();

                    System.out.print("\t\tEnter Check-out Date (YYYY-MM-DD): ");
                    String checkOutDate = scanner.next();

                    String insertBookingQuery = String.format(
                            "INSERT INTO bookings (booking_id, guest_id, room_number, check_in_date, check_out_date) VALUES (%d, %d, %d, '%s', '%s')",
                            booking_id, guestId, roomNumber, checkInDate, checkOutDate);

                    stmt.executeUpdate(insertBookingQuery);

                    String updateRoomStatusQuery = String
                            .format("UPDATE rooms SET is_booked = true WHERE room_number = %d", roomNumber);
                    stmt.executeUpdate(updateRoomStatusQuery);
                    System.out.println("-------------------------------------------------------------------------");
                    System.out.print(Color.BrightGreen);
                    System.out.println("\t\tRoom booked successfully.");
                    System.out.print(Color.Reset);
                    System.out.print("\tDo you want to book another room press (Y/y) else return : ");
                    loop = scanner.next().charAt(0);
                    System.out.print(Color.BrightCyan);
                } while (loop == 'Y' || loop == 'y');
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
    }
}
