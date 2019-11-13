import java.util.*;
import java.io.*;

public class PaymentRecordDB implements Database {
    private HashMap<String, PaymentRecord> PaymentRecordMap;
    private String filename;

    /**
     Initiate the class using a txt file to populate userPasswordRecord
     */
    public PaymentRecordDB(String filename)
    {
        this.filename = filename;
        try {
            FileInputStream fis = new FileInputStream(filename);
            ObjectInputStream ois = new ObjectInputStream(fis);
            System.out.println("reading data from " + filename + "...");
            this.PaymentRecordMap = (HashMap<String, PaymentRecord>) ois.readObject();
            ois.close();
        } catch (IOException e) {
            System.out.println("File input error");
        } catch (ClassNotFoundException e) {
            System.out.println(e);
        }
        if (PaymentRecordMap == null)
            PaymentRecordMap = new HashMap<String, PaymentRecord>();
    }

//
//    public void addRecord(int movieGoerID, int movieID, int cinemaID, int cineplexID, int amountOfTickets, ArrayList<String> seatID, float totalCost, String dateStarttime, Boolean canceled) {
//        // need to check valid input
//
//        String TID = "Temporary TID"; // TID needs to be updated based on added time stamp, find a way to do this.
//        //XXXYYYYMMDDhhmm (Y : year, M : month, D : day, h : hour, m : minutes, XXX : cinema code in letters)
//        canceled = false;
//        PaymentRecord temp = new PaymentRecord(TID, movieGoerID, movieID, cinemaID, cineplexID, amountOfTickets, seatID, totalCost, dateStarttime, canceled);
//        PaymentRecordMap.put(TID,temp);
//    }



    public void addRecord(String TID, PaymentRecord r) {
        this.PaymentRecordMap.put(TID, r);
    }

    public static void main(String args[]) {
        PaymentRecordDB paymentRecordDB = new PaymentRecordDB(MoblimaApp.paymentRecordDBFile);
//        paymentRecordDB.addRecord();
//        paymentRecordDB.saveToFile();
        paymentRecordDB.updateRecord();
    }



    public void updateRecord(String TID, Boolean canceled){
        // set one Record status to be canceled
        // need to check valid input
        PaymentRecordMap.get(TID).setCanceled(canceled);
        System.out.println("Update successfully!");


    }

    public void updateRecord(){
        Scanner sc = new Scanner(System.in);
        System.out.println("Please input the TID of the PaymentRecord you want to update: ");
        String TID = sc.next();
        System.out.println("Please input the canceled status of the PaymentRecord you want to update (T/F): ");
        Character choice = sc.next().charAt(0);
        Boolean canceled = false;
        if (choice == 'T') {
            canceled = true;
        }
        else if (choice == 'F'){
            canceled = false;
        }
        else
            System.out.println("Invalid input");

        updateRecord(TID,canceled);
    }

    public void deleteRecord(String TID) {
        // need to check valid input
        if (PaymentRecordMap.containsKey(TID) == false) {
            System.out.println("You have entered an invalid PaymentRecord ID");
        } else {
            PaymentRecordMap.remove(TID);
        }
    }


    public void deleteRecord(){
        Scanner sc = new Scanner(System.in);
        System.out.println("Please input the TID of the PaymentRecord you want to delete: ");
        String TID = sc.next();
        deleteRecord(TID);
    }
//
    public ArrayList <PaymentRecord> findRecordByMovieGoerID(int movieGoerID){
        ArrayList <PaymentRecord> temp = new ArrayList<PaymentRecord>();
        for (Map.Entry<String, PaymentRecord> entry : PaymentRecordMap.entrySet()){
            if (entry.getValue().getMovieGoerID() == movieGoerID) {
                //System.out.println("Your PaymentRecord is found!");
                temp.add(entry.getValue());
            }
        }
        if (temp.size() != 0) {
            System.out.println("Your PaymentRecords is found!");
            return temp;
        }
        else {
            System.out.println("The PaymentRecord you are looking for does not exist.");
            return null;
        }
    }


    public void saveToFile() {
        try{
            FileOutputStream fos = new FileOutputStream(this.filename);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            System.out.print("saving data to " + filename + "...");
            oos.writeObject(PaymentRecordMap);
            oos.close();
        } catch (IOException e) {
            System.out.println("File input error");
        }
    }

    public void addRecord() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Please input the movieGoerID: ");
        int movieGoerID = sc.nextInt();
        System.out.println("Please input the movieID: ");
        int movieID = sc.nextInt();
        System.out.println("Please input the cinemaID: ");
        int cinemaID = sc.nextInt();
        System.out.println("Please input the cineplexID: ");
        int cineplexID = sc.nextInt();
        System.out.println("Please input the amount of tickets bought: ");
        int amountOfTickets = sc.nextInt();
        ArrayList<String> seatID = new ArrayList<>();
        System.out.println("Please enter all the seatID, such as 'a1 a2': ");
        String dummy = sc.nextLine();
        String input = sc.nextLine();
        StringTokenizer str_arr = new StringTokenizer(input);
        while (str_arr.hasMoreTokens()) {
            seatID.add(str_arr.nextToken());
        }
        System.out.println("Please input the total cost: ");
        int totalCost = sc.nextInt();
        System.out.println("Please input the movieStartTime: YYYY-MM-DD-HH-MM");
        String dateStarttime = sc.next();
        Boolean canceled = false; // when adding a new Record, the default value of canceled is false;
        addRecord(movieGoerID, movieID, cinemaID, cineplexID, amountOfTickets, seatID, totalCost, dateStarttime, canceled);
    }

    public PaymentRecord getRecordByTID(String TID) {
        return PaymentRecordMap.get(TID);
    }

}
