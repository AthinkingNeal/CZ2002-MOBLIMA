public class Layout{
    enum Movieclass{
        platinum,golden,normal
    }
    private Seat[][] layout;
    private Movieclass movieclass=Movieclass.normal;
//    Layout(int cid, Seat[][] clayout, Movieclass cclass){
    Layout(int cid, Movieclass cclass){
        layout = new Seat[10][10];
        for(int i= 0; i< 10;i++)
            for(int j = 0; j < 10; j++)
            layout[i][j] = new Seat(Integer.toString(i)+Integer.toString(j),false,false);
        movieclass=cclass;

    }
    public Movieclass GetClass(){
        return movieclass;
    }
    public void DisplayLayout(){
        for (int t=0; t<Math.floor(4*layout.length)-5;t++)
            System.out.print(" ");
        System.out.print("[SCREEN]\n");
//        for (int i=0; i<layout.length;i++) {
//            for (int j = 0; j < layout[0].length; j++) {
//                if (j==0)
//                    System.out.print(" \\_"+"_| ");
//                if (j==(Math.floor(0.5*layout[0].length)))
//                    System.out.print(" |_"+"_| ");
//                System.out.print(" ["+layout[i][j].GetId()+"] ");
//                if (j==(layout[0].length-1))
//                    System.out.print(" |_"+"_/ ");
//            }
//            System.out.print("\n");
//
//            for (int j = 0; j < layout[0].length; j++) {
//                if (j==0)
//                    System.out.print(" \\_"+"_| ");
//                if (j==(Math.floor(0.5*layout[0].length)))
//                    System.out.print(" |_"+"_| ");
//                if (layout[i][j].GetOccu()){
//                    System.out.print(" occu ");
//                }
//                else{
//                    System.out.print(" avai ");
//                }
//                if (j==(layout[0].length-1))
//                    System.out.print(" |_"+"_/ ");
//            }
//            System.out.print("\n");
//            System.out.print("\n");
//
//        }
        for (int i=0; i<layout.length;i++) {
            for (int j = 0; j < layout[0].length; j++) {
                if (j==0)
                    System.out.print(" \\_"+"_| ");
                if (j==(Math.floor(0.5*layout[0].length)))
                    System.out.print(" |_"+"_| ");
                System.out.print("  "+layout[i][j].GetId()+"  ");
                if (j==(layout[0].length-1))
                    System.out.print(" |_"+"_/ ");
            }
            System.out.print("\n");
            boolean res=layout[1][5].bookSeat();
            for (int j = 0; j < layout[0].length; j++) {
                if (j==0)
                    System.out.print(" \\_"+"_| ");
                if (j==(Math.floor(0.5*layout[0].length)))
                    System.out.print(" |_"+"_| ");
                if (layout[i][j].GetOccu()){
                    System.out.print(" [XX] ");
                }
                else{
                    System.out.print(" [  ] ");
                }
                if (j==(layout[0].length-1))
                    System.out.print(" |_"+"_/ ");
            }
            System.out.print("\n");
            System.out.print("\n");

        }
    }
    public Seat[][] getLayout(){
        return layout;
    }
    public void cancelSeat(int x, int y){
        layout[x][y].cancelBooking();
    }
    public void bookSeat(int x, int y){
        layout[x][y].bookSeat();
    }

}