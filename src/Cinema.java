import java.io.Serializable;
import java.util.*;

public class Cinema implements Serializable {
    private static final long serialVersionUID = 1L;
    // hashmap: key: "dateStarttime", value: MovieSchedule

    private HashMap<String, MovieSchedule> cinemaSchedule = new HashMap<>();
    private int cineplexID;
    private int cinemaID;
    private String filename;
    private CinemaClass cinemaClass;

    public enum CinemaClass {
        platinum, golden, normal
    }


    public HashMap<String, MovieSchedule> getCinemaSchedule() {
        return cinemaSchedule;
    }

    public int getCinemaID() {
        return cinemaID;
    }

    public void setCinemaID(int cinemaID) {
        this.cinemaID = cinemaID;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public CinemaClass getCinemaClass() {
        return cinemaClass;
    }

    public void setCinemaClass(CinemaClass cinemaClass) {
        this.cinemaClass = cinemaClass;
    }

    public int getCineplexID() {
        return cineplexID;
    }

    public void setCineplexID(int cineplexID) {
        this.cineplexID = cineplexID;
    }

    /**
     * Initialise with a file containing all the moviescheduleinfo
     */
    public Cinema(int cinemaID, int cineplexID, CinemaClass cinemaClass) {
        this.cinemaID = cinemaID;
        this.cineplexID = cineplexID;
        this.cinemaClass = cinemaClass;
    }

    /**
     * Allow staff to add a single record of movie schedule
     */
    public void addRecord() {
        System.out.println("Added");
    }

    public void addRecord(MovieInfo m) {
        Scanner s = new Scanner(System.in);
        String dateStartTime;
        double duration;
        boolean is3D;
        System.out.println("Please enter the date in this format: yyyy-mm-dd.");
        dateStartTime = s.nextLine();
        System.out.println("Please enter the start time of the movie in this format: hh-mm.");
        dateStartTime += "-" + s.nextLine();

        String movieName = m.getTitle();
        System.out.println("This movie supports 3D: " + m.isSupport3D());
        System.out.println("This movie supports 2D: " + m.isSupport2D());
        System.out.println("Is this movie session 3D? Y/N");
        String ans = s.nextLine();
        if (ans.equals("Y") || ans.equals("y"))
            is3D = true;
        else
            is3D = false;

        System.out.println("How long is the duration of this movie? Answer in this format: 2.5");
        duration = Double.parseDouble(s.nextLine());

        this.cinemaSchedule.put(dateStartTime, new MovieSchedule(dateStartTime, cineplexID, m.getMovieId(), movieName, is3D, m.isBlockbluster(), duration, cinemaID, cinemaClass));
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
        System.out.println("Please input the new date YYYY-mm-dd:");
        String date = s.nextLine();
        System.out.println("Please input the new time HH-MM:");
        String dateStartTime = date + '-' + s.nextLine();
        newRecord.setDateStartTime(dateStartTime);
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

    public void displayAllSchedulesOfMovie(int movieID) {
        for (String key : cinemaSchedule.keySet()) {
            if (cinemaSchedule.get(key).getMovieID() == movieID)
                System.out.println("Movie ID: " + movieID + ' ' + cinemaSchedule.get(key).getDateStartTime()); // print out date in "YYYY-mm-dd"
        }
    }

    public HashSet<String> getAvailableDates(int movieID, String currentDate, String currentTime) {
        HashSet<String> toPrint = new HashSet<>();
        for (String key : cinemaSchedule.keySet()) {
            if (cinemaSchedule.get(key).getMovieID() == movieID) {
                if (cinemaSchedule.get(key).getDateStartTime().compareTo(currentDate + '-' + currentTime) > 0)  //only movie schedules in the future are displayed
                    toPrint.add(cinemaSchedule.get(key).getDateStartTime().substring(0, 10));
            } // print out date in "YYYY-mm-dd"
        }
        return toPrint;
//


    }

    /**
     * Once user has selected movie, location and date, display all available times of this movie in this cinema and return selected scheduels
     * Assumption: in one cineplex, a movie cannot start at the same time!
     */
    public HashMap<String, MovieSchedule> getAndDisplayAvailableTime(int movieID, String date, String currentDate, String currentTime) {
        HashMap<String, MovieSchedule> rst = new HashMap<>();
        for (String key : cinemaSchedule.keySet()) {
            if (cinemaSchedule.get(key).getMovieID() == movieID)
                if (cinemaSchedule.get(key).getDateStartTime().substring(0, 10).equals(date))
                    if (cinemaSchedule.get(key).getDateStartTime().compareTo(currentDate + '-' + currentTime) > 0)  //only movie schedules in the future are displayed
                    {
                    cinemaSchedule.get(key).displayMovieRecord();
                    rst.put(key, cinemaSchedule.get(key));
                }

        }
        return rst;
    }

    public MovieSchedule getByDateStarttime(String dateStarttime) {
        return cinemaSchedule.get(dateStarttime);
    }

}



