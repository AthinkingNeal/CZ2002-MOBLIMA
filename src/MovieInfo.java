import java.util.ArrayList;
import java.util.Scanner;

public class MovieInfo {
    private String movieId;
    private String title;
    private Cineplex[] cineplexes;
    private String showingStatus;
    private String synopsis;
    private boolean support2D;
    private boolean support3D;
    private boolean isBlockbluster;
    private String director;
    private String cast;
    private int numOfSales;
    private double overAllRating;
    private int numOfRating;
    private ArrayList<RevNRat> reviewsAndRating;

    public void addReviewRating(int userID) {
        Scanner s = new Scanner(System.in);
        System.out.println("Please input your comment: ");
        String review = s.nextLine();
        System.out.println("Please input your rating: (0-5) ");
        Float rating = Float.parseFloat(s.nextLine());
        RevNRat newReview = new RevNRat(userID, review, rating);
        System.out.println("Your review has been posted successfully!");
        this.overAllRating = (this.overAllRating * this.numOfRating)


    }

    public void incrementSales() {
        this.numOfSales++;
    }

    public String movieInfo() {
        String temp = "";
        temp += "Movie Title: " + getTitle();
        temp += "\nShowing Status: " + getShowingStatus();
        temp += "\nSynoposis: " + getSynopsis();
        temp += "\nAvailable types: " + getAvailableTypes();
        temp += "\nDirector: " + getDirector();
        temp += "\nCast: " + getCast();//!!!
        temp += "\nOverall Rating: " + getOverAllRating();
        temp += "\nNumber Of Sales: " + getNumOfSales();

        //!!!
    }

    public String nextRev() {
        String temp = "";
        temp +=
        if (getCurrRev() == getNumOfRating())

        //!!!
    }

    private class RevNRat {
        private int moviegoerID;
        private String review;
        private float rating;

        public RevNRat(int id, String review, float rating) {
            this.moviegoerID = id;
            this.review = review;
            this.rating = rating;
        }

        public void displayReviewRating() {
            System.out.println("User: " + userName);
            System.out.println("Review:" + review);
            System.out.println("Rating: " + rating);
        }

    }

}




}
