package org.m1.electriquePlus;

import java.io.IOException;

import static org.m1.electriquePlus.FileManagement.*;

/**
 * La classe {@code Borne} représente une borne de recharge pour véhicules électriques.
 * Elle permet de gérer les disponibilités et le statut de la borne.
 */
public class Borne {

    /**
     * Numéro de la borne.
     */
    private int numero;

    /**
     * Nom du fichier contenant l'emploi du temps de la borne.
     */
    private String timetableFileName;

    /**
     * Constructeur de la classe Borne.
     * Initialise la borne avec un numéro et génère un fichier d'emploi du temps pour la borne.
     *
     * @param numero Numéro de la borne.
     */
    public Borne(int numero) {
        this.numero = numero;
        this.timetableFileName = "timeTableBorne" + numero;
        try {
            generateFileBorne(timetableFileName);
        } catch (IOException e) {
            System.out.println("Erreur dans la génération de l'emploi du temps de la borne " + numero + " : " + e.getMessage());
        }
    }

    /**
     * Obtient le numéro de la borne.
     *
     * @return Numéro de la borne.
     */
    public int getNumero() {
        return numero;
    }

    /**
     * Définit le numéro de la borne.
     *
     * @param numero Numéro de la borne.
     */
    public void setNumero(int numero) {
        this.numero = numero;
    }

    /**
     * Vérifie les disponibilités de la borne à une date et une heure données.
     * Retourne un caractère indiquant le statut de la borne :
     * 'D' pour disponible, 'R' pour réservé, 'I' pour indisponible, 'O' pour occupé.
     *
     * @param date Date à vérifier (au format "yyyy-MM-dd").
     * @param hour Heure à vérifier (au format "HH:mm").
     * @return Caractère indiquant le statut de la borne ('D', 'R', 'I', 'O').
     */
    public char checkDisponibilites(String date, String hour) {
        char dispos = '_';
        try {
            dispos = checkStatus(date, hour, this.timetableFileName);
            String status = "";
            switch (dispos) {
                case 'D':
                    status = "Disponible";
                    break;
                case 'R':
                    status = "Réservé";
                    break;
                case 'I':
                    status = "Indisponible";
                    break;
                case 'O':
                    status = "Occupé";
                    break;
                default:
                    System.out.println("Erreur dans le fichier " + this.timetableFileName + ", il y a écrit : " + dispos);
                    break;
            }
            if (!status.equals("")) {
                System.out.println("La borne " + this.numero + " est " + status);
            }

        } catch (IOException e) {
            System.out.println("Erreur dans la lecture du fichier " + this.timetableFileName + " : " + e.getMessage());
        }
        return dispos;
    }

    /**
     * Change le statut de la borne à une date et une heure données.
     * Le statut peut être "DISPONIBLE", "INDISPONIBLE", "RESERVE" ou "OCCUPE".
     *
     * @param date   Date à laquelle changer le statut (au format "yyyy-MM-dd").
     * @param hour   Heure à laquelle changer le statut (au format "HH:mm").
     * @param status Nouveau statut de la borne ("DISPONIBLE", "INDISPONIBLE", "RESERVE", "OCCUPE").
     * @return 0 si la modification a réussi, 1 sinon.
     */
    public int changeStatusBorne(String date, String hour, String status) {
        try {
            if (status.equals("DISPONIBLE") || status.equals("INDISPONIBLE") || status.equals("RESERVE") || status.equals("OCCUPE")) {
                changeStatus("" + status.charAt(0), date, hour, this.timetableFileName);
                return 0;
            } else {
                System.out.println("Le statut " + status + " n'est pas reconnu");
                return 1;
            }
        } catch (IOException e) {
            System.out.println("Erreur dans la modification du statut de la borne " + this.numero + " : " + e.getMessage());
        }
        return 1;
    }
}
