package controller;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import model.Data;
import model.ReadOnly;
import view.FXMLLoadingController;

public class CreateAccountController {

	@FXML
	private TextField username;

	@FXML
	private PasswordField password;

	@FXML
	private Button create;

	@FXML
	private Button cancel;

	@FXML
	private Label confirmationLbl;

	@FXML
	private Button newAccountButton;

	@FXML
	void handleCancel(ActionEvent event) {
		System.exit(0);
	}

	@FXML
	void handleNewAccount(ActionEvent event) {
		if ((username.getText() != null && password.getText() != null)
				&& (username.getText() != "" && password.getText() != "")) {
			ReadOnly r = new ReadOnly(username.getText(), password.getText());
			Data.createAccount(r);
			try {
				FXMLLoadingController.popUp(r.getID());
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else {
			confirmationLbl.setText("INVALID EMAIL OR PASSWORD");
		}
	}

	@FXML
	void viewSwap(ActionEvent event) {
		try {
			FXMLLoadingController.login();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
