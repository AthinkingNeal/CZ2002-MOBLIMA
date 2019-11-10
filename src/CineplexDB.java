

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class CineplexDB implements Database {
    private HashMap<Integer, Cineplex> cineplexMap = new HashMap<Integer, Cineplex>();
    private String filename;

    public CineplexDB(String filename) {
        this.filename = filename;
        try {
            FileInputStream fis = new FileInputStream(filename);
            ObjectInputStream ois = new ObjectInputStream(fis);

            System.out.print("reading data from " + filename + "...");
            this.cineplexMap = (HashMap<Integer, Cineplex>) ois.readObject();
            ois.close();
        } catch (IOException e) {
            System.out.println("File input error");
        } catch (ClassNotFoundException e) {
            System.out.println(e);
        }
    }


    public void addRecord(int cineplexID, String name, String location){
        Cineplex temp = new Cineplex(cineplexID,name,location);
        cineplexMap.put(cineplexID,temp);
        System.out.println("You have successfully added a new cineplex!");
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

    public boolean findCineplexByID(int cineplexID) {
        if (cineplexMap.containsKey(cineplexID))
            return true;
        else
            return false;
    }

    public Cineplex getCineplexByID(int cineplexID) {
        if (findCineplexByID(cineplexID))
            return this.cineplexMap.get(cineplexID);
        else return null;
    }

    public void addRecord() {
        Scanner sc = new Scanner(System.in);
        int cineplexID;
        String name;
        String location;
        System.out.println("Please enter the cineplexID: ");
        cineplexID = sc.nextInt();
        String dummy = sc.nextLine();
        System.out.println("Please enter the name of the cineplex: ");
        name = sc.nextLine();
        System.out.println("Please enter the location of the cineplex: ");
        location = sc.nextLine();

        addRecord(cineplexID, name, location);


    }

    public static void main(String args[]) {
        CineplexDB cineplexDB = new CineplexDB(MoblimaApp.cineplexDBFile);
//        for (int i = 0; i < 3; i++) {
//            cineplexDB.addRecord();
//        }
//
//        cineplexDB.saveToFile();

        System.out.println(cineplexDB.getCineplexByID(1).getLocation());
    }

}
