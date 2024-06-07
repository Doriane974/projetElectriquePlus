package org.m1.electriquePlus;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertThrows;


@DisplayName("Tests sur la classe Immatriculation")
public class AdresseTest {

    @DisplayName("Test création d'une adresse avec des informations valides")
    @Test
    public void testAdresseCreationValide() {
        Adresse adresse = new Adresse(123, "Rue de la Paix", "75001", "Paris", "France");
        assertThat(adresse.getNumeroHabitation()).isEqualTo(123);
        assertThat(adresse.getNomRue()).isEqualTo("Rue de la Paix");
        assertThat(adresse.getCodePostal()).isEqualTo("75001");
        assertThat(adresse.getNomVille()).isEqualTo("Paris");
        assertThat(adresse.getNomPays()).isEqualTo("France");
    }

    @DisplayName("Test création d'une adresse avec un numéro d'habitation invalide")
    @Test
    public void testAdresseCreationNumeroHabitationInvalide() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new Adresse(0, "Rue de la Paix", "75001", "Paris", "France");
        });
        assertThat(exception.getMessage()).isEqualTo("Le numéro d'habitation doit être supérieur à 0");
    }

    @DisplayName("Test création d'une adresse avec un nom de rue vide")
    @Test
    public void testAdresseNomVide() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new Adresse(123, "", "75001", "Paris", "France");
        });
        assertThat(exception.getMessage()).isEqualTo("Le nom de rue ne doit pas être vide");
    }

    @DisplayName("Test création d'une adresse avec un code postal de longueur incorrecte")
    @Test
    public void testAdresseCPCourt() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new Adresse(123, "Rue de la Paix", "7501", "Paris", "France");
        });
        assertThat(exception.getMessage()).isEqualTo("Le code postal doit contenir 5 chiffres");
    }

    @DisplayName("Test création d'une adresse avec un nom de ville vide")
    @Test
    public void testAdresseVilleVide() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new Adresse(123, "Rue de la Paix", "75001", "", "France");
        });
        assertThat(exception.getMessage()).isEqualTo("Le nom de ville ne doit pas être vide");
    }

    @DisplayName("Test création d'une adresse avec un nom de pays vide")
    @Test
    public void testAdressePaysVide() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new Adresse(123, "Rue de la Paix", "75001", "Paris", "");
        });
        assertThat(exception.getMessage()).isEqualTo("Le pays ne doit pas être vide");
    }

    @Test
    @DisplayName("Test égalité entre adresses avec les mêmes valeurs")
    public void testEgaliteAdressesValeursIdentiques() {
        Adresse adresse1 = new Adresse(123, "Rue de la Paix", "75001", "Paris", "France");
        Adresse adresse2 = new Adresse(123, "Rue de la Paix", "75001", "Paris", "France");
        assertThat(adresse1).isEqualTo(adresse2);
    }

    @Test
    @DisplayName("Test inégalité entre adresses avec des valeurs différentes")
    public void testInegaliteAdressesValeursDifferentes() {
        Adresse adresse1 = new Adresse(123, "Rue de la Paix", "75001", "Paris", "France");
        Adresse adresse2 = new Adresse(124, "Avenue des Champs-Élysées", "75008", "Paris", "France");
        assertThat(adresse1).isNotEqualTo(adresse2);
    }

    @Test
    @DisplayName("Création de l'adresse avec numéro d'habitation négatif lève une exception")
    void creationAdresseNumeroHabitationNegatif() {
        assertThatThrownBy(() -> new Adresse(-10, "Rue de l'exemple", "12345", "Ville", "Pays"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("Le numéro d'habitation doit être supérieur à 0");
    }

    @Test
    @DisplayName("Création de l'adresse avec numéro d'habitation nul lève une exception")
    void creationAdresseNumeroHabitationNul() {
        assertThatThrownBy(() -> new Adresse(0, "Rue de l'exemple", "12345", "Ville", "Pays"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("Le numéro d'habitation doit être supérieur à 0");
    }

    @Test
    @DisplayName("Création de l'adresse avec nom de rue vide lève une exception")
    void creationAdresseNomRueVide() {
        assertThatThrownBy(() -> new Adresse(10, "", "12345", "Ville", "Pays"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("Le nom de rue ne doit pas être vide");
    }

    @Test
    @DisplayName("Création de l'adresse avec nom de rue null lève une exception")
    void creationAdresseNomRueNull() {
        assertThatThrownBy(() -> new Adresse(10, null, "12345", "Ville", "Pays"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("Le nom de rue ne doit pas être vide");
    }

    @Test
    @DisplayName("Création de l'adresse avec code postal de longueur incorrecte lève une exception")
    void creationAdresseCodePostalLongueurIncorrecte() {
        assertThatThrownBy(() -> new Adresse(10, "Rue de l'exemple", "1234", "Ville", "Pays"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("Le code postal doit contenir 5 chiffres");
    }

    @Test
    @DisplayName("Création de l'adresse avec code postal contenant des lettres lève une exception")
    void creationAdresseCodePostalAvecLettres() {
        assertThatThrownBy(() -> new Adresse(10, "Rue de l'exemple", "12A45", "Ville", "Pays"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("Le code postal doit contenir 5 chiffres");
    }

    @Test
    @DisplayName("Création de l'adresse avec nom de ville vide lève une exception")
    void creationAdresseNomVilleVide() {
        assertThatThrownBy(() -> new Adresse(10, "Rue de l'exemple", "12345", "", "Pays"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("Le nom de ville ne doit pas être vide");
    }

    @Test
    @DisplayName("Création de l'adresse avec nom de ville null lève une exception")
    void creationAdresseNomVilleNull() {
        assertThatThrownBy(() -> new Adresse(10, "Rue de l'exemple", "12345", null, "Pays"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("Le nom de ville ne doit pas être vide");
    }

    @Test
    @DisplayName("Création de l'adresse avec nom de pays vide lève une exception")
    void creationAdresseNomPaysVide() {
        assertThatThrownBy(() -> new Adresse(10, "Rue de l'exemple", "12345", "Ville", ""))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("Le pays ne doit pas être vide");
    }

    @Test
    @DisplayName("Création de l'adresse avec nom de pays null lève une exception")
    void creationAdresseNomPaysNull() {
        assertThatThrownBy(() -> new Adresse(10, "Rue de l'exemple", "12345", "Ville", null))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("Le pays ne doit pas être vide");
    }

    @Test
    @DisplayName("Création de l'adresse avec des valeurs valides ne lève pas d'exception")
    void creationAdresseValeursValides() {
        Adresse adresse = new Adresse(10, "Rue de l'exemple", "12345", "Ville", "Pays");
        assertThat(adresse.getNumeroHabitation()).isEqualTo(10);
        assertThat(adresse.getNomRue()).isEqualTo("Rue de l'exemple");
        assertThat(adresse.getCodePostal()).isEqualTo("12345");
        assertThat(adresse.getNomVille()).isEqualTo("Ville");
        assertThat(adresse.getNomPays()).isEqualTo("Pays");
    }

    @Test
    @DisplayName("Création de l'adresse avec un code postal commençant par 0")
    void creationAdresseCodePostalCommencantParZero() {
        Adresse adresse = new Adresse(10, "Rue de l'exemple", "01234", "Ville", "Pays");
        assertThat(adresse.getCodePostal()).isEqualTo("01234");
    }

    @Test
    @DisplayName("Création de l'adresse avec nom de ville et de pays contenant des espaces")
    void creationAdresseNomVilleEtPaysAvecEspaces() {
        Adresse adresse = new Adresse(10, "Rue de l'exemple", "12345", "Ville de Test", "Pays Exemple");
        assertThat(adresse.getNomVille()).isEqualTo("Ville de Test");
        assertThat(adresse.getNomPays()).isEqualTo("Pays Exemple");
    }

    @Test
    @DisplayName("Création de l'adresse avec numéro d'habitation positif fonctionne correctement")
    void creationAdresseNumeroHabitationPositif() {
        Adresse adresse = new Adresse(123, "Rue de l'exemple", "12345", "Ville", "Pays");
        assertThat(adresse.getNumeroHabitation()).isEqualTo(123);
    }

    @Test
    @DisplayName("Création de l'adresse avec code postal valide de 5 chiffres")
    void creationAdresseCodePostalValide() {
        Adresse adresse = new Adresse(10, "Rue de l'exemple", "54321", "Ville", "Pays");
        assertThat(adresse.getCodePostal()).isEqualTo("54321");
    }

    @Test
    @DisplayName("Création de l'adresse avec code postal contenant seulement des chiffres")
    void creationAdresseCodePostalChiffres() {
        Adresse adresse = new Adresse(10, "Rue de l'exemple", "12345", "Ville", "Pays");
        assertThat(adresse.getCodePostal()).isEqualTo("12345");
    }

    @Test
    @DisplayName("Création de l'adresse avec nom de rue valide")
    void creationAdresseNomRueValide() {
        Adresse adresse = new Adresse(10, "Boulevard de la République", "12345", "Ville", "Pays");
        assertThat(adresse.getNomRue()).isEqualTo("Boulevard de la République");
    }

    @Test
    @DisplayName("Création de l'adresse avec nom de ville valide")
    void creationAdresseNomVilleValide() {
        Adresse adresse = new Adresse(10, "Rue de l'exemple", "12345", "Paris", "France");
        assertThat(adresse.getNomVille()).isEqualTo("Paris");
    }

    @Test
    @DisplayName("Création de l'adresse avec nom de pays valide")
    void creationAdresseNomPaysValide() {
        Adresse adresse = new Adresse(10, "Rue de l'exemple", "12345", "Ville", "France");
        assertThat(adresse.getNomPays()).isEqualTo("France");
    }

    @Test
    @DisplayName("Création de l'adresse avec des champs non vides")
    void creationAdresseChampsNonVides() {
        Adresse adresse = new Adresse(10, "Rue de l'exemple", "12345", "Ville", "Pays");
        assertThat(adresse.getNomRue()).isNotEmpty();
        assertThat(adresse.getCodePostal()).isNotEmpty();
        assertThat(adresse.getNomVille()).isNotEmpty();
        assertThat(adresse.getNomPays()).isNotEmpty();
    }

    @Test
    @DisplayName("Création de l'adresse avec numéro d'habitation supérieur à zéro")
    void creationAdresseNumeroHabitationSuperieurZero() {
        Adresse adresse = new Adresse(1, "Rue de l'exemple", "12345", "Ville", "Pays");
        assertThat(adresse.getNumeroHabitation()).isGreaterThan(0);
    }

    @Test
    @DisplayName("Création de l'adresse avec code postal valide et correct")
    void creationAdresseCodePostalValideEtCorrect() {
        Adresse adresse = new Adresse(10, "Rue de l'exemple", "67890", "Ville", "Pays");
        assertThat(adresse.getCodePostal()).isEqualTo("67890");
    }

}

