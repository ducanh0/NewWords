package main;

import java.lang.reflect.Array;
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
        if(! this.word_explain.contains(word_explain))
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
        if(this.word_explain == null){
            this.word_explain = new ArrayList<>();
        }
        if(! this.word_explain.contains(word_explain)) {
            this.word_explain.add(word_explain);
        }
    }

    /**
     * sua y nghia
     * for trau de xoa
     * khi tim duoc oldMeaning , swap vs thang cuoi arraylist -> xoa thang cuoi arraylist
     *
     * khi bo sung y nghia -> khong phai xoa thang nao -> cho oldMeaning  = "??"
     */
    public void adjustMeaning(String oldMeaning,String newMeaning){
        for(int i  = 0;i < word_explain.size();i ++){
            if(word_explain.get(i).equals(oldMeaning)) {
                word_explain.remove(i) ;
                i -- ;
            }
        }

        word_explain.add(newMeaning);
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
}
