
import java.io.Serializable;
import java.util.ArrayList;

public class PaymentRecord implements Serializable {
    private static final long serialVersionUID = 1L;
    private String TID;
    //XXXYYYYMMDDhhmm (Y : year, M : month, D : day, h : hour, m : minutes, XXX : cinema code in letters)
    private int movieGoerID;
    private int movieID;
    private int cinemaID;
    private int cineplexID;
    private int amountOfTickets;
    private String movieDateStartTime;
    private ArrayList<String> seatID;
    private float totalCost;
    private boolean canceled;

    public PaymentRecord(String TID, int movieGoerID, int movieID, int cinemaID, int cineplexID, int amountOfTickets, ArrayList<String> seatID, float totalCost, String movieDateStartTime, Boolean canceled) {
        this.TID = TID;
        this.movieGoerID = movieGoerID;
        this.movieID = movieID;
        this.cinemaID = cinemaID;
        this.cineplexID = cineplexID;
        this.amountOfTickets = amountOfTickets;
        this.seatID = seatID;
        this.totalCost = totalCost;
        this.canceled = canceled;
        this.movieDateStartTime = movieDateStartTime;
    }

    public String getMovieDateStartTime() {
        return movieDateStartTime;
    }

    public void setMovieDateStartTime(String movieDateStartTime) {
        this.movieDateStartTime = movieDateStartTime;
    }


    public int getCineplexID() {
        return cineplexID;
    }

    public void setCineplexID(int cineplexID) {
        this.cineplexID = cineplexID;
    }

    public Boolean getCanceled() {
        return canceled;
    }

    public void setCanceled(Boolean canceled) {
        this.canceled = canceled;
    }

    public ArrayList<String> getSeatID() {
        return seatID;
    }

    public void setSeatID(ArrayList<String> seatID) {
        this.seatID = seatID;
    }

    public int getCinemaID() {
        return cinemaID;
    }

    public void setCinemaID(int cinemaID) {
        this.cinemaID = cinemaID;
    }

    public String getTID() {
        return TID;
    }

    public void setTID(String TID) {
        this.TID = TID;
    }

    public int getMovieGoerID() {
        return movieGoerID;
    }

    public void setMovieGoerID(int movieGoerID) {
        this.movieGoerID = movieGoerID;
    }

    public int getMovieID() {
        return movieID;
    }

    public void setMovieID(int movieID) {
        this.movieID = movieID;
    }


    public int getAmountOfTickets() {
        return amountOfTickets;
    }

    public void setAmountOfTickets(int amountOfTickets) {
        this.amountOfTickets = amountOfTickets;
    }

    public float getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(float totalCost) {
        this.totalCost = totalCost;
    }


    public void printRecord() {
        if (canceled)
            return;
        String temp = "Following are your record information: ";
        temp += "\nTransactionID: " + getTID();
        temp += " |MovieGoerID: " + getMovieGoerID();
        temp += " |MovieID: " + getMovieID();
        temp += " |CinemaID: " + getCinemaID();
        temp += " |MovieDateTime: " + getMovieDateStartTime();
        temp += " |No.Tickets: " + getAmountOfTickets();
        temp += " |SeatIDs: ";
        for (int i = 0; i < seatID.size(); i++)
            temp += seatID.get(i) + ' ';
        temp += " |TotalPrice: $" + getTotalCost();
        System.out.println(temp);
    }
}