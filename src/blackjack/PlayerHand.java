package blackjack;

import java.util.ArrayList;

public class PlayerHand extends ArrayList<Card> implements HandInterface{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private int betAmount;
	

	public void display() {
		// TODO Auto-generated method stub
		System.out.println("Players Hand:");
		for (int i = 0; i < this.size(); i++) {
			System.out.println(this.get(i).toString());
		}
		System.out.println("");
	}
	
	public int valueOf() {
		// TODO Auto-generated method stub
		int sum = 0;
		for (int i = 0; i < this.size(); i++) {
			sum+= this.get(i).getValue();
		}
		int counter = 0;
		while (sum > 21 && counter != this.size()) {
			if (this.get(counter).getValue() == 11) {
				this.get(counter).setValue(1);
				sum -= 10;
			}
			counter++;
		}
		return sum;
	}

	
	public void draw(Card card) {
		this.add(card);
		
	}
	
	public void removeAll() {
		this.clear();
	}
	
	public boolean containsCard(String type) {
		for (int i = 0; i < this.size(); i++) {
			if (this.get(i).getType().equals(type)) {
				return true;
			}
		}
		return false;
	}
	
	public boolean containsCard(int value) {
		for (int i = 0; i < this.size(); i++) {
			if (this.get(i).getValue() == value) {
				return true;
			}
		}
		return false;
	}
	
	public void setBetAmount(int betAmount) {
		this.betAmount = betAmount;
	}
	
	public int getBetAmount() {
		return betAmount;
	}

}
