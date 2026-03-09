import java.util.Scanner;

public class ElectricTool extends Tool
{
    private boolean rechargeable;
    private int power;

    // No-arg constructor
    public ElectricTool()
    {
        super();
        rechargeable = false;
        power        = 0;
    }

    // Getters
    public boolean isRechargeable() { return rechargeable; }
    public int getPower()           { return power; }

    // Overrides Tool's printDetails() - calls super first then adds own fields
    @Override
    public void printDetails()
    {
        super.printDetails();
        System.out.println("  rechargeable: " + rechargeable
                         + "; power: "        + power);
    }

    // Overrides Tool's readData() - calls super first then reads own fields
    @Override
    public void readData(Scanner scanner)
    {
        super.readData(scanner);
        rechargeable = Boolean.parseBoolean(scanner.next().trim());
        power        = Integer.parseInt(scanner.next().trim());
    }
}