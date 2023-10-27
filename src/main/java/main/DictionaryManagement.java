package main;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * quan ly them, sua, xoa bang cmd, file , database ??
 * tim kiem tu vung , goi den main.MyDictionary
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
        if (lookUpWord.isEmpty()) return;

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
     *
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

    public void dictionaryExportToFile() {
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
        if (!word.isEmpty() && !explanation.isEmpty())
            dictionary.addWord(new Word(word, explanation));
    }

    /**
     * xoa 1 tu trong tu dien
     */
    public void removeWord() {
        System.out.print("Nhap tu can xoa: ");
        dictionary.removeWord(new Word(scanner.next()));
    }

    /**
     * sua du lieu tu vung
     * [0] sua tu tieng anh
     * [1] sua nghia tieng viet
     * [0] them nghia moi
     * [1] xoa nghia cu
     */
    public void adjustWord() {
        System.out.println("Ban co 02 lua chon:\n [0] Sua 01 tu tieng anh da ton tai\n [1] Sua nghia tieng viet cua 01 tu tieng anh da ton tai");
        System.out.print("Lua chon cua ban: ");

        int act = scanner.nextInt();
        switch (act) {
            case 0: {
                System.out.println("Hay nhap tu tieng anh ban muon sua:");
                scanner.nextLine();

                do {
                    String old = scanner.nextLine();
                    if (old.equals("-1")) {
                        break;
                    }

                    Word x = dictionary.findWord(old);
                    if (x != null) {
                        System.out.println("Nhap tu tieng anh moi, tap hop nghia tieng viet cua tu tieng anh moi se la tap hop nghia tieng viet cua tu tieng anh cu");
                        System.out.println("[CHU Y] Tu tieng anh moi phai khac tu tieng anh cu");

                        String newWord = scanner.nextLine();

                        if (!newWord.isEmpty() && !newWord.equals(old)) {
                            dictionary.fixWord(x, new Word(newWord, x.getWord_explain()));
                        } else {
                            System.out.println("Thao tac khong hop le, hay thu lai neu muon");
                        }

                        break;

                    } else {
                        System.out.println("Tu nay khong ton tai, hay thu lai neu muon, nhap -1 neu muon dung lai");
                    }
                }while (true) ;

                break;
            }
            case 1: {
                System.out.println("Nhap tu tieng anh ban can sua nghia tieng viet:");
                scanner.nextLine();

                do {
                    String old = scanner.nextLine();
                    if (old.equals("-1")) {
                        break;
                    }

                    Word x = dictionary.findWord(old);
                    if (x != null) {
                        System.out.println("Ban co 02 lua chon\n [0] Them 01 nghia tieng viet moi\n [1] Xoa nghia tieng viet cu");
                        System.out.print("Lua chon cua ban : ");

                        int actt = scanner.nextInt();
                        switch (actt) {
                            case 0: {
                                System.out.println("Nhap nghia tieng viet moi can them vao:");
                                scanner.nextLine();

                                String newMeaning = scanner.nextLine();
                                if (!newMeaning.isEmpty()) x.setWord_explain(newMeaning);

                                break;
                            }
                            case 1: {
                                System.out.println("Cac nghia tieng viet hien tai:");

                                for (int i = 0; i < x.getWord_explain().size(); i++) {
                                    System.out.println("[" + i + "] " + x.getWord_explain().get(i));
                                }

                                System.out.println("Nhap nhung chi so cua nghia tieng viet ban muon xoa, nhap -1 de dung lai");
                                System.out.println("[CHU Y] Neu tu tieng anh bi xoa het tat ca cac nghia tieng viet thi no se bi xoa khoi csdl");

                                ArrayList<Integer> arr = new ArrayList<>();
                                do {
                                    int id = scanner.nextInt();
                                    if (id == -1) {
                                        break;
                                    }
                                    if ((id >= 0) && (id < x.getWord_explain().size())) {
                                        arr.add(id);
                                    }
                                } while (true);

                                int slTuDaXoa = 0;
                                for (int i = 0; i < x.getWord_explain().size(); i++) {
                                    if (arr.contains(i + slTuDaXoa)) {
                                        x.getWord_explain().remove(i);
                                        i--;
                                        slTuDaXoa++;
                                    }
                                }

                                if(x.getWord_explain().isEmpty()){
                                    dictionary.removeWord(x);
                                }
                                break;
                            }
                            default: {
                                System.out.println("Thao tac khong hop le, hay thu lai neu muon");
                            }
                        }
                        break;
                    } else {
                        System.out.println("Tu nay khong ton tai, hay thu lai neu muon, nhap -1 neu muon dung lai");
                    }
                } while (true);
                break;
            }
            default: {
                System.out.println("Thao tac khong hop le, hay thu lai neu muon");
            }
        }
    }

    public void showAllWords() {
        int index = 0;

        ArrayList<Word> wordArrayList = dictionary.getListWords();
        int size = wordArrayList.size();

        int lenSize = Math.max((int) Math.log10(size) + 1, 2);

        System.out.println("Tong so tu: " + wordArrayList.size());

        System.out.printf("%" + lenSize + "s", "No ");
        System.out.println("| English     | Vietnamese");
        System.out.println("-----------------------------");

        for (Word word: wordArrayList) {
            index++;
            System.out.printf("%" + lenSize + "d ", index);

            System.out.println("| " + word.toString());

          //  System.out.println(word.toString(lenSize + 1));

        }
    }


    /** tim kiem tu bang trie.*/
    public void searchWords() {
        System.out.println("Tien to can tim: ");
        scanner.nextLine();
        String prefix = scanner.nextLine();

        if(prefix.isEmpty()) return;

        int index = 0;
        ArrayList<Word> ans = dictionary.findWords(prefix);
        if(ans == null){
            System.out.println("Khong tim thay tu nao");
            return;
        }
        int len = Math.max(2, (int) Math.log10(ans.size()) + 1);
        System.out.printf("%" + len + "s", "No ");
        System.out.println("| English     | Vietnamese");
        System.out.println("-----------------------------");
        for (Word word: ans) {
            index++;
            System.out.printf("%" + len + "d ",index);

            System.out.println("| " + word.toString());

         //   System.out.println(word.toString(len + 1));

        }
    }
}
