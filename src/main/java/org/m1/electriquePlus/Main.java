package org.m1.electriquePlus;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class Main {
    private static Scanner scanner = new Scanner(System.in);
    private static List<Gestionnaire> gestionnaires = new ArrayList<>();
    public static List<Client> clients = new ArrayList<>();
    public static Client clientConnecté;
    public static Parc parc;

    public static void main(String[] args) {
        // création du parc et des borne
        parc = new Parc(new ArrayList<Borne>());
        parc.addBorne(new Borne(1));
        parc.addBorne(new Borne(2));
        parc.addBorne(new Borne(3));
        parc.addBorne(new Borne(4));
        parc.addBorne(new Borne(5));

        // création de Fred, le gestionnaire
        Gestionnaire fred = new Gestionnaire("Dupont", "Fred");
        gestionnaires.add(fred);

        // création d'un client lambda
        Adresse adressePaul = new Adresse(71, "Rue du bois", 54000, "Nancy", "France");
        Vehicule vehicule = new Vehicule("AB-123-CD", "marque", "modele", 2022);
        Client paul = new Client("Dupont", "Paul", adressePaul, "0601020304", "Dupont.Fred@ElectriquePlus.fr", "1234567890", vehicule);
        clients.add(paul);

        // création de l'application en ligne de commande
        while (true) {
            System.out.println("____________MENU PRINCIPAL______________");
            System.out.println("1. Se connecter en tant que gestionnaire");
            System.out.println("2. Se connecter en tant que client");
            System.out.println("3. Quitter");
            System.out.println("_________________________________________");


            int choix = verifChoix(1, 3);

            switch (choix) {
                case 1: // le gestionnaire
                    if (verifGestionnaire()) {
                        menuGestionnaire();
                    }
                    break;
                case 2: // le client
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
            System.out.println("3. Faire une réservation");
            System.out.println("4. Retour au menu principal");
            int choix = scanner.nextInt();
            scanner.nextLine();

            switch (choix) {
                case 1:
                    if (verifClient() == null){
                        inscrireClient();
                    }else return; //sile client existe on le retourne au menu client
                    break;
                case 2:
                    if (verifClient() != null){
                        ajouteVehiculeClient();
                    }
                    break;
                case 3:
                    if (verifClient() != null){
                        faireUneReservation();
                    }
                    break;
                case 4:
                    return;
                default:
                    System.out.println("Choix invalide");
            }
        }
    }

    private static void faireUneReservation() {
        int jour = 0;
        int mois = 0;
        int annee = LocalDate.now().getYear();
        int heure = 0;
        Vehicule vehicule = null;
        boolean valid;
        System.out.println("Veuillez entrer une date de reservation :");

        //saisie du mois de reservation
        LocalDate maxReservationDate = LocalDate.now().plusMonths(2);
        do {
            System.out.println("Veuillez saisir le mois numerique ("+LocalDate.now().format(DateTimeFormatter.ofPattern("MM"))+" à "+maxReservationDate.format(DateTimeFormatter.ofPattern("MM"))+")");
            try {
                mois = scanner.nextInt();
                valid = (mois < 13) && (mois > 0);
            }catch(InputMismatchException e){
                valid = false;
            }
            if(valid){
                LocalDate reservationDate = LocalDate.of(annee, mois, 1);
                if (reservationDate.isAfter(maxReservationDate) || reservationDate.isBefore(LocalDate.now())) {
                    valid = false;
                    System.out.println("Nous prenons les reservations que 3 mois à l'avance pas plus.");
                }
            }else{
                System.out.println("Veuillez saisir le mois en numerique et compris entre "+LocalDate.now().format(DateTimeFormatter.ofPattern("mm"))+" à "+maxReservationDate.format(DateTimeFormatter.ofPattern("mm")));
            }
        } while (!valid);

        //saisie du jour de reservation
        YearMonth moisAnnee = YearMonth.of(annee, mois);
        int nbJourMois = moisAnnee.lengthOfMonth();

        do {
            System.out.println("Veuillez saisir le jour numérique (1 à "+nbJourMois+")");
            try{
                jour = scanner.nextInt();
                if (jour < 1 || jour > 31) {
                    valid = false;
                }else {
                    valid = jour <= nbJourMois;
                }
            }catch (InputMismatchException e){
                valid = false;
            }
            if (!valid) {
                System.out.println("Veuillez saisir le jour en numerique et compris entre 1 à "+nbJourMois);
            }
        }while(!valid);

        do{
            System.out.println("Veuillez saisir l'heure (00h - 23h)");
            boolean heureValid = false;
            try{
                heure = scanner.nextInt();
                heureValid = true;
            }catch (InputMismatchException e){
                String heuretempo = scanner.nextLine();
                if(heuretempo.toLowerCase().endsWith("h")){
                    try {
                        heure = Integer.parseInt(heuretempo.split("h")[0]);
                        heureValid = true;
                    }catch(Exception e2){
                        valid = false;
                    }
                }
            }
            if(heureValid){
                if(heure<24 || heure >= 0)
                    valid = true;
            }
            if(!valid)
            {
                System.out.println("Veuillez saisir une heure en numerique et compris 00h - 23h");
            }
        }while(!valid);

        do{
            vehicule = clientConnecté.getVehicule();
            if(vehicule != null) {
                System.out.println("Veuillez Confirmé que ces bien pour se véhicule : " + vehicule.getPlaque());
                System.out.println("1. oui");
                System.out.println("2. non, saisir les information du véhicule");
            }else{
                System.out.println("Vous n'avez pas de véhicule enregistrer voulez vous l'enregistrer apres avoir saisie les informations");
                System.out.println("1. oui");
                System.out.println("2. non");
            }
            try{
                int choix = 0;
                choix = scanner.nextInt();
                if(choix < 1 || choix > 2){
                    valid = false;
                }else{
                    if(choix == 2){
                        vehicule = ajouteVehicule();
                    }else if(vehicule == null){
                        ajouteVehiculeClient();
                        vehicule = clientConnecté.getVehicule();
                    }
                    valid = true;
                }
            }catch (InputMismatchException e){
                valid = false;
            }
            if(!valid)
            {
                System.out.println("Veuillez saisir 1 ou 2");
            }
        }while(!valid);

        LocalDateTime datetime = LocalDateTime.of(annee, mois, jour, heure, 0);
        ArrayList<Borne> bornes = parc.getDispBornes(datetime.format(DateTimeFormatter.ofPattern("dd/MM")),datetime.format(DateTimeFormatter.ofPattern("hh")));
        if(!bornes.isEmpty()){
            if(bornes.get(0).changeStatusBorne(datetime.format(DateTimeFormatter.ofPattern("dd/MM")),datetime.format(DateTimeFormatter.ofPattern("hh")),"RESERVE") == 1)
            {
                Reservation r = new Reservation(clientConnecté,vehicule,bornes.get(0),datetime,datetime.plusHours(1));
                parc.ajouterReservation(r);
                System.out.println("Vous aurez une reservation a la date et l'heure voulue sur la borne n°"+bornes.get(0).getNumero());
            }
        }
    }


    private static void inscrireClient() {
        String nom = "";
        String prenom = "";
        int numeroHabitation = 0;
        String nomRue = "";
        int codePostal = 0;
        String nomVille = "";
        String nomPays = "";
        String numeroTelephone = "";
        String email = "";
        String numeroCarteDebit = "";
        boolean valid;

        System.out.println("Il n'y a pas de compte existant, merci de vous inscrire :");
        // Saisie et validation du nom de famille
        do {
            System.out.println("Nom de famille:");
            nom = scanner.nextLine();
            valid = nom != null && !nom.isEmpty() && nom.matches("[a-zA-Z\\sàâäéèêëîïôöùûüÿçÀÂÄÉÈÊËÎÏÔÖÙÛÜŸÇ-]+");
            if (!valid) {
                System.out.println("Le nom ne doit pas être vide et ne doit contenir que des lettres, des espaces, des accents ou des tirets. Veuillez réessayer.");
            }
        } while (!valid);

        // Saisie et validation du prénom
        do {
            System.out.println("Prénom:");
            prenom = scanner.nextLine();
            valid = prenom != null && !prenom.isEmpty() && prenom.matches("[a-zA-Z\\sàâäéèêëîïôöùûüÿçÀÂÄÉÈÊËÎÏÔÖÙÛÜŸÇ-]+");
            if (!valid) {
                System.out.println("Le prénom ne doit pas être vide et ne doit contenir que des lettres, des espaces, des accents ou des tirets. Veuillez réessayer.");
            }
        } while (!valid);


        // Saisie et validation du numéro d'habitation
        do {
            System.out.println("Numéro d'habitation:");
            if (scanner.hasNextInt()) {
                numeroHabitation = scanner.nextInt();
                scanner.nextLine(); // Consomme la nouvelle ligne
                valid = numeroHabitation > 0;
                if (!valid) {
                    System.out.println("Le numéro d'habitation doit être supérieur à 0. Veuillez réessayer.");
                }
            } else {
                System.out.println("Veuillez entrer un numéro valide.");
                valid = false;
                scanner.next(); // Consomme l'entrée invalide
            }
        } while (!valid);

        // Saisie et validation du nom de la rue
        do {
            System.out.println("Nom de la rue:");
            nomRue = scanner.nextLine();
            valid = nomRue != null && !nomRue.isEmpty();
            if (!valid) {
                System.out.println("Le nom de la rue ne doit pas être vide. Veuillez réessayer.");
            }
        } while (!valid);

        // Saisie et validation du code postal
        do {
            System.out.println("Code postal:");
            if (scanner.hasNextInt()) {
                codePostal = scanner.nextInt();
                scanner.nextLine(); // Consomme la nouvelle ligne
                valid = String.valueOf(codePostal).length() == 5;
                if (!valid) {
                    System.out.println("Le code postal doit contenir 5 chiffres. Veuillez réessayer.");
                }
            } else {
                System.out.println("Veuillez entrer un code postal valide.");
                valid = false;
                scanner.next(); // Consomme l'entrée invalide
            }
        } while (!valid);

        // Saisie et validation du nom de la ville
        do {
            System.out.println("Nom de la ville:");
            nomVille = scanner.nextLine();
            valid = nomVille != null && !nomVille.isEmpty() && nomVille.matches("[a-zA-Z\\sàâäéèêëîïôöùûüÿçÀÂÄÉÈÊËÎÏÔÖÙÛÜŸÇ-]+");
            if (!valid) {
                System.out.println("Le nom de la ville ne doit pas être vide et ne doit contenir que des lettres, des espaces, des accents ou des tirets. Veuillez réessayer.");
            }
        } while (!valid);

        // Saisie et validation du nom du pays
        do {
            System.out.println("Nom du pays:");
            nomPays = scanner.nextLine();
            valid = nomPays != null && !nomPays.isEmpty() && nomPays.matches("[a-zA-Z\\s]+");
            if (!valid) {
                System.out.println("Le nom du pays ne doit pas être vide et ne doit contenir que des lettres. Veuillez réessayer.");
            }
        } while (!valid);

        // Saisie et validation du numéro de téléphone
        do {
            System.out.println("Numéro de téléphone: par exemple 0607080901");
            numeroTelephone = scanner.nextLine();
            valid = numeroTelephone.length() == 10 && numeroTelephone.matches("\\d+");
            if (!valid) {
                System.out.println("Le numéro de téléphone doit contenir 10 chiffres. Veuillez réessayer.");
            }
        } while (!valid);

        // Saisie et validation de l'email
        do {
            System.out.println("Email: par exemple jean@gmail.com");
            email = scanner.nextLine();
            valid = email.matches("^[A-Za-z0-9+_.-]+@(.+)$");
            if (!valid) {
                System.out.println("Adresse mail invalide. Veuillez réessayer.");
            }
        } while (!valid);

        // Saisie et validation du numéro de carte de débit
        do {
            System.out.println("Numéro de carte de débit: par exemple 1234567890");
            numeroCarteDebit = scanner.nextLine();
            valid = numeroCarteDebit.matches("\\d+");
            if (!valid) {
                System.out.println("Le numéro de carte de débit ne doit pas être vide et doit contenir uniquement des chiffres. Veuillez réessayer.");
            }
        } while (!valid);

        Adresse adresse = new Adresse(numeroHabitation, nomRue, codePostal, nomVille, nomPays);
        Client client = new Client(nom, prenom, adresse, numeroTelephone, email, numeroCarteDebit);
        clients.add(client);

        System.out.println("Compte créé avec succès!");
    }



    private static void menuGestionnaire() {
        while (true) {
            System.out.println("______MENU DU GESTIONNAIRE_____________");
            System.out.println("1. Afficher la liste des clients");
            System.out.println("2. ...");
            System.out.println("3. Retour au menu principal");

            int choix = verifChoix(1, 3);

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

    public static int verifChoix(int choixmin, int choixmax){ //verifie si le choix est entre les deux bornes
        int choix = -1;
        do {
            System.out.print("Veuillez entrer votre choix (entre " + choixmin + " et " + choixmax + ")");
            if (scanner.hasNextInt()) {
                choix = scanner.nextInt();
                scanner.nextLine();
                if (choix < choixmin || choix > choixmax) {
                    System.out.println("Choix invalide. Veuillez réessayer.");
                }
            } else {
                System.out.println("Entrée non valide. Veuillez entrer un chiffre.");
                scanner.next();
            }
        } while (choix < choixmin || choix > choixmax);
        return choix;
    }

    private static boolean verifGestionnaire() {
        System.out.println("________VERIFICATION DE L'IDENTITE______");
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
        System.out.println("Verification, si un compte existe déja.");

        System.out.println("Nom de famille:");
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
                System.out.println("Vous etes connecté ! Bonjour " + c.getNom() + " " + c.getPrenom());
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

    private static void ajouteVehiculeClient(){
        if (clientConnecté.getVehicule() != null) {
            System.out.println("Ce client a déjà un véhicule enregistré.");
            return;
        }
        Vehicule vehicule = ajouteVehicule();

        clientConnecté.setVehicule(vehicule);

        // Vérification de l'enregistrement du véhicule
        if (clientConnecté.getVehicule().equals(vehicule)) {
            System.out.println("Véhicule ajouté avec succès : " + vehicule);
        } else {
            System.out.println("Erreur lors de l'ajout du véhicule. Retour au menu client.");
        }
    }

    private static Vehicule ajouteVehicule(){ //CLIENT
        boolean valid;
        String plaque;

        do {
            System.out.println("Entrez la plaque d'immatriculation : par exemple AB-123-CD");
            plaque = scanner.nextLine();
            if (!plaque.matches("[A-Z]{2}-\\d{3}-[A-Z]{2}")) {
                System.out.println("Format de plaque invalide. Veuillez réessayer.");
            }
        } while (!plaque.matches("[A-Z]{2}-\\d{3}-[A-Z]{2}"));


        System.out.println("Entrez la marque du véhicule : par exemple Tesla");
        String marque = scanner.nextLine();

        System.out.println("Entrez le modèle du véhicule : par exemple Model S");
        String modele = scanner.nextLine();

        System.out.println("Entrez l'année de fabrication du véhicule : par exemple 2020");
        int anneeFabrication = scanner.nextInt();
        scanner.nextLine();

        return new Vehicule(plaque, marque, modele, anneeFabrication);
    }

}
