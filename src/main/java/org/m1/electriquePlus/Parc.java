package org.m1.electriquePlus;

import java.io.*;
import java.util.ArrayList;

public class Parc {

    /**
     * Liste des bornes de recharge.
     */
    ArrayList<Borne> bornes ;
    /**
     *  Liste des clients enregistrés.
     */
    ArrayList<Client> clients;
    /**
     * Liste des adresses des clients.
     */
    ArrayList<Adresse> adresses;
    /**
     * Liste des immatriculations des véhicules enregistrés dans l'application
     */
    ArrayList<Immatriculation> immatriculations;
    /**
     * Liste des véhicules enregistrés.
     */
    ArrayList<Vehicule> vehicules;
    /**
     * Liste des réservations effectuées.
     */
    ArrayList<Reservation> reservations;

    private String filenameClients;
    private String filenameAdresses;
    private String filenameImmatriculations;
    private String filenameVehicules;
    private String filenameReservations;

    /**
     * Constructeur par défaut qui initialise les noms des fichiers de sauvegarde avec des valeurs par défaut.
     */
    public Parc() {
        this.filenameClients = "ClientsSave.txt";
        this.filenameAdresses = "AdressesSave.txt";
        this.filenameImmatriculations = "ImmatriculationsSave.txt";
        this.filenameVehicules = "VehiculesSave.txt";
        this.filenameReservations = "ReservationsSave.txt";
    }

    /**
     * Constructeur qui initialise le parc avec une liste de bornes spécifique et utilise les noms de fichiers par défaut.
     * @param bornes : liste des bornes avec lesquelles on veut initialiser le parc
     */
    public Parc(ArrayList<Borne> bornes) {
        this();
        this.bornes = bornes;
   }

    /**
     *  Retourne le nom du fichier de sauvegarde des clients.
     * @return String : le nom du fichier de sauvegarde
     */
    public String getFilenameClients(){
        return this.filenameClients;
    }


    /**
     * Définit la liste des clients.
     * @param clients : ArrayList<Client>
     */
    public void setClients(ArrayList<Client> clients){
        this.clients = clients;
    }

    /**
     * Retourne la liste des clients.
     * @return ArrayList<Client>
     */
    public ArrayList<Client> getClients() {
        return this.clients;
    }

    /**
     * Retourne le nom du fichier de sauvegarde des adresses.
     * @return String : le nom du fichier de sauvegarde.
     */
    public String getFilenameAdresses(){ return  this.filenameAdresses;}
    public void setFilenameAdresses(String filenameAdresses){
        this.filenameAdresses = filenameAdresses;
    }

    /**
     * Définit la liste des adresses.
     * @param adresses : ArrayList<Adresse> liste des adresses.
     */
    public void setAdresses(ArrayList<Adresse> adresses){this.adresses=adresses;}
    public ArrayList<Adresse> getAdresses(){ return this.adresses;}

    /**
     * Retourne le nom du fichier de sauvegarde des immatriculations.
     * @return le nom du fichier de sauvegarde.
     */
    public String getFilenameImmatriculations(){ return  this.filenameImmatriculations;}
    public void setFilenameImmatriculations(String filenameImmatriculations){
        this.filenameImmatriculations = filenameImmatriculations;
    }

    /**
     * Définit la liste des immatriculations.
     * @param immatriculations liste des immatriculations.
     */
    public void setImmatriculations(ArrayList<Immatriculation> immatriculations){this.immatriculations=immatriculations;}
    public ArrayList<Immatriculation> getImmatriculations(){ return this.immatriculations;}

    /**
     * Retourne le nom du fichier de sauvegarde des véhicules.
     * @return le nom du fichier de sauvegarde.
     */
    public String getFilenameVehicules(){ return  this.filenameVehicules;}
    public void setFilenameVehicules(String filenameVehicules){
        this.filenameVehicules = filenameVehicules;
    }

    /**
     * Définit la liste des véhicules.
     * @param vehicules liste des véhicules.
     */
    public void setVehicules(ArrayList<Vehicule> vehicules){this.vehicules=vehicules;}


