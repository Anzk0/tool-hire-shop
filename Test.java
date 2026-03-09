public class Test
{
    public static void main(String[] args)
    {
        // --- Step 1: Create a tool and store it in the shop ---
        Shop shop = new Shop();
        Tool t1 = new Tool("DeWalt Circular Saw", "RD6582", 250, false, 350, 345);
        shop.storeTool(t1);
        shop.printAllTools();
        System.out.println();

        // --- Test printDetails() directly on a single tool ---
        t1.printDetails();
        System.out.println();

        // --- Test storing a second tool ---
        Tool t2 = new Tool("Makita Drill", "RD2001", 12, false, 149, 1800);
        shop.storeTool(t2);
        shop.printAllTools();
        System.out.println();

        // --- Step 2/3: Read tools from file ---
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
