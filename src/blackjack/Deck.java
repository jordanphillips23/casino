package blackjack;

import java.util.ArrayList;
import java.util.Random;


public class Deck extends ArrayList<Card>{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final int deckNumbers = 6;
	
	private int[] cardValues = {2, 3, 4, 5, 6, 7, 8, 9, 10, 10, 10, 10, 11};
	private String[] cardSuits = {"Spades", "Diamonds", "Clubs", "Hearts"};
	private String[] cardTypes = {"2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K", "A"};
	
	public Deck() {
		createDeck();
		shuffle();
	}
	
	public void createDeck() {
		this.clear();
		for (int k = 0; k < deckNumbers; k++) {
			for (int i = 0; i < cardValues.length; i++) {
				for (int j = 0; j < cardSuits.length; j++) {
					this.add(new Card(cardSuits[j], cardTypes[i], cardValues[i]));
				}
			}
		}
	}
	
	public void shuffle() {
		int n = this.size();
        Random random = new Random();
        for (int i = 0; i < this.size()-1; i++) {
            int randomValue = i + random.nextInt(n - i);
            Card randomElement = this.get(randomValue);
            
            this.remove(randomValue);
            this.add(randomValue, this.get(i));
            
            this.remove(i);
            this.add(i, randomElement);
        }
	}
	
	public void draw( HandInterface hand ) {
		hand.draw( this.get(0) );
		this.remove(0);
	}
}
