package org.m1.electriquePlus;

import java.io.*;
import java.util.ArrayList;

public class Parc {


    ArrayList<Borne> bornes = new ArrayList<>();
    ArrayList<Client> clients = new ArrayList<>();
    ArrayList<Adresse> adresses = new ArrayList<>();
    ArrayList<Immatriculation> immatriculations = new ArrayList<>();
    ArrayList<Vehicule> vehicules = new ArrayList<>();
    ArrayList<Reservation> reservations = new ArrayList<>();
    ArrayList<Gestionnaire> gestionnaires = new ArrayList<>();

    private String filenameClients;
    private String filenameAdresses;
    private String filenameImmatriculations;
    private String filenameVehicules;
    private String filenameReservations;
    private String filenameGestionnaire;


    public Parc() {
        this.filenameClients = "ClientsSave.txt";
        this.filenameGestionnaire = "GestionnaireSave.txt";
        this.filenameAdresses = "AdressesSave.txt";
        this.filenameImmatriculations = "ImmatriculationsSave.txt";
        this.filenameVehicules = "VehiculesSave.txt";
        this.filenameReservations = "ReservationsSave.txt";
    }

    public Parc(ArrayList<Borne> bornes) {
        this();
        this.bornes = bornes;
   }

    public String getFilenameClients(){
        return this.filenameClients;
    }
    public void setFilenameClients(String filenameClients){
        this.filenameClients = filenameClients;
    }
    public void setClients(ArrayList<Client> clients){
        this.clients = clients;
    }
    public ArrayList<Client> getClients() {
        return this.clients;
    }

    public String getFilenameGestionnaire(){
        return this.filenameGestionnaire;
    }
    public void setFilenameGestionnaire(String filenameGestionnaire){ this.filenameGestionnaire = filenameGestionnaire; }
    public void setGestionnaire(ArrayList<Gestionnaire> gestionnaires){
        this.gestionnaires = gestionnaires;
    }
    public ArrayList<Gestionnaire> getGestionnaires() {
        return this.gestionnaires;
    }

    public String getFilenameAdresses(){ return  this.filenameAdresses;}
    public void setFilenameAdresses(String filenameAdresses){
        this.filenameAdresses = filenameAdresses;
    }
    public void setAdresses(ArrayList<Adresse> adresses){this.adresses=adresses;}
    public ArrayList<Adresse> getAdresses(){ return this.adresses;}

    public String getFilenameImmatriculations(){ return  this.filenameImmatriculations;}
    public void setFilenameImmatriculations(String filenameImmatriculations){
        this.filenameImmatriculations = filenameImmatriculations;
    }
    public void setImmatriculations(ArrayList<Immatriculation> immatriculations){this.immatriculations=immatriculations;}
    public ArrayList<Immatriculation> getImmatriculations(){ return this.immatriculations;}

    public String getFilenameVehicules(){ return  this.filenameVehicules;}
    public void setFilenameVehicules(String filenameVehicules){
        this.filenameVehicules = filenameVehicules;
    }
    public void setVehicules(ArrayList<Vehicule> vehicules){this.vehicules=vehicules;}
    public ArrayList<Vehicule> getVehicules(){ return this.vehicules;}

    public String getFilenameReservations(){ return  this.filenameReservations;}
    public void setFilenameReservations(String filenameReservations){this.filenameReservations = filenameReservations; }
    public void setReservations(ArrayList<Reservation> reservations){this.reservations=reservations;}
    public ArrayList<Reservation> getReservations(){ return this.reservations;}


    /**
     * Genere une liste de clients qui sont inscrit a ce parc de rechargement.
     * @throws IOException
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

                if(c.getVehicule()!=null)
                {
                    line.append(c.getVehicule().getAnneeFabrication());
                    line.append("\t");
                    line.append(c.getVehicule().getMarque());
                    line.append("\t");
                    line.append(c.getVehicule().getModele());
                    line.append("\t");

                    line.append(c.getVehicule().getPlaque().getLettresAvant() + "-" + c.getVehicule().getPlaque().getChiffres() + "-" + c.getVehicule().getPlaque().getLettresApres());
                    line.append("\t");
                }

                writer.write(line.toString());
                writer.newLine();
            }
        }
    }
    /**
     * A partir du fichier filenameclients, on recupère les données du fichiers, et on crées les nouveaux clients pour les mettre dans le tableau clients de parc
     * @throws IOException
     */
    public void getClientsFromFile() throws IOException{
        BufferedReader reader = new BufferedReader(new FileReader(this.getFilenameClients()));
        String line;
        while ((line = reader.readLine()) != null) {
            String[] parts = line.split("\t");
            if (parts.length == 15 || parts.length == 11) {
                int id = Integer.parseInt(parts[0]);
                String nom = parts[1];
                String prenom = parts[2];
                String numeroTelephone = parts[3];
                String email = parts[4];
                String numeroCarteDebit = parts[5];
                //adresse client
                int numeroHabitation = Integer.parseInt(parts[6]);
                String nomRue = parts[7];
                String codePostal = parts[8];
                String nomVille = parts[9];
                String nomPays = parts[10];
                String vehiculePlaque = "";
                String vehiculeModele = "";
                String vehiculeMarque = "";
                int vehiculeAnneeFabrication = 0;
                if(!(parts.length == 11))
                {
                    //vehicule client
                    vehiculeAnneeFabrication = Integer.parseInt(parts[11]);
                    vehiculeMarque = parts[12];
                    vehiculeModele = parts[13];
                    vehiculePlaque =parts[14];

                }

                Adresse adresse = new Adresse(numeroHabitation, nomRue, codePostal, nomVille, nomPays);
                Vehicule vehicule;
                if(parts.length == 11)
                    vehicule = null;
                else
                    vehicule = new Vehicule(vehiculePlaque, vehiculeMarque, vehiculeModele, vehiculeAnneeFabrication);
                Client client = new Client(nom, prenom, adresse, numeroTelephone, email, numeroCarteDebit, vehicule);
                client.setId(id);
                this.getClients().add(client);
            }
        }
    }
    /**
     * Verifie qu'un fichier de sauvegarde des clients n'existent pas encore, et si c'est le cas, crée un nouveau fichier contenant les clients inscrits au parc (présents dans le champ clients du parc)
     * @throws IOException
     */
    public void loadFileClients() throws IOException{
        File file = new File(this.getFilenameClients());
        if (!file.exists()) {
            generateFileClients();
        }
    }

