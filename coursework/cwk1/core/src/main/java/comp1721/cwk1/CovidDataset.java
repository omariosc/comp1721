// Class to represent a Covid Dataset
package comp1721.cwk1;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class CovidDataset {

  // private list to represent array of records
  private List<CaseRecord> data = new ArrayList<>();

  public CovidDataset() {
    // intialised data set as array outside constructor
  }

  /**
   * @return data for use in CovidChat
   */
  public List<CaseRecord> getData() {
    return data;
  }

  /**
   * @return Size of the dataset
   */
  public int size() {
    return data.size();
  }

  /**
   * Returns a CaseRecord object stored at the given position index.
   * 
   * @param index Specific index
   * @return CaseRecord given specific index
   * @throws DatasetException
   */
  public CaseRecord getRecord(int index) throws DatasetException {
    // validates index value
    if (index >= 0 && index < size()) {
      return data.get(index);
    }
    else {
      throw new DatasetException("Error: index is invalid");
    }
  }

  /**
   * Appends given CaseRecord object to end of current sequence of stored records.
   * 
   * @param rec CaseRecord to add
   */
  public void addRecord(CaseRecord rec) {
    data.add(rec);
  }

  /**
   * Retrieves case record given a specific day.
   * 
   * @param day Specific day
   * @return CaseRecord relating to specific day
   * @throws DatasetException
   */
  public CaseRecord dailyCasesOn(LocalDate day) throws DatasetException {
    // iterates through records in data list
    for (CaseRecord element : data) {
      // if date matches with specified day
      if (element.getDate() == day) {
        return element;    
      }
    }
    // if no element was found
    throw new DatasetException("Error: record not found");
  }

  /**
   * Reads all cases in the file.
   * 
   * @param filename Name of file to read from
   * @throws IOException
   */
  public void readDailyCases(String filename) throws FileNotFoundException, DatasetException {
    try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
      String line;

      // reads header line
      String[] header = reader.readLine().split(",");

      // clears the data list
      data.clear();
      
      // reads through every line
      while ((line = reader.readLine()) != null) {
        // splits on a comma delimiter
        String[] fields = line.split(",");

        // stores all fields as values for the CaseRecord
        LocalDate date = LocalDate.parse(fields[0]);
        int staffCases = Integer.parseInt(fields[1]);
        int studentCases = Integer.parseInt(fields[2]);
        int otherCases = Integer.parseInt(fields[3]);
        CaseRecord record = new CaseRecord(date, staffCases, studentCases, otherCases);
        data.add(record);
      }

    // exception handling 
    } catch (FileNotFoundException exception) {
      throw new FileNotFoundException("Error: invalid file");
    } catch (Exception e) {
      throw new DatasetException("Error: invalid data set");
    }
  }

  /**
   * Writes all cases to the file
   * 
   * @param filename Name of file to write to
   * @throws IOException
   */
  public void writeActiveCases(String filename) throws IOException {
    
    // used to build string to store in csv file
    StringBuilder sb = new StringBuilder();
    
    // retrieves size of data list
    int size = size();
    if (size < 11) {
      throw new DatasetException("Error: dataset too small");
    }
    
    // attempts to write into csv file
    try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))){
      sb.append("Date,Staff,Students,Other\n");
      
      // keeps track of the number of days
      int day = 0;
      
      // stores number of active cases
      int activeStaff = 0;
      int activeStudent = 0;
      int activeOther = 0;
      
      // iterates through every CaseRecord element in the data list
      for (CaseRecord element : data) {

        // retrives all values for the CaseRecord
        LocalDate date = element.getDate();
        int staffCases = element.getStaffCases();
        int studentCases = element.getStudentCases();
        int otherCases = element.getOtherCases();
        String formatDate = date.toString();

        // adds the the number of cases to the active count
        activeStaff += staffCases;
        activeStudent += studentCases;
        activeOther += otherCases;

        // increments the day
        day++;

        // active cases must be at least for 10 days
        if (day >= 10) {
          String values = String.format("%s,%d,%d,%d%n",
          formatDate, activeStaff, activeStudent, activeOther);
          sb.append(values);

          // subtracts the oldest record in the active cases count
          CaseRecord oldRecord = data.get(day-10);
          int oldStaff = oldRecord.getStaffCases();
          int oldStudent = oldRecord.getStudentCases();
          int oldOther = oldRecord.getOtherCases();
          activeStaff -= oldStaff;
          activeStudent -= oldStudent;
          activeOther -= oldOther;
        }
      }
      // writes string to file
      writer.write(sb.toString());
    }
  }

} // end of class CovidDataset
