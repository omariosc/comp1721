// Class to represent rectangular shapes

public class Rectangle {
  
  // private fields to represent components of a rectangle
  private double width;
  private double height;

  // constructor that initialises a Rectangle object (default)
  public Rectangle() {
    this(1.0, 1.0); // calls the two parameter constructor
  }

  // constructor that initialises a Rectangle object (specific)
  public Rectangle(double w, double h) {
    this.width = w;
    this.height = h;
  }

  public double getWidth() { // returns width
    return width;
  } // end of getWidth

  public double getHeight() { // returns height
    return height;
  } // end of getHeight

  public double area() { // returns area
    return width*height;
  } // end of area

  public double perimeter() { // returns perimeter
    return 2*(width+height);
  } // end of perimeter

  // toString method, providing a string representation of a rectangle
  // called automatically when printing a Rectangle object
  
  @Override
  public String toString() {
    return String.format("Rectangle: %.3f x %.3f", width, height);
  } // end of toString method
  
} // end of class Rectangle