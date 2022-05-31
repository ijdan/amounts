package com.ijdan.amounts.adapters.rightadapters;

import com.ijdan.amounts.corelogic.parametres.StructureParametre;
import com.ijdan.amounts.corelogic.ports.RecuperationParametersInterface;
import org.springframework.stereotype.Component;

@Component
public class RecuperationParametresDepuisLaBase implements RecuperationParametersInterface {
    @Override
    public StructureParametre recupererParametres() {
        return new StructureParametre("FR", true);
    }
}
