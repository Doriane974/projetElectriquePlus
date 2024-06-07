package org.m1.electriquePlus;

import java.util.Objects;

/**
 * La classe {@code Adresse} représente une adresse avec un numéro d'habitation, un nom de rue,
 * un code postal, un nom de ville et un nom de pays.
 */
public class Adresse {

    /**
     * Identifiant unique de l'adresse.
     */
    private int id;

    /**
     * Numéro d'habitation.
     */
    private int numeroHabitation;

    /**
     * Nom de la rue.
     */
    private String nomRue;

    /**
     * Code postal.
     */
    private int codePostal;

    /**
     * Nom de la ville.
     */
    private String nomVille;

    /**
     * Nom du pays.
     */
    private String nomPays;

    /**
     * Constructeur de la classe Adresse.
     * Initialise une adresse avec un numéro d'habitation, un nom de rue, un code postal,
     * un nom de ville et un nom de pays. Des exceptions sont levées pour les paramètres invalides.
     *
     * @param numeroHabitation Numéro d'habitation. Doit être supérieur à 0.
     * @param nomRue           Nom de la rue. Ne doit pas être vide.
     * @param codePostal       Code postal. Doit être un nombre de 5 chiffres.
     * @param nomVille         Nom de la ville. Ne doit pas être vide.
     * @param nomPays          Nom du pays. Ne doit pas être vide.
     * @throws IllegalArgumentException Si un des paramètres est invalide.
     */
    public Adresse(int numeroHabitation, String nomRue, int codePostal, String nomVille, String nomPays) {
        if (numeroHabitation <= 0) {
            throw new IllegalArgumentException("Le numéro d'habitation doit être supérieur à 0");
        }

        if (nomRue == null || nomRue.isEmpty()) {
            throw new IllegalArgumentException("Le nom de rue ne doit pas être vide");
        }

        if (String.valueOf(codePostal).length() != 5) {
            throw new IllegalArgumentException("Le code postal doit contenir 5 chiffres");
        }

        if (nomVille == null || nomVille.isEmpty()) {
            throw new IllegalArgumentException("Le nom de ville ne doit pas être vide");
        }

        if (nomPays == null || nomPays.isEmpty()) {
            throw new IllegalArgumentException("Le pays ne doit pas être vide");
        }

        this.numeroHabitation = numeroHabitation;
        this.nomRue = nomRue;
        this.codePostal = codePostal;
        this.nomVille = nomVille;
        this.nomPays = nomPays;
    }

    /**
     * Vérifie si cette adresse est égale à l'objet spécifié.
     * Deux adresses sont considérées égales si elles ont le même numéro d'habitation,
     * le même code postal, la même rue, la même ville et le même pays.
     *
     * @param o l'objet à comparer.
     * @return true si les objets sont égaux, false sinon.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Adresse adresse = (Adresse) o;
        return numeroHabitation == adresse.numeroHabitation &&
                codePostal == adresse.codePostal &&
                Objects.equals(nomRue, adresse.nomRue) &&
                Objects.equals(nomVille, adresse.nomVille) &&
                Objects.equals(nomPays, adresse.nomPays);
    }

    /**
     * Définit l'identifiant unique de l'adresse.
     *
     * @param id Identifiant unique de l'adresse.
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Obtient l'identifiant unique de l'adresse.
     *
     * @return Identifiant unique de l'adresse.
     */
    public int getId() {
        return id;
    }

    /**
     * Obtient le numéro d'habitation.
     *
     * @return Numéro d'habitation.
     */
    public int getNumeroHabitation() {
        return numeroHabitation;
    }

    /**
     * Obtient le nom de la rue.
     *
     * @return Nom de la rue.
     */
    public String getNomRue() {
        return nomRue;
    }

    /**
     * Obtient le code postal.
     *
     * @return Code postal.
     */
    public int getCodePostal() {
        return codePostal;
    }

    /**
     * Obtient le nom de la ville.
     *
     * @return Nom de la ville.
     */
    public String getNomVille() {
        return nomVille;
    }

    /**
     * Obtient le nom du pays.
     *
     * @return Nom du pays.
     */
    public String getNomPays() {
        return nomPays;
    }

    /**
     * Retourne une chaîne de caractères représentant cette adresse.
     *
     * @return Chaîne de caractères représentant l'adresse.
     */
    @Override
    public String toString() {
        return "Adresse{" +
                "numeroHabitation=" + numeroHabitation +
                ", nomRue='" + nomRue + '\'' +
                ", codePostal=" + codePostal +
                ", nomVille='" + nomVille + '\'' +
                ", nomPays='" + nomPays + '\'' +
                '}';
    }
}
