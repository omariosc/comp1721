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

  // private field to represent array of records
  private List<CaseRecord> data = new ArrayList<>();

  public CovidDataset() {

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
    if (index >= 0 && index < size()) { // validates index value
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
    for (CaseRecord element : data) {
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
  public void readDailyCases(String filename) throws IOException {
    BufferedReader reader = null;
    try {
      reader = new BufferedReader(new FileReader(filename));
      String line;
      String[] header = reader.readLine().split(","); // reads header line
      data.clear();
      while ((line = reader.readLine()) != null) {
        String[] fields = line.split(",");
        LocalDate date = LocalDate.parse(fields[0]);
        int staffCases = Integer.parseInt(fields[1]);
        int studentCases = Integer.parseInt(fields[2]);
        int otherCases = Integer.parseInt(fields[3]);
        CaseRecord record = new CaseRecord(date, staffCases, studentCases, otherCases);
        data.add(record);
      }
    } catch (FileNotFoundException exception) {
      throw new FileNotFoundException("Error: invalid file");
    } catch (Exception e) {
      throw new DatasetException("Error: invalid file");
    } finally {
      if (reader != null) reader.close();
    }
  }

  /**
   * Writes all cases to the file
   * 
   * @param filename Name of file to write to
   * @throws IOException
   */
  public void writeActiveCases(String filename) throws IOException {
    BufferedWriter writer = null;
    StringBuilder sb = new StringBuilder();
    int size = size();
    if (size < 11) {
      throw new DatasetException("Error: dataset too small");
    }
    try {
      writer = new BufferedWriter(new FileWriter(filename));
      sb.append("Date,Staff,Students,Other\n");
      int day = 0; // keeps track of the number of days
      // stores number of active cases
      int activeStaff = 0;
      int activeStudent = 0;
      int activeOther = 0;
      for (CaseRecord element : data) {
        LocalDate date = element.getDate();
        int staffCases = element.getStaffCases();
        int studentCases = element.getStudentCases();
        int otherCases = element.getOtherCases();
        String formatDate = date.toString();
        activeStaff += staffCases;
        activeStudent += studentCases;
        activeOther += otherCases;
        day++;
        if (day >= 10) {
          String values = String.format("%s,%d,%d,%d%n", formatDate, activeStaff, activeStudent, activeOther);
          sb.append(values);
          CaseRecord oldRecord = data.get(day-10);
          int oldStaff = oldRecord.getStaffCases();
          int oldStudent = oldRecord.getStudentCases();
          int oldOther = oldRecord.getOtherCases();
          activeStaff -= oldStaff;
          activeStudent -= oldStudent;
          activeOther -= oldOther;
        }
      }
      writer.write(sb.toString());
    } finally {
      if (writer != null) writer.close();
    }
  }

} // end of class CovidDataset
