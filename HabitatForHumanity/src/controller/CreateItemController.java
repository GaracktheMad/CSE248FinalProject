package controller;

import java.io.File;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import model.Item;
import javafx.stage.FileChooser.ExtensionFilter;

public class CreateItemController extends CreationController {

	@FXML
	private TextField priceField;

	@FXML
	private TextField imageURLField;

	@FXML
	private Button imageBtn;

	@FXML
	private TextField quantityField;

	@FXML
	private ImageView img;

	private Stage thisStage;

	public void setStage(Stage s) {
		thisStage = s;
	}

	@FXML
	void selectImage(ActionEvent event) {
		FileChooser chooseImg = new FileChooser();
		chooseImg.setTitle("Select an image to use");
		chooseImg.getExtensionFilters().add(new ExtensionFilter("Image Files", "*.gif", "*.bmp", "*.jpeg", "*.png"));
		File selectedFile = chooseImg.showOpenDialog(thisStage);
		if (selectedFile.getPath() != null && CurrentUserController.userIsClerk() != null) {
			String formattedFile = String.format("file:///%s", selectedFile.getPath());
			imageURLField.setText(formattedFile);
			img.setImage(new Image(formattedFile));
		}
	}

	@Override
	void submit(ActionEvent event) {
		if (priceField.getText() == null || nameField.getText() == null || imageURLField.getText() == null
				|| quantityField.getText() == null) {
			setErrorLbl("No Fields May Be Blank");
		} else {
			double price;
			int quantity;
			try {
				price = Double.valueOf(priceField.getText());
				new Image(imageURLField.getText());
				quantity = Integer.valueOf(quantityField.getText());
				Item item = new Item(nameField.getText(), price, imageURLField.getText(), quantity);
				CurrentUserController.userIsClerk().createItem(item);
				idField.setText(item.getID());
				setErrorLbl("Success.");
			} catch (NumberFormatException e) {
				setErrorLbl("Invalid Price or Quantity");
			} catch (NullPointerException | IllegalArgumentException e) {
				setErrorLbl("Invalid Image");
			}
		}
	}
}
