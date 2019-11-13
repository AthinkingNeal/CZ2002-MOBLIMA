
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Scanner;

public class MovieInfo implements Serializable {
    private static final long serialVersionUID = 1L;
    private int movieId;
    private String title;
    // private Cineplex[] cineplexes;
    // change array to arrayList for future operation
    private ArrayList<Integer> cineplexes; // store a list of cineplexes ID
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
    private String ageLimit;
    private String movieCategory;

    public MovieInfo(int movieid, String title, String showingStatus, String synopsis, ArrayList<Integer> cineplexes, boolean support2D,
                     boolean support3D, boolean isBlockbluster, String director, ArrayList<String> cast, String ageLimit, String movieCategory) {
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
        this.ageLimit = ageLimit;
        this.movieCategory = movieCategory;
    }


    public String getAgeLimit() {
        return ageLimit;
    }

    public void setAgeLimit(String agelimit) {
        this.ageLimit = agelimit;
    }

    public String getMovieCategory() {
        return movieCategory;
    }

    public void setMovieCategory(String movieCategory) {
        this.movieCategory = movieCategory;
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

    public ArrayList<Integer> getCineplexes() {
        return cineplexes;
    }

    public boolean displayCineplexes(CineplexDB cineplexDB, String currentDate, String currentTime) {
        boolean available = false;
        for (int i = 0; i < cineplexes.size(); i++) {
            Cineplex c = cineplexDB.getCineplexByID(cineplexes.get(i));
            if (c.getMovieScheduleByID(this.getMovieId(), currentDate, currentTime).size() != 0) {
                System.out.println("Cineplex ID: " + c.getCineplexID() + " Name: " + c.getName() + " Location: " + c.getLocation());
                available = true;
            }
        }
        return available;
    }

    public void setCineplexes(ArrayList<Integer> cineplexes) {
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

    public void setNumOfSales(int numOfSales) {
        this.numOfSales = numOfSales;
    }

    public void setOverAllRating(float overAllRating) {
        this.overAllRating = overAllRating;
    }

    public void setReviewsAndRating(ArrayList<RevNRat> reviewsAndRating) {
        this.reviewsAndRating = reviewsAndRating;
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

        if (this.getReviewsAndRating().size() > 1) return overAllRating;
        else return -1;
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


    public void displayMovieInfo() {
        StringBuilder temp = new StringBuilder();
        temp.append("Movie Title: ").append(getTitle());
        temp.append("\nMovie ID: ").append(getMovieId());
        temp.append("\nMovie Category: ").append(getMovieCategory());
        temp.append("\nMovie Age Limit: ").append(getAgeLimit());
        temp.append("\nShowing Status: ").append(getShowingStatus());
        temp.append("\nSynoposis: ").append(getSynopsis());
        temp.append("\nAvailable types: " + "3D: ").append(isSupport3D()).append(" 2D: ").append(isSupport2D());
        temp.append("\nBlockbuster: ").append(isBlockbluster());
        temp.append("\nDirector: ").append(getDirector());

        temp.append("\nCast: ");
        for (int i = 0; i < getCast().size(); i++) {
            temp.append(getCast().get(i));
            temp.append("; ");
        }

        temp.append("\nNumber Of Sales: ").append(getNumOfSales());
        if (getOverAllRating() != -1)
            temp.append("\nOverall Rating: ").append(getOverAllRating());
        else
            temp.append("\nOverall Rating: N.A.");

        if (reviewsAndRating.size() == 0)
            temp.append("\nReviews: N.A.");
        else
            temp.append("\nReviews: ");
        System.out.println(temp);
        for (int i = 0; i < this.reviewsAndRating.size(); i++)
            reviewsAndRating.get(i).displayReviewRating();

    }


    private class RevNRat implements Serializable {
        private static final long serialVersionUID = 1L;
        private int moviegoerID;
        private String review;
        private float rating;

        public RevNRat(int id, String review, float rating) {
            this.moviegoerID = id;
            this.review = review;
            this.rating = rating;
        }

        public void displayReviewRating() {
            System.out.println("    MoviegoerID: " + moviegoerID);
            System.out.println("    Review: " + review);
            System.out.println("    Rating: " + rating + '\n');
        }
    }
}
