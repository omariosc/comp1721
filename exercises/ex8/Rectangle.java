// Class to represent rectangular shapes

public class Rectangle {
  
  // private fields to represent components of a rectangle
  private double width;
  private double height;

  // constructor that initialises a Rectangle object (default)
  public Rectangle() {
    this(1.0, 1.0); // calls the two parameter constructor
  }

  /**
  * Creates a rectangle with width and height.
  *
  * @param w Value for width
  * @param h Value for height
  */ 
  // constructor that initialises a Rectangle object (specific)
  public Rectangle(double w, double h) {
    setWidth(w);
    setHeight(h);
  }

  /**
   * @return Width component of this Rectangle
   */
  public double getWidth() {
    return width;
  }

  /**
   * @return Height component of this Rectangle
   */
  public double getHeight() {
    return height;
  }

  /**
   * @return Area of Rectangle
   */
  public double area() {
    return width*height;
  }

  /**
   * @return Perimeter of Rectangle
   */
  public double perimeter() {
    return 2*(width+height);
  }

  // toString method, providing a string representation of a rectangle
  // called automatically when printing a Rectangle object
  
  @Override
  public String toString() {
    return String.format("Rectangle: %.3f x %.3f", width, height);
  }

  // Private helper methods

  private void setWidth(double w) {
    if (w <= 0) {
      throw new IllegalArgumentException("Error: invalid width (must be > 0)");
    }

    width = w; // assignment only happens if there is no exception
  }

  private void setHeight(double h) {
    if (h <= 0) {
      throw new IllegalArgumentException("Error: invalid height (must be > 0)");
    }

    height = h; // assignment only happens if there is no exception
  }
} // end of class Rectangle