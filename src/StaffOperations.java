import javax.xml.crypto.Data;
import java.io.Serializable;
import java.util.Scanner;

public class StaffOperations {

    private CineplexDB cineplexDB;
    private MovieInfoDB movieInfoDB; //!! if use Database,
    private DateDB date;
    private PriceTable priceTable;
    private StaffRecordDB staffRecordDB;

    public StaffOperations(String cineplexDBFile, String movieInfoDBFile, String dateFile, String priceTableFile, String staffRecordDBFile) {

        this.cineplexDB = new CineplexDB(cineplexDBFile);
        this.movieInfoDB = new MovieInfoDB(movieInfoDBFile);
        this.date = new DateDB(dateFile);
        this.priceTable = new PriceTable(priceTableFile);
        this.staffRecordDB = new StaffRecordDB(staffRecordDBFile);
        if (!staffRecordDB.login())
            System.exit(0);
        startOperations();
    }

    private void displayMainMenu() {
        System.out.println("Please enter your choice: ");
        System.out.println("1. Configure the ticket prices system setting.");
        System.out.println("2. Configure the holiday dates system setting.");
        System.out.println("3. List all movies");
        System.out.println("4. List all schedules of a particular movie");
        System.out.println("5. Enter information about forthcoming movies");
        System.out.println("6. Update information/Change status about Movies");
        System.out.println("7. Add Movie Schedules for Currently Showing Movies");
        System.out.println("8. Update Movie Schedules for Currently Showing Movies");
        System.out.println("9. Delete Movie Schedules for Currently Showing Movies");
        System.out.println("10. Delete movies.");
        System.out.println("11. List the current top 5 ranking movies by ticket sales.");
        System.out.println("12. List the current top 5 ranking movies by rating.");
        System.out.println("13. Update your staff password.");
        System.out.println("14. Exit");
    }

    private void startOperations() {

        displayMainMenu();
        Scanner s = new Scanner(System.in);
        int choice = s.nextInt();
        switch (choice) {
            case 1:
                configureTicketPrice();
                break;
            case 2:
                configureHolidaySetting();
                break;
            case 3:
                listAllMovies();
                break;
            case 4:
                listAllSchedulesOfMovie();
                break;
            case 5:
                enterNewMovieInfo();
                break;
            case 6:
                updateMovieInfo();
                break;
            case 7:
                addMovieSchedule();
                break;
            case 8:
                updateMovieSchedule();
                break;
            case 9:
                deleteMovieSchedule();
                break;
            case 10:
                deleteMovies();
                break;
            case 11:
                listCurrentTopBySales();
                break;
            case 12:
                listCurrentTopByRating();
                break;
            case 13:
                staffRecordDB.updateRecord();
                break;
            case 14:
                System.out.println("Have a nice day! Good bye!");
                saveToFile();
                System.exit(0);
        }
    }

    private void saveToFile() {
        cineplexDB.saveToFile();
        movieInfoDB.saveToFile();
        date.saveToFile();
        priceTable.saveToFile();
        staffRecordDB.saveToFile();
    }

    private void configureTicketPrice() {
        this.priceTable.updatePriceTable();
        startOperations();
    }

    private void configureHolidaySetting() {
        this.date.displayContent();
        this.date.addHoliday();
        startOperations();

    }

    private void listAllMovies() {
        movieInfoDB.listAllMovies();
        startOperations();
    }

    private void listAllSchedulesOfMovie() {
        movieInfoDB.listAllSchedulesOfMovie(cineplexDB);
        startOperations();
    }

    private void enterNewMovieInfo() {
        movieInfoDB.addRecord(cineplexDB);
        startOperations();
    }

    private void updateMovieInfo() {
        movieInfoDB.updateRecord(cineplexDB);
        startOperations();
    }

    private void addMovieSchedule() {
        System.out.println("Add new schedule for which movie? Enter movie ID");
        Scanner s = new Scanner(System.in);
        int movieID = s.nextInt();
        System.out.println("Add new schedule for which cineplex? Enter cineplex ID");
        int cineplexID = s.nextInt();
        System.out.println("Add new schedule for which cinema? Enter cinema ID");
        int cinemaID = s.nextInt();
        cineplexDB.getCineplexByID(cineplexID).getCinemaByCinemaID(cinemaID).addRecord(movieInfoDB.getMovieInfoByMovieID(movieID));
        saveToFile();
        startOperations();
    }

    private void updateMovieSchedule() {
        System.out.println("Update schedule for which movie? Enter movie ID");
        Scanner s = new Scanner(System.in);
        int movieID = s.nextInt();
        System.out.println("which cineplex? Enter cineplex ID");
        int cineplexID = s.nextInt();
        System.out.println("which cinema? Enter cinema ID");
        int cinemaID = s.nextInt();
        cineplexDB.getCineplexByID(cineplexID).getCinemaByCinemaID(cinemaID).updateRecord();
        startOperations();

    }

    private void deleteMovieSchedule() {
        System.out.println("delete schedule for which movie? Enter movie ID");
        Scanner s = new Scanner(System.in);
        int movieID = s.nextInt();
        System.out.println("which cineplex? Enter cineplex ID");
        int cineplexID = s.nextInt();
        System.out.println("which cinema? Enter cinema ID");
        int cinemaID = s.nextInt();
        cineplexDB.getCineplexByID(cineplexID).getCinemaByCinemaID(cinemaID).deleteRecord();
        startOperations();

    }

    private void deleteMovies() {
        movieInfoDB.deleteRecord();
        startOperations();
    }

    private void listCurrentTopBySales() {
        movieInfoDB.listTopMovies("sales");
        startOperations();
    }

    private void listCurrentTopByRating() {
        movieInfoDB.listTopMovies("rating");
        startOperations();
    }

    public static void main(String args[]) {
    }


}
