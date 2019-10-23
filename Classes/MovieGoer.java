
public class MovieGoer {
    protected String name;
    protected int movieGoerID;
    protected int mobileNumber;
    protected String emailAddress;
    protected int age;
    protected PaymentRecord [] History;

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
}
