package com.ijdan.amounts.configuration;

import com.ijdan.amounts.corelogic.ManagerLangue.LanguageFactory;
import com.ijdan.amounts.corelogic.ManagerLangue.LanguageInterface;
import com.ijdan.amounts.corelogic.ManagerLangue.CommonRules;
import com.ijdan.amounts.corelogic.ManagerLangue.Langues.EnglishLanguage;
import com.ijdan.amounts.corelogic.ManagerLangue.Langues.SpanishLanguage;
import com.ijdan.amounts.corelogic.ManagerLangue.Langues.FrenchLanguage;
import com.ijdan.amounts.corelogic.ManagerLangue.Langues.ItalianLanguage;
import com.ijdan.amounts.corelogic.ManagerLangue.Langues.DutchLanguage;
import com.ijdan.amounts.corelogic.NumberToTextConverter;
import com.ijdan.amounts.corelogic.ports.ParametersRetrievalPort;
import com.ijdan.amounts.corelogic.ports.NumberToTextConverterPort;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class DomainConfiguration {

    @Bean
    public CommonRules commonRules() {
        return new CommonRules();
    }

    @Bean("FR")
    public LanguageInterface francais(CommonRules commonRules) {
        return new FrenchLanguage(commonRules);
    }

    @Bean("EN")
    public LanguageInterface anglais(CommonRules commonRules) {
        return new EnglishLanguage(commonRules);
    }

    @Bean("ES")
    public LanguageInterface espagnol(CommonRules commonRules) {
        return new SpanishLanguage(commonRules);
    }

    @Bean("IT")
    public LanguageInterface italiano(CommonRules commonRules) {
        return new ItalianLanguage(commonRules);
    }

    @Bean("NL")
    public LanguageInterface dutch(CommonRules commonRules) {
        return new DutchLanguage(commonRules);
    }

    @Bean("reglesParLangueFactory")
    public LanguageFactory langueFactory(@Qualifier("FR") LanguageInterface francais,
            @Qualifier("EN") LanguageInterface anglais,
            @Qualifier("ES") LanguageInterface espagnol,
            @Qualifier("IT") LanguageInterface italiano,
            @Qualifier("NL") LanguageInterface dutch) {
        Map<String, LanguageInterface> langueMap = new HashMap<>();
        langueMap.put("FR", francais);
        langueMap.put("EN", anglais);
        langueMap.put("ES", espagnol);
        langueMap.put("IT", italiano);
        langueMap.put("NL", dutch);
        return new LanguageFactory(langueMap);
    }

    @Bean
    public NumberToTextConverterPort transformationNombreEnTexteInterface(
            LanguageFactory langueFactory,
            ParametersRetrievalPort recuperationParametersInterface) {
        return new NumberToTextConverter(langueFactory, recuperationParametersInterface);
    }
}
