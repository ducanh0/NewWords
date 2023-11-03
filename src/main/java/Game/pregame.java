package Game;

import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.entity.SpawnData;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class pregame {
    private static Label titlePreGame, rulesPreGame;
    public static void renderPreGame(){
        Main.preGame = true;

        FXGL.spawn("Background", new SpawnData(0, 0).put("link", "back-pre2.png"));

        titlePreGame = new Label("NewTypes");
        titlePreGame.setFont(Font.loadFont("file:src/main/resources/assets/fonts/chu5.ttf", 100));
        titlePreGame.setTextFill(Color.GREEN);

        FXGL.addUINode(titlePreGame, 300, 30);

        rulesPreGame = new Label("Press enter to play, esc to quit\nPress arrow keys to move the main character\nPress keys to kill enemy\n\nEach time the main character touch another object, the correct types is minus 3\nThe game ends if the accurate is equal to 0\n\naccurate = correct types / total types");
        rulesPreGame.setFont(Font.loadFont("file:src/main/resources/assets/fonts/chu1.ttf", 25));
        rulesPreGame.setTextFill(Color.LIGHTPINK);

        FXGL.addUINode(rulesPreGame, 70, 300);

        FXGL.loopBGM("Endgame.mp3");
    }

    public static void clearPreGame(){
        Main.preGame = false;

        FXGL.getAudioPlayer().stopAllSoundsAndMusic();

        FXGL.getGameScene().removeUINode(titlePreGame);
        FXGL.getGameScene().removeUINode(rulesPreGame);
    }
}
