public class Test {
    public static void main(String[] args) {

        // Step 1
        Shop shop = new Shop();
        Tool t = new Tool("DeWalt Circular Saw", "RD6582", 250, false, 350, 345);
        shop.storeTool(t);
        shop.printAllTools();

        System.out.println();

        // Step 2/3
        Shop shop2 = new Shop();
        shop2.readToolData();
        System.out.println("Tools loaded from file:");
        shop2.printAllTools();

        System.out.println();

        // Empty shop test
        Shop emptyShop = new Shop();
        emptyShop.printAllTools();
    }
}

