package main;

import java.util.ArrayList;

/**
 * quan ly tu dien chua nhieu vu vung
 * cai tien len dung trie de quan ly
 */
public class MyDictionary {

    //main.Word [] arr ;

    class Node{
        private ArrayList<String> meaning ; // nghia cua tu (neu ket thuc tai node nay)
        private Node [] nxt ; // mang con tro den cac node con

        private int cnt ; // so tu di qua no

        private char link ; // ki tu ma tu node cha di den no

        Node(char link){
            this.link = link;
        }


    }

    Node root ; // nut goc
    private ArrayList<Word> listWords;
    private Trie trie;

    /**
     * Constructor.
     */
    public MyDictionary() {
        listWords = new ArrayList<>();
        trie = new Trie();
    }

    public ArrayList<Word> getListWords() {
        return trie.convertToArrayList();
    }

    /**
     * them tu moi vao tu dien
     */
    public void addWord(Word word) {
        //listWords.add(word);
        trie.addWord(word);
    }

    /**
     * xoa tu khoi tu dien
     */

    public void removeWord(Word word) {
        trie.deleteWord(word);
    }

    /**
     * sua du lieu tu vung
     */

    public void fixWord(Word oldWord, Word newWord) {
        trie.deleteWord(oldWord);
        trie.addWord(newWord);
    }

    public Word findWord(String search) {
        return trie.findWord(search);
    }

    public ArrayList<Word> findWords(String prefix) {
        return trie.getByPrefix(prefix);
    }
}
