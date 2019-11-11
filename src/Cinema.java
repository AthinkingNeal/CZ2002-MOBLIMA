import java.io.Serializable;
import java.util.*;

public class Cinema implements Serializable {
    private static final long serialVersionUID = 1L;
    // hashmap: key: "dateStarttime", value: MovieSchedule

    private HashMap<String, MovieSchedule> cinemaSchedule = new HashMap<String, MovieSchedule>();
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

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
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
    public void addRecord(){
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

    public void displayAllSchedulesOfMovie(int movieID) {
        for (String key : cinemaSchedule.keySet()) {
            if (cinemaSchedule.get(key).getMovieID() == movieID)
                System.out.println(movieID + cinemaSchedule.get(key).getDateStartTime()); // print out date in "YYYY-mm-dd"
        }
    }


    public void showAvailableDates(int movieID) {
        for (String key : cinemaSchedule.keySet()) {
            if (cinemaSchedule.get(key).getMovieID() == movieID)
                System.out.println(cinemaSchedule.get(key).getDateStartTime().substring(0, 10)); // print out date in "YYYY-mm-dd"
        }
    }

    /**
     * Once user has selected movie, location and date, display all available times of this movie in this cinema and return selected scheduels
     *Assumption: in one cineplex, a movie cannot start at the same time!
     */
    public HashMap<String, MovieSchedule> getAndDisplayAvailableTime(int movieID, String date) {
        HashMap<String, MovieSchedule> rst = new HashMap<String, MovieSchedule>();
        for (String key : cinemaSchedule.keySet()) {
            if (cinemaSchedule.get(key).getMovieID() == movieID)
                if (cinemaSchedule.get(key).getDateStartTime().substring(0, 10).equals(date)) {
                    cinemaSchedule.get(key).displayMovieRecord();
                    rst.put(key, cinemaSchedule.get(key));
                }

        }
        return rst;
    }


    /**
     * Save all MovieSchedule objects into the file
     */
//    public void saveToFile() {
//        try {
//            FileOutputStream fos = new FileOutputStream(this.filename);
//            ObjectOutputStream oos = new ObjectOutputStream(fos);
//            System.out.print("saving data to " + filename + "...");
//
//
////            for(String key : cinemaSchedule.keySet())
////            {
////                oos.writeObject(cinemaSchedule.get(key));
////            }
//
////            oos.writeObject(null);
//            oos.writeObject(cinemaSchedule);
//            oos.close();
//        } catch (IOException e) {
//            System.out.println("File input error");
//        }
//    }

}



