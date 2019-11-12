import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class PriceTable {

    private String filename;
    private HashMap<String, Double> priceTableRecord = new HashMap<String, Double>();
    // private MovieGoer movieGoer;
    // private MovieSchedule schedule;

    // class of movie is saved in layout
    /*
     * initialise with a file containing all the detail price records
     * */

    public PriceTable(String filename) {
        this.filename = filename;
        try {
            FileInputStream fis = new FileInputStream(filename);
            ObjectInputStream ois = new ObjectInputStream(fis);

            System.out.println("reading data from " + filename + "...");
            this.priceTableRecord = (HashMap<String, Double>) ois.readObject();
            ois.close();
        } catch (IOException e) {
            System.out.println("File input error");
        } catch (ClassNotFoundException e) {
            System.out.println(e);
        }
    }

    // movieID, cinemaType, 2d/3d,

    public void initialiseTable() {
        if (!priceTableRecord.containsKey("initial price")) priceTableRecord.put("initial price", 0.0);
        if (!priceTableRecord.containsKey("children")) priceTableRecord.put("children", 0.0);
        if (!priceTableRecord.containsKey("blockbusters")) priceTableRecord.put("blockbusters", 0.0);
        if (!priceTableRecord.containsKey("3D movies")) priceTableRecord.put("3D movies", 0.0);
        if (!priceTableRecord.containsKey("weekends")) priceTableRecord.put("weekends", 0.0);
        if (!priceTableRecord.containsKey("holidays")) priceTableRecord.put("holidays", 0.0);
        if (!priceTableRecord.containsKey("seniors")) priceTableRecord.put("seniors", 0.0);
        if (!priceTableRecord.containsKey("platinum class")) priceTableRecord.put("platinum class", 0.0);
        if (!priceTableRecord.containsKey("golden class")) {
            priceTableRecord.put("golden class", 0.0);
            System.out.println("Initialised successfully!");
        }
        // if (!priceTableRecord.containsKey("price")) priceTableRecord.put("price", 0.0);
        else System.out.println("You have already initialised!");
    }

    public void updatePriceTable() {
        int choice;
        double price;
        Scanner sc = new Scanner(System.in);

        do {

            System.out.println("Please enter your choice: ");

            System.out.println("1. Update initial price");
            System.out.println("2. Update price reduced for seniors");
            System.out.println("3. Update price reduced for children");
            System.out.println("4. Update price increased for blockbuster movie");
            System.out.println("5. Update price increased for 3D movie");
            System.out.println("6. Update price increased for golden class");
            System.out.println("7. Update price increased for platinum class");
            System.out.println("8. Update price increased for weekends");
            System.out.println("9. Update price increased for holidays");
            System.out.println("10. Show current price settings");
            System.out.println("11. Exit");

            choice = sc.nextInt();

            switch (choice) {
                case 1:
                    utilUpdate("initial price");
                    break;
                case 2:
                    utilUpdate("seniors");
                    break;
                case 3:
                    utilUpdate("children");
                    break;
                case 4:
                    utilUpdate("blockbusters");
                    break;
                case 5:
                    utilUpdate("3D movies");
                    break;
                case 6:
                    utilUpdate("golden class");
                    break;
                case 7:
                    utilUpdate("platinum class");
                    break;
                case 8:
                    utilUpdate("weekends");
                    break;
                case 9:
                    utilUpdate("holidays");
                    break;
                case 10:
                    displayContent();
                    break;
                case 11:
                    break;
            }
        } while (choice < 11);
    }

    public double getPrice(boolean is3D, boolean isBlockbuster, Cinema.CinemaClass cClass, int age, boolean isHoliday, boolean isWeekend) {

        // initial price is the price for normal adults on weekdays
        double price = priceTableRecord.get("initial price");
        double blockbuster_up = priceTableRecord.get("blockbusters");
        double threeD_up = priceTableRecord.get("3D movies");
        double platinum_up = priceTableRecord.get("platinum class");
        double golden_up = priceTableRecord.get("golden class");
        double child_down = priceTableRecord.get("children");
        double senior_down = priceTableRecord.get("seniors");

        // on special dates, no discount for all people
        if (isHoliday || isWeekend) {
            if (isBlockbuster) price += blockbuster_up;
            if (is3D) price += threeD_up;
            if (cClass == Cinema.CinemaClass.golden) price += golden_up;
            if (cClass == Cinema.CinemaClass.platinum) price += platinum_up;
            // normal days, discount apply
        } else {
            // senior
            if (age > 60) {
                price += senior_down;
                if (isBlockbuster) price += blockbuster_up;
                if (is3D) price += threeD_up;
                if (cClass == Cinema.CinemaClass.platinum) price += platinum_up;
                if (cClass == Cinema.CinemaClass.golden) price += golden_up;
            }
            // junior
            else if (age < 12) {
                price += child_down;
                if (isBlockbuster) price += blockbuster_up;
                if (is3D) price += threeD_up;
                if (cClass == Cinema.CinemaClass.golden) price += golden_up;
                if (cClass == Cinema.CinemaClass.platinum) price += platinum_up;
            }
            // common people, no discounts
            else {
                if (isBlockbuster) price += blockbuster_up;
                if (is3D) price += threeD_up;
                if (cClass == Cinema.CinemaClass.platinum) price += platinum_up;
                if (cClass == Cinema.CinemaClass.golden) price += golden_up;
            }
        }
        return price;
    }


    // assume staff would enter price adjustment with +/-
    private void utilUpdate(String type) {
        Scanner sc = new Scanner(System.in);
        double price;
        System.out.println("Enter new price adjustment for " + type + ": ");
        price = sc.nextDouble();
        priceTableRecord.remove(type);
        priceTableRecord.put(type, price);
        System.out.println("Price adjustment for " + type + " successfully updated!");
    }

    public void saveToFile() {
        try {
            FileOutputStream fos = new FileOutputStream(this.filename);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            System.out.print("saving data to " + filename + "...");
            oos.writeObject(priceTableRecord);
            oos.close();
        } catch (IOException e) {
            System.out.println("File input error");
        }
    }

    public void displayContent() {
        priceTableRecord.entrySet().forEach(entry -> {
            System.out.println(entry.getKey() + " " + entry.getValue());
        });
    }

    public static void main(String args[]) {

        String filename = "priceTable.ser";
        PriceTable priceTable = new PriceTable(filename);

        //      priceTable.saveToFile();
        priceTable.displayContent();

//        // priceTable.initialiseTable();
        priceTable.updatePriceTable();

    }
}
