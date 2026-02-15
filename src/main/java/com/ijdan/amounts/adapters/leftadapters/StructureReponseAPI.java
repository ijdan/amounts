package com.ijdan.amounts.adapters.leftadapters;

public class StructureReponseAPI {
    private String inputNumber;
    private String response;
    private String language;

    public StructureReponseAPI(String reponse, String language, String inputNumber) {
        setResponse(reponse);
        setLanguage(language);
        setInputNumber(inputNumber);
    }

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }

    public String getInputNumber() {
        return inputNumber;
    }

    public void setInputNumber(String inputNumber) {
        this.inputNumber = inputNumber;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }
}
