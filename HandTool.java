import java.util.Scanner;

public class HandTool extends Tool
{
    private boolean sharpenable;

    // No-arg constructor
    public HandTool()
    {
        super();
        sharpenable = false;
    }

    // Getter
    public boolean isSharpenable() { return sharpenable; }

    // Overrides Tool's printDetails()
    @Override
    public void printDetails()
    {
        super.printDetails();
        System.out.println("  sharpenable: " + sharpenable);
    }

    // Overrides Tool's readData()
    @Override
    public void readData(Scanner scanner)
    {
        super.readData(scanner);
        sharpenable = Boolean.parseBoolean(scanner.next().trim());
    }
}