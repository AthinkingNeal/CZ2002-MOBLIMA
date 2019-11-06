
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class CineplexDB extends Database {
    private static HashMap<Integer, Cineplex> cineplexMap = new HashMap<Integer, Cineplex>();
    private String filename;


    public void addRecord(int cineplexID, String name, String location){
        Cineplex temp = new Cineplex(cineplexID,name,location);
        cineplexMap.put(cineplexID,temp);
        System.out.println("You have successfully added a new cineplex!");
    }


    public void addRecord() {
        Scanner sc = new Scanner(System.in);
        int cineplexID;
        String name;
        String location;
        System.out.println("Please enter the cineplexID: ");
        cineplexID = sc.nextInt();
        System.out.println("Please enter the name of the cineplex: ");
        name = sc.next();
        System.out.println("Please enter the location of the cineplex: ");
        location = sc.nextLine();

        addRecord(cineplexID,name,location);


    }

    public void deleteRecord(int cineplexID){
        if (cineplexMap.containsKey(cineplexID) == false) {
            System.out.println("You have entered an invalid cineplex ID");
        }
        else{
            cineplexMap.remove(cineplexID);
        }
    }


    public void deleteRecord() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Please input the TID of the PaymentRecord you want to delete: ");
        int cineplexID = sc.nextInt();
        deleteRecord(cineplexID);
    }


    // The Cineplex information  is seldom changed and is not required for the staff. Therefore we don't need to implement this method.
    public void updateRecord() {

    }

    public void saveToFile() {
            try{
                FileOutputStream fos = new FileOutputStream(this.filename);
                ObjectOutputStream oos = new ObjectOutputStream(fos);
                System.out.print("saving data to " + filename + "...");
                oos.writeObject(cineplexMap);
                oos.close();
            } catch (IOException e) {
                System.out.println("File input error");
            }
        }



    public static boolean findCineplexByID(Integer cineplexID) {

        if (cineplexMap.containsKey(cineplexID))
            return true;
        else
            return false;
    }


    public static Cineplex getCineplexByID(Integer cineplexID) {
        if (findCineplexByID(cineplexID))
            return cineplexMap.get(cineplexID);
        else return null;
    }

}
