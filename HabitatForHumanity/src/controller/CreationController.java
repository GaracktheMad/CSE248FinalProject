

package controller;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import view.FXMLLoadingController;

public abstract class CreationController {
	@FXML
	protected TextField nameField;
	
	@FXML
	private Label errorLbl;
	
	@FXML
	protected Button returnBtn;
	
	@FXML
    protected Button submitBtn;

	@FXML
	protected Button cancelBtn;
	
    @FXML
    protected TextField idField;
    
    protected void setErrorLbl(String text) {
    	errorLbl.setText(text);
    }

	@FXML
	void returnHome(ActionEvent event) {
		try {
			FXMLLoadingController.taskPane();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@FXML
	abstract void submit(ActionEvent event);

}
