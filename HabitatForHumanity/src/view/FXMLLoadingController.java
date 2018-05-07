package view;

import java.io.IOException;

import controller.CurrentUserController;
import controller.ListController;
import controller.PopupController;
import controller.UserDetailPaneController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import model.Identity;

public class FXMLLoadingController {

	private static Parent root;
	
	public static void login() throws IOException {
		root = FXMLLoader.load(FXMLLoadingController.class.getResource("Login.fxml"));
	}

	public static void popUp(String string) throws IOException {
		FXMLLoader loader = new FXMLLoader(FXMLLoadingController.class.getResource("Popup.fxml"));
		loader.<PopupController>getController().setUsername(string);
		root = loader.load();
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
			ListController.setItemList(isItemList);
		} else {
			ListController.setItemList(true);
		}
		root = FXMLLoader.load(FXMLLoadingController.class.getResource("List.fxml"));
	}

	public static void createAccount() throws IOException {
		root = FXMLLoader.load(FXMLLoadingController.class.getResource("CreateAccount.fxml"));
	}

	public static void detail(Identity identity, boolean isItemList) {
		if(isItemList == false && CurrentUserController.getRank().equals("Admin")) {
			UserDetailPaneController.setIdentity(identity);
		}

	}

	public static boolean loginToTaskPane(Identity i) throws IOException {
		CurrentUserController.set(i);
		return taskPane();
	}

	public static void createUser() throws IOException {
		root = FXMLLoader.load(FXMLLoadingController.class.getResource("CreateUser.fxml"));
	}

	public static void createItem() throws IOException {
		root = FXMLLoader.load(FXMLLoadingController.class.getResource("CreateItem.fxml"));
	}

}
