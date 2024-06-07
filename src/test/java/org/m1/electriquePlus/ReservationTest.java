package org.m1.electriquePlus;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import static org.junit.jupiter.api.Assertions.*;

public class ReservationTest {

    private Client client;
    private Vehicule vehicule;
    private Borne borne;
    private LocalDateTime dateTimeDebut;
    private LocalDateTime dateTimeFin;

    @DisplayName("Test création d'une reservation avec des informations valides")
    @Test
    public void testReservationSuccess() {
        borne = new Borne(1);
        Adresse a = new Adresse(5,"Rue d'une rue","54000","ville","France");
        vehicule = new Vehicule("AA-123-AA","marque","modele",1999);
        client = new Client("test","testeur",a,"6460428820","email@email.email","1234123412341234",vehicule);
        dateTimeDebut = LocalDateTime.of(2024, 06, 28, 10, 0);
        dateTimeFin = LocalDateTime.of(2024, 06, 28, 11, 0);

        Reservation reservation = new Reservation(client, vehicule, borne, dateTimeDebut, dateTimeFin);
        assertNotNull(reservation);
        assertEquals(borne.getNumero(), reservation.informerNumeroBorne());
    }

    @DisplayName("Test création d'une reservation avec des informations pas valides")
    @Test
    public void testReservationEchecDueADuration() {
        borne = new Borne(1);
        Adresse a = new Adresse(5,"Rue d'une rue","54000","ville","France");
        vehicule = new Vehicule("AA-123-AA","marque","modele",1999);
        client = new Client("test","testeur",a,"6460428820","email@email.email","1234123412341234",vehicule);
        dateTimeDebut = LocalDateTime.of(2024, 06, 28, 10, 0);
        dateTimeFin = LocalDateTime.of(2024, 06, 28, 11, 0);
        LocalDateTime dateTimeFinLong = dateTimeDebut.plusHours(2);
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new Reservation(client, vehicule, borne, dateTimeDebut, dateTimeFinLong);
        });
        assertEquals("La durée de la réservation ne peut pas excéder 1 heure.", exception.getMessage());
    }

    @DisplayName("Test création d'une reservation, sur la meme date et heure qu'une autre")
    @Test
    public void testReservationFailureDueToUnavailability() {
        borne = new Borne(1);
        Adresse adresse = new Adresse(5,"Rue d'une rue","54000","ville","France");
        vehicule = new Vehicule("AA-123-AA","marque","modele",2020);
        client = new Client("test","testeur",adresse,"6460428820","email@email.email","1234123412341234",vehicule);

        Adresse adresse2 = new Adresse(7,"Rue d'une rue","54000","ville","France");
        Vehicule vehicule2 = new Vehicule("AA-456-AA","marque","modele",2020);
        Client client2 = new Client("test2","testeur2",adresse2,"1234567890","email2@email.email","1234123412341234",vehicule2);

        dateTimeDebut = LocalDateTime.of(2024, 06, 28, 10, 0);
        dateTimeFin = LocalDateTime.of(2024, 06, 28, 11, 0);
        new Reservation(client, vehicule, borne, dateTimeDebut, dateTimeFin);

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new Reservation(client2, vehicule2, borne, dateTimeDebut, dateTimeFin);
        });
        assertEquals("Reservation non disponible", exception.getMessage());
    }

}
