package blackjack;

public class Card {
	private String suit;
	private String type;
	private int value;
	
	public Card( String suit, String type, int value ) {
		this.suit = suit;
		this.type = type;
		this.value = value;
	}

	public String getSuit() {
		return suit;
	}

	public String getType() {
		return type;
	}

	public int getValue() {
		return value;
	}
	
	public void setValue( int value ) {
		this.value = value;
	}
	
	@Override
	public String toString() {
		return getType() + " of " + getSuit();
	}

}
