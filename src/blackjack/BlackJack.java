package blackjack;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class BlackJack {
	private Deck deck;
	private JudgeHand judgeHand;
	private ArrayList<PlayerHand> playerHand;
	private int Money;
	private Scanner input = new Scanner(System.in);
	
	public BlackJack() {
		this.deck = new Deck();
		this.judgeHand = new JudgeHand();
		this.playerHand = new ArrayList<PlayerHand>();
		playerHand.add(new PlayerHand());
	}
	
	public void newHand() {
		judgeHand.clear();
		playerHand.clear();
		playerHand.add(new PlayerHand());
		for (int i = 0; i < 2; i++) {
			deck.draw(playerHand.get(0));
			deck.draw(judgeHand);
		}
	}
	
	public ArrayList<String> getOptions( PlayerHand hand, int amount, int totalBet ) {
		ArrayList<String> options = new ArrayList<String>();
		options.addAll(Arrays.asList(new String[] {"hit", "stand"}));
		if (hand.size() == 2 && amount + totalBet < Money) {
			options.add("double down");
			if (hand.get(0).getValue() == hand.get(1).getValue()) {
				options.add("split");
			}
		}
		return options;
	}
	
	public void playGame() {
		
		
		while (true) {
			System.out.println("How many chips would you like to buy?");
			
			 try
		    {
				 Money = Integer.parseInt(input.nextLine());	
				 break;
		    } catch (NumberFormatException ex)
			{
		    	System.out.println("\nInvalid Input");
	    	}
			 
		}
		 System.out.println("What is your name?");
		 String name = input.nextLine();
		 System.out.println("Hello " + name + "!");
		
		while (Money > 0) {
			playRound();
			System.out.println("Would you like to cash out? if so please type out. If not type anything else.");
			String response = input.nextLine();
			if (response.equals("out")) {
				System.out.println("You cashed out with " + Money + " chips!");
				break;
			}
		}
		System.out.println("Thank you for playing " + name + ".");
	}
	
	public void playRound() {
		
		System.out.println("You have " + Money + " chips. \n");
		int amount = 0;
		
		while (true) {
			System.out.println("How much would you like to bet?");
			
			 try
		    {
				 String usrinput = input.nextLine();
				 amount = Integer.parseInt(usrinput);
				 if (amount > 0 && amount <= Money) {
					 break;
				 }
		    } catch (NumberFormatException ex)
			{
		    	
	    	}
		}
		newHand();
		if (playerHand.get(0).containsCard(10) && playerHand.get(0).containsCard("A")) {
			System.out.println("You got blackjack! You win double your bet amount!");
			Money += 2 * amount;
			return;
		}
		
		judgeHand.display();
		
		
		ArrayList<PlayerHand> remainingHand = new ArrayList<PlayerHand>();
		remainingHand.addAll(playerHand);
		playerHand.clear();
		remainingHand.get(0).setBetAmount(amount);
		int totalBet = amount;
		while (remainingHand.size() > 0) {
			int counter = 0;
			for (int i = 0; i < remainingHand.size(); i++) {
				counter++;
				System.out.println("Player Hand #" + (counter));
				remainingHand.get(i).display();
				System.out.println("What is your move?");
				System.out.print("Your options are : ");
				ArrayList<String> options = getOptions(remainingHand.get(i), amount, totalBet);
				options.forEach(option -> System.out.print(option + ", "));
				System.out.println("");
				String choice = input.nextLine();
				if (options.contains(choice)) {
					switch (choice) {
					case "double down":
						
						deck.draw(remainingHand.get(i));
						remainingHand.get(i).setBetAmount(2 * amount);
						playerHand.add(remainingHand.remove(i));
						i--;
						totalBet += amount;
						break;
					case "split":
						counter--;
						remainingHand.add(new PlayerHand());
						remainingHand.get(remainingHand.size() - 1).setBetAmount(amount);
						remainingHand.get(remainingHand.size() - 1).add(remainingHand.get(i).remove(0));
						deck.draw(remainingHand.get(i));
						deck.draw(remainingHand.get(remainingHand.size() - 1));
						i--;
						totalBet+= amount;
						break;
					case "hit":
						deck.draw(remainingHand.get(i));
						if (remainingHand.get(i).valueOf() >= 21) {
							playerHand.add(remainingHand.remove(i));
							i--;
						}
						break;
					case "stand":
						playerHand.add(remainingHand.remove(i));
						i--;
						break;
					}
				}
				else {
					System.out.println();
					System.out.println("Invalid Choice.");
				}
				System.out.println();
			}
			
		}
		while (judgeHand.valueOf() < 17 || (judgeHand.valueOf() == 17 && judgeHand.containsCard("A"))) {
			deck.draw(judgeHand);
		}
		judgeHand.finalDisplay();
		
		for (int i = 0; i < playerHand.size(); i++) {
			if (playerHand.get(i).valueOf() > 21) {
				System.out.println("You busted. You lost on hand number " + (i+1) + ".");
				Money-=playerHand.get(i).getBetAmount();
			}
			else if (judgeHand.valueOf() > 21) {
				System.out.println("The judge busted. You win on hand number " + (i+1) + ".");
				Money+=playerHand.get(i).getBetAmount();
				
			}
			else if (judgeHand.valueOf() > playerHand.get(i).valueOf()) {
				System.out.println("You lost with a score of " + playerHand.get(i).valueOf() + " on hand number " + (i+1) + ".");
				Money-=playerHand.get(i).getBetAmount();
			}
			else if (playerHand.get(i).valueOf() > judgeHand.valueOf()){
				System.out.println("You won with a score of " + playerHand.get(i).valueOf() + " on hand number " + (i+1) + ".");
				Money+=playerHand.get(i).getBetAmount();
			}
			else {
				System.out.println("You pushed with a score of " + judgeHand.valueOf() + " on hand number " + (i+1) + ".");
			}
		}
			
		
	}

	public int getMoney() {
		return Money;
	}
	
}
