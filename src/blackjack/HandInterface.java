package blackjack;

public interface HandInterface {
	
	public abstract void display();
	
	public abstract int valueOf();
	
	public abstract void draw( Card card );
	
	public abstract void removeAll();
}
