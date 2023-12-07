package com.example.newwords.supportAPI;

import databaseDictionary.DatabaseManager;
import main.Main;
import main.MyDictionary;
import main.Word;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CoreGUIDictionaryManager {
    MyDictionary dictionary;
    DatabaseManager dbm;
    ExecutorService service;
    public CoreGUIDictionaryManager()
    {
        dictionary = new MyDictionary();
        dbm = new DatabaseManager(Main.class.getResource("copy.db").toString());
        dbm.importDictionary(dictionary);
        service = Executors.newSingleThreadExecutor();
    }
    public void addWord(String target, List<String> explains){
        Word word = new Word(target,new ArrayList<>(explains));
        dictionary.addWord(word);
        service.submit(() -> dbm.insert(word));
    }

    public void fixTarget(String oldTarget, String newTarget) {
        Word findWord=dictionary.findWord(oldTarget);
        Word newWord = new Word(newTarget, findWord.getWord_explain());
        dictionary.fixWord(findWord, newWord);
        service.submit(() -> {
            dbm.delete(oldTarget);
            dbm.insert(newWord);
        });
    }

    public void fixExplanations(String target, List<String> meaning) {
        Word findWord=dictionary.findWord(target);
        Word newWord = new Word(target);
        for (String s : meaning)
            newWord.setWord_explain(s);
        dictionary.fixWord(findWord, newWord);
        service.submit(() -> dbm.update(newWord));
    }

    public Word search(String target) {
        return dictionary.findWord(target);
    }

    public void delete(String target) {
        dictionary.removeWord(new Word(target));
        service.submit(() -> dbm.delete(target));
    }

    public ArrayList<Word> searchPrefix(String prefix) {
        ArrayList<Word> words = dictionary.findWords(prefix);
        return (words == null ? new ArrayList<>() : words);
    }
}
