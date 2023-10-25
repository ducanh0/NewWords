package main;

import java.util.ArrayList;

public class Trie {
    private TrieNode root;

    private ArrayList<Word> convert;
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
        for (int i = 0; i < last; ++i)
        {
            int c = string.charAt(i)  - 'a';
            if (current.trieNodes[c] == null)
                current.trieNodes[c] = new TrieNode();
            current = current.trieNodes[c];
            current.increaseSize();
        }

        TrieNode leaf = new TrieNode(word);
        current.trieNodes[string.charAt(last) - 'a'] = leaf;
        leaf.increaseSize();
    }

    public ArrayList<Word> getByPrefix(String prefix) {
        if(prefix == null || prefix.isEmpty()) return null;

        TrieNode current = root;
        convert = new ArrayList<>();

        for (int i = 0; i < prefix.length(); ++i){
            if(current.trieNodes[prefix.charAt(i) - 'a'] == null){
                return null;
            }
            current = current.trieNodes[prefix.charAt(i) - 'a'];
        }
        recursiveGetAll(current);
        return convert;
    }

    public void deleteWord(Word word) {
        if(word == null || word.getWord_target() == null
                || word.getWord_target().isEmpty() || word.getWord_explain() == null) return;

        TrieNode current = root;
        String string = word.getWord_target().toLowerCase();
        TrieNode[] trace = new TrieNode[string.length()];
        for (int i = 0; i < string.length(); ++i) {
            if (current == null || current.trieNodes[string.charAt(i) - 'a'] == null)
                return;

            current = current.trieNodes[string.charAt(i) - 'a'];
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
                break;
            }

            current = current.trieNodes[nhanh];
        }
    }

    public Word findWord(String search) {
        if(search == null || search.isEmpty()) return null;

        TrieNode current = root;
        for(int i = 0; i < search.length() && current != null; ++i){
            if(current.trieNodes[search.charAt(i) - 'a'] == null) {
                current = null;
                break;
            }
            current = current.trieNodes[search.charAt(i) - 'a'];
        }

        return current == null ? null: current.getWord();
    }

    /** main for testing. */
    public static void main(String[] args) {
        Word w1 = new Word("a", "một");
        Word w2 = new Word("at", "tại");
        w1.setWord_explain("chữ A");
        Trie trie = new Trie();
        trie.addWord(w1);
        trie.addWord(w2);
        trie.addWord(new Word("be", "là"));
        trie.deleteWord(w2);
        System.out.println(w1);
        System.out.println(trie.getByPrefix("a"));
        System.out.println(trie.findWord("be"));
    }
}
