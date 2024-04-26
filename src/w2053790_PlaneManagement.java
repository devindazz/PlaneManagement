import java.util.*;

public class w2053790_PlaneManagement {
    // constants
    // marked as final so that they cannot be changed
    static final int ROWS = 4;
    static final String[] ROWS_CHARS = { "A", "B", "C", "D" };

    // static so that it can be accessed
    static Scanner sc = new Scanner(System.in);

    // 2D array to store the seats
    static int[][] seats = new int[ROWS][];

    static Ticket[] tickets = new Ticket[0];

    public static void main(String[] args) {
        // Initialize the seats array based on the number of rows and their configurations
        for (int i = 0; i < ROWS; i++) {
            // if the row is B or C, then there are 12 seats
            if (getRow(i) == "B" || getRow(i) == "C") {
                seats[i] = new int[12];
            } else {
                seats[i] = new int[14];
            }
            Arrays.fill(seats[i], 0);//fill each row with 0 initially

        }

        int option = -1;
        // Main menu loop
        do {
            if (option == 1)
                buySeat();
            else if (option == 2)
                cancelSeat();
            else if (option == 3)
                displayFirstAvailableSeat();
            else if (option == 4)
                displaySeatingPlan();
            else if (option == 5)
                displayTicketsInformationAndTotalSales();
            else if (option == 6)
                displayTicket();
            option = getOption();//Get the user's choice from the menu
        } while (option != 0);//Exit loop when option is 0 (Exit)
    }

