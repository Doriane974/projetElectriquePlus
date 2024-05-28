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
        Vehicule vehicule = new Vehicule("AB-123-CD","marque","modele",2022);
        Client client = new Client("Dupont", "Jean", adresse, 1234567890L, "jean.dupont@example.com", "1234567812345678",vehicule);
        assertThat(client.getNom()).isEqualTo("Dupont");
        assertThat(client.getPrenom()).isEqualTo("Jean");
        assertThat(client.getAdresse()).isEqualTo(adresse);
        assertThat(client.getNumeroTelephone()).isEqualTo(1234567890L);
        assertThat(client.getEmail()).isEqualTo("jean.dupont@example.com");
        assertThat(client.getNumeroCarteDebit()).isEqualTo("1234567812345678");
    }

    @DisplayName("Test création d'un client avec un nom vide")
    @Test
    public void testClientCreationNomVide() {
        Adresse adresse = new Adresse(123, "Rue de la Paix", 75001, "Paris", "France");
        Vehicule vehicule = new Vehicule("AB-123-CD","marque","modele",2022);
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new Client("", "Jean", adresse, 1234567890L, "jean.dupont@example.com", "1234567812345678",vehicule);
        });
        assertThat(exception.getMessage()).isEqualTo("Aucun champ ne doit être vide");
    }

    @DisplayName("Test création d'un client avec un prénom vide")
    @Test
    public void testClientCreationPrenomVide() {
        Adresse adresse = new Adresse(123, "Rue de la Paix", 75001, "Paris", "France");
        Vehicule vehicule = new Vehicule("AB-123-CD","marque","modele",2022);
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new Client("Dupont", "", adresse, 1234567890L, "jean.dupont@example.com", "1234567812345678",vehicule);
        });
        assertThat(exception.getMessage()).isEqualTo("Aucun champ ne doit être vide");
    }

    @DisplayName("Test création d'un client avec une adresse nulle")
    @Test
    public void testClientCreationAdresseNull() {
        Vehicule vehicule = new Vehicule("AB-123-CD","marque","modele",2022);
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new Client("Dupont", "Jean", null, 1234567890L, "jean.dupont@example.com", "1234567812345678",vehicule);
        });
        assertThat(exception.getMessage()).isEqualTo("Aucun champ ne doit être vide");
    }

    @DisplayName("Test création d'un client avec un email vide")
    @Test
    public void testClientCreationEmailVide() {
        Adresse adresse = new Adresse(123, "Rue de la Paix", 75001, "Paris", "France");
        Vehicule vehicule = new Vehicule("AB-123-CD","marque","modele",2022);
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new Client("Dupont", "Jean", adresse, 1234567890L, "", "1234567812345678",vehicule);
        });
        assertThat(exception.getMessage()).isEqualTo("Aucun champ ne doit être vide");
    }

    @DisplayName("Test création d'un client avec un numéro de carte de débit vide")
    @Test
    public void testClientCreationCBVide() {
        Adresse adresse = new Adresse(123, "Rue de la Paix", 75001, "Paris", "France");
        Vehicule vehicule = new Vehicule("AB-123-CD","marque","modele",2022);
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new Client("Dupont", "Jean", adresse, 1234567890L, "jean.dupont@example.com", "",vehicule);
        });
        assertThat(exception.getMessage()).isEqualTo("Aucun champ ne doit être vide");
    }

    @DisplayName("Test création d'un client avec un numéro de téléphone invalide")
    @Test
    public void testClientCreationNumeroTelephoneInvalide() {
        Adresse adresse = new Adresse(123, "Rue de la Paix", 75001, "Paris", "France");
        Vehicule vehicule = new Vehicule("AB-123-CD","marque","modele",2022);
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new Client("Dupont", "Jean", adresse, 123456789L, "jean.dupont@example.com", "1234567812345678",vehicule);
        });
        assertThat(exception.getMessage()).isEqualTo("Le numéro de téléphone doit contenir 10 chiffres");
    }

    @DisplayName("Test création d'un client avec un email invalide")
    @Test
    public void testClientEmailInvalide() {
        Adresse adresse = new Adresse(123, "Rue de la Paix", 75001, "Paris", "France");
        Vehicule vehicule = new Vehicule("AB-123-CD","marque","modele",2022);
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new Client("Dupont", "Jean", adresse, 1234567890L, "jean.dupont", "1234567812345678",vehicule);
        });
        assertThat(exception.getMessage()).isEqualTo("Adresse mail invalide");
    }

}

