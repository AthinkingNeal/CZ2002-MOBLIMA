import java.io.*;
import java.util.*;

public class MovieSheduleDB extends Database {

    // hashmap: key: "dateStarttime", value: MovieSchedule
    private Map<String, MovieSchedule> cinemaSchedule = new HashMap<String, MovieSchedule>();
    private String filename;


    /**
     * Initialise with a file containing all the moviescheduleinfo
     *
     * @param filename file containing records in the order (movieID, movieName, date, startTime, endTime)
     */
    public MovieSheduleDB(String filename) {
        this.filename = filename;
        try {
            FileInputStream fis = new FileInputStream(filename);
            ObjectInputStream ois = new ObjectInputStream(fis);

            System.out.print("reading data from " + filename + "...");
            MovieSchedule movieShedule;
            while ((movieShedule = (MovieSchedule) ois.readObject()) != null) // read the Date object from file
            {
                cinemaSchedule.put(movieShedule.getDateStartTime(), movieShedule);
            }
            ois.close();
        } catch (IOException e) {
            System.out.println("File input error");
        } catch (ClassNotFoundException e) {
            System.out.println(e);
        }
    }


    /**
     * Allow staff to add a single record of movie schedule
     */
    public void addRecord() {
        Scanner s = new Scanner(System.in);
        String dateStartTime;
        int movieID;
        String movieName;
        boolean is3D;
        boolean isBlockbuster;
        double duration;
        System.out.println("Please enter the date in this format: yyyy-mm-dd.");
        dateStartTime = s.nextLine();
        System.out.println("Please enter the start time of the movie in this format: hh-mm.");
        dateStartTime += "-" + s.nextLine();

        System.out.println("Please input the movie ID");
        movieID = Integer.parseInt(s.nextLine());
        System.out.println("Please input the movie name");
        movieName = s.nextLine();

        System.out.println("Is this movie 3D? Y/N");
        String ans = s.nextLine();
        if (ans == "Y" || ans == "y")
            is3D = true;
        else
            is3D = false;

        System.out.println("Is this movie a blockbuster? Y/N");
        String ans1 = s.nextLine();
        if (ans1 == "Y" || ans1 == "y")
            isBlockbuster = true;
        else
            isBlockbuster = false;

        System.out.println("How long is the duration of this movie? Answer in this format: 2.5");
        duration = Double.parseDouble(s.nextLine());

        this.cinemaSchedule.put(dateStartTime, new MovieSchedule(dateStartTime, movieID, movieName, is3D, isBlockbuster, duration));
        System.out.println("Successfully added!");
    }

    /**
     * Allow staff to delete a single record of movie schedule
     */
    public void deleteRecord() {
        displayAllRecords();
        Scanner s = new Scanner(System.in);
        System.out.println("Please enter the date and time of the movie schedule you want to delete in this format: YYYY-mm-dd-hh-mm");
        String key = s.nextLine();
        this.cinemaSchedule.remove(key);
        System.out.println("Successfully deleted!");
    }

    /**
     * Allow staff to update a single record of movie schedule
     * Only allow staff to update
     */
    public void updateRecord() {
        Scanner s = new Scanner(System.in);
        System.out.println("Please enter the date and time of the movie schedule you want to update in this format: YYYY-mm-dd-hh-mm");
        String key = s.nextLine();
        MovieSchedule newRecord = cinemaSchedule.get(key);
        this.cinemaSchedule.remove(key); // delete original record
        System.out.println("Which attribute in the record would you like to change?\n"
                + "1. Change is3D\n" + "2. Change isBlockbluster\n" + "3. Change duration\n" + "4. Quit\n" + "Please key in your option");
        int choice = Integer.parseInt(s.nextLine());
        while (choice != 4) {
            switch (choice) {
                case 1:
                    System.out.println("The current is3D is:" + newRecord.getIs3D());
                    System.out.println("Please key in the new is3D");
                    newRecord.setIs3D(Boolean.parseBoolean(s.nextLine()));
                    System.out.println("Changed!");
                    break;
                case 2:
                    System.out.println("The current isBlockbuster is:" + newRecord.getIsBlockbuster());
                    System.out.println("Please key in the new isBlockbuster");
                    newRecord.setIsBlockbuster(Boolean.parseBoolean(s.nextLine()));
                    System.out.println("Changed!");
                    break;
                case 3:
                    System.out.println("The current duration is:" + newRecord.getDuration());
                    System.out.println("Please key in the new duration");
                    newRecord.setDuration(Double.parseDouble(s.nextLine()));
                    System.out.println("Changed!");
                    break;
                default:
                    break;
            }
            System.out.println("Please key in your option:");
            choice = s.nextInt();
        }
        this.cinemaSchedule.put(newRecord.getDateStartTime(), newRecord);
        System.out.println("Successfully updated!");
    }

    /**
     * Allow staff to update a single record of movie schedule
     */
    public void displayAllRecords() {
        for (String key : cinemaSchedule.keySet()) {
            cinemaSchedule.get(key).displayMovieRecord();
        }
    }

    /**
     * Once user has selected movie, location, display all available dates of this movie in this cinema
     */
    public void showAvailableDates(int movieID) {
        for (String key : cinemaSchedule.keySet()) {
            if (cinemaSchedule.get(key).getMovieID() == movieID)
                cinemaSchedule.get(key).displayMovieRecord();
        }
    }

    /**
     * Once user has selected movie, location and date, display all available times of this movie in this cinema
     */
    public void showAvailableTime(int movieID, String date) {
        for (String key : cinemaSchedule.keySet()) {
            if (cinemaSchedule.get(key).getMovieID() == movieID)
                if (cinemaSchedule.get(key).getDateStartTime().substring(0, 10).equals(date))
                    cinemaSchedule.get(key).displayMovieRecord();
        }
    }

    public void cancelBooking(String dateStartTime, String[] seatID) {
        this.cinemaSchedule.get(dateStartTime).getLayout().cancelbooking(seatID);
    }

    public void bookSeat(String dateStarttime) {
        this.cinemaSchedule.get(dateStartTime).getLayout().bookseat();
    }

}



