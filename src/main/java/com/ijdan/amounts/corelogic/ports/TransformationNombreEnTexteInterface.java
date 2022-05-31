package com.ijdan.amounts.corelogic.ports;

import org.springframework.stereotype.Component;

@Component
public interface TransformationNombreEnTexteInterface {
    String transformerLeNombreEnTexte(String nombre, String langue);
}
