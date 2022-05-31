package com.ijdan.amounts.corelogic;

import com.ijdan.amounts.corelogic.ManagerLangue.LangueFactory;
import com.ijdan.amounts.corelogic.ManagerLangue.LangueInterface;
import com.ijdan.amounts.corelogic.parametres.StructureParametre;
import com.ijdan.amounts.corelogic.ports.RecuperationParametersInterface;
import com.ijdan.amounts.corelogic.ports.TransformationNombreEnTexteInterface;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TransformationNombreEnTexte implements TransformationNombreEnTexteInterface {

    private static final Logger LOGGER = LoggerFactory.getLogger(TransformationNombreEnTexte.class);
    public static final String LANGUE_PAR_DEFAUT = "FR";

    @Autowired
    LangueFactory langueFactory;

    @Autowired
    RecuperationParametersInterface recuperationParametersInterface;

    @Override
    public String transformerLeNombreEnTexte(String nombre, String langue) {
        StructureParametre structureParametre = recuperationParametersInterface.recupererParametres();

        LOGGER.info("structureParametre >>" + structureParametre.getLangue());

        langue = langue != null ? langue : LANGUE_PAR_DEFAUT;
        LangueInterface langueInterface = langueFactory.donneReglesParLangue(langue);

        return langueInterface.transformerLeNombreEnTexte(nombre);
    }

}
