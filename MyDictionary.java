import java.util.ArrayList;

/**
 * quan ly tu dien chua nhieu vu vung
 * cai tien len dung trie de quan ly
 */
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

    /**
     * xoa tu khoi tu dien
     */

    public void removeWord(Word word){

    }

    /**
     * sua du lieu tu vung
     */

    public void fixWord(Word oldWord,Word newWord){

    }

}
