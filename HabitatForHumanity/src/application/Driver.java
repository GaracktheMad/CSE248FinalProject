package application;

import javafx.application.Application;
import javafx.stage.Stage;
import view.FXMLLoadingController;

public class Driver extends Application {

	@Override
	public void start(Stage primaryStage) throws Exception {
		FXMLLoadingController.setStage(primaryStage);
		primaryStage.show();
	}

	public static void main(String[] args) {
		launch(args);
	}

}
