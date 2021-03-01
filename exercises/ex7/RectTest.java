// Tests class Rectangle
// Creates 2 Rectangle objects
// One default, one specific object

public class RectTest {
  public static void main(String[] args) {
    Rectangle r1 = new Rectangle(); // deafult rectangle
    Rectangle r2 = new Rectangle(2.75, 5.5); // specific rectangle
    System.out.println("Rectangle 1:");
    System.out.println(r1);
    System.out.printf("Area = %.3f%n", r1.area());
    System.out.printf("Perimeter = %.3f%n%n", r1.perimeter());
    System.out.println("Rectangle 2:");
    System.out.println(r2);
    System.out.printf("Area = %.3f%n", r2.area());
    System.out.printf("Perimeter = %.3f%n", r2.perimeter());
  } // end of main
} // end of class RectTest
