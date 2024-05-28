package org.m1.electriquePlus;

public class Gestionnaire {

        private String nom;
        private String prenom;

        public Gestionnaire(String nom, String prenom) {
            this.nom = nom;
            this.prenom = prenom;
        }

    public String getNom() {
        return nom;
    }

    public String getPrenom() {
        return prenom;
    }
}
