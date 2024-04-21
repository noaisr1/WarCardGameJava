

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Collections;

/*
 * DeckOfCards represents the deck while playing the cards
 */
public class DeckOfCards {
	
	private static final SecureRandom randomNumbers = new SecureRandom();
	private final int NUM_OF_CARDS = 52; // Total number of cards in deck.
	
	private ArrayList<Card> deck = new ArrayList<Card>();
	private int currentCard;
	
	/*
	 * initializing new deck of cards
	 */
	public DeckOfCards() {
		currentCard = 0;
	    String[] faces = {"2", "3", "4", "5", "6", "7", "8", "9", "10",
	            "Jack", "Queen", "King", "Ace"};
	    String[] suits = {"Hearts", "Diamonds", "Clubs", "Spades"};

	    // Populate deck with cards
	    for (int i = 0; i < NUM_OF_CARDS; i++) {
	        deck.add(new Card(faces[i % 13], suits[i / 13]));
	    }
	}
	
	/*
	 * Shuffle the cards with one-pass algorithm
	 */
	
	public void shuffle() {
		// One-pass shuffle algorithm
		currentCard = 0;
		
		for (int firstCard = 0; firstCard < deck.size(); firstCard++) {
			// Select random number between 0 and 51
			int secondCard = randomNumbers.nextInt(NUM_OF_CARDS);
			
			// Swap first and random second card
			Collections.swap(deck, firstCard, secondCard);
		}
	}
	
	/*
	 * Deals one card
	 */
	public Card dealCard() {
		// See if there are anymore cards left
		if (currentCard < deck.size()) {
			return deck.get(currentCard++); 
		}else {
			// Nothing to return
			return null;
		}
	}
	
	public int getSize() {
		return deck.size();
	}

}
