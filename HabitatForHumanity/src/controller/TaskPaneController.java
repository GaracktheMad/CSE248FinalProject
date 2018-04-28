package controller;

import javafx.fxml.FXML;

import javafx.scene.control.Button;

import javafx.scene.control.TextField;
import view.FXMLLoadingController;

import java.io.IOException;

import javafx.event.ActionEvent;

import javafx.scene.control.Label;

import javafx.scene.control.PasswordField;

public class TaskPaneController implements IsTaskPane {
	@FXML
	private Button productViewBtn;
	@FXML
	private TextField userIDBox;
	@FXML
	private Label changePassLbl;
	@FXML
	private PasswordField currentPass;
	@FXML
	private PasswordField newPass;
	@FXML
	private PasswordField newPassRetype;
	@FXML
	private Button submitButton;
	@FXML
	private Button exitButton;

	// Event Listener on Button[#productViewBtn].onAction
	@FXML
	public void activateProductList(ActionEvent event) {
		try {
			FXMLLoadingController.list(true);
		} catch (IOException e) {
			System.exit(1);
		}
	}
	// Event Listener on Button[#submitButton].onAction
	@FXML
	public void attemptPassChange(ActionEvent event) {
		int result = changePassword(currentPass.getText(), newPass.getText(), newPassRetype.getText());
		if(result == 0) {
			changePassLbl.setText("New Passwords Do Not Match!");
		}
		else if(result == -1) {
			changePassLbl.setText("PASSWORD NOT RECOGNIZED");
		}
		else if(result == 1) {
			changePassLbl.setText("Success!");
		}
		else {
			changePassLbl.setText("ERROR");
		}
	}
	// Event Listener on Button[#exitButton].onAction
	@FXML
	public void logout(ActionEvent event) {
		exit();
	}
}
