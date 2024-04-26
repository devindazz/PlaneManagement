// Define a person class to represent individuals with name,surname and email attributes
class Person {
    // can only it access from own class
    private String name;
    private String surname;
    private String email;

    // Contructor to initialize a person object with provide name,surname and email
    public Person(String name, String surname, String email) {
        this.name = name;
        this.surname = surname;
        this.email = email;
    }

    // Getter method to retrieve the name fo the person
    public String getName() {
        return name;
    }

    // Setter method to update the name of the person
    public void setName(String name) {
        this.name = name;
    }

    // Getter method to retrieve the surname of the person
    public String getSurname() {
        return surname;
    }

    // Setter method to update the surname of the person
    public void setSurname(String surname) {
        this.surname = surname;
    }

    // Getter method to retrieve the email of the person
    public String getEmail() {
        return email;
    }

    // Setter method to update the email of the person
    public void setEmail(String email) {
        this.email = email;
    }

    // Method to print the details of the person
    public void print() {
        //String formaters
        System.out.printf("Name: %s%n", name);
        System.out.printf("Surname: %s%n", surname);
        System.out.printf("Email: %s%n", email);
    }
}