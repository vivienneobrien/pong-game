import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 * Game class is a class that creates a pong game.
 * 
 * @authors sebastianstanici & vivienneobrien
 * @version begin date: 03/04/20 
 */
public class Game extends Application {

	public Circle ball;
	public Rectangle player1;
	public Rectangle player2;
	public Scene scene;
	public Group root;

	public static void main(String[] args) {
		launch(args);
	}

	/**
	 * setUp method sets the game up using two players and a ball Adds all contents
	 * to the stage
	 * 
	 * @param primaryStage
	 */
	public void setUp(Stage primaryStage) {
		root = new Group();
		ball = new Circle(10, Color.WHITE);
		ball.relocate(200, 200);
		player1 = new Rectangle(20, 200, PlayerParameters.radiusX, PlayerParameters.radiusY);
		player1.setFill(Color.BLUE);
		player2 = new Rectangle(370, 200, PlayerParameters.radiusX, PlayerParameters.radiusY);
		player2.setFill(Color.BLUE);
		root.getChildren().addAll(ball, player1, player2);
		scene = new Scene(root, 400, 400, Color.BLACK);
		primaryStage.setScene(scene);
		primaryStage.setTitle("Pong-Game");
		primaryStage.show();
	}

	/**
	 * Start method is used set up the game for when the user opens the window.
	 * Contains actions that allow player 1, player 2 and ball to move.
	 */
	public void startGame() {
		/**
		 * Key up
		 */
		scene.setOnKeyPressed(e -> {
			if (e.getCode() == KeyCode.UP) {
				if (PlayerConstraints.checkLowerBound(player1.getY()) == true) {
					// change y position of player 1
					player1.setY(player1.getY() - PlayerParameters.speed);
					// System.out.println("Up key was pressed");
				}
				/**
				 * Key down
				 */
			} else if (e.getCode() == KeyCode.DOWN) {
				if (PlayerConstraints.checkUpperBound(player1.getY()) == true) {
					player1.setY(player1.getY() + PlayerParameters.speed);
					// System.out.println("Down key was pressed" + player1.getY());
				}
			} else {
				// System.out.println("Not the right key");
			}
		});
		/**
		 * Timeline sets up the animation
		 */
		Timeline timeline = new Timeline(new KeyFrame(Duration.millis(20), new EventHandler<ActionEvent>() {

			/**
			 * Distance
			 */
			double dx = 4; // Makes the ball is fast on x axis
			double dy = 2; // Mkaes the ball fast on y axis

			@Override
			public void handle(ActionEvent t) {
				/**
				 * adds the speed of the ball. Layout: the way in which the parts of something
				 * are arranged or laid out -> on x and y axis
				 */
				ball.setLayoutX(ball.getLayoutX() + dx);
				ball.setLayoutY(ball.getLayoutY() + dy);

				/**
				 * Keeps in bounds If the ball reaches the left or right border make the step
				 * negative
				 */
				if (ball.getLayoutX() <= (WindowConstraints.minimumX + ball.getRadius())
						|| ball.getLayoutX() >= (WindowConstraints.maximumX - ball.getRadius())
						|| PlayerConstraints.checkBallTouchesPlayer(ball, player1, player2)) {

					dx = -dx;

				}

				/**
				 * If the ball reaches the bottom or top border make the step negative
				 */
				if ((ball.getLayoutY() >= (WindowConstraints.maximumY - ball.getRadius()))
						|| (ball.getLayoutY() <= (WindowConstraints.minimumY + ball.getRadius()))) {

					dy = -dy;

				}
			}
		}));
		/**
		 * How many times the game will reset if you loose. play() llows the timeline to begin
		 */
		timeline.setCycleCount(Timeline.INDEFINITE);
		timeline.play();

	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		setUp(primaryStage);
		startGame();

	}
}
