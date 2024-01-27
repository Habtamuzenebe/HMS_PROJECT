package hotel_management_system;

import java.sql.*;

//*****************************************************************************    
// method to View rooms
//*****************************************************************************
public class ViewRoom {
     public void viewRooms(Statement stmt) {
        try {
            System.out.print(Color.bold);
            System.out.print(Color.Magenta);
            System.out.println("\n\t\t\t\t+==========================+");
            System.out.println("\t\t\t\t|\tAll Rooms          |");
            System.out.println("\t\t\t\t+==========================+\n");
            System.out.print(Color.Reset);
String query = "SELECT rooms.room_number, rooms.capacity, rooms.price_per_night, "
        + "rooms.is_booked, bookings.booking_id, guests.guest_id, guests.first_name, guests.last_name "
        + "FROM rooms "
        + "LEFT JOIN bookings ON rooms.room_number = bookings.room_number "
        + "LEFT JOIN guests ON bookings.guest_id = guests.guest_id "
        + "order by capacity desc";


            ResultSet resultSet = stmt.executeQuery(query);
            System.out.print(Color.BrightCyan);
            System.out.println("+--------------+--------------+---------------------+-------------------------------------------------------------+");
            System.out.println("| Room Number  |   Capacity   |   Price per Night,  |   Status                                                    |");
            System.out.println("+--------------+--------------+---------------------+-------------------------------------------------------------+");
            while (resultSet.next()) {
                int roomNumber = resultSet.getInt("room_number");
                int capacity = resultSet.getInt("capacity");
                double pricePerNight = resultSet.getDouble("price_per_night");
                boolean isBooked = resultSet.getBoolean("is_booked");
                int bookingId = resultSet.getInt("booking_id");
                int guestId = resultSet.getInt("guest_id");
                String guestFirstName = resultSet.getString("first_name");
                String guestLastName = resultSet.getString("last_name");

                String status = isBooked ? "Booking ID "
                        + bookingId + ": "
                        + "Booked by Guest ID "
                        + guestId + ": " + guestFirstName + " "
                        + guestLastName : "Available";
                System.out.println("| " + roomNumber + "\t---->  |  " + capacity + "\t--->  |  "
                        + pricePerNight + "0 birr" + "  --->  |\t" + status);

            }
            System.out.println("+--------------+----------------+---------------------+-----------------------------------------------------------+");
        } catch (SQLException e) {
            System.out.println(e);
        }
    }
}
