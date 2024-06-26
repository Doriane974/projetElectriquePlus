package org.m1.electriquePlus;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class Main {
    private static Scanner scanner = new Scanner(System.in);
    private static ArrayList<Gestionnaire> gestionnaires = new ArrayList<>();
    public static ArrayList<Client> clients = new ArrayList<>();
    public static Client clientConnecté;
    public static Parc parc;

    //-------------------------------------
    //            AFFICHAGE PAGES
    //-------------------------------------
    public static void main(String[] args) { //affiche la page principale
        initialisation();

        // création de l'application en ligne de commande
        while (true) {
            int choix = verifChoix(1, afficheMenuPrincipal());

            switch (choix) {
                case 1: // le gestionnaire
                    if (verifGestionnaire()) {
                        pageGestionnaire();
                    }
                    break;
                case 2: // le client
                    pageClient();
                    break;
                case 3:
                    saveAllData();
                    System.exit(0);
                    break;
                default:
                    System.out.println("Choix invalide");
            }
        }
    }

    private static void pageClient() {
        while (true) {
            int choix = verifChoix(1, afficheMenuClient());

            switch (choix) {
                case 1:
                    if (verifClient() == null){
                        inscrireClient();
                    } //si le client existe on le retourne au menu client
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
                    if (verifClient() != null){
                        modifierClient();
                    }
                    break;
                case 5:
                    if (verifClient() != null){
                        signalerRetrait();
                    }
                    break;
                case 6:
                    return;
                default:
                    System.out.println("Choix invalide");
            }
        }
    }

    private static void pageGestionnaire() {
        while (true) {
            int choix = verifChoix(1, afficheMenuGestionnaire());
            switch (choix) {
                case 1:
                    afficherListeClients();
                    break;
                case 2:
                    return;
                default:
                    System.out.println("Choix invalide");
            }
        }
    }

    //-------------------------------------
    //            Fonctionnalités
    //-------------------------------------

    private static void initialisation(){
        // création du parc et des borne
        parc = new Parc(new ArrayList<Borne>());
        parc.addBorne(new Borne(1));
        parc.addBorne(new Borne(2));
        parc.addBorne(new Borne(3));
        parc.addBorne(new Borne(4));
        parc.addBorne(new Borne(5));

        // création de Fred, le gestionnaire
        try {
            parc.loadFileGestionnaires();
            parc.getGestionnairesFromFile();
            if(parc.getGestionnaires().isEmpty())
            {
                Gestionnaire fred = new Gestionnaire("Dupont", "Fred");
                gestionnaires.add(fred);
            }
            else
                gestionnaires = parc.getGestionnaires();
        }catch (IOException e)
        {
            System.out.println("erreur lors de la lecture du fichier Gestionnaire");
        }

        // création d'un client lambda
        try {
            parc.loadFileClients();
            parc.getClientsFromFile();
            if(parc.getClients().isEmpty())
            {
                Adresse adressePaul = new Adresse(71, "Rue du bois", "54000", "Nancy", "France");
                Vehicule vehicule = new Vehicule("AB-123-CD", "marque", "modele", 2022);
                Client paul = new Client("Dupont", "Paul", adressePaul, "0601020304", "Dupont.Fred@ElectriquePlus.fr", "1234567854735390", vehicule);
                clients.add(paul);
            }else{
                clients = parc.getClients();
            }
        }catch (IOException e)
        {
            System.out.println("erreur lors de la lecture du fichier Client");
        }

        // Load fichier Vehicule et adresse et Immatricule
        try {
            parc.loadFileImmatriculations();
            parc.loadFileVehicules();
            parc.loadFileAdresses();
            parc.getImmatriculationsFromFile();
            parc.getVehiculesFromFile();
            parc.getAdressesFromFile();
        }catch (IOException e)
        {
            System.out.println("erreur lors de la lecture du fichier Immatriculation, Vehicule ou Adresses");
        }
    }

    private static void saveAllData() {
        try {
            parc.setClients(clients);
            parc.setGestionnaire(gestionnaires);
            if (!parc.getGestionnaires().isEmpty())
                parc.generateFileGestionnaires();
            if (!parc.getClients().isEmpty())
                parc.generateFileClients();
            if (!parc.getAdresses().isEmpty())
                parc.generateFileAdresses();
            if (!parc.getImmatriculations().isEmpty())
                parc.generateFileImmatriculations();
            if (!parc.getVehicules().isEmpty())
                parc.generateFileVehicule();
        }catch (IOException ignored) {
            System.out.println("erreur lors de la sauvegarde des données");
        }

    }

    private static void faireUneReservation() {
        int jour = 0;
        int mois = 0;
        int annee = LocalDate.now().getYear();
        int heure = 0;
        Vehicule vehicule = null;
        boolean valid = true;
        System.out.println("Veuillez entrer une date de reservation :");

        // Saisie du mois de reservation
        LocalDate maxReservationDate = LocalDate.now().plusMonths(2);
        do {
            System.out.println("Veuillez saisir le mois numerique (" + LocalDate.now().format(DateTimeFormatter.ofPattern("MM")) + " à " + maxReservationDate.format(DateTimeFormatter.ofPattern("MM")) + ")");
            mois = verifChoix(LocalDate.now().getMonthValue(), maxReservationDate.getMonthValue());
            LocalDate reservationDate = LocalDate.of(annee, mois, 1);
            if (reservationDate.isAfter(maxReservationDate) || reservationDate.isBefore(LocalDate.now())) {
                valid = false;
                System.out.println("Nous prenons les reservations que 3 mois à l'avance pas plus.");
            } else {
                valid = true;
            }
        } while (!valid);

        // Saisie du jour de reservation
        YearMonth moisAnnee = YearMonth.of(annee, mois);
        int nbJourMois = moisAnnee.lengthOfMonth();
        jour = verifChoix(1, nbJourMois);

        // Saisie de l'heure de reservation
        heure = verifChoix(0, 23);

        // Vérification du véhicule
        valid = false;
        do {
            vehicule = clientConnecté.getVehicule();
            if (vehicule != null) {
                System.out.println("Veuillez confirmer que c'est bien pour ce véhicule : " + vehicule.getPlaque());
                System.out.println("1. oui");
                System.out.println("2. non, saisir les informations du véhicule");
            } else {
                System.out.println("Vous n'avez pas de véhicule enregistré. Voulez-vous l'enregistrer après avoir saisi les informations ?");
                System.out.println("1. oui");
                System.out.println("2. non");
            }
            int choix = verifChoix(1, 2);
            if (choix == 2) {
                vehicule = ajouteVehicule();
            } else if (vehicule == null) {
                ajouteVehiculeClient();
                vehicule = clientConnecté.getVehicule();
            }
            valid = true;
        } while (!valid);

        LocalDateTime datetime = LocalDateTime.of(annee, mois, jour, heure, 0);
        ArrayList<Borne> bornes = parc.getDispBornes(datetime.format(DateTimeFormatter.ofPattern("dd/MM")), datetime.format(DateTimeFormatter.ofPattern("HH")));
        if (!bornes.isEmpty()) {
            if (bornes.get(0).changeStatusBorne(datetime.format(DateTimeFormatter.ofPattern("dd/MM")), datetime.format(DateTimeFormatter.ofPattern("HH")), "RESERVE") == 1) {
                Reservation r = new Reservation(clientConnecté, vehicule, bornes.get(0), datetime, datetime.plusHours(1));
                parc.ajouterReservation(r);
                System.out.println("Vous aurez une reservation à la date et l'heure voulues sur la borne n°" + bornes.get(0).getNumero());
            }
        } else {
            System.out.println("Aucune borne disponible pour cette date et heure.");
        }
    }


    private static void inscrireClient() {
        System.out.println("Il n'y a pas de compte existant, merci de vous inscrire :");
        String nom = saisieNomPrenomVillePays("Nom de famille");
        String prenom = saisieNomPrenomVillePays("Prénom");
        int numeroHabitation = saisieNumHabitation();
        String nomRue = saisieNomRue();
        String codePostal = saisieTelephoneCodePostalCarteDebit("Code postal:","\\d+", "Le code postal doit contenir 5 chiffres. Veuillez réessayer.", 5);
        String nomVille = saisieNomPrenomVillePays("Ville");
        String nomPays = saisieNomPrenomVillePays("Pays");
        String numeroTelephone = saisieTelephoneCodePostalCarteDebit("Numéro de téléphone: par exemple 0607080901","\\d+","Le numéro de téléphone doit contenir 10 chiffres. Veuillez réessayer", 10);
        String email = saisieString("Email: par exemple jean@gmail.com","^[A-Za-z0-9+_.-]+@(.+)$", "Adresse mail invalide. Veuillez réessayer." );
        String numeroCarteDebit = saisieTelephoneCodePostalCarteDebit("Numéro de carte de débit: par exemple 1234567890123456","\\d+","Le numéro de carte de débit ne doit pas être vide et doit contenir uniquement des chiffres. Veuillez réessayer.", 16 );

        Adresse adresse = new Adresse(numeroHabitation, nomRue, codePostal, nomVille, nomPays);
        Client client = new Client(nom, prenom, adresse, numeroTelephone, email, numeroCarteDebit);
        clients.add(client);
        clientConnecté=client;
        System.out.println("Voulez vous inscrire un véhicule");
        System.out.println("1 oui");
        System.out.println("2 non");
        int choix = verifChoix(1,2);
        if (choix == 1){
            ajouteVehiculeClient();
        }
        System.out.println("Compte créé avec succès!");
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
            System.out.println("Véhicule ajouté avec succès : " + vehicule.toString());
        } else {
            System.out.println("Erreur lors de l'ajout du véhicule. Retour au menu client.");
        }
    }

    private static Vehicule ajouteVehicule(){ //CLIENT
        boolean valid;
        String plaque = saisieString("Entrez la plaque d'immatriculation : par exemple AB-123-CD", "[A-Z]{2}-\\d{3}-[A-Z]{2}","Format de plaque invalide. Veuillez réessayer." );

        System.out.println("Entrez la marque du véhicule : par exemple Tesla");
        String marque = scanner.nextLine();

        System.out.println("Entrez le modèle du véhicule : par exemple Model S");
        String modele = scanner.nextLine();

        int anneeFabrication = saisieAnnee();
        return new Vehicule(plaque, marque, modele, anneeFabrication);
    }

    private static void modifierClient() {
        System.out.println("Saisir le numéro de ce que vous voulez modifier :");
        int choix;
        do {
            System.out.println("1. Modifier votre email (Actuellement : " + clientConnecté.getEmail() + " )");
            System.out.println("2. Modifier votre numéro de téléphone (Actuellement : " + clientConnecté.getNumeroTelephone() + " )");
            System.out.println("3. Modifier votre numéro de Carte ");
            System.out.println("4. Modifier votre adresse (Actuellement : " + clientConnecté.getAdresse() + " )");
            System.out.println("0. Retour au menu principal");
            choix = verifChoix(0, 4);

            switch (choix) {
                case 1:
                    String newEmail = saisieString("Saisir votre nouveau email :", "^[A-Za-z0-9+_.-]+@(.+)$", "Adresse mail invalide. Veuillez réessayer.");
                    clientConnecté.setEmail(newEmail);
                    System.out.println("Email modifié avec succès!");
                    break;
                case 2:
                    String newTelephone = saisieTelephoneCodePostalCarteDebit("Saisir votre nouveau numéro de téléphone : ", "\\d+", "Le numéro de téléphone doit contenir 10 chiffres. Veuillez réessayer", 10);
                    clientConnecté.setNumeroTelephone(newTelephone);
                    System.out.println("Numéro de téléphone modifié avec succès!");
                    break;
                case 3:
                    String newNumeroCarte = saisieTelephoneCodePostalCarteDebit("Saisir votre nouvelle carte de crédit : ", "\\d+", "Le numéro de carte de crédit doit contenir 16 chiffres. Veuillez réessayer", 16);
                    clientConnecté.setNumeroCarteDebit(newNumeroCarte);
                    System.out.println("Numéro de carte de crédit modifié avec succès!");
                    break;
                case 4:
                    System.out.println("Saisir votre nouvelle adresse : ");
                    int numeroHabitation = saisieNumHabitation();
                    String nomRue = saisieNomRue();
                    String codePostal = saisieTelephoneCodePostalCarteDebit("Code postal : ", "\\d+", "Le code postal doit contenir 5 chiffres. Veuillez réessayer.", 5);
                    String nomVille = saisieNomPrenomVillePays("Ville");
                    String nomPays = saisieNomPrenomVillePays("Pays");
                    Adresse updateAdresse = new Adresse(numeroHabitation, nomRue, codePostal, nomVille, nomPays);
                    clientConnecté.setAdresse(updateAdresse);
                    System.out.println("Adresse modifiée avec succès!");
                    break;
                case 0:
                    return;
                default:
                    System.out.println("Choix invalide");
            }
        } while (choix != 0);
    }

    private static void signalerRetrait(){
        System.out.println("Saisir le numéro de votre borne (1 - "+parc.getBornes().size()+")");
        int choix = verifChoix(0, parc.getBornes().size());
        Borne borne = parc.getById(choix);
        if(borne != null)
            borne.changeStatusBorne(LocalDate.now().format(DateTimeFormatter.ofPattern("jj/MM")),LocalDate.now().format(DateTimeFormatter.ofPattern("hh")),"DISPONIBLE");
        else
            System.out.println("La borne que vous avez choisie n'est pas correcte");
    }


    //-------------------------------------
    //            Verifications
    //-------------------------------------

    //verifie si le choix est entre les deux bornes
    public static int verifChoix(int choixmin, int choixmax){
        int choix = -1;
        do {
            System.out.print("Veuillez entrer votre choix (entre " + choixmin + " et " + choixmax + ") : ");
            if (scanner.hasNextInt()) {
                choix = scanner.nextInt();
                scanner.nextLine();
                if (choix < choixmin || choix > choixmax) {
                    System.out.println("Choix invalide. Veuillez réessayer!");
                }
            } else {
                System.out.println("Entrée non valide. Veuillez entrer un chiffre!");
                scanner.next();
            }
        } while (choix < choixmin || choix > choixmax);
        return choix;
    }

    private static boolean verifGestionnaire() {
        System.out.println("________VERIFICATION DE L'IDENTITE______");
        String nom = saisieNomPrenomVillePays("Nom de famille");
        String prenom = saisieNomPrenomVillePays("Prénom");

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
        System.out.println("________VERIFICATION DE L'IDENTITE______");
        String nom = saisieNomPrenomVillePays("Nom de famille");
        String prenom = saisieNomPrenomVillePays("Prénom");

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

    //retourne le client connecté
    private static Client getClient(String nom, String prenom) {
        for (Client c : clients) {
            if (c.getNom().equalsIgnoreCase(nom) && c.getPrenom().equalsIgnoreCase(prenom)) {
                return c;
            }
        }
        return null;
    }



    //-------------------------------------
    //            SAISIES
    //-------------------------------------

    private static String saisieNomPrenomVillePays(String type){
        String saisie;
        boolean valid;
        do {
            System.out.println("Saisir votre " +type);
            saisie = scanner.nextLine();
            valid = saisie != null && !saisie.isEmpty() && saisie.matches("[a-zA-Z\\sàâäéèêëîïôöùûüÿçÀÂÄÉÈÊËÎÏÔÖÙÛÜŸÇ-]+");
            if (!valid) {
                System.out.println("Le " +type+ " ne doit pas être vide, il doit contenir que des lettres, des espaces, des accents ou des tirets. Veuillez réessayer.");
            }
        } while (!valid);
        return saisie;
    }

    private static int saisieNumHabitation(){
        int saisie=0;
        boolean valid;
        do {
            System.out.println("Numéro d'habitation:");
            if (scanner.hasNextInt()) {
                saisie = scanner.nextInt();
                scanner.nextLine();
                valid = saisie > 0;
                if (!valid) {
                    System.out.println("Le numéro d'habitation doit être supérieur à 0. Veuillez réessayer.");
                }
            } else {
                System.out.println("Veuillez entrer un numéro valide.");
                valid = false;
                scanner.next();
            }
        } while (!valid);
        return saisie;
    }



    private static String saisieTelephoneCodePostalCarteDebit(String demande, String regex, String erreur, int taille) {
        String saisie="";
        boolean valid;
        do {
            System.out.println(demande);
            saisie = scanner.nextLine();
            valid = saisie.length() == taille && saisie.matches(regex);
            if (!valid) {
                System.out.println(erreur);
            }
        } while (!valid);
        return saisie;
    }
    private static String saisieNomRue(){
        String saisie="";
        boolean valid;
        do {
            System.out.println("Nom de la rue: ");
            saisie = scanner.nextLine();
            valid = saisie != null && !saisie.isEmpty();
            if (!valid) {
                System.out.println("Le nom de la rue ne doit pas être vide. Veuillez réessayer.");
            }
        } while (!valid);
        return saisie;
    }

    //pour email , plaque, ...
    private static String saisieString(String demande, String regex, String erreur){
        String saisie="";
        boolean valid;
        do {
            System.out.println(demande);
            saisie = scanner.nextLine();
            valid = saisie.matches(regex);
            if (!valid) {
                System.out.println(erreur);
            }
        } while (!valid);
        return saisie;
    }

    private static int saisieAnnee(){
        int saisie=0;
        boolean valid;
        int currentYear = 2024;
        do {
            System.out.println("Entrez l'année de fabrication du véhicule : par exemple 2020 : ");
            if (scanner.hasNextInt()) {
                saisie = scanner.nextInt();
                scanner.nextLine();
                
                valid = saisie >= 1950 && saisie <= currentYear;
                if (!valid) {
                    System.out.println("L'année de fabrication doit être comprise entre 1950 et " + currentYear + ". Veuillez réessayer.");
                }
            } else {
                System.out.println("Veuillez entrer une année valide.");
                valid = false;
                scanner.next();
            }
        } while (!valid);
        return saisie;
    }

    //-------------------------------------
    //            AFFICHAGE MENU
    //-------------------------------------

    public static int afficheMenuPrincipal(){ //affiche et retourne le nombre de choix
        System.out.println("____________MENU PRINCIPAL______________");
        List<String> options = Arrays.asList(
                "1. Se connecter en tant que gestionnaire",
                "2. Se connecter en tant que client",
                "3. Quitter"
        );
        for (String option : options) System.out.println(option);
        System.out.println("_________________________________________");
        return options.size();
    }

    public static int afficheMenuClient(){ //affiche et retourne le nombre de choix
        System.out.println("____________MENU CLIENT______________");
        List<String> options = Arrays.asList(
                "1. S'inscrire",
                "2. Ajouter un véhicule",
                "3. Faire une réservation",
                "4. Modifier son profil",
                "5. Signaler un retrait anticipé du véhicule",
                "6. Retour au menu principal"

        );
        for (String option : options) System.out.println(option);
        System.out.println("_________________________________________");
        return options.size();
    }

    private static int afficheMenuGestionnaire(){
        System.out.println("______MENU DU GESTIONNAIRE_____________");
        List<String> options = Arrays.asList(
                "1. Afficher la liste des clients",
                "2. Retour au menu principal"
        );
        for (String option : options) System.out.println(option);
        System.out.println("_________________________________________");
        return options.size();
    }
}