    /**
     * Retourne la liste des véhicules.
     * @return liste des véhicules.
     */
    public ArrayList<Vehicule> getVehicules(){ return this.vehicules;}

    /**
     * Retourne le nom du fichier de sauvegarde des réservations.
     * @return le nom du fichier de sauvegarde.
     */
    public String getFilenameReservations(){ return  this.filenameReservations;}


    /**
     * Définit le nom du fichier de sauvegarde des réservations.
     * @param filenameReservations le nom du fichier de sauvegarde.
     */
    public void setFilenameReservations(String filenameReservations){this.filenameReservations = filenameReservations; }

    /**
     * Définit la liste des réservations.
     * @param reservations liste des réservations.
     */
    public void setReservations(ArrayList<Reservation> reservations){this.reservations=reservations;}
    public ArrayList<Reservation> getReservations(){ return this.reservations;}


    /**
     * Genere une liste de clients qui sont inscrit a ce parc de rechargement.
     * @throws IOException si une erreur d'entrée/sortie se produit.
     */
    public void generateFileClients() throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(this.getFilenameClients()))) {
            int i = 0; //représente l'ID des clients //TODO : implementer l'ID directement dans le client et le concatener sur la ligne
            for (Client c : this.getClients()) {
                StringBuilder line = new StringBuilder(); //on commence chaque nouvelle ligne par un ID des clients
                //i++;
                line.append(c.getId());
                line.append("\t");
                line.append(c.getNom());
                line.append("\t");
                line.append(c.getPrenom());
                line.append("\t");
                line.append(c.getNumeroTelephone());
                line.append("\t");
                line.append(c.getEmail());
                line.append("\t");
                line.append(c.getNumeroCarteDebit());
                line.append("\t");
                line.append(c.getAdresse().getNumeroHabitation());
                line.append("\t");
                line.append(c.getAdresse().getNomRue());
                line.append("\t");
                line.append(c.getAdresse().getCodePostal());
                line.append("\t");
                line.append(c.getAdresse().getNomVille());
                line.append("\t");
                line.append(c.getAdresse().getNomPays());
                line.append("\t");

                line.append(c.getVehicule().getAnneeFabrication());
                line.append("\t");
                line.append(c.getVehicule().getMarque());
                line.append("\t");
                line.append(c.getVehicule().getModele());
                line.append("\t");

                line.append(c.getVehicule().getPlaque().getLettresAvant() + "-" + c.getVehicule().getPlaque().getChiffres() + "-" + c.getVehicule().getPlaque().getLettresApres());
                line.append("\t");


                writer.write(line.toString());
                writer.newLine();
            }
        }
    }
    /**
     * A partir du fichier filenameclients, on recupère les données du fichiers, et on crées les nouveaux clients pour les mettre dans le tableau clients de parc
     * @throws IOException si une erreur d'entrée/sortie se produit.
     */
    public void getClientsFromFile() throws IOException{
        BufferedReader reader = new BufferedReader(new FileReader(this.getFilenameClients()));
        String line;
        while ((line = reader.readLine()) != null) {
            String[] parts = line.split("\t");
            if (parts.length == 15) {
                int id = Integer.parseInt(parts[0]);
                String nom = parts[1];
                String prenom = parts[2];
                String numeroTelephone = parts[3];
                String email = parts[4];
                String numeroCarteDebit = parts[5];
                int numeroHabitation = Integer.parseInt(parts[6]);
                String nomRue = parts[7];
                int codePostal = Integer.parseInt(parts[8]);
                String nomVille = parts[9];
                String nomPays = parts[10];

                int vehiculeAnneeFabrication = Integer.parseInt(parts[11]);
                String vehiculeMarque = parts[12];
                String vehiculeModele = parts[13];
                String vehiculePlaque =parts[14];



                // Créez un objet Adresse et Vehicule temporaire, car les détails ne sont pas disponibles
                Adresse adresse = new Adresse(numeroHabitation, nomRue, codePostal, nomVille, nomPays); // Remplacez par des valeurs appropriées
                Vehicule vehicule = new Vehicule(vehiculePlaque, vehiculeMarque, vehiculeModele, vehiculeAnneeFabrication); // Remplacez par des valeurs appropriées
                Client client = new Client(nom, prenom, adresse, numeroTelephone, email, numeroCarteDebit, vehicule);
                client.setId(id); 
                this.getClients().add(client);
            }
        }
    }
    /**
     * Verifie qu'un fichier de sauvegarde des clients n'existent pas encore, et si c'est le cas, crée un nouveau fichier contenant les clients inscrits au parc (présents dans le champ clients du parc)
     * @throws IOException si une erreur d'entrée/sortie se produit.
     */
    public void loadFileClients() throws IOException{
        File file = new File(this.getFilenameClients());
        if (!file.exists()) {
            generateFileClients();
        }
    }



    /**
     * Genere un fichiet contenant les adresses qui sont enregistrées a ce parc de rechargement.
     * @throws IOException si une erreur d'entrée/sortie se produit.
     */
    public void generateFileAdresses() throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(this.getFilenameAdresses()))) {
            int i = 0; //représente l'ID des adresses //TODO : implementer l'ID directement dans l'adresse et le concatener sur la ligne
            for (Adresse a : this.getAdresses()) {
                StringBuilder line = new StringBuilder(); //on commence chaque nouvelle ligne par un ID des clients
                //i++;
                line.append(a.getId());
                line.append("\t");
                line.append(a.getNumeroHabitation());
                line.append("\t");
                line.append(a.getNomRue());
                line.append("\t");
                line.append(a.getCodePostal());
                line.append("\t");
                line.append(a.getNomVille());
                line.append("\t");
                line.append(a.getNomPays());
                writer.write(line.toString());
                writer.newLine();
            }
        }
    }
    /**
     * A partir du fichier filenameAdresses, on recupère les données du fichiers, et on crées les adresses du fichier pour les mettre dans le tableau adresses du parc
     * @throws IOException si une erreur d'entrée/sortie se produit.
     */
    public void getAdressesFromFile() throws IOException{
        BufferedReader reader = new BufferedReader(new FileReader(this.getFilenameAdresses()));
        String line;
        while ((line = reader.readLine()) != null) {
            String[] parts = line.split("\t");
            if (parts.length == 6) {
                int id = Integer.parseInt(parts[0]);
                int numeroHabitation = Integer.parseInt(parts[1]);
                String nomRue = parts[2];
                int codePostal = Integer.parseInt(parts[3]);
                String nomVille = parts[4];
                String nomPays = parts[5];


                // Créez un objet Adresse et Vehicule temporaire, car les détails ne sont pas disponibles
                Adresse adresse = new Adresse(numeroHabitation, nomRue, codePostal, nomVille, nomPays); // Remplacez par des valeurs appropriées
                adresse.setId(id);
                this.getAdresses().add(adresse);
            }
        }
    }
    /**
     * Verifie qu'un fichier de sauvegarde des adresses n'existent pas encore, et si c'est le cas, crée un nouveau fichier contenant les adresses inscrites au parc (présentes dans le champ adresses du parc)
     * @throws IOException si une erreur d'entrée/sortie se produit.
     */
    public void loadFileAdresses() throws IOException{
        File file = new File(this.getFilenameAdresses());
        if (!file.exists()) {
            generateFileAdresses();
        }
    }


    /**
     * Genere un fichier contenant les immatriculations qui sont enregistrées a ce parc de rechargement.
     * @throws IOException si une erreur d'entrée/sortie se produit.
     */
    public void generateFileImmatriculations() throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(this.getFilenameImmatriculations()))) {
            for (Immatriculation i : this.getImmatriculations()) {
                StringBuilder line = new StringBuilder(); //on commence chaque nouvelle ligne par un ID des immatriculations
                //i++;
                line.append(i.getId());
                line.append("\t");
                line.append(i.getLettresAvant());
                line.append("\t");
                line.append(i.getChiffres());
                line.append("\t");
                line.append(i.getLettresApres());
                writer.write(line.toString());
                writer.newLine();
            }
        }
    }
    /**
     * A partir du fichier filenameImmatriculation, on recupère les données du fichiers, et on crées les immatriculations du fichier pour les mettre dans le tableau immatriculations du parc
     * @throws IOException si une erreur d'entrée/sortie se produit.
     */
    public void getImmatriculationsFromFile() throws IOException{
        BufferedReader reader = new BufferedReader(new FileReader(this.getFilenameImmatriculations()));
        String line;
        while ((line = reader.readLine()) != null) {
            String[] parts = line.split("\t");
            if (parts.length == 4) {
                int id = Integer.parseInt(parts[0]);
                String lettresAvant = parts[1];
                int chiffres = Integer.parseInt(parts[2]);
                String lettresApres = parts[3];


                // Créez un objet Immatriculation
                Immatriculation immatriculation = new Immatriculation(lettresAvant+"-"+chiffres+"-"+lettresApres); // Remplacez par des valeurs appropriées
                immatriculation.setId(id);
                this.getImmatriculations().add(immatriculation);
            }
        }
    }
    /**
     * Verifie qu'un fichier de sauvegarde des immatriculations n'existent pas encore, et si c'est le cas, crée un nouveau fichier contenant les immatriculations inscrits au parc (présents dans le champ immatriculations du parc)
     * @throws IOException si une erreur d'entrée/sortie se produit.
     */
    public void loadFileImmatriculations() throws IOException{
        File file = new File(this.getFilenameImmatriculations());
        if (!file.exists()) {
            generateFileImmatriculations();
        }
    }


    /**
     * Génère un fichier contenant les vehicules qui sont enregistrées a ce parc de rechargement.
     * @throws IOException si une erreur d'entrée/sortie se produit.
     */
    public void generateFileVehicule() throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(this.getFilenameVehicules()))) {
           for (Vehicule v : this.getVehicules()) {
                Immatriculation plaque = v.getPlaque();
                StringBuilder line = new StringBuilder(); //on commence chaque nouvelle ligne par un ID des vehicules
                line.append(v.getId());
                line.append("\t");
                line.append(plaque.getLettresAvant());
                line.append("\t");
                line.append(plaque.getChiffres());
                line.append("\t");
                line.append(plaque.getLettresApres());
                line.append("\t");
                line.append(v.getMarque());
                line.append("\t");
                line.append(v.getModele());
                line.append("\t");
                line.append(v.getAnneeFabrication());
                writer.write(line.toString());
                writer.newLine();
            }
        }
    }
    /**
     * A partir du fichier filenameVehicule, on recupère les données du fichiers, et on crées les vehicules du fichier pour les mettre dans le tableau vehicules du parc
     * @throws IOException si une erreur d'entrée/sortie se produit.
     */
    public void getVehiculesFromFile() throws IOException{
        BufferedReader reader = new BufferedReader(new FileReader(this.getFilenameVehicules()));
        String line;
        while ((line = reader.readLine()) != null) {
            String[] parts = line.split("\t");
            if (parts.length == 7) {
                int id = Integer.parseInt(parts[0]);
                String lettresAvant = parts[1];
                int chiffres = Integer.parseInt(parts[2]);
                String lettresApres = parts[3];
                String marque = parts[4];
                String modele = parts[5];
                int anneeFabrication = Integer.parseInt(parts[6]);


                // Créez un objet Immatriculation
                Vehicule vehicule = new Vehicule(lettresAvant+"-"+chiffres+"-"+lettresApres,marque, modele, anneeFabrication);
                vehicule.setId(id);
                this.getVehicules().add(vehicule);
            }
        }
    }
    /**
     * Verifie qu'un fichier de sauvegarde des vehicules n'existent pas encore, et si c'est le cas, crée un nouveau fichier contenant les vehicules inscrits au parc (présents dans le champ vehicules du parc
     * @throws IOException si une erreur d'entrée/sortie se produit.
     */
    public void loadFileVehicules() throws IOException{
        File file = new File(this.getFilenameVehicules());
        if (!file.exists()) {
            generateFileVehicule();
        }
    }


