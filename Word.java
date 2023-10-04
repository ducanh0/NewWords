/**
 * quan ly 1 tu vung
 */
public class Word {
    private String word_target ; // tu vung tieng anh
    private String word_explain; // nghia tieng viet

    /**
     * Constructor.
     */
    public Word(String word_target, String word_explain) {
        this.word_target = word_target;
        this.word_explain = word_explain;
    }

    public String getWord_target() {
        return word_target;
    }

    public void setWord_target(String word_target) {
        this.word_target = word_target;
    }

    public String getWord_explain() {
        return word_explain;
    }

    public void setWord_explain(String word_explain) {
        this.word_explain = word_explain;
    }
}
