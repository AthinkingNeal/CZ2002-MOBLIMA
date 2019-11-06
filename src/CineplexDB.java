
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
        for (Map.Entry<Integer, Cineplex> entry : CineplexMap.entrySet()) {
            if (entry.getValue().getCineplexID() == cineplexID) {
                // System.out.println("The movie goer is found!");
                return true;
            }
        }
        return false;
    }

    public static Cineplex getCineplexByID(Integer cineplexID) {
        for (Map.Entry<Integer, Cineplex> entry : CineplexMap.entrySet()) {
            if (entry.getValue().getCineplexID() == cineplexID) {
                // System.out.println("The movie goer is found!");
                return entry.getValue();
            }
        }
        return null;
    }

}
