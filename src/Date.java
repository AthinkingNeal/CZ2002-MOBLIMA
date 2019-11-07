

import java.io.*;
import java.util.ArrayList;
import java.util.Calendar;

/*
  in the class Date, the attribute stored is a hashmap, storing the date of holidays

  day of week is retrieved from system

 */
public class Date {
    private String filename;
    // the value of the hashmap is a boolean array, containing [isHoliday, isHolidayEve]
    private ArrayList<String> dateList = new ArrayList<String>();

    public Date(String filename) {
        this.filename = filename;
        try {
            FileInputStream fis = new FileInputStream(filename);
            ObjectInputStream ois = new ObjectInputStream(fis);

            System.out.print("reading data from " + filename + "...");
            this.dateList = (ArrayList<String>) ois.readObject();
            ois.close();
        } catch (IOException e) {
            System.out.println("File input error");
        } catch (ClassNotFoundException e) {
            System.out.println(e);
        }
    }

    public String getDate() {
        return java.time.LocalDate.now().toString();
    }

    public int getDayOfWeek() {
        Calendar calendar = Calendar.getInstance();
        return calendar.get(Calendar.DAY_OF_WEEK);
    }

    public boolean IsHoliday(String date) { // input date should be in the format of yyyy-mm-dd
        return dateList.contains(date);
    }

    public void addHoliday(String date) {
        if (dateList.contains(date)) {
            System.out.println("Holiday already added!");
        } else {
            dateList.add(date);
        }
    }

    public void saveToFile() {
        try{
            FileOutputStream fos = new FileOutputStream(this.filename);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            System.out.print("saving data to " + filename + "...");
            oos.writeObject(dateList);
            oos.close();
        } catch (IOException e) {
            System.out.println("File input error");
        }
    }

}
