package databaseDictionary;

import main.MyDictionary;
import main.Word;

import java.sql.*;
import java.util.ArrayList;
import java.util.Objects;
import java.util.stream.Collectors;

public class DatabaseManager {
    Statement statement;

    public DatabaseManager(String location) {
        try {
            Connection connection = DriverManager.getConnection("jdbc:sqlite:" + location);
            statement = connection.createStatement();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void importDictionary(MyDictionary dictionary) {
        String top100 = "select * from av";
        ResultSet rs;
        try {
            rs = statement.executeQuery(top100);
            while (rs.next()) {
                String r1 = rs.getString("word"),
                        r2 = rs.getString("description");
                if (r1.matches("^[a-zA-Z]+$")) {
                    String[] r22 = r2.split("\\\\\\\\ ");
                    Word word = new Word(r1);
                    for (String mean : r22) {
                        word.setWord_explain(mean);
                    }
                    dictionary.addWord(word);
                }
            }
            System.out.println(dictionary.getListWords().size());
        } catch (SQLException e) {
            System.out.println("Khong tim thay file");
        }
    }

    public void update(Word editWord) {
        String query = String.format("UPDATE av SET description = '%s' WHERE word = '%s'",
                String.join("\\\\ ", editWord.getWord_explain()), editWord.getWord_target());
        try {
            statement.execute(query);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    // main method, use for testing
    public void insert(Word word) {
        try {
            System.out.println(word.getWord_explain().toString());
            statement.execute(String.format("INSERT INTO av (word, description) " +
                            "values ('%s', '%s')",
                    word.getWord_target(), String.join("\\n ", word.getWord_explain())));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void delete(String wordTarget) {
        try {
            statement.execute(
                    String.format("DELETE FROM av WHERE word = '%s'", wordTarget));

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) {
        DatabaseManager db;
        db = new DatabaseManager(
                "src/main/resources/main/copy.db");
        MyDictionary dict = new MyDictionary();
        db.importDictionary(dict);
        ArrayList<Word> wal = dict.findWords("ze");
        System.out.println(wal.stream().map(Objects::toString).collect(Collectors.joining("\n")));
        Word word=new Word("zetafunction","(toán) hàm zeta");
        db.insert(word);
        db.insert(word);
        System.out.println(dict.findWords("zet"));
    }
}
