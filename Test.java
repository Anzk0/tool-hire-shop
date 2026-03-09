public class Test
{
    public static void main(String[] args)
    {
        // --- Test Customer constructor with 4 params ---
        Customer c1 = new Customer("Newton", "David", "E", "Dr");
        System.out.println("New customer (ID should be unknown):");
        c1.printDetails();
        System.out.println();

        // --- Test no-arg Customer constructor ---
        Customer c2 = new Customer();
        System.out.println("No-arg customer (all empty):");
        c2.printDetails();
        System.out.println();

        // --- Test storeCustomer() generates a proper ID ---
        Shop shop = new Shop();
        shop.storeCustomer(c1);
        System.out.println("After storeCustomer() - ID should now be generated:");
        c1.printDetails();
        System.out.println();

        // --- Test readCustomerData() ---
        Shop shop2 = new Shop();
        shop2.readCustomerData();
        shop2.printAllCustomers();
        System.out.println();

        // --- Test writeCustomerData() ---
        shop2.writeCustomerData();
        System.out.println();

        // --- Test readToolData() still works ---
        Shop shop3 = new Shop();
        shop3.readToolData();
        shop3.printAllTools();
        System.out.println();

        // --- Test getItem() and getCustomer() ---
        System.out.println("Testing getItem() and getCustomer():");
        ShopItem found = shop3.getItem("RD2001");
        if (found != null) { found.printDetails(); }
        else { System.out.println("Item not found."); }

        // --- Empty shop edge case ---
        Shop emptyShop = new Shop();
        emptyShop.printAllTools();
        emptyShop.printAllCustomers();
    }
}