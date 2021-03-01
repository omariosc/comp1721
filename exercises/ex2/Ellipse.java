// Computes area and eccentricity of an ellipse using user input lengths

package exercises.ex2;

import java.util.Scanner;

class Ellipse {
  public static void main(String[] args) {
    Scanner input = new Scanner(System.in); // creates the scanner
    System.out.print("Enter semi-major axis length (larger length) : "); // outputs prompt for first length
    double alpha = input.nextDouble(); // stores user input for first length
    System.out.print("Enter semi-minor axis length (smaller length): "); // outputs prompt for second length
    double beta = input.nextDouble(); // stores user input for second length
    double area = alpha*beta*Math.PI; // computes area
    double eccentricity = Math.sqrt(1-((beta*beta)/(alpha*alpha))); // computes eccentricity
    System.out.printf("The area of the ellipse is = %1.3f%n", area); // outputs area
    System.out.printf("The eccentricity of the ellipse is = %1.3f%n", eccentricity); // outputs eccentricity
    input.close(); // closes the scanner
  } // end of main
} // end of class Ellipse