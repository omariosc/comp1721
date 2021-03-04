// Class represents a Case Record
// Composition with CovidDataset

package comp1721.cwk1;

import java.time.LocalDate;

public class CaseRecord {
  
  // private fields to represent date and number of cases for case
  private LocalDate date;
  private int staffCases;
  private int studentCases;
  private int otherCases;

  // private fields used for CovidChart
  private int dayOfYear;
  private int totalActiveCases;

  /**
   * Creates a Case Record using date and cases for staff, student and others.
   *
   * @param d Value for date of case
   * @param staff Value for number of staff cases
   * @param student Value for number of student cases
   * @param other Value for number of other cases
   */
  public CaseRecord(LocalDate d, int staff, int student, int other) {
    setDate(d);
    setStaffCases(staff);
    setStudentCases(student);
    setOtherCases(other);
  }

  /**
   * Creates a case record using day of the year for the CovidChart class
   * 
   * @param d Value for date of the year of case
   * @param cases Value for total number of cases
   */
  public CaseRecord(int d, int cases) {
    this.dayOfYear = d;
    this.totalActiveCases = cases;
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
   * @return Day of the Year component of case record for CovidChart
   */
  public int getDayOfYear() {
    return dayOfYear;
  }

  /**
   * @return Total number of cases
   */
  public int getTotalActiveCases() {
    return totalActiveCases;
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
    return String.format("%s: %d staff, %d students, %d other", 
    formatDate, staffCases, studentCases, otherCases);
  }

  // Private helper methods

  private void setDate(LocalDate d) {
    this.date = d;
  }
  
  private void setStaffCases(int staff) {
    // validates number of staff cases
    if (staff >= 0) {
      this.staffCases = staff;
    } else {
      throw new DatasetException("Error: staff cases should be >= 0");
    }
  }

  private void setStudentCases(int student) {
    // validates number of student cases
    if (student >= 0) {
      this.studentCases = student;
    } else {
      throw new DatasetException("Error: student cases should be >= 0");
    }   
  }

  private void setOtherCases(int other) {
    // validates number of other cases
    if (other >= 0) {
      this.otherCases = other;
    } else {
      throw new DatasetException("Error: other cases should be >= 0");
    }
  }

} // end of class CaseRecord
