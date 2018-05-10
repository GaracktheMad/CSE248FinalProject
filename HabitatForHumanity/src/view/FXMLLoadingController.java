package view;

import java.io.IOException;

import controller.CurrentUserController;
import controller.ItemDetailsPaneController;
import controller.ListController;
import controller.PopupController;
import controller.UserDetailPaneController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.Data;
import model.Identity;

public class FXMLLoadingController {

	private static Stage stage;
	private static Parent root;

	public static void setStage(Stage s) {
		stage = s;
		if (root == null) {
			try {
				login();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public static void updateStage() {
		Scene scene = new Scene(root);
		stage.setScene(scene);
	}

	public static void login() throws IOException {
		if (Data.load() == false) {
			Data.init();
		}
		root = FXMLLoader.load(FXMLLoadingController.class.getResource("Login.fxml"));
		updateStage();
	}

	public static void popUp(String id) throws IOException {
		FXMLLoader loader = new FXMLLoader(FXMLLoadingController.class.getResource("Popup.fxml"));
		root = loader.load();
		((PopupController) loader.getController()).setUsername(id);
		updateStage();
	}

	public static boolean taskPane() throws IOException {
		String answer = CurrentUserController.getRank();
		if (answer.equals("Admin")) {
			root = FXMLLoader.load(FXMLLoadingController.class.getResource("AdministrativeTaskPane.fxml"));
			updateStage();
			return true;
		} else if (answer.equals("Clerk") || answer.equals("ReadOnly")) {
			root = FXMLLoader.load(FXMLLoadingController.class.getResource("TaskPane.fxml"));
			updateStage();
			return true;
		} else {
			return false;
		}
	}

	public static void list(boolean isItemList) throws IOException {
		FXMLLoader loader = new FXMLLoader(FXMLLoadingController.class.getResource("List.fxml"));
		root = loader.load();
		ListController ctrl = loader.getController();
		if (isItemList == false && CurrentUserController.getRank().equals("Admin")) {
			ctrl.setItemList(isItemList);
		} else {
			ctrl.setItemList(true);
		}
		updateStage();
	}

	public static void createAccount() throws IOException {
		root = FXMLLoader.load(FXMLLoadingController.class.getResource("CreateAccount.fxml"));
		updateStage();
	}

	public static void detail(Identity identity, boolean isItemList) {
		if (isItemList == false && CurrentUserController.getRank().equals("Admin")) {
			FXMLLoader loader = new FXMLLoader(FXMLLoadingController.class.getResource("UserDetailPane.fxml"));
			try {
				root = loader.load();
				UserDetailPaneController ctrl = loader.getController();
				ctrl.setIdentity(identity);
				updateStage();
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else {
			FXMLLoader loader = new FXMLLoader(FXMLLoadingController.class.getResource("ItemDetailPane.fxml"));
			try {
				root = loader.load();
				ItemDetailsPaneController ctrl = loader.getController();
				ctrl.data(identity, stage);
				updateStage();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public static boolean loginToTaskPane(Identity i) throws IOException {
		CurrentUserController.set(i);
		return taskPane();
	}

	public static void createUser() throws IOException {
		root = FXMLLoader.load(FXMLLoadingController.class.getResource("CreateUser.fxml"));
		updateStage();
	}

	public static void createItem() throws IOException {
		root = FXMLLoader.load(FXMLLoadingController.class.getResource("CreateItem.fxml"));
		updateStage();
	}

}
