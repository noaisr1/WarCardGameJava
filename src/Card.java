
/*
 * Card class represents a single card in the game.
 */
public class Card {
	private final String face; // Ace, King, 3, 4, etc ...
	private final String suit; // Hearts, Diamonds, etc ...
	
	public Card(String cardFace, String cardSuit) {
		this.face = cardFace;
		this.suit = cardSuit;
	}
	
	/*
	 * toString function to prompt the actions to the user
	 */
	public String toString() {
		return face + " of " + suit;
	}
	
	public String getFace() {
		return face;
	}


}
