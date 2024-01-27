package hotel_management_system;

import java.sql.*;

//*****************************************************************************    
// method to View all guests
//*****************************************************************************
public class ViewGuests {
    public void viewAllGuests(Statement stmt) {
        try {
            System.out.print(Color.bold);
            System.out.print(Color.Magenta);
            System.out.println("\n\t\t\t\t+=============================+");
            System.out.println("\t\t\t\t|\tAll Guests            |");
            System.out.println("\t\t\t\t+=============================+\n");
            System.out.print(Color.Reset);

            String query = "SELECT * FROM guests";
            ResultSet resultSet = stmt.executeQuery(query);
            System.out.print(Color.BrightCyan);
            System.out.println("+----------+--------------------+----------------+------------------------+----------------------------+");
            System.out.println("| Guest ID | First_Name \t| Last_Name\t | Email\t\t  |  Phone Number              |");
            System.out.println("+----------+--------------------+----------------+------------------------+----------------------------+");
            while (resultSet.next()) {
                int guestId = resultSet.getInt("guest_id");
                String firstName = resultSet.getString("first_name");
                String lastName = resultSet.getString("last_name");
                String email = resultSet.getString("email");
                String phoneNumber = resultSet.getString("phone_number");
                System.out.println("| " + guestId + "  -----> " + firstName + "\t-------> " + lastName + "  -------> " + email + "  --------> " + phoneNumber);
            }
            System.out.println("+----------+--------------------+----------------+------------------------+----------------------------+");
        } catch (SQLException e) {
            System.out.println("Error executing SQL query: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
