package controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import model.Data;
import model.InvalidListTypeException;
import view.FXMLLoadingController;

import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;

public class TaskPaneController implements LogsOut {
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
	private Button myOrders;
	@FXML
	private Button manageOrders;
	@FXML
	private Button invoiceBtn;

	@FXML
	void activateMyOrders(ActionEvent event) {
		try {
			FXMLLoadingController.list("my orders");
		} catch (IOException | InvalidListTypeException e) {
			e.printStackTrace();
		}
	}

	@FXML
	void masterInvoiceGen(ActionEvent event) {
		try {
			FXMLLoadingController.invoiceGen();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@FXML
	void orderManagement(ActionEvent event) {
		try {
			FXMLLoadingController.list("orders");
		} catch (IOException | InvalidListTypeException e) {
			e.printStackTrace();
		}
	}

	@FXML
	void activateProductList(ActionEvent event) {
		try {
			FXMLLoadingController.list("Items");
		} catch (IOException e) {
			e.printStackTrace();
		} catch (InvalidListTypeException e) {
			e.printStackTrace();
		}
	}

	@FXML
	void attemptPassChange(ActionEvent event) {
		int result = CurrentUserController.setPassword(currentPass.getText(), newPass.getText(),
				newPassRetype.getText());
		if (result == 0) {
			changePassLbl.setText("New Passwords Do Not Match!");
		} else if (result == -1) {
			changePassLbl.setText("PASSWORD NOT RECOGNIZED");
		} else if (result == 1) {
			changePassLbl.setText("Success!");
			try {
				Data.save();
			} catch (IOException e) {
				e.printStackTrace();
			}
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
		if (CurrentUserController.userIsClerk() != null) {
			createItemBtn.setVisible(true);
			createItemBtn.setDisable(false);
			invoiceBtn.setVisible(true);
			invoiceBtn.setDisable(false);
			manageOrders.setVisible(true);
			manageOrders.setDisable(false);
		}
		userIDBox.setText(CurrentUserController.getID().toString());
	}
}
