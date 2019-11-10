import java.awt.desktop.SystemSleepEvent;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class MovieGoerOperations {
    private MovieInfoDB movieInfoDB;  // You can not declare this DB as Database because of Static binding
    private CineplexDB cineplexDB;    // If that is the case, you can only use the methods declared in Database.
    private PaymentRecordDB paymentRecordDB;
    private PriceTable priceTable;
    private MovieGoerDB movieGoerDB;
    private MovieGoer movieGoer;
    private Date todayDate;

    public MovieGoerOperations() {
        //TODO database initialization;

    }

    public int getID() {
        System.out.println("Please enter your MovieGoer ID or a new ID of you own: ");
        Scanner sc = new Scanner(System.in);
        int movieGoerID = sc.nextInt();
        return movieGoerID;
    }

    private void displayMainMenu() {
        System.out.println("Please enter your choice: ");
        System.out.println("1. Search movies."); // Check sear availability, selection of seats and Booking tickets are inside this option
        System.out.println("2. List movies"); // when listing movies, we list all movies at one time but organize them in different groups by their type
        System.out.println("3. Check Seat Availability and Book tickets"); // as long as you know the movie name you can book seats, user check the seat availability in this.
        //TODO cancel booking?
        System.out.println("4. List the Top 5 ranking movies by ticket sales");
        System.out.println("5. List the Top 5 ranking movies by overall reviewers' ratings");
    }


    public void startOperations() {

        Scanner sc = new Scanner(System.in);
        int movieGoerID = getID();
        // we assume this ID already exists
        movieGoer = movieGoerDB.findRecordByMovieGoerID(movieGoerID);
        displayMainMenu();
        int choice;
        choice = sc.nextInt();
        switch (choice) {
            case 1:
                searchMovies();
                break;
            case 2:
                listMovies();
                break;
            case 3:
                checkSeatAvailability();
                break;
            case 4:
                listCurrentTopBySales();
                break;
            case 5:
                listCurrentTopByRating();
                break;
        }

    }

    public void searchMovies() { // display a single movie info
        Scanner sc = new Scanner(System.in);
        System.out.println("Please enter the Movie Name you want to Search: "); // example: Joker, Iron Man
        String movieName = sc.nextLine();
        MovieInfo movieInfo = movieInfoDB.getMovieInfoByName(movieName);
        // return a MovieInfo object or a null pointer
        if (movieInfo == null)
            System.out.println("This movie does not exist yet ");
        else if (movieInfo.getShowingStatus() == "Currently Showing" || movieInfo.getShowingStatus() == "Preview") {
            System.out.println("Do you want to view more details about this movie? Enter Y to view more details; Enter N to return to main menu.");
            char choice = sc.nextLine().charAt(0);
            if (choice == 'Y' || choice == 'y')
                viewMovieDetails(movieInfo.getMovieId());
            else
                displayMainMenu();
        }
    }

    public void listMovies() { // display all the movie's name, user can query further details from here
        movieInfoDB.listAllMovies();
        Scanner s = new Scanner(System.in);
        System.out.println("Do you want to view more details? Enter Y to view more details; Enter N to return to main menu.");
        char choice = s.nextLine().charAt(0);
        if (choice == 'Y' || choice == 'y') {
            System.out.println("Which movie detail do you want to view? Enter movie ID.");
            int movieID = s.nextInt();
            viewMovieDetails(movieID);
        } else
            displayMainMenu();
    }


    public void viewMovieDetails(int movieID) { // inside searchMovies and listMovies
        Scanner sc = new Scanner(System.in);
        movieInfoDB.getMovieInfoByMovieID(movieID).displayMovieInfo();
        System.out.println("Do you want to check the seat availability for this movie? Enter Y/N");
        char choice = sc.nextLine().charAt(0);
        if (choice == 'Y' || choice == 'y')
            checkSeatAvailability(movieID);
        else
            displayMainMenu();
    }

    public void checkSeatAvailability() {
        listMovies();
        Scanner sc = new Scanner(System.in);
        System.out.println("Please enter the movieID you want to book: ");
        int movieID = sc.nextInt();
        checkSeatAvailability(movieID);
    }

    public void checkSeatAvailability(int movieID) {
        MovieInfo m = movieInfoDB.getMovieInfoByMovieID(movieID);
        m.displayCineplexes();
        System.out.println("Please select the cineplexID: ");
        Scanner sc = new Scanner(System.in);
        int cineplexID = sc.nextInt();

        ArrayList<Cinema> c = cineplexDB.getCineplexByID(cineplexID).getCinemas();
        for (int i = 0; i < c.size(); i++) {
            c.get(i).showAvailableDates(movieID);
        }

        System.out.println("Please select the date in this format yyyy-mm-dd:  ");
        String date = sc.nextLine();
        HashMap<String, MovieSchedule> movieSchedules = new HashMap<>(); // movieSchedules contain all schedules of the movie in this cineplex on this date
        for (int i = 0; i < c.size(); i++) {
            movieSchedules.putAll(c.get(i).getAndDisplayAvailableTime(movieID, date));
        }

        System.out.println("Please select the start time of the movie session in this format hh-mm: ");
        String dateStarttime = date + '-' + sc.nextLine();
        MovieSchedule selectedSchedule = movieSchedules.get(dateStarttime);

        // display the seat layout
        System.out.println("Please check the seat availability below:");
        selectedSchedule.getLayout().displayLayout(); //TODO: add seat ID in display!
        float price = priceTable.getPrice(selectedSchedule.getIs3D(), selectedSchedule.getIsBlockbuster(), selectedSchedule.getCinemaClass(), movieGoer.getAge(), todayDate.IsHoliday(""), todayDate.IsWeekend("")); //TODO isWeekend to be implemented
        System.out.println("The price of this movie session is:" + price + " per seat");
        System.out.println("Do you want to proceed to book a seat? Enter Y/N");
        char choice = sc.nextLine().charAt(0);
        if (choice == 'Y' || choice == 'y')
            bookTickets(selectedSchedule, price);
        else
            displayMainMenu();
    }


    private void bookTickets(MovieSchedule ms, float price) {
        System.out.println("How many tickets you would like to book?");
        Scanner s = new Scanner(System.in);
        int numTickets = s.nextInt();
        ArrayList<String> seatIDs = new ArrayList<>();
        for (int i = 0; i < numTickets; i++) {
            System.out.println("This is the " + (i + 1) + "th ticket you are booking");
            System.out.println("Please enter the seat ID you would like to book: ");
            String seatID = s.nextLine();
            seatIDs.add(seatID);
            ms.bookSeat(seatID);
            System.out.println("This seat is successfully booked!");
        }

        addToPaymentRecordDB(ms, seatIDs, price); // add to record
        System.out.println("Booking complete! You will be returned to main menu!");
        displayMainMenu();


    }

    private void printReceipt(MovieSchedule ms, ArrayList<String> seatIDs, float price) {
        System.out.println("Here is your receipt!");
        String seatIDString = "";
        for (int i = 0; i < seatIDs.size(); i++)
            seatIDString += seatIDs.get(i) + ' ';

        String receipt = "Moviegoer ID: " + movieGoer.movieGoerID + "\n"
                + "Movie Booked: " + movieInfoDB.getMovieInfoByMovieID(ms.getMovieID()).getTitle() + "\n"
                + "Cineplex ID: " + ms.getCineplexID() + "\n"
                + "Cinema ID: " + ms.getCinemaID() + "\n"
                + "Movie Date and Start time: " + ms.getDateStartTime() + "\n"
                + "Seats booked: " + seatIDString + "\n"
                + "Date of payment: " + todayDate.getDate() + "\n"
                + "Total price: " + price * seatIDs.size() + "\n";
        System.out.println(receipt);
    }

    /**
     * @param ms
     * @param seatIDs
     * @param price   price of a single ticket
     */
    private void addToPaymentRecordDB(MovieSchedule ms, ArrayList<String> seatIDs, float price) {
        // need to check valid input
        String dateStartTime = ms.getDateStartTime();

        String TID = ms.getCinemaID() + dateStartTime.substring(0, 4) + dateStartTime.substring(5, 7) + dateStartTime.substring(8, 10) + dateStartTime.substring(11, 13) + dateStartTime.substring(14, 16); // TID needs to be updated based on added time stamp, find a way to do this.
        //XXXYYYYMMDDhhmm (Y : year, M : month, D : day, h : hour, m : minutes, XXX : cinema code in letters)


        Boolean canceled = false; //TODO why need canceled? Answer: Becauase in case we want to allow the user to cancel his/her booking

        PaymentRecord temp = new PaymentRecord(TID, movieGoer.getMovieGoerID(), ms.getMovieID(), ms.getCinemaID(),
                ms.getCineplexID(), seatIDs.size(), seatIDs, price * seatIDs.size(), canceled);

        paymentRecordDB.addRecord(TID, temp);
        System.out.println("Your booking is successful! You can cancel your booking or check payment history in the main menu!");
        printReceipt(ms, seatIDs, price);
    }


    public void viewBookingHistory() {
        ArrayList<PaymentRecord> paymentRecordArrayList = new ArrayList<PaymentRecord>;
        paymentRecordArrayList = paymentRecordDB.findRecordByMovieGoerID(movieGoer.getMovieGoerID());
        int len = paymentRecordArrayList.size();
        if (len == 0) {
            System.out.println("The PaymentRecord you are looking for does not exist.");
            return;
        }
        for (int i = 0; i < len; i++) {
            paymentRecordArrayList.get(i).printRecord();
        }
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
