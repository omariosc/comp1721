// Class represents a Case Record
// Composition with CovidDataset

package comp1721.cwk1;

import java.time.LocalDate;
import java.time.format.*;

public class CaseRecord {
  
  // private fields to represent date and number of cases for case
  private LocalDate date;
  private int staffCases;
  private int studentCases;
  private int otherCases;

  /**
   * Creates a Case Record using date and cases for staff, student and others.
   *
   * @param d Value for date of case
   * @param staff Value for number of staff cases
   * @param student Value for number of student cases
   * @param other Value for number of other cases
   */
  public CaseRecord(LocalDate d, int staff, int student, int other) {
    
  }

  /**
   * @return Date component of case record
   */
  public LocalDate getDate() {
    return date;
  }

  /**
   * @return Staff cases component of case record
   */
  public int getStaffCases() {
    return staffCases;
  }

  /**
   * @return Student cases component of case record
   */
  public int getStudentCases() {
    return studentCases;
  }

  /**
   * @return Other cases component of case record
   */
  public int getOtherCases() {
    return otherCases;
  }
  
  /**
   * @return Total number of cases
   */
  public int totalCases() {
    return staffCases + studentCases + otherCases;
  }
  
  /**
   * Generates a string representation of this case.
   *
   * <p>The format is YYYY-MM-DD - i.e., 4 digits for year, 2 digits for month and day
   * with dashes as separators.</p>
   *
   * @return Case record as a string
   */
  @Override
  public String toString() {
    date.format(DateTimeFormatter.ofPattern("yyyy-mm-dd"));
    return String.format("%s: %d staff, %d students, %d other", date, staffCases, studentCases, otherCases);
  }

} // end of class CaseRecord
