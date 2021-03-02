// Tests CovidDataset class
// reads daily cases and writes active cases

package comp1721.cwk1;

import java.io.IOException;

public class ActiveCases {
  public static void main(String[] args) {

    // requires 2 command line arguments
    if (args.length != 2) {
      // usage message
      System.err.println("Usage: java ActiveCases <filename> <filename>");
      // terminates program
      System.exit(2);
    }

    // creates new dataset
    CovidDataset dataset = new CovidDataset();
    
    try {
      // reads daily cases from first file
      dataset.readDailyCases(args[0]);
      // writes daily cases to second file
      dataset.writeActiveCases(args[1]);
      // outputs number of records in CovidDataset
      System.out.println("Number of records in the dataset: " + dataset.size());
    } catch (IOException error) {
      // outputs error message
      System.err.println("Error: cannot access files");
      // terminates program
      System.exit(2);
    } catch (Exception error) {
      // outputs error message
      System.err.println(error);
      // terminates program
      System.exit(2);
    }
  } // end of main
} // end of class ActiveCases
