import javax.xml.crypto.Data;
import java.util.Scanner;

public class StaffOperations {

    private CineplexDB cineplexDB;
    private MovieInfoDB movieInfoDB; //!! if use Database,
    private Date date;
    private PriceTable priceTable;
    private StaffRecordDB staffRecordDB;

    public StaffOperations() {

        this.cineplexDB = new CineplexDB("");
        this.movieInfoDB = new MovieInfoDB("");
        this.date = new Date("");
        this.priceTable = new PriceTable("");
        this.staffRecordDB = new StaffRecordDB("");
        startOperations();

    }

    private void displayMainMenu() {
        System.out.println("Please enter your choice: ");
        System.out.println("1. Configure the ticket prices system setting.");
        System.out.println("2. Configure the holiday dates system setting.");
        System.out.println("3. Enter information about forthcoming movies");
        System.out.println("4. Update information/Change status about Movies");
        System.out.println("5. Add Movie Schedules for Currently Showing Movies");
        System.out.println("6. Update Movie Schedules for Currently Showing Movies");
        System.out.println("7. Delete Movie Schedules for Currently Showing Movies");
        System.out.println("8. Delete movies.");
        System.out.println("9. List the current top 5 ranking movies by ticket sales.");
        System.out.println("10. List the current top 5 ranking movies by rating.");
        System.out.println("11. Exit");
    }

    private void startOperations() {
        if (!staffRecordDB.login())
            System.exit(0);
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
                enterNewMovieInfo();
                break;
            case 4:
                updateMovieInfo();
                break;
            case 5:
                addMovieSchedule();
                break;
            case 6:
                updateMovieSchedule();
                break;
            case 7:
                deleteMovieSchedule();
                break;
            case 8:
                deleteMovies();
                break;
            case 9:
                listCurrentTopBySales();
                break;
            case 10:
                listCurrentTopByRating();
                break;
            case 11:
                System.out.println("Have a nice day! Good bye!");
                System.exit(0);
        }
    }

    private void configureTicketPrice() {
        this.priceTable.updatePriceTable();
        startOperations();
    }

    private void configureHolidaySetting() {
        this.date.addHoliday();
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
        cineplexDB.getCineplexByID(cineplexID).getCinemaByCinemaID(cinemaID).addRecord();
        startOperations();
    }

    private void updateMovieSchedule() {
        System.out.println("Add new schedule for which movie? Enter movie ID");
        Scanner s = new Scanner(System.in);
        int movieID = s.nextInt();
        System.out.println("Add new schedule for which cineplex? Enter cineplex ID");
        int cineplexID = s.nextInt();
        System.out.println("Add new schedule for which cinema? Enter cinema ID");
        int cinemaID = s.nextInt();
        cineplexDB.getCineplexByID(cineplexID).getCinemaByCinemaID(cinemaID).updateRecord();
        startOperations();

    }

    private void deleteMovieSchedule() {
        System.out.println("Add new schedule for which movie? Enter movie ID");
        Scanner s = new Scanner(System.in);
        int movieID = s.nextInt();
        System.out.println("Add new schedule for which cineplex? Enter cineplex ID");
        int cineplexID = s.nextInt();
        System.out.println("Add new schedule for which cinema? Enter cinema ID");
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


}
