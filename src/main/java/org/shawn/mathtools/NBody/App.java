package org.shawn.mathtools.NBody;

import java.util.*;

import javafx.animation.*;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.util.Duration;

public class App extends Application
{
	private BodyManager bodies;
	private Pane root;
	private Timeline timeline;
	private Scene scene;

	@Override
	public void start(Stage stage)
	{
		bodies = new BodyManager(0.001);

		root = new Pane();
		root.setStyle("-fx-background-color: black;");

		Random ran = new Random();

		for (int i = 0; i < 50; i++)
		{
			bodies.getBodies()
					.add(new Body(700 * (ran.nextDouble() - 0.5), 700 * (ran.nextDouble() - 0.5), 0,
							0.15 * (ran.nextDouble() - 0.5), 0.15 * (ran.nextDouble() - 0.5), 0));
		}

		bodies.getBodies().stream().forEach(body -> {
			body.getDot().relocate(body.getPos().getEntry(0) + 320,
					body.getPos().getEntry(1) + 320);
			root.getChildren().add(body.getDot());
		});

		timeline = new Timeline(new KeyFrame(Duration.millis(0.1), event -> update(bodies)));
		timeline.setCycleCount(Animation.INDEFINITE);
		timeline.play();

		scene = new Scene(root, 640, 640);
		stage.setTitle("Moving Dots");
		stage.setScene(scene);
		stage.show();
	}

	private void update(BodyManager bodies)
	{
		double width = scene.getWidth() / 2;
		double height = scene.getHeight() / 2;
		bodies.update();
		bodies.getBodies().stream().forEach(body -> body.getDot()
				.relocate(body.getPos().getEntry(0) + width, body.getPos().getEntry(1) + height));
	}

	public static void main(String[] args)
	{
		launch();
	}

}