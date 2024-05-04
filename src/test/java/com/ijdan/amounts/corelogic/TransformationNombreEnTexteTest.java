package com.ijdan.amounts.corelogic;

import com.ijdan.amounts.corelogic.ports.TransformationNombreEnTexteInterface;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class TransformationNombreEnTexteTest {
    @Autowired
    TransformationNombreEnTexteInterface transformationNombreEnTexteInterface;

    @Test
    void transformerLeNombreEnTexteFrancaisTestZero() throws Exception {
        assertThat(transformationNombreEnTexteInterface.transformerLeNombreEnTexte("0", "FR")).isEqualTo("Zéro");
    }
    @Test
    void transformerLeNombreEnTexteFrancaisTestDeux() throws Exception {
        assertThat(transformationNombreEnTexteInterface.transformerLeNombreEnTexte("2", "FR")).isEqualTo("Deux");
    }

    @Test
    void transformerLeNombreEnTexteFrancaisTestDix() throws Exception {
        assertThat(transformationNombreEnTexteInterface.transformerLeNombreEnTexte("10", "FR")).isEqualTo("Dix");
    }

    @Test
    void transformerLeNombreEnTexteFrancaisTestVingtEtUn() throws Exception {
        assertThat(transformationNombreEnTexteInterface.transformerLeNombreEnTexte("21", "FR")).isEqualTo("Vingt-et-un");
    }

    @Test
    void transformerLeNombreEnTexteFrancaisTestVingtDeux() throws Exception {
        assertThat(transformationNombreEnTexteInterface.transformerLeNombreEnTexte("22", "FR")).isEqualTo("Vingt-deux");
    }

    @Test
    void transformerLeNombreEnTexteFrancaisTestQuatreVingtUn() throws Exception {
        assertThat(transformationNombreEnTexteInterface.transformerLeNombreEnTexte("81", "FR")).isEqualTo("Quatre-vingt-un");
    }

    @Test
    void transformerLeNombreEnTexteFrancaisTestCentDeux() throws Exception {
        assertThat(transformationNombreEnTexteInterface.transformerLeNombreEnTexte("102", "FR")).isEqualTo("Cent-deux");
    }

    @Test
    void transformerLeNombreEnTexteFrancaisTestCentVingtEtUn() throws Exception {
        assertThat(transformationNombreEnTexteInterface.transformerLeNombreEnTexte("121", "FR")).isEqualTo("Cent-vingt-et-un");
    }

    @Test
    void transformerLeNombreEnTexteFrancaisTestCentVingtCinq() throws Exception {
        assertThat(transformationNombreEnTexteInterface.transformerLeNombreEnTexte("125", "FR")).isEqualTo("Cent-vingt-cinq");
    }
    @Test
    void transformerLeNombreEnTexteFrancaisTestTroisCents() throws Exception {
        assertThat(transformationNombreEnTexteInterface.transformerLeNombreEnTexte("300", "FR")).isEqualTo("Trois-cents");
    }

    @Test
    void transformerLeNombreEnTexteFrancaisTestTroisCentQuaranteTrois() throws Exception {
        assertThat(transformationNombreEnTexteInterface.transformerLeNombreEnTexte("343", "FR")).isEqualTo("Trois-cent-quarante-trois");
    }

    @Test
    void transformerLeNombreEnTexteFrancaisTestCinqCentQuatreVingtDixSept() throws Exception {
        assertThat(transformationNombreEnTexteInterface.transformerLeNombreEnTexte("597", "FR")).isEqualTo("Cinq-cent-quatre-vingt-dix-sept");
    }

    @Test
    void transformerLeNombreEnTexteFrancaisTestTroisMilleCinqCentQuatreVingtDixSept() throws Exception {
        assertThat(transformationNombreEnTexteInterface.transformerLeNombreEnTexte("3597", "FR")).isEqualTo("Trois-mille-cinq-cent-quatre-vingt-dix-sept");
    }

    @Test
    void transformerLeNombreEnTexteFrancaisTestDixMilleCinqCentQuatreVingtDixSept() throws Exception {
        assertThat(transformationNombreEnTexteInterface.transformerLeNombreEnTexte("10597", "FR")).isEqualTo("Dix-mille-cinq-cent-quatre-vingt-dix-sept");
    }

    @Test
    void transformerLeNombreEnTexteFrancaisTestDeuxCentsMillions() throws Exception {
        assertThat(transformationNombreEnTexteInterface.transformerLeNombreEnTexte("200000000", "FR")).isEqualTo("Deux-cents-millions");
    }

    @Test
    void transformerLeNombreEnTexteFrancaisTestDeuxCentsMilliards() throws Exception {
        assertThat(transformationNombreEnTexteInterface.transformerLeNombreEnTexte("200000000000", "FR")).isEqualTo("Deux-cents-milliards");
    }

    @Test
    void transformerLeNombreEnTexteFrancaisTestUnMilliardTroisMillionsQuatreCentVingtEtUnMilleCentNeuf() throws Exception {
        assertThat(transformationNombreEnTexteInterface.transformerLeNombreEnTexte("1003421109", "FR")).isEqualTo("Un-milliard-trois-millions-quatre-cent-vingt-et-un-mille-cent-neuf");
    }

    @Test
    void transformerLeNombreEnTexteFrancaisTestCentMillions() throws Exception {
        assertThat(transformationNombreEnTexteInterface.transformerLeNombreEnTexte("100000000", "FR")).isEqualTo("Cent-millions");
    }

    @Test
    void transformerLeNombreEnTexteFrancaisTestUnMillion() throws Exception {
        assertThat(transformationNombreEnTexteInterface.transformerLeNombreEnTexte("1000000", "FR")).isEqualTo("Un-million");
    }

    @Test
    void transformerLeNombreEnTexteFrancaisTestUnMilliard() throws Exception {
        assertThat(transformationNombreEnTexteInterface.transformerLeNombreEnTexte("1000000000", "FR")).isEqualTo("Un-milliard");
    }

    @Test
    void transformerLeNombreEnTexteFrancaisTestUnMilliardUn() throws Exception {
        assertThat(transformationNombreEnTexteInterface.transformerLeNombreEnTexte("1000000001", "FR")).isEqualTo("Un-milliard-un");
    }

    @Test
    void transformerLeNombreEnTexteFrancaisTestUnBillionCentMillionsDixMilleVingt() throws Exception {
        assertThat(transformationNombreEnTexteInterface.transformerLeNombreEnTexte("1000100010020", "FR")).isEqualTo("Un-billion-cent-millions-dix-mille-vingt");
    }


    @Test
    void transformerLeNombreEnTexteAnglaisTest() throws Exception {

        assertThat(
                transformationNombreEnTexteInterface.transformerLeNombreEnTexte("0", "EN")
        ).isEqualTo("Zero");

        assertThat(
                transformationNombreEnTexteInterface.transformerLeNombreEnTexte("2", "EN")
        ).isEqualTo("Two");

        assertThat(
                transformationNombreEnTexteInterface.transformerLeNombreEnTexte("10", "EN")
        ).isEqualTo("Ten");

        assertThat(
                transformationNombreEnTexteInterface.transformerLeNombreEnTexte("21", "EN")
        ).isEqualTo("Twenty-one");

        assertThat(
                transformationNombreEnTexteInterface.transformerLeNombreEnTexte("22", "EN")
        ).isEqualTo("Twenty-two");

        assertThat(
                transformationNombreEnTexteInterface.transformerLeNombreEnTexte("3465", "EN")
        ).isEqualTo("Three thousand four hundred and sixty-five");

        assertThat(
                transformationNombreEnTexteInterface.transformerLeNombreEnTexte("340543", "EN")
        ).isEqualTo("Three hundred and forty thousand five hundred and forty-three");

        assertThat(
                transformationNombreEnTexteInterface.transformerLeNombreEnTexte("12345678901234", "EN")
        ).isEqualTo("Twelve trillion three hundred and forty-five billion six hundred and seventy-eight million nine hundred and one thousand two hundred and thirty-four");

        assertThat(
                transformationNombreEnTexteInterface.transformerLeNombreEnTexte("1000100010020", "EN")
        ).isEqualTo("One trillion one hundred million ten thousand twenty");

        assertThat(
                transformationNombreEnTexteInterface.transformerLeNombreEnTexte("12045", "EN")
        ).isEqualTo("Twelve thousand forty-five");

        assertThat(
                transformationNombreEnTexteInterface.transformerLeNombreEnTexte("100012334", "EN")
        ).isEqualTo("One hundred million twelve thousand three hundred and thirty-four");

        assertThat(
                transformationNombreEnTexteInterface.transformerLeNombreEnTexte("1021", "EN")
        ).isEqualTo("One thousand twenty-one");

        assertThat(
                transformationNombreEnTexteInterface.transformerLeNombreEnTexte("1345476892356", "EN")
        ).isEqualTo("One trillion three hundred and forty-five billion four hundred and seventy-six million eight hundred and ninety-two thousand three hundred and fifty-six");

        assertThat(
                transformationNombreEnTexteInterface.transformerLeNombreEnTexte("9456823", "EN")
        ).isEqualTo("Nine million four hundred and fifty-six thousand eight hundred and twenty-three");

        assertThat(
                transformationNombreEnTexteInterface.transformerLeNombreEnTexte("3002004001", "EN")
        ).isEqualTo("Three billion two million four thousand one");

        assertThat(
                transformationNombreEnTexteInterface.transformerLeNombreEnTexte("300041", "EN")
        ).isEqualTo("Three hundred thousand forty-one");
    }

}
