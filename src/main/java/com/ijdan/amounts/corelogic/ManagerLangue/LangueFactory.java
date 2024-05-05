package com.ijdan.amounts.corelogic.ManagerLangue;

import com.ijdan.amounts.corelogic.ManagerLangue.Langues.Anglais;
import com.ijdan.amounts.corelogic.ManagerLangue.Langues.Francais;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component("reglesParLangueFactory")
public class LangueFactory {

    private static final Map<String, LangueInterface> LANGUE_MAP = new HashMap<>();

    static {
        LANGUE_MAP.put("EN", new Anglais());
        LANGUE_MAP.put("FR", new Francais());
    }

    public LangueInterface donneReglesParLangue (String langue){
        return LANGUE_MAP.getOrDefault(langue, new Francais());
    }
}