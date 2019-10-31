public class Seat{
    private String id;
    private boolean normal;
    private boolean isOccupied;
    Seat(int sid, boolean snormal,boolean sisOccupied){
        id=sid;
        normal=snormal;
        isOccupied=sisOccupied;
    }
    public boolean bookSeat(){
        if (isOccupied==true){
            return false
        }
        else{
            isOccupied=true;
            return true;
        }
    }
    public boolean cancelBooking(){
        isOccupied=false
        
    }
}