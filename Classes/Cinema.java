public class Cinema{
    enum Class{
        platinum,golden,normal
    }
    private int id;
    private Seat[][] layout;
    private Class class;
    Cinema(int cid, Seat[][] clayout, Class cclass){
        id=cid;
        layout=clayout;
        class=cclass;
    }
    public void DisplayLayout(){
        System.out.println(layout);
    }
    public void cancelSeat(int x, int y){
        layout[x][y].cancelBooking()
    }
    public void bookSeat(int x, int y){
        layout[x][y].bookSeat()
    }
}