package com.example.newwords;

import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.util.Duration;
import main.GoogleTranslate;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {
    @FXML
    private StackPane stackPane;

    @FXML
    private ImageView Exit;

    @FXML
    private Label Menu;

    @FXML
    private Label MenuBack;

    @FXML
    private AnchorPane Slider;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Exit.setOnMouseClicked(mouseEvent -> {
            System.exit(0);
        });

        Menu.setOnMouseClicked(mouseEvent -> {
            TranslateTransition slide = new TranslateTransition();
            slide.setDuration(Duration.seconds(0.4));
            slide.setNode(Slider);
            slide.setToX(0);
            slide.play();
            Slider.setTranslateX(-176);
            slide.setOnFinished((ActionEvent e) -> {
                Menu.setVisible(false);
                MenuBack.setVisible(true);
            });
        });

        MenuBack.setOnMouseClicked(mouseEvent -> {
            TranslateTransition slide = new TranslateTransition();
            slide.setDuration(Duration.seconds(0.4));
            slide.setNode(Slider);
            slide.setToX(-176);
            Slider.setTranslateX(0);
            slide.play();
            slide.setOnFinished((ActionEvent e) -> {
                Menu.setVisible(true);
                MenuBack.setVisible(false);
            });
        });

        try {
            Parent fxml = FXMLLoader.load(getClass().getResource("dashboard.fxml"));
            stackPane.getChildren().removeAll();
            stackPane.getChildren().setAll(fxml);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @FXML
    public void dashboard(ActionEvent event) throws Exception {
        Parent fxml = FXMLLoader.load(getClass().getResource("dashboard.fxml"));
        stackPane.getChildren().removeAll();
        stackPane.getChildren().setAll(fxml);
    }

    @FXML
    public void add(ActionEvent event) throws Exception {
        Parent fxml = FXMLLoader.load(getClass().getResource("add.fxml"));
        stackPane.getChildren().removeAll();
        stackPane.getChildren().setAll(fxml);
    }

    @FXML
    public void translate(ActionEvent e) throws Exception {
        Parent fxml = FXMLLoader.load(getClass().getResource("translate.fxml"));
        stackPane.getChildren().removeAll();
        stackPane.getChildren().setAll(fxml);
    }

    @FXML
    public void setting(ActionEvent event) throws Exception {
        Parent fxml = FXMLLoader.load(getClass().getResource("setting.fxml"));
        stackPane.getChildren().removeAll();
        stackPane.getChildren().setAll(fxml);
    }


}