    /**
     * Genere une liste de Gestionnaire qui sont inscrit a ce parc de rechargement.
     * @throws IOException
     */
    public void generateFileGestionnaires() throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(this.getFilenameGestionnaire()))) {
            if(!this.getGestionnaires().isEmpty())
            {
                for (Gestionnaire g : this.getGestionnaires()) {
                    StringBuilder line = new StringBuilder();
                    line.append(g.getId());
                    line.append("\t");
                    line.append(g.getNom());
                    line.append("\t");
                    line.append(g.getPrenom());
                    writer.write(line.toString());
                    writer.newLine();
                }
            }else{
                gestionnaires = new ArrayList<Gestionnaire>();
            }
        }
    }

    /**
     * A partir du fichier filenamegestionnaire, on recupère les données du fichiers, et on crées les nouveaux Gestionnaire pour les mettre dans le tableau gestionnaire de parc
     * @throws IOException
     */
    public void getGestionnairesFromFile() throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(this.getFilenameGestionnaire()));
        String line;
        while ((line = reader.readLine()) != null) {
            String[] parts = line.split("\t");
            if (parts.length == 3) {
                int id = Integer.parseInt(parts[0]);
                String nom = parts[1];
                String prenom = parts[2];
                Gestionnaire gestionnaire = new Gestionnaire(nom, prenom);
                gestionnaire.setId(id);
                this.getGestionnaires().add(gestionnaire);
            }
        }
    }

    /**
     * Verifie qu'un fichier de sauvegarde des gestionnaires n'existent pas encore, et si c'est le cas, crée un nouveau fichier contenant les gestionnaires inscrits au parc (présents dans le champ gestionnaires du parc)
     * @throws IOException
     */
    public void loadFileGestionnaires() throws IOException {
        File file = new File(this.getFilenameGestionnaire());
        if (!file.exists()) {
            generateFileGestionnaires();
        }
    }



    /**
     * Genere un fichiet contenant les adresses qui sont enregistrées a ce parc de rechargement.
     * @throws IOException
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
     * @throws IOException
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
                String codePostal = parts[3];
                String nomVille = parts[4];
                String nomPays = parts[5];

                Adresse adresse = new Adresse(numeroHabitation, nomRue, codePostal, nomVille, nomPays);
                adresse.setId(id);
                this.getAdresses().add(adresse);
            }
        }
    }
    /**
     * Verifie qu'un fichier de sauvegarde des adresses n'existent pas encore, et si c'est le cas, crée un nouveau fichier contenant les adresses inscrites au parc (présentes dans le champ adresses du parc)
     * @throws IOException
     */
    public void loadFileAdresses() throws IOException{
        File file = new File(this.getFilenameAdresses());
        if (!file.exists()) {
            generateFileAdresses();
        }
    }


    /**
     * Genere un fichier contenant les immatriculations qui sont enregistrées a ce parc de rechargement.
     * @throws IOException
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
     * @throws IOException
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
     * @throws IOException
     */
    public void loadFileImmatriculations() throws IOException{
        File file = new File(this.getFilenameImmatriculations());
        if (!file.exists()) {
            generateFileImmatriculations();
        }
    }


    /**
     * Génère un fichier contenant les vehicules qui sont enregistrées a ce parc de rechargement.
     * @throws IOException
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
     * @throws IOException
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
     * @throws IOException
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





    public ArrayList<Borne> getBornes() {
        return bornes;
    }

    public void setBornes(ArrayList<Borne> bornes) {
        this.bornes = bornes;
    }

    public void addBorne(Borne borneAAjouter){
        this.getBornes().add(borneAAjouter);
    }

    public void ajouterReservation(Reservation reservation){
        this.reservations.add(reservation);
    }

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

    public Borne getById(int choix) {
        return this.getBornes().stream().filter(b -> b.getNumero() == choix).findFirst().orElse(null);
    }
}
