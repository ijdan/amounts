package com.ijdan.amounts.corelogic.ManagerLangue;

import java.util.Map;

public class LanguageFactory {

    private final Map<String, LanguageInterface> langueMap;

    public LanguageFactory(Map<String, LanguageInterface> langueMap) {
        this.langueMap = langueMap;
    }

    public LanguageInterface donneReglesParLangue(String langue) {
        if (!langueMap.containsKey(langue)) {
            throw new IllegalArgumentException("Language '" + langue + "' is not yet integrated.");
        }
        return langueMap.get(langue);
    }
}