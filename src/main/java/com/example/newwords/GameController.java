package com.example.newwords;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;

import java.net.URL;
import java.util.ResourceBundle;

public class GameController implements Initializable {

    @FXML
    private Button runGameButton;

    @FXML
    void runGame(ActionEvent event) {
        Game.Main.main(new String[0]);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
