// This class is incomplete yet



import java.util.ArrayList;
import java.util.HashMap;


public class Cineplex{
    private int cineplexID;
    private String name;
    private String location;
    private ArrayList<Cinema> cinemaArrayList; // Will this also initialize all the cinemas inside? Double Check

    public Cineplex(int cineplexID, String name, String location, ArrayList<Cinema> cinemaArrayList){
        this.cineplexID = cineplexID;
        this.name = name;
        this.location = location;
        this.cinemaArrayList = cinemaArrayList;
    }


    // the user already selects the movieID, date, and time already.
    // we assume that one movie will only exist in one MovieSchedule at a specific time.
//    public MovieSchedule getSchedule(int movieID, String date, String time){
//
//    }
//
//





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

    public ArrayList<Cinema> getCinemaArrayList() {
        return cinemaArrayList;
    }

    public void setCinemaArrayList(ArrayList<Cinema> cinemaArrayList) {
        this.cinemaArrayList = cinemaArrayList;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }


}
