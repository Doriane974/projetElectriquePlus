package org.m1.electriquePlus;

import java.util.Objects;

public class Vehicule {

    int id;
    private Immatriculation plaque;
    private String marque;
    private String modele;
    private int anneeFabrication;


    // Constructeur
    public Vehicule(String plaque, String marque, String modele, int anneeFabrication) {
        this.plaque = new Immatriculation(plaque);
        this.marque = marque;
        this.modele = modele;
        this.anneeFabrication = anneeFabrication;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Vehicule vehicule = (Vehicule) o;
        return anneeFabrication == vehicule.anneeFabrication &&
                Objects.equals(plaque, vehicule.plaque) &&
                Objects.equals(marque, vehicule.marque) &&
                Objects.equals(modele, vehicule.modele);
    }
    public Immatriculation getPlaque() {
        return plaque;
    }

    public String getMarque() {
        return marque;
    }

    public String getModele() {
        return modele;
    }

    public int getAnneeFabrication() {
        return anneeFabrication;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}