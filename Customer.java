import java.util.Scanner;
import java.io.PrintWriter;

public class Customer
{
    private String customerID;
    private String surname;
    private String firstName;
    private String otherInitials;
    private String title;

    // Constructor with 4 params - customerID always set to "unknown"
    public Customer(String surname, String firstName, String otherInitials, String title)
    {
        this.customerID     = "unknown";
        this.surname        = surname;
        this.firstName      = firstName;
        this.otherInitials  = otherInitials;
        this.title          = title;
    }

    // No-arg constructor - used when reading from file
    public Customer()
    {
        customerID    = "unknown";
        surname       = "";
        firstName     = "";
        otherInitials = "";
        title         = "";
    }

    // Getters
    public String getCustomerID()    { return customerID; }
    public String getSurname()       { return surname; }
    public String getFirstName()     { return firstName; }
    public String getOtherInitials() { return otherInitials; }
    public String getTitle()         { return title; }

    // Setter for customerID - used when generating a proper ID
    public void setCustomerID(String id) { customerID = id; }

    // Prints customer details to terminal
    public void printDetails()
    {
        System.out.println("ID: "       + customerID
                         + " | Name: "  + title
                         + " "          + firstName
                         + " "          + otherInitials
                         + " "          + surname);
    }

    // Reads customer fields from scanner
    // Field order: customerID, surname, firstName, otherInitials, title
    public void readData(Scanner sc)
    {
        customerID    = sc.next().trim();
        surname       = sc.next().trim();
        firstName     = sc.next().trim();
        otherInitials = sc.next().trim();
        title         = sc.next().trim();
    }

    // Writes customer data to file via PrintWriter
    public void writeData(PrintWriter pw)
    {
        pw.println(customerID    + ", "
                 + surname       + ", "
                 + firstName     + ", "
                 + otherInitials + ", "
                 + title);
    }
}