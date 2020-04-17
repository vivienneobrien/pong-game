import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
/**
 * Player Constraints class
 * @author sebastianstanici & vivienneobrien
 * @version 03/04/20
 *
 */
public class PlayerConstraints {

	public final static int upperBound = 400 - Player.radiusY;
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
		// layout is centre of ball
		if (player1.getX() < ball.getLayoutX() + ball.getRadius()
				&& ball.getLayoutX() - ball.getRadius() < player1.getX() + Player.radiusX
				&& player1.getY() < ball.getLayoutY() + ball.getRadius()
				&& ball.getLayoutY() - ball.getRadius() < player1.getY() + Player.radiusY) {
			return true;
		} else if (player2.getX() < ball.getLayoutX() + ball.getRadius()
				&& ball.getLayoutX() - ball.getRadius() < player2.getX() + Player.radiusX
				&& player2.getY() < ball.getLayoutY() + ball.getRadius()
				&& ball.getLayoutY() - ball.getRadius() < player2.getY() + Player.radiusY) {
			return true;
		} else {
			return false;
		}

	}

}
