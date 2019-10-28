import java.io.*;
import java.util.ArrayList;

public abstract class Database {

    protected ArrayList<String> inputData;


    /**
     * read in content from the file and save it in Arraylist<String> so that subclasses can direcly read from the Arraylist
     *
     * @param filename
     */
    public Database(String filename) {
        inputData = new ArrayList<>();

        try {
            FileInputStream fis = new FileInputStream(filename);
            ObjectInputStream ois = new ObjectInputStream(fis);
            String record;
            while ((record = (String) ois.readObject()) != null) {
                System.out.print("reading data from " + filename + "...");
                inputData.add(record);
            }
            ois.close();
        } catch (IOException e) {
            System.out.println("File input error");
        } catch (ClassNotFoundException e) {
            System.out.println(e);
        }
    }

    public abstract void addRecord();

    public abstract void deleteRecord();

    public abstract void updateRecord();

    public abstract void getRecord();

    public abstract void displayAllRecord();

}
