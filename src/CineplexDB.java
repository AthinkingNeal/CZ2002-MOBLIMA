
import java.util.HashMap;
import java.util.Map;

public class CineplexDB extends Database {
    private static Map<Integer, Cineplex> CineplexMap = new HashMap<Integer, Cineplex>();


    public void addRecord() {
    }

    public void deleteRecord() {
    }

    public void updateRecord() {
    }

    public void saveToFile() {
    }

    public static boolean findCineplexByID(Integer cineplexID) {

        if (CineplexMap.containsKey(cineplexID))
            return true;
        else
            return false;
    }

    public static Cineplex getCineplexByID(Integer cineplexID) {

        if (findCineplexByID(cineplexID))
            return CineplexMap.get(cineplexID);
        else return null;

    }

}
