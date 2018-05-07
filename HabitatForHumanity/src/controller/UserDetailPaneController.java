package controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import model.Data;
import model.Identity;
import view.FXMLLoadingController;
import java.io.IOException;
import java.util.Stack;
import javafx.event.ActionEvent;

public class UserDetailPaneController {
	@FXML
	private Button editBtn;
	@FXML
	private Button homeBtn;
	@FXML
	private Button backBtn;
	@FXML
	private Button saveBtn;
	@FXML
	private Button deleteBtn;
	@FXML
	private TextField emailField;
	@FXML
	private TextField idField;
	@FXML
	private TextField rankField;
	@FXML
	private VBox passChangeBox;

	@FXML
	private PasswordField passchangeField;

	private static Identity identity;

	// Event Listener on Button[#editBtn].onAction
	@FXML
	void edit(ActionEvent event) {
		saveBtn.setDisable(false);
		passChangeBox.setDisable(false);
		editBtn.setDisable(true);
		backBtn.setDisable(true);
		homeBtn.setDisable(true);
		emailField.setEditable(true);
		rankField.setEditable(true);
	}

	@FXML
	void deleteThis(ActionEvent event) {

	}

	// Event Listener on Button[#homeBtn].onAction
	@FXML
	void goHome(ActionEvent event) {
		try {
			FXMLLoadingController.taskPane();
		} catch (IOException e) {
			System.exit(1);
		}
	}

	// Event Listener on Button[#backBtn].onAction
	@FXML
	void returnToList(ActionEvent event) {
		try {
			FXMLLoadingController.list(false);
		} catch (IOException e) {
			System.exit(1);
		}
	}

	public static void setIdentity(Identity id) {
		identity = id;
	}

	@FXML
	void saveFields(ActionEvent event) {
		String rank = rankField.getText();
		if (rank.substring(0, 1).equalsIgnoreCase(identity.getClassification()) == true || rank == null) {
			if (emailField.getText() != null) {
				CurrentUserController.userIsAdmin().editEmail(identity, emailField.getText());
			}
		} else if (rankField.getText().equalsIgnoreCase("Admin") == true
				|| rankField.getText().equalsIgnoreCase("Clerk") == true
				|| rankField.getText().equalsIgnoreCase("ReadOnly") == true) {

		}

	}

	@FXML
	void initialize() {
		Stack<String> userItems = Data.getCopyUser(identity).details();
		idField.setText(userItems.pop());
		emailField.setText(userItems.pop());
		rankField.setText(userItems.pop());
	}

}
