package controller;

import javafx.fxml.FXML;

import javafx.scene.control.Button;

import javafx.scene.control.TextField;
import view.FXMLLoadingController;

import java.io.IOException;

import javafx.event.ActionEvent;

public class PopupController {
	@FXML
	private static TextField userNameGen;
	@FXML
	private Button returnToLogin;
	
	// Event Listener on Button[#returnToLogin].onAction
	@FXML
	public void activateLogin(ActionEvent event) {
		try {
			FXMLLoadingController.login();
		} catch (IOException e) {
			System.exit(1);
		}
	}
	
	public static void setUsername(String s) {
		userNameGen.setText(s);
	}
}
