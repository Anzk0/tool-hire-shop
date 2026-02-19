import java.util.Scanner;

public class Tool {
    private String toolName;
    private String itemCode;
    private int timesBorrowed;
    private boolean onLoan;
    private int cost;   // whole pounds 
    private int weight; // grams

    public Tool() {
        // empty tool
    }

    public Tool(String toolName, String itemCode, int timesBorrowed, boolean onLoan, int cost, int weight) {
        this.toolName = toolName;
        this.itemCode = itemCode;
        this.timesBorrowed = timesBorrowed;
        this.onLoan = onLoan;
        this.cost = cost;
        this.weight = weight;
    }

    public String getToolName() {
        return toolName;
    }

    public String getItemCode() {
        return itemCode;
    }

    public int getTimesBorrowed() {
        return timesBorrowed;
    }

    public boolean isOnLoan() {
        return onLoan;
    }

    public int getCost() {
        return cost;
    }

    public int getWeight() {
        return weight;
    }

    public void printDetails() {
        System.out.println(
            "Tool name: " + toolName +
            "; code: " + itemCode +
            "; timesBorrowed: " + timesBorrowed +
            "; onLoan: " + (onLoan ? "yes" : "no") +
            "; cost: " + cost +
            "; weight: " + weight
        );
    }

    public void readData(Scanner sc) {
        sc.useDelimiter("\\s*,\\s*"); // comma with optional spaces around it
    
        toolName = sc.next().trim();
        itemCode = sc.next().trim();
        timesBorrowed = sc.nextInt();
        onLoan = sc.nextBoolean();
        cost = sc.nextInt();
        weight = sc.nextInt();
        
        //The tool works even if you forget the delimiter in Shop:
    }

}
