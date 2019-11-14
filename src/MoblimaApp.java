import java.io.Serializable;
import java.util.Scanner;

/**
 * This class is the main application class
 */
public class MoblimaApp {
//    /**
//     * The constant cineplexDBFile.
//     */
//    public static String cineplexDBFile = "bin/cineplexDB.ser";
//    /**
//     * The constant movieInfoDBFile.
//     */
//    public static String movieInfoDBFile = "bin/movieInfoDB.ser";
//    /**
//     * The constant dateDBFile.
//     */
//    public static String dateDBFile = "bin/dateDB.ser";
//    /**
//     * The constant priceTableFile.
//     */
//    public static String priceTableFile = "bin/priceTable.ser";
//    /**
//     * The constant staffRecordDBFile.
//     */
//    public static String staffRecordDBFile = "bin/staffRecordDB.ser";
//    /**
//     * The constant paymentRecordDBFile.
//     */
//    public static String paymentRecordDBFile = "bin/paymentRecordDB.ser";
//    /**
//     * The constant movieGoerDBFile.
//     */
//    public static String movieGoerDBFile = "bin/movieGoerDB.ser";


    /**
     * The constant cineplexDBFile.
     */
    public static String cineplexDBFile = "src/bin/cineplexDB.ser";
    /**
     * The constant movieInfoDBFile.
     */
    public static String movieInfoDBFile = "src/bin/movieInfoDB.ser";
    /**
     * The constant dateDBFile.
     */
    public static String dateDBFile = "src/bin/dateDB.ser";
    /**
     * The constant priceTableFile.
     */
    public static String priceTableFile = "src/bin/priceTable.ser";
    /**
     * The constant staffRecordDBFile.
     */
    public static String staffRecordDBFile = "src/bin/staffRecordDB.ser";
    /**
     * The constant paymentRecordDBFile.
     */
    public static String paymentRecordDBFile = "src/bin/paymentRecordDB.ser";
    /**
     * The constant movieGoerDBFile.
     */
    public static String movieGoerDBFile = "src/bin/movieGoerDB.ser";


    /**
     * Main.
     *
     * @param args the args
     */
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



