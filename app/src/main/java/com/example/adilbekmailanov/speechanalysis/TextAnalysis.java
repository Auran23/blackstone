package com.example.adilbekmailanov.speechanalysis;

import java.util.ArrayList;

public class TextAnalysis {

    WordModel[] wordModelList;

    public TextAnalysis (WordModel[] wordModelList) {
        this.wordModelList = wordModelList;
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
            int result = binarySearch(word);
            if (result != -1) {
                newList.add(wordModelList[result].getWord());
            } else {
                newList.add(word);
            }
        }
        return newList;
    }

    public int binarySearch(String key) {
        int low = 0;
        int high = wordModelList.length - 1;
        while(high >= low) {
            int middle = (low + high) / 2;
            int result = toCompare(wordModelList[middle].getWord(), key);
            switch (result) {
                case 0:
                    return middle;
                case -1:
                    low = middle + 1;
                    break;
                case 1:
                    high = middle - 1;
                    break;
            }
        }
        return -1;
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
