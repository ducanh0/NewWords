package com.example.newwords;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import main.GoogleTranslate;

public class Translate {
    GoogleTranslate googleTranslate = new GoogleTranslate();

    @FXML
    private TextField input;

    @FXML
    private TextField output;

    @FXML
    public void translate(ActionEvent e) throws Exception {
        String translated = googleTranslate.translateText(input.getText());
        output.setText(translated);
    }
}
