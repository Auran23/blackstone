package com.example.adilbekmailanov.speechanalysis;

/**
 * Created by adilbekmailanov on 28.01.18.
 */

public class WordModel {
    private String word;
    private String alternative;

    public WordModel(String word, String alternative) {
        this.word = word;
        this.alternative = alternative;
    }

    public String getAlternative() {
        return alternative;
    }

    public String getWord() {
        return word;
    }
}
