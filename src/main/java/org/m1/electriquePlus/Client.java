package org.m1.electriquePlus;

import java.util.Objects;

/**
 * La classe {@code Client} représente un client avec des informations personnelles, une adresse,
 * des coordonnées, et un véhicule optionnel.
 */
public class Client {

	/**
	 * Identifiant unique du client.
	 */
	private int Id;

	/**
	 * Nom du client.
	 */
	private String nom;

	/**
	 * Prénom du client.
	 */
	private String prenom;

	/**
	 * Adresse du client.
	 */
	private Adresse adresse;

	/**
	 * Numéro de téléphone du client (doit contenir 10 chiffres).
	 */
	private String numeroTelephone;

	/**
	 * Adresse email du client.
	 */
	private String email;

	/**
	 * Numéro de carte de débit du client.
	 */
	private String numeroCarteDebit;

	/**
	 * Véhicule du client (optionnel).
	 */
	private Vehicule vehicule;

	/**
	 * Constructeur pour créer un client sans véhicule.
	 *
	 * @param nom              Le nom du client.
	 * @param prenom           Le prénom du client.
	 * @param adresse          L'adresse du client.
	 * @param numeroTelephone  Le numéro de téléphone du client (10 chiffres).
	 * @param email            L'adresse email du client.
	 * @param numeroCarteDebit Le numéro de carte de débit du client.
	 * @throws IllegalArgumentException Si une des informations est invalide ou manquante.
	 */
	public Client(String nom, String prenom, Adresse adresse, String numeroTelephone, String email, String numeroCarteDebit) {
		if (verifierClient(nom, prenom, adresse, numeroTelephone, email, numeroCarteDebit)) {
			this.nom = nom;
			this.prenom = prenom;
			this.adresse = adresse;
			this.numeroTelephone = numeroTelephone;
			this.email = email;
			this.numeroCarteDebit = numeroCarteDebit;
		} else {
			throw new IllegalArgumentException("Les données saisies ne permettent pas de créer un client");
		}
	}

	/**
	 * Constructeur pour créer un client avec véhicule.
	 *
	 * @param nom              Le nom du client.
	 * @param prenom           Le prénom du client.
	 * @param adresse          L'adresse du client.
	 * @param numeroTelephone  Le numéro de téléphone du client (10 chiffres).
	 * @param email            L'adresse email du client.
	 * @param numeroCarteDebit Le numéro de carte de débit du client.
	 * @param vehicule         Le véhicule du client (optionnel).
	 * @throws IllegalArgumentException Si une des informations est invalide ou manquante.
	 */
	public Client(String nom, String prenom, Adresse adresse, String numeroTelephone, String email, String numeroCarteDebit, Vehicule vehicule) {
		this(nom, prenom, adresse, numeroTelephone, email, numeroCarteDebit);
		if (vehicule != null) {
			this.vehicule = vehicule;
		}
	}

	/**
	 * Vérifie la validité des informations d'un client.
	 *
	 * @param nom              Le nom du client.
	 * @param prenom           Le prénom du client.
	 * @param adresse          L'adresse du client.
	 * @param numeroTelephone  Le numéro de téléphone du client.
	 * @param email            L'adresse email du client.
	 * @param numeroCarteDebit Le numéro de carte de débit du client.
	 * @return {@code true} si toutes les informations sont valides, {@code false} sinon.
	 * @throws IllegalArgumentException Si une des informations est invalide ou manquante.
	 */
	private boolean verifierClient(String nom, String prenom, Adresse adresse, String numeroTelephone, String email, String numeroCarteDebit) {
		if (nom == null || nom.isEmpty() || prenom == null || prenom.isEmpty() || adresse == null ||
				email == null || email.isEmpty() || numeroCarteDebit == null || numeroCarteDebit.isEmpty()) {
			throw new IllegalArgumentException("Aucun champ ne doit être vide");
		}

		if (numeroTelephone.length() != 10) {
			throw new IllegalArgumentException("Le numéro de téléphone doit contenir 10 chiffres");
		}

		if (!email.matches("^[A-Za-z0-9+_.-]+@(.+)$")) {
			throw new IllegalArgumentException("Adresse mail invalide");
		}

		return true;
	}

	/**
	 * Vérifie si ce client est égal à l'objet spécifié.
	 *
	 * @param o l'objet à comparer.
	 * @return {@code true} si les objets sont égaux, {@code false} sinon.
	 */
	@Override
	public boolean equals(Object o) {
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

	/**
	 * Obtient l'identifiant unique du client.
	 *
	 * @return Identifiant unique du client.
	 */
	public int getId() {
		return this.Id;
	}

	/**
	 * Définit l'identifiant unique du client.
	 *
	 * @param id Identifiant unique du client.
	 */
	public void setId(int id) {
		this.Id = id;
	}

	/**
	 * Obtient le nom du client.
	 *
	 * @return Le nom du client.
	 */
	public String getNom() {
		return nom;
	}

	/**
	 * Obtient le prénom du client.
	 *
	 * @return Le prénom du client.
	 */
	public String getPrenom() {
		return prenom;
	}

	/**
	 * Obtient l'adresse du client.
	 *
	 * @return L'adresse du client.
	 */
	public Adresse getAdresse() {
		return adresse;
	}

	/**
	 * Obtient le numéro de téléphone du client.
	 *
	 * @return Le numéro de téléphone du client.
	 */
	public String getNumeroTelephone() {
		return numeroTelephone;
	}

	/**
	 * Obtient l'adresse email du client.
	 *
	 * @return L'adresse email du client.
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * Obtient le numéro de carte de débit du client.
	 *
	 * @return Le numéro de carte de débit du client.
	 */
	public String getNumeroCarteDebit() {
		return numeroCarteDebit;
	}

	/**
	 * Obtient le véhicule du client.
	 *
	 * @return Le véhicule du client, ou {@code null} s'il n'y en a pas.
	 */
	public Vehicule getVehicule() {
		return this.vehicule;
	}

	/**
	 * Définit le véhicule du client.
	 *
	 * @param vehicule Le véhicule à associer au client.
	 */
	public void setVehicule(Vehicule vehicule) {
		this.vehicule = vehicule;
	}

	/**
	 * Retourne une chaîne de caractères représentant ce client.
	 *
	 * @return Chaîne de caractères représentant le client.
	 */
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
}
