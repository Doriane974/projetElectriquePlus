package org.m1.electriquePlus;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;


@DisplayName("Tests sur la classe Immatriculation")
public class AdresseTest {

    @DisplayName("Test création d'une adresse avec des informations valides")
    @Test
    public void testAdresseCreationValide() {
        Adresse adresse = new Adresse(123, "Rue de la Paix", 75001, "Paris", "France");
        assertThat(adresse.getNumeroHabitation()).isEqualTo(123);
        assertThat(adresse.getNomRue()).isEqualTo("Rue de la Paix");
        assertThat(adresse.getCodePostal()).isEqualTo(75001);
        assertThat(adresse.getNomVille()).isEqualTo("Paris");
        assertThat(adresse.getNomPays()).isEqualTo("France");
    }

    @DisplayName("Test création d'une adresse avec un numéro d'habitation invalide")
    @Test
    public void testAdresseCreationNumeroHabitationInvalide() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new Adresse(0, "Rue de la Paix", 75001, "Paris", "France");
        });
        assertThat(exception.getMessage()).isEqualTo("Le numéro d'habitation doit être supérieur à 0");
    }

    @DisplayName("Test création d'une adresse avec un nom de rue vide")
    @Test
    public void testAdresseNomVide() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new Adresse(123, "", 75001, "Paris", "France");
        });
        assertThat(exception.getMessage()).isEqualTo("Le nom de rue ne doit pas être vide");
    }

    @DisplayName("Test création d'une adresse avec un code postal de longueur incorrecte")
    @Test
    public void testAdresseCPCourt() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new Adresse(123, "Rue de la Paix", 7501, "Paris", "France");
        });
        assertThat(exception.getMessage()).isEqualTo("Le code postal doit contenir 5 chiffres");
    }

    @DisplayName("Test création d'une adresse avec un nom de ville vide")
    @Test
    public void testAdresseVilleVide() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new Adresse(123, "Rue de la Paix", 75001, "", "France");
        });
        assertThat(exception.getMessage()).isEqualTo("Le nom de ville ne doit pas être vide");
    }

    @DisplayName("Test création d'une adresse avec un nom de pays vide")
    @Test
    public void testAdressePaysVide() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new Adresse(123, "Rue de la Paix", 75001, "Paris", "");
        });
        assertThat(exception.getMessage()).isEqualTo("Le pays ne doit pas être vide");
    }
}

