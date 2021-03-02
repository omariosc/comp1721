// Class that reads COVID case data from a CSV file
// Draws a line chart showing how the total number of active cases changes over time

package comp1721.cwk1;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.stage.Stage;

public class CovidChart {

  // private object to represent data set
  private static CovidDataset dataset = new CovidDataset();

  // private list to represent array of active cases
  private static List<CaseRecord> activeData = new ArrayList<>();
  

  public static void main(String[] args) {

    // requires a command line argument
    if (args.length != 1) {
      // usage message
      System.err.println("Usage: java CovidChart <filename>");
      // terminates program
      System.exit(2);
    }

    try {
      // reads daily cases from file
      dataset.readDailyCases(args[0]);
      loadActiveCases();
      createLineChart();
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
  }

  /**
   * Calculates all active cases Stores active cases in activeData list
   * Implemented similar to data list in CovidDataset Uses same method for
   * calculating active cases as writeActiveCases
   */
  private static void loadActiveCases() {

    // retrieves size of data list
    int size = dataset.size();
    if (size < 11) {
      throw new DatasetException("Error: dataset too small");
    }

    // keeps track of the number of days
    int day = 0;

    // stores number of active cases
    int activeCases = 0;

    // iterates through every CaseRecord element in the data list
    for (CaseRecord element : dataset.getData()) {

      // retrives all values for the CaseRecord
      LocalDate date = element.getDate();
      int staffCases = element.getStaffCases();
      int studentCases = element.getStudentCases();
      int otherCases = element.getOtherCases();

      // adds the number of cases to the active count
      activeCases += staffCases + studentCases + otherCases;
      
      // converts date to day of the year
      int dayOfYear = date.getDayOfYear();
      
      // increments the day
      day++;

      // active cases must be at least for 10 days
      if (day >= 10) {
        // adds new record to active data list
        CaseRecord record = new CaseRecord(dayOfYear, activeCases);
        activeData.add(record);

        // subtracts the oldest record in the active cases count
        CaseRecord oldRecord = dataset.getData().get(day - 10);
        int oldStaff = oldRecord.getStaffCases();
        int oldStudent = oldRecord.getStudentCases();
        int oldOther = oldRecord.getOtherCases();
        activeCases -= oldStaff - oldStudent - oldOther;
      }
    }
  }

  /**
   * Creates a line chart based on activeData Active cases stored on y-axis Day of
   * the year stored on x-axis
   */
  private static void createLineChart() {
    
  }

} // end of class CovidChart