package main;

import java.util.ArrayList;

/**
 * quan ly tu dien chua nhieu vu vung
 * cai tien len dung trie de quan ly
 */
public class MyDictionary {
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
        if(word == null || word.getWord_target() == null
                || word.getWord_explain() == null || word.getWord_target().isEmpty()) return;

        trie.addWord(word);
    }

    /**
     * xoa tu khoi tu dien
     */

    public void removeWord(Word word) {
        if(word == null || word.getWord_target() == null
           || word.getWord_target().isEmpty()) return;

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
        if(search == null || search.isEmpty()) return null;

        return trie.findWord(search);
    }

    public ArrayList<Word> findWords(String prefix) {
        if(prefix == null || prefix.isEmpty()) return null;

        return trie.getByPrefix(prefix);
    }
}
