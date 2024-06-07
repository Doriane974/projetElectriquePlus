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


	//constructeur sans vehicule
	public Client(String nom, String prenom, Adresse adresse, String numeroTelephone, String email, String numeroCarteDebit) {

		if(verifierClient(nom, prenom, adresse, numeroTelephone, email, numeroCarteDebit)){
			this.nom = nom;
			this.prenom = prenom;
			this.adresse = adresse;
			this.numeroTelephone = numeroTelephone;
			this.email = email;
			this.numeroCarteDebit = numeroCarteDebit;
		}else {
			throw new IllegalArgumentException("Les données saisies ne permettent pas de créer un client");
		}
	}

	//constructeur avec vehicule
	public Client(String nom, String prenom, Adresse adresse, String numeroTelephone, String email, String numeroCarteDebit, Vehicule vehicule) {
		this(nom, prenom, adresse, numeroTelephone, email, numeroCarteDebit);
		if (vehicule != null){
			this.vehicule = vehicule;
		}
	}

	private boolean verifierClient(String nom, String prenom, Adresse adresse, String numeroTelephone, String email, String numeroCarteDebit) {
		if (nom == null || nom.isEmpty() || prenom == null || prenom.isEmpty() || adresse == null ||
				email == null || email.isEmpty() || numeroCarteDebit == null || numeroCarteDebit.isEmpty()) {
			throw new IllegalArgumentException("Aucun champ ne doit être vide");
		}

		if (numeroTelephone.length() != 10 || !numeroTelephone.matches("\\d+")) {
			throw new IllegalArgumentException("Le numéro de téléphone doit contenir 10 chiffres");
		}

		if (!email.matches("^[A-Za-z0-9+_.-]+@(.+)$")) {
			throw new IllegalArgumentException("Adresse mail invalide");
		}
		if (numeroCarteDebit.length() != 16 || !numeroCarteDebit.matches("\\d+")) {
			throw new IllegalArgumentException("Le numéro de carte de débit doit contenir 16 chiffres");
		}


		return true;
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

	public int getId(){return this.Id;}

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

	public void setVehicule(Vehicule vehicule) {
		this.vehicule = vehicule;
	}

	public void setId(int id){this.Id = id;}
	/*
	public void declarerUnVehicule(String plaque, String marque, String modele, int anneeFabrication){
		//Immatriculation plaqueClient = new Immatriculation("plaque");
		Vehicule vehiculeClient = new Vehicule(plaque, marque, modele, anneeFabrication);
		this.vehicule = vehiculeClient;
	}*/
}