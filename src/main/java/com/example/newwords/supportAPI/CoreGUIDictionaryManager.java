package com.example.newwords.supportAPI;

import databaseDictionary.DatabaseManager;
import main.Main;
import main.MyDictionary;
import main.Word;

import java.util.ArrayList;

public class CoreGUIDictionaryManager {
    MyDictionary dictionary;
    DatabaseManager dbm;
    public CoreGUIDictionaryManager()
    {
        dictionary = new MyDictionary();
        dbm = new DatabaseManager(Main.class.getResource("copy.db").toString());
        dbm.importDictionary(dictionary);
    }
    public void addWord(String target, ArrayList<String> explains){
        Word word = new Word(target,explains);
        dictionary.addWord(word);
    }

    public void fixTarget(String oldTarget, String newTarget) {
        Word findWord=dictionary.findWord(oldTarget);
        Word newWord = new Word(newTarget, findWord.getWord_explain());
        dictionary.fixWord(findWord, newWord);
        dbm.delete(oldTarget);
        dbm.insert(newWord);
    }

    public void fixExplanations(String target, ArrayList<String> meaning,
                                ArrayList<Boolean> check) {
        Word findWord=dictionary.findWord(target);
        Word newWord = new Word(target);
        for (int i = 0; i < meaning.size();++i)
            if (check.get(i)) newWord.setWord_explain(meaning.get(i));
        dictionary.fixWord(findWord, newWord);
        dbm.update(newWord);
    }

    public Word search(String target) {
        return dictionary.findWord(target);
    }

    public void delete(String target) {
        dictionary.removeWord(new Word(target));
        dbm.delete(target);
    }

    public ArrayList<Word> searchPrefix(String prefix) {
        ArrayList<Word> words = dictionary.findWords(prefix);
        return (words == null ? new ArrayList<>() : words);
    }
}
