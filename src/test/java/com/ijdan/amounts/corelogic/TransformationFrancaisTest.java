package com.ijdan.amounts.corelogic;

import com.ijdan.amounts.corelogic.ports.TransformationNombreEnTexteInterface;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class TransformationFrancaisTest {

    @Autowired
    TransformationNombreEnTexteInterface transformationNombreEnTexteInterface;

    @Autowired
    TransformationNombreEnTexte transformationNombreEnTexte;

    @Test
    void transformerLeNombreEnTexteFrancaisTestZero() {
        assertThat(transformationNombreEnTexteInterface.transformerLeNombreEnTexte("0", "FR")).isEqualTo("Zéro");
    }

    @Test
    void transformerLeNombreEnTexteFrancaisTestDeux() {
        assertThat(transformationNombreEnTexteInterface.transformerLeNombreEnTexte("2", "FR")).isEqualTo("Deux");
    }

    @Test
    void transformerLeNombreEnTexteFrancaisTestDix() {
        assertThat(transformationNombreEnTexteInterface.transformerLeNombreEnTexte("10", "FR")).isEqualTo("Dix");
    }

    @Test
    void transformerLeNombreEnTexteFrancaisTestVingtEtUn() {
        assertThat(transformationNombreEnTexteInterface.transformerLeNombreEnTexte("21", "FR"))
                .isEqualTo("Vingt-et-un");
    }

    @Test
    void transformerLeNombreEnTexteFrancaisTestVingtDeux() {
        assertThat(transformationNombreEnTexteInterface.transformerLeNombreEnTexte("22", "FR")).isEqualTo("Vingt-deux");
    }

    @Test
    void transformerLeNombreEnTexteFrancaisTestQuatreVingtUn() {
        assertThat(transformationNombreEnTexteInterface.transformerLeNombreEnTexte("81", "FR"))
                .isEqualTo("Quatre-vingt-un");
    }

    @Test
    void transformerLeNombreEnTexteFrancaisTestCentDeux() {
        assertThat(transformationNombreEnTexteInterface.transformerLeNombreEnTexte("102", "FR")).isEqualTo("Cent-deux");
    }

    @Test
    void transformerLeNombreEnTexteFrancaisTestCentVingtEtUn() {
        assertThat(transformationNombreEnTexteInterface.transformerLeNombreEnTexte("121", "FR"))
                .isEqualTo("Cent-vingt-et-un");
    }

    @Test
    void transformerLeNombreEnTexteFrancaisTestCentVingtCinq() {
        assertThat(transformationNombreEnTexteInterface.transformerLeNombreEnTexte("125", "FR"))
                .isEqualTo("Cent-vingt-cinq");
    }

    @Test
    void transformerLeNombreEnTexteFrancaisTestTroisCents() {
        assertThat(transformationNombreEnTexteInterface.transformerLeNombreEnTexte("300", "FR"))
                .isEqualTo("Trois-cents");
    }

    @Test
    void transformerLeNombreEnTexteFrancaisTestTroisCentQuaranteTrois() {
        assertThat(transformationNombreEnTexteInterface.transformerLeNombreEnTexte("343", "FR"))
                .isEqualTo("Trois-cent-quarante-trois");
    }

    @Test
    void transformerLeNombreEnTexteFrancaisTestCinqCentQuatreVingtDixSept() {
        assertThat(transformationNombreEnTexteInterface.transformerLeNombreEnTexte("597", "FR"))
                .isEqualTo("Cinq-cent-quatre-vingt-dix-sept");
    }

    @Test
    void transformerLeNombreEnTexteFrancaisTestTroisMilleCinqCentQuatreVingtDixSept() {
        assertThat(transformationNombreEnTexteInterface.transformerLeNombreEnTexte("3597", "FR"))
                .isEqualTo("Trois-mille-cinq-cent-quatre-vingt-dix-sept");
    }

    @Test
    void transformerLeNombreEnTexteFrancaisTestDixMilleCinqCentQuatreVingtDixSept() {
        assertThat(transformationNombreEnTexteInterface.transformerLeNombreEnTexte("10597", "FR"))
                .isEqualTo("Dix-mille-cinq-cent-quatre-vingt-dix-sept");
    }

    @Test
    void transformerLeNombreEnTexteFrancaisTestDeuxCentsMillions() {
        assertThat(transformationNombreEnTexteInterface.transformerLeNombreEnTexte("200000000", "FR"))
                .isEqualTo("Deux-cents-millions");
    }

    @Test
    void transformerLeNombreEnTexteFrancaisTestDeuxCentsMilliards() {
        assertThat(transformationNombreEnTexteInterface.transformerLeNombreEnTexte("200000000000", "FR"))
                .isEqualTo("Deux-cents-milliards");
    }

    @Test
    void transformerLeNombreEnTexteFrancaisTestUnMilliardTroisMillionsQuatreCentVingtEtUnMilleCentNeuf() {
        assertThat(transformationNombreEnTexteInterface.transformerLeNombreEnTexte("1003421109", "FR"))
                .isEqualTo("Un-milliard-trois-millions-quatre-cent-vingt-et-un-mille-cent-neuf");
    }

    @Test
    void transformerLeNombreEnTexteFrancaisTestCentMillions() {
        assertThat(transformationNombreEnTexteInterface.transformerLeNombreEnTexte("100000000", "FR"))
                .isEqualTo("Cent-millions");
    }

    @Test
    void transformerLeNombreEnTexteFrancaisTestUnMillion() {
        assertThat(transformationNombreEnTexteInterface.transformerLeNombreEnTexte("1000000", "FR"))
                .isEqualTo("Un-million");
    }

    @Test
    void transformerLeNombreEnTexteFrancaisTestUnMilliard() {
        assertThat(transformationNombreEnTexteInterface.transformerLeNombreEnTexte("1000000000", "FR"))
                .isEqualTo("Un-milliard");
    }

    @Test
    void transformerLeNombreEnTexteFrancaisTestUnMilliardUn() {
        assertThat(transformationNombreEnTexteInterface.transformerLeNombreEnTexte("1000000001", "FR"))
                .isEqualTo("Un-milliard-un");
    }

    @Test
    void transformerLeNombreEnTexteFrancaisTestUnBillionCentMillionsDixMilleVingt() {
        assertThat(transformationNombreEnTexteInterface.transformerLeNombreEnTexte("1000100010020", "FR"))
                .isEqualTo("Un-billion-cent-millions-dix-mille-vingt");
    }

    @Test
    void transformerLeNombreEnTexteTest() {
        String nombre = "123";
        String langue = "FR";
        String expected = "Cent-vingt-trois";

        String result = transformationNombreEnTexte.transformerLeNombreEnTexte(nombre, langue);

        assertEquals(expected, result);
    }

    @Test
    void transformerLeNombreEnTexteFrancaisTestQuinze() {
        assertThat(transformationNombreEnTexteInterface.transformerLeNombreEnTexte("15", "FR")).isEqualTo("Quinze");
    }

    @Test
    void transformerLeNombreEnTexteFrancaisTestCinquante() {
        assertThat(transformationNombreEnTexteInterface.transformerLeNombreEnTexte("50", "FR")).isEqualTo("Cinquante");
    }

    @Test
    void transformerLeNombreEnTexteFrancaisTestCinquanteEtUn() {
        assertThat(transformationNombreEnTexteInterface.transformerLeNombreEnTexte("51", "FR"))
                .isEqualTo("Cinquante-et-un");
    }

    @Test
    void transformerLeNombreEnTexteFrancaisTestSoixanteEtOnze() {
        assertThat(transformationNombreEnTexteInterface.transformerLeNombreEnTexte("71", "FR"))
                .isEqualTo("Soixante-et-onze");
    }

    @Test
    void transformerLeNombreEnTexteFrancaisTestQuatreVingts() {
        assertThat(transformationNombreEnTexteInterface.transformerLeNombreEnTexte("80", "FR"))
                .isEqualTo("Quatre-vingts");
    }

    @Test
    void transformerLeNombreEnTexteFrancaisTestQuatreVingtDeux() {
        assertThat(transformationNombreEnTexteInterface.transformerLeNombreEnTexte("82", "FR"))
                .isEqualTo("Quatre-vingt-deux");
    }

    @Test
    void transformerLeNombreEnTexteFrancaisTestDeuxCentQuatreVingts() {
        assertThat(transformationNombreEnTexteInterface.transformerLeNombreEnTexte("280", "FR"))
                .isEqualTo("Deux-cent-quatre-vingts");
    }

    @Test
    void transformerLeNombreEnTexteFrancaisTestQuatreVingtMille() {
        assertThat(transformationNombreEnTexteInterface.transformerLeNombreEnTexte("80000", "FR"))
                .isEqualTo("Quatre-vingt-mille");
    }

    @Test
    void transformerLeNombreEnTexteFrancaisTestQuatreVingtsMillions() {
        assertThat(transformationNombreEnTexteInterface.transformerLeNombreEnTexte("80000000", "FR"))
                .isEqualTo("Quatre-vingts-millions");
    }

    @Test
    void transformerLeNombreEnTexteFrancaisTestDixSept() {
        assertThat(transformationNombreEnTexteInterface.transformerLeNombreEnTexte("17", "FR")).isEqualTo("Dix-sept");
    }

    @Test
    void transformerLeNombreEnTexteFrancaisTestDixHuit() {
        assertThat(transformationNombreEnTexteInterface.transformerLeNombreEnTexte("18", "FR")).isEqualTo("Dix-huit");
    }

    @Test
    void transformerLeNombreEnTexteFrancaisTestDixNeuf() {
        assertThat(transformationNombreEnTexteInterface.transformerLeNombreEnTexte("19", "FR")).isEqualTo("Dix-neuf");
    }

    @Test
    void transformerLeNombreEnTexteFrancaisTestSoixanteDix() {
        assertThat(transformationNombreEnTexteInterface.transformerLeNombreEnTexte("70", "FR"))
                .isEqualTo("Soixante-dix");
    }

    @Test
    void transformerLeNombreEnTexteFrancaisTestSoixanteDouze() {
        assertThat(transformationNombreEnTexteInterface.transformerLeNombreEnTexte("72", "FR"))
                .isEqualTo("Soixante-douze");
    }

    @Test
    void transformerLeNombreEnTexteFrancaisTestSoixanteTreize() {
        assertThat(transformationNombreEnTexteInterface.transformerLeNombreEnTexte("73", "FR"))
                .isEqualTo("Soixante-treize");
    }

    @Test
    void transformerLeNombreEnTexteFrancaisTestSoixanteQuatorze() {
        assertThat(transformationNombreEnTexteInterface.transformerLeNombreEnTexte("74", "FR"))
                .isEqualTo("Soixante-quatorze");
    }

    @Test
    void transformerLeNombreEnTexteFrancaisTestSoixanteQuinze() {
        assertThat(transformationNombreEnTexteInterface.transformerLeNombreEnTexte("75", "FR"))
                .isEqualTo("Soixante-quinze");
    }

    @Test
    void transformerLeNombreEnTexteFrancaisTestSoixanteSeize() {
        assertThat(transformationNombreEnTexteInterface.transformerLeNombreEnTexte("76", "FR"))
                .isEqualTo("Soixante-seize");
    }

    @Test
    void transformerLeNombreEnTexteFrancaisTestSoixanteDixSept() {
        assertThat(transformationNombreEnTexteInterface.transformerLeNombreEnTexte("77", "FR"))
                .isEqualTo("Soixante-dix-sept");
    }

    @Test
    void transformerLeNombreEnTexteFrancaisTestSoixanteDixHuit() {
        assertThat(transformationNombreEnTexteInterface.transformerLeNombreEnTexte("78", "FR"))
                .isEqualTo("Soixante-dix-huit");
    }

    @Test
    void transformerLeNombreEnTexteFrancaisTestSoixanteDixNeuf() {
        assertThat(transformationNombreEnTexteInterface.transformerLeNombreEnTexte("79", "FR"))
                .isEqualTo("Soixante-dix-neuf");
    }

    @Test
    void transformerLeNombreEnTexteFrancaisTestQuatreVingtDix() {
        assertThat(transformationNombreEnTexteInterface.transformerLeNombreEnTexte("90", "FR"))
                .isEqualTo("Quatre-vingt-dix");
    }

    @Test
    void transformerLeNombreEnTexteFrancaisTestQuatreVingtOnze() {
        assertThat(transformationNombreEnTexteInterface.transformerLeNombreEnTexte("91", "FR"))
                .isEqualTo("Quatre-vingt-onze");
    }

    @Test
    void transformerLeNombreEnTexteFrancaisTestQuatreVingtDouze() {
        assertThat(transformationNombreEnTexteInterface.transformerLeNombreEnTexte("92", "FR"))
                .isEqualTo("Quatre-vingt-douze");
    }

    @Test
    void transformerLeNombreEnTexteFrancaisTestQuatreVingtTreize() {
        assertThat(transformationNombreEnTexteInterface.transformerLeNombreEnTexte("93", "FR"))
                .isEqualTo("Quatre-vingt-treize");
    }

    @Test
    void transformerLeNombreEnTexteFrancaisTestQuatreVingtQuatorze() {
        assertThat(transformationNombreEnTexteInterface.transformerLeNombreEnTexte("94", "FR"))
                .isEqualTo("Quatre-vingt-quatorze");
    }

    @Test
    void transformerLeNombreEnTexteFrancaisTestQuatreVingtQuinze() {
        assertThat(transformationNombreEnTexteInterface.transformerLeNombreEnTexte("95", "FR"))
                .isEqualTo("Quatre-vingt-quinze");
    }

    @Test
    void transformerLeNombreEnTexteFrancaisTestQuatreVingtSeize() {
        assertThat(transformationNombreEnTexteInterface.transformerLeNombreEnTexte("96", "FR"))
                .isEqualTo("Quatre-vingt-seize");
    }

    @Test
    void transformerLeNombreEnTexteFrancaisTestQuatreVingtDixSept() {
        assertThat(transformationNombreEnTexteInterface.transformerLeNombreEnTexte("97", "FR"))
                .isEqualTo("Quatre-vingt-dix-sept");
    }

    @Test
    void transformerLeNombreEnTexteFrancaisTestQuatreVingtDixHuit() {
        assertThat(transformationNombreEnTexteInterface.transformerLeNombreEnTexte("98", "FR"))
                .isEqualTo("Quatre-vingt-dix-huit");
    }

    @Test
    void transformerLeNombreEnTexteFrancaisTestQuatreVingtDixNeuf() {
        assertThat(transformationNombreEnTexteInterface.transformerLeNombreEnTexte("99", "FR"))
                .isEqualTo("Quatre-vingt-dix-neuf");
    }
}
