package org.m1.electriquePlus;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    private static Scanner scanner = new Scanner(System.in);
    private static List<Gestionnaire> gestionnaires = new ArrayList<>();
    public static List<Client> clients = new ArrayList<>();
    public static Client clientConnecté;

    public static void main(String[] args){
        //creation de fred, le gestionnaire
        Gestionnaire fred = new Gestionnaire("Dupont", "fred");
        gestionnaires.add(fred);

        //création d'un client lambda
        Adresse adressePaul = new Adresse(71, "Rue du bois", 54000, "Nancy", "France" );
        Vehicule vehicule = new Vehicule("AB-123-CD","marque","modele",2022);
        Client paul = new Client("Dupont", "Paul", adressePaul, "0601020304", "Dupont.Fred@ElectriquePlus.fr", "1234567890", vehicule);
        clients.add(paul);

        //création de l'application en ligne de commande

        while (true) {
            System.out.println("1. Se connecter en tant que gestionnaire");
            System.out.println("2. Se connecter en tant que client");
            System.out.println("3. Quitter");
            int choix = scanner.nextInt();
            scanner.nextLine();

            switch (choix) {
                case 1: //le gestionnaire
                    if (verifGestionnaire()) {
                        menuGestionnaire();
                    }
                    break;
                case 2: //le client
                    menuClient();
                    break;
                case 3:
                    System.exit(0);
                    break;
                default:
                    System.out.println("Choix invalide");
            }
        }
    }

    private static void menuClient() {
        while (true) {
            System.out.println("1. S'incrire");
            System.out.println("2. Ajouter un véhicule");
            System.out.println("3. Retour au menu principal");
            int choix = scanner.nextInt();
            scanner.nextLine();

            switch (choix) {
                case 1:
                    if (verifClient() == null){
                        incrireClient();
                    }
                    break;
                case 2:
                    if (verifClient() != null){
                        ajouteVehicule();
                    }
                    break;
                case 3:
                    return;
                default:
                    System.out.println("Choix invalide");
            }
        }
    }

    private static void incrireClient() {
        System.out.println("Nom:");
        String nom = scanner.nextLine();
        System.out.println("Prénom:");
        String prenom = scanner.nextLine();
        System.out.println("Numéro d'habitation:");
        int numeroHabitation = scanner.nextInt();
        scanner.nextLine();
        System.out.println("Nom de la rue:");
        String nomRue = scanner.nextLine();
        System.out.println("Code postal:");
        int codePostal = scanner.nextInt();
        scanner.nextLine();
        System.out.println("Nom de la ville:");
        String nomVille = scanner.nextLine();
        System.out.println("Nom du pays:");
        String nomPays = scanner.nextLine();
        System.out.println("Numéro de téléphone: par exemple 0607080901");
        String numeroTelephone = scanner.nextLine();
        System.out.println("Email: par exemple jean@gmail.com");
        String email = scanner.nextLine();
        System.out.println("Numéro de carte de débit: par exemple 1234567890");
        String numeroCarteDebit = scanner.nextLine();

        Adresse adresse = new Adresse(numeroHabitation, nomRue, codePostal, nomVille, nomPays);
        Client client = new Client(nom, prenom, adresse, numeroTelephone, email, numeroCarteDebit);
        clients.add(client);

        System.out.println("Compte créé avec succès!");
    }


    private static void menuGestionnaire() {
        while (true) {
            System.out.println("1. Afficher la liste des clients");
            System.out.println("2. ...");
            System.out.println("3. Retour au menu principal");
            int choix = scanner.nextInt();
            scanner.nextLine();

            switch (choix) {
                case 1:
                    afficherListeClients();
                    break;
                case 2:
                    //...();
                    break;
                case 3:
                    return;
                default:
                    System.out.println("Choix invalide");
            }
        }
    }


    private static boolean  verifGestionnaire() {
        System.out.println("Nom du gestionnaire:");
        String nom = scanner.nextLine();
        System.out.println("Prénom du gestionnaire:");
        String prenom = scanner.nextLine();

        //recherche si le gestionnaire existe
        Gestionnaire gestionnaire = null;
        for (Gestionnaire g : gestionnaires) {
            if (g.getNom().equalsIgnoreCase(nom) && g.getPrenom().equalsIgnoreCase(prenom)) {
                gestionnaire = g;
                break;
            }
        }

        if (gestionnaire == null) {
            System.out.println("Gestionnaire non trouvé. Retour au menu principal.");
            return false;
        }
        System.out.println("Bienvenue, " + gestionnaire.getNom() + " " + gestionnaire.getPrenom());
        return true;
    }

    private static Client verifClient() { //VERIFIE SI LE CLIENT EXISTE

        System.out.println("Nom:");
        String nom = scanner.nextLine();
        System.out.println("Prénom:");
        String prenom = scanner.nextLine();

        Client client = getClient(nom, prenom);
        if (client == null) {
            System.out.println("Client non trouvé. Veuillez créer un compte d'abord.");
            return null;
        }

        for (Client c : clients) {
            if (c.getNom().equalsIgnoreCase(nom) && c.getPrenom().equalsIgnoreCase(prenom)) {
                clientConnecté = c;
                return c;
            }
        }
        return null;
    }

    private static Client getClient(String nom, String prenom) { //RETOURNE LE CLIENT CONNECTE
        for (Client c : clients) {
            if (c.getNom().equalsIgnoreCase(nom) && c.getPrenom().equalsIgnoreCase(prenom)) {
                return c;
            }
        }
        return null;
    }

    private static void afficherListeClients() { //GESTIONNAIRE
        System.out.println("Voici la liste des clients inscrit dans le parc:");
        for (Client c : clients) {
            System.out.println(c.toString());
        }
    }

    private static void ajouteVehicule(){ //CLIENT
        if (clientConnecté.getVehicule() != null) {
            System.out.println("Ce client a déjà un véhicule enregistré.");
            return;
        }

        System.out.println("Entrez la plaque d'immatriculation : par exemple AB-123-CD ");
        String plaque = scanner.nextLine();

        System.out.println("Entrez la marque du véhicule : par exemple Tesla");
        String marque = scanner.nextLine();

        System.out.println("Entrez le modèle du véhicule : par exemple Model S");
        String modele = scanner.nextLine();

        System.out.println("Entrez l'année de fabrication du véhicule : par exemple 2020");
        int anneeFabrication = scanner.nextInt();
        scanner.nextLine();

        Vehicule vehicule = new Vehicule(plaque, marque, modele, anneeFabrication);
        clientConnecté.setVehicule(vehicule);

        // Vérification de l'enregistrement du véhicule
        if (clientConnecté.getVehicule().equals(vehicule)) {
            System.out.println("Véhicule ajouté avec succès : " + vehicule);
        } else {
            System.out.println("Erreur lors de l'ajout du véhicule. Retour au menu client.");
        }
    }

}
