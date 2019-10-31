public class Seat{
    private String id;
    private boolean normal;
    private boolean isOccupied;

    Seat(String sid, boolean snormal,boolean sisOccupied){
        id=sid;
        normal=snormal;
        isOccupied=sisOccupied;
    }
    public  boolean GetOccu(){
        return isOccupied;}
    public String GetId(){
        return id;
    }
    public boolean bookSeat(){
        if (isOccupied==true){
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