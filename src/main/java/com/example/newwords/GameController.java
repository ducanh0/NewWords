package com.example.newwords;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class GameController implements Initializable {

    @FXML
    private Button runGameButton;
    ProcessBuilder processBuilder;
    @FXML
    void runGame(ActionEvent event) {
        try {
            processBuilder.start();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        final String javaBin = System.getProperty("java.home")+ "/bin" + "/java";
        final String classpath = System.getProperty("java.class.path");
        final String modulePath = System.getProperty("jdk.module.path");
        processBuilder = new ProcessBuilder(javaBin,
                "-cp", classpath, "-p",
                modulePath, "-m", "com.example.newwords/" + Game.Main.class.getName());
        processBuilder.redirectOutput(new File("output.txt"));
    }
}
