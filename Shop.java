import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.awt.FileDialog;

public class Shop
{
    private ArrayList<Tool> toolList;

    public Shop()
    {
        toolList = new ArrayList<Tool>();
    }

    // Add a tool to the list
    public void storeTool(Tool tool)
    {
        toolList.add(tool);
    }

    // Print all tools in the list
    public void printAllTools()
    {
        if (toolList.isEmpty()) {
            System.out.println("No tools stored.");
            return;
        }
        for (Tool tool : toolList) {
            tool.printDetails();
        }
    }

    // Read tool data from a file chosen via file dialog
    public void readToolData()
    {
        // Open file dialog for user to select the file
        FileDialog fileDialog = new FileDialog((java.awt.Frame) null, "Select Tool Data File", FileDialog.LOAD);
        fileDialog.setDirectory(".");
        fileDialog.setVisible(true);

        String directory = fileDialog.getDirectory();
        String filename  = fileDialog.getFile();

        if (filename == null) {
            System.out.println("No file selected.");
            return;
        }

        try {
            Scanner scanner1 = new Scanner(new File(directory + filename));

            while (scanner1.hasNextLine()) {
                String lineOfText = scanner1.nextLine().trim();

                // Skip blank lines and comments
                if (lineOfText.isEmpty() || lineOfText.startsWith("//")) {
                    continue;
                }

                // Second scanner to parse the data line
                Scanner scanner2 = new Scanner(lineOfText);
                scanner2.useDelimiter(",\\s*|\\s*,\\s*");

                // Create blank tool and let it read its own data (Step 3)
                Tool tool = new Tool();
                tool.readData(scanner2);
                storeTool(tool);

                scanner2.close();
            }

            scanner1.close();
            System.out.println("Loaded " + toolList.size() + " tools.");

        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + filename);
        }
    }

    public ArrayList<Tool> getToolList()
    {
        return toolList;
    }
}