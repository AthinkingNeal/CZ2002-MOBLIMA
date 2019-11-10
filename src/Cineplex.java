// This class is incomplete yet

import java.util.Scanner;
import java.util.ArrayList;
import java.util.HashMap;
import java.io.Serializable;

public class Cineplex implements Serializable {
    private int cineplexID;
    private String name;
    private String location;
    private HashMap<Integer, Cinema> cinemaHashMap = new HashMap<Integer, Cinema>();

    public Cineplex(int cineplexID, String name, String location){
        this.cineplexID = cineplexID;
        this.name = name;
        this.location = location;
    }


    // the user already selects the movieID, date, and time already.
    // we assume that one movie will only exist in one MovieSchedule at a specific time.
    public ArrayList <MovieSchedule> getMovieScheduleByID(int movieID, String dateStartTime){
        ArrayList <MovieSchedule> scheduleArrayList = new ArrayList<MovieSchedule>();
        int len = cinemaHashMap.size();
        for(int i = 0; i < len; i++){
            Cinema currentCinema = cinemaHashMap.get(i);
            HashMap<String,MovieSchedule> currentScheduleMap = currentCinema.getCinemaSchedule();
            MovieSchedule currentSchedule = currentScheduleMap.get(dateStartTime);
            if (currentSchedule == null) {
                System.out.println("There is no movieSchedule in this period");
            }
        scheduleArrayList.add(currentSchedule);
        }
        return scheduleArrayList;
    }

    public void addCinema(){
        Scanner sc = new Scanner(System.in);
        int cinemaID;
        Cinema.CinemaClass cinemaClass = Cinema.CinemaClass.normal; // initialize MovieClass to normal
        int temp;
        System.out.println("Please enter the cinema ID");
        cinemaID = sc.nextInt();
        System.out.println("Please enter the cinemaClass (enter corresponded integer): ");
        System.out.println("1. Normal");
        System.out.println("2. Golden");
        System.out.println("3. Platinum");
        temp = sc.nextInt();
        if (temp == 1) {
            cinemaClass = Cinema.CinemaClass.normal;
        }
        else if (temp == 2) {
            cinemaClass = Cinema.CinemaClass.golden;
        }
        else if(temp == 3){
            cinemaClass = Cinema.CinemaClass.platinum;
        }
        else{
            System.out.println("You have entered an invalid class");
        }

        Cinema cinemaAdded = new Cinema(cinemaID = cinemaID, cineplexID, cinemaClass = cinemaClass);
        cinemaHashMap.put(cinemaID, cinemaAdded);
        System.out.println("You have successfully added this cinema to this cineplex");
    }


    public int getCineplexID() {
        return cineplexID;
    }

    public void setCineplexID(int cineplexID) {
        this.cineplexID = cineplexID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public ArrayList<Cinema> getCinemas() {
        return new ArrayList<>(cinemaHashMap.values());
    }

    public Cinema getCinemaByCinemaID(int cinemaID) {
        if (cinemaHashMap.containsKey(cinemaID))
            return cinemaHashMap.get(cinemaID);
        else
            return null;
    }

}
