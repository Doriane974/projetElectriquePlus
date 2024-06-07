package org.m1.electriquePlus;

public class Gestionnaire {
    private int Id;
    private String nom;
    private String prenom;

    public Gestionnaire(String nom, String prenom) {
        if (nom == null || nom.isEmpty() || prenom == null || prenom.isEmpty()) {
            throw new IllegalArgumentException("Le nom et le prénom ne doivent pas être vides");
        }
        this.nom = nom;
        this.prenom = prenom;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        this.Id = id;
    }

    public String getNom() {
        return nom;
    }

    public String getPrenom() {
        return prenom;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Gestionnaire that = (Gestionnaire) o;
        return nom.equals(that.nom) && prenom.equals(that.prenom);
    }

    @Override
    public String toString() {
        return "Gestionnaire{" +
                "Id=" + Id +
                ", nom='" + nom + '\'' +
                ", prenom='" + prenom + '\'' +
                '}';
    }
}
