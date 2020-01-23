import org.apache.log4j.Logger;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
* Given a collection of 5-digit ZIP code ranges
* (each range includes both their upper and lower bounds),
* This class produces the minimum number of ranges
* required to represent the same restrictions as the input.
* */
public class ZipCodeChallenge
{
    private final static Logger logger = Logger.getLogger(ZipCodeChallenge.class);

    public static void main(String[] args){
        List<int[]> input = new ArrayList<int[]>();

        /** Custom Test Input from file present in the root directory of the Project */
        String inputFile = "input.txt";

        // Read the zip code ranges from the input file
        logger.info("Reading Zip Code ranges from the input file: " + inputFile);
        BufferedReader objReader = null;

        try {
            String currentLine;
            objReader = new BufferedReader(new FileReader(inputFile));

            while ((currentLine = objReader.readLine()) != null){
                String[] zipcodes = currentLine.split(" ");
                for (String zipcodeRange : zipcodes){
                    int[] zipcode = new int[2];
                    zipcode[0] = Integer.valueOf(zipcodeRange.substring(zipcodeRange.indexOf("[") + 1, zipcodeRange.indexOf(",")));
                    zipcode[1] = Integer.valueOf(zipcodeRange.substring(zipcodeRange.indexOf(",") + 1, zipcodeRange.indexOf("]")));

                    // Store the zip code ranges in a List of Arrays
                    input.add(zipcode);
                }
            }

            // Find the minimum possible zip code ranges
            List<int[]> minRanges = getMinimumRanges(input);

            // Print the result list
            printOutput(minRanges);
        }
        catch (IOException exception){
            System.out.println("Exception: " + exception);
        }
        finally {
            try {
                if (objReader != null)
                    objReader.close();
            } catch (IOException exception) {
                exception.printStackTrace();
            }
        }
    }

    /**
    * Gets the Minimum number of Zip Code ranges required
    * to represent the same restrictions as the input
    * @param zipcodeRanges
    * @return An instance of {@link List}
    * */
    protected static List<int[]> getMinimumRanges(List<int[]> zipcodeRanges){
        logger.info("Finding Minimum possible Zip Code ranges");

        List<int[]> minRanges = new ArrayList<int[]>();

        // Nothing to minimize if length == 1 or null
        if(zipcodeRanges == null || zipcodeRanges.size() <= 1)
            return zipcodeRanges;

        // Sort intervals based on their lower bounds using a custom comparator
        Collections.sort(zipcodeRanges, new Comparator<int[]>() {
            public int compare(int[] o1, int[] o2) {
                return o1[0] - o2[0];
            }
        });

        int[] prev = zipcodeRanges.get(0);
        for (int[] currRange : zipcodeRanges){

            if(prev[1] >= currRange[0]){
                // Need to create a merged range
                int[] mergedRange = new int[]{prev[0], Math.max(prev[1], currRange[1])};
                prev = mergedRange;
            }
            else {
                // No need to create a merged range
                minRanges.add(prev);
                prev = currRange;
            }
        }

        // Add zip code range to the result list
        minRanges.add(prev);

        return minRanges;
    }

    /**
     * Print the result for calculated Minimum Zip Code ranges
     * @param minRanges
     */
    private static void printOutput(List<int[]> minRanges) {
        logger.info("Printing the Result");

        for (int[] eachRange : minRanges){
            System.out.print("[" + eachRange[0] + "," + eachRange[1] + "]");
            System.out.print("  ");
        }
    }

}