//    /**
//     * Génère un fichier contenant les reservations qui sont enregistrées a ce parc de rechargement.
//     * @throws IOException
//     */
//    public void generateFileReservation() throws IOException {
//        try (BufferedWriter writer = new BufferedWriter(new FileWriter(this.getFilenameReservations()))) {
//            int i = 0; //représente l'ID des reservations //TODO : implementer l'ID directement dans les reservations et le concatener sur la ligne
//            for (Reservation r : this.getReservations()) {
//
//                StringBuilder line = new StringBuilder(); //on commence chaque nouvelle ligne par un ID des reservations
//                i++;
//                line.append(i);
//                line.append("\t");
//                line.append(plaque.getLettresAvant());
//                line.append("\t");
//                line.append(plaque.getChiffres());
//                line.append("\t");
//                line.append(plaque.getLettresApres());
//                line.append("\t");
//                line.append(a.getMarque());
//                line.append("\t");
//                line.append(a.getModele());
//                line.append("\t");
//                line.append(a.getAnneeFabrication());
//                writer.write(line.toString());
//                writer.newLine();
//            }
//        }
//    }
//    /**
//     * A partir du fichier filenameVehicule, on recupère les données du fichiers, et on crées les vehicules du fichier pour les mettre dans le tableau vehicules du parc
//     * @throws IOException
//     */
//    public void getVehiculesFromFile() throws IOException{
//        BufferedReader reader = new BufferedReader(new FileReader(this.getFilenameVehicules()));
//        String line;
//        while ((line = reader.readLine()) != null) {
//            String[] parts = line.split("\t");
//            if (parts.length == 7) {
//                int id = Integer.parseInt(parts[0]);
//                String lettresAvant = parts[1];
//                int chiffres = Integer.parseInt(parts[2]);
//                String lettresApres = parts[3];
//                String marque = parts[4];
//                String modele = parts[5];
//                int anneeFabrication = Integer.parseInt(parts[6]);
//
//
//                // Créez un objet Immatriculation
//                Vehicule vehicule = new Vehicule(lettresAvant+"-"+chiffres+"-"+lettresApres,marque, modele, anneeFabrication);
//                this.getVehicules().add(vehicule);
//            }
//        }
//    }
//    /**
//     * Verifie qu'un fichier de sauvegarde des vehicules n'existent pas encore, et si c'est le cas, crée un nouveau fichier contenant les vehicules inscrits au parc (présents dans le champ vehicules du parc
//     * @throws IOException
//     */
//    public void loadFileVehicules() throws IOException{
//        File file = new File(this.getFilenameVehicules());
//        if (!file.exists()) {
//            generateFileVehicule();
//        }
//    }


    /**
     * Retourne la liste des bornes de recharge.
     * @return
     */
    public ArrayList<Borne> getBornes() {
        return bornes;
    }

    /**
     * Définit la liste des bornes.
     * @param bornes
     */
    public void setBornes(ArrayList<Borne> bornes) {
        this.bornes = bornes;
    }

    /**
     * Ajoute une borne de recharge à la liste des bornes.
     * @param borneAAjouter
     */
    public void addBorne(Borne borneAAjouter){
        this.getBornes().add(borneAAjouter);
    }

    /**
     *  Ajoute une réservation à la liste des réservations du parc.
     * @param reservation
     */
    public void ajouterReservation(Reservation reservation){
        this.reservations.add(reservation);
    }

    /**
     *  Cette méthode vérifie la disponibilité de chaque borne et ajoute les bornes disponibles à une liste.
     * @param date : format dd/mm, date a laquelle on veut verifier la disponibilité de la borne
     * @param hour : format HH (par exemple 04 ou 11), heure a laquelle on veut vérifier la disponibilité de chaque bornes.
     * @return ArrayList<Borne> : liste des bornes disponibles
     */
    public ArrayList<Borne> getDispBornes(String date, String hour){
        //ArrayList<char> dispos = new ArrayList<char>();
        ArrayList<Borne> dispo = new ArrayList<>();
        for(Borne b : this.getBornes()){
            b.checkDisponibilites(date, hour);
            if(b.checkDisponibilites(date, hour)=='D')
                dispo.add(b);
        }
        return dispo;
    }
}
