package projectMe;

import java.util.ArrayList;
import java.util.Scanner;

public class MovieInfo {
    private int movieId;
    private String title;
    // private Cineplex[] cineplexes;
    // change array to arrayList for future operation
    private ArrayList<Cineplex> cineplexes;
    private String showingStatus;
    private String synopsis;
    private boolean support2D;
    private boolean support3D;
    private boolean isBlockbluster;
    private String director;
    // private String cast;
    // change String to arrayList of String for future operation
    private ArrayList<String> cast;
    private int numOfSales;
    private float overAllRating;
    private ArrayList<RevNRat> reviewsAndRating;

    public MovieInfo(int movieid, String title, String showingStatus, String synopsis, ArrayList<Cineplex> cineplexes, boolean support2D, boolean support3D, boolean isBlockbluster, String director, ArrayList<String> cast) {
        this.movieId = movieid;
        this.title = title;
        this.showingStatus = showingStatus;
        this.synopsis = synopsis;
        this.cineplexes = cineplexes;
        this.support2D = support2D;
        this.support3D = support3D;
        this.isBlockbluster = isBlockbluster;
        this.director = director;
        this.cast = cast;
        this.overAllRating = 0;
        this.numOfSales = 0;
        reviewsAndRating = new ArrayList<>();
    }

    public int getMovieId() {
        return movieId;
    }

    public void setMovieId(int movieId) {
        this.movieId = movieId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public ArrayList<Cineplex> getCineplexes() {
        return cineplexes;
    }

    public void setCineplexes(ArrayList<Cineplex> cineplexes) {
        this.cineplexes = cineplexes;
    }

    public String getShowingStatus() {
        return showingStatus;
    }

    public void setShowingStatus(String showingStatus) {
        this.showingStatus = showingStatus;
    }

    public String getSynopsis() {
        return synopsis;
    }

    public void setSynopsis(String synopsis) {
        this.synopsis = synopsis;
    }

    public boolean isSupport2D() {
        return support2D;
    }

    public void setSupport2D(boolean support2D) {
        this.support2D = support2D;
    }

    public boolean isSupport3D() {
        return support3D;
    }

    public void setSupport3D(boolean support3D) {
        this.support3D = support3D;
    }

    public boolean isBlockbluster() {
        return isBlockbluster;
    }

    public void setBlockbluster(boolean blockbluster) {
        isBlockbluster = blockbluster;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public ArrayList<String> getCast() {
        return cast;
    }

    public void setCast(ArrayList<String> cast) {
        this.cast = cast;
    }

    public int getNumOfSales() {
        return numOfSales;
    }

    public float getOverAllRating() {
        return overAllRating;
    }

    public ArrayList<RevNRat> getReviewsAndRating() {
        return reviewsAndRating;
    }

    public void addReviewRating(int userID) {
        Scanner s = new Scanner(System.in);
        System.out.println("Please input your comment: ");
        String review = s.nextLine();
        System.out.println("Please input your rating: (0-5) ");
        Float rating = Float.parseFloat(s.nextLine());
        RevNRat newReview = new RevNRat(userID, review, rating);
        this.overAllRating = ((this.overAllRating * this.reviewsAndRating.size()) + rating) / (this.reviewsAndRating.size() + 1);
        reviewsAndRating.add(newReview);
        System.out.println("Your review has been posted successfully!");
    }

    public void incrementSales() {
        this.numOfSales++;
    }


    public void displayMovieInfo() {
        StringBuilder temp = new StringBuilder();
        temp.append("Movie Title: ").append(getTitle());
        temp.append("Movie ID: ").append(getMovieId());
        temp.append("\nShowing Status: ").append(getShowingStatus());
        temp.append("\nSynoposis: ").append(getSynopsis());
        temp.append("\nAvailable types: " + "3D: ").append(isSupport3D()).append(" 2D: ").append(isSupport2D());
        temp.append("\nBlockbuster: ").append(isBlockbluster());
        temp.append("\nDirector: ").append(getDirector());

        for (int i = 0; i < getCast().size(); i++)
            temp.append(getCast().get(i));

        temp.append("\nNumber Of Sales: ").append(getNumOfSales());
        temp.append("\nOverall Rating: ").append(getOverAllRating());
        temp.append("\nReviews: ");
        System.out.println(temp);
        for (int i = 0; i < this.reviewsAndRating.size(); i++)
            reviewsAndRating.get(i).displayReviewRating();


        // original
//        String temp = "";
//        temp += "Movie Title: " + getTitle();
//        temp += "Movie ID: " + getMovieId();
//        temp += "\nShowing Status: " + getShowingStatus();
//        temp += "\nSynoposis: " + getSynopsis();
//        temp += "\nAvailable types: " + "3D: " + isSupport3D() + " 2D: " + isSupport2D();
//        temp += "\nBlockbuster: " + isBlockbluster();
//        temp += "\nDirector: " + getDirector();
//        temp += "\nCast: " + getCast(); //!!!
//        temp += "\nNumber Of Sales: " + getNumOfSales();
//        temp += "\nOverall Rating: " + getOverAllRating();
//        temp += "\nReviews: ";
//        System.out.println(temp);
//        for (int i = 0; i < this.reviewsAndRating.size(); i++)
//            reviewsAndRating.get(i).displayReviewRating();


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
            System.out.println("User: " + moviegoerID);
            System.out.println("Review:" + review);
            System.out.println("Rating: " + rating);
        }

    }
}
