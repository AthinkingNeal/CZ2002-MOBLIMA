import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class MovieInfoDB extends Database {
    // key: movieID, value: MovieInfo object
    private Map<Integer, MovieInfo> movieInfoRecord = new HashMap<Integer, MovieInfo>();
    private String filename;

    /**
     * Initiate the class using a txt file to populate movieInfo
     */
    public MovieInfoDB(String fileName) {
        this.filename = filename;
        try {
            FileInputStream fis = new FileInputStream(filename);
            ObjectInputStream ois = new ObjectInputStream(fis);

            System.out.print("reading data from " + filename + "...");
            this.movieInfoRecord = (Map<Integer, MovieInfo>) ois.readObject();
            ois.close();
        } catch (IOException e) {
            System.out.println("File input error");
        } catch (ClassNotFoundException e) {
            System.out.println(e);
        }
    }

    /**
     * Enable adding of a new movieInfo record;
     * new movieID must be different than existing movieIDs;
     */
    public void addRecord() {
        Scanner sc = new Scanner(System.in);
        Integer movieId = -1;

        // add movie ID
        System.out.println("Please enter a new movie ID: ");
        do {
            if (sc.hasNextInt()) {
                if (movieInfoRecord.containsKey(sc.nextInt()))
                    System.out.println("Movie ID already exists! Please enter a new movie ID");
                else {
                    movieId = sc.nextInt();
                    break;
                }
            } else
                System.out.println("Invalid movie ID. Please try again.");
        } while (true);

        // add movie title
        System.out.println("Please enter movie title: ");
        String movieTitle = sc.next();

        // add cineplexes showing the movie
        System.out.println("Please enter the number of cineplexes for this movie: ");
        int noCine = sc.nextInt();
        // Cineplex[] cineplexes = new Cineplex[noCine];
        ArrayList<Cineplex> cineplexes = new ArrayList<Cineplex>();
        for (int i = 0; i < noCine; i++) {
            do {
                System.out.println("Please enter cineplex ID " + i + 1);
                if (CineplexDB.findCineplexByID(sc.nextInt())) {
                    // cineplexes[i] = CineplexDB.CineplexMap.get(sc.nextInt());
                    cineplexes.add(CineplexDB.getCineplexByID(sc.nextInt()));
                    break;
                } else
                    System.out.println("Cinexplex ID does not exist");
            } while (true);
        }

        // add showing status for the movie
        System.out.println("Please enter showing status for the new movie: ");
        String showingStatus = sc.next();

        // add synopsis for the movie
        System.out.println("Please enter synopsis for the movie: ");
        String synopsis = sc.next();

        // check whether 2d supported
        boolean support2D;
        do {
            System.out.println("Please indicate whether this movie is available in 2D (Y/N): ");
            if (sc.next().charAt(0) == 'Y' || sc.next().charAt(0) == 'y') {
                support2D = true;
                break;
            } else if (sc.next().charAt(0) == 'N' || sc.next().charAt(0) == 'n') {
                support2D = false;
                break;
            } else
                System.out.println("Invalid input, please try again. ");
        } while (true);

        // check whether 2d supported
        boolean support3D;
        do {
            System.out.println("Please indicate whether this movie is available in 3D (Y/N): ");
            if (sc.next().charAt(0) == 'Y' || sc.next().charAt(0) == 'y') {
                support3D = true;
                break;
            } else if (sc.next().charAt(0) == 'N' || sc.next().charAt(0) == 'n') {
                support3D = false;
                break;
            } else
                System.out.println("Invalid input, please try again. ");
        } while (true);

        // check if the movie is a blockbuster
        boolean isBlockbuster;
        do {
            System.out.println("Please indicate whether this movie is a blockbuster (Y/N): ");
            if (sc.next().charAt(0) == 'Y' || sc.next().charAt(0) == 'y') {
                isBlockbuster = true;
                break;
            } else if (sc.next().charAt(0) == 'N' || sc.next().charAt(0) == 'n') {
                isBlockbuster = false;
                break;
            } else
                System.out.println("Invalid input, please try again. ");
        } while (true);

        // add director for the movie
        System.out.println("Enter the name of the director for this movie: ");
        String director = sc.next();

        // add cast for the movie
        ArrayList<String> cast = new ArrayList<String>();
        do {
            System.out.println("Please enter the number of cast for this movie");
            if (sc.nextInt() < 3) {
                System.out.println("You must enter at least 3 cast for the movie");
                continue;
            }
            int noCast = sc.nextInt();
            // String[] cast = new String[noCast];
            for (int i = 0; i < noCast; i++) {
                System.out.println("Enter the name of cast" + i + 1);
                cast.add(sc.next());
            }
            break;
        } while (true);

        // note better change cast to type of String[] rather than String
        MovieInfo newMovie = new MovieInfo(movieId, movieTitle, showingStatus, synopsis, cineplexes, support2D, support3D,
                isBlockbuster, director, cast);
        movieInfoRecord.put(movieId, newMovie);
        System.out.println("New movie info successfully added! ");
    }

    /*
     * Enable update a record
     * allow staff to update
     * */
    public void updateRecord() {
        Scanner sc = new Scanner(System.in);
        Integer movieId;
        do {
            System.out.println("Please enter the movieID for the movie to be updated");
            movieId = sc.nextInt();
            if (!movieInfoRecord.containsKey(movieId))
                System.out.println("Sorry no such movie ID exist. Please enter again");
            else
                break;
        } while (true);
        System.out.println("Please enter your choice: ");
        System.out.println("1. Update movie ID");
        System.out.println("2. Update movie title");
        System.out.println("3. Update movie showing status");
        System.out.println("4. Update 2D support status for the movie");
        System.out.println("5. Update 3D support status for the movie");
        System.out.println("6. Update blockbuster status for the movie");
        System.out.println("7. Update cineplexes showing the movie");
        System.out.println("8. Update synopsis for the movie");
        System.out.println("9. Update director for the movie");
        System.out.println("10. Update cast for the movie");
        System.out.println("11. Quit");

        int choice = sc.nextInt();

        switch (choice) {
            case 1:
                System.out.println("Enter new movieID for the movie: ");
                Integer newID = sc.nextInt();
                MovieInfo temp = movieInfoRecord.get(movieId);
                temp.setMovieId(newID);
                movieInfoRecord.remove(movieId);
                movieInfoRecord.put(newID, temp);
                break;
            case 2:
                System.out.println("Enter new title for the movie: ");
                String newTitle = sc.next();
                movieInfoRecord.get(movieId).setTitle(newTitle);
                break;
            case 3:
                System.out.println("Enter new showing status for the movie: ");
                String newStatus = sc.next();
                movieInfoRecord.get(movieId).setShowingStatus(newStatus);
                break;
            case 4:
                System.out.println("Enter new 2D support for the movie (Y/N): ");
                if (sc.next().charAt(0) == 'Y' || sc.next().charAt(0) == 'y')
                    movieInfoRecord.get(movieId).setSupport2D(true);
                else
                    movieInfoRecord.get(movieId).setSupport2D(false);
                break;
            case 5:
                System.out.println("Enter new 3D support for the movie (Y/N): ");
                if (sc.next().charAt(0) == 'Y' || sc.next().charAt(0) == 'y')
                    movieInfoRecord.get(movieId).setSupport3D(true);
                else
                    movieInfoRecord.get(movieId).setSupport3D(false);
                break;
            case 6:
                System.out.println("Enter new blockbuster status for the movie: ");
                if (sc.next().charAt(0) == 'Y' || sc.next().charAt(0) == 'y')
                    movieInfoRecord.get(movieId).setBlockbluster(true);
                else
                    movieInfoRecord.get(movieId).setBlockbluster(false);
                break;
            case 7: // cineplex
                System.out.println("Please enter your choice");
                System.out.println("1. Remove a cineplex showing this movie");
                System.out.println("2. Add a cineplex showing this movie");
                System.out.println("3. Change a cineplex with another");
                choice = sc.nextInt();
                // Cineplex[] cineplexes = movieInfoRecord.get(movieId).getCineplexes();
                ArrayList<Cineplex> cineplexes = movieInfoRecord.get(movieId).getCineplexes();
                int size = cineplexes.size();

                System.out.println("Current cineplexes for movieID" + movieId);
                for (int i = 0; i < cineplexes.size(); i++)
                    System.out.println(cineplexes.get(i).getCineplexID());

                // remove a cineplex showing the movie
                if (choice == 1) {
                    System.out.println("Please enter the cineplexID you want to remove: ");
                    int i = 0;
                    if (sc.hasNextInt()) {
                        for (i = 0; i < cineplexes.size(); i++) {
                            if (cineplexes.get(i).getCineplexID() == sc.nextInt()) {
                                cineplexes.remove(i);
                                System.out.println("Cineplex successfully removed!");
                                break;
                            }
                        }
                        if (i == size) System.out.println("Cineplex to be removed does not exist");
                    } else {
                        System.out.println("Invalid input. Please try again.");
                    }


                    // add a cineplex showing the movie
                } else if (choice == 2) {
                    System.out.println("Please enter the cineplexID you want to add: ");
                    int i = 0;
                    if (sc.hasNextInt()) {
                        int newCine = sc.nextInt();
                        for (i = 0; i < cineplexes.size(); i++) {
                            if (cineplexes.get(i).getCineplexID() == newCine) {
                                System.out.println("Cineplex already exists!");
                                break;
                            }
                        }
                        if (i == cineplexes.size()) {
                            if (CineplexDB.findCineplexByID(newCine)) {
                                cineplexes.add(CineplexDB.getCineplexByID(newCine));
                                System.out.println("Cineplex successfully added!");
                            } else System.out.println("No Cineplex with ID" + newCine);
                        }
                    } else System.out.println("Invalid input. Please try again.");

                } else {
                    // change a cineplex for another
                    int i;
                    int sub;
                    System.out.println("Please enter the CineplexID to be replaced: ");

                    if (sc.hasNextInt()) {
                        for (i = 0; i < cineplexes.size(); i++)
                            if (cineplexes.get(i).getCineplexID() == sc.nextInt()) // to-be-replaced exists
                                break;
                        if (i == cineplexes.size()) {
                            System.out.println("Cineplex to be replaced does not exist");
                            break;
                        } else {
                            System.out.println("Enter new cineplexID");
                            if (sc.hasNextInt()) {
                                sub = sc.nextInt();
                                if (CineplexDB.findCineplexByID(sub))
                                    cineplexes.set(i, CineplexDB.getCineplexByID(sub));
                            } else System.out.println("Invalid input.");
                        }
                    } else {
                        System.out.println("Invalid input. Please try again.");
                    }
                }
                movieInfoRecord.get(movieId).setCineplexes(cineplexes);
                break;
            case 8:
                System.out.println("Enter new synopsys for the movie");
                String newSyn = sc.next();
                movieInfoRecord.get(movieId).setSynopsis(newSyn);
                break;
            case 9:
                System.out.println("Enter new director for the movie");
                String newDir = sc.next();
                movieInfoRecord.get(movieId).setDirector(newDir);
                break;
            case 10:
                System.out.println("Please enter your choice");
                System.out.println("1. Remove a cast member for this movie");
                System.out.println("2. Add a cast member for this movie");
                System.out.println("3. Change a cast with another");
                choice = sc.nextInt();
                int i;
                String name;
                ArrayList<String> cast = movieInfoRecord.get(movieId).getCast();
                int sizeCast = cast.size();
                // remove a cast member for this movie
                if (choice == 1) {
                    System.out.println("Enter the cast name to be removed: ");
                    name = sc.next();
                    for (i = 0; i < cast.size(); i++) {
                        if (name == cast.get(i)) {
                            cast.remove(i);
                            break;
                        }
                    }
                    if (i == sizeCast) System.out.println("Cast to be removed does not exist");

                    // add a cast member for this movie
                } else if (choice == 1) {
                    System.out.println("Enter the cast name to be added: ");
                    name = sc.next();
                    for (i = 0; i < cast.size(); i++) {
                        if (name == cast.get(i)) {
                            System.out.println("Cast already exists!");
                            break;
                        }
                    }
                    if (i == cast.size())
                        cast.add(name);

                    // change a cast member with another
                } else {
                    System.out.println("Enter the cast member to be replaced");
                    name = sc.next();
                    for (i = 0; i < cast.size(); i++) {
                        if (name == cast.get(i))
                            break;
                    }
                    if (i == cast.size()) System.out.println("Cast does not exist");
                    else {
                        System.out.println("Please enter new cast member");
                        cast.set(i, sc.next());
                    }
                }
                movieInfoRecord.get(movieId).setCast(cast);
                break;
            case 11:
                break;
        }
    }

    /**
     * enable staff to delete movieInfo record
     */
    public void deleteRecord() {
        Scanner sc = new Scanner(System.in);
        Integer movieId;
        do {
            System.out.println("Please enter the movieID for the movie to be deleted");
            movieId = sc.nextInt();
            if (!movieInfoRecord.containsKey(movieId))
                System.out.println("Sorry no such movie ID exist. Please enter again");
            else
                break;
        } while (true);
        movieInfoRecord.remove(movieId);
    }

    /**
     * save to file after modifying the database
     */

    public void saveToFile() {
        try {
            FileOutputStream fos = new FileOutputStream(this.filename);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            System.out.print("saving data to " + filename + "...");
            oos.writeObject(movieInfoRecord);
            oos.close();
        } catch (IOException e) {
            System.out.println("File input error");
        }
    }

}
