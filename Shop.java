import java.awt.FileDialog;
import java.awt.Frame;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Shop {
    private ArrayList<Tool> toolList;

    public Shop() {
        toolList = new ArrayList<Tool>();
    }

    public void storeTool(Tool tool) {
        toolList.add(tool);
    }

    public void printAllTools() {
        if (toolList.size() == 0) {
            System.out.println("No tools stored.");
            return;
        }
        for (Tool t : toolList) {
            t.printDetails();
        }
    }

   public void readToolData() {
    FileDialog fd = new FileDialog((Frame) null, "Select tool data file", FileDialog.LOAD);
    fd.setVisible(true);

    String fileName = fd.getFile();
    String dirName = fd.getDirectory();

    if (fileName == null || dirName == null) {
        System.out.println("No file selected.");
        return;
    }

    File file = new File(dirName, fileName);
    System.out.println("Selected file: " + file.getAbsolutePath());

    Scanner fileScanner = null;
    try {
        fileScanner = new Scanner(file);

        while (fileScanner.hasNextLine()) {
            String lineOfText = fileScanner.nextLine().trim();

            if (lineOfText.length() == 0 || lineOfText.startsWith("//")) {
                continue;                
            }
            Scanner lineScanner = new Scanner(lineOfText);
            lineScanner.useDelimiter("\\s*,\\s*");

            Tool tool = new Tool();
            tool.readData(lineScanner);
            storeTool(tool);

            lineScanner.close();
        }

    } catch (FileNotFoundException e) {
        System.out.println("File not found: " + file.getAbsolutePath());
    } finally {
        if (fileScanner != null) {
            fileScanner.close();
        }
    }

    //after reading, call printAllTools() 
    printAllTools();
}
}
