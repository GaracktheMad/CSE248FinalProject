package controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;

public class ItemDetailsController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField nameField;

    @FXML
    private TextField idField;

    @FXML
    private TextField priceField;

    @FXML
    private TextField donorInfoField;

    @FXML
    private Button deleteBtn;

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
    void deleteThis(ActionEvent event) {

    }

    @FXML
    void edit(ActionEvent event) {

    }

    @FXML
    void goBack(ActionEvent event) {

    }

    @FXML
    void goHome(ActionEvent event) {

    }

    @FXML
    void setImage(ActionEvent event) {

    }

    @FXML
    void initialize() {
        assert nameField != null : "fx:id=\"nameField\" was not injected: check your FXML file 'ItemDetailsPane.fxml'.";
        assert idField != null : "fx:id=\"idField\" was not injected: check your FXML file 'ItemDetailsPane.fxml'.";
        assert priceField != null : "fx:id=\"priceField\" was not injected: check your FXML file 'ItemDetailsPane.fxml'.";
        assert donorInfoField != null : "fx:id=\"donorInfoField\" was not injected: check your FXML file 'ItemDetailsPane.fxml'.";
        assert homeBtn != null : "fx:id=\"homeBtn\" was not injected: check your FXML file 'ItemDetailsPane.fxml'.";
        assert editBtn != null : "fx:id=\"editBtn\" was not injected: check your FXML file 'ItemDetailsPane.fxml'.";
        assert backBtn != null : "fx:id=\"backBtn\" was not injected: check your FXML file 'ItemDetailsPane.fxml'.";
        assert imgField != null : "fx:id=\"imgField\" was not injected: check your FXML file 'ItemDetailsPane.fxml'.";

    }
}

