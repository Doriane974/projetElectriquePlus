package org.m1.electriquePlus;

import java.util.ArrayList;

public class Parc {
    ArrayList<Borne> bornes;
    ArrayList<Reservation> reservations;

    public Parc() {
        reservations = new ArrayList<>();
    }

    public Parc(ArrayList<Borne> bornes) {
        new Parc();
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
