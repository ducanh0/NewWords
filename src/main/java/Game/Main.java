package Game;

import com.almasb.fxgl.app.GameApplication;
import com.almasb.fxgl.app.GameSettings;
import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.input.*;
import com.almasb.fxgl.physics.CollisionHandler;
import javafx.scene.input.KeyCode;
import java.util.*;
import static com.almasb.fxgl.dsl.FXGLForKtKt.*;

public class Main extends GameApplication {
    public static int slGai = 8;
    public static final int dx[] = {0, 1, 1, 1, 0, -1, -1, -1};
    public static final int dy[] = {-1, -1, 0, 1, 1, 1, 0, -1};
    public static int totalTypes = 0;
    public static int correctTypes = 0;
    public static int totalEnemies = 0; // de gan id cho enemy
    public static int id = -1; // luu thg dang go phim de tieu diet
    public static int slMaxEnemy = 5; // tang theo tgian
    public static int slMaxObj = 5; // tang theo tgian
    public static int currentEnemy = 0;
    public static int currentObj = 0;
    public static ArrayList<Double> arr = new ArrayList<>();
    // store accurate each 5 times of types, use for render chart in endgame
    public static boolean preGame = false;
    public static boolean inGame = false;
    public static boolean endGame = false;

    public static void main(String [] args){
        launch(args);
    }
    @Override
    protected void initSettings(GameSettings settings){
        settings.setWidth(Factory.BACKGROUND_W);
        settings.setHeight(Factory.BACKGROUND_H);
        settings.setTitle("NewTypes");
        settings.setVersion("23-24");
    }

    @Override
    protected void initGame(){
        FXGL.getGameWorld().addEntityFactory(new Factory());

        pregame.renderPreGame();
    }

    @Override
    protected void initPhysics(){
        getPhysicsWorld().addCollisionHandler(new CollisionHandler(EntityType.MAINCHA, EntityType.ENEMY) {
            @Override
            protected void onCollisionBegin(Entity a, Entity b) {
                Main.correctTypes = Math.max(0, Main.correctTypes - 3);
                ingame.reCal();
            }
        });

        getPhysicsWorld().addCollisionHandler(new CollisionHandler(EntityType.MAINCHA, EntityType.GAI) {
            @Override
            protected void onCollisionBegin(Entity a, Entity b) {
                Main.correctTypes = Math.max(0, Main.correctTypes - 3);
                ingame.reCal();
            }
        });

        getPhysicsWorld().addCollisionHandler(new CollisionHandler(EntityType.MAINCHA, EntityType.OBJ) {
            @Override
            protected void onCollisionBegin(Entity a, Entity b) {
                Main.correctTypes = Math.max(0, Main.correctTypes - 3);
                ingame.reCal();
            }
        });
    }

    @Override
    protected void initInput(){
        UserAction userAction = new UserAction("Move Down") {
            @Override
            protected void onActionBegin() {
                if(! inGame) return;

                Entity e = getGameWorld().getSingleton(EntityType.MAINCHA);
                if(e != null && e.isActive()){
                    e.getComponent(Behavior.class).dichuyen(0, 5);
                }
            }

            @Override
            protected void onAction() {
                if(! inGame) return;

                Entity e = getGameWorld().getSingleton(EntityType.MAINCHA);
                if(e != null && e.isActive()){
                    e.getComponent(Behavior.class).dichuyen(0, 5);
                }
            }
        };
        getInput().addAction(userAction, KeyCode.DOWN);

        userAction = new UserAction("Move Up") {
            @Override
            protected void onActionBegin() {
                if(! inGame) return;

                Entity e = getGameWorld().getSingleton(EntityType.MAINCHA);
                if(e != null && e.isActive()){
                    e.getComponent(Behavior.class).dichuyen(0, -5);
                }
            }

            @Override
            protected void onAction() {
                if(! inGame) return;

                Entity e = getGameWorld().getSingleton(EntityType.MAINCHA);
                if(e != null && e.isActive()){
                    e.getComponent(Behavior.class).dichuyen(0, -5);
                }
            }
        };
        getInput().addAction(userAction, KeyCode.UP);

        userAction = new UserAction("Move Left") {
            @Override
            protected void onActionBegin() {
                if(! inGame) return;

                Entity e = getGameWorld().getSingleton(EntityType.MAINCHA);
                if(e != null && e.isActive()){
                    e.getComponent(Behavior.class).dichuyen( -5, 0);
                }
            }

            @Override
            protected void onAction() {
                if(! inGame) return;

                Entity e = getGameWorld().getSingleton(EntityType.MAINCHA);
                if(e != null && e.isActive()){
                    e.getComponent(Behavior.class).dichuyen(-5, 0);
                }
            }
        };
        getInput().addAction(userAction, KeyCode.LEFT);

        userAction = new UserAction("Move Right") {
            @Override
            protected void onActionBegin() {
                if(! inGame) return;

                Entity e = getGameWorld().getSingleton(EntityType.MAINCHA);
                if(e != null && e.isActive()){
                    e.getComponent(Behavior.class).dichuyen(5, 0);
                }
            }

            @Override
            protected void onAction() {
                if(! inGame) return;

                Entity e = getGameWorld().getSingleton(EntityType.MAINCHA);
                if(e != null && e.isActive()){
                    e.getComponent(Behavior.class).dichuyen(5, 0);
                }
            }
        };
        getInput().addAction(userAction, KeyCode.RIGHT);

        userAction = new UserAction("Quit") {
            @Override
            protected void onActionBegin() {
                if(preGame){
                    pregame.clearPreGame();
                }
                 if(inGame) {
                    ingame.clearInGame();
                }
                if(! endGame){
                    endgame.renderEndGame();
                }
            }
        };
        getInput().addAction(userAction, KeyCode.ESCAPE);

        userAction = new UserAction("Play Game") {
            @Override
            protected void onActionBegin() {
                if(endGame) return;

                if(preGame){
                    pregame.clearPreGame();
                }
                if(! inGame){
                    ingame.renderInGame();
                }
            }
        };
        getInput().addAction(userAction, KeyCode.ENTER);

        getInput().addTriggerListener(new TriggerListener() {
            @Override
            protected void onActionBegin(Trigger trigger) {
                if(trigger.isKey()){
                    var c = (KeyTrigger) trigger;
                    KeyCode key = c.getKey();

                    ingame.sol(key);
                }
            }
        });
    }

    @Override
    protected void initGameVars(Map<String, Object> vars){
        vars.put("accurate", 0.0);
        vars.put("score", 0);
    }
}