import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.Buffer;


public class PriceTableGeneration {
    public static void main(String args[]) {
        String filename = "Pricetable.txt";
        PriceTable priceTable = new PriceTable(filename);

        //priceTable.initialiseTable();
//      priceTable.saveToFile();

        priceTable.displayContent();
        //priceTable.updatePriceTable();

        //priceTable.displayContent();
        priceTable.saveToFile();
    }
}