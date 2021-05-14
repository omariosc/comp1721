package comp1721.cwk2;

/**
 * Representation of a Baccarat playing card.
 * Implementation takes use of Card.java
 */
public class BaccaratCard extends Card {

  // Private fields to represent the rank and suit of a Baccaratcard
  private Rank rank;
  private Suit suit;

  public BaccaratCard(Rank r, Suit s) {
    super(r, s);
  }

  @Override
  public final Rank getRank() {
    return null;
  }

  @Override
  public final Suit getSuit() {
    return null;
  }

  @Override
  public String toString() {
    return null;
  }

  @Override
  public boolean equals(Object other) {
    return true;
  }

  @Override
  public final int compareTo(Card other) {
    return 0;
  }

  @Override
  public final int value() {
    return 0;
  }
}