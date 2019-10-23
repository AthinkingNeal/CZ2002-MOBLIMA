
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
        System.out.println("Print information");
    }
}
