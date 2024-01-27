package hotel_management_system;

import java.util.regex.Matcher; // for validations 
import java.util.regex.Pattern;

import java.sql.*;
import java.util.Scanner;

//*****************************************************************************    
// method to Add guests
//*****************************************************************************
public class Guests {
    // Email validation regex
    private static final String EMAIL_REGEX = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
    private static final Pattern EMAIL_PATTERN = Pattern.compile(EMAIL_REGEX);

    // Phone number validation regex
    private static final String PHONE_REGEX = "^[0-9]{9}$";
    private static final Pattern PHONE_PATTERN = Pattern.compile(PHONE_REGEX);

    // email validation
    static boolean isValidEmail(String email) {
        Matcher matcher = EMAIL_PATTERN.matcher(email);
        return matcher.matches();
    }

    // phone number validation
    static boolean isValidPhoneNumber(String phoneNumber) {
        Matcher matcher = PHONE_PATTERN.matcher(phoneNumber);
        return matcher.matches();
    }
    public void addGuest(Statement stmt, Scanner scanner) {
        char choice;
        try {
            System.out.print(Color.bold);
            System.out.print(Color.Magenta);
            System.out.println("\n\t+==================================+");
            System.out.println("\t|\tEnter Guest Information    |");
            System.out.println("\t+==================================+\n");
            System.out.print(Color.Reset);
            int loop;
            System.out.print(Color.BrightCyan);
            System.out.print(Color.BrightCyan);
            System.out.print("\tif you want to continue say (Y/y) else return to menu: ");
            choice = scanner.next().charAt(0);
            if (choice == 'Y' || choice == 'y') {
                do {
                    int guestID;
                    while (true) {
                        System.out.print("\t\tEnter Guest ID: ");
                        if (scanner.hasNextInt()) {
                            guestID = scanner.nextInt();
                            scanner.nextLine(); // Consume the newline character
                            // Check if the guest ID is already occupied
                            if (isGuestIdOccupied(stmt, guestID)) {
                                System.out.println("\t\tGuest with ID " + guestID
                                        + " is already occupied. Please enter a different Guest ID.");
                            } else {
                                break;
                            }
                        } else {
                            System.out.println("\t\tInvalid input. Guest ID must be a number.");
                            scanner.next();
                            scanner.nextLine();
                        }
                    }

                    String firstName;
                    while (true) {
                        System.out.print("\t\tEnter first name: ");
                        firstName = scanner.nextLine();
                        if (firstName.matches("^[a-zA-Z]+$")) {
                            break;
                        } else {
                            System.out
                                    .println("\t\tInvalid input. First name must be a string containing only letters.");
                        }
                    }

                    String lastName;
                    while (true) {
                        System.out.print("\t\tEnter last name: ");
                        lastName = scanner.nextLine();

                        if (lastName.matches("^[a-zA-Z]+$")) {
                            break;
                        } else {
                            System.out.println("\tInvalid input. Last name must be a string containing only letters.");
                        }
                    }

                    String email;
                    while (true) {
                        System.out.print("\t\tEnter Email: ");
                        email = scanner.next();
                        if (isValidEmail(email)) {
                            break;
                        } else {
                            System.out.println("\tInvalid email format. enter email like(example@example.com).");
                        }
                    }

                    String phoneNumber;
                    while (true) {
                        System.out.print("\t\tEnter Phone Number: +251");
                        phoneNumber = scanner.next();

                        if (isValidPhoneNumber(phoneNumber)) {
                            break;
                        } else {
                            System.out.println("\tInvalid input. enter a valid phone number like(9xxxxxxxx).");
                        }
                    }

                    String insertGuestQuery = String.format(
                            "INSERT INTO guests (guest_id,first_name, last_name, email, phone_number)"
                                    + " VALUES ('%d', '%s', '%s', '%s', '%s')",
                            guestID, firstName, lastName, email, phoneNumber);

                    stmt.executeUpdate(insertGuestQuery);
                    System.out
                            .println("-------------------------------------------------------------------------------");
                    System.out.print(Color.Green);
                    System.out.println("\t\tGuest added successfully.");
                    System.out.print(Color.Reset);
                    System.out.print("\tDo you want to add another guest? Press (1) else return : ");
                    loop = scanner.nextInt();
                    System.out.print(Color.BrightCyan);
                } while (loop == 1);
            } else if (choice == 'X')
                return;
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    // Function to check if the guest ID is already occupied
    static boolean isGuestIdOccupied(Statement stmt, int guestID) throws SQLException {
        ResultSet guestCheck = stmt.executeQuery("SELECT * FROM guests WHERE guest_id = " + guestID);
        return guestCheck.next();
    }
}
