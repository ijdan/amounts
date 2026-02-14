package com.ijdan.amounts.corelogic.ManagerLangue.Langues;

import com.ijdan.amounts.corelogic.ManagerLangue.LangueInterface;
import com.ijdan.amounts.corelogic.ManagerLangue.ReglesCommunes;

import java.util.ArrayList;

public abstract class AbstractLangue implements LangueInterface {

    protected final ReglesCommunes reglesCommunes;

    protected AbstractLangue(ReglesCommunes reglesCommunes) {
        this.reglesCommunes = reglesCommunes;
    }

    @Override
    public String transformerLeNombreEnTexte(String nombre) {
        String nombrePrepare = reglesCommunes.preparerLeNombre(nombre);
        ArrayList<String> leNombreParPaquetsDeTrois = reglesCommunes.subdiviserLeNombreParMillier(nombrePrepare);
        ArrayList<String> partiesATransformer = identifierToutesLesPartiesATransformer(nombrePrepare,
                leNombreParPaquetsDeTrois);
        ArrayList<String> texteAssocieAChaquePartie = transformerChaquePartie(partiesATransformer);

        return alignerLesPartiesTransformeesEnUneSeulePhrase(texteAssocieAChaquePartie);
    }

    protected ArrayList<String> transformerChaquePartie(ArrayList<String> splitedNumber) {
        ArrayList<String> subdividedNumberOnText = new ArrayList<>();
        for (int i = 0; i < splitedNumber.size(); i++) {
            transformerEnTexteLesComposantesPrononcables(splitedNumber, subdividedNumberOnText, i);
        }
        return subdividedNumberOnText;
    }

    protected abstract void transformerEnTexteLesComposantesPrononcables(ArrayList<String> splitedNumber,
            ArrayList<String> subdividedNumberOnText, int digitPosition);

    protected ArrayList<String> identifierToutesLesPartiesATransformer(String nombre,
            ArrayList<String> partiesComposantLeNombreSubdiviseParMillier) {
        ArrayList<String> resultatSubdivisionDuNombre = new ArrayList<>();
        int nombreDePartiesATransformerEnTexte = partiesComposantLeNombreSubdiviseParMillier.size();

        if (estUnChiffreElementaire(nombre)) {
            traiterChiffreElementaireGlobal(resultatSubdivisionDuNombre, nombre);
        } else {
            for (int i = 0; i < nombreDePartiesATransformerEnTexte; i++) {
                String unePartie = partiesComposantLeNombreSubdiviseParMillier.get(i);
                unePartie = reglesCommunes.supprimerLesZerosSuperflux(unePartie);

                if (reglesCommunes.siPartieNonVide(unePartie)) {
                    traiterPartieNonVide(resultatSubdivisionDuNombre, unePartie, nombreDePartiesATransformerEnTexte, i);
                }
            }
        }
        return resultatSubdivisionDuNombre;
    }

    // Hooks for specific behaviors (like "One" before "Hundred" in English/Spanish
    // vs French)
    protected void traiterChiffreElementaireGlobal(ArrayList<String> resultat, String nombre) {
        resultat.add(nombre);
    }

    protected void traiterPartieNonVide(ArrayList<String> resultat, String unePartie, int totalParties, int index) {
        ajouterPartieAuxComposantesDuNombre(resultat, unePartie);
        siGrandNombreDetecteLeRajouterParmiLesComposantesDuNombre(resultat, totalParties, index);
    }

    protected void siGrandNombreDetecteLeRajouterParmiLesComposantesDuNombre(
            ArrayList<String> resultatSubdivisionDuNombre, int nombreDePartiesATransformerEnTexte, int i) {
        String grandNombreAssocie = reglesCommunes.recupererGrandNombreAssocie(nombreDePartiesATransformerEnTexte, i);
        if (unePuissanceCorrespondanteTrouvee(grandNombreAssocie)) {
            ajouterPartieAuxComposantesDuNombre(resultatSubdivisionDuNombre, grandNombreAssocie);
        }
    }

    protected boolean unePuissanceCorrespondanteTrouvee(String puissanceCorrespondanteALaPartie) {
        return puissanceCorrespondanteALaPartie != null;
    }

    protected void ajouterPartieAuxComposantesDuNombre(ArrayList<String> resultatSubdivisionDuNombre, String onePart) {
        if (estUnChiffreElementaire(onePart)) {
            ajouterAuxPartiesComposantLeNombreADecomposer(resultatSubdivisionDuNombre, onePart);
        } else {
            addDecomposedDigitPartToSubdividedNumber(resultatSubdivisionDuNombre, onePart);
        }
    }

    protected void addDecomposedDigitPartToSubdividedNumber(ArrayList<String> resultatSubdivisionDuNombre,
            String onePart) {
        if (onePart.length() == 3) {
            decomposerUnePartieDeLongueurTrois(resultatSubdivisionDuNombre, onePart);
        } else {
            decomposerUnePartieDeLongueurDeux(resultatSubdivisionDuNombre, onePart);
        }
    }

    protected void decomposerUnePartieDeLongueurDeux(ArrayList<String> resultatSubdivisionDuNombre, String onePart) {
        // Default implementation logic from old classes
        ajouterAuxPartiesComposantLeNombreADecomposer(resultatSubdivisionDuNombre, onePart.substring(0, 1).concat("0"));
        ajouterAuxPartiesComposantLeNombreADecomposer(resultatSubdivisionDuNombre, onePart.substring(1, 2));
    }

    protected void decomposerUnePartieDeLongueurTrois(ArrayList<String> resultatSubdivisionDuNombre, String onePart) {
        // Default implementation logic
        if (!onePart.startsWith("1")) { // Was CHIFFRE_UN static const
            ajouterAuxPartiesComposantLeNombreADecomposer(resultatSubdivisionDuNombre, onePart.substring(0, 1));
        }

        ajouterAuxPartiesComposantLeNombreADecomposer(resultatSubdivisionDuNombre, "100"); // Was CHIFFRE_CENT

        if (estUnChiffreElementaire(reglesCommunes.supprimerLesZerosSuperflux(onePart.substring(1, 3)))) {
            ajouterAuxPartiesComposantLeNombreADecomposer(resultatSubdivisionDuNombre,
                    reglesCommunes.supprimerLesZerosSuperflux(onePart.substring(1, 3)));
        } else {
            ajouterAuxPartiesComposantLeNombreADecomposer(resultatSubdivisionDuNombre,
                    onePart.substring(1, 2).concat("0"));
            ajouterAuxPartiesComposantLeNombreADecomposer(resultatSubdivisionDuNombre, onePart.substring(2, 3));
        }
    }

    protected void ajouterAuxPartiesComposantLeNombreADecomposer(ArrayList<String> resultatSubdivisionDuNombre,
            String onePart) {
        if (reglesCommunes.siPartieNonVide(onePart)) {
            resultatSubdivisionDuNombre.add(onePart);
        }
    }

    protected abstract boolean estUnChiffreElementaire(String number);

    protected abstract String alignerLesPartiesTransformeesEnUneSeulePhrase(ArrayList<String> subdividedNumberOnText);
}
