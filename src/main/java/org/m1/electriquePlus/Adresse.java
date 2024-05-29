package org.m1.electriquePlus;

public class Adresse {

    private int numeroHabitation;
    private String nomRue;
    private int codePostal;
    private String nomVille;
    private String nomPays;

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



    public int getNumeroHabitation() {
        return numeroHabitation;
    }

    public String getNomPays() {
        return nomPays;
    }

    public String getNomRue() {
        return nomRue;
    }

    public int getCodePostal() {
        return codePostal;
    }

    public String getNomVille() {
        return nomVille;
    }

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
