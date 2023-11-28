package Game;

import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.entity.SpawnData;
import com.almasb.fxgl.entity.components.IDComponent;
import com.almasb.fxgl.time.TimerAction;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.util.Duration;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import static com.almasb.fxgl.dsl.FXGLForKtKt.*;
import static com.almasb.fxgl.dsl.FXGLForKtKt.getGameWorld;

public class ingame {
    private static TimerAction runInGame, changeInGame;
    private static Text score, accurate;
    public static void renderInGame() {
        Main.inGame = true;

        Font font = Font.loadFont("file:src/main/resources/assets/fonts/chu2.ttf", 30);

        score = new Text();
        score.setFont(font);
        score.setTranslateX(1200);
        score.setTranslateY(35);
        score.setFill(Color.LIGHTGOLDENRODYELLOW);

        score.textProperty().bind(getWorldProperties().intProperty("score").asString("Score: %d"));

        getGameScene().addUINode(score);

        accurate = new Text();
        accurate.setFont(font);
        accurate.setTranslateX(1200);
        accurate.setTranslateY(70);
        accurate.setFill(Color.LIGHTGOLDENRODYELLOW);

        accurate.textProperty().bind(getWorldProperties().doubleProperty("accurate").asString("Accurate: %.2f"));

        getGameScene().addUINode(accurate);

        // init
        FXGL.loopBGM("Ingame.wav");

        FXGL.spawn("Background", new SpawnData(0, 0).put("link", "back-in.png"));

        FXGL.spawn("Maincha", new SpawnData(Factory.BACKGROUND_W / 2, Factory.BACKGROUND_H / 2).put("link", "main.png")).getComponent(Behavior.class).setType(Behavior.MainCha);

        for(int i = 0;i < Main.slMaxEnemy;i ++){
            Entity a = FXGL.spawn("Enemy", new SpawnData(FXGL.random(100 , 1400), 10).put("link", "ene" + FXGL.random(0, 4) + ".png"));

            a.addComponent(new IDComponent("ene", Main.totalEnemies ++));

            a.getComponent(Behavior.class).setType(Behavior.Enemy);
            a.getComponent(Behavior.class).setHuong(FXGL.random(3, 5));
        }
        Main.currentEnemy = Main.slMaxEnemy;

        for(int i = 0;i < Main.slMaxObj;i ++){
            Entity b = FXGL.spawn("Obj", new SpawnData(FXGL.random(100, 1400), 10).put("link", "mystery" + FXGL.random(0, 1) +".png"));

            b.getComponent(Behavior.class).setType(Behavior.Obj);
            b.getComponent(Behavior.class).setHuong(FXGL.random(3, 5));
        }
        Main.currentObj = Main.slMaxObj;

        // run game
        runInGame = getGameTimer().runAtInterval(() -> {
            if(Main.correctTypes == 0 && Main.totalTypes > 0){
                FXGL.play("maindie.wav");
                getInput().mockKeyPress(KeyCode.ESCAPE);
            }

            List<Entity> enemies = getGameWorld().getEntitiesByType(EntityType.ENEMY);
            for(Entity e : enemies){
                if(e.getComponent(Txt.class).isDead()){
                    FXGL.play("enemydie.wav");
                    inc("score", 1);
                    Main.currentEnemy --;

                    bangai(e);

                    e.getComponent(Txt.class).clear();
                    e.removeFromWorld();

                    List<Entity> bullets = getGameWorld().getEntitiesByType(EntityType.BULLET);
                    for(Entity e0 : bullets){
                        e0.removeFromWorld();
                    }

                    if(e.getComponent(IDComponent.class).getId() == Main.id){
                        Main.id = -1;
                    }

                    continue;
                }

                int x = e.getComponent(Behavior.class).getX();
                int y = e.getComponent(Behavior.class).getY();
                int h = e.getComponent(Behavior.class).getH();

                e.getComponent(Behavior.class).dichuyen();

                e.getComponent(Txt.class).render(x , y + h);
            }

            List<Entity> gais = getGameWorld().getEntitiesByType(EntityType.GAI);
            for(Entity e : gais){
                e.getComponent(Behavior.class).dichuyen();
                e.getComponent(Behavior.class).xoay();
            }

            List<Entity> objs = getGameWorld().getEntitiesByType(EntityType.OBJ);
            for(Entity e : objs){
                e.getComponent(Behavior.class).dichuyen();
            }

            Iterator<Entity> it = getGameWorld().getEntityByID("ene", Main.id).stream().iterator();
            if(it.hasNext()){
                Entity e = it.next();

                List<Entity> bullets = getGameWorld().getEntitiesByType(EntityType.BULLET);
                for(int i = 0;i < bullets.size();i ++){
                    bullets.get(i).getComponent(Behavior.class).dichuyen(e);
                }
            }
        }, Duration.millis(10));

        // change huong + re-spawn
        changeInGame = getGameTimer().runAtInterval(() -> {
            Entity a0 = getGameWorld().getSingleton(EntityType.MAINCHA);
            int x = a0.getComponent(Behavior.class).getX();
            int y = a0.getComponent(Behavior.class).getY();

            Optional<Entity> op = getGameWorld().getRandom(EntityType.OBJ);
            Iterator<Entity> it = op.stream().iterator();

            while (it.hasNext()){
                it.next().getComponent(Behavior.class).changeDirection(x, y);
            }

            op = getGameWorld().getRandom(EntityType.ENEMY);
            it = op.stream().iterator();

            while (it.hasNext()){
                it.next().getComponent(Behavior.class).changeDirection(x, y);
            }

            if(Main.currentEnemy < 10){
                for(int i = Main.currentEnemy;i < Main.slMaxEnemy;i ++){
                    Entity a = FXGL.spawn("Enemy", new SpawnData(FXGL.random(100 , 1400), 10).put("link", "ene" + FXGL.random(0, 4) + ".png"));

                    a.addComponent(new IDComponent("ene", Main.totalEnemies ++));

                    a.getComponent(Behavior.class).setType(Behavior.Enemy);
                    a.getComponent(Behavior.class).setHuong(FXGL.random(3, 5));
                }
                Main.currentEnemy = Main.slMaxEnemy;
            }

            if(Main.currentObj < 20){
                for(int i = Main.currentObj;i < Main.slMaxObj;i ++){
                    Entity b = FXGL.spawn("Obj", new SpawnData(FXGL.random(100, 1400), 10).put("link", "mystery" + FXGL.random(0, 1) +".png"));

                    b.getComponent(Behavior.class).setType(Behavior.Obj);
                    b.getComponent(Behavior.class).setHuong(FXGL.random(3, 5));
                }
                Main.currentObj = Main.slMaxObj;
            }
        }, Duration.millis(1000));
    }

