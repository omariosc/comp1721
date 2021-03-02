// Class represents a Case Record
// Composition with CovidDataset

package comp1721.cwk1;

import java.time.LocalDate;

public class CaseRecord extends CovidDataset {
  
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
  public CaseRecord(LocalDate date, int staffCases, int studentCases, int otherCases) {
    setDate(date);
    setStaffCases(staffCases);
    setStudentCases(studentCases);
    setOtherCases(otherCases);
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
    String formatDate = date.toString();
    return String.format("%s: %d staff, %d students, %d other", formatDate, staffCases, studentCases, otherCases);
  }

  // Private helper methods

  public void setDate(LocalDate date) {
    this.date = date;
  }
  

  public void setStaffCases(int staffCases) {
    if (staffCases >= 0) {
      this.staffCases = staffCases;
    } else {
      throw new DatasetException("Error: staff cases should be >= 0");
    }
  }

  public void setStudentCases(int studentCases) {
    if (studentCases >= 0) {
      this.studentCases = studentCases;
    } else {
      throw new DatasetException("Error: student cases should be >= 0");
    }   
  }

  public void setOtherCases(int otherCases) {
    if (otherCases >= 0) {
      this.otherCases = otherCases;
    } else {
      throw new DatasetException("Error: other cases should be >= 0");
    }
  }

} // end of class CaseRecord
