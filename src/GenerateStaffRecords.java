
import java.io.*;
public class GenerateStaffRecords {
    private static final String ALPHA_NUMERIC_STRING = "abcdefghijklmnopqrskuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
    public static int numRecords = 10;

    /**
     * generate a random alphanumeric string as ID/password
     * @param count is the length of the random string
     */
    public static String generateRandomString(int count)
    {
        StringBuilder builder = new StringBuilder();
        while (count-- != 0) {
            int character = (int)(Math.random()*ALPHA_NUMERIC_STRING.length());
            builder.append(ALPHA_NUMERIC_STRING.charAt(character));
        }
        return builder.toString();
    }


    public static void main( String[] args ) {
        try {
            FileWriter     fwStream = new FileWriter(    "staffRecords.txt" );
            BufferedWriter bwStream = new BufferedWriter( fwStream  );
            PrintWriter    pwStream = new PrintWriter(    bwStream  );
            for ( int i = 0 ; i < numRecords ; i++)
                pwStream.println( generateRandomString(8) +"  "+ generateRandomString(8) );
            pwStream.close();
        }
        catch ( IOException e ) {
            System.out.println( "IO Error!" + e.getMessage() );
            e.printStackTrace();
            System.exit( 0 );
        }
    }
}

