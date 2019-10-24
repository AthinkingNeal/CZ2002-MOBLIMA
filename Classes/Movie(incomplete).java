import java.util.*;
public class Movie
{
	private String movieId;
	private String title;
	private Cineplex[] cineplexes;
	private String showingStatus;
	private String synopsis;
	private String availableTypes;
	private String director;
	private String cast;

	private float overAllRating;
	private int numOfRating;

	private LinkedList<RevNRat> reviewsAndRating;
	private int currRev;

	private int numOfSales;
	//movieSchedule
	
	public Movie()
	{
		//!!!
		currRev=0;
	}
	public String getMovieId()
	{
		return movieId;
	}

	public String getTitle()
	{
		return title;
	}

	public Cineplex[] getCineplex()
	{
		//!!!
		return Cineplex[];
	}	

	public String getShowingStatus()
	{
		return showingStatus;
	}

	public String getSynopsis()
	{
		return synopsis;	
	}			
	public String getAvailableTypes()
	{
		return availableTypes;
	}
	public String getDirector()
	{
		return director;	
	}
	public String getCast()
	{
		//!!!!!
		return Cast;	
	}


	public float getOverAllRating()
	{
		return overAllRating;
	}
	public void setOverAllRating(float temp)
	{
		overAllRating=temp;
	}
	public int getNumOfRating()
	{
		return numOfRating;
	}
	public void setNumOfRating(int temp)
	{
		numOfRating=temp;
	}
	public int getCurrRev()
	{
		return currRev;
	}
	public void setCurrRev(int temp)
	{
		currRev=temp;
	}
	//public int nextRev()
	//{
	//	if(getCurrRev()==getNumOfRating())
	//		return -1;
	//	
	//}
	public int getNumOfSales()
	{
		return numOfSales;
	}
	public String availableTypes()
	{
		//!!!
		return availableTypes;
	}
	
	public void enterReviewAndRating(String userName, String review, float rating)
	{
		setOverAllRating((getOverAllRating()+rating)/(getNumOfRating()+1));
		setNumOfRating(getNumOfRating()+1);

		RevNRat temp=new RevNRat(userName,review,rating);
		reviewsAndRating.add(temp);
	}

	public String movieInfo()
	{
		String temp="";
		temp+="Movie Title: "+getTitle();
		temp+="\nShowing Status: "+getShowingStatus();
		temp+="\nSynoposis: "+getSynopsis();
		temp+="\nAvailable types: "+getAvailableTypes();
		temp+="\nDirector: "+getDirector();
		temp+="\nCast: "+getCast();//!!!
		temp+="\nOverall Rating: "+getOverAllRating();
		temp+="\nNumber Of Sales: "+getNumOfSales();
		
		//!!!
	}

	public String nextRev()
	{
		String temp="";
		temp+= 
		if(getCurrRev()==getNumOfRating())
		
		//!!!
	}
	
}

public class RevNRat
{
	private String userName;
	private String review;
	private float rating;

	public RevNRat(String userName,String review,float rating)
	{
		this.userName=userName;
		this.review=review;
		this.rating=rating;
	}
	public String getUserName()
	{
		return userName;
	}
	public String getReview()
	{
		return review;
	}
	public float getRating()
	{
		return rating;
	}
}
