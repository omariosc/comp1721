// Creates a temperature table from Fahrenheit to Celsius 
// Steps of 5° between 20°F - 100°F

class TempTable {
  public static void main(String[] args) {
    String degree = "\u00b0"; // degree symbol
    System.out.printf("+---------+---------+%n|   %s F   |   %s C   |%n+---------+---------+%n", degree, degree); // outputs header
    double fahrenheitTemperature  = 20.0; // initial fahrenheit temperature
    while (fahrenheitTemperature < 105) { // while fahreheit is under 100°F
      double celsiusTemperature = (fahrenheitTemperature - 32) * 5/9; // converts fahrenheit temperature to celsius
      System.out.printf("|%7.1f  |%7.1f  |%n", fahrenheitTemperature, celsiusTemperature); // outputs temperature
      fahrenheitTemperature = fahrenheitTemperature + 5.0; // increments fahrenheit temperature
    }
  } // end of main
} // end of class TempTable