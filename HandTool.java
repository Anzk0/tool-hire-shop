import java.util.Scanner;

public class HandTool extends Tool
{
    private boolean sharpenable;

    // No-arg constructor
    public HandTool()
    {
        super();
        sharpenable = false;
    }

    // Getter
    public boolean isSharpenable() { return sharpenable; }

    // Prints tool fields then adds handtool-specific fields
    @Override
    public void printDetails()
    {
        super.printDetails();
        System.out.println("  [HandTool] Sharpenable: " + sharpenable);
    }

    // Reads tool fields first then reads handtool-specific fields
    // Field order: itemName, itemCode, cost, timesBorrowed, onLoan, weight, sharpenable
    @Override
    public void readData(Scanner sc)
    {
        super.readData(sc);
        sharpenable = Boolean.parseBoolean(sc.next().trim());
    }
}