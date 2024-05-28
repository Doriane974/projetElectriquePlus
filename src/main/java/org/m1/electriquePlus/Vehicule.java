package org.m1.electriquePlus;

public class Vehicule {

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
}