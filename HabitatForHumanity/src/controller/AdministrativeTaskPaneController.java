package controller;

import javafx.fxml.FXML;

import javafx.scene.control.Button;
import view.FXMLLoadingController;

import java.io.IOException;

import javafx.event.ActionEvent;

public class AdministrativeTaskPaneController extends TaskPaneController {
	@FXML
	private Button userManagementButton;
	@FXML
	private Button createUserBtn;

	@FXML
	void createUser(ActionEvent event) {
		try {
			FXMLLoadingController.createUser();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@FXML
	void activateUserList(ActionEvent event) {
		try {
			FXMLLoadingController.list(false);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