    public static void bangai(Entity e){
        int x = e.getComponent(Behavior.class).tamX();
        int y = e.getComponent(Behavior.class).tamY();

        for(int i = 0;i < Main.slGai;i ++){
            Entity g0 = FXGL.spawn("Gai", new SpawnData(x, y).put("link", "gai.png"));
            g0.getComponent(Behavior.class).setHuong(i);
            g0.getComponent(Behavior.class).setAngle(10);
            g0.getComponent(Behavior.class).setType(Behavior.Gai);
        }
    }

    public static void clearInGame(){
        Main.inGame = false;

        runInGame.expire();
        changeInGame.expire();

        FXGL.getAudioPlayer().stopAllSoundsAndMusic();

        FXGL.getGameScene().removeUINode(score);
        FXGL.getGameScene().removeUINode(accurate);

        List<Entity> l = getGameWorld().getEntitiesByType(EntityType.MAINCHA);
        for(Entity e : l){
            e.removeFromWorld();
        }

        l = getGameWorld().getEntitiesByType(EntityType.GAI);
        for(Entity e : l){
            e.removeFromWorld();
        }

        l = getGameWorld().getEntitiesByType(EntityType.OBJ);
        for(Entity e : l){
            e.removeFromWorld();
        }

        l = getGameWorld().getEntitiesByType(EntityType.BULLET);
        for(Entity e : l){
            e.removeFromWorld();
        }

        l = getGameWorld().getEntitiesByType(EntityType.ENEMY);
        for(Entity e : l){
            e.getComponent(Txt.class).clear();
            e.removeFromWorld();
        }

        l = getGameWorld().getEntitiesByType(EntityType.BACKGROUND);
        for(Entity e : l){
            e.removeFromWorld();
        }
    }

