package controller;

import java.io.IOException;

import view.FXMLLoadingController;

public interface IsTaskPane {
	default void exit(){
		CurrentUserController.logout();
		ListController.logout();
		try {
			FXMLLoadingController.login();
		} catch (IOException e) {
			System.exit(0);
		}
	}
	default int changePassword(String original, String newPass, String repeat) {
		if(newPass.equals(repeat) == false) {
			return 0;
		}
		else if(CurrentUserController.checkPass(original) == false) {
			return -1;
		}
		else {
			CurrentUserController.setPassword(newPass);
			return 1;
		}
		
	}

}
