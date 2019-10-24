package edu.ntu.assignment;

import java.util.ArrayList;

public class PaymentRecord {
    protected String TID;
    //XXXYYYYMMDDhhmm (Y : year, M : month, D : day, h : hour, m : minutes, XXX : cinema code in letters)

    protected int movieGoerID;
    protected int movieID;
    protected int cinemaID;
    protected int mineplexID;
    protected int amountOfTickets;
    protected ArrayList<String> seatID;
    protected float totalCost;
    protected Boolean canceled;

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

    public float getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(float totalCost) {
        this.totalCost = totalCost;
    }


    public void printRecord()
    {
        String temp = "Following are your record information: ";
        temp += "\nTransaction ID: "+ getTID();
        temp += "\nmovieGoerID: " + getMovieGoerID();
        temp += "\nmovieID: " + getMovieID();
        temp += "\ncinemaID: " + getCinemaID();
        temp += "\namountOfTickets: " + getAmountOfTickets();
        for(int i = 0; i < seatID.size(); i++)
            temp += "seatID: " + seatID.get(i);
        temp += "totalCost: " + getTotalCost();

        System.out.println("temp");
    }


}
