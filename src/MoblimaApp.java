import java.io.Serializable;
import java.util.Scanner;

public class MoblimaApp {
    private boolean isStaff;
    public static String cineplexDBFile = "cineplexDB.ser";
    public static String movieInfoDBFile = "movieInfoDB.ser";
    public static String dateDBFile = "dateDB.ser";
    public static String priceTableFile = "priceTable.ser";
    public static String staffRecordDBFile = "staffRecordDB.ser";
    public static String paymentRecordDBFile = "paymentRecordDB.ser";
    public static String movieGoerDBFile = "movieGoer.ser";


    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Please enter your choice:");
        System.out.println("1.Staff");
        System.out.println("2.MovieGoer");
        int choice = sc.nextInt();
        boolean isStaff;
        if (choice == 1) isStaff = true;
        else isStaff = false;

        if (isStaff) {
            StaffOperations staffOperations = new StaffOperations(cineplexDBFile, movieInfoDBFile, dateDBFile, priceTableFile, staffRecordDBFile);
        } else {
            MovieGoerOperations movieGoerOperations = new MovieGoerOperations(movieInfoDBFile, cineplexDBFile, paymentRecordDBFile, priceTableFile, movieGoerDBFile, dateDBFile);
        }

    }
}



