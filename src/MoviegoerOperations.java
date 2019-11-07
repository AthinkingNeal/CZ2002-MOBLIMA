import java.util.HashMap;
import java.util.Scanner;

public class MovieGoerOperations {
    private MovieInfoDB movieInfoDB;  // You can not declare this DB as Database because of Static binding
    private CineplexDB cinePlexDB;    // If that is the case, you can only use the methods declared in Database.
    private PaymentRecordDB PaymentRecordDB;
    private PriceTable priceTable;
    private MovieGoerDB movieGoerDB;
    private MovieGoer movieGoer;

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
            ;
            case 3:
                bookTickets();
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
        if (movieInfo == null):
        System.out.println("This movie does not exist yet ");
        else if (movieInfo.status == "Currently Showing" || movieInfo.status == "Preview") {
            System.out.println("Do you want to view more details about this movie? Enter Y to view more details; Enter N to return to main menu.")
            char choice = s.nextLine().charAt(0);
            if (choice == 'Y' || choice == 'y')
                viewMovieDetails(movieInfo.getMovieID);
            else
                displayMainMenu();
        }
    }

    public void listMovies() { // display all the movie's name, user can query further details from here
        movieInfoDB.listAllMovies();
        Scanner s = new Scanner(System.in);
        System.out.println("Do you want to view more details? Enter Y to view more details; Enter N to return to main menu.")
        char choice = s.nextLine().charAt(0);
        if (choice == 'Y' || choice == 'y') {
            System.out.println("Which movie detail do you want to view? Enter movie ID.")
            Scanner s = new Scanner(System.in);
            int movieID = s.nextInt();
            viewMovieDetails(movieID);
        } else
            displayMainMenu();
    }


    public void viewMovieDetails(int movieID) { // inside searchMovies and listMovies

        movieInfoDB.getMovieInfoByMovieID(movieID).displayMovieInfo();
        System.out.println("Do you want to check the seat availability for this movie? Enter Y/N")
        char choice = s.nextLine().charAt(0);
        if (choice == 'Y' || choice == 'y')
            checkSeatAvailablity(movieID);
        else
            displayMainMenu();
    }

    public void checkSeatAvailability() {
        listMovies();
        Scanner sc = new Scanner(System.in);
        System.out.println("Please enter the movieID you want to book: ")
        int movieID = sc.nextInt();
        checkSeatAvailability(movieID);
    }

    public void checkSeatAvailability(int movieID) {
        MovieInfo m = movieInfoDB.getMovieInfoByMovieID(movieID);
        m.displayCineplexes();
        System.out.println("Please select the cineplexID: ");
        int cineplexID = sc.nextInt();

        ArrayList<Cinema> c = cineplexDB.getCinemas();
        for (int i = 0; i < c.size(); i++) {
            c.get(i).showAvailableDates(movieID);
        }

        System.out.println("Please select the date: ");
        String date = sc.nextLine();
        for (int i = 0; i < c.size(); i++) {
            c.get(i).getAvailableTime(movieID, date);
        }


        System.out.println("Please enter all the seatID, such as 'a1 a2': ");
        String input = sc.nextLine();
        StringTokenizer str_arr = new StringTokenizer(input);
        while (str_arr.hasMoreTokens()) {
            seatID.add(str_arr.nextToken());
        }

    }


    public void bookTickets(int movieGoerID, int movieID, int cinemaID, int cineplexID, int amountOfTickets, ArrayList<String> seatID, float totalCost) {
        // need to check valid input
        String TID = "Temporary TID"; // TID needs to be updated based on added time stamp, find a way to do this.
        //XXXYYYYMMDDhhmm (Y : year, M : month, D : day, h : hour, m : minutes, XXX : cinema code in letters)
        Boolean canceled = false;
        PaymentRecord temp = new PaymentRecord(TID, movieGoerID, movieID, cinemaID, cineplexID, amountOfTickets, seatID, totalCost, canceled);
        PaymentRecordMap.put(TID, temp);
    }

    public void viewBookingHistory() {
        ArrayList<PaymentRecord> paymentRecordArrayList = new ArrayList<PaymentRecord>;
        paymentRecordArrayList = paymentRecordDB.findRecordByMovieGoerID(movieGoerID);
        int len = paymentRecordArrayList.size();
        if (len == 0) {
            System.out.println("The PaymentRecord you are looking for does not exist.");
            return;
        }
        for (int i = 0; i < len; i++) {
            paymenRecordArrayList.get(i).printRecord();
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
