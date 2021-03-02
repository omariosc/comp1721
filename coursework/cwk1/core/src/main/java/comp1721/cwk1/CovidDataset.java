// Class to represent a Covid Dataset
package comp1721.cwk1;

import java.time.LocalDate;

public class CovidDataset {

  public CovidDataset() {

  }

  /**
   * @return Size of the dataset
   */
  public int size() {
    return 0;
  }

  /**
   * @param index Specific index
   * @return CaseRecord given specific index
   */
  public CaseRecord getRecord(int index) {
    return null;
  }

  /**
   * Adds a record.
   * 
   * @param rec CaseRecord to add
   */
  public void addRecord(CaseRecord rec) {
    
  }

  /**
   * Retrieves case record given a specific day.
   * 
   * @param day Specific day
   * @return CaseRecord relating to specific dat
   */
  public CaseRecord dailyCasesOn(LocalDate day) {
    return null;
  }

  /**
   * Reads all cases in the file.
   * 
   * @param filename Name of file to read from
   */
  public void readDailyCases(String filename) {

  }

  /**
   * Writes all cases to the file
   * 
   * @param filename Name of file to write to
   */
  public void writeActiveCases(String filename) {

  }

} // end of class CovidDataset
