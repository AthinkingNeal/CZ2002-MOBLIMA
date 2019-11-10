import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Scanner;

/*
  in the class Date, the attribute stored is a hashmap, storing the date of holidays

  day of week is retrieved from system

 */

public class DateDB {
    private String filename;
    // the value of the hashmap is a boolean array, containing [isHoliday, isHolidayEve]
    private ArrayList<String> dateList = new ArrayList<String>();

    public DateDB(String filename) {
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

    public String getCurrentDate() {
        return java.time.LocalDate.now().toString();
    }

    public String getCurrentTime() {
        LocalTime now = LocalTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH-mm");
        return now.format(formatter);
    }

    public boolean getIsWeekend(String yourDate){
        try{
            Calendar calendar = Calendar.getInstance();
            java.util.Date date = new SimpleDateFormat("yyyy-MM-dd").parse(yourDate);
            calendar.setTime(date);
            int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);
            return dayOfWeek == 7 || dayOfWeek == 1;
        }
        catch (ParseException e)
        {
            System.out.println("Invalid date format!");
            return false;
        }

    }

    public boolean IsHoliday(String date) { // input date should be in the format of yyyy-mm-dd
        return dateList.contains(date);
    }

    public void addHoliday() {
        System.out.println("Enter the date to be set as holiday (yyyy-MM-dd)");
        Scanner sc = new Scanner(System.in);
        String date = sc.next();
        if (dateList.contains(date)) {
            System.out.println("Holiday already added!");
        } else {
            dateList.add(date);
            System.out.println("New holiday date successfully added");
        }
    }

    public void saveToFile() {
        try {
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
