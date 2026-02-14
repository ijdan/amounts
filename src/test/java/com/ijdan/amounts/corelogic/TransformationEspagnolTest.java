package com.ijdan.amounts.corelogic;

import com.ijdan.amounts.corelogic.ports.TransformationNombreEnTexteInterface;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class TransformationEspagnolTest {

    @Autowired
    TransformationNombreEnTexteInterface transformationNombreEnTexteInterface;

    @Test
    void transformerLeNombreEnTexteEspagnolTestZero() {
        assertThat(transformationNombreEnTexteInterface.transformerLeNombreEnTexte("0", "ES")).isEqualTo("Cero");
    }

    @Test
    void transformerLeNombreEnTexteEspagnolTestCinco() {
        assertThat(transformationNombreEnTexteInterface.transformerLeNombreEnTexte("5", "ES")).isEqualTo("Cinco");
    }

    @Test
    void transformerLeNombreEnTexteEspagnolTestQuinze() {
        assertThat(transformationNombreEnTexteInterface.transformerLeNombreEnTexte("15", "ES")).isEqualTo("Quince");
    }

    @Test
    void transformerLeNombreEnTexteEspagnolTestDiecisiete() {
        assertThat(transformationNombreEnTexteInterface.transformerLeNombreEnTexte("17", "ES")).isEqualTo("Diecisiete");
    }

    @Test
    void transformerLeNombreEnTexteEspagnolTestVeinte() {
        assertThat(transformationNombreEnTexteInterface.transformerLeNombreEnTexte("20", "ES")).isEqualTo("Veinte");
    }

    @Test
    void transformerLeNombreEnTexteEspagnolTestVeintiuno() {
        assertThat(transformationNombreEnTexteInterface.transformerLeNombreEnTexte("21", "ES")).isEqualTo("Veintiuno");
    }

    @Test
    void transformerLeNombreEnTexteEspagnolTestTreinta() {
        assertThat(transformationNombreEnTexteInterface.transformerLeNombreEnTexte("30", "ES")).isEqualTo("Treinta");
    }

    @Test
    void transformerLeNombreEnTexteEspagnolTestTreintaYUno() {
        assertThat(transformationNombreEnTexteInterface.transformerLeNombreEnTexte("31", "ES"))
                .isEqualTo("Treinta y uno");
    }

    @Test
    void transformerLeNombreEnTexteEspagnolTestCien() {
        assertThat(transformationNombreEnTexteInterface.transformerLeNombreEnTexte("100", "ES")).isEqualTo("Cien");
    }

    @Test
    void transformerLeNombreEnTexteEspagnolTestCientoUno() {
        assertThat(transformationNombreEnTexteInterface.transformerLeNombreEnTexte("101", "ES"))
                .isEqualTo("Ciento uno");
    }

    @Test
    void transformerLeNombreEnTexteEspagnolTestQuinientos() {
        assertThat(transformationNombreEnTexteInterface.transformerLeNombreEnTexte("500", "ES"))
                .isEqualTo("Quinientos");
    }

    @Test
    void transformerLeNombreEnTexteEspagnolTestMil() {
        assertThat(transformationNombreEnTexteInterface.transformerLeNombreEnTexte("1000", "ES")).isEqualTo("Mil");
    }

    @Test
    void transformerLeNombreEnTexteEspagnolTestDosMil() {
        assertThat(transformationNombreEnTexteInterface.transformerLeNombreEnTexte("2000", "ES")).isEqualTo("Dos mil");
    }

    @Test
    void transformerLeNombreEnTexteEspagnolTestVeintiunMil() {
        assertThat(transformationNombreEnTexteInterface.transformerLeNombreEnTexte("21000", "ES"))
                .isEqualTo("Veintiún mil");
    }

    @Test
    void transformerLeNombreEnTexteEspagnolTestTreintaYUnMil() {
        assertThat(transformationNombreEnTexteInterface.transformerLeNombreEnTexte("31000", "ES"))
                .isEqualTo("Treinta y un mil");
    }

    @Test
    void transformerLeNombreEnTexteEspagnolTestUnMillon() {
        assertThat(transformationNombreEnTexteInterface.transformerLeNombreEnTexte("1000000", "ES"))
                .isEqualTo("Un millón");
    }

    @Test
    void transformerLeNombreEnTexteEspagnolTestDosMillones() {
        assertThat(transformationNombreEnTexteInterface.transformerLeNombreEnTexte("2000000", "ES"))
                .isEqualTo("Dos millones");
    }
}
