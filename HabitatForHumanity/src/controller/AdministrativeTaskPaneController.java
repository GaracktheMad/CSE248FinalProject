package controller;

import javafx.fxml.FXML;

import javafx.scene.control.Button;
import view.FXMLLoadingController;

import java.io.IOException;

import javafx.event.ActionEvent;

public class AdministrativeTaskPaneController extends TaskPaneController{
	@FXML
	private Button userManagementButton;

	// Event Listener on Button[#userManagementButton].onAction
	@FXML
	public void activateUserList(ActionEvent event) {
		try {
			FXMLLoadingController.list(false);
		} catch (IOException e) {
			System.exit(1);
		}
	}
}
