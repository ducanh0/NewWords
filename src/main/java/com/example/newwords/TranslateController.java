package com.example.newwords;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import main.GoogleTranslate;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.regex.Pattern;

import static main.TextToSpeech.enterTextToSpeech;

public class TranslateController implements Initializable {
    GoogleTranslate googleTranslate = new GoogleTranslate();

    @FXML
    private TextArea inputText;

    @FXML
    private TextArea outputText;

    public boolean isEnglishText(String input) {
        return Pattern.matches("^[a-zA-Z\\s]*$", input);
    }

    @FXML
    public void translateText(ActionEvent actionEvent) throws Exception {
        String input = inputText.getText();
        String output = "";

        if (isEnglishText(input)) {
            output = googleTranslate.translateText(input, "en", "vi");
        } else {
            output = googleTranslate.translateText(input, "vi", "en");
        }

        outputText.setText(output);
    }

    @FXML
    public void pronounceTextInput(ActionEvent actionEvent) throws Exception {
        String input = inputText.getText();
        if (isEnglishText(input)) {
            enterTextToSpeech(input, "en");
        } else {
            enterTextToSpeech(input, "vi");
        }
    }

    @FXML
    public void pronounceTextOutput(ActionEvent actionEvent) throws Exception {
        String output = outputText.getText();
        if (isEnglishText(output)) {
            enterTextToSpeech(output, "en");
        } else {
            enterTextToSpeech(output, "vi");
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
