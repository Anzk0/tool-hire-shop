import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;
import java.util.Map;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Date;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.awt.FileDialog;
import java.util.Random;

public class Shop
{
    // Part 3 Step 5 - List interface instead of ArrayList
    private List<ShopItem> itemList;
    private List<Customer> customerList;

    // Part 3 Step 5 - Map storage for quick lookup by ID
    private Map<String, ShopItem> itemsMap;
    private Map<String, Customer> customerMap;

    // Part 4 - reservation storage
    private Map<String, ShopItemReservation> itemReservationMap;

    // Part 4 Step 3 - diary for tracking reservations by date
    private Diary diary;

    // Part 3 Step 3 - random ID generator and used ID tracker
    private Random randomGenerator;
    private HashSet<String> usedIDs;

    // Part 4 - sequential reservation number counter
    private int reservationCounter;

    public Shop()
    {
        itemList            = new ArrayList<ShopItem>();
        customerList        = new ArrayList<Customer>();
        itemsMap            = new HashMap<String, ShopItem>();
        customerMap         = new HashMap<String, Customer>();
        itemReservationMap  = new HashMap<String, ShopItemReservation>();
        diary               = new Diary();
        randomGenerator     = new Random();
        usedIDs             = new HashSet<String>();
        reservationCounter  = 0;
    }

    // -------------------------------------------------------
    // Shop item methods
    // -------------------------------------------------------

    public void storeTool(ShopItem item)
    {
        itemList.add(item);
        itemsMap.put(item.getItemCode(), item);
    }

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

    // Returns a ShopItem by itemCode or null if not found
    public ShopItem getItem(String itemCode)
    {
        return itemsMap.get(itemCode);
    }

    // -------------------------------------------------------
    // Customer methods
    // -------------------------------------------------------

    // Generates a random unique customer ID
    private String generateCustomerID(String prefix, int digits)
    {
        String newID;
        do {
            int max = (int) Math.pow(10, digits);
            int randomNum = randomGenerator.nextInt(max);
            String formatted = String.format("%0" + digits + "d", randomNum);
            newID = prefix + formatted;
        }
        while (usedIDs.contains(newID));
        usedIDs.add(newID);
        return newID;
    }

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

    // Returns a Customer by customerID or null if not found
    public Customer getCustomer(String customerID)
    {
        return customerMap.get(customerID);
    }

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

        try
        {
            Scanner fileScanner = new Scanner(new File(dir + file));
            while (fileScanner.hasNextLine())
            {
                String currentLine = fileScanner.nextLine().trim();
                if (currentLine.isEmpty() || currentLine.startsWith("//"))
                    continue;

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
                c.writeData(pw);
            pw.close();
            System.out.println("Customer data saved to: " + file);
        }
        catch (FileNotFoundException e)
        {
            System.out.println("Could not save file: " + file);
        }
    }

    // -------------------------------------------------------
    // Reservation methods (Part 4)
    // -------------------------------------------------------

    // Generates a sequential reservation number e.g. 000001, 000002
    private String generateReservationNo()
    {
        reservationCounter++;
        return String.format("%06d", reservationCounter);
    }

    // Stores a reservation in the map and diary
    public void storeItemReservation(ShopItemReservation reservation)
    {
        itemReservationMap.put(reservation.getReservationNo(), reservation);
        diary.addReservation(reservation);
    }

    // Returns a reservation by its number or null if not found
    public ShopItemReservation getItemReservation(String reservationNo)
    {
        return itemReservationMap.get(reservationNo);
    }

    // Makes a reservation after validating all parameters
    // Returns true if successful, false if not
    public boolean makeItemReservation(String customerID, String itemID,
                                       String startDate, int noOfDays)
    {
        // Check customer exists
        if (getCustomer(customerID) == null)
        {
            System.out.println("Reservation failed: customer ID " + customerID + " not found.");
            return false;
        }

        // Check item exists
        if (getItem(itemID) == null)
        {
            System.out.println("Reservation failed: item ID " + itemID + " not found.");
            return false;
        }

        // Check start date is valid
        if (!DateUtil.isValidDateString(startDate))
        {
            System.out.println("Reservation failed: invalid start date " + startDate);
            return false;
        }

        // Check number of days is positive
        if (noOfDays <= 0)
        {
            System.out.println("Reservation failed: number of days must be at least 1.");
            return false;
        }

        // Step 3 - Check item is not already reserved for any of those days
        Date currentDate = DateUtil.convertStringToDate(startDate);
        for (int day = 1; day <= noOfDays; day++)
        {
            ShopItemReservation[] existingReservations = diary.getReservations(currentDate);
            if (existingReservations != null)
            {
                for (ShopItemReservation existing : existingReservations)
                {
                    if (existing.getItemID().equals(itemID))
                    {
                        System.out.println("Reservation failed: item " + itemID
                                         + " is already reserved on "
                                         + DateUtil.convertDateToShortString(currentDate));
                        return false;
                    }
                }
            }
            currentDate = DateUtil.nextDate(currentDate);
        }

        // All checks passed - create and store the reservation
        String reservationNo = generateReservationNo();
        ShopItemReservation reservation = new ShopItemReservation(
                reservationNo, itemID, customerID, startDate, noOfDays);
        storeItemReservation(reservation);
        System.out.println("Reservation " + reservationNo + " created successfully.");
        return true;
    }

