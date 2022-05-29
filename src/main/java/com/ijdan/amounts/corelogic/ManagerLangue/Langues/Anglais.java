package com.ijdan.amounts.corelogic.ManagerLangue.Langues;

import com.ijdan.amounts.corelogic.ManagerLangue.LangueInterface;
import com.ijdan.amounts.corelogic.ManagerLangue.ReglesCommunes;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Anglais implements LangueInterface {

    public static final String CHIFFRE_UN   = "1";
    public static final String CHIFFRE_CENT = "100";


    public static final int THREE_DIGITS    = 3;
    public static final int TWO_DIGITS      = 2;
    public static final int ONE_DIGIT       = 1;

    public static final String COORDINATEUR_AND = "AND";

    public static final String SEPARATEUR_MOTS      = " ";
    public static final String SEPARATEUR_DIZAINE   = "-";

    private static final Map<String, String> SIMPLE_NOMBRES_ELEMENTAIRES;
    static {
        SIMPLE_NOMBRES_ELEMENTAIRES = new HashMap<>();
        SIMPLE_NOMBRES_ELEMENTAIRES.put("0", "Zero");
        SIMPLE_NOMBRES_ELEMENTAIRES.put("1", "One");
        SIMPLE_NOMBRES_ELEMENTAIRES.put("2", "Two");
        SIMPLE_NOMBRES_ELEMENTAIRES.put("3", "Three");
        SIMPLE_NOMBRES_ELEMENTAIRES.put("4", "Four");
        SIMPLE_NOMBRES_ELEMENTAIRES.put("5", "Five");
        SIMPLE_NOMBRES_ELEMENTAIRES.put("6", "Six");
        SIMPLE_NOMBRES_ELEMENTAIRES.put("7", "Seven");
        SIMPLE_NOMBRES_ELEMENTAIRES.put("8", "Eight");
        SIMPLE_NOMBRES_ELEMENTAIRES.put("9", "Nine");
        SIMPLE_NOMBRES_ELEMENTAIRES.put("10", "Ten");
        SIMPLE_NOMBRES_ELEMENTAIRES.put("11", "Eleven");
        SIMPLE_NOMBRES_ELEMENTAIRES.put("12", "Twelve");
        SIMPLE_NOMBRES_ELEMENTAIRES.put("13", "Thirteen");
        SIMPLE_NOMBRES_ELEMENTAIRES.put("14", "Fourteen");
        SIMPLE_NOMBRES_ELEMENTAIRES.put("15", "Fifteen");
        SIMPLE_NOMBRES_ELEMENTAIRES.put("16", "Sixteen");
        SIMPLE_NOMBRES_ELEMENTAIRES.put("17", "Seventeen");
        SIMPLE_NOMBRES_ELEMENTAIRES.put("18", "Eighteen");
        SIMPLE_NOMBRES_ELEMENTAIRES.put("19", "Nineteen");
    }

    private static final Map<String, String> DIZAINE_NOMBRES_ELEMENTAIRES;
    static {
        DIZAINE_NOMBRES_ELEMENTAIRES = new HashMap<>();
        DIZAINE_NOMBRES_ELEMENTAIRES.put("20", "Twenty");
        DIZAINE_NOMBRES_ELEMENTAIRES.put("30", "Thirty");
        DIZAINE_NOMBRES_ELEMENTAIRES.put("40", "Forty");
        DIZAINE_NOMBRES_ELEMENTAIRES.put("50", "Fifty");
        DIZAINE_NOMBRES_ELEMENTAIRES.put("60", "Sixty");
        DIZAINE_NOMBRES_ELEMENTAIRES.put("70", "Seventy");
        DIZAINE_NOMBRES_ELEMENTAIRES.put("80", "Eighty");
        DIZAINE_NOMBRES_ELEMENTAIRES.put("90", "Ninety");
    }

    private static final Map<String, String> GRAND_NOMBRES_ELEMENTAIRES;
    static {
        GRAND_NOMBRES_ELEMENTAIRES = new HashMap<>();
        GRAND_NOMBRES_ELEMENTAIRES.put("100", "Hundred");
        GRAND_NOMBRES_ELEMENTAIRES.put("1000", "Thousand");
        GRAND_NOMBRES_ELEMENTAIRES.put("1000000", "Million");
        GRAND_NOMBRES_ELEMENTAIRES.put("1000000000", "Billion");
        GRAND_NOMBRES_ELEMENTAIRES.put("1000000000000", "Trillion");
        GRAND_NOMBRES_ELEMENTAIRES.put("1000000000000000", "Quadrillion");
    }

    ReglesCommunes reglesCommunes;

    @Override
    public String transformerLeNombreEnTexte(String nombre){
        reglesCommunes = new ReglesCommunes();
        reglesCommunes.preparerLeNombre(nombre);

        ArrayList<String> leNombreParPaquetsDeTrois = reglesCommunes.subdiviserLeNombreParMillier(nombre);
        ArrayList<String> partiesATransformer = identifierToutesLesPartiesATransformer(nombre, leNombreParPaquetsDeTrois);
        ArrayList<String> texteAssocieAChaquePartie = transformerChaquePartie(partiesATransformer);

        return alignerLesPartiesTransformeesEnUneSeulePhrase(texteAssocieAChaquePartie);
    }

    private ArrayList<String> transformerChaquePartie(ArrayList<String> splitedNumber) {
        ArrayList<String> subdividedNumberOnText = new ArrayList<>();
        for (int i = 0; i < splitedNumber.size(); i++){
            transformerEnTexteLesComposantesPrononcables(splitedNumber, subdividedNumberOnText, i);
        }
        return subdividedNumberOnText;
    }

    private ArrayList<String> identifierToutesLesPartiesATransformer(String nombre, ArrayList<String> partiesComposantLeNombreSubdiviseParMillier) {
        ArrayList<String> resultatSubdivisionDuNombre = new ArrayList<>();
        int nombreDePartiesATransformerEnTexte = partiesComposantLeNombreSubdiviseParMillier.size();

        if (estUnChiffreElementaire(nombre)){
            if (estNombreElementaireGrand(nombre)){
                resultatSubdivisionDuNombre.add(CHIFFRE_UN);
            }
            resultatSubdivisionDuNombre.add(nombre);
        }else {
            for (int i = 0; i < nombreDePartiesATransformerEnTexte; i++) {
                String unePartie = partiesComposantLeNombreSubdiviseParMillier.get(i);
                unePartie = reglesCommunes.supprimerLesZerosSuperflux(unePartie);

                if (reglesCommunes.siPartieNonVide(unePartie)) {
                    if (estNombreElementaireGrand(unePartie)){
                        ajouterPartieAuxComposantesDuNombre(resultatSubdivisionDuNombre, CHIFFRE_UN);
                    }
                    ajouterPartieAuxComposantesDuNombre(resultatSubdivisionDuNombre, unePartie);
                    siGrandNombreDetecteLeRajouterParmiLesComposantesDuNombre(resultatSubdivisionDuNombre, nombreDePartiesATransformerEnTexte, i);
                }
            }
        }

        return resultatSubdivisionDuNombre;
    }

    private void siGrandNombreDetecteLeRajouterParmiLesComposantesDuNombre(ArrayList<String> resultatSubdivisionDuNombre, int nombreDePartiesATransformerEnTexte, int i) {
        String grandNombreAssocie = reglesCommunes.recupererGrandNombreAssocie(nombreDePartiesATransformerEnTexte, i);
        if (unePuissanceCorrespondanteTrouvee(grandNombreAssocie)) {
            ajouterPartieAuxComposantesDuNombre(resultatSubdivisionDuNombre, grandNombreAssocie);
        }
    }

    private boolean unePuissanceCorrespondanteTrouvee(String puissanceCorrespondanteALaPartie) {
        return puissanceCorrespondanteALaPartie != null;
    }

    void ajouterPartieAuxComposantesDuNombre(ArrayList<String> resultatSubdivisionDuNombre, String onePart) {
        if (estUnChiffreElementaire(onePart)) {
            ajouterAuxPartiesComposantLeNombreADecomposer(resultatSubdivisionDuNombre, onePart);
        }else {
            addDecomposedDigitPartToSubdividedNumber(resultatSubdivisionDuNombre, onePart);
        }
    }

    private void addDecomposedDigitPartToSubdividedNumber(ArrayList<String> resultatSubdivisionDuNombre, String onePart) {
        switch (onePart.length()) {
            case THREE_DIGITS:
                decomposerUnePartieDeLongueurTrois(resultatSubdivisionDuNombre, onePart);
                break;
            case TWO_DIGITS:
                decomposerUnePartieDeLongueurDeux(resultatSubdivisionDuNombre, onePart);
                break;
            case ONE_DIGIT:
                ajouterAuxPartiesComposantLeNombreADecomposer(resultatSubdivisionDuNombre, onePart);
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + onePart.length());
        }
    }


    private void decomposerUnePartieDeLongueurDeux(ArrayList<String> resultatSubdivisionDuNombre, String onePart) {
        //first part
        ajouterAuxPartiesComposantLeNombreADecomposer(resultatSubdivisionDuNombre,onePart.substring(0, 1).concat("0"));
        ajouterAuxPartiesComposantLeNombreADecomposer(resultatSubdivisionDuNombre,onePart.substring(1, 2));
    }

    private void decomposerUnePartieDeLongueurTrois(ArrayList<String> resultatSubdivisionDuNombre, String onePart) {
        //first part
        if (!onePart.startsWith(CHIFFRE_UN)) {
            ajouterAuxPartiesComposantLeNombreADecomposer(resultatSubdivisionDuNombre,onePart.substring(0, 1));
        }

        ajouterAuxPartiesComposantLeNombreADecomposer(resultatSubdivisionDuNombre, CHIFFRE_CENT);

        //second part
        if (estUnChiffreElementaire(reglesCommunes.supprimerLesZerosSuperflux(onePart.substring(1, 3)))) {
            ajouterAuxPartiesComposantLeNombreADecomposer(resultatSubdivisionDuNombre,reglesCommunes.supprimerLesZerosSuperflux(onePart.substring(1, 3)));
        } else {
            ajouterAuxPartiesComposantLeNombreADecomposer(resultatSubdivisionDuNombre, onePart.substring(1, 2).concat("0"));
            ajouterAuxPartiesComposantLeNombreADecomposer(resultatSubdivisionDuNombre, onePart.substring(2, 3));
        }
    }

    private void ajouterAuxPartiesComposantLeNombreADecomposer(ArrayList<String> resultatSubdivisionDuNombre, String onePart) {
        if (reglesCommunes.siPartieNonVide(onePart)) {
            resultatSubdivisionDuNombre.add(onePart);
        }
    }

    private void transformerEnTexteLesComposantesPrononcables(ArrayList<String> splitedNumber, ArrayList<String> subdividedNumberOnText, int digitPosition) {
        String onePart = splitedNumber.get(digitPosition);
        String texteCorrespondant = recupererLeTexteAssocieAuNombreElementaire(onePart);
        subdividedNumberOnText.add(texteCorrespondant);
        if (estLeCasDuAndApresLeCent(splitedNumber, digitPosition)){
            subdividedNumberOnText.add(COORDINATEUR_AND);
        }
    }

    private boolean estLeCasDuAndApresLeCent(ArrayList<String> splitedNumber, int digitPosition) {
        return estEgaleACent(splitedNumber, digitPosition)
                && (digitPosition + 1 < splitedNumber.size())
                && !estNombreElementaireGrand(splitedNumber.get(digitPosition + 1));
    }

    private String recupererLeTexteAssocieAuNombreElementaire(String number) {
        if (estUnNombreElementaireSimple(number)) {
            return recupererNombreElementaireSimple(number);
        }else if (estNombreElementaireDizaine(number)) {
            return recupererNombreElementaireDizaine(number);
        }else if (estNombreElementaireGrand(number)) {
            return recupererNombreElementaireGrand(number);
        }else{
            return null;
        }
    }

    String recupererNombreElementaireGrand(String number) {
        return GRAND_NOMBRES_ELEMENTAIRES.get(number);
    }

    String recupererNombreElementaireDizaine(String number) {
        return DIZAINE_NOMBRES_ELEMENTAIRES.get(number);
    }

    String recupererNombreElementaireSimple(String number) {
        return SIMPLE_NOMBRES_ELEMENTAIRES.get(number);
    }

    public boolean estNombreElementaireGrand(String number) {
        return recupererNombreElementaireGrand(number) != null;
    }

    boolean estNombreElementaireDizaine(String number) {
        return recupererNombreElementaireDizaine(number) != null;
    }

    boolean estUnNombreElementaireSimple(String number) {
        return recupererNombreElementaireSimple(number) != null;
    }

    private boolean estUnChiffreElementaire(String number) {
        return estUnNombreElementaireSimple(number)
                || estNombreElementaireDizaine(number)
                || estNombreElementaireGrand(number);
    }

    private boolean estEgaleACent(ArrayList<String> splitedNumber, int digitPosition) {
        return splitedNumber.get(digitPosition).equals(CHIFFRE_CENT);
    }

    private String alignerLesPartiesTransformeesEnUneSeulePhrase(ArrayList<String> subdividedNumberOnText) {
        StringBuilder resultingText = new StringBuilder();
        String precedenteValeur = null;

        for (String actuelleValeur : subdividedNumberOnText){
            resultingText.append(choisirSeparateur(precedenteValeur, actuelleValeur).concat(actuelleValeur));
            precedenteValeur = actuelleValeur;
        }

        return resultingText.substring(1, 2).
                toUpperCase().
                concat(resultingText.substring(2).
                        toLowerCase());
    }

    String choisirSeparateur(String precedenteValeur, String actuelleValeur) {
        return estLaRegleTiretEntreDeuxComposantes(precedenteValeur, actuelleValeur) ? SEPARATEUR_DIZAINE : SEPARATEUR_MOTS;
    }

    boolean estLaRegleTiretEntreDeuxComposantes(String precedenteValeur, String actuelleValeur) {
        return nombreElementDizaineContient(precedenteValeur)
                && nombreElementSimpleContient(actuelleValeur);
    }

    boolean nombreElementSimpleContient(String actuelleValeur) {
        return SIMPLE_NOMBRES_ELEMENTAIRES.containsValue(actuelleValeur);
    }

    boolean nombreElementDizaineContient(String precedenteValeur) {
        return DIZAINE_NOMBRES_ELEMENTAIRES.containsValue(precedenteValeur);
    }

}
