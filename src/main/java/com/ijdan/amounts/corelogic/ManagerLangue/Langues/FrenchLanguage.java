package com.ijdan.amounts.corelogic.ManagerLangue.Langues;

import com.ijdan.amounts.corelogic.ManagerLangue.CommonRules;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class FrenchLanguage extends AbstractLanguage {

    public static final String CHIFFRE_UN = "1";
    public static final String CHIFFRE_CENT = "100";
    public static final String CHIFFRE_MILLE = "1000";

    public static final String CARACTRE_SUFFIXE_PLLURIEL = "s";
    public static final String SEPARATEUR_MOTS = "-";

    private static final Map<String, String> SIMPLE_NOMBRES_ELEMENTAIRES;
    static {
        SIMPLE_NOMBRES_ELEMENTAIRES = new HashMap<>();
        SIMPLE_NOMBRES_ELEMENTAIRES.put("0", "Zéro");
        SIMPLE_NOMBRES_ELEMENTAIRES.put("1", "Un");
        SIMPLE_NOMBRES_ELEMENTAIRES.put("2", "Deux");
        SIMPLE_NOMBRES_ELEMENTAIRES.put("3", "Trois");
        SIMPLE_NOMBRES_ELEMENTAIRES.put("4", "Quatre");
        SIMPLE_NOMBRES_ELEMENTAIRES.put("5", "Cinq");
        SIMPLE_NOMBRES_ELEMENTAIRES.put("6", "Six");
        SIMPLE_NOMBRES_ELEMENTAIRES.put("7", "Sept");
        SIMPLE_NOMBRES_ELEMENTAIRES.put("8", "Huit");
        SIMPLE_NOMBRES_ELEMENTAIRES.put("9", "Neuf");
        SIMPLE_NOMBRES_ELEMENTAIRES.put("10", "Dix");
        SIMPLE_NOMBRES_ELEMENTAIRES.put("11", "Onze");
        SIMPLE_NOMBRES_ELEMENTAIRES.put("12", "Douze");
        SIMPLE_NOMBRES_ELEMENTAIRES.put("13", "Treize");
        SIMPLE_NOMBRES_ELEMENTAIRES.put("14", "Quatorze");
        SIMPLE_NOMBRES_ELEMENTAIRES.put("15", "Quinze");
        SIMPLE_NOMBRES_ELEMENTAIRES.put("16", "Seize");
        SIMPLE_NOMBRES_ELEMENTAIRES.put("17", "Dix-sept");
        SIMPLE_NOMBRES_ELEMENTAIRES.put("18", "Dix-huit");
        SIMPLE_NOMBRES_ELEMENTAIRES.put("19", "Dix-neuf");
        SIMPLE_NOMBRES_ELEMENTAIRES.put("20", "Vingt");
        SIMPLE_NOMBRES_ELEMENTAIRES.put("21", "Vingt-et-Un");
        SIMPLE_NOMBRES_ELEMENTAIRES.put("30", "Trente");
        SIMPLE_NOMBRES_ELEMENTAIRES.put("31", "Trente-et-Un");
        SIMPLE_NOMBRES_ELEMENTAIRES.put("40", "Quarante");
        SIMPLE_NOMBRES_ELEMENTAIRES.put("41", "Quarante-et-Un");
        SIMPLE_NOMBRES_ELEMENTAIRES.put("50", "Cinquante");
        SIMPLE_NOMBRES_ELEMENTAIRES.put("51", "Cinquante-et-Un");
        SIMPLE_NOMBRES_ELEMENTAIRES.put("60", "Soixante");
        SIMPLE_NOMBRES_ELEMENTAIRES.put("61", "Soixante-et-Un");
        SIMPLE_NOMBRES_ELEMENTAIRES.put("70", "Soixante-Dix");
        SIMPLE_NOMBRES_ELEMENTAIRES.put("71", "Soixante-et-Onze");
        SIMPLE_NOMBRES_ELEMENTAIRES.put("72", "Soixante-Douze");
        SIMPLE_NOMBRES_ELEMENTAIRES.put("73", "Soixante-Treize");
        SIMPLE_NOMBRES_ELEMENTAIRES.put("74", "Soixante-Quatorze");
        SIMPLE_NOMBRES_ELEMENTAIRES.put("75", "Soixante-Quinze");
        SIMPLE_NOMBRES_ELEMENTAIRES.put("76", "Soixante-Seize");
        SIMPLE_NOMBRES_ELEMENTAIRES.put("77", "Soixante-Dix-Sept");
        SIMPLE_NOMBRES_ELEMENTAIRES.put("78", "Soixante-Dix-Huit");
        SIMPLE_NOMBRES_ELEMENTAIRES.put("79", "Soixante-Dix-Neuf");
        SIMPLE_NOMBRES_ELEMENTAIRES.put("80", "Quatre-Vingt");
        SIMPLE_NOMBRES_ELEMENTAIRES.put("90", "Quatre-Vingt-Dix");
        SIMPLE_NOMBRES_ELEMENTAIRES.put("91", "Quatre-Vingt-Onze");
        SIMPLE_NOMBRES_ELEMENTAIRES.put("92", "Quatre-Vingt-Douze");
        SIMPLE_NOMBRES_ELEMENTAIRES.put("93", "Quatre-Vingt-Treize");
        SIMPLE_NOMBRES_ELEMENTAIRES.put("94", "Quatre-Vingt-Quatorze");
        SIMPLE_NOMBRES_ELEMENTAIRES.put("95", "Quatre-Vingt-Quinze");
        SIMPLE_NOMBRES_ELEMENTAIRES.put("96", "Quatre-Vingt-Seize");
        SIMPLE_NOMBRES_ELEMENTAIRES.put("97", "Quatre-Vingt-Dix-Sept");
        SIMPLE_NOMBRES_ELEMENTAIRES.put("98", "Quatre-Vingt-Dix-Huit");
        SIMPLE_NOMBRES_ELEMENTAIRES.put("99", "Quatre-Vingt-Dix-Neuf");
    }

    private static final Map<String, String> GRAND_NOMBRES_ELEMENTAIRES;
    static {
        GRAND_NOMBRES_ELEMENTAIRES = new HashMap<>();
        GRAND_NOMBRES_ELEMENTAIRES.put("100", "Cent");
        GRAND_NOMBRES_ELEMENTAIRES.put("1000", "Mille");
        GRAND_NOMBRES_ELEMENTAIRES.put("1000000", "Million");
        GRAND_NOMBRES_ELEMENTAIRES.put("1000000000", "Milliard");
        GRAND_NOMBRES_ELEMENTAIRES.put("1000000000000", "Billion");
        GRAND_NOMBRES_ELEMENTAIRES.put("1000000000000000", "Billiard");
    }

    public FrenchLanguage(CommonRules reglesCommunes) {
        super(reglesCommunes);
    }

    @Override
    protected void transformerEnTexteLesComposantesPrononcables(ArrayList<String> splitedNumber,
            ArrayList<String> subdividedNumberOnText, int digitPosition) {
        String onePart = splitedNumber.get(digitPosition);
        String texteCorrespondant = recupererLeTexteAssocieAuNombreElementaire(onePart);
        if (estEgaleACent(splitedNumber, digitPosition)) {
            if (estLeCasCentAMettreAuPluriel(splitedNumber, digitPosition)) {
                texteCorrespondant = mettreAuPluriel(texteCorrespondant);
            }
        } else if (estLeCasQuatreVingtAMettreAuPluriel(splitedNumber, digitPosition, texteCorrespondant)) {
            texteCorrespondant = mettreAuPluriel(texteCorrespondant);
        } else if (estNombreElementaireGrand(onePart) && estDifferentDeMille(onePart)
                && estLeCasPlurielAuDelaDeMille(splitedNumber, digitPosition)) {
            texteCorrespondant = mettreAuPluriel(texteCorrespondant);
        }

        if (estLeCasAvecUnARajouterDevant(digitPosition, texteCorrespondant)) {
            subdividedNumberOnText.add(recupererLeTexteAssocieAuNombreElementaire(CHIFFRE_UN));
        }
        subdividedNumberOnText.add(texteCorrespondant);
    }

    private String recupererLeTexteAssocieAuNombreElementaire(String number) {
        if (estUnNombreElementaireSimple(number)) {
            return recupererNombreElementaireSimple(number);
        } else {
            return recupererNombreElementaireGrand(number);
        }
    }

    String recupererNombreElementaireGrand(String number) {
        return GRAND_NOMBRES_ELEMENTAIRES.get(number);
    }

    String recupererNombreElementaireSimple(String number) {
        return SIMPLE_NOMBRES_ELEMENTAIRES.get(number);
    }

    private boolean estNombreElementaireGrand(String number) {
        return recupererNombreElementaireGrand(number) != null;
    }

    boolean estUnNombreElementaireSimple(String number) {
        return recupererNombreElementaireSimple(number) != null;
    }

    @Override
    protected boolean estUnChiffreElementaire(String number) {
        return (estUnNombreElementaireSimple(number)
                || estNombreElementaireGrand(number));
    }

    private boolean grandNombreElementaireContient(String valeur) {
        return GRAND_NOMBRES_ELEMENTAIRES.containsValue(valeur);
    }

    private boolean estLeCasAvecUnARajouterDevant(int digitPosition, String texteCorrespondant) {
        return digitPosition == 0
                && leTexteEstDifferentDeCent(texteCorrespondant)
                && leTexteEstDifferentDeMille(texteCorrespondant)
                && grandNombreElementaireContient(texteCorrespondant);
    }

    private boolean leTexteEstDifferentDeCent(String valeur) {
        return !valeur.equals(GRAND_NOMBRES_ELEMENTAIRES.get(CHIFFRE_CENT));
    }

    private boolean leTexteEstDifferentDeMille(String valeur) {
        return !valeur.equals(GRAND_NOMBRES_ELEMENTAIRES.get(CHIFFRE_MILLE));
    }

    private boolean estDifferentDeMille(String valeur) {
        return !valeur.equals(CHIFFRE_MILLE);
    }

    private boolean estLeCasQuatreVingtAMettreAuPluriel(ArrayList<String> splitedNumber, int digitPosition,
            String texteCorrespondant) {
        return texteCorrespondant.equals("Quatre-Vingt") && (estLaDerniereValeur(splitedNumber, digitPosition)
                || valeurSuivanteEstSuperieureAMille(splitedNumber, digitPosition));
    }

    private boolean estLeCasCentAMettreAuPluriel(ArrayList<String> splitedNumber, int digitPosition) {
        return estEgaleACent(splitedNumber, digitPosition)
                && precedenteValeurExiste(digitPosition)
                && precedenteValeurEstMultipliante(splitedNumber, digitPosition)
                && (estLaDerniereValeur(splitedNumber, digitPosition)
                        || valeurSuivanteEstSuperieureAMille(splitedNumber, digitPosition))
                && !estNombreElementaireGrand(splitedNumber.get(digitPosition - 1));
    }

    private boolean estEgaleACent(ArrayList<String> splitedNumber, int digitPosition) {
        return splitedNumber.get(digitPosition).equals(CHIFFRE_CENT);
    }

    private boolean precedenteValeurExiste(int digitPosition) {
        return digitPosition > 0;
    }

    private boolean precedenteValeurEstMultipliante(ArrayList<String> splitedNumber, int digitPosition) {
        return Long.parseLong(splitedNumber.get(digitPosition - 1)) > 1L;
    }

    private boolean valeurSuivanteEstSuperieureAMille(ArrayList<String> splitedNumber, int digitPosition) {
        return digitPosition + 1 < splitedNumber.size()
                && Long.parseLong(splitedNumber.get(digitPosition + 1)) > Long.parseLong(CHIFFRE_MILLE);
    }

    private boolean estLaDerniereValeur(ArrayList<String> splitedNumber, int digitPosition) {
        return digitPosition == splitedNumber.size() - 1;
    }

    private String mettreAuPluriel(String recupererLeTexteAssocieAuChiffreIndivisible) {
        return recupererLeTexteAssocieAuChiffreIndivisible + CARACTRE_SUFFIXE_PLLURIEL;
    }

    private boolean estLeCasPlurielAuDelaDeMille(ArrayList<String> splitedNumber, int digitPosition) {
        return digitPosition > 0 && Long.parseLong(splitedNumber.get(digitPosition - 1)) > 1L;
    }

    @Override
    protected String alignerLesPartiesTransformeesEnUneSeulePhrase(ArrayList<String> subdividedNumberOnText) {
        String resultingText = String.join(SEPARATEUR_MOTS, subdividedNumberOnText);
        return resultingText.substring(0, 1).toUpperCase().concat(resultingText.substring(1).toLowerCase());
    }

}
