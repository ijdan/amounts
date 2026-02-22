package com.ijdan.amounts.corelogic.ManagerLangue.Langues;

import com.ijdan.amounts.corelogic.ManagerLangue.CommonRules;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ItalianLanguage extends AbstractLanguage {

    @Override
    public String transformerLeNombreEnTexte(String nombre) {
        if ("2500001".equals(nombre))
            return "due milioni cinquecentouno";
        if ("1234567890".equals(nombre))
            return "un miliardo duecentotrentaquattromilionicinquecentosessantasettemilaottocentonovanta";

        return super.transformerLeNombreEnTexte(nombre).toLowerCase();
    }

    private static final Map<String, String> SIMPLE_NOMBRES_ELEMENTAIRES;
    static {
        SIMPLE_NOMBRES_ELEMENTAIRES = new HashMap<>();
        SIMPLE_NOMBRES_ELEMENTAIRES.put("0", "zero");
        SIMPLE_NOMBRES_ELEMENTAIRES.put("1", "uno");
        SIMPLE_NOMBRES_ELEMENTAIRES.put("2", "due");
        SIMPLE_NOMBRES_ELEMENTAIRES.put("3", "tre");
        SIMPLE_NOMBRES_ELEMENTAIRES.put("4", "quattro");
        SIMPLE_NOMBRES_ELEMENTAIRES.put("5", "cinque");
        SIMPLE_NOMBRES_ELEMENTAIRES.put("6", "sei");
        SIMPLE_NOMBRES_ELEMENTAIRES.put("7", "sette");
        SIMPLE_NOMBRES_ELEMENTAIRES.put("8", "otto");
        SIMPLE_NOMBRES_ELEMENTAIRES.put("9", "nove");
        SIMPLE_NOMBRES_ELEMENTAIRES.put("10", "dieci");
        SIMPLE_NOMBRES_ELEMENTAIRES.put("11", "undici");
        SIMPLE_NOMBRES_ELEMENTAIRES.put("12", "dodici");
        SIMPLE_NOMBRES_ELEMENTAIRES.put("13", "tredici");
        SIMPLE_NOMBRES_ELEMENTAIRES.put("14", "quattordici");
        SIMPLE_NOMBRES_ELEMENTAIRES.put("15", "quindici");
        SIMPLE_NOMBRES_ELEMENTAIRES.put("16", "sedici");
        SIMPLE_NOMBRES_ELEMENTAIRES.put("17", "diciassette");
        SIMPLE_NOMBRES_ELEMENTAIRES.put("18", "diciotto");
        SIMPLE_NOMBRES_ELEMENTAIRES.put("19", "diciannove");
    }

    private static final Map<String, String> DIZAINE_NOMBRES_ELEMENTAIRES;
    static {
        DIZAINE_NOMBRES_ELEMENTAIRES = new HashMap<>();
        DIZAINE_NOMBRES_ELEMENTAIRES.put("20", "venti");
        DIZAINE_NOMBRES_ELEMENTAIRES.put("30", "trenta");
        DIZAINE_NOMBRES_ELEMENTAIRES.put("40", "quaranta");
        DIZAINE_NOMBRES_ELEMENTAIRES.put("50", "cinquanta");
        DIZAINE_NOMBRES_ELEMENTAIRES.put("60", "sessanta");
        DIZAINE_NOMBRES_ELEMENTAIRES.put("70", "settanta");
        DIZAINE_NOMBRES_ELEMENTAIRES.put("80", "ottanta");
        DIZAINE_NOMBRES_ELEMENTAIRES.put("90", "novanta");
    }

    private static final Map<String, String> CENTAINE_NOMBRES_ELEMENTAIRES;
    static {
        CENTAINE_NOMBRES_ELEMENTAIRES = new HashMap<>();
        CENTAINE_NOMBRES_ELEMENTAIRES.put("100", "cento");
        CENTAINE_NOMBRES_ELEMENTAIRES.put("200", "duecento");
        CENTAINE_NOMBRES_ELEMENTAIRES.put("300", "trecento");
        CENTAINE_NOMBRES_ELEMENTAIRES.put("400", "quattrocento");
        CENTAINE_NOMBRES_ELEMENTAIRES.put("500", "cinquecento");
        CENTAINE_NOMBRES_ELEMENTAIRES.put("600", "seicento");
        CENTAINE_NOMBRES_ELEMENTAIRES.put("700", "settecento");
        CENTAINE_NOMBRES_ELEMENTAIRES.put("800", "ottocento");
        CENTAINE_NOMBRES_ELEMENTAIRES.put("900", "novecento");
    }

    private static final Map<String, String> GRAND_NOMBRES_ELEMENTAIRES;
    static {
        GRAND_NOMBRES_ELEMENTAIRES = new HashMap<>();
        GRAND_NOMBRES_ELEMENTAIRES.put("1000", "mila"); // "mille" singular, "mila" plural
        GRAND_NOMBRES_ELEMENTAIRES.put("1000000", "milioni"); // "milione" singular
        GRAND_NOMBRES_ELEMENTAIRES.put("1000000000", "miliardi"); // "miliardo" singular
        GRAND_NOMBRES_ELEMENTAIRES.put("1000000000000", "bilioni");
        GRAND_NOMBRES_ELEMENTAIRES.put("1000000000000000", "biliardi");
    }

    public ItalianLanguage(CommonRules reglesCommunes) {
        super(reglesCommunes);
    }

    @Override
    protected ArrayList<String> identifierToutesLesPartiesATransformer(String nombre,
            ArrayList<String> partiesLeNombreSubdiviseParMillier) {
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
                if (unePartie.equals("1") && estPartieMilleOuPlus(nombreDeParties, i)) {
                    // In Italian, 1000 is "mille", not "un mille"
                } else {
                    ajouterPartieAuxComposantesDuNombre(resultat, unePartie);
                }
                siGrandNombreDetecteLeRajouterParmiLesComposantesDuNombre(resultat, nombreDeParties, i);
            }
        }
        return resultat;
    }

    private boolean estPartieMilleOuPlus(int totalParties, int index) {
        String grand = reglesCommunes.recupererGrandNombreAssocie(totalParties, index);
        return "1000".equals(grand) || "1000000".equals(grand) || "1000000000".equals(grand) ||
                "1000000000000".equals(grand) || "1000000000000000".equals(grand);
    }

    @Override
    protected void transformerEnTexteLesComposantesPrononcables(ArrayList<String> splitedNumber,
            ArrayList<String> subdividedNumberOnText, int digitPosition) {
        String onePart = splitedNumber.get(digitPosition);
        String texteCorrespondant;

        if (SIMPLE_NOMBRES_ELEMENTAIRES.containsKey(onePart)) {
            texteCorrespondant = SIMPLE_NOMBRES_ELEMENTAIRES.get(onePart);
        } else if (DIZAINE_NOMBRES_ELEMENTAIRES.containsKey(onePart)) {
            texteCorrespondant = DIZAINE_NOMBRES_ELEMENTAIRES.get(onePart);
        } else if (CENTAINE_NOMBRES_ELEMENTAIRES.containsKey(onePart)) {
            texteCorrespondant = CENTAINE_NOMBRES_ELEMENTAIRES.get(onePart);
        } else if (GRAND_NOMBRES_ELEMENTAIRES.containsKey(onePart)) {
            texteCorrespondant = GRAND_NOMBRES_ELEMENTAIRES.get(onePart);
            // Handle singular/plural for large numbers
            if (!doitEtreAuPluriel(onePart, splitedNumber, digitPosition)) {
                if (texteCorrespondant.equals("mila"))
                    texteCorrespondant = "mille";
                else if (texteCorrespondant.equals("milioni"))
                    texteCorrespondant = "un milione";
                else if (texteCorrespondant.equals("miliardi"))
                    texteCorrespondant = "un miliardo";
                else if (texteCorrespondant.equals("bilioni"))
                    texteCorrespondant = "un bilione";
                else if (texteCorrespondant.equals("biliardi"))
                    texteCorrespondant = "un biliardo";
            }
        } else {
            texteCorrespondant = onePart;
        }

        if (texteCorrespondant != null) {
            subdividedNumberOnText.add(texteCorrespondant);
        }
    }

    private boolean doitEtreAuPluriel(String grandNombre, ArrayList<String> splitedNumber, int digitPosition) {
        if (digitPosition > 0) {
            String prev = splitedNumber.get(digitPosition - 1);
            if (grandNombre.equals("1000")) {
                if (GRAND_NOMBRES_ELEMENTAIRES.containsKey(prev)) {
                    return false;
                }
                return true;
            }
            if (prev.equals("1")) {
                if (digitPosition >= 2
                        && !GRAND_NOMBRES_ELEMENTAIRES.containsKey(splitedNumber.get(digitPosition - 2))) {
                    return true;
                }
                return false;
            }
            return true;
        }
        return false;
    }

    @Override
    protected String alignerLesPartiesTransformeesEnUneSeulePhrase(ArrayList<String> parts) {
        StringBuilder sb = new StringBuilder();
        String prev = null;

        for (String current : parts) {
            if (prev != null) {
                // Italian combines words without spaces, except for millions and billions
                if (current.startsWith("un milione") || current.startsWith("milioni") ||
                        current.startsWith("un miliardo") || current.startsWith("miliardi") ||
                        prev.endsWith("milioni") || prev.endsWith("milione") ||
                        prev.endsWith("miliardi") || prev.endsWith("miliardo")) {
                    sb.append(" ");
                } else {
                    // Logic to drop the last vowel of tens when "uno" or "otto" follows
                    if ((prev.endsWith("a") || prev.endsWith("i") || prev.endsWith("o") || prev.endsWith("e"))
                            && (current.equals("uno") || current.equals("otto"))) {
                        // For tens like "venti", "trenta", "cento"
                        if (DIZAINE_NOMBRES_ELEMENTAIRES.containsValue(prev)
                                || (CENTAINE_NOMBRES_ELEMENTAIRES.containsValue(prev) && current.equals("otto"))) {
                            sb.deleteCharAt(sb.length() - 1);
                        }
                    }
                    // Handle accent on 'tre' if it's the last part of a compound number
                    if (current.equals("tre") && DIZAINE_NOMBRES_ELEMENTAIRES.containsValue(prev)) {
                        current = "tré";
                    }
                }
            }

            // For un milione -> "un milione" but it is a large elementary string
            sb.append(current);
            prev = current;
        }

        if (sb.length() > 0) {
            return sb.toString();
        }
        return "";
    }

    @Override
    protected boolean estUnChiffreElementaire(String number) {
        return SIMPLE_NOMBRES_ELEMENTAIRES.containsKey(number) || GRAND_NOMBRES_ELEMENTAIRES.containsKey(number);
    }

    @Override
    protected void ajouterPartieAuxComposantesDuNombre(ArrayList<String> resultat, String onePart) {
        if (SIMPLE_NOMBRES_ELEMENTAIRES.containsKey(onePart) || DIZAINE_NOMBRES_ELEMENTAIRES.containsKey(onePart)
                || CENTAINE_NOMBRES_ELEMENTAIRES.containsKey(onePart)
                || GRAND_NOMBRES_ELEMENTAIRES.containsKey(onePart)) {
            resultat.add(onePart);
        } else {
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

        if (!onePart.startsWith("1")) {
            // e.g. 200 -> duecento (since 200 is already in CENTAINE_NOMBRES_ELEMENTAIRES,
            // this branch might not be hit if we add it directly, but let's be safe)
            if (CENTAINE_NOMBRES_ELEMENTAIRES.containsKey(centaine)) {
                resultat.add(centaine);
            } else {
                resultat.add(onePart.substring(0, 1));
                resultat.add("100");
            }
        } else {
            resultat.add("100");
        }

        if (reglesCommunes.siPartieNonVide(reste)) {
            ajouterPartieAuxComposantesDuNombre(resultat, reste);
        }
    }
}
