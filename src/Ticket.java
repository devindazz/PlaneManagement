import java.io.FileWriter;

// Define a ticket class to represent tickets with row, seat, price and person attributes
public class Ticket {
    private String row;
    private int seat;
    private float price;
    // no need write seperate Strings.bcz already have
    private Person person;

    // Constructer to initialize a ticket object with provided row,seat,price and person details
    public Ticket(String row, int seat, float price, Person person) {
        this.row = row;
        this.seat = seat;
        this.price = price;
        this.person = person;
    }
    // Getter method to retrieve the row of the ticket
    public String getRow() {
        return row;
    }

    // Setter method to update the row of the ticket
    public void setRow(String row) {
        this.row = row;
    }

    //Getter method to retrieve the seat number of the ticket
    public int getSeat() {
        return seat;
    }

    //Setter method to update the seat number of the ticket
    public void setSeat(int seat) {
        this.seat = seat;
    }

    // Getter method to retrieve the price of the ticket
    public float getPrice() {
        return price;
    }

    // Setter method to update the price of the ticket
    public void setPrice(float price) {
        this.price = price;
    }

    // Getter method to retrieve the person associated with the ticket
    public Person getPerson() {
        return person;
    }

    // Setter method to update the person associated with the ticket
    public void setPerson(Person person) {
        this.person = person;
    }

    // Method to print the details of the ticket
    public void print() {
        System.out.printf("Row: %s%n", row);
        System.out.printf("Seat: %d%n", seat);
        System.out.printf("Price: %.2f%n", price);
        person.print();
    }

    // Method to save the ticket information to a file
    public void save() {
        try {
            FileWriter writer = new FileWriter(String.format("%s.txt", String.format("%s%d", row, seat)));
            writer.write(String.format("Row: %s%n", row));
            writer.write(String.format("Seat: %d%n", seat));
            writer.write(String.format("Price: £%.2f%n", price));
            writer.write(String.format("Name: %s%n", person.getName()));
            writer.write(String.format("Surname: %s%n", person.getSurname()));
            writer.write(String.format("Email: %s%n", person.getEmail()));
            writer.close();
        } catch (Exception e) {}
    }
}
