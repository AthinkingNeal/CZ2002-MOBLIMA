import java.util.*;
import java.io.*;


public class MovieGoerDB implements Database {
    private HashMap<Integer, MovieGoer> MovieGoerMap = new HashMap<Integer, MovieGoer>();
    private String filename;


    public MovieGoerDB(String filename) {
        this.filename = filename;
        try {
            FileInputStream fis = new FileInputStream(filename);
            ObjectInputStream ois = new ObjectInputStream(fis);
            System.out.print("reading data from " + filename + "...");
            this.MovieGoerMap = (HashMap<Integer, MovieGoer>) ois.readObject();
            ois.close();
        } catch (IOException e) {
            System.out.println("File input error");
        } catch (ClassNotFoundException e) {
            System.out.println(e);
        }
    }

    public void addRecord(String name, int movieGoerID, int mobileNumber, String emailAddress,int age){
        MovieGoer temp = new MovieGoer(name,movieGoerID,mobileNumber,emailAddress,age);
        MovieGoerMap.put(movieGoerID,temp);
        System.out.println("MovieGoer Record update success.");
    }

    public void addRecord(){
         Scanner sc = new Scanner(System.in);
         System.out.println("Please input your name: ");
         String name = sc.nextLine();
         System.out.println("Please input your movieGoerID");
         int movieGoerID = sc.nextInt();
         System.out.println("Please input your mobile number: ");
         int mobileNumber = sc.nextInt();
         System.out.println("Please input your email address: ");
         String emailAddress = sc.nextLine();
         System.out.println("Please input your age: ");
         int age = sc.nextInt();

         addRecord(name,movieGoerID,mobileNumber,emailAddress,age);
    }


    // We assume that Movie Goer does not need to delete his/her own record

    public MovieGoer findRecordByMovieGoerID(int movieGoerID){
        for (Map.Entry<Integer,MovieGoer> entry : MovieGoerMap.entrySet()){
            if(entry.getValue().getMovieGoerID() == movieGoerID){
                System.out.println("The movie goer is found!");
                return entry.getValue();
            }
        }
        System.out.println("The movieGoer you are looking for does not exist, we are assigning you to this new ID");
        addRecord();

        for (Map.Entry<Integer,MovieGoer> entry : MovieGoerMap.entrySet()){
            if(entry.getValue().getMovieGoerID() == movieGoerID){
                System.out.println("The movie goer is found!");
                return entry.getValue();
            }
        }
        System.out.println("The movieGoer is not found!");
        return null;
    }

    public void deleteRecord() {
        System.out.println("delete record");
    }

    public void updateRecord() {
        System.out.println("Update record");
    }

    public void updateRecord(int movieGoerID) {
        MovieGoer m = findRecordByMovieGoerID(movieGoerID);
        System.out.println("Which attribute would you like to update?");
        System.out.println("1. Update name");
        System.out.println("2. Update mobile number");
        System.out.println("3. Update email address");
        System.out.println("4. Update age");
        System.out.println("5. Exit");

        Scanner sc = new Scanner(System.in);
        int choice = sc.nextInt();
        while (choice != 5) {
            switch (choice) {
                case 1:
                    System.out.println("Please input your new name: ");
                    m.setName(sc.nextLine());
                case 2:
                    System.out.println("Please input your new mobile number: ");
                    m.setMobileNumber(sc.nextInt());
                case 3:
                    System.out.println("Please input your new email address: ");
                    m.setEmailAddress(sc.nextLine());
                case 4:
                    System.out.println("Please input your new age: ");
                    m.setAge(sc.nextInt());
            }
            choice = sc.nextInt();
        }


        System.out.println("Please input your age: ");
        int age = sc.nextInt();
    }

    public void saveToFile() {
        try{
            FileOutputStream fos = new FileOutputStream(this.filename);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            System.out.print("saving data to " + filename + "...");
            oos.writeObject(MovieGoerMap);
            oos.close();
        } catch (IOException e) {
            System.out.println("File input error");
        }
    }

}
