import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import javafx.scene.shape.Ellipse;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class Game extends Application {

	public Ellipse ball;
	public Rectangle player1;
	public Rectangle player2;
	public Scene scene;

	public static void main(String[] args) {
		launch(args);
	}

	public void setUp(Stage primaryStage) {
		Group root = new Group();

		// CENTRE X, CENTRE Y, RADIUS X, RADIUS Y
		ball = new Ellipse(200, 100, 10, 10);
		ball.setFill(Color.WHITE);
		player1 = new Rectangle(20, 200, 10, 40);
		player1.setFill(Color.BLUE);
		player2 = new Rectangle(370, 200, 10, 40);
		player2.setFill(Color.BLUE);

		root.getChildren().add(ball);
		root.getChildren().add(player1);
		root.getChildren().add(player2);
		scene = new Scene(root, 400, 400, Color.BLACK);
		primaryStage.setTitle("Pong-Game");
		primaryStage.setScene(scene);
		primaryStage.show();

	}

	public void startGame() {

		scene.setOnKeyPressed(e -> {
			if (e.getCode() == KeyCode.UP) {
				if (PlayerConstraints.checkLowerBound(player1.getY()) == true) {
					// change y position of player 1
					player1.setY(player1.getY() - PlayerConstraints.speed);
					// System.out.println("Up key was pressed");
				}
			} else if (e.getCode() == KeyCode.DOWN) {
				if (PlayerConstraints.checkUpperBound(player1.getY()) == true) {
					player1.setY(player1.getY() + PlayerConstraints.speed);
					// System.out.println("Down key was pressed" + player1.getY());
				}
			} else {
				// System.out.println("Not the right key");
			}
		});

	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		setUp(primaryStage);
		startGame();

	}
}
