package controller;

import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import model.Data;
import view.FXMLLoadingController;

public class InvoiceGenerationController {

    @FXML
    private TextArea invoiceField;

    @FXML
    private Button returnBtn;

    @FXML
    void returnHome(ActionEvent event) {
    	try {
			FXMLLoadingController.taskPane();
		} catch (IOException e) {
			e.printStackTrace();
		}
    }
    
    @FXML
    void initialize() {
        assert invoiceField != null : "fx:id=\"invoiceField\" was not injected: check your FXML file 'GenerateInvoice.fxml'.";
        assert returnBtn != null : "fx:id=\"returnBtn\" was not injected: check your FXML file 'GenerateInvoice.fxml'.";
        invoiceField.setText(Data.generateMasterInvoice());
    }

}
