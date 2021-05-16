package comp1721.cwk2;

import java.util.Scanner;

public class Baccarat {

  // Declares Baccarat hands and shoe
  private static BaccaratHand playerHand;
  private static BaccaratHand bankerHand;
  private static Shoe baccaratShoe;

  // Values used to record game statistics
  private static int rounds = 0;
  private static int roundsPlayer = 0;
  private static int roundsBanker = 0;
  private static int roundsTies = 0;

  // Value used to check for interactive mode
  private static int interactiveMode = 0;

  // Creates scanner for input in interactive mode
  private static Scanner input;

  /**
   * Checks if the user wants to play interactive mode
   * 
   * @param args Used to check if user wants to play using interactive mode
   * @return 1 if user specified interactive mode, 0 otherwise
   */
  private static void interactiveModeCheck(String[] args) {
    // If program is run with the "-i" option specified on the command line
    if (args.length != 0 && args[0] != null && args[0].equals("-i")) {
      // Enables interactive mode
      interactiveMode = 1;
    }
  }

  /**
   * Uses unicode symbols for card suit
   */
  private static void useFancySymbols() {
    // Uses fancy symbols
    Card.useFancySymbols(true);
  }

  /**
   * Initialises Baccarat hands and shoe
   */
  private static void gameInitialisation() {
    // Initialises player and banker hands
    playerHand = new BaccaratHand();
    bankerHand = new BaccaratHand();
    // Initialises shoe
    baccaratShoe = new Shoe(6);
    // If in interactive mode
    if (interactiveMode == 1) {
      // Creates scanner
      input = new Scanner(System.in);
    }
  }

  /**
   * Displays the round number message
   */
  private static void roundDisplay() {
    rounds++;
    System.out.printf("%nRound %d%n", rounds);
  }

  /**
   * Deals two cards to player and banker
   */
  private static void dealCards() {
    playerHand.add(baccaratShoe.deal());
    playerHand.add(baccaratShoe.deal());
    bankerHand.add(baccaratShoe.deal());
    bankerHand.add(baccaratShoe.deal());
  }

  /**
   * Outputs string representation and value of each hand
   */
  private static void handDisplay() {
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
  private static int roundCheck() {
    // Until valid user input
    while (true) {
      // Outputs round check message
      System.out.print("Another round? (y/n): ");
      // Stores user input
      String userInput = input.next();
      // Checks if input is equal to "y"
      if (userInput.equals("y")) {
        // Game continues
        return 1;
      }
      // Checks if input is equal to "n"
      else if (userInput.equals("n")) {
        // Ends the round
        return 0;
      }
      // If user supplied an invalid input
      else {
        System.out.println("Please enter a valid input.");
      }
    }
  }

  /**
   * Checks size of shoe and asks user for continuation if in interactive mode
   * 
   * @return 1 if less than 6 cards or if user wants to stop in interactive mode
   *   0 if 6 or more cards and user wants to continnue in interactive mode 
   */
  private static int shoeSizeCheck() {
    // If there are less than 6 cards remaining in the shoe
    if (baccaratShoe.size() < 6) {
      // Ends the game
      return 0;
    }
    // If there are at least 6 cards in the shoe
    else {
      // Checks for interactive mode, if yes then checks if user wants to continue
      if (interactiveMode == 1 && roundCheck() == 0) {
        // Ends the game
        return 0;
      }
      // If in default mode or if user does not want to continue
      else {
        // Game continues
        return 1;
      }
    }
  }

  /**
   * Outputs final game statistics
   */
  private static void gameDisplay() {
    // Displays number of rounds played
    System.out.printf("%n%d rounds played %n", rounds);
    // Displays number of player wins
    System.out.printf("%d player wins %n", roundsPlayer);
    // Displays number of banker wins
    System.out.printf("%d banker wins %n", roundsBanker);
    // Displays number of ties
    System.out.printf("%d ties", roundsTies);
  }

  /**
   * Closes scanner if in interactive mode
   */
  private static void scannerClose() {
    // If in interactive mode
    if (interactiveMode == 1) {
      // Closes the scanner
      input.close();
    }
  }

  /**
   * Program to play a complete game of Baccarat
   * 
   * @param args Used to check if user wants to play using interactive mode
   */
  public static void main(String[] args) {
    // Checks for interactive mode
    interactiveModeCheck(args);

    // Uses fancy symbols for suit, comment if you would like to use regular letters
    // useFancySymbols();

    // Initialises game
    gameInitialisation();
    
    do {
      // Displays rounds number message
      roundDisplay();

      // Shuffles shoe and deals two cards to both player and banker
      baccaratShoe.shuffle();

      // Deals cards to player and banker
      dealCards();

      // Displays player and banker hands and their values
      handDisplay();
    }
    while (shoeSizeCheck() == 1);
    
    // Outputs final game statistics
    gameDisplay();

    // Closes the scanner if in interactive mode
    scannerClose();
  }
}
