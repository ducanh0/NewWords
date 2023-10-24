package main;

public class TrieNode {
    public static final int ABC_SIZE = 26;
    Word word;
    TrieNode[] trieNodes;

    int count;

    public TrieNode(Word word) {
        this.word = word;
        this.trieNodes = new TrieNode[ABC_SIZE];
    }

    public TrieNode() {
        this.trieNodes = new TrieNode[ABC_SIZE];
    }

    public int getCount() {
        return count;
    }

    public boolean isLeaf() {
        return word != null;
    }

    public Word getWord() {
        return word;
    }

    public TrieNode[] getTrieNodes() {
        return trieNodes;
    }

    public int decreaseSize() {
        return (--count);
    }

    public void increaseSize() {
        ++count;
    }
}
