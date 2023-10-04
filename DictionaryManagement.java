import jdk.jshell.execution.DirectExecutionControl;

import java.util.Dictionary;
import java.util.Scanner;

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
     * them du lieu tu dong lenh
     */

    public void addNewWordsFromCmd(){

    }

    /**
     * xoa du lieu tu dong lenh
     */

    public void removeWordsFromCmd(){

    }

    /**
     * sua du lieu tu dong lenh , sua y nghia cua tu (vi du : sua nghia cua 'hello' tu 'xin chao' thanh 'halo' , vi du vui thoi :v)
     */

    public void adjustWordsFromCmd(){

    }

    /**
     * xuat du lieu ra tep
     */

    public void dictionaryExportToFile(){

    }


}
