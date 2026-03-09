import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;
import java.util.Map;
import java.util.HashSet;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.awt.FileDialog;
import java.util.Random;

public class Shop
{
    // Step 5 - changed from ArrayList to List interface
    // private ArrayList<ShopItem> itemList;
    private List<ShopItem> itemList;

    // Step 5 - changed from ArrayList to List interface
    // private ArrayList<Customer> customerList;
    private List<Customer> customerList;

    // Step 5 - Map versions for key/value storage
    private Map<String, ShopItem> itemsMap;
    private Map<String, Customer> customerMap;

    // Step 3 - random number generator for customer IDs
    private Random randomGenerator;

    // Step 4 - tracks used IDs to guarantee uniqueness
    private HashSet<String> usedIDs;

    public Shop()
    {
        itemList       = new ArrayList<ShopItem>();
        customerList   = new ArrayList<Customer>();
        itemsMap       = new HashMap<String, ShopItem>();
        customerMap    = new HashMap<String, Customer>();
        randomGenerator = new Random();
        usedIDs        = new HashSet<String>();
    }

    // --- Shop item methods ---

    // Adds any ShopItem to the list and map
    public void storeTool(ShopItem item)
    {
        itemList.add(item);
        itemsMap.put(item.getItemCode(), item);
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

    // Returns a ShopItem by its itemCode, or null if not found
    public ShopItem getItem(String itemCode)
    {
        return itemsMap.get(itemCode);
    }

    // --- Customer methods ---

    // Step 3 - generates a random customer ID with given prefix and digit count
    private String generateCustomerID(String prefix, int digits)
    {
        String newID;
        do {
            // Build a random number with the right number of digits
            int max = (int) Math.pow(10, digits);
            int randomNum = randomGenerator.nextInt(max);
            // Pad with leading zeros if needed
            String formatted = String.format("%0" + digits + "d", randomNum);
            newID = prefix + formatted;
        }
        // Step 4 - keep generating until we get a unique ID
        while (usedIDs.contains(newID));

        // Record this ID as used
        usedIDs.add(newID);
        return newID;
    }

    // Adds a customer to the list and map
    // Step 3 - generates a proper ID if customerID is still "unknown"
    public void storeCustomer(Customer customer)
    {
        if (customer.getCustomerID().equals("unknown"))
        {
            String newID = generateCustomerID("CU-", 6);
            customer.setCustomerID(newID);
        }
        customerList.add(customer);
        customerMap.put(customer.getCustomerID(), customer);
    }

    // Displays all customers
    public void printAllCustomers()
    {
        if (customerList.isEmpty())
        {
            System.out.println("No customers registered.");
            return;
        }
        System.out.println("=== Customer List (" + customerList.size() + " customers) ===");
        for (Customer c : customerList)
        {
            c.printDetails();
        }
        System.out.println("==========================================");
    }

    // Returns a Customer by their customerID, or null if not found
    public Customer getCustomer(String customerID)
    {
        return customerMap.get(customerID);
    }

    // Reads customer data from a file chosen via file dialog
    public void readCustomerData()
    {
        FileDialog fd = new FileDialog((java.awt.Frame) null, "Open Customer Data File", FileDialog.LOAD);
        fd.setDirectory(".");
        fd.setVisible(true);

        String dir  = fd.getDirectory();
        String file = fd.getFile();

        if (file == null)
        {
            System.out.println("No file was chosen - operation cancelled.");
            return;
        }

        System.out.println("Reading customers from: " + file);

        try
        {
            Scanner fileScanner = new Scanner(new File(dir + file));

            while (fileScanner.hasNextLine())
            {
                String currentLine = fileScanner.nextLine().trim();

                // Skip blank lines and comments
                if (currentLine.isEmpty() || currentLine.startsWith("//"))
                {
                    continue;
                }

                Scanner lineScanner = new Scanner(currentLine);
                lineScanner.useDelimiter("\\s*,\\s*");

                Customer c = new Customer();
                c.readData(lineScanner);
                storeCustomer(c);

                lineScanner.close();
            }

            fileScanner.close();
            System.out.println("Done! " + customerList.size() + " customers loaded.");

        }
        catch (FileNotFoundException e)
        {
            System.out.println("Could not open file: " + file);
        }
    }

    // Step 2 - writes customer data to a file chosen via file dialog
    public void writeCustomerData()
    {
        FileDialog fd = new FileDialog((java.awt.Frame) null, "Save Customer Data File", FileDialog.SAVE);
        fd.setDirectory(".");
        fd.setVisible(true);

        String dir  = fd.getDirectory();
        String file = fd.getFile();

        if (file == null)
        {
            System.out.println("No file was chosen - operation cancelled.");
            return;
        }

        try
        {
            PrintWriter pw = new PrintWriter(dir + file);

            pw.println("// Customer data file");
            pw.println("// data is customerID, surname, firstName, otherInitials, title");
            pw.println();

            for (Customer c : customerList)
            {
                c.writeData(pw);
            }

            pw.close();
            System.out.println("Customer data saved to: " + file);

        }
        catch (FileNotFoundException e)
        {
            System.out.println("Could not save file: " + file);
        }
    }

    // --- Tool data reading (from Part 2) ---

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
            String currentType = "";

            while (fileScanner.hasNextLine())
            {
                String currentLine = fileScanner.nextLine().trim();

                if (currentLine.isEmpty() || currentLine.startsWith("//"))
                {
                    continue;
                }
                else if (currentLine.startsWith("["))
                {
                    currentType = currentLine.toLowerCase();
                }
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

    // Getters for lists
    public List<ShopItem> getToolList()     { return itemList; }
    public List<Customer> getCustomerList() { return customerList; }
}