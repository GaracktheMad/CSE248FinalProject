package controller;

import java.io.File;
import java.io.IOException;
import java.util.Stack;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;
import model.ClerkPrivs;
import model.Data;
import model.Identity;
import model.InvalidListTypeException;
import model.Item;
import view.FXMLLoadingController;

public class ItemDetailPaneController {
	@FXML
	private TextField nameField;
	@FXML
	private TextField idField;
	@FXML
	private TextField priceField;
	@FXML
	private TextField quantityField;
	@FXML
	private Button deleteBtn;
	@FXML
    private Button orderBtn;
	@FXML
	private Button homeBtn;
	@FXML
	private Button editBtn;
	@FXML
	private Button backBtn;
	@FXML
	private Button setImageBtn;
	@FXML
	private ImageView imgField;
	@FXML
	private Button saveBtn;

	private Stage thisStage;
	private Identity identity;

	private void swapMode() {
		saveBtn.setDisable(!saveBtn.isDisabled());
		editBtn.setDisable(!editBtn.isDisabled());
		backBtn.setDisable(!backBtn.isDisabled());
		homeBtn.setDisable(!homeBtn.isDisabled());
		deleteBtn.setDisable(!deleteBtn.isDisabled());
		nameField.setEditable(!nameField.isEditable());
		priceField.setEditable(!priceField.isEditable());
		quantityField.setEditable(!quantityField.isEditable());
	}

	public void data(Identity item, Stage stage) {
		identity = item;
		thisStage = stage;
		init();
	}
	
	@FXML
    void order(ActionEvent event) {
		FXMLLoadingController.order(identity);
    }


	@FXML
	void deleteThis(ActionEvent event) {
		CurrentUserController.userIsClerk().removeItem(identity);
		try {
			FXMLLoadingController.list("Items");
		} catch (IOException e) {
			e.printStackTrace();
		} catch (InvalidListTypeException e) {
			e.printStackTrace();
		}
	}

	@FXML
	void edit(ActionEvent event) {
		swapMode();
	}

	@FXML
	void goBack(ActionEvent event) {
		try {
			FXMLLoadingController.list("Items");
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

	@FXML
	void save(ActionEvent event) {
		ClerkPrivs c = CurrentUserController.userIsClerk();
		int quantity;
		try {
			quantity = Integer.valueOf(quantityField.getText());
		} catch (NumberFormatException e) {
			quantity = -1;
		}
		double price;
		try {
			price = Double.valueOf(quantityField.getText());
		} catch (NumberFormatException e) {
			price = -1;
		}
		String name = nameField.getText();
		if (quantity > 1) {
			c.changeQuantity(identity, quantity);
		}
		if (price > 1) {
			c.changePrice(identity, price);
		}
		if (name != null && !name.equals("")) {
			c.changeName(identity, name);
		}
		swapMode();
	}

	@FXML
	void setImage(ActionEvent event) {
		FileChooser chooseImg = new FileChooser();
		chooseImg.setTitle("Select an image to use");
		chooseImg.getExtensionFilters().add(new ExtensionFilter("Image Files", "*.gif", "*.bmp", "*.jpeg", "*.png"));
		File selectedFile = chooseImg.showOpenDialog(thisStage);
		if (selectedFile.getAbsolutePath() != null && CurrentUserController.userIsClerk() != null) {
			String formattedFile = String.format("file:///%s", selectedFile.getPath());
			ClerkPrivs p = CurrentUserController.userIsClerk();
			p.changeImage(identity, formattedFile);
			imgField.setImage(new Image(formattedFile));
		}
	}

	private void init() {
		Item i = Data.getCopyItem(identity);
		Stack<String> itemProperties = i.details();
		idField.setText(itemProperties.pop());
		priceField.setText(itemProperties.pop());
		nameField.setText(itemProperties.pop());
		quantityField.setText(itemProperties.pop());
		imgField.setImage(new Image(i.getPhotoLocation()));
	}

	@FXML
	void initialize() {
		assert nameField != null : "fx:id=\"nameField\" was not injected: check your FXML file 'ItemDetailsPane.fxml'.";
		assert idField != null : "fx:id=\"idField\" was not injected: check your FXML file 'ItemDetailsPane.fxml'.";
		assert priceField != null : "fx:id=\"priceField\" was not injected: check your FXML file 'ItemDetailsPane.fxml'.";
		assert deleteBtn != null : "fx:id=\"deleteBtn\" was not injected: check your FXML file 'ItemDetailsPane.fxml'.";
		assert homeBtn != null : "fx:id=\"homeBtn\" was not injected: check your FXML file 'ItemDetailsPane.fxml'.";
		assert editBtn != null : "fx:id=\"editBtn\" was not injected: check your FXML file 'ItemDetailsPane.fxml'.";
		assert backBtn != null : "fx:id=\"backBtn\" was not injected: check your FXML file 'ItemDetailsPane.fxml'.";
		assert setImageBtn != null : "fx:id=\"setImageBtn\" was not injected: check your FXML file 'ItemDetailsPane.fxml'.";
		assert imgField != null : "fx:id=\"imgField\" was not injected: check your FXML file 'ItemDetailsPane.fxml'.";
		assert saveBtn != null : "fx:id=\"saveBtn\" was not injected: check your FXML file 'ItemDetailsPane.fxml'.";
		if (CurrentUserController.getUser() instanceof ClerkPrivs) {
			saveBtn.setVisible(true);
			editBtn.setVisible(true);
			editBtn.setDisable(false);
			deleteBtn.setVisible(true);
			setImageBtn.setVisible(true);
			setImageBtn.setDisable(false);
		}
	}
}
