// Converts input fom user and converts from Fahrenheit to Celsius

package exercises.ex1;

import java.util.Scanner;

class TemperatureConvert {
  public static void main(String[] args) {
    Scanner input = new Scanner(System.in); // creates the scanner
    System.out.print("Enter Fahrenheit temperature: "); // outputs prompt 
    double fahrenheitTemperature = input.nextDouble(); // stores fahrenheit temperature input
    double celsiusTemperature = (fahrenheitTemperature - 32) * 5/9; // converts fahrenheit temperature to celsius 
    System.out.println("Equivalent Celsius temperature = " + celsiusTemperature); // outputs celsius temperature
    input.close(); // closes the scanner
  } // end of main ()
} // end of class TemperatureConvert