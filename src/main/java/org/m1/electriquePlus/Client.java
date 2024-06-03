package org.m1.electriquePlus;

import java.util.Objects;



public class Client {

	private int Id;
	private String nom;
	private String prenom;
	private Adresse adresse;
	private String numeroTelephone;
	//on ne peux pas mettre de int avec 10 chiffres
	//on ne peux pas mettre de long car cela ne prend pas en compte le 0 de début
	private String email;
	private String numeroCarteDebit;
	private Vehicule vehicule;

	public Client(String nom, String prenom, Adresse adresse, String numeroTelephone, String email, String numeroCarteDebit, Vehicule vehicule) {
		if (nom == null || nom.isEmpty() || prenom == null || prenom.isEmpty() || adresse == null ||
				email == null || email.isEmpty() || numeroCarteDebit == null || numeroCarteDebit.isEmpty() ||
				numeroTelephone == null || numeroTelephone.isEmpty()) {
			throw new IllegalArgumentException("Aucun champ ne doit être vide");
		}

		if (!numeroTelephone.matches("\\d{10}")) {
			throw new IllegalArgumentException("Le numéro de téléphone doit contenir 10 chiffres");
		}

		if (!email.matches("^[A-Za-z0-9+_.-]+@(.+)$")) {
			throw new IllegalArgumentException("Adresse mail invalide");
		}

		this.nom = nom;
		this.prenom = prenom;
		this.adresse = adresse;
		this.numeroTelephone = numeroTelephone;
		this.email = email;
		this.numeroCarteDebit = numeroCarteDebit;
		this.vehicule = vehicule;
	}

	@Override
	public boolean equals(Object o) {//TODO : a tester
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Client client = (Client) o;
		return nom.equals(client.nom) &&
				Objects.equals(prenom, client.prenom) &&
				Objects.equals(adresse, client.adresse) &&
				Objects.equals(numeroTelephone, client.numeroTelephone) &&
				Objects.equals(email, client.email) &&
				Objects.equals(numeroCarteDebit, client.numeroCarteDebit) &&
				Objects.equals(vehicule, client.vehicule);
	}

	public String getNom() {
		return nom;
	}

	public String getPrenom() {
		return prenom;
	}

	public Adresse getAdresse() {
		return adresse;
	}

	public String getNumeroTelephone() {
		return numeroTelephone;
	}

	public String getEmail() {
		return email;
	}

	public String getNumeroCarteDebit() {
		return numeroCarteDebit;
	}

	@Override
	public String toString() {
		return "Client{" +
				"nom='" + nom + '\'' +
				", prenom='" + prenom + '\'' +
				", adresse=" + adresse.toString() +
				", numeroTelephone=" + numeroTelephone +
				", email='" + email + '\'' +
				", numeroCarteDebit='" + numeroCarteDebit + '\'' +
				'}';
	}
	
		public Vehicule getVehicule() {
			return this.vehicule;
		}


}