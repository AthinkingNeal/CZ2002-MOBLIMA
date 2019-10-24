import java.io.*;
import java.util.*;

public class MovieSheduleDB {

    // integer is the index that is used to identify the

    // create movieRecordID
    private Map<Integer, movieRecord> movieRecords = new HashMap<Integer, movieRecord>();

    /**
     * Initialise with empty movie records
     */
    public MovieSheduleDB() {
    }

    /**
     * Initialise with a single record
     *
     * @param record an instance of the movieRecord object
     */
    public MovieSheduleDB(movieRecord record) {
        addMovieRecord(record);
    }

    /**
     * Initialise with a file containing all the records info
     *
     * @param filename file containing records in the order (cineplexID, cinemaID, movieID, movieName, date, startTime, endTime)
     */
    public MovieSheduleDB(String filename) {
        addMovieRecords(filename);
    }


    public void addMovieRecords(int cineplexID, int cinemaID, int movieID, String movieName, String date, String startTime, String endTime) {
        this.movieRecords.put(movieRecords.size() + 1, new movieRecord(cineplexID, cinemaID, movieID, movieName, date, startTime, endTime));
    }

    public void addMovieRecord(movieRecord record) {
        movieRecords.put(movieRecords.size() + 1, record);
    }


    public void addMovieRecords(String fileName) {
        try {
            FileReader frStream = new FileReader(fileName);
            BufferedReader brStream = new BufferedReader(frStream);
            String inputLine;
            while ((inputLine = brStream.readLine()) != null) {

                String delims = "[ ]+";
                String[] tokens = inputLine.split(delims);
                this.movieRecords.put(movieRecords.size() + 1, new movieRecord(Integer.parseInt(tokens[0]), Integer.parseInt(tokens[1]),
                        Integer.parseInt(tokens[2]), tokens[3], tokens[4], tokens[5], tokens[6]));
            }
            brStream.close();
        } catch (FileNotFoundException e) {
            System.out.println("Error opening the input file!");
            System.exit(0);
        } catch (IOException e) {
            System.out.println("IO Error!");
            e.printStackTrace();
            System.exit(0);
        }
    }

    public void displayAllMovieRecords() {
        for (int i = 0; i < movieRecords.size(); i++) {
            System.out.println("Index: " + i + 1);
            movieRecords.get(i).displayMovieRecord();
        }
    }

    /**
     * modify the attributes in the record that has key [index]
     *
     * @param index record index that is used to search and identify the record
     */

    public void modifyMovieReord(int index) {
        movieRecord newRecord = movieRecords.get(index);
        System.out.println("Which attribute in the record would you like to change?\n"
                + "1. Change Movie ID\n" + "2. Change Cineplex ID\n" + "3. Change Cinema ID\n" + "4. Change date of screening\n"
                + "5. Change start time of screening\n" + "6. Change end time of screening\n" + "7. Quit\n" + "Please key in your option");
        Scanner s = new Scanner(System.in);
        int choice = s.nextInt();
        while (choice != 7) {
            switch (choice) {
                case 1:
                    System.out.println("Please key in the new movie ID");
                    int newMovieID = s.nextInt();
                    newRecord.setMovieID(newMovieID);
                    movieRecords.put(index, newRecord);
                    System.out.println("Changed!");
                case 2:
                    System.out.println("Please key in the new Cineplex ID");
                    int newCineplexID = s.nextInt();
                    newRecord.setCineplexID(newCineplexID);
                    movieRecords.put(index, newRecord);
                    System.out.println("Changed!");
                case 3:
                    System.out.println("Please key in the new Cinema ID");
                    int newCinemaID = s.nextInt();
                    newRecord.setCinemaID(newCinemaID);
                    movieRecords.put(index, newRecord);
                    System.out.println("Changed!");
                case 4:
                    System.out.println("Please key in the new date of screening");
                    String newdate = s.next();
                    newRecord.setDateTime(newdate);
                    movieRecords.put(index, newRecord);
                    System.out.println("Changed!");
                case 5:
                    System.out.println("Please key in the new start time");
                    String starttime = s.next();
                    newRecord.setStartTime(starttime);
                    movieRecords.put(index, newRecord);
                    System.out.println("Changed!");
                case 6:
                    System.out.println("Please key in the new end time");
                    String end = s.next();
                    newRecord.setEndTime(end);
                    movieRecords.put(index, newRecord);
                    System.out.println("Changed!");
                default:
                    break;
            }
            System.out.println("Please key in your option:");
            choice = s.nextInt();
        }

    }

    public void removeMovieReord(int index) {
        movieRecords.remove(index);
        System.out.println("Successfullly removed!");
    }

    public ArrayList<movieRecord> findAllRecordsByMovieName(String moviename) {
        ArrayList<movieRecord> records = new ArrayList<movieRecord>();
        for (int i = 0; i < movieRecords.size(); i++) {
            if (movieRecords.get(i).getMovieName() == moviename)
                records.add(movieRecords.get(i));
        }
        return records;
    }

