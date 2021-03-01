// Computes factorial
// when using int, can compute up to 33! (negative output), up to 19! (positive output)
// when using long, can compute up to 65! (negative output), up to 21! (positive output)

import java.util.Scanner;
import java.math.*;

class Factorial {
  public static void main(String[] args) {
    Scanner input = new Scanner(System.in); // creates the scanner
    if (args == null || args.length == 0) { // if user does not pass a number
      System.out.println("Error: no value supplied."); // outputs error message
      System.exit(1); // exits program
    }
    int n = Integer.parseInt(args[0]); // assigns argument to n
    BigInteger factorial = BigInteger.valueOf(1); // stores value of factorial as a BigInteger
    if (n > 0) {
      for (int i = 1; i <= n; i++) { // iterate up to input value
        BigInteger a = BigInteger.valueOf(i); // assigns value of i to a BigInteger variable
        factorial = factorial.multiply(a); // computes factorial
      }
    }
    System.out.printf("%d! = %d", n, factorial); // outputs factorial
    input.close(); // closes the scanner  
  } // end of main
} // end of class Factorial