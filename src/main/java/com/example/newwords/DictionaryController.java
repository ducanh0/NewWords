package com.example.newwords;

import com.example.newwords.supportAPI.CoreGUIDictionaryManager;
import javafx.beans.property.ReadOnlyIntegerProperty;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import main.Word;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class DictionaryController implements Initializable {
    @FXML
    public TextField textField;
    public ListView<String> searchResults;
    public TextArea explanation;
    ArrayList<Word> arrayList;
    CoreGUIDictionaryManager core;
    String[] searchTarget = new String[1];

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        core = new CoreGUIDictionaryManager();
        textField.textProperty().addListener(((observableValue, s, t1) -> searchTarget[0] = t1));
        searchResults.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            ReadOnlyIntegerProperty index =
                    searchResults.getSelectionModel().selectedIndexProperty();
            explanation.textProperty().set(
                    String.join("\n", arrayList.get(index.get()).getWord_explain())
            );
        });
    }

    public void search(KeyEvent keyEvent) {
        arrayList = core.searchPrefix(searchTarget[0]);
        List<String> ls = arrayList.stream().map(Word::getWord_target).toList();
        searchResults.setItems(FXCollections.observableList(ls));
    }
}
