package com.ijdan.amounts.corelogic.ManagerLangue;

import com.ijdan.amounts.corelogic.ManagerLangue.Langues.Anglais;
import com.ijdan.amounts.corelogic.ManagerLangue.Langues.Francais;
import org.springframework.stereotype.Component;

@Component("reglesParLangueFactory")
public class LangueFactory {

    public LangueInterface donneReglesParLangue (String langue){
        switch (langue){
            case "EN":
                return new Anglais();
            default:
                return new Francais();
        }
    }
}
