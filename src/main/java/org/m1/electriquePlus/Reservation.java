package org.m1.electriquePlus;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Reservation {

    private int Id;
    private Client client;
    private Vehicule vehicule;
    private Borne borne;
    private LocalDateTime DateTimeDebut;
    private LocalDateTime DateTimeFin;

    // Pour récuperer soit la date ou l'heure sur nos variable LocalDateTime
    DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd/MM");
    DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH");

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
            this.borne.changeStatusBorne(DateTimeDebut.format(dateFormatter),DateTimeDebut.format(timeFormatter),"RESERVE");
            informerNumeroBorne();
        }
        else
            throw new IllegalArgumentException("Reservation non disponible");
    }

    public int informerNumeroBorne() {
        return this.borne.getNumero();
    }

    /**
     * Retourne True si la Plaque de la voiture dans la reservation est la meme que la plaque de la voiture dans client
     * sinon retourne False
     * @param vehicule
     * @param client
     * @return Boolean
     */
    public boolean verificationImmatriculation(Vehicule vehicule, Client client) {
        return client.getVehicule().getPlaque().toString().equals(vehicule.getPlaque().toString());
    }

    public void setID(int id){this.Id = id;}
    public int getId(){return this.Id;}
}