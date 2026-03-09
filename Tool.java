import java.util.Scanner;

public abstract class Tool extends ShopItem
{
    private int timesBorrowed;
    private boolean onLoan;
    private int weight;

    // No-arg constructor
    public Tool()
    {
        super();
        timesBorrowed = 0;
        onLoan        = false;
        weight        = 0;
    }

    // Getters
    public int getTimesBorrowed() { return timesBorrowed; }
    public boolean isOnLoan()     { return onLoan; }
    public int getWeight()        { return weight; }

    // Prints ShopItem fields then adds tool-specific fields
    @Override
    public void printDetails()
    {
        super.printDetails();
        String currentLoanStatus = onLoan ? "yes" : "no";
        System.out.println("  [Tool] Times hired: " + timesBorrowed
                         + " | On loan: "           + currentLoanStatus
                         + " | Weight: "            + weight + "g");
    }

    // Reads ShopItem fields first then reads tool-specific fields
    // Field order in file: itemName, itemCode, cost, timesBorrowed, onLoan, weight
    @Override
    public void readData(Scanner sc)
    {
        super.readData(sc);
        timesBorrowed = Integer.parseInt(sc.next().trim());
        onLoan        = Boolean.parseBoolean(sc.next().trim());
        weight        = Integer.parseInt(sc.next().trim());
    }
}