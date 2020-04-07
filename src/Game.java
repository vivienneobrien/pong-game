import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Bounds;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Ellipse;
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

	public void setUp(Stage primaryStage) {
		root = new Group();

		// CENTRE X, CENTRE Y, RADIUS X, RADIUS Y
		ball = new Circle(10, Color.WHITE);
		ball.relocate(200, 200);
		player1 = new Rectangle(20, 200, PlayerParameters.radiusX, PlayerParameters.radiusY);
		player1.setFill(Color.BLUE);
		player2 = new Rectangle(370, 200, PlayerParameters.radiusX, PlayerParameters.radiusY);
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
			/**
			 * key up
			 */
			if (e.getCode() == KeyCode.UP) {
				if (PlayerConstraints.checkLowerBound(player1.getY()) == true) {
					// change y position of player 1
					player1.setY(player1.getY() - PlayerParameters.speed);
					// System.out.println("Up key was pressed");
				}
				/**
				 * key down
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
		 * Circle movement
		 */
		Timeline timeline = new Timeline(new KeyFrame(Duration.millis(20), 
                new EventHandler<ActionEvent>() {

        	double dx = 7; //Step on x or velocity
        	double dy = 3; //Step on y
        	
            @Override
            public void handle(ActionEvent t) {
            	//move the ball
            	ball.setLayoutX(ball.getLayoutX() + dx);
            	ball.setLayoutY(ball.getLayoutY() + dy);

                Bounds bounds = root.getBoundsInLocal();
                
                //If the ball reaches the left or right border make the step negative
                if(ball.getLayoutX() <= (bounds.getMinX() + ball.getRadius()) || 
                        ball.getLayoutX() >= (bounds.getMaxX() - ball.getRadius()) ){

                	dx = -dx;

                }

                //If the ball reaches the bottom or top border make the step negative
                if((ball.getLayoutY() >= (bounds.getMaxY() - ball.getRadius())) || 
                        (ball.getLayoutY() <= (bounds.getMinY() + ball.getRadius()))){

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
