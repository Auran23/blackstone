package com.example.adilbekmailanov.speechanalysis;

/**
 * Created by adilbekmailanov on 28.01.18.
 */

public class ResultModel {
    private String newText;
    private AlternativeWordModel[] alternativeWordModels;

    public ResultModel(String newText, AlternativeWordModel[] alternativeWordModels) {
        this.newText = newText;
        this.alternativeWordModels = alternativeWordModels;
    }

    public String getNewText () {
        return newText;
    }

    public AlternativeWordModel[] getAlternativeWordModels() {
        return alternativeWordModels;
    }

}
