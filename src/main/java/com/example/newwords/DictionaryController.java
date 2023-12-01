package com.example.newwords;

import com.example.newwords.supportAPI.CoreGUIDictionaryManager;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
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
    ArrayList<Word> arrayList;
    CoreGUIDictionaryManager core;
    String[] searchTarget = new String[1];

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        core = new CoreGUIDictionaryManager();
        textField.textProperty().addListener(((observableValue, s, t1) -> {
            searchTarget[0] = t1;
        }));
    }

    public void search(KeyEvent keyEvent) {
        arrayList = core.searchPrefix(searchTarget[0]);
        List<String> ls = arrayList.stream().map(Word::getWord_target).toList();
        ObservableList<String> obls = FXCollections.observableList(ls);
        ListView lsv = (ListView) textField.getParent().lookup("#searchResults");
        lsv.setItems(obls);
    }
}
