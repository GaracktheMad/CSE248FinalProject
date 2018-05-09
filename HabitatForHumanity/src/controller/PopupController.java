package controller;

import javafx.fxml.FXML;

import javafx.scene.control.Button;

import javafx.scene.control.TextField;
import view.FXMLLoadingController;

import java.io.IOException;

import javafx.event.ActionEvent;

public class PopupController {
	@FXML
	private TextField userNameGen;
	@FXML
	private Button returnToLogin;

	@FXML
	void activateLogin(ActionEvent event) {
		try {
			FXMLLoadingController.login();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void setUsername(String s) {
		userNameGen.setText(s);
	}
}