    public static void reCal(){
        if(Main.totalTypes == 0);
        else {
            double d = 100.0 * Main.correctTypes / Main.totalTypes;
            FXGL.getWorldProperties().setValue("accurate", d);

            if(Main.totalTypes % 5 == 0){
                Main.arr.add(d);
            }
        }

        if(Main.totalTypes != 0 && Main.totalTypes % 30 == 0){
            Main.slMaxEnemy = Math.min(50, Main.slMaxEnemy + 2);
            Main.slMaxObj = Math.min(50, Main.slMaxObj + 3);
        }
    }

    public static void solve(char c){
        Main.totalTypes ++ ;

        if((c >= 'a') && (c <= 'z')){
            if(Main.id == -1){
                // for de tim thg khop
                List<Entity> l = getGameWorld().getEntitiesByType(EntityType.ENEMY);

                for(Entity e : l){
                    if(e.getComponent(Txt.class).isMatch(c)){
                        // ban dan
                        Main.correctTypes ++;
                        e.getComponent(Txt.class).upd();

                        bandan();
                        Main.id = e.getComponent(IDComponent.class).getId();
                        break;
                    }
                }
            } else {
                Optional<Entity> op = getGameWorld().getEntityByID("ene", Main.id);
                Iterator<Entity> it = op.stream().iterator();

                while (it.hasNext()){
                    Entity e = it.next();

                    if(e.getComponent(Txt.class).isMatch(c)){
                        // ban dan
                        Main.correctTypes ++;
                        e.getComponent(Txt.class).upd();

                        bandan();
                        break;
                    }
                }
            }
        }
        reCal();
    }

    public static void bandan(){
        FXGL.play("bullet.wav");

        Entity a0 = getGameWorld().getSingleton(EntityType.MAINCHA);

        FXGL.spawn("Bullet", new SpawnData(a0.getComponent(Behavior.class).tamX() , a0.getComponent(Behavior.class).tamY()).put("link", "bullet.png"));
    }

    public static void sol(KeyCode c){
        switch (c){
            case A : {
                solve('a');
                break;
            }
            case B : {
                solve('b');
                break;
            }
            case C:  {
                solve('c');
                break;
            }
            case D:  {
                solve('d');
                break;
            }
            case E : {
                solve('e');
                break;
            }
            case F : {
                solve('f');
                break;
            }
            case G : {
                solve('g');
                break;
            }
            case H : {
                solve('h');
                break;
            }
            case I : {
                solve('i');
                break;
            }
            case J : {
                solve('j');
                break;
            }
            case K : {
                solve('k');
                break;
            }
            case L : {
                solve('l');
                break;
            }
            case M : {
                solve('m');
                break;
            }
            case N : {
                solve('n');
                break;
            }
            case O : {
                solve('o');
                break;
            }
            case P : {
                solve('p');
                break;
            }
            case Q : {
                solve('q');
                break;
            }
            case R : {
                solve('r');
                break;
            }
            case S : {
                solve('s');
                break;
            }
            case T : {
                solve('t');
                break;
            }
            case U : {
                solve('u');
                break;
            }
            case V : {
                solve('v');
                break;
            }
            case W : {
                solve('w');
                break;
            }
            case X : {
                solve('x');
                break;
            }
            case Y : {
                solve('y');
                break;
            }
            case Z : {
                solve('z');
                break;
            }
            default:{

            }
        }
    }
}
