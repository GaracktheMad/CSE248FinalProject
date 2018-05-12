package view;

import java.io.IOException;
import controller.CurrentUserController;
import controller.ItemDetailPaneController;
import controller.ListController;
import controller.PopupController;
import controller.UserDetailPaneController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import model.Data;
import model.Identity;

public class FXMLLoadingController {

	private static Stage stage;
	private static StackPane root;

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

	public static void updateStage(StackPane sp) {
		root.getChildren().setAll(sp);
	}

	public static void login() throws IOException {
		if (Data.load() == false) {
			Data.init();
		}
		root = FXMLLoader.load(FXMLLoadingController.class.getResource("Login.fxml"));
		stage.setScene(new Scene(root));
	}

	public static void popUp(String id) throws IOException {
		FXMLLoader loader = new FXMLLoader(FXMLLoadingController.class.getResource("Popup.fxml"));
		StackPane sp = loader.load();
		((PopupController) loader.getController()).setUsername(id);
		updateStage(sp);
	}

	public static boolean taskPane() throws IOException {
		String answer = CurrentUserController.getRank();
		StackPane sp;
		if (answer.equals("Admin")) {
			sp = FXMLLoader.load(FXMLLoadingController.class.getResource("AdministrativeTaskPane.fxml"));
			updateStage(sp);
			return true;
		} else if (answer.equals("Clerk") || answer.equals("ReadOnly")) {
			sp = FXMLLoader.load(FXMLLoadingController.class.getResource("TaskPane.fxml"));
			updateStage(sp);
			return true;
		} else {
			return false;
		}
	}

	public static void list(boolean isItemList) throws IOException {
		FXMLLoader loader = new FXMLLoader(FXMLLoadingController.class.getResource("List.fxml"));
		StackPane sp = loader.load();
		ListController ctrl = loader.getController();
		if (isItemList == false && CurrentUserController.getRank().equals("Admin")) {
			ctrl.setItemList(isItemList);
		} else {
			ctrl.setItemList(true);
		}
		updateStage(sp);
	}

	public static void createAccount() throws IOException {
		StackPane sp = FXMLLoader.load(FXMLLoadingController.class.getResource("CreateAccount.fxml"));
		updateStage(sp);
	}

	public static void detail(Identity identity, boolean isItemList) {
		if (isItemList == false && CurrentUserController.getRank().equals("Admin")) {
			FXMLLoader loader = new FXMLLoader(FXMLLoadingController.class.getResource("UserDetailPane.fxml"));
			try {
				StackPane sp = loader.load();
				UserDetailPaneController ctrl = loader.getController();
				ctrl.setIdentity(identity);
				updateStage(sp);
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else {
			FXMLLoader loader = new FXMLLoader(FXMLLoadingController.class.getResource("ItemDetailPane.fxml"));
			try {
				StackPane sp = loader.load();
				ItemDetailPaneController ctrl = loader.getController();
				ctrl.data(identity, stage);
				updateStage(sp);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public static boolean loginToTaskPane(Identity i, String password) throws IOException {
		boolean status = CurrentUserController.set(i);
		if (status == false || CurrentUserController.getUser().verifyPass(password) == false) {
			CurrentUserController.logout();
			return false;
		}
		return taskPane();
	}

	public static void createUser() throws IOException {
		StackPane sp = FXMLLoader.load(FXMLLoadingController.class.getResource("CreateUser.fxml"));
		updateStage(sp);
	}

	public static void createItem() throws IOException {
		StackPane sp = FXMLLoader.load(FXMLLoadingController.class.getResource("CreateItem.fxml"));
		updateStage(sp);
	}

}
