package controller;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import model.Data;
import model.Identity;
import model.ReadOnly;
import view.FXMLLoadingController;

public class Login {

	@FXML
	Button submit;

	@FXML
	Button create;

	@FXML
	Button cancel;

	@FXML
	TextField username;

	@FXML
	PasswordField password;

	@FXML
	Label confirmationLbl;

	@FXML
	Button viewSwap;

	private boolean isLoginScreen = true;

	public void handleSubmit(ActionEvent e) {
		Identity i = new Identity(username.getText().substring(0, 1), Integer.valueOf(username.getText().substring(1)));
		boolean state = false;
		try {
			state = FXMLLoadingController.loginToTaskPane(i);
		} catch (IOException e1) {
			System.exit(1);
		}
		if (state == false) {
			confirmationLbl.setText("INVALID USERNAME OR PASSWORD");
		}
	}

	public void handleCancel(ActionEvent e) {
		System.exit(0);
	}

	public void viewSwap(ActionEvent e) {
		if (isLoginScreen == false) {
			isLoginScreen = true;
			try {
				FXMLLoadingController.login();;
			} catch (IOException e1) {
				System.exit(1);
			}
		}
		if (isLoginScreen == true) {
			isLoginScreen = false;
			try {
				FXMLLoadingController.createAccount();
			} catch (IOException e1) {
				System.exit(1);
			}
		}

	}

	public void handleNewAccount(ActionEvent e) {
		if (username.getText() != null || password.getText() != null) {
			ReadOnly r = new ReadOnly(username.getText(), password.getText());
			Data.createAccount(r);
			try {
				FXMLLoadingController.popUp(r.getKey().toString());
			} catch (IOException e1) {
				System.exit(1);
			}
		} else {
			confirmationLbl.setText("INVALID EMAIL OR PASSWORD");
		}

	}

}
