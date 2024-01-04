
import java.util.*;

class MovieTicketBookingSystem {
    private static  String USERNAME = "admin";
    private static String PASSWORD = "password";
    
    private static final int ROWS = 5;
    private static final int SEATS_PER_ROW = 10;
    private static final double TICKET_PRICE = 10.0;

    private static boolean[][] seatsAvailability = new boolean[ROWS][SEATS_PER_ROW];

    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println("Welcome to Movie Ticket Booking Simulator");

        // Login to theatre
        loginToTheatre();

        while (true) {
            System.out.println("Select an option:");
            System.out.println("1. View Seating Arrangement");
            System.out.println("2. Book Ticket");
            System.out.println("3. View Booking Status");
            System.out.println("4. Update Password");
            System.out.println("5. Exit");

            int choice = scanner.nextInt();
            scanner.nextLine();  // Consume the newline character

            switch (choice) {
                case 1:
                    viewSeatingArrangement();
                    break;
                case 2:
                    bookTicket();
                    break;
                case 3:
                    viewBookingStatus();
                    break;
                case 4:
                    updatePassword();
                    break;
                case 5:
                    System.out.println("Exiting Movie Ticket Booking Simulator. Thank you!");
                    System.exit(0);
                default:
                    System.out.println("Invalid choice. Please select a valid option.");
            }
        }
    }

    private static void loginToTheatre() {
        System.out.print("Enter username: ");
        String enteredUsername = scanner.nextLine();

        System.out.print("Enter password: ");
        String enteredPassword = scanner.nextLine();

        if (enteredUsername.equals(USERNAME) && enteredPassword.equals(PASSWORD)) {
            System.out.println("Login successful. Welcome, Front Desk!");
        } else {
            System.out.println("Invalid credentials. Exiting the application.");
            System.exit(0);
        }
    }

    private static void viewSeatingArrangement() {
        System.out.println("View Seating Arrangement:");
        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < SEATS_PER_ROW; j++) {
                System.out.print(seatsAvailability[i][j] ? "X " : "O ");
            }
            System.out.println();
        }
    }

    private static void bookTicket() {
        System.out.print("Enter booking date: ");
        String bookingDate = scanner.nextLine();

        System.out.print("Enter show time: ");
        String showTime = scanner.nextLine();

        System.out.print("Enter row number (1-" + ROWS + "): ");
        int row = scanner.nextInt();

        System.out.print("Enter seat number (1-" + SEATS_PER_ROW + "): ");
        int seat = scanner.nextInt();

        if (isValidSeat(row, seat) && !seatsAvailability[row - 1][seat - 1]) {
            seatsAvailability[row - 1][seat - 1] = true;
            double amount = calculateAmount(row);
            System.out.println("Ticket booked successfully!");
            System.out.println("Amount: $" + amount);
        } else {
            System.out.println("Invalid seat selection or seat already booked. Please try again.");
        }
    }

    private static void viewBookingStatus() {
        System.out.println("View Booking Status:");
        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < SEATS_PER_ROW; j++) {
                if (seatsAvailability[i][j]) {
                    System.out.println("Seat booked at Row " + (i + 1) + ", Seat " + (j + 1));
                }
            }
        }
    }

    private static void updatePassword() {
        System.out.print("Enter current password: ");
        String currentPassword = scanner.nextLine();

        if (currentPassword.equals(PASSWORD)) {
            System.out.print("Enter new password: ");
            String newPassword = scanner.nextLine();
            PASSWORD = newPassword;
            System.out.println("Password updated successfully!");
        } else {
            System.out.println("Incorrect current password. Password not updated.");
        }
    }

    private static boolean isValidSeat(int row, int seat) {
        return row >= 1 && row <= ROWS && seat >= 1 && seat <= SEATS_PER_ROW;
    }

    private static double calculateAmount(int row) {
        // Assume simple pricing logic for demonstration purposes
        return TICKET_PRICE * row;
    }
}
