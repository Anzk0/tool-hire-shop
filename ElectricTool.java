import java.util.Scanner;

public class ElectricTool extends Tool
{
    private boolean rechargeable;
    private String power;  // e.g. "18V" or "1350W" so stored as String

    // No-arg constructor
    public ElectricTool()
    {
        super();
        rechargeable = false;
        power        = "unknown";
    }

    // Getters
    public boolean isRechargeable() { return rechargeable; }
    public String getPower()        { return power; }

    // Prints tool fields then adds electric-specific fields
    @Override
    public void printDetails()
    {
        super.printDetails();
        System.out.println("  [Electric] Rechargeable: " + rechargeable
                         + " | Power: "                  + power);
    }

    // Reads tool fields first then reads electric-specific fields
    // Field order: itemName, itemCode, cost, timesBorrowed, onLoan, weight, rechargeable, power
    @Override
    public void readData(Scanner sc)
    {
        super.readData(sc);
        rechargeable = Boolean.parseBoolean(sc.next().trim());
        power        = sc.next().trim();
    }
}