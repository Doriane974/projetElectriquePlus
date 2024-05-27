package org.m1.electriquePlus;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;


@DisplayName("Tests sur la classe Immatriculation")
public class ImmatriculationTest {

    @DisplayName("Test avec une plaque d'immatriculation valide")
    @Test
    public void testPlaqueImmatriculationValide() {
        Immatriculation plaque = new Immatriculation("AB-123-CD");
        assertThat(plaque.getLettresAvant()).isEqualTo("AB");
        assertThat(plaque.getChiffres()).isEqualTo(123);
        assertThat(plaque.getLettresApres()).isEqualTo("CD");
    }

    @DisplayName("Test avec un format de plaque invalide")
    @Test
    public void testPlaqueImmatriculationInvalide() {
        Immatriculation plaque = new Immatriculation("EF-456-GH");
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
           new Immatriculation("A1-23-CD");
        });
        assertThat(exception.getMessage()).isEqualTo("Format de plaque invalide");
    }

    @DisplayName("Test avec des chiffres invalides")
    @Test
    public void testPlaqueImmatriculationChiffresInvalide() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new Immatriculation("AB-12A-CD");
        });
        assertThat(exception.getMessage()).isEqualTo("Format de plaque invalide");
    }

    @DisplayName("Test de la méthode toString")
    @Test
    public void testToString() {
        Immatriculation plaque = new Immatriculation("EF-456-GH");
        assertThat(plaque.toString()).isEqualTo("EF-456-GH");
    }

    @DisplayName("Test avec des lettres minuscules dans la plaque d'immatriculation")
    @Test
    public void testPlaqueImmatriculationLettreMisuscule() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new Immatriculation("ab-123-cd");
        });
        assertThat(exception.getMessage()).isEqualTo("Format de plaque invalide");
    }

    @DisplayName("Test avec des des chiffres en plus dans la plaque")
    @Test
    public void testPlaqueImmatriculationChiffreEnPlus() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new Immatriculation("AB-1234-CD");
        });
        assertThat(exception.getMessage()).isEqualTo("Format de plaque invalide");
    }

}

