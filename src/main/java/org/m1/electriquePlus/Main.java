package org.m1.electriquePlus;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class Main {
    private static Scanner scanner = new Scanner(System.in);
    private static List<Gestionnaire> gestionnaires = new ArrayList<>();
    public static List<Client> clients = new ArrayList<>();
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
                        prendreBorneSansReserv();
                    }
                    break;
                case 5:
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
        Gestionnaire fred = new Gestionnaire("Dupont", "Fred");
        gestionnaires.add(fred);

        // création d'un client lambda
        Adresse adressePaul = new Adresse(71, "Rue du bois", "54000", "Nancy", "France");
        Vehicule vehicule = new Vehicule("AB-123-CD", "marque", "modele", 2022);
        Client paul = new Client("Dupont", "Paul", adressePaul, "0601020304", "Dupont.Fred@ElectriquePlus.fr", "1234567890", vehicule);
        clients.add(paul);
    }

    private static void infoBorneSansReserv(){
        LocalDateTime auj = LocalDateTime.now();
        System.out.println("---------------------");
        System.out.println("Bonjour " +clientConnecté.getPrenom()+ " " +clientConnecté.getNom() +"nous allons rechercher dans notre base de données si une place est disponible pour vous");
        System.out.println("Sachez que toute heure entamé doit etre payé ! Il est " + auj.getHour() +" : " + auj.getMinute());
    }


    public static void prendreBorneSansReserv(){
        infoBorneSansReserv();

        System.out.println("Merci de fournir la place d'immatriculation du véhicule:");
        String plaque = scanner.nextLine();



        //pas besoin de saisir l'heure d'arrivé on prend l'heure actuelle

/*
        if (!borneDisponible()) {
            System.out.println("Aucune borne de recharge n'est disponible.");
            return;
        }

        System.out.println("Numéro d'immatriculation du véhicule:");
        String plaque = scanner.nextLine();

        Vehicule vehicule = client.getVehicule();
        if (vehicule != null && vehicule.getPlaque().equalsIgnoreCase(plaque) && !vehiculeEstReserve(plaque)) {
            demanderDureeRechargeOuHeureDepart();
        } else {
            System.out.println("Véhicule non reconnu. Est-ce votre véhicule personnel ? (oui/non)");
            String reponse = scanner.nextLine();
            if (reponse.equalsIgnoreCase("oui")) {
                System.out.println("Numéro de téléphone:");
                String numeroTelephone = scanner.nextLine();
                if (numeroTelephone.equals(client.getNumeroTelephone())) {
                    System.out.println("Véhicule ajouté au profil.");
                    client.setVehicule(new Vehicule(plaque, "marque", "modele", 2022)); // Demander plus de détails si nécessaire
                    demanderDureeRechargeOuHeureDepart();
                } else {
                    System.out.println("Numéro de téléphone incorrect.");
                }
            } else {
                System.out.println("Numéro de téléphone:");
                String numeroTelephone = scanner.nextLine();
                if (verifClientParTelephone(numeroTelephone)) {
                    demanderDureeRechargeOuHeureDepart();
                } else {
                    System.out.println("Numéro de téléphone non reconnu. Créer un compte.");
                    inscrireClientAvecTelephone(numeroTelephone);
                }
            }
        }
        //ET qu’une borne de recharge est disponible

        //
        //rechercher si le numéro d’immatriculation du véhicule est identique au profil et qu’aucune réservation est en cours sur cette plaque. Demander la durée de recharge ou l’heure de départ.
        //
        //si véhicule n’est pas reconnu, demander si c’est son véhicule personnel (-> entrer dans la bdd, demander le numero de telephone pour justifier l’identité) si le vehicule est pas le véhicule personnel, entrer le numéro de tel et la duré de charge, si tel non reconnu => créer un compte.
   */ }

    public void confirmationVehicule(Client c){//a changer
       /* Vehicule vehicule = clientConnecté.getVehicule();
        if(vehicule != null) {
            System.out.println("Veuillez Confirmer que c'est bien pour ce véhicule : " + vehicule.getPlaque());
            System.out.println("1. oui");
            System.out.println("2. non, saisir les informations du véhicule");
        }else{
            System.out.println("Vous n'avez pas de véhicule enregistré, voulez-vous l'enregistrer apres avoir saisie les informations");
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
        }*/
    }

    //refaire absolument
    private static void faireUneReservation() {
        int jour = 0;
        int mois = 0;
        int annee = LocalDate.now().getYear();
        int heure = 0;
        Vehicule vehicule = null;
        boolean valid=true;
        System.out.println("Veuillez entrer une date de reservation :");

        //saisie du mois de reservation
        LocalDate maxReservationDate = LocalDate.now().plusMonths(2);
        do {
            System.out.println("Veuillez saisir le mois numerique ("+LocalDate.now().format(DateTimeFormatter.ofPattern("MM"))+" à "+maxReservationDate.format(DateTimeFormatter.ofPattern("MM"))+")");
            mois = verifChoix(1,12);//mettre directement todaymonth à todaymonth+3 @romain
            LocalDate reservationDate = LocalDate.of(annee, mois, 1);
                if (reservationDate.isAfter(maxReservationDate) || reservationDate.isBefore(LocalDate.now())) {
                    valid = false;
                    System.out.println("Nous prenons les reservations que 3 mois à l'avance pas plus.");
                }
            //else{
                System.out.println("Veuillez saisir le mois en numerique et compris entre "+LocalDate.now().format(DateTimeFormatter.ofPattern("mm"))+" à "+maxReservationDate.format(DateTimeFormatter.ofPattern("mm")));

        } while (!valid);

        //saisie du jour de reservation
        YearMonth moisAnnee = YearMonth.of(annee, mois);
        int nbJourMois = moisAnnee.lengthOfMonth();

        do {
            System.out.println("Veuillez saisir le jour numérique (1 à "+nbJourMois+")");
            //int jour = choix() //faire le cas si il choisi le mois en cours ou sinon dire au dessu que c'est pas possible le choix du mois en cours
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
            //int heure2 = verifChoix(0, 23); //tout simplement
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
                System.out.println("Veuillez Confirmer que c'est bien pour ce véhicule : " + vehicule.getPlaque());
                System.out.println("1. oui");
                System.out.println("2. non, saisir les informations du véhicule");
            }else{
                System.out.println("Vous n'avez pas de véhicule enregistré. Voulez-vous l'enregistrer après avoir saisie les informations");
                System.out.println("1. oui");
                System.out.println("2. non");
            }
            //la fait juste verifierChoix(1,2); @romain
            //puis un case
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

    private static int saisieCodePostal(){
        int saisie=0;
        boolean valid;
        do {
            System.out.println("Code postal:");
            if (scanner.hasNextInt()) {
                saisie = scanner.nextInt();
                scanner.nextLine();
                valid = String.valueOf(saisie).length() == 5;
                if (!valid) {
                    System.out.println("Le code postal doit contenir 5 chiffres. Veuillez réessayer.");
                }
            } else {
                System.out.println("Veuillez entrer un code postal valide.");
                valid = false;
                scanner.next();
            }
        } while (!valid);
        return saisie;
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
        String numeroCarteDebit = saisieTelephoneCodePostalCarteDebit("Numéro de carte de débit: par exemple 1234567890","\\d+","Le numéro de carte de débit ne doit pas être vide et doit contenir uniquement des chiffres. Veuillez réessayer.", 16 );

        Adresse adresse = new Adresse(numeroHabitation, nomRue, codePostal, nomVille, nomPays);
        Client client = new Client(nom, prenom, adresse, numeroTelephone, email, numeroCarteDebit);
        clients.add(client);

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
            System.out.println("Véhicule ajouté avec succès : " + vehicule);
        } else {
            System.out.println("Erreur lors de l'ajout du véhicule. Retour au menu client.");
        }
    }

    private static Vehicule ajouteVehicule(){ //CLIENT FAIRE LES MODIFS
        boolean valid;
        String plaque = saisieString("Entrez la plaque d'immatriculation : par exemple AB-123-CD", "[A-Z]{2}-\\d{3}-[A-Z]{2}","Format de plaque invalide. Veuillez réessayer." );

        System.out.println("Entrez la marque du véhicule : par exemple Tesla");
        String marque = scanner.nextLine();

        System.out.println("Entrez le modèle du véhicule : par exemple Model S");
        String modele = scanner.nextLine();

        System.out.println("Entrez l'année de fabrication du véhicule : par exemple 2020");
        int anneeFabrication = scanner.nextInt();
        scanner.nextLine();

        return new Vehicule(plaque, marque, modele, anneeFabrication);
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
                "4. Prendre une borne sans reservation",
                "5. Retour au menu principal"

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
