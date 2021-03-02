// Class to represent a Covid Dataset
package comp1721.cwk1;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class CovidDataset {

  // private field to represent array of data
  private List<CaseRecord> data = new ArrayList<>();
  private String[] header;

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
    return null;
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
      header = reader.readLine().split(","); // reads header line
      while ((line = reader.readLine()) != null) {
        String[] fields = line.split(",");  
        LocalDate date = LocalDate.parse(fields[0]);
        int staffCases = Integer.parseInt(fields[1]);
        int studentCases = Integer.parseInt(fields[2]);
        int otherCases = Integer.parseInt(fields[3]);
        CaseRecord record = new CaseRecord(date, staffCases, studentCases, otherCases);
        data.add(record);
      }
    } catch (Exception e) {
      e.printStackTrace();
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
    try {
      writer = new BufferedWriter(new FileWriter(filename));
      sb.append(header);
      for (CaseRecord element : data) {
        sb.append(element);
        sb.append(",");
      }
      writer.write(sb.toString());
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      if (writer != null) writer.close();
    }
  }

} // end of class CovidDataset
