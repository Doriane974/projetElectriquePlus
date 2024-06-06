package org.m1.electriquePlus;


public class Immatriculation {

    int id;
    private String lettresAvant;
    private int chiffres;
    private String lettresApres;

    // Constructeur
    public Immatriculation(String plaque) throws IllegalArgumentException {
        if (!plaque.matches("[A-Z]{2}-\\d{3}-[A-Z]{2}")) {
            throw new IllegalArgumentException("Format de plaque invalide");
        }

        String[] parties = plaque.split("-");
        this.lettresAvant = parties[0];
        this.chiffres = Integer.parseInt(parties[1]);
        this.lettresApres = parties[2];
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Immatriculation plaque = (Immatriculation) o;
        return lettresAvant.equals(plaque.lettresAvant) &&
                lettresApres.equals((plaque.lettresApres)) &&
                chiffres == plaque.chiffres;
    }

    @Override
    public String toString() {
        return lettresAvant + "-" + chiffres + "-" + lettresApres;
    }

    public String getLettresAvant() {
        return lettresAvant;
    }

    public int getChiffres() {
        return chiffres;
    }

    public String getLettresApres() {
        return lettresApres;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}