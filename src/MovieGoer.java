import java.util.ArrayList;

public class MovieGoer implements java.io.Serializable{
    protected String name;
    protected int movieGoerID;
    protected int mobileNumber;
    protected String emailAddress;
    protected int age;
    protected ArrayList <PaymentRecord> History;


    public MovieGoer(String name, int movieGoerID, int mobileNumber, String emailAddress, int age){
        this.name = name;
        this.movieGoerID = movieGoerID;
        this.mobileNumber = mobileNumber;
        this.emailAddress = emailAddress;
        this.age = age;
    }

    public void getHistory(){
        PaymentRecord temp;
        int len = History.size();
        if (len == 0) {
            System.out.println("This is no ticket history.");
        }
        else{
            int i;
            for (i = 0; i < len; i++){
                History.get(i).printRecord();
            }
        }
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

    public void setHistory(ArrayList <PaymentRecord> history) {
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
