import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;

public class PlayerConstraints {

	public final static int upperBound = 400 - PlayerParameters.radiusY;
	public final static int lowerBound = 0;

	public static boolean checkLowerBound(double y) {
		if (lowerBound < y) {
			return true;
		} else {
			return false;
		}
	}

	public static boolean checkUpperBound(double y) {
		if (y < upperBound) {
			return true;
		} else {
			return false;
		}
	}

	public static boolean checkBallTouchesPlayer(Circle ball, Rectangle player1, Rectangle player2) {

		// if it touches the player
		// in range of y of player

		// player.getY < ball.getY < player.getY + radiusY + check X
		if (player1.getY() < ball.getLayoutY() && ball.getLayoutY() < player1.getY() + PlayerParameters.radiusY) {
			return true;
		} else {
			return false;
		}

	}

}
