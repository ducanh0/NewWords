package com.example.newwords;

import com.almasb.fxgl.app.FXGLPane;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import java.net.URL;
import java.util.ResourceBundle;

public class GameController implements Initializable {

    @FXML
    private Button runGameButton;

    @FXML
    void runGame(ActionEvent event) {
        Game.Main game = new Game.Main();
        FXGLPane gamePane = Game.Main.embeddedLaunch(game);
        Stage stage = new Stage();
        stage.setScene(new Scene(gamePane, 1200, 800));
        stage.addEventHandler(WindowEvent.WINDOW_CLOSE_REQUEST, windowEvent -> {
            Game.Main.embeddedShutdown();
            Game.pregame.clearPreGame();
        });
        stage.show();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
