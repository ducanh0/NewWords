package com.example.newwords;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import main.GoogleTranslate;

import java.net.URL;
import java.util.ResourceBundle;

import static main.TextToSpeech.enterTextToSpeech;

public class TranslateController implements Initializable {
    GoogleTranslate googleTranslate = new GoogleTranslate();

    @FXML
    private TextField inputText;

    @FXML
    private TextField outputText;

    @FXML
    public void translateText(ActionEvent actionEvent) throws Exception {
        String output = googleTranslate.translateText(inputText.getText());
        outputText.setText(output);
    }

    @FXML
    public void pronounceText(ActionEvent actionEvent) throws Exception {
        enterTextToSpeech(inputText.getText(), "en");
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
