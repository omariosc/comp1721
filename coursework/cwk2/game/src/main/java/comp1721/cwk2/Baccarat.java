package comp1721.cwk2;

import java.util.Scanner;

public class Baccarat {

  // Declares Baccarat hands and shoe
  private BaccaratHand playerHand;
  private BaccaratHand bankerHand;
  private Shoe baccaratShoe;

  // Values used to record game statistics
  private int rounds = 0;
  private int roundsPlayer = 0;
  private int roundsBanker = 0;
  private int roundsTies = 0;

  // Value used to check for interactive mode
  private int interactiveMode = 0;

  // Creates scanner for input in interactive mode
  private Scanner input;

  /**
   * Initialises Baccarat hands and shoe
   */
  public void gameInitialisation() {
    // Initialises player and banker hands
    playerHand = new BaccaratHand();
    bankerHand = new BaccaratHand();
    // Initialises shoe
    baccaratShoe = new Shoe(6);
    // Creates scanner
    input = new Scanner(System.in);
  }

  /**
   * Outputs string representation and value of each hand
   */
  public void roundDisplay() {
    // Outputs hands and message if either or both hands are naturals
    System.out.printf("Player: %s = %d%n", playerHand.toString(), playerHand.value());
    System.out.printf("Banker: %s = %d%n", bankerHand.toString(), bankerHand.value());
  }

  /**
   * Outputs message for round check and processes user input
   * Continues until user inputs valid input
   * 
   * @return 1 if user inputs "y", 0 if user inputs "n"
   */
  public int roundCheck() {
    // Until valid user input
    while (true) {
      // Outputs round check message
      System.out.print("Another round? (y/n): ");
      // Stores user input
      String userInput = input.next();
      // Checks if input is equal to "n"
      if (userInput.equals("n")) {
        return 0;
      }
      else if (userInput.equals("y")) {
        return 1;
      }
      else {
        System.out.println("Please enter a valid input.");
      }
    }
  }

  /**
   * Outputs final game statistics
   */
  public void gameDisplay() {
    System.out.printf("%d rounds played %n", rounds);
    System.out.printf("%d player wins %n", roundsPlayer);
    System.out.printf("%d banker wins %n", roundsBanker);
    System.out.printf("%d ties", roundsTies);
  }

  /**
   * Program to play a complete game of Baccarat
   * 
   * @param args Used to check if user wants to play using interactive mode
   */
  public void main(String[] args) {
    // If program is run with the "-i" option specified on the command line
    if (args[0] != null && args[0].equals("-i")) {
      // Enables interactive mode
      interactiveMode = 1;
    }

    // Initialises game
    gameInitialisation();

    

    // Outputs final game statistics
    gameDisplay();

    // Closes the scanner
    input.close();
  }
}
