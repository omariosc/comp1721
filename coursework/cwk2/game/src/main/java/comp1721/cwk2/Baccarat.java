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
   * Initialises number of decks in the shoe
   * 
   * <p>Below commented implementation hard codes and uses 6 as the number of decks
   * 
   *    <code>baccaratShoe = new Shoe(6);</code>
   * 
   * Meaning that the game will not be able to use 8 decks
   * However, there are two ways to let the user specify the number of decks:
   * 
   * 1. User specified number of decks in the command line (processing any errors too)
   * 
   *    <code>if (args != null && args[1] != null & (args[1].equals("6") || args[1].equals("8"))
   *            { baccaratShoe = new Shoe(args[1]); }</code>
   * 
   * 2. Prompt user for input for the number of the decks in interactive mode
   *    Use hard coded value in default mode
   * 
   * I decided to go with implementation number 2 in which the user
   * chooses the number of decks they would like to play with in interactive mode,
   * since in the default mode, no command line arguments are passed (as per coursework specification)
   * and the functionality is essentialy the same.
   * 
   * Therefore in default mode, the shoe contains 6 decks. Of course 
   * this has its own limitations in that in default mode the user cannot play with 8 decks.
   * 
   * This option can be disabled by commenting out the <code>while</code> loop</p>
   */
  private static void initialiseShoe() {
    // Initially hard codes number of decks
    var decks = 6;
    // If interactive mode is on
    while (interactiveMode == 1) {
      // Outputs number of decks prompt
      System.out.print("Enter number of decks you would like to play with (6/8): ");
      // Stores user input
      String userInput = input.next();
      // Checks if input is valid number of decks
      if (userInput.equals("6") || userInput.equals("8")) {
        // Sets number of decks to userInput
        decks = Integer.parseInt(userInput);
        break;
      }
      // If user supplied an invalid input
      else {
        System.out.println("Please enter a valid input.");
      }
    }
    baccaratShoe = new Shoe(decks);
  }

  /**
   * Initialises Baccarat hands and shoe
   */
  private static void gameInitialisation() {
    // Initialises player and banker hands
    playerHand = new BaccaratHand();
    bankerHand = new BaccaratHand();
    // If in interactive mode
    if (interactiveMode == 1) {
      // Creates scanner
      input = new Scanner(System.in);
    }
    // Initialises shoe
    initialiseShoe();
  }

  /**
   * Displays the round number message and clears hand
   */
  private static void newRound() {
    rounds++;
    System.out.printf("%nRound %d%n", rounds);
    playerHand.discard();
    bankerHand.discard();
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
   * Processes player win
   */
  private static void playerWin() {
    // Increments number of rounds player has won
    roundsPlayer++;
    // Outputs player win message
    System.out.println("Player win!");
  }

  /**
   * Processes banker win
   */
  private static void bankerWin() {
    // Increments number of rounds banker has won
    roundsBanker++;
    // Outputs banker win message
    System.out.println("Banker win!");
  }

  /**
   * Deals third card to player and outputs dealing message
   */
  private static void dealThirdCardPlayer() {
    // Outputs dealing message
    System.out.println("Dealing third card to player...");
    // Adds card to player hand from game shoe
    playerHand.add(baccaratShoe.deal());
  }

  /**
   * Deals third card to banker and outputs dealing message
   */
  private static void dealThirdCardBanker() {
    // Outputs dealing message
    System.out.println("Dealing third card to banker...");
    // Adds card to banker hand from game shoe
    bankerHand.add(baccaratShoe.deal());
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
    useFancySymbols();

    // Initialises game
    gameInitialisation();
    
    do {
      // Processes new round
      newRound();

      // Shuffles shoe
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
