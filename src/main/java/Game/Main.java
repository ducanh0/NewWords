package Game;

import javafx.application.Application;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.Group;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

import java.io.File;
import java.io.InputStream;
import java.util.Objects;


public class Main extends Application
{
    public static void main(String[] args)
    {
        launch(args);
    }

    @Override
    public void start(Stage theStage) throws Exception
    {
        theStage.setTitle( "Canvas Example" );



        Text text = new Text(50 , 50, "Halo banana!");
        Font theFont = Font.loadFont( Main.class.getResource("../../../../data/font/chu5.ttf").toExternalForm(),  100 );
        System.out.println("" + (theFont == null));
        text.setFont(theFont);

//        InputStream is = Main.class.getResourceAsStream("data/font/chu5.ttf");
//        Font font = Font.loadFont(is, 12.0);
//        System.out.println("Font: " + (font == null));
//        File f = new File(Objects.requireNonNull(Main.class.getResource("data/font/chu5.ttf")).toURI());
//        // should not be used because of f.toURL() is deprecated
//        Font font1 = Font.loadFont(f.toURL().toExternalForm(), 12.0);
//        System.out.println("Font 1: " + (font1 == null));
//        Font font2 = Font.loadFont(f.toURI().toURL().toExternalForm(), 12.0);
//        System.out.println("Font 2: " + (font2 == null));
////
//        Group root = new Group();
//        Scene theScene = new Scene();
//        theStage.setScene( theScene );
////
//        Canvas canvas = new Canvas( 400, 200 );
//        root.getChildren().add( canvas );
//
//        GraphicsContext gc = canvas.getGraphicsContext2D();
//
//        gc.setFill( Color.RED );
//        gc.setStroke( Color.BLACK );
//        gc.setLineWidth(2);
//
//        gc.setFont( theFont );
//        gc.fillText( "Hello, World!", 60, 50 );
//        gc.strokeText( "Hello, World!", 60, 50 );

//        Image earth = new Image( "E:\\IT\\oop + dsa\\NewWords\\data\\image\\bullet.png" );
//        gc.drawImage( earth, 180, 100 );
//
//        theStage.show();
    }
}