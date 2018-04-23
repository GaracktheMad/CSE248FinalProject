package view;

import java.io.IOException;

import controller.FXMLLoadingController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import model.Data;
import model.Identity;
import model.ReadOnly;

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
		String answer = Data.userStatus(i);
		if (answer.equals("n")) {
			confirmationLbl.setText("INVALID USERNAME OR PASSWORD");
		} else if (answer.equals("a")) {
			try {
				FXMLLoadingController.setFXML("view/AdministrativeTaskPane.fxml");
			} catch (IOException e1) {
				System.exit(1);
			}
		} else if (answer.equals("c")) {
			try {
				FXMLLoadingController.setFXML("view/ClerkTaskPane.fxml");
			} catch (IOException e1) {
				System.exit(1);
			}
		} else if (answer.equals("ro")) {
			try {
				FXMLLoadingController.setFXML("view/ReadOnlyTaskPane.fxml");
			} catch (IOException e1) {
				System.exit(1);
			}
		} else {
			confirmationLbl.setText("An error has occured. Check your username and password, and try again.");
		}
	}

	public void handleCancel(ActionEvent e) {
		System.exit(0);
	}

	public void viewSwap(ActionEvent e) {
		if (isLoginScreen == true) {
			isLoginScreen = true;
			try {
				FXMLLoadingController.setFXML("view/Login.fxml");
			} catch (IOException e1) {
				System.exit(1);
			}
		}
		if (isLoginScreen == false) {
			isLoginScreen = true;
			try {
				FXMLLoadingController.setFXML("view/CreateAccount.fxml");
			} catch (IOException e1) {
				System.exit(1);
			}
		}

	}

	public void handleNewAccount(ActionEvent e) {
		if(username.getText() != null || password.getText() != null) {
			ReadOnly r = new ReadOnly(username.getText(), password.getText());
		Data.createAccount(r);
		confirmationLbl.setText("CREATION SUCCESS! Your username is: " + r.getKey().toString());
		}else {
			confirmationLbl.setText("INVALID EMAIL OR PASSWORD");
		}

	}

}
