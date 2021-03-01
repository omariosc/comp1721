// Pasword checker

import java.util.Scanner;

class PasswordCheck {
  public static void main(String[] args) {
    Scanner input = new Scanner(System.in); // creates the scanner
    System.out.println("Input password: "); // outputs prompt
    String password = input.nextLine(); // stores input password

    // runs all checks
    digitCheck(password);
    lowerCheck(password);
    upperCheck(password);
    characterCheck(password);
    
    // if all checks are valid
    System.out.println("Success: password valid.");

    input.close(); // closes the scanner
  } // end of main
  public static void digitCheck(String str) { // checks number of digits in string
    int counter = 0; // counter variable for digits
    for (char c : str.toCharArray()) { // iterate through password
      if (Character.isDigit(c)) counter++; // increments if string contains digit
    }
    if (counter < 2) { // if string contains less than 2 digits
      System.out.println("Error: password should contain at least two digits."); // error message
      System.exit(1); // exits program
    }
  } // end of digitCheck
  public static void lowerCheck(String str) { // checks number of lower case characters in string
    int counter = 0; // counter variable for lower case characters
    for (char c : str.toCharArray()) { // iterate through password
      if (Character.isLowerCase(c)) counter++; // increments if string contains lower case character
    }
    if (counter < 2) { // if string contains less than 2 digits
      System.out.println("Error: password should contain at least two lower case characters."); // error message
      System.exit(1); // exits program
    }
  } // end of lowerCheck
  public static void upperCheck(String str) { // checks number of upper case characters in string
    int counter = 0; // counter variable for upper case characters
    for (char c : str.toCharArray()) { // iterate through password
      if (Character.isUpperCase(c)) counter++; // increments if string contains upper case character
    }
    if (counter < 2) { // if string contains less than 2 digits
      System.out.println("Error: password should contain at least two upper case characters."); // error message
      System.exit(1); // exits program
    }
  } // end of upperCheck
  public static void characterCheck(String str) {  // checks number of characters in string
    if (str.length() < 10) { // if string contains less than 10 characters
      System.out.println("Error: password should contain at least 10 characters."); // error message
      System.exit(1); // exits program
    }
  } // end of characterCheck
} // end of class PasswordCheck