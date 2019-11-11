import java.io.Serializable;

public class MovieSchedule implements Serializable {
    private String dateStartTime;
    private int movieID;
    private String movieName;
    private boolean is3D;
    private boolean isBlockbuster;
    private double duration;
    private Layout layout;
    private int cinemaID;
    private int cineplexID;
    private Cinema.CinemaClass cinemaClass;

    public MovieSchedule(String dateStartTime, int cineplexID, int movieID, String movieName, boolean is3D, boolean isBlockbuster, double duration, int cinemaID, Cinema.CinemaClass cinemaClass) {
        this.cineplexID = cineplexID;
        this.cinemaID = cinemaID;
        this.dateStartTime = dateStartTime;
        this.movieID = movieID;
        this.movieName = movieName;
        this.is3D = is3D;
        this.isBlockbuster = isBlockbuster;
        this.duration = duration;
        this.layout = new Layout(cinemaID,cinemaClass);
        this.cinemaClass = cinemaClass;
    }

    public int getCineplexID() {
        return cineplexID;
    }

    public int getCinemaID() {
        return cinemaID;
    }

    public Cinema.CinemaClass getCinemaClass() {
        return cinemaClass;
    }

    public String getDateStartTime() {
        return this.dateStartTime;
    }
    public void setMovieID(int newMovieID){this.movieID = newMovieID;}
    public void setMovieName(String newName){this.movieName = newName;}

    public void setDateStartTime(String dateStartTime) {
        this.dateStartTime = dateStartTime;
    }

    public boolean getIs3D() {
        return this.is3D;
    }

    public void setIs3D(boolean is3D) {
        this.is3D = is3D;
    }

    public boolean getIsBlockbuster() {
        return this.isBlockbuster;
    }
    public int getMovieID(){return this.movieID;}
    public String getMovieName(){return this.movieName;}

    public void setIsBlockbuster(boolean isBlockbuster) {
        this.isBlockbuster = isBlockbuster;
    }

    public double getDuration() {
        return this.duration;
    }

    public void setDuration(double newDuration) {
        this.duration = newDuration;
    }

    public Layout getLayout() {
        return layout;
    }

    public void displayMovieRecord()
    {
        System.out.println(dateStartTime + ": (" + movieID + ")" + movieName + " 3D: " + is3D + " Blockbuster: " + isBlockbuster + " Duration: " + duration);
    }

    public void cancelBooking(String seatID) {
        this.getLayout().cancelSeat(seatID);
    }

    public void bookSeat(String seatID) {
        this.getLayout().bookSeat(seatID);
    }

}
