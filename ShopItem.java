import java.util.Scanner;

public abstract class ShopItem
{
    private String itemName;
    private String itemCode;
    private int cost;

    // No-arg constructor
    public ShopItem()
    {
        itemName = "unknown";
        itemCode = "N/A";
        cost     = 0;
    }

    // Getters
    public String getItemName() { return itemName; }
    public String getItemCode() { return itemCode; }
    public int getCost()        { return cost; }

    // Displays shared item info
    public void printDetails()
    {
        System.out.println("Name: "     + itemName
                         + " | Code: "  + itemCode
                         + " | Cost: £" + cost);
    }

    // Reads shared fields: itemName, itemCode, cost
    public void readData(Scanner sc)
    {
        itemName = sc.next().trim();
        itemCode = sc.next().trim();
        cost     = Integer.parseInt(sc.next().trim());
    }
}