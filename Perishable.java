import java.util.Scanner;

public class Perishable extends Accessory
{
    private boolean irritant;
    private String useByDate;
    private int volume;

    // No-arg constructor
    public Perishable()
    {
        super();
        irritant   = false;
        useByDate  = "unknown";
        volume     = 0;
    }

    // Getters
    public boolean isIrritant()  { return irritant; }
    public String getUseByDate() { return useByDate; }
    public int getVolume()       { return volume; }

    // Prints accessory fields then adds perishable-specific fields
    @Override
    public void printDetails()
    {
        super.printDetails();
        System.out.println("  [Perishable] Irritant: " + irritant
                         + " | Use by: "               + useByDate
                         + " | Volume: "               + volume + "ml");
    }

    // Reads accessory fields first then reads perishable-specific fields
    // Field order: isRecyclable, itemName, itemCode, cost, isIrritant, useByDate, volume
    @Override
    public void readData(Scanner sc)
    {
        super.readData(sc);
        irritant  = Boolean.parseBoolean(sc.next().trim());
        useByDate = sc.next().trim();
        volume    = Integer.parseInt(sc.next().trim());
    }
}