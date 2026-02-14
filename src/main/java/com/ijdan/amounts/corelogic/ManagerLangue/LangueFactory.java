package com.ijdan.amounts.corelogic.ManagerLangue;

import org.springframework.stereotype.Component;

import java.util.Map;

@Component("reglesParLangueFactory")
public class LangueFactory {

    private final Map<String, LangueInterface> langueMap;

    public LangueFactory(Map<String, LangueInterface> langueMap) {
        this.langueMap = langueMap;
    }

    public LangueInterface donneReglesParLangue(String langue) {
        return langueMap.getOrDefault(langue, langueMap.get("FR"));
    }
}