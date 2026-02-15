package com.ijdan.amounts.corelogic.ManagerLangue.Langues;

import com.ijdan.amounts.corelogic.ManagerLangue.CommonRules;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class SpanishLanguage extends AbstractLanguage {

    public static final String CHIFFRE_UN = "1";
    public static final String CHIFFRE_CENT = "100";
    public static final String SEPARATEUR_MOTS = " ";
    public static final String SEPARATEUR_Y = " y ";

    private static final Map<String, String> SIMPLE_NOMBRES_ELEMENTAIRES;
    static {
        SIMPLE_NOMBRES_ELEMENTAIRES = new HashMap<>();
        SIMPLE_NOMBRES_ELEMENTAIRES.put("0", "Cero");
        SIMPLE_NOMBRES_ELEMENTAIRES.put("1", "Uno");
        SIMPLE_NOMBRES_ELEMENTAIRES.put("2", "Dos");
        SIMPLE_NOMBRES_ELEMENTAIRES.put("3", "Tres");
        SIMPLE_NOMBRES_ELEMENTAIRES.put("4", "Cuatro");
        SIMPLE_NOMBRES_ELEMENTAIRES.put("5", "Cinco");
        SIMPLE_NOMBRES_ELEMENTAIRES.put("6", "Seis");
        SIMPLE_NOMBRES_ELEMENTAIRES.put("7", "Siete");
        SIMPLE_NOMBRES_ELEMENTAIRES.put("8", "Ocho");
        SIMPLE_NOMBRES_ELEMENTAIRES.put("9", "Nueve");
        SIMPLE_NOMBRES_ELEMENTAIRES.put("10", "Diez");
        SIMPLE_NOMBRES_ELEMENTAIRES.put("11", "Once");
        SIMPLE_NOMBRES_ELEMENTAIRES.put("12", "Doce");
        SIMPLE_NOMBRES_ELEMENTAIRES.put("13", "Trece");
        SIMPLE_NOMBRES_ELEMENTAIRES.put("14", "Catorce");
        SIMPLE_NOMBRES_ELEMENTAIRES.put("15", "Quince");
        SIMPLE_NOMBRES_ELEMENTAIRES.put("16", "Dieciséis");
        SIMPLE_NOMBRES_ELEMENTAIRES.put("17", "Diecisiete");
        SIMPLE_NOMBRES_ELEMENTAIRES.put("18", "Dieciocho");
        SIMPLE_NOMBRES_ELEMENTAIRES.put("19", "Diecinueve");
        SIMPLE_NOMBRES_ELEMENTAIRES.put("20", "Veinte");
        SIMPLE_NOMBRES_ELEMENTAIRES.put("21", "Veintiuno");
        SIMPLE_NOMBRES_ELEMENTAIRES.put("22", "Veintidós");
        SIMPLE_NOMBRES_ELEMENTAIRES.put("23", "Veintitrés");
        SIMPLE_NOMBRES_ELEMENTAIRES.put("24", "Veinticuatro");
        SIMPLE_NOMBRES_ELEMENTAIRES.put("25", "Veinticinco");
        SIMPLE_NOMBRES_ELEMENTAIRES.put("26", "Veintiséis");
        SIMPLE_NOMBRES_ELEMENTAIRES.put("27", "Veintisiete");
        SIMPLE_NOMBRES_ELEMENTAIRES.put("28", "Veintiocho");
        SIMPLE_NOMBRES_ELEMENTAIRES.put("29", "Veintinueve");
    }

    private static final Map<String, String> DIZAINE_NOMBRES_ELEMENTAIRES;
    static {
        DIZAINE_NOMBRES_ELEMENTAIRES = new HashMap<>();
        DIZAINE_NOMBRES_ELEMENTAIRES.put("30", "Treinta");
        DIZAINE_NOMBRES_ELEMENTAIRES.put("40", "Cuarenta");
        DIZAINE_NOMBRES_ELEMENTAIRES.put("50", "Cincuenta");
        DIZAINE_NOMBRES_ELEMENTAIRES.put("60", "Sesenta");
        DIZAINE_NOMBRES_ELEMENTAIRES.put("70", "Setenta");
        DIZAINE_NOMBRES_ELEMENTAIRES.put("80", "Ochenta");
        DIZAINE_NOMBRES_ELEMENTAIRES.put("90", "Noventa");
    }

    private static final Map<String, String> CENTAINE_NOMBRES_ELEMENTAIRES;
    static {
        CENTAINE_NOMBRES_ELEMENTAIRES = new HashMap<>();
        CENTAINE_NOMBRES_ELEMENTAIRES.put("100", "Cien");
        CENTAINE_NOMBRES_ELEMENTAIRES.put("200", "Doscientos");
        CENTAINE_NOMBRES_ELEMENTAIRES.put("300", "Trescientos");
        CENTAINE_NOMBRES_ELEMENTAIRES.put("400", "Cuatrocientos");
        CENTAINE_NOMBRES_ELEMENTAIRES.put("500", "Quinientos");
        CENTAINE_NOMBRES_ELEMENTAIRES.put("600", "Seiscientos");
        CENTAINE_NOMBRES_ELEMENTAIRES.put("700", "Setecientos");
        CENTAINE_NOMBRES_ELEMENTAIRES.put("800", "Ochocientos");
        CENTAINE_NOMBRES_ELEMENTAIRES.put("900", "Novecientos");
    }

    private static final Map<String, String> GRAND_NOMBRES_ELEMENTAIRES;
    static {
        GRAND_NOMBRES_ELEMENTAIRES = new HashMap<>();
        GRAND_NOMBRES_ELEMENTAIRES.put("1000", "Mil");
        GRAND_NOMBRES_ELEMENTAIRES.put("1000000", "Millón");
        GRAND_NOMBRES_ELEMENTAIRES.put("1000000000", "Mil millones");
        GRAND_NOMBRES_ELEMENTAIRES.put("1000000000000", "Billón");
        GRAND_NOMBRES_ELEMENTAIRES.put("1000000000000000", "Mil billones");
    }

    public SpanishLanguage(CommonRules reglesCommunes) {
        super(reglesCommunes);
    }

    @Override
    protected ArrayList<String> identifierToutesLesPartiesATransformer(String nombre,
            ArrayList<String> partiesLeNombreSubdiviseParMillier) {
        // Needs Custom Override because of "estPartieMille" logic skipping "Uno" before
        // "Mil"
        ArrayList<String> resultat = new ArrayList<>();
        int nombreDeParties = partiesLeNombreSubdiviseParMillier.size();

        if (nombre.equals("0")) {
            resultat.add("0");
            return resultat;
        }

        for (int i = 0; i < nombreDeParties; i++) {
            String unePartie = partiesLeNombreSubdiviseParMillier.get(i);
            unePartie = reglesCommunes.supprimerLesZerosSuperflux(unePartie);

            if (reglesCommunes.siPartieNonVide(unePartie)) {
                if (unePartie.equals("1") && estPartieMille(nombreDeParties, i)) {
                    // Do not add "1" before "Mil"
                } else {
                    ajouterPartieAuxComposantesDuNombre(resultat, unePartie);
                }

                siGrandNombreDetecteLeRajouterParmiLesComposantesDuNombre(resultat, nombreDeParties, i);
            }
        }
        return resultat;
    }

    private boolean estPartieMille(int totalParties, int index) {
        String grand = reglesCommunes.recupererGrandNombreAssocie(totalParties, index);
        return "1000".equals(grand);
    }

    @Override
    protected void transformerEnTexteLesComposantesPrononcables(ArrayList<String> splitedNumber,
            ArrayList<String> subdividedNumberOnText, int digitPosition) {
        String onePart = splitedNumber.get(digitPosition);
        String texteCorrespondant;

        if (estUnNombreElementaireSimple(onePart)) {
            texteCorrespondant = SIMPLE_NOMBRES_ELEMENTAIRES.get(onePart);
        } else if (DIZAINE_NOMBRES_ELEMENTAIRES.containsKey(onePart)) {
            texteCorrespondant = DIZAINE_NOMBRES_ELEMENTAIRES.get(onePart);
        } else if (CENTAINE_NOMBRES_ELEMENTAIRES.containsKey(onePart)) {
            texteCorrespondant = CENTAINE_NOMBRES_ELEMENTAIRES.get(onePart);
            // Handle "Cien" vs "Ciento"
            if (onePart.equals("100") && estSuiviParAutreChose(splitedNumber, digitPosition)) {
                texteCorrespondant = "Ciento";
            }
        } else if (GRAND_NOMBRES_ELEMENTAIRES.containsKey(onePart)) {
            texteCorrespondant = GRAND_NOMBRES_ELEMENTAIRES.get(onePart);
            // Pluralize Millions/Billions if needed
            if (doitEtreAuPluriel(onePart, splitedNumber, digitPosition)) {
                if (texteCorrespondant.equals("Millón"))
                    texteCorrespondant = "Millones";
                else if (texteCorrespondant.equals("Billón"))
                    texteCorrespondant = "Billones";
                else if (texteCorrespondant.equals("Mil billones"))
                    texteCorrespondant = "Mil billones";
            }
        } else {
            texteCorrespondant = onePart;
        }

        if (texteCorrespondant != null) {
            // Apocope rules for "Uno" -> "Un", "Veintiuno" -> "Veintiún"
            if (estSuiviParGrandNombre(splitedNumber, digitPosition)) {
                if (texteCorrespondant.equals("Uno")) {
                    texteCorrespondant = "Un";
                } else if (texteCorrespondant.equals("Veintiuno")) {
                    texteCorrespondant = "Veintiún";
                }
            }
            subdividedNumberOnText.add(texteCorrespondant);
        }
    }

    private boolean estSuiviParAutreChose(ArrayList<String> splitedNumber, int digitPosition) {
        return (digitPosition + 1 < splitedNumber.size())
                && !estNombreElementaireGrand(splitedNumber.get(digitPosition + 1));
    }

    private boolean doitEtreAuPluriel(String grandNombre, ArrayList<String> splitedNumber, int digitPosition) {
        if (digitPosition > 0) {
            String prev = splitedNumber.get(digitPosition - 1);
            if (grandNombre.equals("1000"))
                return false;

            // If previous is "1", it might still be plural if it's part of a larger number
            // (e.g., 41 -> 40, 1)
            if (prev.equals("1")) {
                if (digitPosition >= 2) {
                    String prevPrev = splitedNumber.get(digitPosition - 2);
                    // If the one before "1" is a number component (not a large unit like "Millón"
                    // from previous block), then it's a compound number -> plural
                    return !estNombreElementaireGrand(prevPrev);
                }
                return false; // Just "1" -> Singular
            }
            return true; // Not "1" (e.g. 2, 3, 20...) -> Plural
        }
        return false;
    }

    private boolean estUnNombreElementaireSimple(String number) {
        return SIMPLE_NOMBRES_ELEMENTAIRES.containsKey(number);
    }

    private boolean estNombreElementaireGrand(String number) {
        return GRAND_NOMBRES_ELEMENTAIRES.containsKey(number);
    }

    private boolean estSuiviParGrandNombre(ArrayList<String> splitedNumber, int digitPosition) {
        return (digitPosition + 1 < splitedNumber.size())
                && estNombreElementaireGrand(splitedNumber.get(digitPosition + 1));
    }

    @Override
    protected String alignerLesPartiesTransformeesEnUneSeulePhrase(ArrayList<String> parts) {
        StringBuilder sb = new StringBuilder();
        String prev = null;

        for (String current : parts) {
            if (prev != null) {
                if (doitAjouterY(prev, current)) {
                    sb.append(SEPARATEUR_Y);
                } else {
                    sb.append(SEPARATEUR_MOTS);
                }
            }
            sb.append(current);
            prev = current;
        }

        if (sb.length() > 0) {
            return sb.substring(0, 1).toUpperCase() + sb.substring(1).toLowerCase();
        }
        return "";
    }

    private boolean doitAjouterY(String prev, String current) {
        // "Treinta" "Uno" -> "Treinta y Uno"
        boolean isTens = DIZAINE_NOMBRES_ELEMENTAIRES.containsValue(prev) && !prev.equals("Veinte");

        boolean isUnit = (SIMPLE_NOMBRES_ELEMENTAIRES.containsValue(current) || current.equals("Un"))
                && !current.equals("Mil")
                && !current.equals("Millón");

        return isTens && isUnit;
    }

    @Override
    protected boolean estUnChiffreElementaire(String number) {
        return estUnNombreElementaireSimple(number) || estNombreElementaireGrand(number);
    }

    @Override
    protected void ajouterPartieAuxComposantesDuNombre(ArrayList<String> resultat, String onePart) {
        if (SIMPLE_NOMBRES_ELEMENTAIRES.containsKey(onePart) || DIZAINE_NOMBRES_ELEMENTAIRES.containsKey(onePart)
                || CENTAINE_NOMBRES_ELEMENTAIRES.containsKey(onePart)
                || GRAND_NOMBRES_ELEMENTAIRES.containsKey(onePart)) {
            resultat.add(onePart);
        } else {
            // Decompose
            if (onePart.length() == 3) {
                decomposerUnePartieDeLongueurTrois(resultat, onePart);
            } else if (onePart.length() == 2) {
                decomposerUnePartieDeLongueurDeux(resultat, onePart);
            }
        }
    }

    @Override
    protected void decomposerUnePartieDeLongueurDeux(ArrayList<String> resultat, String onePart) {
        String dizaine = onePart.substring(0, 1) + "0";
        String unite = onePart.substring(1);

        resultat.add(dizaine);
        if (!unite.equals("0")) {
            resultat.add(unite);
        }
    }

    @Override
    protected void decomposerUnePartieDeLongueurTrois(ArrayList<String> resultat, String onePart) {
        String centaine = onePart.substring(0, 1) + "00";
        String reste = reglesCommunes.supprimerLesZerosSuperflux(onePart.substring(1));

        resultat.add(centaine);

        if (reglesCommunes.siPartieNonVide(reste)) {
            ajouterPartieAuxComposantesDuNombre(resultat, reste);
        }
    }
}
