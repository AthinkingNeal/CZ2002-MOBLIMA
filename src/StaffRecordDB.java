import java.util.HashMap;
import java.util.Map;
import java.io.*;
import java.util.Scanner;


public class StaffRecordDB extends Database {
    // key: StaffID, value: password
    private Map<String, String> staffIDPasswordRecord = new HashMap<String, String>();
    private String filename;

    /**
     Initiate the class using a txt file to populate userPasswordRecord
     */
    public StaffRecordDB(String fileName)
    {
        this.filename = filename;
        try {
            FileInputStream fis = new FileInputStream(filename);
            ObjectInputStream ois = new ObjectInputStream(fis);

            System.out.print("reading data from " + filename + "...");
            this.staffIDPasswordRecord = (Map<String, String>) ois.readObject();
            ois.close();
        } catch (IOException e) {
            System.out.println("File input error");
        } catch (ClassNotFoundException e) {
            System.out.println(e);
        }
    }


    /**
     Enable adding of a new staff record;
     new staffID must be different than existing usernames;
     */
    public void addRecord()
    {
        int trial = 0;
        Scanner s = new Scanner(System.in);
        System.out.println("Please input a new staffID ");
        String staffID = s.nextLine();
        while (staffIDPasswordRecord.containsKey(staffID) && ++trial <= 3) {
            System.out.println("This staffID already exists! Try again!");
            System.out.println("Please input a new staffID ");
            staffID = s.nextLine();
        }
        if (trial > 3) {
            System.out.println("Too many trials! Program exiting!");
            System.exit(0);
        }
        // if does not exist:
        Console console = System.console();
        int trial2 = 0;
        char[] passwordArray = console.readPassword("Please create your password: ");
        char[] passwordArray2 = console.readPassword("Please confirm your password: ");
        while (!passwordArray2.equals(passwordArray) && ++trial2 <= 3) {
            System.out.println("The password does not match with the previous one! Try again!");
            passwordArray = console.readPassword("Please create your password: ");
            passwordArray2 = console.readPassword("Please confirm your password: ");
        }
        if (trial2 > 3) {
            System.out.println("Too many trials! Program exiting!");
            System.exit(0);
        }
        // if correctly input password
        String password = new String(passwordArray);
        staffIDPasswordRecord.put(staffID, password);
        System.out.println("Successfully Added!");
    }

    /**
     * Enable staff to update their password after they have logged in;
     */
    public void updateRecord() {
        // if successfully logged in
        if (login()) {
            System.out.println("Enter your staff ID: ");
            Scanner s = new Scanner(System.in);
            String staffID = s.nextLine();
            Console console = System.console();
            int trial2 = 0;
            char[] passwordArray = console.readPassword("Please type in your new password: ");
            char[] passwordArray2 = console.readPassword("Please confirm your new password: ");
            while (!passwordArray2.equals(passwordArray) && ++trial2 <= 3) {
                System.out.println("The password does not match with the previous one! Try again!");
                passwordArray = console.readPassword("Please update your password: ");
                passwordArray2 = console.readPassword("Please confirm your new password: ");
            }
            if (trial2 > 3) {
                System.out.println("Too many trials! Program exiting!");
                System.exit(0);
            }
            String newPassword = new String(passwordArray);
            // if successfully changed password
            staffIDPasswordRecord.remove(staffID);
            staffIDPasswordRecord.put(staffID, newPassword);
            System.out.println("Successfully updated password!");
        }
    }

    /**
     * Enable staff to delete their record after they have logged in;
     */
    public void deleteRecord() {
        // if successfully logged in
        if (login()) {
            System.out.println("Enter your staff ID: ");
            Scanner s = new Scanner(System.in);
            String staffID = s.nextLine();
            System.out.println("Are you sure you would like to delete your record? you can no longer access the system after this!");
            String ans = s.nextLine();

            if (ans.equals("Y") || ans.equals("y")) {
                staffIDPasswordRecord.remove(staffID);
                System.out.println("Successfully deleter!");
                System.exit(0);
            }
        }
    }

    /**
     Prompts staff to enter staffID and username;
     If all inputs are correct, login is successful;
     Password is masked by asterisks;
     returns true if login is successful.
     Only runs in console! not in IDE!
     */
    public boolean login()
    {
        System.out.println("Please log in first.");

        int trial = 0;
        boolean loggedIn = false;
        String staffID = "";
        while (!loggedIn && ++trial <= 3) {
            Scanner s = new Scanner(System.in);
            System.out.println("Please enter your staff ID:");
            staffID = s.nextLine();
            Console console = System.console();
            char[] passwordArray = console.readPassword("Please enter your password: ");
            String password = new String(passwordArray);
            if (!this.staffIDPasswordRecord.containsKey(staffID)) {
                if (!this.staffIDPasswordRecord.get(staffID).equals(password)) {
                    System.out.println("Successful Login!");
                    loggedIn = true;
                } else {
                    System.out.println("Wrong password! Try again!   ");
                    loggedIn = false;
                }
            } else {
                System.out.println("This staffID does not exists! Try again!   ");
                loggedIn = false;
            }
        }
        if (trial > 3) {
            System.out.println("Too many trials! Program exiting!");
            System.exit(0);
        }

        return loggedIn;
    }


    public void saveToFile() {
        try {
            FileOutputStream fos = new FileOutputStream(this.filename);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            System.out.print("saving data to " + filename + "...");
            oos.writeObject(staffIDPasswordRecord);
            oos.close();
        } catch (IOException e) {
            System.out.println("File input error");
        }
    }

    /**
     Unit testing
     */
    public static void main(String args[])
    {

        //StaffRecordDB record = new StaffRecordDB("sazt", "123456");
        StaffRecordDB record = new StaffRecordDB("staffRecords.txt");
        record.login();

    }


}
