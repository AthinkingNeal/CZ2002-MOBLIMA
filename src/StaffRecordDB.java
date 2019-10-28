import java.util.HashMap;
import java.util.Map;
import java.io.*;
import java.util.Scanner;


public class StaffRecordDB extends Database {
    // key: StaffID, value: password
    private Map<String, String> staffIDPasswordRecord = new HashMap<String, String>();

    /**
    Default constructor with empty staffIDPasswordRecord
     */
    public StaffRecordDB() {
    }

    /**
     Initiate the class by adding a single staff record
     */
    public StaffRecordDB(String staffID, String password)
    {
        addLoginRecord(staffID, password);
    }

    /**
     Initiate the class using a txt file to populate userPasswordRecord
     */
    public StaffRecordDB(String fileName)
    {
        this.addLoginRecords(fileName);
    }

    /**
     Enable adding of a new staff record;
     new staffID must be different than existing usernames; usernam
     password
     */
    public void addLoginRecord(String staffID, String password)
    {
       try { // check whether this staffID exists or not;
           if (staffIDPasswordRecord.containsKey(staffID))
               throw new IllegalArgumentException();

           // if does not exist:
           this.staffIDPasswordRecord.put(staffID, password);
       }
       catch(IllegalArgumentException e)
       {
           System.out.println( "This staffID already exists! Try again!");
           System.exit( 0 );
       }
    }


    /**
     Given a buffererReader stream, populate this.staffIDPasswordRecord
     */
    public void addLoginRecords(String fileName)
    {
        try {
            FileReader frStream = new FileReader(fileName);
            BufferedReader brStream = new BufferedReader(frStream);
            String inputLine;
            while((inputLine = brStream.readLine())!=null)
            {

                String delims = "[ ]+";
                String[] tokens = inputLine.split(delims);
                this.addLoginRecord(tokens[0], tokens[1]);
            }
            brStream.close();
        }
        catch ( FileNotFoundException e ) {
            System.out.println( "Error opening the input file!");
            System.exit( 0 );
        }
        catch ( IOException e ) {
            System.out.println( "IO Error!");
            e.printStackTrace();
            System.exit( 0 );
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
        Scanner s = new Scanner(System.in);
        System.out.println("Please enter your staff ID:");
        String id = s.nextLine();
        Console console = System.console();
        char[] passwordArray = console.readPassword("Please enter your password: ");
        String password = new String(passwordArray);
        // if running in IDE:
        //        System.out.println("Please enter your passward:");
        //        String password = s.nextLine();
        if(!this.staffIDPasswordRecord.containsKey(id))
        {
            if(!this.staffIDPasswordRecord.get(id).equals(password)) {
                System.out.println("Successful Login!");
                return true;
            }
            else
            {
                System.out.println( "Wrong password! Try again!   ");
                return false;
            }
        }
        else
        {
            System.out.println( "This staffID does not exists! Try again!   ");
            return false;
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
