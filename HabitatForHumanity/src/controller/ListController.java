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
	private Label name1;

	@FXML
	private Label id1;

	@FXML
	private Button select1;

	@FXML
	private Label name2;

	@FXML
	private Label id2;

	@FXML
	private Button select2;

	@FXML
	private Label name3;

	@FXML
	private Label id3;

	@FXML
	private Button select3;

	@FXML
	private Label name4;

	@FXML
	private Label id4;

	@FXML
	private Button select4;

	@FXML
	private Label name5;

	@FXML
	private Label id5;

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
	private ArrayList<Button> btnProcessor;
	private int position = 0;

	public void setItemList(boolean itemList) {
		isItemList = itemList;
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

	private void populateScreen(int start) {
		for (int i = 0; i < 5; i++) {
			try {
				idLabelProcessor.get(i).setText(elements.get(start).getKey().toString());
				nameLabelProcessor.get(i).setText(elements.get(start).getName());
				btnProcessor.get(i).setDisable(false);
				start++;
			} catch (IndexOutOfBoundsException | NullPointerException e) {
				idLabelProcessor.get(i).setText(" ");
				nameLabelProcessor.get(i).setText(" ");
				btnProcessor.get(i).setDisable(true);
				start++;
			}
			position = start;
		}
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
		if (position - 10 >= 0) {
			populateScreen(position - 10);
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

	@FXML
	void initialize() {
		assert name1 != null : "fx:id=\"name1\" was not injected: check your FXML file 'List.fxml'.";
		assert id1 != null : "fx:id=\"id1\" was not injected: check your FXML file 'List.fxml'.";
		assert select1 != null : "fx:id=\"select1\" was not injected: check your FXML file 'List.fxml'.";
		assert name2 != null : "fx:id=\"name2\" was not injected: check your FXML file 'List.fxml'.";
		assert id2 != null : "fx:id=\"id2\" was not injected: check your FXML file 'List.fxml'.";
		assert select2 != null : "fx:id=\"select2\" was not injected: check your FXML file 'List.fxml'.";
		assert name3 != null : "fx:id=\"name3\" was not injected: check your FXML file 'List.fxml'.";
		assert id3 != null : "fx:id=\"id3\" was not injected: check your FXML file 'List.fxml'.";
		assert select3 != null : "fx:id=\"select3\" was not injected: check your FXML file 'List.fxml'.";
		assert name4 != null : "fx:id=\"name4\" was not injected: check your FXML file 'List.fxml'.";
		assert id4 != null : "fx:id=\"id4\" was not injected: check your FXML file 'List.fxml'.";
		assert select4 != null : "fx:id=\"select4\" was not injected: check your FXML file 'List.fxml'.";
		assert name5 != null : "fx:id=\"name5\" was not injected: check your FXML file 'List.fxml'.";
		assert id5 != null : "fx:id=\"id5\" was not injected: check your FXML file 'List.fxml'.";
		assert select5 != null : "fx:id=\"select5\" was not injected: check your FXML file 'List.fxml'.";
		assert prevButton != null : "fx:id=\"prevButton\" was not injected: check your FXML file 'List.fxml'.";
		assert homeButton != null : "fx:id=\"homeButton\" was not injected: check your FXML file 'List.fxml'.";
		assert nextButton != null : "fx:id=\"nextButton\" was not injected: check your FXML file 'List.fxml'.";
		initProcessors();
	}

	private void selectionProcess(int i) {
		String id = idLabelProcessor.get(i - 1).getText();
		if (id != null) {
			try {
				FXMLLoadingController.detail(new Identity(id.substring(0, 1), Integer.valueOf(id.substring(1))),
						isItemList);
			} catch (NumberFormatException e) {
				e.printStackTrace();
			}
		}
	}

	private void initProcessors() {
		idLabelProcessor = new ArrayList<Label>();
		nameLabelProcessor = new ArrayList<Label>();
		btnProcessor = new ArrayList<Button>();
		elements = new ArrayList<IsListable>();
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
		btnProcessor.add(select1);
		btnProcessor.add(select2);
		btnProcessor.add(select3);
		btnProcessor.add(select4);
		btnProcessor.add(select5);
	}
}
