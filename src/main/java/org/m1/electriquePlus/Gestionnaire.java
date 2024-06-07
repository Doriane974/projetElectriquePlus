package org.m1.electriquePlus;

/**
 * La classe {@code Gestionnaire} représente le gestionnaire du parc de rechargement.
 */
public class Gestionnaire {

    /**
     * Le nom du gestionnaire.
     */
    private String nom;

    /**
     * Le prénom du gestionnaire.
     */
    private String prenom;

    /**
     * Construit un objet {@code Gestionnaire} avec le nom et le prénom spécifiés.
     *
     * @param nom    Le nom du gestionnaire.
     * @param prenom Le prénom du gestionnaire.
     */
    public Gestionnaire(String nom, String prenom) {
        this.nom = nom;
        this.prenom = prenom;
    }

    /**
     * Récupère le nom du gestionnaire.
     *
     * @return Le nom du gestionnaire.
     */
    public String getNom() {
        return nom;
    }

    /**
     * Récupère le prénom du gestionnaire.
     *
     * @return Le prénom du gestionnaire.
     */
    public String getPrenom() {
        return prenom;
    }
}
