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
	@FXML
	private Button createItemBtn;

	@FXML
	void activateProductList(ActionEvent event) {
		try {
			FXMLLoadingController.list(true);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@FXML
	void attemptPassChange(ActionEvent event) {
		int result = changePassword(currentPass.getText(), newPass.getText(), newPassRetype.getText());
		if (result == 0) {
			changePassLbl.setText("New Passwords Do Not Match!");
		} else if (result == -1) {
			changePassLbl.setText("PASSWORD NOT RECOGNIZED");
		} else if (result == 1) {
			changePassLbl.setText("Success!");
		} else {
			changePassLbl.setText("ERROR");
		}
	}

	@FXML
	void createItem(ActionEvent event) {
		try {
			FXMLLoadingController.createItem();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	@FXML
	void logout(ActionEvent event) {
		exit();
	}
	

    @FXML
    void initialize() {
    	if(CurrentUserController.userIsClerk() != null) {
    		createItemBtn.setVisible(true);
    		createItemBtn.setDisable(false);
    	}
    	userIDBox.setText(CurrentUserController.getID().toString());
    }
}
