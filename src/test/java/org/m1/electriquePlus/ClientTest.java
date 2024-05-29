package org.m1.electriquePlus;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.m1.electriquePlus.Client;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertThrows;


@DisplayName("Tests sur la classe Client")
public class ClientTest {

    @DisplayName("Test création d'un client avec des informations valides")
    @Test
    public void testClientValide() {
        Adresse adresse = new Adresse(123, "Rue de la Paix", 75001, "Paris", "France");
        Client client = new Client("Dupont", "Jean", adresse, "1234567890", "jean.dupont@example.com", "1234567812345678");
        assertThat(client.getNom()).isEqualTo("Dupont");
        assertThat(client.getPrenom()).isEqualTo("Jean");
        assertThat(client.getAdresse()).isEqualTo(adresse);
        assertThat(client.getNumeroTelephone()).isEqualTo("1234567890");
        assertThat(client.getEmail()).isEqualTo("jean.dupont@example.com");
        assertThat(client.getNumeroCarteDebit()).isEqualTo("1234567812345678");
    }

    @DisplayName("Test création d'un client avec un nom vide")
    @Test
    public void testClientCreationNomVide() {
        Adresse adresse = new Adresse(123, "Rue de la Paix", 75001, "Paris", "France");
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new Client("", "Jean", adresse, "1234567890", "jean.dupont@example.com", "1234567812345678");
        });
        assertThat(exception.getMessage()).isEqualTo("Aucun champ ne doit être vide");
    }

    @DisplayName("Test création d'un client avec un prénom vide")
    @Test
    public void testClientCreationPrenomVide() {
        Adresse adresse = new Adresse(123, "Rue de la Paix", 75001, "Paris", "France");
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new Client("Dupont", "", adresse, "1234567890", "jean.dupont@example.com", "1234567812345678");
        });
        assertThat(exception.getMessage()).isEqualTo("Aucun champ ne doit être vide");
    }

    @DisplayName("Test création d'un client avec une adresse nulle")
    @Test
    public void testClientCreationAdresseNull() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new Client("Dupont", "Jean", null, "1234567890", "jean.dupont@example.com", "1234567812345678");
        });
        assertThat(exception.getMessage()).isEqualTo("Aucun champ ne doit être vide");
    }

    @DisplayName("Test création d'un client avec un email vide")
    @Test
    public void testClientCreationEmailVide() {
        Adresse adresse = new Adresse(123, "Rue de la Paix", 75001, "Paris", "France");
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new Client("Dupont", "Jean", adresse, "1234567890", "", "1234567812345678");
        });
        assertThat(exception.getMessage()).isEqualTo("Aucun champ ne doit être vide");
    }

    @DisplayName("Test création d'un client avec un numéro de carte de débit vide")
    @Test
    public void testClientCreationCBVide() {
        Adresse adresse = new Adresse(123, "Rue de la Paix", 75001, "Paris", "France");
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new Client("Dupont", "Jean", adresse, "1234567890", "jean.dupont@example.com", "");
        });
        assertThat(exception.getMessage()).isEqualTo("Aucun champ ne doit être vide");
    }

    @DisplayName("Test création d'un client avec un numéro de téléphone invalide")
    @Test
    public void testClientCreationNumeroTelephoneInvalide() {
        Adresse adresse = new Adresse(123, "Rue de la Paix", 75001, "Paris", "France");
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new Client("Dupont", "Jean", adresse, "123456789", "jean.dupont@example.com", "1234567812345678");
        });
        assertThat(exception.getMessage()).isEqualTo("Le numéro de téléphone doit contenir 10 chiffres");
    }

    @DisplayName("Test création d'un client avec un email invalide")
    @Test
    public void testClientEmailInvalide() {
        Adresse adresse = new Adresse(123, "Rue de la Paix", 75001, "Paris", "France");
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new Client("Dupont", "Jean", adresse, "1234567890", "jean.dupont", "1234567812345678");
        });
        assertThat(exception.getMessage()).isEqualTo("Adresse mail invalide");
    }

    @DisplayName("Test création d'un client avec un numéro de téléphone invalide (moins de 10 chiffres)")
    @Test
    public void testClientTelephoneTropPetit() {
        Adresse adresse = new Adresse(123, "Rue de la Paix", 75001, "Paris", "France");
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new Client("Dupont", "Jean", adresse, "060102030", "jean.dupont@example.com", "1234567812345678");
        });
        assertThat(exception.getMessage()).isEqualTo("Le numéro de téléphone doit contenir 10 chiffres");
    }

    @DisplayName("Test création d'un client avec un numéro de téléphone invalide (plus de 10 chiffres)")
    @Test
    public void testClientTelephoneTropLong() {
        Adresse adresse = new Adresse(123, "Rue de la Paix", 75001, "Paris", "France");
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new Client("Dupont", "Jean", adresse, "06010203045", "jean.dupont@example.com", "1234567812345678");
        });
        assertThat(exception.getMessage()).isEqualTo("Le numéro de téléphone doit contenir 10 chiffres");
    }

    @DisplayName("Test création d'un client avec un numéro de téléphone invalide (non numérique)")
    @Test
    public void testClientTelephoneAvecLettre() {
        Adresse adresse = new Adresse(123, "Rue de la Paix", 75001, "Paris", "France");
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new Client("Dupont", "Jean", adresse, "06010A0304", "jean.dupont@example.com", "1234567812345678");
        });
        assertThat(exception.getMessage()).isEqualTo("Le numéro de téléphone doit contenir 10 chiffres");
    }
}

