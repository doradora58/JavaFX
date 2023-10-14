package application;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.fxml.FXMLLoader;


public class Main extends Application {
	FleetController sampleController;
	FleetModule fleetModule ;

	@Override
	public void start(Stage primaryStage) {
		try {
			//			Parent root = FXMLLoader.load(getClass().getResource("Sample.fxml"));
			this.fleetModule = new FleetModule();
			
			FXMLLoader loader = new FXMLLoader(this.getClass().getResource("Sample.fxml"));
			sampleController = new FleetController(fleetModule);
			loader.setController(sampleController);

			Parent root = loader.load();
			Scene scene = new Scene(root);
			primaryStage.setTitle("FXML Welcome");
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		launch(args);
	}
}
