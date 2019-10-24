package edu.ntu.assignment;

public class PaymentRecord {
    protected String TID;
    //XXXYYYYMMDDhhmm (Y : year, M : month, D : day, h : hour, m : minutes, XXX : cinema code in letters)

    protected int movieGoerID;
    protected int movieID;
    protected int minemaID;
    protected int mineplexID;
    protected int amountOfTickets;
    protected int [][] seatID;
    protected float totalCost;


    public void printRecord()
    {
        System.out.println("Print information"); // to be done
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

    public int getMinemaID() {
        return minemaID;
    }

    public void setMinemaID(int minemaID) {
        this.minemaID = minemaID;
    }

    public int getMineplexID() {
        return mineplexID;
    }

    public void setMineplexID(int mineplexID) {
        this.mineplexID = mineplexID;
    }

    public int getAmountOfTickets() {
        return amountOfTickets;
    }

    public void setAmountOfTickets(int amountOfTickets) {
        this.amountOfTickets = amountOfTickets;
    }

    public int[][] getSeatID() {
        return seatID;
    }

    public void setSeatID(int[][] seatID) {
        this.seatID = seatID;
    }

    public float getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(float totalCost) {
        this.totalCost = totalCost;
    }

}
