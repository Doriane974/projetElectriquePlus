package org.m1.electriquePlus;

import java.io.*;
import java.util.ArrayList;

public class Parc {

    ArrayList<Borne> bornes ;
    ArrayList<Client> clients;
    ArrayList<Adresse> adresses;
    ArrayList<Immatriculation> immatriculations;
    ArrayList<Vehicule> vehicules;
    ArrayList<Reservation> reservations;

    private String filenameClients;
    private String filenameAdresses;
    private String filenameImmatriculations;
    private String filenameVehicules;
    private String filenameReservations;

    public Parc() {
        this.filenameClients = "ClientsSave.txt";
        this.filenameAdresses = "AdressesSave.txt";
        this.filenameImmatriculations = "ImmatriculationsSave.txt";
        this.filenameVehicules = "VehiculesSave.txt";
        this.filenameReservations = "ReservationsSave.txt";
    }

    public Parc(ArrayList<Borne> bornes) {
        this();
        this.bornes = bornes;
   }

    public ArrayList<Borne> getBornes() {
        return bornes;
    }

    public void setBornes(ArrayList<Borne> bornes) {
        this.bornes = bornes;
    }

    public void addBorne(Borne borneAAjouter){
        this.getBornes().add(borneAAjouter);
    }

    public void ajouterReservation(Reservation reservation){
        this.reservations.add(reservation);
    }

    public ArrayList<Borne> getDispBornes(String date, String hour){
        //ArrayList<char> dispos = new ArrayList<char>();
        ArrayList<Borne> dispo = new ArrayList<>();
        for(Borne b : this.getBornes()){
            if(b.checkDisponibilites(date, hour)=='D')
                dispo.add(b);
        }
        return dispo;
    }
}
