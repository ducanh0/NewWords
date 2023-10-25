package Game;
/**
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.io.File;
import java.util.Scanner;

public class Sound{
    private static Clip clip ;
    private static File [] soundURL = new File[3];

    Sound(){
        soundURL[0] = new File("E:\\IT\\oop + dsa\\NewWords\\data\\sound\\hurt_njs.wav");
        soundURL[2] = new File("E:\\IT\\oop + dsa\\NewWords\\data\\sound\\background.wav");
        soundURL[1] = new File("E:\\IT\\oop + dsa\\NewWords\\data\\sound\\nhacnen.wav");
    }

    public void setFile(int i){
      //  System.out.println("?? " );
        try{
          //  System.out.println("?? " );
            AudioInputStream ais = AudioSystem.getAudioInputStream(soundURL[i]);
          //  System.out.println("?? " + (ais == null));
            clip = AudioSystem.getClip();
            clip.open(ais);

         //   System.out.println("?? " );
        }catch (Exception e){
           // System.out.println("?? " );
        }
    }

    public void play(){
        clip.start();
    }

    public void loop(){
        clip.loop(Clip.LOOP_CONTINUOUSLY);
    }

    public void stop(){
        clip.stop();
    }

    public static void main(String [] args) throws Exception{
        Sound kltm = new Sound();

        Scanner io = new Scanner(System.in);
        kltm.setFile(2);
        kltm.play();
        kltm.loop();

        String quit = io.next();
    }
}
/**
 * Scanner io = new Scanner(System.in);
 *
 *         File file = new File("E:\\IT\\oop + dsa\\NewWords\\data\\sound\\hurthurt.wav");
 *
 *         AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(file);
 *
 *         Clip clip = AudioSystem.getClip();
 *
 *         clip.open(audioInputStream);
 *
 *         String kltm = "";
 *
 *         while (! kltm.equals("q")){
 *             System.out.println("p = play, s = stop, q = quit");
 *             kltm = io.next();
 *
 *             switch (kltm){
 *                 case "p" : {
 *                     clip.start();
 *                     break;
 *                 }
 *                 case "s" : {
 *                     clip.stop();
 *                     break;
 *                 }
 *                 case "q" : {
 *                     clip.close();
 *                     break;
 *                 }
 *                 default:{
 *                     System.out.println("thao tac khong hop le");
 *                 }
 *             }
 *         }
 */