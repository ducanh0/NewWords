package Game;

import com.almasb.fxgl.app.GameApplication;
import com.almasb.fxgl.app.GameSettings;
import com.almasb.fxgl.dsl.FXGL;
import javafx.scene.control.Label;
import javafx.scene.text.Font;

public class Main extends GameApplication {

    private static final int HEIGHT = 800;
    private static final int WIDTH = 1500;

    @Override
    protected void initSettings(GameSettings settings) {
        settings.setWidth(WIDTH);
        settings.setHeight(HEIGHT);
        settings.setTitle("NewWords");
     //   settings.setVersion("00");
    }

    @Override
    protected void initUI() {
        Label label = new Label("Halo, banana!");
        Font font = Font.loadFont("file:data/font/chu8.ttf", 50);
       // System.out.println("?? " + (font == null));
        label.setFont(font); // Font.font(20.0)
        FXGL.addUINode(label, 350.0, 290.0);
    }

    public static void main(String[] args) {
        launch(args);
    }
}