import java.util.Scanner;
public Class UI
{

	private boolean isStaff;
  private Database cineplexDB; 
  private Database moviesInfoDB;
  private Database movieGoerDB;
  // private ArrayList<Cineplex> cineplexes; //check whether can change into class
  // private ArrayList<Movie> movies; //check whether can change into class
  // private ArrayList<MovieGoer> movieGoers; //check whether can change into class
  private Database paymentRecordDB;
  private Database staffRecordDB;
  private Database movieScheduleDB;
  private Date date;
  private PriceTable pricetable;
  


	public UI(//filenames)
{
	this.cineplexDB = new CineplexDB();
  this.moviesInfoDB = new MoviesInfoDB();
  this.movieGoerDB = new MovieGoerDB();
  this.paymentRecordDB = new paymentRecordDB;
  this.staffRecordDB = new ;
  private Database movieScheduleDB;
  private Date date;
  private PriceTable pricetable;

}


	public static void main(String args[])
{
		Scanner sc = new Scanner(System.in);
		System.out.println("Please enter your choice:");
		System.out.println("1.Staff");
    System.out.println("2.MovieGoer");
    int choice = sc.nextInt();
    if(choice == 1) isStaff = True;
    else ifStaff = False;
    
    if(isStaff){
    	System.out.println("Please enter your choice:");
      System.out.println("1. Register a new staff");
      System.out.println("2. Current staff log in");
      choice = sc.nextInt();
      //check valid input
      while(choice != 1 &&&& choice != 2){
      	System.out.println("Please enter a valid input");
        choice = sc.nextInt();
      }
      
      int trialNumber = 0;
      while(choice == 1 && trialNumber<3){
  
      		System.out.println("Please enter your new staffID:");
          String staffID = sc.next();
      		System.out.println("Please enter your passward:");
          String password = sc.next();
          System.out.println("Please re-enter and confirm your password:");
          String reenterPassword = sc.next();
          if (password.equals(reenterPassword))
          {
          staffRecords.addLoginRecord(staffID, password);
          break;
          }
          else
          	{
            System.out.println("Passwords don't match! Try again!");
              if (trialNumber++ == 3)
              {
              System.out.println("Too many trials. Program terminated. Please try again!");
              System.exit( 0 );
              }
            }   
      }
      
      while(choice == 2 && trialNumber < 3){
      	// System.out.println("Please enter your staffID:");
      	// String staffID = sc.next()
      	// System.out.println("Please enter your passward:");
      	// String password = sc.next();
      	if (staffRecords.login())
        	break;
        else
        {
          if (trialNumber++ == 3)
                {
                System.out.println("Too many trials. Program terminated. Please try again!");
                System.exit( 0 );
                }
        }      
      }
      
      // below is the Staff Operation
      
      System.out.println("Please enter your choices: ");
      System.out.println("1. Configure the system settings: (ticket prices, holidays,etc)");
      System.out.println("2. Enter information about forthcoming movies");
      System.out.println("3. Update details of the moives or remove one movie");
      System.out.println("4. List the current top 5 ranking movies (by ticket sales or rating)");
      System.out.println("5. Exit");
      
      choice = sc.nextInt();
      while(choice!=5){
       switch(choice)
      {
        case 1:
        // method used:
        // PriceTable.configureTicketPrice()
				// Date.configure()
							System.out.println("Please enter your choices: ");
              System.out.println("1. Configure ticket price");
              System.out.println("2. Configure holiday");
              choice = sc.nextInt();
              if(choice == 1){
              		continue;
                  // PriceTable.configurePrice()
              }
              break;
        case 2:
							// not sure which class and which method
              break;
        case 3:// Update details of the moives or remove one movie
							System.out.println("Please enter your choices: ");
              System.out.println("1. Update movie details");
              System.out.println("2. remove one movie");
              choice = sc.nextInt();
              if(choice == 1):{
              		continue;
                  // get the movie ID and update the corresponded movie
              }
              
              
              break;
        case 4:
       	 			break;
      }
      System.out.println("Please enter your choice:");
      choice = sc.nextInt();
      }
      
      if(choice==5)
      	System.exit(0);
      
     
      		
      
      
    }
  
    
    
    /////////////////////////////////////////////////////////////////////////////
    else if (!isStaff){
    		System.out.println("Please enter your choice:");
        System.out.println("1. Register a new user");
        System.out.println("2. Current user log in");
        
        while(choice != 1 && choice != 2){
      	System.out.println("Please enter a valid input");
        choice = sc.nextInt();
      }
        
        if(choice == 1 ){
  
      		do{
          System.out.println("Please enter your email address:");
          String emailAddress = sc.next();
          System.out.println("Please confirm your email address: " + emailAddress + "Y/N");
          char ch = sc.next().charAt(0);
          }while(ch!='Y'&& ch!='y');
          
          do{System.out.println("Please enter your mobile number:");
          int mobileno = sc.nextInt();
          System.out.println("Please confirm your mobile number: " + mobileno + "Y/N");
          char ch = sc.next().charAt(0);
          }while(ch!='Y' && ch!='y');
          
          do{System.out.println("Please enter your name:");
          String name = sc.next();
          System.out.println("Please confirm your name: " + name + "Y/N");
          char ch = sc.next().charAt(0);
          }while(ch!='Y' && ch!='y''');
          
          System.out.println("Please enter your age:");
          int age = sc.nextInt();
          	
          MovieGoer newMovieGoer = new MovieGoer(emailAddress, mobileno, name, age);
          movieGoerDB.addRecord(newMovieGoer);
    
          }
          //if end here
          do{
          	System.out.println("Welcome"+);
            System.out.println("Which movie you want to watch?\n");
            Arraylist <MovieInfo> movielist=movieInfoDb.getMovieList()
            for (int i=0;i<movielist.size();i++){
            	if movielist[i].getShowingStatus(){
              	System.out.println(movielist.get(i).movieinfo())
              }
            }
            System.out.println("Please Enter the MovieId")
            int movieId = sc.nextInt();
            
          }while();
          
          }   
      
      
      }
      
      
        
        
        
        
        
    }
}
	


}



