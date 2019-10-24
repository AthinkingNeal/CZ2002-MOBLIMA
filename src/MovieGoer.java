package edu.ntu.assignment;

public class MovieGoer {
    protected String name;
    protected int movieGoerID;
    protected int mobileNumber;
    protected String emailAddress;
    protected int age;
    protected PaymentRecord [] History;

    public void MovieGoer(String name, int mobileNumber, String emailAddress, int age){
        this.name = name;
        this.mobileNumber = mobileNumber;
        this.emailAddress = emailAddress;
        this.age = age;
    }

    public void getHistory(){
        PaymentRecord temp;
        int len = History.length;
        if (len == 0) {
            System.out.println("This is no ticket history.");
        }
        else{
            int i;
            for (i = 0; i < len; i++){
                History[i].printRecord();
            }
        }
    }

    /*
    update PaymentRecord, flag as refunded
    update MovieSale, sale - 1
    update Seat, flag as available
    */
    public void cancelBooking(String recordID){

    }
    public int getMovieGoerID() {
        return movieGoerID;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setMovieGoerID(int movieGoerID) {
        this.movieGoerID = movieGoerID;
    }

    public void setMobileNumber(int mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setHistory(PaymentRecord[] history) {
        History = history;
    }

    public int getMobileNumber() {
        return mobileNumber;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public int getAge() {
        return age;
    }

    public String getName() {
        return name;
    }



}
