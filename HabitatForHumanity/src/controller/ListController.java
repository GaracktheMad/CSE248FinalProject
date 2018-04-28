package controller;

import javafx.fxml.FXML;

import javafx.scene.control.Button;

import java.io.IOException;
import java.util.ArrayList;

import javafx.event.ActionEvent;

import javafx.scene.control.Label;
import model.Identity;
import model.IsListable;
import view.FXMLLoadingController;

public class ListController {
	@FXML
	private static Label name1;
	@FXML
	private static Label id1;
	@FXML
	private Button select1;
	@FXML
	private static Label name2;
	@FXML
	private static Label id2;
	@FXML
	private Button select2;
	@FXML
	private static Label name3;
	@FXML
	private static Label id3;
	@FXML
	private Button select3;
	@FXML
	private static Label name4;
	@FXML
	private static Label id4;
	@FXML
	private Button select4;
	@FXML
	private static Label name5;
	@FXML
	private static Label id5;
	@FXML
	private Button select5;
	@FXML
	private Button prevButton;
	@FXML
	private Button homeButton;
	@FXML
	private Button nextButton;
	private static boolean isItemList;
	static ArrayList<Label> idLabelProcessor;
	static ArrayList<Label> nameLabelProcessor;
	static ArrayList<IsListable> elements;
	private static int position = 0;

	public static void populateList(boolean itemList) {
		isItemList = itemList;
		idLabelProcessor = new ArrayList<Label>();
		nameLabelProcessor = new ArrayList<Label>();
		idLabelProcessor.add(id1);
		idLabelProcessor.add(id2);
		idLabelProcessor.add(id3);
		idLabelProcessor.add(id4);
		idLabelProcessor.add(id5);
		nameLabelProcessor.add(name1);
		nameLabelProcessor.add(name2);
		nameLabelProcessor.add(name3);
		nameLabelProcessor.add(name4);
		nameLabelProcessor.add(name5);
		if (CurrentUserController.getRank().equals("Admin") && isItemList == false) {
			elements = CurrentUserController.userIsAdmin().listAllUsers();
		} else {
			elements = CurrentUserController.userViewsItemList().listAllItems();
		}
		populateScreen(0);

	}

	private static void populateScreen(int start) {
		for (int i = 0; i < 5; i++) {
			try {
				idLabelProcessor.get(i).setText(elements.get(start).getKey().toString());
				nameLabelProcessor.get(i).setText(elements.get(start).getName());
				start++;
			} catch (IndexOutOfBoundsException e) {
				for (int ii = i; ii < 5; i++) {
					idLabelProcessor.get(ii).setText("");
					nameLabelProcessor.get(ii).setText("");
					start++;
				}
				i = 6;
			}
		}
		position = start;
	}

	// Event Listener on Button[#select1].onAction
	@FXML
	public void detailSelect1(ActionEvent event) {
		selectionProcess(1);
	}

	// Event Listener on Button[#select2].onAction
	@FXML
	public void detailSelect2(ActionEvent event) {
		selectionProcess(2);
	}

	// Event Listener on Button[#select3].onAction
	@FXML
	public void detailSelect3(ActionEvent event) {
		selectionProcess(3);
	}

	// Event Listener on Button[#select4].onAction
	@FXML
	public void detailSelect4(ActionEvent event) {
		selectionProcess(4);
	}

	// Event Listener on Button[#select5].onAction
	@FXML
	public void detailSelect5(ActionEvent event) {
		selectionProcess(5);
	}

	// Event Listener on Button[#prevButton].onAction
	@FXML
	public void populateLast(ActionEvent event) {
		if (position - 5 >= 0) {
			populateScreen(position - 5);
		}
	}

	// Event Listener on Button[#homeButton].onAction
	@FXML
	public void returnToTaskPane(ActionEvent event) {
		try {
			FXMLLoadingController.taskPane();
		} catch (IOException e) {
			System.exit(1);
		}
	}

	// Event Listener on Button[#nextButton].onAction
	@FXML
	public void populateNextPage(ActionEvent event) {
		if (position + 5 < elements.size() + 5) {
			populateScreen(position);
		}
	}

	private static void selectionProcess(int i) {
		String id = idLabelProcessor.get(i).getText();
		if (id != null) {
			try {
				FXMLLoadingController.detail(new Identity(id.substring(0, 1), Integer.valueOf(id.substring(1))) , isItemList);
			} catch (NumberFormatException e) {
				System.exit(1);
			}
		}
	}

	public static void logout() {
		isItemList = true;
		idLabelProcessor = null;
		nameLabelProcessor = null;
		elements = null;
	}
}
