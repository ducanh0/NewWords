package main;

import java.util.Scanner;

/**
 * dieu phoi ung dung
 * quyet dinh chay phien ban nao , cmd hay do hoa
 * game toi se add vao sau
 */
public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Hãy chọn phiên bản ứng dụng mà bạn muốn chạy!");
        System.out.println("[0] Chạy bản commandline");
        System.out.println("[1] Chạy bản đồ họa");
        System.out.print("Your action: ");

       // while (scanner.hasNext()) {
            int act = scanner.nextInt();
            if (act == 0) {
               // System.out.println("");
                System.out.println("\n**********************************\n");
                DictionaryCommandline.main(args);
//                break;
            } else if (act == 1) {
             //   System.out.println("");
                System.out.println("\n**********************************\n");
                com.example.newwords.Main.main(args);
//                break;
            } else {
                System.out.print("Your action must be 0 or 1, try again");
            }
        }
    //}
}
