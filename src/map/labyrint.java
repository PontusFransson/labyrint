package map;

import java.io.File;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class labyrint extends Application {

	@Override
	public void start(Stage primaryStage) throws Exception {

		Map map = MapInterpreter.interpretMap(new File("map.txt"));

		Scene scene = new Scene(map, map.getWidth(), map.getHeight());

		primaryStage.setScene(scene);
		primaryStage.show();

		MapSolver mapsolver = new MapSolver(map);
		AnimationTimer at = new AnimationTimer() {
			long before = 0;

			@Override
			public void handle(long now) {
				// TODO Auto-generated method stub
				if (now - before >= 1_0_000_0000) {
					mapsolver.showNextPath();
					before = now;
				}
			}
		};
		at.start();
	}

	public static void main(String[] args) {
		launch();
	}

}