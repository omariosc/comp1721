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
    hand.add((BaccaratCard) card);
  }

  /**
   * Calculates the points value of a hand in the game of Bacarrat.
   * 
   * @return Points value.
   */
  public int value() {
    var value = 0;
    // Iterates through cards in hand
    for (BaccaratCard card : hand) {
      value += card.value();
    }
    return value % 10;
  }

  /**
   * Returns boolean value based on points value of the Baccarat hand.
   * 
   * @return true if the hand has a points value of 8 or 9, false otherwise
   */
  public boolean isNatural() {
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
    var bld = new StringBuilder();
    for (BaccaratCard card : hand) {
      bld.append(card.toString());
      bld.append(" ");
    }
    var string = bld.toString();
    if (string.length() > 0) {
      string = string.substring(0, string.length() - 1);
    }
    return string;
  }
}