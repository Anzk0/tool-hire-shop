import java.util.Date;

public class Test
{
    public static void main(String[] args)
    {
        // --- Step 1: Test DateUtil ---
        System.out.println("=== DateUtil Tests ===");
        Date date1 = DateUtil.convertStringToDate("25-03-2023");
        Date date2 = DateUtil.convertStringToDate("01-04-2023");
        System.out.println("Date 1: " + DateUtil.convertDateToLongString(date1));
        System.out.println("Date 2: " + DateUtil.convertDateToShortString(date2));
        System.out.println("Days between: " + DateUtil.daysBetween(date1, date2));
        System.out.println();

        // --- Step 2: Load data and make reservations ---
        Shop shop = new Shop();
        shop.readToolData();      // select items_all.txt
        shop.readCustomerData();  // select customer_data.txt
        System.out.println();

        shop.printAllCustomers();
        System.out.println();

        // Get a valid customer and item ID to test with
        String custID = shop.getCustomerList().get(0).getCustomerID();
        String itemID = "RD2001";

        // Make a valid reservation
        System.out.println("=== Making Reservations ===");
        shop.makeItemReservation(custID, itemID, "25-03-2023", 3);
        System.out.println();

        // Try invalid reservation - bad customer ID
        shop.makeItemReservation("INVALID", itemID, "25-03-2023", 3);

        // Try invalid reservation - bad item ID
        shop.makeItemReservation(custID, "INVALID", "25-03-2023", 3);

        // Try invalid reservation - item already booked on those days
        shop.makeItemReservation(custID, itemID, "26-03-2023", 2);
        System.out.println();

        // Print all reservations
        shop.printItemReservations();
        System.out.println();

        // --- Step 3: Test diary ---
        System.out.println("=== Diary Entries ===");
        shop.printDiaryEntries("24-03-2023", "30-03-2023");
        System.out.println();

        // --- Test write and read reservation data ---
        shop.writeItemReservationData();
        System.out.println();

        // --- Test delete reservation ---
        System.out.println("=== Delete Reservation ===");
        shop.deleteItemReservation("000001");
        shop.printItemReservations();
        System.out.println();

        // --- Empty shop edge case ---
        Shop emptyShop = new Shop();
        emptyShop.printAllTools();
        emptyShop.printAllCustomers();
        emptyShop.printItemReservations();
    }
}