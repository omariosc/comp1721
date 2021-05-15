package comp1721.cwk2;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import comp1721.cwk2.Card.Suit;
import comp1721.cwk2.Card.Rank;

/**
 * Representation of a Baccarat shoe.
 * Implementation takes use of CardCollection.java
 */
public class Shoe {

  // Private list to represent the Baccarat shoe
  protected List<BaccaratCard> baccaratShoe;

  /**
   * Creates a shoe using specified value for cards in the shoe.
   * 
   * @param decks Number of cards in the deck
   * @throws CardException If value for deck is not 6 or 8
   */
  public Shoe(int decks) throws CardException {
    // Checks that decks are of correct value
    if (decks == 6 || decks == 8) {
      // Creates shoe as a linked list
      baccaratShoe = new LinkedList<>();
      // Iterates through decks
      for (var i = 0; i < decks; i++) {
        // Iterates through suits
        for (Suit cardSuit : Suit.values()) {
          // Iterates through ranks
          for (Rank cardRank : Rank.values()) {
            // Adds card to shoe
            baccaratShoe.add(new BaccaratCard(cardRank, cardSuit));
          }
        }
      } 
    }
    // If decks are not of correct value
    else {
      // Throws CardException
      throw new CardException("Error: Invalid deck size. Must be 6 or 8.");
    }
  }

  /**
   * Provides the size of the Baccarat shoe.
   * 
   * @return Number of cards in the shoe
   */
  public int size() {
    return baccaratShoe.size();
  }
  
  /**
   * Reorders the cards in the shoe randomly 
   */
  public void shuffle() {
    // Uses Collections class to shuffle shoe
    Collections.shuffle(baccaratShoe);
  }

  /**
   * Removes the fisrt stored card and returns it to the caller
   * 
   * @return Card to be dealt
   * @throws CardException If size of shoe is 0
   */
  public Card deal() throws CardException {
    // If there exists a card within the shoe
    if (size() != 0) {
      // Stores the first card in the shoe
      var card = baccaratShoe.get(0);
      // Removes the card from the shoe
      baccaratShoe.remove(0);
      // Returns the stored card
      return card;
    }
    // If there are no more cards in the shoe
    else {
      // Throws CardException
      throw new CardException("Error: Deck contains no more cards.");
    }
  }
}