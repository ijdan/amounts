package com.ijdan.amounts.adapters.leftadapters;

public class StructureReponseAPI {
    private String nombre;
    private String response;
    private String langue;

    public StructureReponseAPI(String reponse, String langue, String nombre){
        setResponse(reponse);
        setLangue(langue);
        setNombre(nombre);
    }
    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }


    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getLangue() {
        return langue;
    }

    public void setLangue(String langue) {
        this.langue = langue;
    }
}
