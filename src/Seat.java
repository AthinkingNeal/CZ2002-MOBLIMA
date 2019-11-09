public class Seat{
    private String seatId;
    private boolean isOccupied;
    private String seatIDAlphabets = "ABCDEFGHIJ";

    Seat(int x, int y, boolean sisOccupied) {
        this.seatId = seatIDAlphabets.charAt(x) + Integer.toString(y);
        isOccupied = sisOccupied;
    }
    public  boolean GetOccu(){
        return isOccupied;}

    public String GetId(){
        return seatId;
    }
    public boolean bookSeat(){
        if (isOccupied==true){
            System.out.println("This seat is already occupied! Book failed!");
            return false;
        }
        else{
            isOccupied=true;
            return true;
        }
    }
    public void cancelBooking(){
        isOccupied=false;
        
    }
}