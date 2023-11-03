package Game;

import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.entity.component.Component;
import com.almasb.fxgl.entity.components.TransformComponent;

public class Behavior extends Component {
    public static final int MainCha = 0;
    public static final int Enemy = 1;
    public static final int Gai = 2;
    public static final int Obj = 3;
    // di chuyen , xoay
    private TransformComponent position;
    private int huong;
    private int type; // 0 - mainCha , 1 - enemy , 2 - gai , 3 - obj
    private double angle;

    public void setHuong(int huong){
        this.huong = huong;
    }

    public void setAngle(double angle){
        this.angle = angle;
    }

    public void setType(int type){
        this.type = type;
    }

    public void xoay(){
        entity.rotateBy(angle);
    }

    public void dichuyen(int x,int y){
        position.translate(x, y);

        if(!isInside()){
            position.translate(- x, - y);
        }
    }

    public int getX(){
        return (int)position.getX();
    }
    public int getY(){
        return (int) position.getY();
    }

    public int getW(){
        switch (type){
            case MainCha : {
                return Factory.MAINCHA_W;
            }
            case Enemy : {
                return Factory.ENEMY_W;
            }
            case Gai : {
                return Factory.GAI_W;
            }
            case Obj:{
                return Factory.OBJ_W;
            }
            default:{
                return 0;
            }
        }
    }

    public int getH(){
        switch (type){
            case MainCha : {
                return Factory.MAINCHA_H;
            }
            case Enemy : {
                return Factory.ENEMY_H;
            }
            case Gai : {
                return Factory.GAI_H;
            }
            case Obj:{
                return Factory.OBJ_H;
            }
            default:{
                return 0;
            }
        }
    }

    public boolean isInside(){
        int x = (int)position.getX();
        int y = (int)position.getY();

        return !((x <= 0) || (x >= Factory.BACKGROUND_W - getW() )
        || (y <= 0) || (y >= Factory.BACKGROUND_H - getH()));
    }

    public void dichuyen(){
        position.translate(Main.dx[huong], Main.dy[huong]);

        if(!isInside()){
            if(type == Gai || type == Obj){
                if(type == Obj){
                    Main.currentObj --;
                }

                entity.removeFromWorld();
            } else {
                position.translate(- Main.dx[huong], - Main.dy[huong]);
                if(type == Enemy)
                    changeDirection();
            }
        }
    }

    public void changeDirection(){
        int x = (int)position.getX();
        int y = (int)position.getY();

        if(y <= getH()){
            if(huong == 0) huong = 4;
            else if (huong == 1) huong = 3;
            else huong = 5;
        }

        if(x <= getW()){
            if(huong == 5) huong = 3;
            else if (huong == 6) huong = 2;
            else huong = 1;
        }

        if(x >= Factory.BACKGROUND_W - 2 * getW()){
            if(huong == 1) huong = 7;
            else if (huong == 2) huong = 6;
            else huong = 5;
        }

        if(y >= Factory.BACKGROUND_H - 2 * getH()){
            if(huong == 3) huong = 1;
            else if (huong == 4) huong = 0;
            else huong = 7;
        }
    }

    public static final int vBullet = 30;
    public void dichuyen(Entity e){
        int x = e.getComponent(Behavior.class).tamX();
        int y = e.getComponent(Behavior.class).tamY();

        int X = tamX();
        int Y = tamY();

        int kc = (int)Math.sqrt((x - X) * (x - X) + (y - Y) * (y - Y));

        if(kc <= 2) {
           // e.getComponent(Txt.class).upd();
            entity.removeFromWorld();

            return;
        }

        int dis = Math.min(vBullet , kc);

        position.translate((x - X ) * dis / kc , (y -  Y ) * dis / kc);
    }

    public int tamX(){
        return Math.min(getX() + getW() / 2 , Factory.BACKGROUND_W - 2 * getW());
    }

    public int tamY(){
        return Math.min(getY() + getH() / 2, Factory.BACKGROUND_H - 2 * getH());
    }

    public void changeDirection(int x,int y){
        int rd = FXGL.random(0, 1);

        if(rd == 0){
            huong = FXGL.random(0, 7);
        } else {
            int X = getX();
            int Y = getY();

            if(X <= x) {
                if(Y <= y){
                    huong = ((X == x) ? 4 : ((Y == y) ? 2 : 3));
                } else {
                    huong = ((X == x) ? 0 : 1);
                }
            } else {
                if(Y <= y) {
                    huong = ((Y == y) ? 6 : 5);
                } else {
                    huong = 7;
                }
            }
        }
    }
}
