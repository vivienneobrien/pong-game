import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.shape.Ellipse;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class Game extends Application {

	public Ellipse ball;
	public Rectangle player1;
	public Rectangle player2;

	public static void main(String[] args) {
		launch(args);
	}

	public void setUp(Stage primaryStage) {
		Group root = new Group();

		// CENTRE X, CENTRE Y, RADIUS X, RADIUS Y
		ball = new Ellipse(200, 100, 10, 10);
		ball.setFill(Color.WHITE);
		player1 = new Rectangle(50, 200, 10, 40);
		player1.setFill(Color.BLUE);
		player2 = new Rectangle(350, 200, 10, 40);
		player2.setFill(Color.BLUE);

		root.getChildren().add(ball);
		root.getChildren().add(player1);
		root.getChildren().add(player2);
		Scene scene = new Scene(root, 400, 400, Color.BLACK);
		primaryStage.setTitle("Pong-Game");
		primaryStage.setScene(scene);
		primaryStage.show();

	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		setUp(primaryStage);

	}
}
