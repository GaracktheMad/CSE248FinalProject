package controller;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;

public class FXMLLoadingController {

	private static Parent root;

	private static void init() throws IOException {
		root = FXMLLoader.load(FXMLLoadingController.class.getResource("view/Login.fxml"));
	}

	public static void setFXML(String s) throws IOException {
		root = FXMLLoader.load(FXMLLoadingController.class.getResource(s));
	}

	public static Parent getRoot() throws IOException {
		if (root == null) {
			init();
		}
		return root;
	}

}
