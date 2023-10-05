import java.util.Scanner;

public class DictionaryCommandLine {
    DictionaryManagement dictionaryManagement = new DictionaryManagement();
    Scanner scanner = new Scanner(System.in);

    /**
     * co the goi duoc insertFromCommandline() va showAllWords()
     */
    public void dictionaryBasic() {

    }

    /**
     * tim kiem tu , dung trie ??
     * co the thay doi kieu tra ve trong qua trinh code cho phu hop
     */

    public void dictionarySearcher(){

    }

    /**
     * chay ung dung theo yeu cau
     * [0] Exit
     * [1] Add
     * [2] Remove
     * [3] Update
     * [4] Display
     * [5] Lookup
     * [6] Search
     * [7] Game
     * [8] Import from file
     * [9] Export to file
     *
     * dung switch case de xu ly yeu cau , neu yeu cau khong hop le in ra “Action not supported”
     */

    public void dictionaryAdvanced() {
        dictionaryManagement.insertFromFile();
        System.out.println("Welcome to My Application!");
        System.out.println("[0] Exit");
        System.out.println("[1] Add");
        System.out.println("[2] Remove");
        System.out.println("[3] Update");
        System.out.println("[4] Display");
        System.out.println("[5] Lookup");
        System.out.println("[6] Search");
        System.out.println("[7] Game");
        System.out.println("[8] Import from file");
        System.out.println("[9] Export to file");
        System.out.println("Your action: ");
        int act = scanner.nextInt();
        if (act < 0 || act > 9) {
            System.out.println("Action not supported");
        }
        switch (act) {
            case 0:

                break;
            case 1:
                dictionaryManagement.addNewWordsFromCmd();
                break;
            case 2:
                dictionaryManagement.removeWordsFromCmd();
                break;
            case 3:
                dictionaryManagement.adjustWordsFromCmd();
                break;
            case 4:
                dictionaryManagement.showAllWords();
                break;
            case 5:
                dictionaryManagement.dictionaryLookup();
                break;
            case 6:
                dictionarySearcher();
                break;
            case 7:
                // run game
                break;
            case 8:
                dictionaryManagement.insertFromFile();
                break;
            case 9:
                dictionaryManagement.dictionaryExportToFile();
                break;
        }
    }
}
