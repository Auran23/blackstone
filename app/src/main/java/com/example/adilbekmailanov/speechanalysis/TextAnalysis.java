package com.example.adilbekmailanov.speechanalysis;

import java.util.ArrayList;

public class TextAnalysis {

    private ArrayList<WordModel> wordModelList;
    private DataBaseHelper dataBaseHelper;

    public TextAnalysis (DataBaseHelper dataBaseHelper) {
        this.dataBaseHelper = dataBaseHelper;
        this.wordModelList = dataBaseHelper.loadAllWords();
    }

    public String updateText(String text) {
        String[] wordList = text.split(" ");
        ArrayList<String> replacedWords = replaceWords(wordList);
        replacedWords = removeRepeatingWords(replacedWords);
        return wordsListToWord(replacedWords);
    }

    public ArrayList<String> replaceWords(String[] wordList) {
        ArrayList<String> newList = new ArrayList<>();
        for (String word : wordList) {
            WordModel result = dataBaseHelper.getWordModel(word);
            if (result != null) {
                newList.add(result.getWord());
            } else {
                newList.add(word);
            }
        }
        return newList;
    }

    public static ArrayList<String> removeRepeatingWords(ArrayList<String> wordList) {
        int size = wordList.size();
        for (int index = 0; index < size-1; index++) {
            if (wordList.get(index).equals(wordList.get(index+1))) {
                wordList.remove(index+1);
                size--;
            }
        }
        return wordList;
    }

    public static String wordsListToWord(ArrayList<String> wordList) {
        StringBuilder stringBuilder = new StringBuilder();
        for (int index=0; index<wordList.size()-1; index++) {
            stringBuilder.append(wordList.get(index)+" ");
        }
        stringBuilder.append(wordList.get(wordList.size()-1));
        return stringBuilder.toString();
    }

    public int toCompare(String st1, String st2) {
        for (int index = 0; index < Math.min(st1.length(), st2.length()); index++) {
            int result = st1.charAt(index) - st2.charAt(index);
            if (result == 0) continue;
            else if (result < 0) return -1;
            else if (result > 0) return 1;
        }
        return 0;
    }
}