    // Method to get the character representation of a row based on its index
    static String getRow(int row) {
        return ROWS_CHARS[row];
    }
    // Method to display the menu option and get the user's choice
    static int getOption() {
        System.out.println("***********************************************");
        System.out.println("    Welcome to the Plane Management System!");
        System.out.println("***********************************************");

        System.out.println("1. Buy a seat");
        System.out.println("2. Cancel a seat");
        System.out.println("3. Find first available seat");
        System.out.println("4. Show seating plan");
        System.out.println("5. Print tickets information and total sales");
        System.out.println("6. Search ticket");
        System.out.println("0. Exit");
        System.out.println("***********************************************");

        System.out.println();
        System.out.print("Please select an option: ");

        int option = -1;
        do {
            try {
                option = sc.nextInt();// Read user input for the selected option
            } catch (InputMismatchException e) {
                System.out.println("Invalid input! Please enter a number.");
                sc.nextLine();
            }
            return option;
        } while (option < 0 || option > 6);// Keep asking until a valid option is entered
    }
    // Method to ask the user for a seat row and number
    static int[] askSeatFromUser() {
        try {
            System.out.print("Enter the row: ");
            String character = sc.next().toUpperCase();
            if (!Arrays.asList(ROWS_CHARS).contains(character)) {
                System.out.println("Invalid row!");
                return askSeatFromUser();// Recursive call to ask again for valid input
            }
            int row = Arrays.asList(ROWS_CHARS).indexOf(character);
            System.out.print("Enter the seat number: ");
            int column = sc.nextInt();
            if (column < 1 || column > seats[row].length) {
                System.out.println("Invalid seat number!");
                return askSeatFromUser();
            }
            //
            return new int[] { row, column };
        } catch (InputMismatchException e) {
            System.out.println("Invalid input! Please enter a number.");
            sc.nextLine();
            return askSeatFromUser();
        }
    }
    // Method to handle seat purchase
    static void buySeat() {
        int[] seat = askSeatFromUser();
        int row = seat[0];
        int column = seat[1];
        if (seats[row][column - 1] == 1) {
            System.out.println("The seat is already taken!");
            return;
        } else {
            System.out.print("Enter the name: ");
            String name = sc.next();
            System.out.print("Enter the surname: ");
            String surname = sc.next();
            System.out.print("Enter the email: ");
            String email = sc.next();
            Person person = new Person(name, surname, email);
            float price = getTicketPrice(column);
            Ticket ticket = new Ticket(getRow(row), column, price, person);
            ticket.save();
            addTicket(ticket);
            seats[row][column - 1] = 1;// Mark the seat as taken in the seating plan
            System.out.println("The seat has been successfully purchased! The price is £" + String.valueOf(price));
        }
    }
    // Method to add a ticket to the tickets array
    static void addTicket(Ticket ticket) {
        Ticket[] newTickets = new Ticket[tickets.length + 1];
        for (int i = 0; i < tickets.length; i++) {
            newTickets[i] = tickets[i];
        }
        newTickets[tickets.length] = ticket;
        tickets = newTickets;
    }
    // Method to remove a ticket from the tickets array
    static void removeTicket(int index) {
        Ticket[] newTickets = new Ticket[tickets.length - 1];
        for (int i = 0; i < tickets.length; i++) {
            if (i < index) {
                newTickets[i] = tickets[i];
            } else if (i > index) {
                newTickets[i - 1] = tickets[i];
            }
        }
        tickets = newTickets;
    }
    // Method to calculate ticket price based on seat column
    static float getTicketPrice(int column) {
        float price = 200;
        if (column < 6)
            price = 200;
        else if (column < 10)
            price = 150;
        else
            price = 180;
        return price;
    }
    // Method to cancel a seat reservation
    static void cancelSeat() {
        int[] seat = askSeatFromUser();
        int row = seat[0];
        int column = seat[1];
        if (seats[row][column - 1] == 0) {
            System.out.println("The seat is already unoccupied!");
            return;
        } else {
            seats[row][column - 1] = 0;
            int ticket = -1;
            for (int i = 0; i < tickets.length; i++) {
                if (tickets[i].getRow() == getRow(row) && tickets[i].getSeat() == column) {
                    ticket = i;
                    break;
                }
            }
            if (ticket != -1) {
                removeTicket(ticket);
            } else {
                System.out.println("The ticket was not found!");
                return;
            }
            System.out.println("The seat has been successfully cancelled!");
        }
    }
    // method to find and display the first available seat
    static String getFirstAvailableSeat() {
        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < seats[i].length; j++) {
                if (seats[i][j] == 0) {
                    return getRow(i) + (j + 1);//Return the first available seat in row-column format
                }
            }
        }
        return null;
    }
    // Method to display the first available seat
    static void displayFirstAvailableSeat() {
        System.out.println();
        String seat = getFirstAvailableSeat();
        if (seat != null) {
            System.out.println("The first available seat is " + seat);
        } else {
            System.out.println("There are no available seats.");
        }
        System.out.println();
    }
    // Method to display seating plan
    static void displaySeatingPlan() {
        System.out.println();
        System.out.println("Seating Plan");
        System.out.println();
        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < seats[i].length; j++) {
                System.out.print(seats[i][j] == 0 ? "O " : "X ");
            }
            System.out.println();
        }
        System.out.println();
    }
    // Method to display tickets information and total sales
    static void displayTicketsInformationAndTotalSales() {
        System.out.println();
        System.out.println("Tickets Information");
        System.out.println();
        for (int i = 0; i < tickets.length; i++) {
            System.out.println("Ticket " + (i + 1));
            Ticket ticket = tickets[i];
            ticket.print();
            System.out.println();
        }
        System.out.println("Total Sales: £" + getTotalSales());
        System.out.println();
    }
    // Method to calculate and return the total sales from all sold tickets
    static float getTotalSales() {
        float total = 0;
        for (int i = 0; i < tickets.length; i++) {
            total += tickets[i].getPrice();// Accumulate tickets prices to calculate total sales
        }
        return total;
    }

    // Method to display ticket information for a specific seat
    static void displayTicket() {
        int[] seat = askSeatFromUser();// Get seat information from the user
        int row = seat[0];
        int column = seat[1];
        int ticket = -1;
        for (int i = 0; i < tickets.length; i++) {
            if (tickets[i].getRow() == getRow(row) && tickets[i].getSeat() == column) {
                ticket = i;// Find the ticket index for the specified seat
                break;
            }
        }
        if (ticket != -1) {
            System.out.println("The ticket was found!");
            tickets[ticket].print();
        } else {
            System.out.println("The ticket was not found!");
        }
    }
}