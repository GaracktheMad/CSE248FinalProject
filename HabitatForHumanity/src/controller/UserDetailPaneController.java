package controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import model.Admin;
import model.Clerk;
import model.Data;
import model.Identity;
import model.User;
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

	private Identity identity;
	
	private void swapMode() {
		saveBtn.setDisable(!saveBtn.isDisabled());
		passChangeBox.setDisable(!passChangeBox.isDisabled());
		editBtn.setDisable(!editBtn.isDisabled());
		backBtn.setDisable(!backBtn.isDisabled());
		homeBtn.setDisable(!homeBtn.isDisabled());
		emailField.setEditable(!emailField.isEditable());
		rankField.setEditable(!rankField.isEditable());
		deleteBtn.setDisable(!deleteBtn.isDisabled());
		deleteBtn.setVisible(!deleteBtn.isVisible());
	}

	@FXML
	void edit(ActionEvent event) {
		swapMode();
	}

	@FXML
	void deleteThis(ActionEvent event) {
		CurrentUserController.userIsAdmin().removeUser(identity);
		try {
			FXMLLoadingController.list(false);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@FXML
	void goHome(ActionEvent event) {
		try {
			FXMLLoadingController.taskPane();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@FXML
	void returnToList(ActionEvent event) {
		try {
			FXMLLoadingController.list(false);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void setIdentity(Identity id) {
		identity = id;
	}

	@FXML
	void saveFields(ActionEvent event) {
		String rank = rankField.getText().trim();
		User u = Data.getCopyUser(identity);
		Admin currentUser = CurrentUserController.userIsAdmin();
		String email = emailField.getText().trim();
		String pass = passchangeField.getText().trim();
		if (rank.substring(0, 1).equalsIgnoreCase(identity.getClassification()) == true || rank == null || rank.equals("") == true) {
			if (email != null) {
				currentUser.editEmail(identity, email);
			}
			if (pass != null) {
				currentUser.editPass(identity, pass);
			}
		} else {
			if (email == null || email.equals("") == true) {
				email = u.getName();
			}
			if (pass == null || pass.equals("") == true) {
				u.setName(email);
			} else {
				u.setName(email);
				u.setPassword(pass);
			}
			if (rankField.getText().equalsIgnoreCase("Admin") == true) {
				Admin a;
				a = new Admin(u, u.getKey());
				currentUser.removeUser(identity);
				currentUser.createUser(a);
			} else if (rankField.getText().equalsIgnoreCase("Clerk") == true) {
				Clerk c;
				c = new Clerk(u, u.getKey());
				currentUser.removeUser(identity);
				currentUser.createUser(c);
			} else if (rankField.getText().equalsIgnoreCase("ReadOnly") == true) {
				Admin ro;
				ro = new Admin(u, u.getKey());
				currentUser.removeUser(identity);
				currentUser.createUser(ro);
			}
		}
		swapMode();
	}

	@FXML
	void initialize() {
		Stack<String> userItems = Data.getCopyUser(identity).details();
		idField.setText(userItems.pop());
		emailField.setText(userItems.pop());
		rankField.setText(userItems.pop());
	}

}
