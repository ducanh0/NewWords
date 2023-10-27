package main;

import java.util.ArrayList;

public class Trie {
    private TrieNode root;

    private ArrayList<Word> convert = new ArrayList<>();
    public Trie() {
        root = new TrieNode();
    }

    public ArrayList<Word> convertToArrayList() {
        convert = new ArrayList<>();

        recursiveGetAll(root);
        return convert;
    }

    private void recursiveGetAll(TrieNode u) {
        if (u == null) return;
        if (u.isLeaf()) convert.add(u.getWord());

        for (TrieNode tN: u.getTrieNodes())
            recursiveGetAll(tN);
    }

    public void addWord(Word word) {
        if(word == null || word.getWord_target() == null
                || word.getWord_target().isEmpty() || word.getWord_explain() == null) return;

        String string = word.getWord_target().toLowerCase();
        TrieNode current = root;
        int last = string.length() - 1;

        for (int i = 0; i <= last; ++i) {
            int c = string.charAt(i)  - 'a';

            if (current.trieNodes[c] == null){
                current.trieNodes[c] = new TrieNode();
            }
            if(i == last) {
                current.trieNodes[c].setWord(word);
            }

            current = current.trieNodes[c];
            current.increaseSize();
        }
    }

    public ArrayList<Word> getByPrefix(String prefix) {
        if(prefix == null || prefix.isEmpty()) return null;

        TrieNode current = root;
        convert = new ArrayList<>();

        for (int i = 0; i < prefix.length(); ++i){
            int nhanh = prefix.charAt(i) - 'a';

            if(current.trieNodes[nhanh] == null){
                return null;
            }

            current = current.trieNodes[nhanh];
        }

        recursiveGetAll(current);
        return convert;
    }

    public void deleteWord(Word word) {
        if(word == null || word.getWord_target() == null
                || word.getWord_target().isEmpty()) return;

        TrieNode current = root;

        String string = word.getWord_target().toLowerCase();
        TrieNode[] trace = new TrieNode[string.length()];

        for (int i = 0; i < string.length(); ++i) {
            int nhanh = string.charAt(i) - 'a';

            if (current.trieNodes[nhanh] == null)
                return;

            current = current.trieNodes[nhanh];
            trace[i] = current;
        }

        if(! current.isLeaf()){
            return;
        }

        current = root;
        for(int i = 0;i < string.length();i ++){
            int tmp = trace[i].decreaseSize();
            int nhanh = (string.charAt(i) - 'a');

            if(tmp == 0){
                current.trieNodes[nhanh] = null;
                return;
            }

            current = current.trieNodes[nhanh];
        }

        current.setWord(null);
    }

    public Word findWord(String search) {
        if(search == null || search.isEmpty()) return null;

        TrieNode current = root;
        for(int i = 0; i < search.length(); ++i){
            int nhanh = search.charAt(i) - 'a';

            if(current.trieNodes[nhanh] == null) {
                current = null;
                break;
            }

            current = current.trieNodes[nhanh];
        }

        return current == null ? null: current.getWord();
    }

    /** main for testing. */
    public static void main(String[] args) {

    }
}
