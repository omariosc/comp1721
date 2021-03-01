// Pasword checker

package exercises.ex4;

import java.util.Scanner;

class PasswordCheck {
  public static void main(String[] args) {
    Scanner input = new Scanner(System.in); // creates the scanner
    String password = input.nextLine(); // stores input password

    // counter variables
    int digits = 0;
    int upper = 0;
    int lower = 0;

    for (char c : password.toCharArray()) { // iterate through password
      if (Character.isDigit(c)) digits++; // increments if string contains digit
      if (Character.isLowerCase(c)) lower++; // increments if string contains lower case character
      if (Character.isUpperCase(c)) upper++; // increments if string contains upper case character
    }

    if (password.length() < 10) { // if string contains less than 10 characters
      System.out.println("Error: password should contain at least 10 characters."); // error message
      System.exit(1); // exits program
    }
    if (digits < 2) { // if string contains less than 2 digits
      System.out.println("Error: password should contain at least two digits."); // error message
      System.exit(1); // exits program
    }
    if (upper < 2) { // if string contains less than 2 digits
      System.out.println("Error: password should contain at least two upper case characters."); // error message
      System.exit(1); // exits program
    }
    if (lower < 2) { // if string contains less than 2 digits
      System.out.println("Error: password should contain at least two lower case characters."); // error message
      System.exit(1); // exits program
    }
    System.out.println("Success: password valid.");

    input.close(); // closes the scanner
  } // end of main
} // end of class PasswordCheck