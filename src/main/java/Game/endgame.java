package Game;

import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.entity.SpawnData;
import javafx.scene.Group;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

import static com.almasb.fxgl.dsl.FXGLForKtKt.getGameScene;
import static com.almasb.fxgl.dsl.FXGLForKtKt.getWorldProperties;

public class endgame {
    private static Text score, mess, titleChart, X, Y;
    public static void renderEndGame(){
        Font font = Font.loadFont("file:src/main/resources/assets/fonts/chu8.ttf", 40);

        titleChart = new Text("Accurate chart");
        titleChart.setFont(font);
        titleChart.setTranslateX(647);
        titleChart.setTranslateY(33);
        titleChart.setFill(Color.LIGHTSALMON);

        getGameScene().addUINode(titleChart);

        mess = new Text("From Uet with love <3");
        mess.setFont(font);
        mess.setFill(Color.LIGHTPINK);
        mess.setTranslateX(970);
        mess.setTranslateY(500);

        getGameScene().addUINode(mess);

        score = new Text();
        score.setFont(font);
        score.setTranslateX(300);
        score.setTranslateY(500);
        score.setFill(Color.LIGHTPINK);

        score.textProperty().bind(getWorldProperties().intProperty("score").asString("Final score: %d"));

        getGameScene().addUINode(score);

        Main.endGame = true;

        FXGL.spawn("Background", new SpawnData(0, 0).put("link", "back-end.png"));
        FXGL.loopBGM("Pregame.wav");

        //chart
        Font font2 = Font.loadFont("file:src/main/resources/assets/fonts/chu2.ttf", 15);
        X = new Text("Accurate %");
        X.setFont(font2);
        X.setTranslateX(200);
        X.setTranslateY(30);
        X.setFill(Color.LIGHTCYAN);

        Y = new Text("Total types");
        Y.setFont(font2);
        Y.setTranslateY(420);
        Y.setTranslateX(1200);
        Y.setFill(Color.LIGHTCYAN);

        getGameScene().addUINode(X);
        getGameScene().addUINode(Y);

        Group chart = new Group();

        Line baseX = new Line(250, 370, 1250, 370);
        baseX.setStroke(Color.LIGHTSTEELBLUE);

        Line baseY = new Line(250, 50, 250, 370);
        baseY.setStroke(Color.LIGHTSTEELBLUE);

        int donVi = 32, pre = 370, percent = 0;
        Font font1 = Font.loadFont("file:src/main/resources/assets/fonts/chu2.ttf", 17);
        for(int i = 0;i <= 10;i ++, percent += 10){
            if(i > 0){
                pre -= donVi;

                Line vachY = new Line(247, pre, 253, pre);
                vachY.setStroke(Color.LIGHTSTEELBLUE);

                chart.getChildren().add(vachY);
            }

            Label lb = new Label("" + percent);
            lb.setFont(font1);
            lb.setTextFill(Color.LIGHTYELLOW);

            FXGL.addUINode(lb, 215, pre - 10);
        }

        if(Main.totalTypes % 5 != 0){
            Main.arr.add(100.0 * Main.correctTypes / Main.totalTypes);
        }

        if(! Main.arr.isEmpty()){
            pre = 250; donVi = 1000 / Main.arr.size();
            int preTypes = 0;
            double prePercent = 370.0;

            for(double i : Main.arr){
                pre += donVi;
                preTypes += 5;

                Line vachX = new Line(pre, 367, pre, 373);
                vachX.setStroke(Color.LIGHTSTEELBLUE);

                chart.getChildren().add(vachX);

                Line l = new Line(pre - donVi, prePercent, pre, 370.0 - 3.2 * i);
                l.setStroke(Color.LIGHTSEAGREEN);

                chart.getChildren().add(l);

                prePercent = 370.0 - 3.2 * i;

                if(Main.arr.size() <= 50){
                    if(preTypes % 10 == 0){
                        Label lb = new Label("" + preTypes);
                        lb.setFont(font1);
                        lb.setTextFill(Color.LIGHTYELLOW);

                        FXGL.addUINode(lb, pre - 10, 375);
                    }
                } else {
                    if(preTypes % 20 == 0){
                        Label lb = new Label("" + preTypes);
                        lb.setFont(font1);
                        lb.setTextFill(Color.LIGHTYELLOW);

                        FXGL.addUINode(lb, pre - 10, 375);
                    }
                }
            }
        }

        chart.getChildren().add(baseY);
        chart.getChildren().add(baseX);

        FXGL.addUINode(chart);
    }

    public static void clearEndGame(){
        Main.endGame = false;
    }
}
