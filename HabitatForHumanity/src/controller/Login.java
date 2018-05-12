package controller;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import model.Identity;
import view.FXMLLoadingController;

public class Login  {
	@FXML
	private TextField username;

	@FXML
	private PasswordField password;

	@FXML
	private Button submit;

	@FXML
	private Button cancel;

	@FXML
	private Label confirmationLbl;

	@FXML
	private Button newAccountButton;

	@FXML
	void handleSubmit(ActionEvent event) {
		Identity i;
		boolean state = false;
		try {
			i = new Identity(username.getText().substring(0, 1), Integer.valueOf(username.getText().substring(1)));
			state = FXMLLoadingController.loginToTaskPane(i, password.getText());
		} catch (IOException e) {
			e.printStackTrace();
		} catch (NumberFormatException e) {
			e.printStackTrace();
		}
		if (state == false) {
			confirmationLbl.setText("INVALID USERNAME OR PASSWORD");
		}
	}

	@FXML
	void handleCancel(ActionEvent e) {
		System.exit(0);
	}

	@FXML
	void viewSwap(ActionEvent e) {
		try {
			FXMLLoadingController.createAccount();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}

}
