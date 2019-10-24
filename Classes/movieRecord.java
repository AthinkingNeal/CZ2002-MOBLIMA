public class movieRecord {
    private int index;
    private int cineplexID;
    private int cinemaID;
    private int movieID;
    private String movieName;
    private String dateTime; //"DD-MM-YY"
    private String startTime; //"18:00"
    private String endTime;

    // index is used to search and identify this record
    public movieRecord(int cineplexID, int cinemaID, int movieID, String movieName, String datetime, String startTime, String endTime, Cinema) {
        this.cinemaID = cinemaID;
        this.cineplexID = cineplexID;
        this.movieID = movieID;
        this.movieName = movieName;
        this.dateTime = datetime; // change to Date type! "YYYY-MM-DD-hh-mm"
        this.startTime = startTime;
        this.endTime = endTime;

    }

    public void setCineplexID(int newCineplexID){this.cineplexID = newCineplexID;}
    public void setCinemaID(int newCinemaID){this.cinemaID = newCinemaID;}
    public void setMovieID(int newMovieID){this.movieID = newMovieID;}
    public void setMovieName(String newName){this.movieName = newName;}
    public void setDateTime(String newDate){this.dateTime = newDate;}
    public void setStartTime(String newStarttime){this.startTime = newStarttime;}
    public void setEndTime(String newEndTime){this.endTime = newEndTime;}

    public int getCineplexID(){return this.cineplexID;}
    public int getCinemaID(){return this.cinemaID;}
    public int getMovieID(){return this.movieID;}
    public String getMovieName(){return this.movieName;}
    public String getDateTime(){return this.dateTime;}
    public String getStartTime(){return this.startTime;}
    public String getEndTime(){return this.endTime;}

    public void displayMovieRecord()
    {
        System.out.println("Cineplex ID: " +cineplexID);
        System.out.println("Cinema ID: " + cinemaID);
        System.out.println("Movie ID: "+ movieID);
        System.out.println("Movie Name: "+ movieName);
        System.out.println("Date: "+ dateTime);
        System.out.println("Start time: "+ startTime);
        System.out.println("End time: "+ endTime);
    }


}
