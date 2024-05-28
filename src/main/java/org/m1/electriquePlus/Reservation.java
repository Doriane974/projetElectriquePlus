package org.m1.electriquePlus;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Reservation {

    private Client client;
    private Vehicule vehicule;
    private Borne borne;
    private LocalDateTime DateTimeDebut;
    private LocalDateTime DateTimeFin;

    // Pour récuperer soit la date ou l'heure sur nos variable LocalDateTime
    DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm");

    public Reservation(Client client, Vehicule vehicule,Borne borne, LocalDateTime DateTimeDebut, LocalDateTime DateTimeFin) {
        if(borne.checkDisponibilites(DateTimeDebut.format(dateFormatter),DateTimeDebut.format(timeFormatter))=='D') {
            if (Duration.between(DateTimeDebut, DateTimeFin).toHours() > 1) {
                throw new IllegalArgumentException("La durée de la réservation ne peut pas excéder 1 heure.");
            }
            this.client = client;
            this.vehicule = vehicule;
            this.borne = borne;
            this.DateTimeDebut = DateTimeDebut;
            this.DateTimeFin = DateTimeFin;
            informerNumeroBorne();
        }
        else
            throw new IllegalArgumentException("Reservation non disponible");
    }

    private int informerNumeroBorne() {
        return this.borne.getNumero();
    }

    /**
     * Retourne True si la Plaque de la voiture dans la reservation est la meme que la plaque de la voiture dans client
     * sinon retourne False
     * @param vehicule
     * @param client
     * @return Boolean
     */
    private boolean verificationImmatriculation(Vehicule vehicule, Client client) {
        return client.getVehicule().getPlaque().toString().equals(vehicule.getPlaque().toString());
    }
}