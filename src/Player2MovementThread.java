import javafx.application.Platform;
import javafx.scene.shape.Rectangle;

public class Player2MovementThread extends Thread {
	public int y2; 
	public Rectangle player2;
	
	public Player2MovementThread(int y2, Rectangle player2) {
		this.y2 = y2; 
		this.player2 = player2;
	}
	
	public void run() {
		// Calculating direction 
		if(player2.getY() > y2) {
			// Move player 2 up
			for(double i = player2.getY(); i > y2; i--) {
				player2.setY(i);
			}
		} else {
			// Move player 2 down
			for(double i = player2.getY(); i < y2; i++) {
				player2.setY(i);
			}
		}
		System.out.println("Thread ended");
	}
}
