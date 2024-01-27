package hotel_management_system;

import java.sql.*;
import java.util.*;

//*****************************************************************************    
// Method to delete a guest
//*****************************************************************************
public class DeleteGuest {
    char choice;

    public void deleteGuest(Statement stmt, Scanner scanner) {
        try {
            System.out.print(Color.bold);
            System.out.print(Color.Magenta);
            System.out.println("\n\t\t+==========================+");
            System.out.println("\t\t|\tDelete Guest       |");
            System.out.println("\t\t+==========================+\n");
            System.out.print(Color.Reset);
            int loop;
            System.out.print(Color.BrightCyan);
            System.out.print("\tif you want to continue say (Y/y) else return to menu: ");
            choice = scanner.next().charAt(0);
            if (choice == 'Y' || choice == 'y') {
                do {
                    System.out.print("\t\tEnter Guest ID to delete: ");
                    int guestIdToDelete = scanner.nextInt();

                    // Check if the guest exists
                    ResultSet guestCheck = stmt
                            .executeQuery("SELECT * FROM guests WHERE guest_id = " + guestIdToDelete);

                    if (guestCheck.next()) {
                        String deleteGuestQuery = "DELETE FROM guests WHERE guest_id = " + guestIdToDelete;
                        stmt.executeUpdate(deleteGuestQuery);
                        System.out.println(
                                "----------------------------------------------------------------------------");
                        System.out.print(Color.BrightGreen);
                        System.out.println("\t\tGuest with ID " + guestIdToDelete + " deleted successfully.");
                        System.out.print(Color.Reset);
                    } else {
                        System.out.println("------------------------------------------------------------------------");
                        System.out.print(Color.BrightRed);
                        System.out.println("\t\tGuest with ID " + guestIdToDelete + " does not exist.");
                        System.out.print(Color.Reset);
                    }
                    System.out.print("\tDo you want to delete another guests press (1) else return : ");
                    loop = scanner.nextInt();
                    System.out.print(Color.BrightCyan);
                } while (loop == 1);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
    }
}
