package org.m1.electriquePlus;

import java.io.*;
import java.util.ArrayList;

public class Parc {

    ArrayList<Borne> bornes ;
    ArrayList<Client> clients;
    ArrayList<Adresse> adresses;
    ArrayList<Immatriculation> immatriculations;
    ArrayList<Vehicule> vehicules;

    private String filenameClients;
    private String filenameAdresses;
    private String filenameImmatriculations;
    private String filenameVehicules;

    public Parc() {
        this.filenameClients = "ClientsSave.txt";
        this.filenameAdresses = "AdressesSave.txt";
        this.filenameImmatriculations = "ImmatriculationsSave.txt";
        this.filenameVehicules = "VehiculesSave.txt";
    }

    public Parc(ArrayList<Borne> bornes) {
        this.bornes = bornes;
        this.filenameClients = "ClientsSave.txt";
        this.filenameAdresses = "AdressesSave.txt";
        this.filenameImmatriculations = "ImmatriculationsSave.txt";
        this.filenameVehicules = "VehiculesSave.txt";
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

    public String getFilenameAdresses(){ return  this.filenameAdresses;}
    public void setFilenameAdresses(String filenameAdresses){
        this.filenameAdresses = filenameAdresses;
    }
    public void setAdresses(ArrayList<Adresse> adresses){this.adresses=adresses;};
    public ArrayList<Adresse> getAdresses(){ return this.adresses;}

    public String getFilenameImmatriculations(){ return  this.filenameImmatriculations;}
    public void setFilenameImmatriculations(String filenameImmatriculations){
        this.filenameImmatriculations = filenameImmatriculations;
    }
    public void setImmatriculations(ArrayList<Immatriculation> immatriculations){this.immatriculations=immatriculations;};
    public ArrayList<Immatriculation> getImmatriculations(){ return this.immatriculations;}

    public String getFilenameVehicules(){ return  this.filenameVehicules;}
    public void setFilenameVehicules(String filenameVehicules){
        this.filenameVehicules = filenameVehicules;
    }
    public void setVehicules(ArrayList<Vehicule> vehicules){this.vehicules=vehicules;};
    public ArrayList<Vehicule> getVehicules(){ return this.vehicules;}


    /**
     * Genere une liste de clients qui sont inscrit a ce parc de rechargement.
     * @throws IOException
     */
    public void generateFileClients() throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(this.getFilenameClients()))) {
            int i = 0; //représente l'ID des clients //TODO : implementer l'ID directement dans le client et le concatener sur la ligne
            for (Client c : this.getClients()) {
                StringBuilder line = new StringBuilder(); //on commence chaque nouvelle ligne par un ID des clients
                i++;
                line.append(i);
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
            if (parts.length == 11) {
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

                // Créez un objet Adresse et Vehicule temporaire, car les détails ne sont pas disponibles
                Adresse adresse = new Adresse(numeroHabitation, nomRue, codePostal, nomVille, nomPays); // Remplacez par des valeurs appropriées
                Vehicule vehicule = new Vehicule("AB-123-CD", "inconnu", "inconnu", 1111); // Remplacez par des valeurs appropriées
                Client client = new Client(nom, prenom, adresse, numeroTelephone, email, numeroCarteDebit, vehicule);
                //client.setId(id); // TODO : a modifier quand le client aura un champ ID
                this.clients.add(client);
            }
        }
    }
    /**
     * Verifie qu'un fichier de sauvegarde des clients n'existent pas encore, et si c'est le cas, crée un nouveau fichier contenant les clients inscrits au parc (présents dans le champ clients du parc
     * @throws IOException
     */
    public void loadFileClients() throws IOException{
        File file = new File(this.getFilenameClients());
        if (!file.exists()) {
            generateFileClients();
        }
    }


    //TODO : Tester fichiers Adresses
    /**
     * Genere un fichiet contenant les adresses qui sont enregistrées a ce parc de rechargement.
     * @throws IOException
     */
    public void generateFileAdresses() throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(this.getFilenameAdresses()))) {
            int i = 0; //représente l'ID des adresses //TODO : implementer l'ID directement dans l'adresse et le concatener sur la ligne
            for (Adresse a : this.getAdresses()) {
                StringBuilder line = new StringBuilder(); //on commence chaque nouvelle ligne par un ID des clients
                i++;
                line.append(i);
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
                int codePostal = Integer.parseInt(parts[3]);
                String nomVille = parts[4];
                String nomPays = parts[5];


                // Créez un objet Adresse et Vehicule temporaire, car les détails ne sont pas disponibles
                Adresse adresse = new Adresse(numeroHabitation, nomRue, codePostal, nomVille, nomPays); // Remplacez par des valeurs appropriées
                this.adresses.add(adresse);
            }
        }
    }
    /**
     * Verifie qu'un fichier de sauvegarde des clients n'existent pas encore, et si c'est le cas, crée un nouveau fichier contenant les clients inscrits au parc (présents dans le champ clients du parc
     * @throws IOException
     */
    public void loadFileAdresses() throws IOException{
        File file = new File(this.getFilenameAdresses());
        if (!file.exists()) {
            generateFileAdresses();
        }
    }

    //TODO : Tester fichiers Immatriculations
    /**
     * Genere un fichier contenant les immatriculations qui sont enregistrées a ce parc de rechargement.
     * @throws IOException
     */
    public void generateFileImmatriculations() throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(this.getFilenameImmatriculations()))) {
            int i = 0; //représente l'ID des immatriculations //TODO : implementer l'ID directement dans l'immatriculation et le concatener sur la ligne
            for (Immatriculation a : this.getImmatriculations()) {
                StringBuilder line = new StringBuilder(); //on commence chaque nouvelle ligne par un ID des immatriculations
                i++;
                line.append(i);
                line.append("\t");
                line.append(a.getLettresAvant());
                line.append("\t");
                line.append(a.getChiffres());
                line.append("\t");
                line.append(a.getLettresApres());
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
                this.immatriculations.add(immatriculation);
            }
        }
    }
    /**
     * Verifie qu'un fichier de sauvegarde des clients n'existent pas encore, et si c'est le cas, crée un nouveau fichier contenant les clients inscrits au parc (présents dans le champ clients du parc
     * @throws IOException
     */
    public void loadFileImmatriculations() throws IOException{
        File file = new File(this.getFilenameImmatriculations());
        if (!file.exists()) {
            generateFileImmatriculations();
        }
    }


    //TODO : Tester fichiers Vehicules


    public ArrayList<Borne> getBornes() {
        return bornes;
    }

    public void setBornes(ArrayList<Borne> bornes) {
        this.bornes = bornes;
    }

    public void addBorne(Borne borneAAjouter){
        this.getBornes().add(borneAAjouter);
    }

    public void getDispBornes(String date, String hour){
        //ArrayList<char> dispos = new ArrayList<char>();
        for(Borne b : this.getBornes()){
            b.checkDisponibilites(date, hour);
        }
    }
}
