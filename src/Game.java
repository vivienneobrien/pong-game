import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
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
	 * Sets the game up using two players and a ball
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
		primaryStage.setTitle("Pong-Game");
		primaryStage.setScene(scene);
		primaryStage.show();
	}

	/**
	 * Start method is used set up the game for when the user opens the window
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
		 * Circle movement Timeline = animation
		 */
		Timeline timeline = new Timeline(new KeyFrame(Duration.millis(20), new EventHandler<ActionEvent>() {

			/**
			 * Distance
			 */
			double dx = 4; // Step on x or velocity fast on x axis speed
			double dy = 2; // Step on y faster on y axis

			@Override
			public void handle(ActionEvent t) {
				// move the ball
				ball.setLayoutX(ball.getLayoutX() + dx);
				ball.setLayoutY(ball.getLayoutY() + dy);

				// keeps in bounds
				// If the ball reaches the left or right border make the step negative
				if (ball.getLayoutX() <= (WindowConstraints.minimumX + ball.getRadius())
						|| ball.getLayoutX() >= (WindowConstraints.maximumX - ball.getRadius())
						|| PlayerConstraints.checkBallTouchesPlayer(ball, player1, player2)) {

					dx = -dx;

				}

				// If the ball reaches the bottom or top border make the step negative
				if ((ball.getLayoutY() >= (WindowConstraints.maximumY - ball.getRadius()))
						|| (ball.getLayoutY() <= (WindowConstraints.minimumY + ball.getRadius()))) {

					dy = -dy;

				}
			}
		}));
		timeline.setCycleCount(Timeline.INDEFINITE);
		timeline.play();

	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		setUp(primaryStage);
		startGame();

	}
}
