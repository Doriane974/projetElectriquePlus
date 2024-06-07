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
        Adresse adresse = new Adresse(123, "Rue de la Paix", "75001", "Paris", "France");
        Vehicule vehicule = new Vehicule("AB-123-CD","marque","modele",2022);
        Client client = new Client("Dupont", "Jean", adresse, "1234567890", "jean.dupont@example.com", "1234567812345678", vehicule);
        assertThat(client.getNom()).isEqualTo("Dupont");
        assertThat(client.getPrenom()).isEqualTo("Jean");
        assertThat(client.getAdresse()).isEqualTo(adresse);
        assertThat(client.getNumeroTelephone()).isEqualTo("1234567890");
        assertThat(client.getEmail()).isEqualTo("jean.dupont@example.com");
        assertThat(client.getNumeroCarteDebit()).isEqualTo("1234567812345678");
    }
    public void testEquals_SameValues() {
        Adresse adresse1 = new Adresse(123, "Rue de la Paix", "75001", "Paris", "France");
        Vehicule vehicule1 = new Vehicule("AB-123-CD", "Toyota", "Corolla", 2010);

        Client client1 = new Client("Dupont", "Jean", adresse1, "1234567890", "jean.dupont@example.com", "1234567812345678", vehicule1);
        Client client2 = new Client("Dupont", "Jean", adresse1, "1234567890", "jean.dupont@example.com", "1234567812345678", vehicule1);

        assertThat(client1).isEqualTo(client2);
    }

    @Test
    public void testEquals_DifferentValues() {
        Adresse adresse1 = new Adresse(123, "Rue de la Paix", "75001", "Paris", "France");
        Adresse adresse2 = new Adresse(124, "Avenue des Champs-Élysées", "75008", "Paris", "France");

        Vehicule vehicule1 = new Vehicule("AB-123-CD", "Toyota", "Corolla", 2010);
        Vehicule vehicule2 = new Vehicule("XY-456-ZY", "Honda", "Civic", 2015);

        Client client1 = new Client("Dupont", "Jean", adresse1, "1234567890", "jean.dupont@example.com", "1234567812345678", vehicule1);
        Client client2 = new Client("Bedier", "Janick", adresse2, "0987654321", "janick.bedier@example.com", "8765432187654321", vehicule2);

        assertThat(client1).isNotEqualTo(client2);
    }

    @DisplayName("Test création d'un client avec un nom vide")
    @Test
    public void testClientCreationNomVide() {
        Adresse adresse = new Adresse(123, "Rue de la Paix", "75001", "Paris", "France");
        Vehicule vehicule = new Vehicule("AB-123-CD","marque","modele",2022);
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new Client("", "Jean", adresse, "1234567890", "jean.dupont@example.com", "1234567812345678", vehicule);
        });
        assertThat(exception.getMessage()).isEqualTo("Aucun champ ne doit être vide");
    }

    @DisplayName("Test création d'un client avec un prénom vide")
    @Test
    public void testClientCreationPrenomVide() {
        Adresse adresse = new Adresse(123, "Rue de la Paix", "75001", "Paris", "France");
        Vehicule vehicule = new Vehicule("AB-123-CD","marque","modele",2022);
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new Client("Dupont", "", adresse, "1234567890", "jean.dupont@example.com", "1234567812345678", vehicule);
        });
        assertThat(exception.getMessage()).isEqualTo("Aucun champ ne doit être vide");
    }

    @DisplayName("Test création d'un client avec une adresse nulle")
    @Test
    public void testClientCreationAdresseNull() {
        Vehicule vehicule = new Vehicule("AB-123-CD","marque","modele",2022);
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new Client("Dupont", "Jean", null, "1234567890", "jean.dupont@example.com", "1234567812345678", vehicule);
        });
        assertThat(exception.getMessage()).isEqualTo("Aucun champ ne doit être vide");
    }

    @DisplayName("Test création d'un client avec un email vide")
    @Test
    public void testClientCreationEmailVide() {
        Adresse adresse = new Adresse(123, "Rue de la Paix", "75001", "Paris", "France");
        Vehicule vehicule = new Vehicule("AB-123-CD","marque","modele",2022);
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new Client("Dupont", "Jean", adresse, "1234567890", "", "1234567812345678", vehicule);
        });
        assertThat(exception.getMessage()).isEqualTo("Aucun champ ne doit être vide");
    }

    @DisplayName("Test création d'un client avec un numéro de carte de débit vide")
    @Test
    public void testClientCreationCBVide() {
        Adresse adresse = new Adresse(123, "Rue de la Paix", "75001", "Paris", "France");
        Vehicule vehicule = new Vehicule("AB-123-CD","marque","modele",2022);
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new Client("Dupont", "Jean", adresse, "1234567890", "jean.dupont@example.com", "", vehicule);
        });
        assertThat(exception.getMessage()).isEqualTo("Aucun champ ne doit être vide");
    }

    @DisplayName("Test création d'un client avec un numéro de téléphone invalide")
    @Test
    public void testClientCreationNumeroTelephoneInvalide() {
        Adresse adresse = new Adresse(123, "Rue de la Paix", "75001", "Paris", "France");
        Vehicule vehicule = new Vehicule("AB-123-CD","marque","modele",2022);
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new Client("Dupont", "Jean", adresse, "123456789", "jean.dupont@example.com", "1234567812345678", vehicule);
        });
        assertThat(exception.getMessage()).isEqualTo("Le numéro de téléphone doit contenir 10 chiffres");
    }

    @DisplayName("Test création d'un client avec un email invalide")
    @Test
    public void testClientEmailInvalide() {
        Adresse adresse = new Adresse(123, "Rue de la Paix", "75001", "Paris", "France");
        Vehicule vehicule = new Vehicule("AB-123-CD","marque","modele",2022);
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new Client("Dupont", "Jean", adresse, "1234567890", "jean.dupont", "1234567812345678", vehicule);
        });
        assertThat(exception.getMessage()).isEqualTo("Adresse mail invalide");
    }

    @DisplayName("Test création d'un client avec un numéro de téléphone invalide (moins de 10 chiffres)")
    @Test
    public void testClientTelephoneTropPetit() {
        Adresse adresse = new Adresse(123, "Rue de la Paix", "75001", "Paris", "France");
        Vehicule vehicule = new Vehicule("AB-123-CD","marque","modele",2022);

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new Client("Dupont", "Jean", adresse, "060102030", "jean.dupont@example.com", "1234567812345678", vehicule);
        });
        assertThat(exception.getMessage()).isEqualTo("Le numéro de téléphone doit contenir 10 chiffres");
    }

    @DisplayName("Test création d'un client avec un numéro de téléphone invalide (plus de 10 chiffres)")
    @Test
    public void testClientTelephoneTropLong() {
        Adresse adresse = new Adresse(123, "Rue de la Paix", "75001", "Paris", "France");
        Vehicule vehicule = new Vehicule("AB-123-CD","marque","modele",2022);

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new Client("Dupont", "Jean", adresse, "06010203045", "jean.dupont@example.com", "1234567812345678", vehicule);
        });
        assertThat(exception.getMessage()).isEqualTo("Le numéro de téléphone doit contenir 10 chiffres");
    }

    @DisplayName("Test création d'un client avec un numéro de téléphone invalide (non numérique)")
    @Test
    public void testClientTelephoneAvecLettre() {
        Adresse adresse = new Adresse(123, "Rue de la Paix", "75001", "Paris", "France");
        Vehicule vehicule = new Vehicule("AB-123-CD","marque","modele",2022);

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new Client("Dupont", "Jean", adresse, "06010A0304", "jean.dupont@example.com", "1234567812345678", vehicule);
        });
        assertThat(exception.getMessage()).isEqualTo("Le numéro de téléphone doit contenir 10 chiffres");
    }

    @Test
    @DisplayName("Création du client avec nom contenant un tiret fonctionne correctement")
    void creationClientNomAvecTiret() {
        Adresse adresse = new Adresse(10, "Rue de l'exemple", "01234", "Ville", "Pays");
        Client client = new Client("Nom-Test", "Prenom", adresse, "0123456789", "email@example.com", "1234567890123456");
        assertThat(client.getNom()).isEqualTo("Nom-Test");
    }

    @Test
    @DisplayName("Création du client avec code postal commençant par 0 fonctionne correctement")
    void creationClientCodePostalCommencePar0() {
        Adresse adresse = new Adresse(10, "Rue de l'exemple", "01234", "Ville", "Pays");
        Client client = new Client("Nom", "Prenom", adresse, "0123456789", "email@example.com", "1234567890123456");
        assertThat(client.getAdresse().getCodePostal()).isEqualTo("01234");
    }

    @Test
    @DisplayName("Création du client avec email valide")
    void creationClientEmailValide() {
        Adresse adresse = new Adresse(10, "Rue de l'exemple", "12345", "Ville", "Pays");
        Client client = new Client("Nom", "Prenom", adresse, "0123456789", "email@example.com", "1234567890123456");
        assertThat(client.getEmail()).isEqualTo("email@example.com");
    }

    @Test
    @DisplayName("Création du client avec email invalide lève une exception")
    void creationClientEmailInvalide() {
        Adresse adresse = new Adresse(10, "Rue de l'exemple", "12345", "Ville", "Pays");
        assertThatThrownBy(() -> new Client("Nom", "Prenom", adresse, "0123456789", "email-invalide", "1234567890123456"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("Adresse mail invalide");
    }

    @Test
    @DisplayName("Création du client avec numéro de téléphone correct")
    void creationClientNumeroTelephoneCorrect() {
        Adresse adresse = new Adresse(10, "Rue de l'exemple", "12345", "Ville", "Pays");
        Client client = new Client("Nom", "Prenom", adresse, "0123456789", "email@example.com", "1234567890123456");
        assertThat(client.getNumeroTelephone()).isEqualTo("0123456789");
    }

    @Test
    @DisplayName("Création du client avec numéro de téléphone trop court lève une exception")
    void creationClientNumeroTelephoneTropCourt() {
        Adresse adresse = new Adresse(10, "Rue de l'exemple", "12345", "Ville", "Pays");
        assertThatThrownBy(() -> new Client("Nom", "Prenom", adresse, "01234", "email@example.com", "1234567890123456"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("Le numéro de téléphone doit contenir 10 chiffres");
    }

    @Test
    @DisplayName("Création du client avec numéro de téléphone trop long lève une exception")
    void creationClientNumeroTelephoneTropLong() {
        Adresse adresse = new Adresse(10, "Rue de l'exemple", "12345", "Ville", "Pays");
        assertThatThrownBy(() -> new Client("Nom", "Prenom", adresse, "012345678901", "email@example.com", "1234567890123456"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("Le numéro de téléphone doit contenir 10 chiffres");
    }

    @Test
    @DisplayName("Création du client avec nom vide lève une exception")
    void creationClientNomVide() {
        Adresse adresse = new Adresse(10, "Rue de l'exemple", "12345", "Ville", "Pays");
        assertThatThrownBy(() -> new Client("", "Prenom", adresse, "0123456789", "email@example.com", "1234567890123456"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("Aucun champ ne doit être vide");
    }

    @Test
    @DisplayName("Création du client avec prénom vide lève une exception")
    void creationClientPrenomVide() {
        Adresse adresse = new Adresse(10, "Rue de l'exemple", "12345", "Ville", "Pays");
        assertThatThrownBy(() -> new Client("Nom", "", adresse, "0123456789", "email@example.com", "1234567890123456"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("Aucun champ ne doit être vide");
    }

    @Test
    @DisplayName("Création du client avec adresse null lève une exception")
    void creationClientAdresseNull() {
        assertThatThrownBy(() -> new Client("Nom", "Prenom", null, "0123456789", "email@example.com", "1234567890123456"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("Aucun champ ne doit être vide");
    }

    @Test
    @DisplayName("Création du client avec email vide lève une exception")
    void creationClientEmailVide() {
        Adresse adresse = new Adresse(10, "Rue de l'exemple", "12345", "Ville", "Pays");
        assertThatThrownBy(() -> new Client("Nom", "Prenom", adresse, "0123456789", "", "1234567890123456"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("Aucun champ ne doit être vide");
    }

    @Test
    @DisplayName("Création du client avec numéro de carte de débit vide lève une exception")
    void creationClientNumeroCarteDebitVide() {
        Adresse adresse = new Adresse(10, "Rue de l'exemple", "12345", "Ville", "Pays");
        assertThatThrownBy(() -> new Client("Nom", "Prenom", adresse, "0123456789", "email@example.com", ""))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("Aucun champ ne doit être vide");
    }

    @Test
    @DisplayName("Création du client avec numéro de carte de débit correct")
    void creationClientNumeroCarteDebitCorrect() {
        Adresse adresse = new Adresse(10, "Rue de l'exemple", "12345", "Ville", "Pays");
        Client client = new Client("Nom", "Prenom", adresse, "0123456789", "email@example.com", "1234567890123456");
        assertThat(client.getNumeroCarteDebit()).isEqualTo("1234567890123456");
    }

    @Test
    @DisplayName("Création du client avec nom composé fonctionne correctement")
    void creationClientNomCompose() {
        Adresse adresse = new Adresse(10, "Rue de l'exemple", "12345", "Ville", "Pays");
        Client client = new Client("Nom-De-Test", "Prenom", adresse, "0123456789", "email@example.com", "1234567890123456");
        assertThat(client.getNom()).isEqualTo("Nom-De-Test");
    }

    @Test
    @DisplayName("Création du client avec nom avec apostrophe fonctionne correctement")
    void creationClientNomAvecApostrophe() {
        Adresse adresse = new Adresse(10, "Rue de l'exemple", "12345", "Ville", "Pays");
        Client client = new Client("Nom'De'Test", "Prenom", adresse, "0123456789", "email@example.com", "1234567890123456");
        assertThat(client.getNom()).isEqualTo("Nom'De'Test");
    }

    @Test
    @DisplayName("Création du client avec numéro de carte de débit de longueur incorrecte lève une exception")
    void creationClientNumeroCarteDebitLongueurIncorrecte() {
        Adresse adresse = new Adresse(10, "Rue de l'exemple", "12345", "Ville", "Pays");
        assertThatThrownBy(() -> new Client("Nom", "Prenom", adresse, "0123456789", "email@example.com", "12345678"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("Le numéro de carte de débit doit contenir 16 chiffres");
    }

    @Test
    @DisplayName("Création du client avec nom contenant des espaces fonctionne correctement")
    void creationClientNomAvecEspaces() {
        Adresse adresse = new Adresse(10, "Rue de l'exemple", "12345", "Ville", "Pays");
        Client client = new Client("Nom De Test", "Prenom", adresse, "0123456789", "email@example.com", "1234567890123456");
        assertThat(client.getNom()).isEqualTo("Nom De Test");
    }

    @Test
    @DisplayName("Création du client avec prénom contenant des espaces fonctionne correctement")
    void creationClientPrenomAvecEspaces() {
        Adresse adresse = new Adresse(10, "Rue de l'exemple", "12345", "Ville", "Pays");
        Client client = new Client("Nom", "Prenom De Test", adresse, "0123456789", "email@example.com", "1234567890123456");
        assertThat(client.getPrenom()).isEqualTo("Prenom De Test");
    }

    @Test
    @DisplayName("Création du client avec email contenant un point fonctionne correctement")
    void creationClientEmailAvecPoint() {
        Adresse adresse = new Adresse(10, "Rue de l'exemple", "12345", "Ville", "Pays");
        Client client = new Client("Nom", "Prenom", adresse, "0123456789", "email.test@example.com", "1234567890123456");
        assertThat(client.getEmail()).isEqualTo("email.test@example.com");
    }

    @Test
    @DisplayName("Création du client avec email contenant un tiret fonctionne correctement")
    void creationClientEmailAvecTiret() {
        Adresse adresse = new Adresse(10, "Rue de l'exemple", "12345", "Ville", "Pays");
        Client client = new Client("Nom", "Prenom", adresse, "0123456789", "email-test@example.com", "1234567890123456");
        assertThat(client.getEmail()).isEqualTo("email-test@example.com");
    }

    @Test
    @DisplayName("Création du client avec email contenant un underscore fonctionne correctement")
    void creationClientEmailAvecUnderscore() {
        Adresse adresse = new Adresse(10, "Rue de l'exemple", "12345", "Ville", "Pays");
        Client client = new Client("Nom", "Prenom", adresse, "0123456789", "email_test@example.com", "1234567890123456");
        assertThat(client.getEmail()).isEqualTo("email_test@example.com");
    }

    @Test
    @DisplayName("Création du client avec un email invalide ne respectant pas le format standard lève une exception")
    void creationClientEmailFormatInvalide() {
        Adresse adresse = new Adresse(10, "Rue de l'exemple", "12345", "Ville", "Pays");
        assertThatThrownBy(() -> new Client("Nom", "Prenom", adresse, "0123456789", "email.com", "1234567890123456"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("Adresse mail invalide");
    }

    @Test
    @DisplayName("Modification de l'email avec une adresse valide")
    public void testSetEmailValide() {
        Client client = createValidClient();
        client.setEmail("nouvel.email@example.com");
        assertThat(client.getEmail()).isEqualTo("nouvel.email@example.com");
    }

    @Test
    @DisplayName("Modification de l'email avec une adresse invalide lève une exception")
    public void testSetEmailInvalide() {
        Client client = createValidClient();
        assertThatThrownBy(() -> client.setEmail("email-invalid"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("Adresse mail invalide");
    }

    @Test
    @DisplayName("Modification du numéro de téléphone avec un numéro valide")
    public void testSetNumeroTelephoneValide() {
        Client client = createValidClient();
        client.setNumeroTelephone("0987654321");
        assertThat(client.getNumeroTelephone()).isEqualTo("0987654321");
    }

    @Test
    @DisplayName("Modification du numéro de téléphone avec un numéro invalide lève une exception")
    public void testSetNumeroTelephoneInvalide() {
        Client client = createValidClient();
        assertThatThrownBy(() -> client.setNumeroTelephone("09876"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("Le numéro de téléphone doit contenir 10 chiffres");
    }

    @Test
    @DisplayName("Modification du numéro de carte de débit avec un numéro valide")
    public void testSetNumeroCarteDebitValide() {
        Client client = createValidClient();
        client.setNumeroCarteDebit("8765432187654321");
        assertThat(client.getNumeroCarteDebit()).isEqualTo("8765432187654321");
    }

    @Test
    @DisplayName("Modification du numéro de carte de débit avec un numéro invalide lève une exception")
    public void testSetNumeroCarteDebitInvalide() {
        Client client = createValidClient();
        assertThatThrownBy(() -> client.setNumeroCarteDebit("1234"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("Le numéro de carte de debit n'est pas valide");
    }

    @Test
    @DisplayName("Modification de l'adresse avec une adresse valide")
    public void testSetAdresseValide() {
        Client client = createValidClient();
        Adresse nouvelleAdresse = new Adresse(456, "Nouvelle Rue", "75002", "Paris", "France");
        client.setAdresse(nouvelleAdresse);
        assertThat(client.getAdresse()).isEqualTo(nouvelleAdresse);
    }

    // Méthode utilitaire pour créer un client valide
    private Client createValidClient() {
        Adresse adresse = new Adresse(123, "Rue de la Paix", "75001", "Paris", "France");
        Vehicule vehicule = new Vehicule("AB-123-CD", "marque", "modele", 2022);
        return new Client("Dupont", "Jean", adresse, "1234567890", "jean.dupont@example.com", "1234567812345678", vehicule);
    }
}

