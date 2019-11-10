import java.util.Scanner;

public class MoblimaApp {
    private boolean isStaff;
    public static String cineplexDBFile = "";
    public static String movieInfoDBFile = "";
    public static String dateFile = "";
    public static String priceTableFile = "";
    public static String staffRecordDBFile = "";
    public static String paymentRecordDBFile = "";
    public static String movieGoerDBFile = "";


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
            StaffOperations staffOperations = new StaffOperations(cineplexDBFile, movieInfoDBFile, dateFile, priceTableFile, staffRecordDBFile);
        } else {
            MovieGoerOperations movieGoerOperations = new MovieGoerOperations(movieInfoDBFile, cineplexDBFile, paymentRecordDBFile, priceTableFile, movieGoerDBFile, dateFile);
        }

    }
}



