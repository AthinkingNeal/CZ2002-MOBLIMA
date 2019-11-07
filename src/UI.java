import java.util.Scanner;
/*
Methods to be implemented:
movieInfoDB.configSystem(); 
movieInfoDB.addNewMovie(); 
movieInfoDB.UpdateMovie();
movieInfoDB.removeMovie();
movieInfoDB.listTopMovies()

*/


public class UI {
    private boolean isStaff;
    private Database cineplexDB;
    private Database moviesInfoDB;
    private Database movieGoerDB;
    // p rivate ArrayList<Cineplex> cineplexes; //check whether can change into class
    // private ArrayList<Movie> movies; //check whether can change into class
    // private ArrayList<MovieGoer> movieGoers; //check whether can change into class
    private Database paymentRecordDB;
    private Database staffRecordDB;
    private Database movieScheduleDB;
    private Date date;
    private projectMe.PriceTableDB priceTable;


    public UI() {
        this.cineplexDB = new CineplexDB(); // incomplete
        this.moviesInfoDB = new MoviesInfoDB(); // incomplete
        this.movieGoerDB = new MovieGoerDB();
        this.paymentRecordDB = new PaymentRecordDB();
        this.staffRecordDB = new StaffRecordDB();
        this.movieScheduleDB = new MovieScheduleDB();
        this.date = new Date();
        this.priceTable = new Pricetable();
    }

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
            System.out.println("Please enter your choice:");
            System.out.println("1. Register a new staff");
            System.out.println("2. Current staff log in");
            choice = sc.nextInt();
            //check valid input
            while (choice != 1 && choice != 2) {
                System.out.println("Please enter a valid input");
                choice = sc.nextInt();
            }

            int trialNumber = 0;
            while (choice == 1 && trialNumber < 3) {
                System.out.println("Please enter your new staffID:");
                String staffID = sc.next();
                System.out.println("Please enter your password:");
                String password = sc.next();
                System.out.println("Please re-enter and confirm your password:");
                String reenterPassword = sc.next();
                if (password.equals(reenterPassword)) {
                    staffRecordDB.addRecord(staffID, password); // implemented
                    break;
                } else {
                    System.out.println("Passwords don't match! Try again!");
                    if (trialNumber++ == 3) {
                        System.out.println("Too many trials. Program terminated. Please try again!");
                        System.exit(0);
                    }
                }
            }

            while (choice == 2 && trialNumber < 3) {
                // System.out.println("Please enter your staffID:");
                // String staffID = sc.next()
                // System.out.println("Please enter your passward:");
                // String password = sc.next();
                if (staffRecordDB.login()) // implemented
                    break;
                else {
                    if (trialNumber++ == 3) {
                        System.out.println("Too many trials. Program terminated. Please try again!");
                        System.exit(0);
                    }
                }
            }

            // below is the Staff Operation

            System.out.println("Please enter your choices: ");
            System.out.println("1. Configure the system settings: (ticket prices, holidays,etc)");
            System.out.println("2. Enter information about forthcoming movies");
            System.out.println("3. Update details of the movies or remove one movie");
            System.out.println("4. List the current top 5 ranking movies (by ticket sales or rating)");
            System.out.println("5. Exit");

            choice = sc.nextInt();
            while (choice != 5) {
                switch (choice) {
                    case 1:
                        // method used:
                        // PriceTable.configureTicketPrice()
                        // Date.configure()
                        System.out.println("Please enter your choices: ");
                        System.out.println("1. Configure ticket price");
                        System.out.println("2. Configure holiday");
                        choice = sc.nextInt();
                        if (choice == 1) {
                            continue;
                            movieInfoDB.configSystem(); // to be implemented
                        }
                        break;
                    case 2:
                        movieInfoDB.addNewMovie(); // to be implemented
                        // enter the forthcoming movies, its type
                        /*
					Cinema staff can enter the forthcoming movies, its type (Blockbuster/3D, etc.), movie rating (e.g. PG),
 					show times, the cinema, showing status (Coming Soon, Preview, Now Showing, End Of Showing), etc. 
 						*/

                        break;
                    case 3:// Update details of the moives or remove one movie
                        System.out.println("Please enter your choices: ");
                        System.out.println("1. Update movie details");
                        System.out.println("2. remove one movie");
                        choice = sc.nextInt();
                        if (choice == 1){
                        continue;
                        movieInfoDB.UpdateMovie(); // to be implemented
                   	 	}
                   	 	else{
                   	 		movieInfoDB.removeMovie(); // to be implemented
                   	 	}
                    	break;
                    case 4:
                    	movieInfoDB.listTopMovies() // to be implemented
                    	// have two choices, by sales or by rating
                        break;
                }
                System.out.println("Please enter your choice:");
                choice = sc.nextInt();
            }

            if (choice == 5)
                System.exit(0);
        }


        //////////////////////////////////////////////////////////////////////////////////////////
        else if (!isStaff) {


            while (choice != 1 && choice != 2) {
                System.out.println("Please enter a valid input");
                choice = sc.nextInt();
            }
            if (choice == 1) {

            } else {
                // if end here

                ////////////////////////////////user operation////////////////////////////////////
                // start choice 2 : current user login

                do {
                    // step 1: user login


                    System.out.println("Please Enter your choice: ");
                    System.out.println("1. Display current showing movies");
                    System.out.println("2. Advanced sales");
                    System.out.println("3. Go back");

                    do {

                        switch (choice) {
                            case 1:
                                System.out.println("current showing movies:");
                                ArrayList<MovieInfo> selectedMovies = movieInfoDB.getByStatus("Current showing");
                                for (int i = 0; i < selectedMovies.size(); i++)
                                    System.out.println("Movie ID: " + selectedMovies.get(i).getMovieID() + "Movie Name: " + selectedMovies.get(i).getMovieName());
                                System.out.println("Please Enter the selected MovieId");
                                int movieId = sc.nextInt();


                                break;

                            case 2:
                                System.out.println("current showing movies:");
                                selectedMovies = movieInfoDB.getByStatus("Current showing");
                                for (int i = 0; i < selectedMovies.size(); i++)
                                    System.out.println("Movie ID: " + selectedMovies.get(i).getMovieID() + "Movie Name: " + selectedMovies.get(i).getMovieName());
                                System.out.println("Please Enter the selected MovieId");
                                movieId = sc.nextInt();


                                break;
                            case 3:


                        }
                    } while (choice < 4)

                } choice = sc.nextInt();


            } while () ;

        }
    }

}
        }
                }

