public class Test
{
    public static void main(String[] args)
    {
        // --- Test ElectricTool default values ---
        ElectricTool myElectricTool = new ElectricTool();
        System.out.println("Electric tool default values:");
        myElectricTool.printDetails();
        System.out.println();

        // --- Test HandTool default values ---
        HandTool myHandTool = new HandTool();
        System.out.println("Hand tool default values:");
        myHandTool.printDetails();
        System.out.println();

        // --- Test Perishable default values ---
        Perishable myPerishable = new Perishable();
        System.out.println("Perishable default values:");
        myPerishable.printDetails();
        System.out.println();

        // --- Test Workwear default values ---
        Workwear myWorkwear = new Workwear();
        System.out.println("Workwear default values:");
        myWorkwear.printDetails();
        System.out.println();

        // --- Read from file and display all loaded items ---
        Shop shopFromFile = new Shop();
        shopFromFile.readToolData();
        System.out.println();
        shopFromFile.printAllTools();
        System.out.println();

        // --- Edge case: empty shop ---
        Shop emptyShop = new Shop();
        emptyShop.printAllTools();
    }
}