package com.ijdan.amounts.corelogic.ports;

import com.ijdan.amounts.corelogic.parametres.StructureParametre;
import org.springframework.stereotype.Component;

@Component
public interface RecuperationParametersInterface {
    StructureParametre recupererParametres();
}
