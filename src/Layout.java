

public class Layout{
    //    enum Movieclass{
//        platinum,golden,normal
//    }
    private Seat[][] layout;
    private String seatIDAlphabets = "ABCDEFGHIJ";
    private Cinema.CinemaClass movieclass = Cinema.CinemaClass.normal;
//    Layout(int cid, Seat[][] clayout, Movieclass cclass){

    public Layout(int cid, Cinema.CinemaClass cinemaClass) {

    int dimension;
    if (cinemaClass == Cinema.CinemaClass.platinum)
        dimension = 3; // if it's platinum, cinema has 3*3 seats;
    else if (cinemaClass == Cinema.CinemaClass.golden)
        dimension = 6;
    else
        dimension = 10;
    layout = new Seat[dimension][dimension];
    for(int i = 0; i< 10; i++)
        for(int j = 0; j < 10; j++)
            layout[i][j] = new Seat(seatIDAlphabets.charAt(i) + Integer.toString(j), false, false);
    movieclass=cinemaClass;

    }

    public Cinema.CinemaClass GetClass() {
        return movieclass;
    }

    public void DisplayLayout(){
        for (int t = 0; t<Math.floor(4*layout.length)-5; t++)
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
        for (int i=0; i<layout.length; i++) {
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
                } else{
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

    public void cancelSeat(String[] seatIDs) {
        for (int i = 0; i < seatIDs.length; i++) {
            int x = seatIDAlphabets.indexOf(seatIDs[i].charAt(0));
            int y = Integer.parseInt(String.valueOf(seatIDs[i].charAt(1)));
            layout[x][y].cancelBooking();
        }
    }

    public void bookSeat(String[] seatIDs) {
        for (int i = 0; i < seatIDs.length; i++) {
            int x = seatIDAlphabets.indexOf(seatIDs[i].charAt(0));
            int y = Integer.parseInt(String.valueOf(seatIDs[i].charAt(1)));
            layout[x][y].bookSeat();
        }
    }

}