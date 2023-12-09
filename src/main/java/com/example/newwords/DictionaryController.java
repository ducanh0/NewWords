package com.example.newwords;

import com.example.newwords.supportAPI.CoreGUIDictionaryManager;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import main.Word;

import java.net.URL;
import java.util.*;

import static main.TextToSpeech.enterTextToSpeech;

public class DictionaryController implements Initializable {
    @FXML
    public TextField textField;
    public ListView<String> searchResults;
    public TextArea explanation;
    public Button remove;
    public Button add;
    public Button update;
    public Button pronounce;

    private ArrayList<Word> arrayList;
    private CoreGUIDictionaryManager core;
    int index;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        core = new CoreGUIDictionaryManager();

        add.setDisable(true);

        textField.textProperty().addListener((observableValue, s, t1) -> {
            arrayList = core.searchPrefix(t1);
            List<String> ls = arrayList.stream().map(Word::getWord_target).toList();
            searchResults.setItems(FXCollections.observableList(ls));
            add.setDisable(!ls.isEmpty());
            remove.setDisable(!add.isDisable());
            update.setDisable(!add.isDisable());

            // clear explanation nếu không có từ cần tìm
            if (ls.isEmpty()) {
                explanation.clear();
            }
        });

        searchResults.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            int index = searchResults.getSelectionModel().selectedIndexProperty().get();
            if (index < 0) return;
            explanation.textProperty().set(
                    String.join("\n", arrayList.get(index).getWord_explain())
            );
        });

        // tự động xuống dòng
        explanation.setWrapText(true);
    }

    public void update(ActionEvent actionEvent) {
        core.fixExplanations(arrayList.get(index).getWord_target(),
                Arrays.asList(explanation.textProperty().get().split("\n")));
    }

    public void remove(ActionEvent actionEvent) {
        core.delete(arrayList.get(index).getWord_target());
    }

    public void add(ActionEvent actionEvent) {
        core.addWord(textField.getText(),
                Arrays.asList(explanation.getText().split("\n")));
    }

    public void pronounce(ActionEvent event) {
        int selectedIndex = searchResults.getSelectionModel().getSelectedIndex();
        if (selectedIndex >= 0) {
            String selectedWord = searchResults.getItems().get(selectedIndex);
            enterTextToSpeech(selectedWord, "en");
        }
    }
}
