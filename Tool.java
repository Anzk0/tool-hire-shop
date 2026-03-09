import java.util.Scanner;
public class Tool {

    private String toolName;
    private String itemCode;
    private int timesBorrowed;
    private boolean onLoan;
    private int cost;
    private int weight;

    public Tool(String toolName, String itemCode, int timesBorrowed,
                boolean onLoan, int cost, int weight) {
        this.toolName      = toolName;
        this.itemCode      = itemCode;
        this.timesBorrowed = timesBorrowed;
        this.onLoan        = onLoan;
        this.cost          = cost;
        this.weight        = weight;
    }

    public Tool() {
        // Fields will be populated by readData()
        this.toolName      = "";
        this.itemCode      = "";
        this.timesBorrowed = 0;
        this.onLoan        = false;
        this.cost          = 0;
        this.weight        = 0;
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
        // Convert the boolean onLoan to a readable "yes" or "no"
        String loanStatus = onLoan ? "yes" : "no";

        System.out.println("Tool name: " + toolName
                         + "; code: "          + itemCode
                         + "; timesBorrowed: " + timesBorrowed
                         + "; onLoan: "        + loanStatus
                         + "; cost: "          + cost
                         + "; weight: "        + weight);
    }


    public void readData(Scanner scanner) {
        // Read each token in the order they appear in the file.
        // trim() is used on each token to remove any stray leading/trailing spaces.

        this.toolName      = scanner.next().trim();
        this.itemCode      = scanner.next().trim();
        this.timesBorrowed = Integer.parseInt(scanner.next().trim());
        this.onLoan        = Boolean.parseBoolean(scanner.next().trim());
        this.cost          = Integer.parseInt(scanner.next().trim());
        this.weight        = Integer.parseInt(scanner.next().trim());
    }

} // end class Tool
