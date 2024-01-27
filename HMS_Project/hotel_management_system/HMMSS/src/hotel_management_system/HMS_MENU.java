package hotel_management_system;

import java.sql.*;
import java.util.*;
import javax.swing.*;

public class HMS_MENU {
    private static final String USERNAME = "Hms_Project";
    private static final String PASSWORD = "first@123";

    public static void main(String[] args) throws SQLException {

        // object creations for all class
        Scanner scanner = new Scanner(System.in);
        BookRoom BK = new BookRoom();
        Guests G = new Guests();
        ViewGuests VG = new ViewGuests();
        CancelBook CB = new CancelBook();
        Modifybooking MB = new Modifybooking();
        ViewRoom VR = new ViewRoom();
        DeleteGuest DG = new DeleteGuest();

        // boolean isCorrect = true;
        // do {
        //     JTextField Username= new JTextField();
        //     JPasswordField Password = new JPasswordField();
        //     Object[] fields = {"Username:", Username, "Password:", Password};
        //     int option = JOptionPane.showConfirmDialog(null, fields
        //     );

        //     // If user clicks "Cancel" or closes the dialog
        //     if (option != JOptionPane.OK_OPTION)
        //         System.exit(0);

        //     String username = Username.getText();
        //     char[] passwordChars = Password.getPassword();
        //     String password = new String(passwordChars);

        //     isCorrect = iscorrect(username, password);
        //     if (!isCorrect) {
        //         option = JOptionPane.showConfirmDialog(null, "Invalid Username or Password.\n" +
        //                 "Please try again...");
        //     }
        // } while (!isCorrect);

        try {
            ClearScreen();
            System.out.print(Color.bold);
            System.out.print(Color.BrightMagenta);
            System.out.println("\t\t+=============================================+");
            System.out.println("\t\t|   Welcome to the Hotel Management System    |");
            System.out.println("\t\t+=============================================+");
            System.out.print(Color.Reset);
            // Load the MySQL JDBC driver
            Class.forName("com.mysql.cj.jdbc.Driver");
            // Establish a connection
            Connection conn = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/HMS", "root", "");
            // Create a statement
            Statement stmt = conn.createStatement();
            System.out.print(Color.italic);
            System.out.print(Color.BrightGreen);
            while (true) {
                System.out.println(" \n\n\t\t\tHOTEL MANAGEMENT SYSTEM MENU ");
                System.out.println("\t\t   ===================================");
                System.out.println(Color.Cyan);
                System.out.println(" \t\t\t+-----------------------+");
                System.out.println(" \t\t\t|  1. Add Guest         |");
                System.out.println(" \t\t\t+-----------------------+");
                System.out.println(" \t\t\t|  2. View Rooms        |");
                System.out.println(" \t\t\t+-----------------------+");
                System.out.println(" \t\t\t|  3. Book Room         |");
                System.out.println(" \t\t\t+-----------------------+");
                System.out.println(" \t\t\t|  4. view All Guests   |");
                System.out.println(" \t\t\t+-----------------------+");
                System.out.println(" \t\t\t|  5. Modify Booking    |");
                System.out.println(" \t\t\t+-----------------------+");
                System.out.println(" \t\t\t|  6. Cancel Booking    |");
                System.out.println(" \t\t\t+-----------------------+");
                System.out.println(" \t\t\t|  7. Delete Guest      |");
                System.out.println(" \t\t\t+-----------------------+");
                System.out.println(" \t\t\t|  8. Exit              |");
                System.out.println(" \t\t\t+-----------------------+");

                System.out.println(Color.Yellow);
                System.out.print("\t\t\t  Enter your choice: ");
                int choice = scanner.nextInt();
                scanner.nextLine();
                ClearScreen();

                switch (choice) {
                    case 1:
                        G.addGuest(stmt, scanner);
                        break;
                    case 2:
                        VR.viewRooms(stmt);
                        break;
                    case 3:
                        BK.bookRoom(stmt, scanner);
                        break;
                    case 4:
                        VG.viewAllGuests(stmt);
                        break;

                    case 5:
                        MB.modifyBooking(stmt, scanner);
                        break;
                    case 6:
                        CB.cancelBooking(stmt, scanner);
                        break;
                    case 7:
                        DG.deleteGuest(stmt, scanner);
                        break;
                    case 8:
                        System.out.println("Thankyou !!");
                        System.out.println("Exiting the Hotel Management System. Goodbye!\n");
                        return;
                    default:
                        System.out.println("Invalid choice. Please enter a valid option.");
                }
                System.out.print("\nPress Enter to return to the menu...");
                scanner.nextLine(); // Wait for Enter key press
                ClearScreen();
            }

        } catch (SQLException | ClassNotFoundException e) {
            System.out.println(e);
        } finally {
            scanner.close();
        }
    }

    // this function is to clear the screen (console)
    public static void ClearScreen() {
        try {
            new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
        } catch (Exception e) {
            System.out.println("Error clearing screen: " + e.getMessage());
        }
    }

    /**
     * this function is to check the user name and password
     * when recieve username and password from the user.
     */
    private static boolean iscorrect(String enteredUsername,
            String enteredPassword) {
        return enteredUsername.equals(USERNAME)
                && enteredPassword.equals(PASSWORD);
    }
}
