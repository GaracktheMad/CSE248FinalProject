package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public abstract class CreationController {
	@FXML
	protected TextField nameField;
	
	@FXML
	protected Button submitBtn;

	@FXML
	protected Button cancelBtn;

	@FXML
	abstract void cancel(ActionEvent event);

	@FXML
	abstract void submit(ActionEvent event);

}
