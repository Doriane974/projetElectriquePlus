package org.m1.electriquePlus;

import java.util.ArrayList;
import java.util.List;

import static java.lang.Math.abs;


public class Client {

	private String nom;
	private String prenom;
	private Adresse adresse;
	private long numeroTelephone; //long car il est plus grand que int et on a besoin d'un int avec 10 chiffres
	private String email;
	private String numeroCarteDebit;

	public Client(String nom, String prenom, Adresse adresse, long numeroTelephone, String email, String numeroCarteDebit) {
		if (nom == null || nom.isEmpty() || prenom == null || prenom.isEmpty() || adresse == null ||
				email == null || email.isEmpty() || numeroCarteDebit == null || numeroCarteDebit.isEmpty()) {
			throw new IllegalArgumentException("Aucun champ ne doit être vide");
		}

		if (String.valueOf(numeroTelephone).length() != 10) {
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

	public long getNumeroTelephone() {
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
				", adresse=" + adresse +
				", numeroTelephone=" + numeroTelephone +
				", email='" + email + '\'' +
				", numeroCarteDebit='" + numeroCarteDebit + '\'' +
				'}';
	}
}