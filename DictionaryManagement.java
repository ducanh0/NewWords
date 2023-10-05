import jdk.jshell.execution.DirectExecutionControl;

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
     * nhap du lieu tu tep dictionaries.txt (trong tep nay thi , moi dong co tu tieng anh + nghia tieng viet , vi du : hello xin chao)
     */

    public void insertFromFile(){

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
     * xuat du lieu ra tep
     */

    public void dictionaryExportToFile(){

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
