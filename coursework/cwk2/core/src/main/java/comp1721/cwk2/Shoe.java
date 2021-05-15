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
    if (decks == 6 || decks == 8) {
      baccaratShoe = new LinkedList<>();
      var cardIndex = 0;
      for (var i = 0; i < decks; i++) {
        for (Suit cardSuit : Suit.values()) {
          for (Rank cardRank : Rank.values()) {
            baccaratShoe.add(cardIndex, new BaccaratCard(cardRank, cardSuit));
            cardIndex++;
          }
        }
      }
    }
    else {
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
   * Ensures that a shoe stores the specified number of complete decks of BaccaratCard objects.
   * 
   */
  public void shuffle() {
    Collections.shuffle(baccaratShoe);
  }

  /**
   * Removes the fisrt stored card and returns it to the caller
   * 
   * @return Card to be dealt
   * @throws CardException If size of deck is 0
   */
  public Card deal() throws CardException {
    if (size() != 0) {
      var card = baccaratShoe.get(0);
      baccaratShoe.remove(0);
      return card;
    }
    else {
      throw new CardException("Error: Deck contains no more cards.");
    }
  }
}