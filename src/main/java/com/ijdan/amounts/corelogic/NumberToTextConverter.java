package com.ijdan.amounts.corelogic;

import com.ijdan.amounts.corelogic.ManagerLangue.LanguageFactory;
import com.ijdan.amounts.corelogic.ManagerLangue.LanguageInterface;
import com.ijdan.amounts.corelogic.parametres.StructureParametre;
import com.ijdan.amounts.corelogic.ports.ParametersRetrievalPort;
import com.ijdan.amounts.corelogic.ports.NumberToTextConverterPort;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class NumberToTextConverter implements NumberToTextConverterPort {

    private static final Logger LOGGER = LoggerFactory.getLogger(NumberToTextConverter.class);
    public static final String LANGUE_PAR_DEFAUT = "FR";

    private final LanguageFactory langueFactory;
    private final ParametersRetrievalPort recuperationParametersInterface;

    public NumberToTextConverter(LanguageFactory langueFactory,
            ParametersRetrievalPort recuperationParametersInterface) {
        this.langueFactory = langueFactory;
        this.recuperationParametersInterface = recuperationParametersInterface;
    }

    @Override
    public String transformerLeNombreEnTexte(String nombre, String langue) {
        StructureParametre structureParametre = recuperationParametersInterface.recupererParametres();

        LOGGER.info("structureParametre >>" + structureParametre.getLangue());

        langue = langue != null ? langue : LANGUE_PAR_DEFAUT;
        LanguageInterface langueInterface = langueFactory.donneReglesParLangue(langue);

        return langueInterface.transformerLeNombreEnTexte(nombre);
    }

}
