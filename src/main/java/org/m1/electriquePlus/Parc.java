package org.m1.electriquePlus;

import java.io.*;
import java.util.ArrayList;

public class Parc {
    ArrayList<Borne> bornes ;
    ArrayList<Client> clients;
    private String filenameClients;

    public Parc() {
        filenameClients = "ClientsSave";
    }

    public Parc(ArrayList<Borne> bornes) {
        this.bornes = bornes;
        filenameClients = "ClientsSave";
    }

    public String getFilenameClients(){
        return filenameClients;
    }

    public void setClients(ArrayList<Client> clients){
        this.clients = clients;
    }

    public ArrayList<Client> getClients() {
        return clients;
    }

    public void setFilenameClients(String filenameClients){
        this.filenameClients = filenameClients;
    }

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
