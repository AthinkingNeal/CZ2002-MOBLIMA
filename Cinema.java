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
    public void UpdateLayout(Seat[][] newLayout){
        layout=newLayout;
    }
}