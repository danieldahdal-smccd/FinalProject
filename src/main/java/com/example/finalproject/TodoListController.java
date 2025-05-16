/**
 * This program is a simple todo list application with options to
 * create, remove and edit items.
 * @author Daniel Dahdal
 * @since 05-12-2025
 */

package com.example.finalproject;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

import java.util.ArrayList;

public class TodoListController {
    ArrayList<TodoListItem> todoListItem = new ArrayList<TodoListItem>();
    ArrayList<String> todoListItemName = new ArrayList<String>();

    @FXML
    private TextField itemNameBox;

    @FXML
    ComboBox todoItemSelection;

    /**This method creates the new todo list item.*/
    @FXML
    protected void onCreateItemButtonClick() {
        try {
            TodoListItem newItem = new TodoListItem();

            if(!itemNameBox.getText().isEmpty()) {
                String item = itemNameBox.getText();
                todoListItem.add(newItem);
                todoListItemName.add(item);

                ObservableList<String> updatedTodoList = FXCollections.observableArrayList(todoListItemName);
                todoItemSelection.setItems(updatedTodoList);

                generateInfoAlert("Todo item created.");
                itemNameBox.setText("");
                todoItemSelection.getSelectionModel().selectLast();
            } else {
                generateInfoAlert("The text field can't be empty.");
            }
        }
        catch (ArrayIndexOutOfBoundsException e) {
            generateInfoAlert("Create or select an existing todo item.");
        }
        catch (Exception e) {
            generateInfoAlert(e.getMessage());
        }
    }

    /**This method controls the selection of each todo list item.*/
    @FXML
    protected void onItemSelect(){
        try {
            Integer index = todoListItemName.indexOf(todoItemSelection.getValue());
            String selectedItemName = todoListItemName.get(index);
            itemNameBox.setText(String.valueOf(todoListItem.get(index).getItemName(selectedItemName)));
        }
        catch (Exception e) {
            todoItemSelection.getSelectionModel().selectLast();
        }
    }

    /**This method removes the selected todo list item.*/
    @FXML
    protected void onRemoveItemButtonClick() {
        try {
            Integer index = todoListItemName.indexOf(todoItemSelection.getValue());

            if(index.equals(0)){
                String selectedItemName = todoListItemName.get(index);
                itemNameBox.setText(String.valueOf(todoListItem.get(index).getItemName(selectedItemName)));
                todoListItem.remove(index);
                todoListItemName.remove(todoItemSelection.getValue());

                ObservableList<String> updatedTodoList = FXCollections.observableArrayList(todoListItemName);
                todoItemSelection.setItems(updatedTodoList);

                generateInfoAlert("Todo item removed.");
                itemNameBox.setText("");
                todoItemSelection.getSelectionModel().selectLast();
            } else {
                generateInfoAlert("Please create or select an item to remove.");
            }
        }
        catch (Exception e) {
            todoItemSelection.getSelectionModel().selectLast();
        }
    }

    /**This method edits the selected todo list item.*/
    @FXML
    protected void onEditItemButtonClick() {
        try {
            Integer index = todoListItemName.indexOf(todoItemSelection.getValue());

            if(index.equals(0)){
                TodoListItem newItem = new TodoListItem();

                String item = itemNameBox.getText();
                todoListItem.add(newItem);
                todoListItemName.add(item);

                String selectedItemName = todoListItemName.get(index);
                itemNameBox.setText(String.valueOf(todoListItem.get(index).getItemName(selectedItemName)));
                todoListItem.remove(index);
                todoListItemName.remove(todoItemSelection.getValue());

                ObservableList<String> updatedTodoList = FXCollections.observableArrayList(todoListItemName);
                todoItemSelection.setItems(updatedTodoList);

                generateInfoAlert("Todo item edited.");
                todoItemSelection.getSelectionModel().select(index);
            } else {
                generateInfoAlert("Please create or select an item to edit.");
            }
        }
        catch (Exception e) {
            todoItemSelection.getSelectionModel().selectLast();
        }
    }

    /**This method generates an information alert depending on the error or exception.*/
    private void generateInfoAlert(String msg) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information");
        alert.setContentText(msg);
        alert.showAndWait();
    }
}