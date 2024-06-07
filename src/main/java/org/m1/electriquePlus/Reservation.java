package org.m1.electriquePlus;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * La classe {@code Reservation} représente les réservations effectuées pour les bornes du parc de recharge.
 */
public class Reservation {

    /**
     * L'identifiant de la réservation.
     */
    private int Id;

    /**
     * Le client effectuant la réservation.
     */
    private Client client;

    /**
     * Le véhicule associé à la réservation.
     */
    private Vehicule vehicule;

    /**
     * La borne de recharge réservée.
     */
    private Borne borne;

    /**
     * La date et l'heure de début de la réservation.
     */
    private LocalDateTime DateTimeDebut;

    /**
     * La date et l'heure de fin de la réservation.
     */
    private LocalDateTime DateTimeFin;

    /**
     * Formatter pour formater la date.
     */
    DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd/MM");

    /**
     * Formatter pour formater l'heure.
     */
    DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH");

    /**
     * Construit une nouvelle réservation avec les informations fournies.
     *
     * @param client Le client effectuant la réservation.
     * @param vehicule Le véhicule associé à la réservation.
     * @param borne La borne de recharge réservée.
     * @param DateTimeDebut La date et l'heure de début de la réservation.
     * @param DateTimeFin La date et l'heure de fin de la réservation.
     * @throws IllegalArgumentException Si la durée de la réservation excède 1 heure ou si la réservation n'est pas disponible pour la borne.
     */
    public Reservation(Client client, Vehicule vehicule, Borne borne, LocalDateTime DateTimeDebut, LocalDateTime DateTimeFin) throws IllegalArgumentException {
        if (borne.checkDisponibilites(DateTimeDebut.format(dateFormatter), DateTimeDebut.format(timeFormatter)) == 'D') {
            if (Duration.between(DateTimeDebut, DateTimeFin).toHours() > 1) {
                throw new IllegalArgumentException("La durée de la réservation ne peut pas excéder 1 heure.");
            }
            this.client = client;
            this.vehicule = vehicule;
            this.borne = borne;
            this.DateTimeDebut = DateTimeDebut;
            this.DateTimeFin = DateTimeFin;
            this.borne.changeStatusBorne(DateTimeDebut.format(dateFormatter), DateTimeDebut.format(timeFormatter), "RESERVE");
            informerNumeroBorne();
        } else {
            throw new IllegalArgumentException("Reservation non disponible");
        }
    }

    /**
     * Retourne le numéro de la borne de recharge réservée.
     *
     * @return Le numéro de la borne de recharge.
     */
    public int informerNumeroBorne() {
        return this.borne.getNumero();
    }

    /**
     * Vérifie si la plaque d'immatriculation du véhicule dans la réservation correspond à celle du client.
     *
     * @param vehicule Le véhicule à vérifier.
     * @param client Le client pour lequel la vérification est effectuée.
     * @return true si les plaques d'immatriculation correspondent, sinon false.
     */
    public boolean verificationImmatriculation(Vehicule vehicule, Client client) {
        return client.getVehicule().getPlaque().toString().equals(vehicule.getPlaque().toString());
    }

    /**
     * Définit l'identifiant de la réservation.
     *
     * @param id Le nouvel identifiant de la réservation.
     */
    public void setID(int id) {
        this.Id = id;
    }

    /**
     * Retourne l'identifiant de la réservation.
     *
     * @return L'identifiant de la réservation.
     */
    public int getId() {
        return this.Id;
    }
}
