package application;

import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import view.FXMLLoadingController;

public class Driver extends Application {

	@Override
	public void start(Stage primaryStage) throws Exception {
		Parent root = FXMLLoadingController.getRoot();
		Scene scene = new Scene(root);
		primaryStage.setScene(scene);
		primaryStage.show();
	}

	public static void main(String[] args) {
		launch(args);
	}

}
