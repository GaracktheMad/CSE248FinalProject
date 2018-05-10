package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.PasswordField;
import model.Admin;
import model.Clerk;
import model.ReadOnly;

public class CreateUserController extends CreationController {

	@FXML
	private PasswordField passField;

	@FXML
	private ComboBox<String> rankCombo;

	@Override
	void submit(ActionEvent event) {
		String cmb = rankCombo.getValue();
		if (passField.getText() == null || nameField.getText() == null || cmb == null) {
			setErrorLbl("No fields may be left blank");
		} else {
			if (cmb.equals("Admin")) {
				Admin a = new Admin(nameField.getText(), passField.getText());
				idField.setText(a.getID());
				CurrentUserController.userIsAdmin().createUser(a);
			} else if (cmb.equals("Clerk")) {
				Clerk c = new Clerk(nameField.getText(), passField.getText());
				idField.setText(c.getID());
				CurrentUserController.userIsAdmin().createUser(c);
			} else {
				ReadOnly r = new ReadOnly(nameField.getText(), passField.getText());
				idField.setText(r.getID());
				CurrentUserController.userIsAdmin().createUser(r);
			}
		}
	}

	@FXML
	void initialize() {
		rankCombo.getItems().clear();
		rankCombo.getItems().addAll("ReadOnly", "Clerk", "Admin");
	}
}
