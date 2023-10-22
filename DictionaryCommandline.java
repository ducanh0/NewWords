import java.util.Scanner;

public class DictionaryCommandline {
    Scanner scanner = new Scanner(System.in);
    DictionaryManagement dictionaryManagement = new DictionaryManagement(scanner);

    /**
     * in ra man hinh commandline theo format
     *
     * No  |  English     | Vietnamese
     * 1   |  Hello       | Xin chao
     * 2   | Bye          | Tam biet
     * ...
     */
    public void showAllWords(){
        dictionaryManagement.showAllWords();
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
        //dictionaryManagement.insertFromFile("dictionaries.txt");
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
        System.out.print("Your action: ");

        while (scanner.hasNext()) {
            int act = scanner.nextInt();

            switch (act) {
                case 0:
                    System.exit(0);
                    break;
                case 1:
                    dictionaryManagement.addWord();
                    break;
                case 2:
                    dictionaryManagement.removeWord();
                    break;
                case 3:
                    dictionaryManagement.adjustWord();
                    break;
                case 4:
                    dictionaryManagement.showAllWords();
                    break;
                case 5:
                    dictionaryManagement.dictionaryLookup();
                    break;
                case 6:
                    dictionaryManagement.searchWords();
                    break;
                case 7:
                    // run game
                    break;
                case 8:
                    dictionaryManagement.insertFromFile("dictionaries.txt", ",");
                    break;
                case 9:
                    dictionaryManagement.dictionaryExportToFile();
                    break;
                default:
                    System.out.println("Action not supported");
                    break;
            }
            System.out.print("Your action:");
        }
    }

    public static void main(String[] args) {
        DictionaryCommandline commandline = new DictionaryCommandline();
        commandline.dictionaryAdvanced();
    }
}
