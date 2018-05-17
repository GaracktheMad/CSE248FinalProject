package controller;

import java.io.IOException;
import java.util.Stack;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import model.Data;
import model.Identity;
import model.InvalidListTypeException;
import model.Order;
import view.FXMLLoadingController;

public class PurchaseDetailPaneController {

	@FXML
	private TextField orderNumber;

	@FXML
	private TextField itemField;

	@FXML
	private TextField priceField;

	@FXML
	private TextField quantityField;

	@FXML
	private TextField status;

	@FXML
	private HBox personDisplay;

	@FXML
	private TextField idField;

	@FXML
	private Button completionBtn;

	@FXML
	private Button homeBtn;

	@FXML
	private Button deleteBtn;

	@FXML
	private Button backBtn;

	@FXML
	private TextArea invoiceField;

	private Order order;
	private String list;

	@FXML
	void completeToggle(ActionEvent event) {
		CurrentUserController.userManipulatesOrders().setStatus(order.getKey(), !order.isCompleted());
		try {
			Data.save();
		} catch (IOException e) {
			e.printStackTrace();
		}order.setCompleted(!order.isCompleted());
		if(order.isCompleted() == true) {
			status.setText("Completed!");
		}else {
			status.setText("Order in progress...");
		}
		invoiceField.setText(order.toString());
	}

	@FXML
	void deleteThis(ActionEvent event) {
		if (CurrentUserController.hasOrderManipulationPrivs() == true) {
			CurrentUserController.userManipulatesOrders().cancelOrder(order);
		} else {
			CurrentUserController.getUser().cancelMyOrder(order);
		}
		try {
			Data.save();
			FXMLLoadingController.list(list);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (InvalidListTypeException e) {
			e.printStackTrace();
		}
	}

	@FXML
	void goBack(ActionEvent event) {
		try {
			FXMLLoadingController.list(list);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (InvalidListTypeException e) {
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

	public void data(Identity identity, String listState) {
		list = listState;
		order = Data.getCopyOrder(identity);
		Stack<String> s = order.details();
		if (CurrentUserController.hasOrderManipulationPrivs() == true
				&& CurrentUserController.getID().equals(order.getPerson()) == false) {
			personDisplay.setVisible(true);
		}
		orderNumber.setText(s.pop());
		idField.setText(s.pop());
		itemField.setText(s.pop());
		quantityField.setText(s.pop());
		priceField.setText(s.pop());
		status.setText(s.pop());
		invoiceField.setText(order.toString());
	}
}
