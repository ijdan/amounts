package com.ijdan.amounts.corelogic.ManagerLangue;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.stream.IntStream;

@Component
public class ReglesCommunes {

    public static final int THOUSAND_PART_SIZE = 3;
    public static final Long EMPTY_VALUE_NUMBER = 0L;


    public ArrayList<String> subdiviserLeNombreParMillier(String nombre) {
        int numberLength = nombre.length();
        int subStringStartDigit = 0;
        int subStringEndDigit = recupererLaFinDuPremierPaquet(numberLength);

        ArrayList<String> nombreSubdiviseParMilliers = new ArrayList<>();

        int numberOfThousandsParts = recupererLeNombreDePartsDeMilliers(numberLength);
        for (int i = 0; i < numberOfThousandsParts; i++) {
            addSubdividedPart(nombre, nombreSubdiviseParMilliers, subStringStartDigit, subStringEndDigit);
            subStringStartDigit = subStringEndDigit;
            subStringEndDigit = subStringStartDigit + THOUSAND_PART_SIZE;
        }

        return nombreSubdiviseParMilliers;
    }

    private void addSubdividedPart(String nombre, ArrayList<String> nombreSubdiviseParMilliers, int start, int end) {
        nombreSubdiviseParMilliers.add(nombre.substring(start, end));
    }

    public String recupererGrandNombreAssocie(int subdividedByThousandsLength, int positionCourante) {
        if (positionCourante + 1 < subdividedByThousandsLength) {
            String zeroADroiteCorrespond = new String(
                    new char[(subdividedByThousandsLength - 1 - positionCourante) * THOUSAND_PART_SIZE]).
                    replace("\0", "0");
            return "1".concat(zeroADroiteCorrespond);
        } else {
            return null;
        }
    }

    private static int recupererLaFinDuPremierPaquet(int numberLength) {
        int subStringEndDigit = numberLength % THOUSAND_PART_SIZE;
        return subStringEndDigit == 0 ? THOUSAND_PART_SIZE : subStringEndDigit;
    }

    private int recupererLeNombreDePartsDeMilliers(double numberLength) {
        return (int) Math.ceil(numberLength / THOUSAND_PART_SIZE);
    }

    public String preparerLeNombre(String nombre) {
        return nombre.trim();
    }

    public String supprimerLesZerosSuperflux(String onePart) {
        return String.valueOf(Integer.valueOf(onePart));
    }

    public boolean siPartieNonVide(String unePart) {
        return !Long.valueOf(unePart).equals(EMPTY_VALUE_NUMBER);
    }

}
