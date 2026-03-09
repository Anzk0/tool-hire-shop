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

    // Adds a tool to the shop
    public void storeTool(Tool tool)
    {
        toolList.add(tool);
    }

    // Displays all tools currently in the shop
    public void printAllTools()
    {
        if (toolList.isEmpty())
        {
            System.out.println("The shop has no tools at the moment.");
            return;
        }
        System.out.println("=== Shop Inventory (" + toolList.size() + " items) ===");
        for (Tool t : toolList)
        {
            t.printDetails();
        }
        System.out.println("==========================================");
    }

    // Opens a file dialog, reads tool data and loads it into toolList
    public void readToolData()
    {
        FileDialog fd = new FileDialog((java.awt.Frame) null, "Open Tool Data File", FileDialog.LOAD);
        fd.setDirectory(".");
        fd.setVisible(true);

        String dir  = fd.getDirectory();
        String file = fd.getFile();

        if (file == null)
        {
            System.out.println("No file was chosen - operation cancelled.");
            return;
        }

        System.out.println("Reading from: " + file);

        try
        {
            Scanner fileScanner = new Scanner(new File(dir + file));

            // Keeps track of what type of tool we are currently reading
            String currentType = "";

            while (fileScanner.hasNextLine())
            {
                String currentLine = fileScanner.nextLine().trim();

                // Skip blank lines
                if (currentLine.isEmpty())
                {
                    continue;
                }

                // Skip comment lines
                else if (currentLine.startsWith("//"))
                {
                    continue;
                }

                // Flag line - update the current type of data
                else if (currentLine.startsWith("["))
                {
                    currentType = currentLine.toLowerCase();
                }

                // Real data line - create the right type of tool
                else
                {
                    Scanner lineScanner = new Scanner(currentLine);
                    lineScanner.useDelimiter("\\s*,\\s*");

                    if (currentType.contains("electric"))
                    {
                        ElectricTool et = new ElectricTool();
                        et.readData(lineScanner);
                        storeTool(et);
                    }
                    else if (currentType.contains("hand"))
                    {
                        HandTool ht = new HandTool();
                        ht.readData(lineScanner);
                        storeTool(ht);
                    }
                    else if (currentType.isEmpty())
                    {
                        // No flag seen yet - basic tool (backwards compatible with tool_data_1.txt)
                        Tool t = new Tool();
                        t.readData(lineScanner);
                        storeTool(t);
                    }
                    else
                    {
                        System.out.println("Warning: did not recognise data type - " + currentType + " - line skipped.");
                    }

                    lineScanner.close();
                }
            }

            fileScanner.close();
            System.out.println("Done! " + toolList.size() + " tools loaded into the shop.");

        }
        catch (FileNotFoundException e)
        {
            System.out.println("Could not open file: " + file);
        }
    }

    public ArrayList<Tool> getToolList()
    {
        return toolList;
    }
}