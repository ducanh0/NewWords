package Game;

import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.entity.component.Component;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Txt extends Component {
    private static List<String> arr = new ArrayList<>();
    private static final Font font = Font.loadFont("file:src/main/resources/assets/fonts/chu.ttf", 20);
    private Label lb;
    private String s;
    int id ; // vi tri bat dau cua string

    public Txt(){
        if(arr == null || arr.isEmpty()){
            arr = new ArrayList<>();
            try(Scanner io = new Scanner(new File("src/main/resources/assets/text/vocab.txt"))){
                while (io.hasNext()){
                    arr.add(io.next());
                }
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
        s = arr.remove(arr.size() - 1);
        id = 0;
    }

    public boolean isDead(){
        return (id == s.length());
    }

    public void upd(){
        if(isDead()) return;

        id ++;
    }

    public boolean isMatch(char c){
        if(isDead()) return false;

        return (s.charAt(id) == c);
    }

    public void render(int x,int y){
        if(isDead()) return;

        clear();

        lb = new Label(s.substring(id));
        lb.setFont(font);

        if(id == 0){
            lb.setTextFill(Color.MINTCREAM);

        } else {
            lb.setTextFill(Color.YELLOW);
        }

        FXGL.addUINode(lb, x, y);
    }

    public void clear(){
        if(lb != null){
            FXGL.removeUINode(lb);
            lb = null;
        }
    }
}
