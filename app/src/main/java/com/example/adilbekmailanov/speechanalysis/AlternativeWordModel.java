package com.example.adilbekmailanov.speechanalysis;

/**
 * Created by adilbekmailanov on 28.01.18.
 */

public class AlternativeWordModel {
    private String parasiteWord;
    private String alternativeWord;

    public AlternativeWordModel(String parasiteWord, String alternativeWord) {
        this.alternativeWord = alternativeWord;
        this.parasiteWord = parasiteWord;
    }

    public String getParasiteWord () {
        return parasiteWord;
    }

    public String getAlternativeWord() {
        return alternativeWord;
    }
}
