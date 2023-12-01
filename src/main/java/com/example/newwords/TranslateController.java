package com.example.newwords;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import main.GoogleTranslate;

import java.net.URL;
import java.util.ResourceBundle;

import static main.TextToSpeech.enterTextToSpeech;

public class TranslateController implements Initializable {
    GoogleTranslate googleTranslate = new GoogleTranslate();

    @FXML
    private TextArea inputText;

    @FXML
    private TextArea outputText;

    @FXML
    public void translateText(ActionEvent actionEvent) throws Exception {
        String output = googleTranslate.translateText(inputText.getText());
        outputText.setText(output);
    }

    @FXML
    public void pronounceTextEnglish(ActionEvent actionEvent) throws Exception {
        enterTextToSpeech(inputText.getText(), "en");
    }

    @FXML
    public void pronounceTextVietnamese(ActionEvent actionEvent) throws Exception {
        String output = googleTranslate.translateText(inputText.getText());
        enterTextToSpeech(output, "vi");
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
