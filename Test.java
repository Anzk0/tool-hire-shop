public class Test
{
    public static void main(String[] args)
    {
        // --- Step 1: Manual tool creation (keeping Part 1 tests) ---
        Shop shop = new Shop();
        Tool t1 = new Tool();
        t1.printDetails();
        shop.storeTool(t1);
        System.out.println();

        // --- Test ElectricTool manually ---
        ElectricTool et = new ElectricTool();
        System.out.println("ElectricTool (defaults):");
        et.printDetails();
        System.out.println();

        // --- Test HandTool manually ---
        HandTool ht = new HandTool();
        System.out.println("HandTool (defaults):");
        ht.printDetails();
        System.out.println();

        // --- Step 2/3: Read tools from file (tool_data_2.txt) ---
        Shop shop2 = new Shop();
        shop2.readToolData();
        System.out.println("Tools loaded from file:");
        shop2.printAllTools();
        System.out.println();

        // --- Empty shop test ---
        Shop emptyShop = new Shop();
        emptyShop.printAllTools();
    }
}