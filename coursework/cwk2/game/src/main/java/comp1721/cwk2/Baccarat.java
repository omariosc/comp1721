package comp1721.cwk2;

public class Baccarat {
  public static void main(String[] args) {

    // Creates and initialises Baccarat hands and shoe
    var playerHand = new BaccaratHand();
    var bankerHand = new BaccaratHand();
    var baccaratShoe = new Shoe(6);

    // Shuffles shoe and deals two cards to both player and banker
    baccaratShoe.shuffle();
    playerHand.add(baccaratShoe.deal());
    playerHand.add(baccaratShoe.deal());
    bankerHand.add(baccaratShoe.deal());
    bankerHand.add(baccaratShoe.deal());
    
    // Stores string representation of each hand
    var playerString = playerHand.toString();
    var bankerString = bankerHand.toString();

    // Calculates value for hands
    var playerValue = playerHand.value();
    var bankerValue = bankerHand.value();

    // Outputs hands and message if either or both hands are naturals    
    System.out.printf("Player: %s = %d%n", playerString, playerValue);
    System.out.printf("Banker: %s = %d%n", bankerString, bankerValue);
    if (playerHand.isNatural()) {
      System.out.println("Player has a Natural");
    }
    if (bankerHand.isNatural()) {
      System.out.println("Banker has a Natural");
    } 
  }
}
