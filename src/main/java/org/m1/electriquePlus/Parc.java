package org.m1.electriquePlus;

import java.util.ArrayList;

public class Parc {
    ArrayList<Borne> bornes ;

    public Parc() {
    }

    public Parc(ArrayList<Borne> bornes) {
        this.bornes = bornes;
    }

    public ArrayList<Borne> getBornes() {
        return bornes;
    }

    public void setBornes(ArrayList<Borne> bornes) {
        this.bornes = bornes;
    }

    public void addBorne(Borne borneAAjouter){ //TODO a tester
        this.getBornes().add(borneAAjouter);
    }

    public void getDispBornes(String date, String hour){
        //ArrayList<char> dispos = new ArrayList<char>();
        for(Borne b : this.getBornes()){
            b.checkDisponibilites(date, hour);
        }
    }
}
