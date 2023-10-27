package main;

import java.util.ArrayList;

/**
 * quan ly 1 tu vung
 */
public class Word {
    private String word_target ; // tu vung tieng anh
    private ArrayList<String> word_explain = new ArrayList<>(); // nghia tieng viet

    /**
     * Constructor.
     */
    public Word(String word_target, String word_explain) {
        this.word_target = word_target;

        if(!(word_explain == null || word_explain.isEmpty()) && !this.word_explain.contains(word_explain))
            this.word_explain.add(word_explain);
    }

    public Word(String word_target, ArrayList<String> word_explain){
        this.word_target = word_target;
        this.word_explain = word_explain;
    }

    public Word(String word_target){
        this.word_target = word_target;
    }

    public String getWord_target() {
        return word_target;
    }

    public void setWord_target(String word_target) {
        this.word_target = word_target;
    }

    public ArrayList<String> getWord_explain() {
        return word_explain;
    }

    public void setWord_explain(String word_explain) {
        if(word_explain == null || word_explain.isEmpty()) return;

        if(this.word_explain == null){
            this.word_explain = new ArrayList<>();
        }
        if(! this.word_explain.contains(word_explain)) {
            this.word_explain.add(word_explain);
        }
    }

    /** Show a word in dictionary, use for testing. */
    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        result.append(String.format("%-12s", word_target)).
                append("| ").
                append(word_explain.get(0));
        for (int i = 1; i < word_explain.size(); ++i) {
            result.append("\n   |             | ").append(word_explain.get(i));
        }
        return  result.toString();
    }

    public String toString(int pad) {
        StringBuilder result = new StringBuilder();
        result.append(String.format("|%-12s", word_target)).
                append("| ").
                append(word_explain.get(0));
        for (int i = 1; i < word_explain.size(); ++i) {
            result.append(String.format("\n%" + pad + "s",""))
                    .append("|             | ").append(word_explain.get(i));
        }
        return  result.toString();
    }
}
