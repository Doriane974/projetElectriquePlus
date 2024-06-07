package org.m1.electriquePlus;

import java.util.Objects;

/**
 * La classe Vehicule représente un véhicule avec une immatriculation, une marque,
 * un modèle et une année de fabrication.
 */
public class Vehicule {

    /**
     * Identifiant unique du véhicule.
     */
    int id;

    /**
     * Immatriculation du véhicule.
     */
    private Immatriculation plaque;

    /**
     * Marque du véhicule.
     */
    private String marque;

    /**
     * Modèle du véhicule.
     */
    private String modele;

    /**
     * Année de fabrication du véhicule.
     */
    private int anneeFabrication;

    /**
     * Constructeur de la classe Vehicule.
     * Initialise un véhicule avec une immatriculation, une marque, un modèle et une année de fabrication.
     *
     * @param plaque           Immatriculation du véhicule.
     * @param marque           Marque du véhicule.
     * @param modele           Modèle du véhicule.
     * @param anneeFabrication Année de fabrication du véhicule.
     */
    public Vehicule(String plaque, String marque, String modele, int anneeFabrication) {
        this.plaque = new Immatriculation(plaque);
        this.marque = marque;
        this.modele = modele;
        this.anneeFabrication = anneeFabrication;
    }

    /**
     * Vérifie si ce véhicule est égal à l'objet spécifié.
     * Deux véhicules sont considérés égaux s'ils ont la même immatriculation, marque,
     * modèle et année de fabrication.
     *
     * @param o l'objet à comparer.
     * @return true si les objets sont égaux, false sinon.
     */
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

    /**
     * Obtient l'immatriculation du véhicule.
     *
     * @return Immatriculation du véhicule.
     */
    public Immatriculation getPlaque() {
        return plaque;
    }

    /**
     * Obtient la marque du véhicule.
     *
     * @return Marque du véhicule.
     */
    public String getMarque() {
        return marque;
    }

    /**
     * Obtient le modèle du véhicule.
     *
     * @return Modèle du véhicule.
     */
    public String getModele() {
        return modele;
    }

    /**
     * Obtient l'année de fabrication du véhicule.
     *
     * @return Année de fabrication du véhicule.
     */
    public int getAnneeFabrication() {
        return anneeFabrication;
    }

    /**
     * Obtient l'identifiant unique du véhicule.
     *
     * @return Identifiant du véhicule.
     */
    public int getId() {
        return id;
    }

    /**
     * Définit l'identifiant unique du véhicule.
     *
     * @param id Identifiant du véhicule.
     */
    public void setId(int id) {
        this.id = id;
    }
}
