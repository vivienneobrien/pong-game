import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
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
	public Text stopPlayer1;
	public Text stopPlayer2;
	private int previousY2 = -1;


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
		player1 = new Rectangle(Player.player1X, Player.playerStartingY, Player.radiusX, Player.radiusY);
		player1.setFill(Color.BLUE);
		player2 = new Rectangle(Player.player2X, Player.playerStartingY, Player.radiusX, Player.radiusY);
		player2.setFill(Color.BLUE);
		
		stopPlayer1 = new Text(Integer.toString(Player.score1));
		stopPlayer1.setFill(Color.WHITE);
		stopPlayer1.relocate(130, 20);
		stopPlayer1.setStyle("-fx-text-fill: white; -fx-font-size: 3em");
		stopPlayer2 = new Text(Integer.toString(Player.score2));
		stopPlayer2.setFill(Color.WHITE);
		stopPlayer2.relocate(240, 20);

		stopPlayer2.setStyle("-fx-text-fill: white; -fx-font-size: 3em");
		root.getChildren().addAll(ball, player1, player2, stopPlayer1, stopPlayer2);
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
					player1.setY(player1.getY() - Player.speed);
					// System.out.println("Up key was pressed");
				}
				/**
				 * Key down
				 */
			} else if (e.getCode() == KeyCode.DOWN) {
				if (PlayerConstraints.checkUpperBound(player1.getY()) == true) {
					player1.setY(player1.getY() + Player.speed);
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

				/*
				 * If ball is past player1's goal
				 */
				if (ball.getLayoutX() < Player.player1X) {
					// reset ball to the middle of the pitch
					ball.setLayoutX(Ball.startingPositionX);
					ball.setLayoutY(Ball.startingPositionY);
					// change direction of ball
					dx = -dx;

					// TODO: increase score for player2
					Player.score2++;
					stopPlayer2.setText(Integer.toString(Player.score2));

					// TODO: Wait 1 sec

				}

				/*
				 * If ball is past player2's goal
				 */
				if (Player.player2X + Player.radiusX < ball.getLayoutX()) {
					// reset ball to the middle of the pitch
					ball.setLayoutX(Ball.startingPositionX);
					ball.setLayoutY(Ball.startingPositionY);
					// change direction of ball
					dx = -dx;

					// TODO: increase score for player1
					Player.score1++;
					stopPlayer1.setText(Integer.toString(Player.score1));
					
					// TODO: Wait 1 sec

				}
				
				/*
				 * Check where ball is going for player 2
				 * */ 
				if(dx > 0 && ball.getLayoutX() >= 200 && ball.getLayoutX() <= (200 + 5*dx)) {
					// Vector function to check where ball is going WITHOUT checking boundaries touched
					int y2 = (int) ((370-ball.getLayoutX())/dx*dy + ball.getLayoutY());
					// If direction is upwards 
					if(y2 < 0) y2 = -y2;
					// If odd amount of boundaries touched
					if(y2/400%2 == 1) y2 = 400-y2%400;
			
					// Move player 2 to location 
					y2 = y2-Player.radiusY/2;
					
					// Check if corresponding y2s
					if(previousY2 != y2) {					
						// Save y2
						previousY2 = y2; 
						
						// Create thread to move player 2
						player2.setY(y2);
//						(new Player2MovementThread(y2, player2)).start();
					}
				}
			}
		}));
		/**
		 * How many times the game will reset if you loose. play() llows the timeline to
		 * begin
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
