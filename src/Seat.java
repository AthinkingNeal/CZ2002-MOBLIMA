public class Seat{
    private int id;
    private boolean normal;
    private boolean isOccupied;
    Seat(int sid, boolean snormal,boolean sisOccupied){
        id=sid;
        normal=snormal;
        isOccupied=sisOccupied;
    }
    public boolean bookSeat(int id){
        if (isOccupied==true){
            return false
        }
        else{
            isOccupied=true;
            return true;
        }
    }
    public boolean cancelBooking(int id){
        //I think need authentication here
        
    }
}