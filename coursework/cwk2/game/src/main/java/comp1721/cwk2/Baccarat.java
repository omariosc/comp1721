/**
 * Note: when running the :game:interactive gradle task through VSCode
 * the build fails with an NoSuchElementException however this
 * does not happen when running the task through the command line
 */

package comp1721.cwk2;

import java.util.Scanner;
import comp1721.cwk2.Card.Rank;

public class Baccarat {

  // Baccarat hands and shoe
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
   * However, there are two ways to let the user specify the number of decks:</p>
   * 
   * <p>1. User specified number of decks in the command line (processing any errors too)
   * 
   *    <code>if (args[1].equals("6") || args[1].equals("8") 
   *            { baccaratShoe = new Shoe(args[1]); }</code>
   * 
   * <p>2. Prompt user for input for the number of the decks in interactive mode
   *    Use hard coded value in default mode</p>
   * 
   * <p>I decided to go with implementation number 2 in which the user
   * chooses the number of decks they would like to play with in interactive mode,
   * since in the default mode, no command line arguments are passed (per coursework specification)
   * and the functionality is essentialy the same.</p>
   * 
   * <p>Therefore in default mode, the shoe contains 6 decks. Of course 
   * this has its own limitations in that in default mode the user cannot play with 8 decks.
   * This option can be disabled by commenting out the <code>while</code> loop</p>
   */
  private static void initialiseShoe() {
    // Initially hard codes number of decks
    var decks = 6;
    // If interactive mode is on
    while (interactiveMode == 1) {
      // Outputs number of decks prompt
      System.out.print("Number of decks? (6/8): ");
      // Stores user input
      String userInput = input.next();
      // Checks if input is valid number of decks
      if (userInput.equals("6") || userInput.equals("8")) {
        // Sets number of decks to userInput
        decks = Integer.parseInt(userInput);
        // Ends while loop
        break;
      }
      // If user supplied an invalid input
      else {
        // Outputs error message
        System.out.println("Please enter a valid number of decks.");
      }
    }
    // Initialises shoe with number of decks (hardcoded or user supplied)
    baccaratShoe = new Shoe(decks);
    // Shuffles shoe
    baccaratShoe.shuffle();
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
    // Increments round number
    rounds++;
    // Outputs round number
    System.out.printf("%nRound %d%n", rounds);
    // Clears player and banker hands
    playerHand.discard();
    bankerHand.discard();
  }

  /**
   * Deals two cards to player and banker
   * 
   * <p>Cards are dealt starting from player and alternate between the hands
   * (as per Wikipedia article)</p>
   */
  private static void dealCards() {
    // 1st player card
    playerHand.add(baccaratShoe.deal());
    // 1st banker card
    bankerHand.add(baccaratShoe.deal());
    // 2nd player card
    playerHand.add(baccaratShoe.deal());
    // 2nd banker card
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
   * Processes round tie
   */
  private static void roundTie() {
    // Increments number of rounds tied
    roundsTies++;
    // Outputs round tied message
    System.out.println("Round tied!");
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
   * Checks if either hand are natural.
   * 
   * <p>If both hands are natural then hand with higher natural will win.
   * If both hands are natural and equal value then it is a tie.
   * If player hand is natural and banker hand is not, then player wins.
   * If banker hand in natural and player hand is not, then banker wins.</p>
   * 
   * <p>Also checks if both cards have value of 6 or 7.
   * If both hands total 6, then round is tied.
   * If both hands total 7, then round is tied.
   * If one hand is 6 and the other hand is 7, then the higher hand wins.</p>
   * 
   * @return 1 if round ends, 0 if round continues
   */
  private static int naturalCheck() {
    // Checks if both players have equal natural hands or both hands have same value of 6 or 7
    if ((playerHand.isNatural() || playerHand.value() == 6 || playerHand.value() == 7)
    && playerHand.value() == bankerHand.value()) {
      // Round ties
      roundTie();
      // Round end
      return 1;
    }
    // Checks if both hands are natural and one hand is higher value then the other
    else if (playerHand.isNatural() && bankerHand.isNatural()) {
      // If player has a value of 9 and banker a value of 8
      if (playerHand.value() > bankerHand.value()) {
        // Player wins
        playerWin();
        // Round end
        return 1;
      }
      // If banker has a value of 9 and player a value of 8
      else {
        // Banker wins
        bankerWin();
        // Round end
        return 1;
      }
    }
    // If player hand is natural or player scores 7 and banker scores 6
    else if (playerHand.isNatural() || (playerHand.value() == 7 && bankerHand.value() == 6)) { 
      // Player wins
      playerWin();
      // Round end
      return 1;
    }
    // If banker hand is natural or banker scores 7 and player scores 6
    else if (bankerHand.isNatural() || (playerHand.value() == 6 && bankerHand.value() == 7)) {
      // Banker wins
      bankerWin();
      // Round end
      return 1;
    }
    // If neither hand was a natural
    else {
      // Round continues
      return 0;
    }
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
   * Player's rule (as per Wikipedia article)
   * 
   * <p>If the player has an initial total of 0-5, they draw a third card.
   * If the player has an initial total of 6 or 7, they stand</p>
   */
  private static void playersRule() {
    // If player's hand value is 5 or less
    if (playerHand.value() <= 5) {
      // Deal third card to player
      dealThirdCardPlayer();
    }
  }

  /**
   * Banker's rule (as per Wikipedia article)
   * 
   * <p>If the player stood pat (i.e., has only two cards), the banker regards only their own hand
   * and acts according to the same rule as the player, that is the banker draws a third card with 
   * hands 0–5 and stands with 6 or 7.</p>
   * 
   * If the player drew a third card, the banker acts according to following rules:
   * <ul><li>If banker's total is <= 2, then banker draws third card</li>
   * <li>If banker's total is 3, banker draws third card unless player's third card was an 8.</li>
   * <li>If banker's total is 4, banker draws third card if player's third card was a 2-7.</li>
   * <li>If banker's total is 5, banker draws third card if player's third card was a 4-7.</li>
   * <li>If banker's total is 6, banker draws third card if player's third card was a 6 or 7.</li>
   * <li>If banker's total is 7, banker stands.</li></ul>
   */
  private static void bankersRule() {
    // If player stood pat and banker's hand value is 5 or less
    if (playerHand.value() > 5 && bankerHand.value() <= 5) {
      // Banker draws a card
      dealThirdCardBanker();
    }
    // If the player drew a third card (using player's rule)
    if (playerHand.value() <= 5) {
      // Stores the player's third card
      var thirdCard = playerHand.get(2);
      // Stores the player's third card rank
      var thirdCardRank = thirdCard.getRank();
      // Computes if banker should have third card according to more complex rules
      if (bankerHand.value() <= 2 ||
      (bankerHand.value() < 7 &&
      (thirdCardRank.equals(Rank.SEVEN) || thirdCardRank.equals(Rank.SIX)))
      || (bankerHand.value() < 6 &&
      (thirdCardRank.equals(Rank.FIVE) || thirdCardRank.equals(Rank.FOUR)))
      || (bankerHand.value() < 5 &&
      (thirdCardRank.equals(Rank.THREE) || thirdCardRank.equals(Rank.TWO))) 
      || (bankerHand.value() == 3 && !(thirdCardRank.equals(Rank.EIGHT)))) {
        // Banker draws a card
        dealThirdCardBanker();
      }
    }
  }

  /**
   * Checks for the score of each hand
   * 
   * <p>If both hands have the same value then round is tied
   * If player hand is higher value than banker hand, then player wins
   * If banker hand is higher value than player hand, then banker wins</p>
   */
  private static void finalScoring() {
    // Checks if both players have same value
    if (playerHand.value() == bankerHand.value()) {
      // Round is tied
      roundTie();
    }
    // If player hand is natural
    else if (playerHand.value() > bankerHand.value()) { 
      // Player wins
      playerWin();
    }
    // If banker hand is natural
    else if (playerHand.value() < bankerHand.value()) {
      // Banker wins
      bankerWin();
    }
  }

  /**
   * Outputs message for round check and processes user input
   * Continues until user inputs valid input
   * 
   * @return 1 if user inputs "y", 0 if user inputs "n"
   */
  private static int roundInputCheck() {
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
        // Outputs error message
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
  private static int roundContinueCheck() {
    // If there are less than 6 cards remaining in the shoe
    if (baccaratShoe.size() < 6) {
      // Ends the game
      return 0;
    }
    // If there are at least 6 cards in the shoe
    else {
      // Checks for interactive mode, if yes then checks if user wants to continue
      if (interactiveMode == 1 && roundInputCheck() == 0) {
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
    
    // Main game program
    do {
      // Processes new round
      newRound();
      // Deals cards to player and banker
      dealCards();
      // Displays player and banker hands and their values
      handDisplay();

      // If neither hand was natural, consults tableau
      if (naturalCheck() == 0) {
        // First consults player's rules
        playersRule();
        // Secondly consults banker's rules
        bankersRule();
        // Displays player and banker hands and their values
        handDisplay();
        // If neither hand is a natural again
        if (naturalCheck() == 0) {
          // Goes to final scoring
          finalScoring();
        }
      }
    }
    // If there are sufficient cards in the shoe
    // If user wants to continue in interactive mode
    while (roundContinueCheck() == 1);
    
    // Outputs final game statistics
    gameDisplay();
    // Closes the scanner if in interactive mode
    scannerClose();
  }
}
