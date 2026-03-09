import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.awt.FileDialog;

public class Shop
{
    // Holds all shop items - tools and accessories
    private ArrayList<ShopItem> itemList;

    public Shop()
    {
        itemList = new ArrayList<ShopItem>();
    }

    // Adds any ShopItem to the list
    public void storeTool(ShopItem item)
    {
        itemList.add(item);
    }

    // Displays all items currently in the shop
    public void printAllTools()
    {
        if (itemList.isEmpty())
        {
            System.out.println("The shop has no items at the moment.");
            return;
        }
        System.out.println("=== Shop Inventory (" + itemList.size() + " items) ===");
        for (ShopItem item : itemList)
        {
            item.printDetails();
            System.out.println();
        }
        System.out.println("==========================================");
    }

    // Opens a file dialog, reads item data and loads it into itemList
    public void readToolData()
    {
        FileDialog fd = new FileDialog((java.awt.Frame) null, "Open Tool Data File", FileDialog.LOAD);
        fd.setDirectory(".");
        fd.setVisible(true);

        String dir  = fd.getDirectory();
        String file = fd.getFile();

        if (file == null)
        {
            System.out.println("No file was chosen - operation cancelled.");
            return;
        }

        System.out.println("Reading from: " + file);

        try
        {
            Scanner fileScanner = new Scanner(new File(dir + file));

            // Keeps track of what type of item we are currently reading
            String currentType = "";

            while (fileScanner.hasNextLine())
            {
                String currentLine = fileScanner.nextLine().trim();

                // Skip blank lines
                if (currentLine.isEmpty())
                {
                    continue;
                }

                // Skip comment lines
                else if (currentLine.startsWith("//"))
                {
                    continue;
                }

                // Flag line - update the current type of data
                else if (currentLine.startsWith("["))
                {
                    currentType = currentLine.toLowerCase();
                }

                // Real data line - create the right type of item
                else
                {
                    Scanner lineScanner = new Scanner(currentLine);
                    lineScanner.useDelimiter("\\s*,\\s*");

                    if (currentType.contains("electric"))
                    {
                        ElectricTool et = new ElectricTool();
                        et.readData(lineScanner);
                        storeTool(et);
                    }
                    else if (currentType.contains("hand"))
                    {
                        HandTool ht = new HandTool();
                        ht.readData(lineScanner);
                        storeTool(ht);
                    }
                    else if (currentType.contains("perishable"))
                    {
                        Perishable p = new Perishable();
                        p.readData(lineScanner);
                        storeTool(p);
                    }
                    else if (currentType.contains("workwear"))
                    {
                        Workwear w = new Workwear();
                        w.readData(lineScanner);
                        storeTool(w);
                    }
                    else
                    {
                        System.out.println("Warning: did not recognise data type - " + currentType + " - line skipped.");
                    }

                    lineScanner.close();
                }
            }

            fileScanner.close();
            System.out.println("Done! " + itemList.size() + " items loaded into the shop.");

        }
        catch (FileNotFoundException e)
        {
            System.out.println("Could not open file: " + file);
        }
    }

    public ArrayList<ShopItem> getToolList()
    {
        return itemList;
    }
}