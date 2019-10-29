import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.io.*;
import java.util.Scanner;

public class PaymentRecordDB extends Database{
    private Map<String, PaymentRecord> PaymentRecordMap = new HashMap<String, PaymentRecord>();
    private String filename;

    /**
     Initiate the class using a txt file to populate userPasswordRecord
     */
    public PaymentRecordDB(String fileName)
    {
        this.filename = fileName;
        try {
            FileInputStream fis = new FileInputStream(filename);
            ObjectInputStream ois = new ObjectInputStream(fis);
            System.out.print("reading data from " + filename + "...");
            this.PaymentRecordMap = (Map<String, PaymentRecord>) ois.readObject();
            ois.close();
        } catch (IOException e) {
            System.out.println("File input error");
        } catch (ClassNotFoundException e) {
            System.out.println(e);
        }
    }

    public void addRecord(int movieGoerID, int movieID, int cinemaID, int cineplexID, int amountOfTickets, ArrayList<String> seatID, float totalCost, Boolean canceled){
        // need to check valid input

        String TID = "Temporary TID"; // TID needs to be updated
        //XXXYYYYMMDDhhmm (Y : year, M : month, D : day, h : hour, m : minutes, XXX : cinema code in letters)
        canceled = false;
        PaymentRecord temp = new PaymentRecord(TID,movieGoerID,movieID,cinemaID,cinemaID,amountOfTickets,seatID,totalCost,canceled);
        PaymentRecordMap.put(TID,temp);

    }


    public void updateRecord(String TID, Boolean canceled){
        // set one Record status to be canceled
        // need to check valid input

        PaymentRecordMap.get(TID).setCanceled(canceled);
        // get() returns a reference to the object

    }

    public void deleteRecord(String TID){
        // need to check valid input
        PaymentRecordMap.remove(TID);
    }
//
//    public PaymentRecord findRecordByMovieGoerID(int movieGoerID){
//
//    }








}
