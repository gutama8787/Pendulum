import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.geometry.Point2D;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.stage.Stage;
import javafx.util.Duration;

public class Pendulum extends Application {

	double width = 500;
	double height = 600;

	double originx = (width / 2);
	double originy = (height / 2);
	
	double bobx;
	double boby;
	double radius = 10;
	double len = 100;
	
	static double angle = 3*Math.PI/4;
	static double aVel = 0.0;
	static double aAcc = 0.0;
	Line line;
	Circle circle;
	Circle point;
	Pane pane = new Pane();
	Pane panePoint = new Pane();

	@Override
	public void start(Stage stage) throws Exception {
		// TODO Auto-generated method stub
		// update();
		Pane p = new Pane();
		Timeline animation = new Timeline(new KeyFrame(Duration.millis(10), e -> update()));
		animation.setCycleCount(Timeline.INDEFINITE);
		animation.play();
		p.getChildren().addAll(pane,panePoint);
		Scene scene = new Scene(p, width, height);
		stage.setScene(scene);
		stage.show();

	}

	public static void main(String[] args) {
		launch(args);
	}

	public void update() {
		pane.getChildren().clear();
		bobx = originx + (len * Math.sin(angle));
		boby = originy + (len * Math.cos(angle));
		
		line = new Line(originx,originy,bobx,boby);
		circle = new Circle(bobx, boby, radius);
		point = new Circle(bobx,boby,1);
		panePoint.getChildren().addAll(point);
		pane.getChildren().addAll(circle, line);
		aAcc = -0.01 * Math.sin(angle);
		
		angle+=aVel;
		aVel+=aAcc;
		
		aVel*=0.99;
	}
}
