package controller;

import javafx.fxml.FXML;

import javafx.scene.control.Button;

import java.io.IOException;
import java.util.ArrayList;

import javafx.event.ActionEvent;

import javafx.scene.control.Label;
import model.Data;
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
	
	private boolean isItemList;
	private ArrayList<Label> idLabelProcessor;
	private ArrayList<Label> nameLabelProcessor;
	private ArrayList<IsListable> elements;
	private int position = 0;

	
	void init() {
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
			if (Data.noUsers() == false) {
				elements = CurrentUserController.userIsAdmin().listAllUsers();
			} else {
				try {
					FXMLLoadingController.taskPane();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		} else {
			if (Data.noItems() == false) {
				elements = CurrentUserController.userViewsItemList().listAllItems();
			} else {
				try {
					FXMLLoadingController.taskPane();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		populateScreen(0);
	}

	public void setItemList(boolean itemList) {
		isItemList = itemList;
		init();
	}

	private void populateScreen(int start) {
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

	@FXML
	void detailSelect1(ActionEvent event) {
		selectionProcess(1);
	}

	@FXML
	void detailSelect2(ActionEvent event) {
		selectionProcess(2);
	}

	@FXML
	void detailSelect3(ActionEvent event) {
		selectionProcess(3);
	}

	@FXML
	void detailSelect4(ActionEvent event) {
		selectionProcess(4);
	}

	@FXML
	void detailSelect5(ActionEvent event) {
		selectionProcess(5);
	}

	@FXML
	void populateLast(ActionEvent event) {
		if (position - 5 >= 0) {
			populateScreen(position - 5);
		}
	}

	@FXML
	void returnToTaskPane(ActionEvent event) {
		try {
			FXMLLoadingController.taskPane();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@FXML
	void populateNextPage(ActionEvent event) {
		if (position + 5 < elements.size() + 5) {
			populateScreen(position);
		}
	}

	private void selectionProcess(int i) {
		String id = idLabelProcessor.get(i).getText();
		if (id != null) {
			try {
				FXMLLoadingController.detail(new Identity(id.substring(0, 1), Integer.valueOf(id.substring(1))),
						isItemList);
			} catch (NumberFormatException e) {
				e.printStackTrace();
			}
		}
	}

}
