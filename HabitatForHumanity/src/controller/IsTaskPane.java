package controller;

import java.io.FileNotFoundException;
import java.io.IOException;

import model.Data;
import view.FXMLLoadingController;

public interface IsTaskPane {
	
	default void exit(){
		CurrentUserController.logout();
		try {
			Data.save();FXMLLoadingController.login();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
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
