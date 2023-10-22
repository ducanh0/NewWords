import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * quan ly them, sua, xoa bang cmd, file , database ??
 * tim kiem tu vung , goi den MyDictionary
 */
public class DictionaryManagement {

    MyDictionary dictionary;
    Scanner scanner;

    public DictionaryManagement(Scanner scanner) {
        this.dictionary = new MyDictionary();
        this.scanner = scanner;
    }

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
        scanner.nextLine();
        String lookUpWord = scanner.nextLine();
//        for (Word x : dictionary.getListWords()) {
//            if (x.getWord_target().equals(lookUpWord)) {
//                System.out.print(x.getWord_target());
//                System.out.print(" co nghia la: ");
//                System.out.println(x.getWord_explain());
//            }
//        }
        Word x = dictionary.findWord(lookUpWord);
        if (x != null) {
            System.out.print(x.getWord_target());
            System.out.print(" co nghia la: ");
            System.out.println(x.getWord_explain());
        } else {
            System.out.println("Khong tim thay tu nao");
        }
    }

    /**
     * nhap du lieu tu tep dictionaries.txt (trong tep nay thi
     * moi dong co tu tieng anh + nghia tieng vietcach nhau boi regex)
     * @regex la "\t" hoac ","
     */

    public void insertFromFile(String fileName, String regex) {
        try {
            BufferedReader br = new BufferedReader(new FileReader(fileName));
            String line;

            while ((line = br.readLine()) != null) {
                String[] parts = line.split(regex);

                if (parts.length >= 2) {
                    String englishWord = parts[0].trim(); // trim() xoa khoang trang thua
                    String vietnameseExplanation = parts[1].trim();

                    Word word = new Word(englishWord, vietnameseExplanation);
                    dictionary.addWord(word);
                }
            }

            br.close();
            System.out.println("Du lieu tu dien da duoc lay tu tep");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * xuat du lieu ra tep DictionaryExport.txt
     */

    public void dictionaryExportToFile(){
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter("DictionaryExport.txt", false));

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
        scanner.nextLine();
        System.out.print("Nhap tu tieng Anh: ");
        String word = scanner.nextLine();
        System.out.print("Nhap nghia tieng Viet: ");
        String explanation = scanner.nextLine();
        if (!word.isEmpty())
            dictionary.addWord(new Word(word, explanation));
    }

    /**
     * xoa 1 tu trong tu dien
     */
    public void removeWord() {
        System.out.print("Nhap tu can xoa: ");
        dictionary.removeWord(new Word(scanner.next(), null));
    }

    /**
     * sua du lieu tu vung
     */
    public void adjustWord() {
        scanner.nextLine();
        System.out.print("Nhap tu can sua: ");
        String word = scanner.nextLine();
        System.out.print("Nhap nghia moi cua tu: ");
        String explain = scanner.nextLine();

        dictionary.removeWord(new Word(word, null));
        dictionary.addWord(new Word(word, explain));
//        dictionary.fixWord(new Word(word, null), new Word(word, explain));
    }

    public void showAllWords() {
        int index = 0;
        ArrayList<Word> wordArrayList = dictionary.getListWords();
        System.out.println("Tong so tu: " + wordArrayList.size());
        System.out.println("No| English     | Vietnamese");

        for (Word word: wordArrayList) {
            index++;
            System.out.printf("%-2d",index);
            System.out.println("| " + word.toString());
        }
    }

    /** tim kiem tu bang trie.*/
    public void searchWords() {
        System.out.println("Tien to can tim: ");
        scanner.nextLine();
        String prefix = scanner.nextLine();
        int index = 0;
        for (Word word: dictionary.findWords(prefix)) {
            index++;
            System.out.printf("%-2d",index);
            System.out.println("| " + word.toString());
        }
    }
}
