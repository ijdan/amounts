package com.ijdan.amounts.corelogic;

import org.springframework.http.ResponseEntity;

public interface TransformationNombreEnTexteInterface {
    String transformerLeNombreEnTexte(String nombre, String langue);
}
