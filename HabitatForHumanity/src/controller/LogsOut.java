package controller;

import java.io.FileNotFoundException;
import java.io.IOException;

import model.Data;
import view.FXMLLoadingController;

public interface LogsOut {

	default void exit() {
		CurrentUserController.logout();
		try {
			Data.save();
			FXMLLoadingController.login();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
