// Computes area and eccentricity of an ellipse using user input lengths

import java.util.Scanner;

class Ellipse {
  public static void main(String[] args) {
    Scanner input = new Scanner(System.in); // creates the scanner
    System.out.print("Enter semi-major axis length (larger length) : "); // outputs prompt for first length
    double alpha = input.nextDouble(); // stores user input for first length
    System.out.print("Enter semi-minor axis length (smaller length): "); // outputs prompt for second length
    double beta = input.nextDouble(); // stores user input for second length

    if (alpha < beta) { // if major length is smaller than minor length
      System.out.println("Error: semi-major axis must be longer than semi-minor axis"); // outputs error message
      System.exit(1); // exits program
    }

    if (alpha <= 0) { // if major length is 0
      System.out.println("Error: semi-major axis length must be > 0"); // outputs error message
      System.exit(1); // exits program
    }

    if (beta <= 0) { // if minor length is 0
      System.out.println("Error: semi-minor axis length must be > 0"); // outputs error message
      System.exit(1); // exits program
    }    

    double area = alpha*beta*Math.PI; // computes area
    double eccentricity = Math.sqrt(1-((beta*beta)/(alpha*alpha))); // computes eccentricity
    System.out.printf("The area of the ellipse is = %1.3f%n", area); // outputs area
    System.out.printf("The eccentricity of the ellipse is = %1.3f%n", eccentricity); // outputs eccentricity
    input.close(); // closes the scanner
  } // end of main
} // end of class Ellipse