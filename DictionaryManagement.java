import jdk.jshell.execution.DirectExecutionControl;

import java.io.*;
import java.util.ArrayList;
import java.util.Dictionary;
import java.util.Scanner;

/**
 * quan ly them, sua, xoa bang cmd, file , database ??
 * tim kiem tu vung , goi den MyDictionary
 */
public class DictionaryManagement {

    MyDictionary dictionary = new MyDictionary();
    Scanner scanner = new Scanner(System.in);

    /**
     * nhap so luong tu vung (goi la N)
     * 2 * N dong tiep theo tuong ung voi N cap (tu tieng anh - nghia tieng viet)
     * tu tieng anh 1
     * nghia tieng viet 1
     * tu tieng anh 2
     * nghia tieng viet 2
     * ...
     * tu tieng anh N
     * nghia tieng viet N
     */
    public void insertFromCommandline() {
        System.out.println("Nhap so luong tu vung: ");
        int N = scanner.nextInt();
        scanner.nextLine();

        for (int i = 0; i < N; i++) {
            System.out.print("Nhap tu tieng Anh: ");
            String englishWord = scanner.nextLine();

            System.out.print("Nhap nghia tieng Viet: ");
            String vietnameseMeaning = scanner.nextLine();

            Word word = new Word(englishWord, vietnameseMeaning);
            dictionary.addWord(word);
        }
    }

    /**
     * tra cuu
     * co the thay doi kieu tra ve trong qua trinh code , tra ve danh sach tu vung tim duoc chang han ?
     * cai tien : dung trie de tim kiem ??
     */

    public void dictionaryLookup() {
        System.out.print("Nhap tu can tra: ");
        String lookUpWord = scanner.nextLine();
        for (Word x : dictionary.getListWords()) {
            if (x.getWord_target().equals(lookUpWord)) {
                System.out.print(x.getWord_target());
                System.out.print(" co nghia la: ");
                System.out.println(x.getWord_explain());
            }
        }
    }

    /**
     * nhap du lieu tu tep dictionaries.txt (trong tep nay thi , moi dong co tu tieng anh + nghia tieng viet cach nhau boi dau tab, vi du : hello   xin chao)
     */

    public void insertFromFile(String fileName) {
        try {
            BufferedReader br = new BufferedReader(new FileReader(fileName));
            String line;

            while ((line = br.readLine()) != null) {
                String[] parts = line.split("\t"); // tu tieng Anh tach nghia tieng Viet boi 1 dau tab

                if (parts.length == 2) {
                    String englishWord = parts[0].trim(); // trim() xoa khoang trang thua
                    String vietnameseExplanation = parts[1].trim();

                    Word word = new Word(englishWord, vietnameseExplanation);
                    dictionary.addWord(word);
                }
            }

            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * xuat du lieu ra tep DictionaryExport.txt
     */

    public void dictionaryExportToFile(){
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter("DictionaryExport.txt"));

            for (Word x : dictionary.getListWords()) {
                bw.write(x.getWord_target() + "\t" + String.join(", ", x.getWord_explain()));
                bw.newLine();
            }

            bw.close();
            System.out.println("Du lieu tu dien da duoc xuat ra tep DictionaryExport.txt");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * them tu vao tu dien
     */
    public void addWord() {

    }

    /**
     * xoa 1 tu trong tu dien
     */
    public void removeWord() {

    }

    /**
     * sua du lieu tu vung
     */
    public void adjustWord() {

    }

    public void showAllWords() {
    }
}
