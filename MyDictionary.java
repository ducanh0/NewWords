import java.util.ArrayList;

public class MyDictionary {

    //Word [] arr ;
    private ArrayList<Word> listWords;

    /**
     * Constructor.
     */
    public MyDictionary() {
        listWords = new ArrayList<>();
    }

    public ArrayList<Word> getListWords() {
        return listWords;
    }

    /**
     * them tu moi vao tu dien
     */
    public void addWord(Word word) {
        listWords.add(word);
    }


}
