// Tests class Rectangle
// Creates 2 Rectangle objects
// One default, one specific object

import java.util.Scanner;

public class RectTest {
  public static void main(String[] args) {
    Scanner input = new Scanner(System.in); // creates the scanner
    System.out.print("Enter rectangle width: "); // outputs prompt
    try {
      double width = input.nextDouble(); // stores input width
      System.out.print("Enter rectangle height: "); // outputs prompt
      double height = input.nextDouble(); // stores input height
      Rectangle r1 = new Rectangle(width, height); // specific rectangle
      System.out.println(r1);
      System.out.printf("Area = %.3f%n", r1.area());
      System.out.printf("Perimeter = %.3f%n%n", r1.perimeter());
    } catch (IllegalArgumentException e) {
      System.out.println("Error: invalid height (must be > 0)"); // outputs error message
    } catch (Exception e) {
      System.out.println("Error: numbers required for width and height!"); // outputs error
    } 
    input.close(); // closes the scanner
  } // end of main
} // end of class RectTest
