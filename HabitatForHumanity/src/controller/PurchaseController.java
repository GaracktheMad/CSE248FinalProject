package controller;

import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import model.Data;
import model.Identity;
import model.InvalidClassificationException;
import model.InvalidStockException;
import model.Item;
import model.Order;
import view.FXMLLoadingController;

public class PurchaseController {

	@FXML
	private TextField priceField;

	@FXML
	private ComboBox<Integer> quantityCmbo;

	@FXML
	private Button submitBtn;

	@FXML
	private Button cancelBtn;

	private Identity identity;
	private double price;

	@FXML
	void cancel(ActionEvent event) {
		FXMLLoadingController.detail(identity, "items");
	}

	@FXML
	void submit(ActionEvent event) {
		Order o = new Order(identity, CurrentUserController.getID(), quantityCmbo.getValue());
		try {
			CurrentUserController.getUser().createOrder(o);
			Data.save();
			FXMLLoadingController.detail(identity, "items");
		} catch (InvalidClassificationException | InvalidStockException | IOException e) {
			e.printStackTrace();
		}
	}

	@FXML
	void qtyChange(ActionEvent event) {
		priceField.setText(String.valueOf(price * quantityCmbo.getValue()));
	}

	public void data(Identity id) {
		identity = id;
		Item item = Data.getCopyItem(id);
		int quantity = item.getQuantity();
		price = item.getPrice();
		for (int i = 0; i <= quantity; i++) {
			quantityCmbo.getItems().add(i);
		}
	}

}
