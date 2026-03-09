import java.util.Scanner;

public class Workwear extends Accessory
{
    private String manufacturingStandard;
    private String colour;
    private String size;

    // No-arg constructor
    public Workwear()
    {
        super();
        manufacturingStandard = "unknown";
        colour                = "unknown";
        size                  = "unknown";
    }

    // Getters
    public String getManufacturingStandard() { return manufacturingStandard; }
    public String getColour()                { return colour; }
    public String getSize()                  { return size; }

    // Prints accessory fields then adds workwear-specific fields
    @Override
    public void printDetails()
    {
        super.printDetails();
        System.out.println("  [Workwear] Standard: " + manufacturingStandard
                         + " | Colour: "             + colour
                         + " | Size: "               + size);
    }

    // Reads accessory fields first then reads workwear-specific fields
    // Field order: isRecyclable, itemName, itemCode, cost, manufacturingStandard, colour, size
    @Override
    public void readData(Scanner sc)
    {
        super.readData(sc);
        manufacturingStandard = sc.next().trim();
        colour                = sc.next().trim();
        size                  = sc.next().trim();
    }
}