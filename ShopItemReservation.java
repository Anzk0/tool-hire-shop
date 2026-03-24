import java.util.Date;
import java.util.Scanner;
import java.io.PrintWriter;

public class ShopItemReservation
{
    private String reservationNo;
    private String itemID;
    private String customerID;
    private Date startDate;
    private int noOfDays;

    // Constructor - startDate passed as String and converted to Date
    public ShopItemReservation(String reservationNo, String itemID, String customerID,
                               String startDate, int noOfDays)
    {
        this.reservationNo = reservationNo;
        this.itemID        = itemID;
        this.customerID    = customerID;
        this.startDate     = DateUtil.convertStringToDate(startDate);
        this.noOfDays      = noOfDays;
    }

    // No-arg constructor - used when reading from file
    public ShopItemReservation()
    {
        reservationNo = "";
        itemID        = "";
        customerID    = "";
        startDate     = null;
        noOfDays      = 0;
    }

    // Getters
    public String getReservationNo() { return reservationNo; }
    public String getItemID()        { return itemID; }
    public String getCustomerID()    { return customerID; }
    public Date getStartDate()       { return startDate; }
    public int getNoOfDays()         { return noOfDays; }

    // Prints reservation details to terminal
    public void printDetails()
    {
        System.out.println("Reservation No: " + reservationNo
                         + " | Item: "        + itemID
                         + " | Customer: "    + customerID
                         + " | Start: "       + DateUtil.convertDateToShortString(startDate)
                         + " | Days: "        + noOfDays);
    }

    // Returns a readable string summary - used by Diary class
    @Override
    public String toString()
    {
        return "Reservation " + reservationNo
             + " (Customer: " + customerID
             + ", Item: "     + itemID + ")";
    }

    // Reads reservation fields from scanner
    // Field order: reservationNo, itemID, customerID, startDate, noOfDays
    public void readData(Scanner sc)
    {
        reservationNo = sc.next().trim();
        itemID        = sc.next().trim();
        customerID    = sc.next().trim();
        startDate     = DateUtil.convertStringToDate(sc.next().trim());
        noOfDays      = Integer.parseInt(sc.next().trim());
    }

    // Writes reservation data to file via PrintWriter
    public void writeData(PrintWriter pw)
    {
        pw.println(reservationNo + ", "
                 + itemID        + ", "
                 + customerID    + ", "
                 + DateUtil.convertDateToShortString(startDate) + ", "
                 + noOfDays);
    }
}