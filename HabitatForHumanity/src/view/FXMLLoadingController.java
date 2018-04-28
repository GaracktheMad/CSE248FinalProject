package view;

import java.io.IOException;

import controller.CurrentUserController;
import controller.ListController;
import controller.PopupController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import model.Identity;

public class FXMLLoadingController {

	private static Parent root;
	
	public static void login() throws IOException {
		root = FXMLLoader.load(FXMLLoadingController.class.getResource("Login.fxml"));
	}

	public static void popUp(String string) throws IOException {
		PopupController.setUsername(string);
		root = FXMLLoader.load(FXMLLoadingController.class.getResource("Popup.fxml"));
	}

	public static Parent getRoot() throws IOException {
		if (root == null) {
			login();
		}
		return root;
	}

	public static boolean taskPane() throws IOException {
		String answer = CurrentUserController.getRank();
		if (answer.equals("Admin")) {
			root = FXMLLoader.load(FXMLLoadingController.class.getResource("AdministrativeTaskPane.fxml"));
			return true;
		} else if (answer.equals("Clerk") || answer.equals("ReadOnly")) {
			root = FXMLLoader.load(FXMLLoadingController.class.getResource("TaskPane.fxml"));
			return true;
		} else {
			return false;
		}
	}

	public static void list(boolean isItemList) throws IOException {
		if (isItemList == false && CurrentUserController.getRank().equals("Admin")) {
			ListController.populateList(isItemList);
		} else {
			ListController.populateList(true);
		}
		root = FXMLLoader.load(FXMLLoadingController.class.getResource("List.fxml"));
	}

	public static void createAccount() throws IOException {
		root = FXMLLoader.load(FXMLLoadingController.class.getResource("CreateAccount.fxml"));
	}

	public static void detail(Identity identity, boolean isItemList) {

	}

	public static boolean loginToTaskPane(Identity i) throws IOException {
		CurrentUserController.set(i);
		return taskPane();
	}
}
