package comp1721.cwk2;

/**
 * Representation of a Baccarat playing card.
 * Implementation takes use of Card.java
 */
public class BaccaratCard extends Card {

  // Private fields to represent the rank and suit of a Baccaratcard
  private Rank rank;
  private Suit suit;

  /**
   * Creates a Baccarat Card object.
   *
   * @param r Rank of the Baccarat card
   * @param s Suit of the Baccarat card
   */
  public BaccaratCard(Rank r, Suit s) {
    super(r, s);
  }

  /**
   * Provides the rank of this Baccarat card.
   *
   * @return The rank
   */
  @Override
  public final Rank getRank() {
    return super.getRank();
  }

  /**
   * Provides the suit of this Baccarat card.
   *
   * @return The suit
   */
  @Override
  public final Suit getSuit() {
    return super.getSuit();
  }

  /**
   * Creates a two-character string representation of this Baccarat card.
   *
   * @return String representation of this card
   */
  @Override
  public String toString() {
    return super.toString();
  }

  /**
   * Tests whether this Baccarat card is equal to another object.
   *
   * @param other Object with which this Baccarat card is being compared
   * @return true if "other" is equal to this Baccarat card, false otherwise
   */
  @Override
  public boolean equals(Object other) {
    return super.equals(other);
  }

  /**
   * Compares this Baccarat card to another, using their natural ordering
   * (by suit, then by rank).
   *
   * @return A negative integer if this card comes before "other", 0 if
   *   they are the same, a positive integer if this card comes after
   */
  @Override
  public final int compareTo(Card other) {
    return super.compareTo(other);
  }

  /**
   * Computes the value of this Baccarat card.
   *
   * <p>Value is based on rank and disregards suit. Aces score 1
   * and picture cards all score 0.</p>
   *
   * @return Baccarat card value
   */
  @Override
  public final int value() {
    // Calculates value of card
    int value = super.value();
    // Value of face cards are 0
    if (value == 10) { value = 0; }
    return value;
  }
}