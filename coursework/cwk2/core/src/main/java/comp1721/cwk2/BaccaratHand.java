package comp1721.cwk2;

import java.util.LinkedList;
import java.util.List;

/**
 * Representation of a Baccarat hand.
 * Implementation takes use of CardCollection.java
 */
public class BaccaratHand {

  // Private list to represent the BaccaratHand
  protected List<BaccaratCard> hand;

  /**
   * Initial BaccaratHand object (linked list).
   * Starts out as empty.
   */
  public BaccaratHand() {
    // Creates hand as a linked list
    hand = new LinkedList<>();
  }

  /**
   * Provides the size of the Baccarat hand.
   * 
   * @return Number of cards in the hand
   */
  public int size() {
    return hand.size();
  }

  /**
   * Adds BaccaratCard objects to the BaccaratHand.
   * 
   * @param card BaccaratCard to be added
   */
  public void add(Card card) {
    // Casts card to BaccaratCard
    hand.add((BaccaratCard) card);
  }

  /**
   * Calculates the points value of a hand in the game of Bacarrat.
   * 
   * @return Points value.
   */
  public int value() {
    // Initial value
    var value = 0;
    // Iterates through cards in hand
    for (BaccaratCard card : hand) {
      // Increments value by value of card
      value += card.value();
    }
    // Performs modulus of value by 10 as maximum value is 9
    return value % 10;
  }

  /**
   * Returns boolean value based on points value of the Baccarat hand.
   * 
   * @return true if the hand has a points value of 8 or 9, false otherwise
   */
  public boolean isNatural() {
    // Returns true if card has value of 8 or 9
    return value() == 8 || value() == 9;
  }

  /**
   * Creates a complete string representation of this Bacarrat hand.
   *
   * <p>String contains two-character representations of each card,
   * separated from each other by a space.</p>
   *
   * @return String representation of this Bacarrat hand
   */
  @Override
  public String toString() {
    // Creates new string builder
    var bld = new StringBuilder();
    // Iterates through every card in the hand
    for (BaccaratCard card : hand) {
      // Adds the card string representation to string builder
      bld.append(card.toString() + " ");
    }
    // Converts string builder to string
    var string = bld.toString();
    // If there was a card in the hand
    if (string.length() > 0) {
      // Removes the final space in the string representation
      string = string.substring(0, string.length() - 1);
    }
    // Returns the final representation of the hadn
    return string;
  }

  /**
   * Discards the contents of this hand.
   * (Used in Baccarat)
   */
  public void discard() {
    // Clears all cards in the hand
    hand.clear();
  }

  /**
   * Returns a Card object from the hand
   * (Used in Bacarrat)
   * 
   * @param index Index value of card in the hand
   * @return the card in the hand
   */
  public Card get(int index) {
    return hand.get(index);
  }
}