package org.m1.electriquePlus;

/**
 * La classe {@code Immatriculation} représente une plaque d'immatriculation de véhicule.
 * Elle stocke les parties de la plaque (lettres avant, chiffres, lettres après) et fournit des méthodes pour les récupérer et les manipuler.
 */
public class Immatriculation {

    /**
     * L'identifiant de l'immatriculation.
     */
    int id;
    /**
     * Les lettres avant de la plaque d'immatriculation.
     */
    private String lettresAvant;
    /**
     * Les chiffres de la plaque d'immatriculation.
     */
    private int chiffres;
    /**
     * Les lettres après de la plaque d'immatriculation.
     */
    private String lettresApres;

    /**
     * Construit un objet {@code Immatriculation} à partir d'une chaîne de caractères représentant une plaque d'immatriculation.
     *
     * @param plaque La chaîne de caractères représentant la plaque d'immatriculation.
     * @throws IllegalArgumentException Si le format de la plaque est invalide.
     */
    public Immatriculation(String plaque) throws IllegalArgumentException {
        if (!plaque.matches("[A-Z]{2}-\\d{3}-[A-Z]{2}")) {
            throw new IllegalArgumentException("Format de plaque invalide");
        }

        String[] parties = plaque.split("-");
        this.lettresAvant = parties[0];
        this.chiffres = Integer.parseInt(parties[1]);
        this.lettresApres = parties[2];
    }

    /**
     * Redéfinition de la méthode equals pour comparer deux plaques d'immatriculation.
     *
     * @param o L'objet à comparer avec cette plaque d'immatriculation.
     * @return true si les plaques d'immatriculation sont équivalentes, sinon false.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Immatriculation plaque = (Immatriculation) o;
        return lettresAvant.equals(plaque.lettresAvant) &&
                lettresApres.equals((plaque.lettresApres)) &&
                chiffres == plaque.chiffres;
    }

    /**
     * Redéfinition de la méthode toString pour obtenir une représentation textuelle de la plaque d'immatriculation.
     *
     * @return La représentation textuelle de la plaque d'immatriculation.
     */
    @Override
    public String toString() {
        return lettresAvant + "-" + chiffres + "-" + lettresApres;
    }

    /**
     * Récupère les lettres avant de la plaque d'immatriculation.
     *
     * @return Les lettres avant de la plaque d'immatriculation.
     */
    public String getLettresAvant() {
        return lettresAvant;
    }

    /**
     * Récupère les chiffres de la plaque d'immatriculation.
     *
     * @return Les chiffres de la plaque d'immatriculation.
     */
    public int getChiffres() {
        return chiffres;
    }

    /**
     * Récupère les lettres après de la plaque d'immatriculation.
     *
     * @return Les lettres après de la plaque d'immatriculation.
     */
    public String getLettresApres() {
        return lettresApres;
    }

    /**
     * Récupère l'identifiant de l'immatriculation.
     *
     * @return L'identifiant de l'immatriculation.
     */
    public int getId() {
        return id;
    }

    /**
     * Modifie l'identifiant de l'immatriculation.
     *
     * @param id Le nouvel identifiant de l'immatriculation.
     */
    public void setId(int id) {
        this.id = id;
    }
}