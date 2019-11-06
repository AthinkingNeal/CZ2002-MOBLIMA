import javax.xml.crypto.Data;
import java.util.Scanner;

public class StaffOperations {

    private Database cineplexDB;
    private Database movieInfoDB;
    private Date date;
    private PriceTable priceTable;

    public StaffOperations() {

        this.cineplexDB = new CineplexDB(//TODO read from file);
                this.movieInfoDB = new MovieInfoDB(//TODO read from file);
                        this.date = new Date();
        this.priceTable = new PriceTable();

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
                deleteMovies();
                break;
            case 8:
                addMovieSchedule();
                break;
            case 9:
                updateMovieSchedule();
                break;
            case 10:
                updateMovieSchedule();
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
        movieInfoDB.addRecord();
        startOperations();
    }

    private void updateMovieInfo() {
        movieInfoDB.updateRecord();
        startOperations();
    }

    private void addMovieSchedule() {
        System.out.println("Which movie? Enter movie ID");
        Scanner s = new Scanner(System.in);
        int movieID = s.nextInt();
        //TODO movieInfoDB.getMovieInfoByMovieID(movieID)

    }

    private void updateMovieSchedule() {

    }

    private void deleteMovieSchedule() {

    }

    private void deleteMovies() {

    }

    private void listCurrentTopBySales() {
    }

    private void listCurrentTopByRating() {

    }


}
