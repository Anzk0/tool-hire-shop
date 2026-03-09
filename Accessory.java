import java.util.Scanner;

public abstract class Accessory extends ShopItem
{
    private boolean recyclable;

    // No-arg constructor
    public Accessory()
    {
        super();
        recyclable = false;
    }

    // Getter
    public boolean isRecyclable() { return recyclable; }

    // Prints ShopItem fields then adds accessory-specific fields
    @Override
    public void printDetails()
    {
        super.printDetails();
        System.out.println("  [Accessory] Recyclable: " + recyclable);
    }

    // Reads recyclable first, then passes itemName, itemCode, cost to ShopItem
    // Field order in file: isRecyclable, itemName, itemCode, cost
    @Override
    public void readData(Scanner sc)
    {
        recyclable = Boolean.parseBoolean(sc.next().trim());
        super.readData(sc);
    }
}