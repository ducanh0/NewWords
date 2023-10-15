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
        //if (current.trieNodes[string.charAt(last) - 'a'] == null)
        TrieNode leaf = new TrieNode(word);
        current.trieNodes[string.charAt(last) - 'a'] = leaf;
        leaf.increaseSize();

    }

    public ArrayList<Word> getByPrefix(String prefix) {
        TrieNode current = root;
        convert = new ArrayList<>();
        for (int i = 0; i < prefix.length(); ++i)
            current = current.trieNodes[prefix.charAt(i) - 'a'];
        recursiveGetAll(current);
        return convert;
    }

    public void deleteWord(Word word) {
        TrieNode current = root;
        String string = word.getWord_target().toLowerCase();
        TrieNode[] trace = new TrieNode[string.length()];
        for (int i = 0; i < string.length(); ++i) {
            if (current == null) return;
            current = current.trieNodes[string.charAt(i) - 'a'];
            trace[i] = current;
        }
        for (int i = string.length() - 1; i >= 0; --i) {
            int tmp = trace[i].decreaseSize();
            if (tmp == 0) {
                trace[i].word = null;
            }
        }
    }

    public Word findWord(String search) {
        TrieNode current = root;
        for(int i = 0; i < search.length() && current != null; ++i)
            current = current.trieNodes[search.charAt(i) - 'a'];
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