    public ArrayList<movieRecord> findAllRecordsByMovieName(ArrayList<movieRecord> searchSpace, String moviename) {
        ArrayList<movieRecord> records = new ArrayList<movieRecord>();

        for (int i = 0; i < searchSpace.size(); i++) {
            if (searchSpace.get(i).getMovieName() == moviename)
                records.add(searchSpace.get(i));
        }
        return records;
    }

    public ArrayList<movieRecord> findAllRecordsByMovieID(int movieID) {
        ArrayList<movieRecord> records = new ArrayList<movieRecord>();
        for (int i = 0; i < movieRecords.size(); i++) {
            if (movieRecords.get(i).getMovieID() == movieID)
                records.add(movieRecords.get(i));
        }
        return records;
    }

    public ArrayList<movieRecord> findAllRecordsByMovieID(ArrayList<movieRecord> searchSpace, int movieID) {
        ArrayList<movieRecord> records = new ArrayList<movieRecord>();

        for (int i = 0; i < searchSpace.size(); i++) {
            if (searchSpace.get(i).getMovieID() == movieID)
                records.add(searchSpace.get(i));
        }
        return records;
    }


    public ArrayList<movieRecord> findAllRecordsByCinemaID(int cinemaID) {
        ArrayList<movieRecord> records = new ArrayList<movieRecord>();
        for (int i = 0; i < movieRecords.size(); i++) {
            if (movieRecords.get(i).getCinemaID() == cinemaID)
                records.add(movieRecords.get(i));
        }
        return records;
    }

    public ArrayList<movieRecord> findAllRecordsByCinemaID(ArrayList<movieRecord> searchSpace, int cinemaID) {
        ArrayList<movieRecord> records = new ArrayList<movieRecord>();

        for (int i = 0; i < searchSpace.size(); i++) {
            if (searchSpace.get(i).getCinemaID() == cinemaID)
                records.add(searchSpace.get(i));
        }
        return records;
    }

    public ArrayList<movieRecord> findAllRecordsByCineplexID(int cineplexID) {
        ArrayList<movieRecord> records = new ArrayList<movieRecord>();
        for (int i = 0; i < movieRecords.size(); i++) {
            if (movieRecords.get(i).getCineplexID() == cineplexID)
                records.add(movieRecords.get(i));
        }
        return records;
    }

    public ArrayList<movieRecord> findAllRecordsByCineplexID(ArrayList<movieRecord> searchSpace, int cineplexID) {
        ArrayList<movieRecord> records = new ArrayList<movieRecord>();

        for (int i = 0; i < searchSpace.size(); i++) {
            if (searchSpace.get(i).getCineplexID() == cineplexID)
                records.add(searchSpace.get(i));
        }
        return records;
    }

    public ArrayList<movieRecord> findAllRecordsByDate(String date) {
        ArrayList<movieRecord> records = new ArrayList<movieRecord>();
        for (int i = 0; i < movieRecords.size(); i++) {
            if (movieRecords.get(i).getDateTime() == date)
                records.add(movieRecords.get(i));
        }
        return records;
    }

    public ArrayList<movieRecord> findAllRecordsByDate(ArrayList<movieRecord> searchSpace, String date) {
        ArrayList<movieRecord> records = new ArrayList<movieRecord>();

        for (int i = 0; i < searchSpace.size(); i++) {
            if (searchSpace.get(i).getDateTime() == date)
                records.add(searchSpace.get(i));
        }
        return records;
    }

    public ArrayList<movieRecord> findAllRecordsByStarttime(String starttime) {
        ArrayList<movieRecord> records = new ArrayList<movieRecord>();
        for (int i = 0; i < movieRecords.size(); i++) {
            if (movieRecords.get(i).getStartTime() == starttime)
                records.add(movieRecords.get(i));
        }
        return records;
    }

    public ArrayList<movieRecord> findAllRecordsByStarttime(ArrayList<movieRecord> searchSpace, String starttime) {
        ArrayList<movieRecord> records = new ArrayList<movieRecord>();

        for (int i = 0; i < searchSpace.size(); i++) {
            if (searchSpace.get(i).getStartTime() == starttime)
                records.add(searchSpace.get(i));
        }
        return records;
    }

    public ArrayList<movieRecord> findAllRecordsByEndtime(String endtime) {
        ArrayList<movieRecord> records = new ArrayList<movieRecord>();
        for (int i = 0; i < movieRecords.size(); i++) {
            if (movieRecords.get(i).getEndTime() == endtime)
                records.add(movieRecords.get(i));
        }
        return records;
    }

    public ArrayList<movieRecord> findAllRecordsByEndtime(ArrayList<movieRecord> searchSpace, String endtime) {
        ArrayList<movieRecord> records = new ArrayList<movieRecord>();

        for (int i = 0; i < searchSpace.size(); i++) {
            if (searchSpace.get(i).getEndTime() == endtime)
                records.add(searchSpace.get(i));
        }
        return records;
    }


}
