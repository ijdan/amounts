package com.ijdan.amounts.corelogic.parametres;

public class StructureParametre {
    String langue;
    Boolean serviceActif;

    public StructureParametre(String langue, Boolean serviceActif) {
        this.langue = langue;
        this.serviceActif = serviceActif;
    }

    public Boolean getServiceActif() {
        return serviceActif;
    }

    public void setServiceActif(Boolean serviceActif) {
        this.serviceActif = serviceActif;
    }


    public String getLangue() {
        return langue;
    }

    public void setLangue(String langue) {
        this.langue = langue;
    }
}
