import java.util.Scanner;

public class Tool
{
    private String toolName;
    private String itemCode;
    private int timesBorrowed;
    private boolean onLoan;
    private int cost;
    private int weight;

    // No-arg constructor - fields set to safe default values
    public Tool()
    {
        toolName      = "unknown";
        itemCode      = "N/A";
        timesBorrowed = 0;
        onLoan        = false;
        cost          = 0;
        weight        = 0;
    }

    // Getters
    public String getToolName()     { return toolName; }
    public String getItemCode()     { return itemCode; }
    public int getTimesBorrowed()   { return timesBorrowed; }
    public boolean isOnLoan()       { return onLoan; }
    public int getCost()            { return cost; }
    public int getWeight()          { return weight; }

    // Displays tool info in a readable format
    public void printDetails()
    {
        String currentLoanStatus = onLoan ? "yes" : "no";
        System.out.println("Name: "            + toolName
                         + " | Code: "         + itemCode
                         + " | Times hired: "  + timesBorrowed
                         + " | On loan: "      + currentLoanStatus
                         + " | Cost: £"        + cost
                         + " | Weight: "       + weight + "g");
    }

    // Reads base tool fields from the provided scanner
    public void readData(Scanner sc)
    {
        toolName      = sc.next().trim();
        itemCode      = sc.next().trim();
        timesBorrowed = Integer.parseInt(sc.next().trim());
        onLoan        = Boolean.parseBoolean(sc.next().trim());
        cost          = Integer.parseInt(sc.next().trim());
        weight        = Integer.parseInt(sc.next().trim());
    }
}