    // Prints all reservations
    public void printItemReservations()
    {
        if (itemReservationMap.isEmpty())
        {
            System.out.println("No reservations found.");
            return;
        }
        System.out.println("=== Reservations (" + itemReservationMap.size() + ") ===");
        for (ShopItemReservation r : itemReservationMap.values())
        {
            r.printDetails();
        }
        System.out.println("==========================================");
    }

    // Prints diary entries between two dates
    public void printDiaryEntries(String startDate, String endDate)
    {
        Date start = DateUtil.convertStringToDate(startDate);
        Date end   = DateUtil.convertStringToDate(endDate);
        diary.printEntries(start, end);
    }

    // Deletes a reservation by reservation number
    public void deleteItemReservation(String reservationNo)
    {
        ShopItemReservation reservation = itemReservationMap.get(reservationNo);
        if (reservation == null)
        {
            System.out.println("Reservation " + reservationNo + " not found.");
            return;
        }
        diary.deleteReservation(reservation);
        itemReservationMap.remove(reservationNo);
        System.out.println("Reservation " + reservationNo + " deleted.");
    }

    // Writes reservation data to a file
    public void writeItemReservationData()
    {
        FileDialog fd = new FileDialog((java.awt.Frame) null, "Save Reservation Data", FileDialog.SAVE);
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
            pw.println("// Reservation data file");
            pw.println("// data is reservationNo, itemID, customerID, startDate, noOfDays");
            pw.println();
            for (ShopItemReservation r : itemReservationMap.values())
                r.writeData(pw);
            pw.close();
            System.out.println("Reservation data saved to: " + file);
        }
        catch (FileNotFoundException e)
        {
            System.out.println("Could not save file: " + file);
        }
    }

    // Reads reservation data from a file
    public void readItemReservationData()
    {
        FileDialog fd = new FileDialog((java.awt.Frame) null, "Open Reservation Data", FileDialog.LOAD);
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
            Scanner fileScanner = new Scanner(new File(dir + file));
            while (fileScanner.hasNextLine())
            {
                String currentLine = fileScanner.nextLine().trim();
                if (currentLine.isEmpty() || currentLine.startsWith("//"))
                    continue;

                Scanner lineScanner = new Scanner(currentLine);
                lineScanner.useDelimiter("\\s*,\\s*");
                ShopItemReservation r = new ShopItemReservation();
                r.readData(lineScanner);
                storeItemReservation(r);
                lineScanner.close();
            }
            fileScanner.close();
            System.out.println("Reservations loaded: " + itemReservationMap.size());
        }
        catch (FileNotFoundException e)
        {
            System.out.println("Could not open file: " + file);
        }
    }

    // -------------------------------------------------------
    // Tool data reading (from Part 2)
    // -------------------------------------------------------

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

        try
        {
            Scanner fileScanner = new Scanner(new File(dir + file));
            String currentType = "";

            while (fileScanner.hasNextLine())
            {
                String currentLine = fileScanner.nextLine().trim();

                if (currentLine.isEmpty() || currentLine.startsWith("//"))
                    continue;
                else if (currentLine.startsWith("["))
                    currentType = currentLine.toLowerCase();
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
                        System.out.println("Warning: did not recognise data type - "
                                         + currentType + " - line skipped.");
                    }

                    lineScanner.close();
                }
            }

            fileScanner.close();
            System.out.println("Done! " + itemList.size() + " items loaded.");
        }
        catch (FileNotFoundException e)
        {
            System.out.println("Could not open file: " + file);
        }
    }

    // Getters
    public List<ShopItem> getToolList()     { return itemList; }
    public List<Customer> getCustomerList() { return customerList; }
}