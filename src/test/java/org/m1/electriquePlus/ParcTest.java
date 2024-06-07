package org.m1.electriquePlus;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;
import static org.assertj.core.api.Assertions.assertThat;

public class ParcTest {

    @DisplayName("Test constructeur avec bornes")
    @Test
    public void constructeurPArcBorneTest(){
        Borne borne1 = new Borne(1);
        Borne borne2 = new Borne(2);
        ArrayList<Borne> tabBornes= new ArrayList<>();
        tabBornes.add(borne1);
        tabBornes.add(borne2);
        Parc parc = new Parc(tabBornes);
        assertThat(parc.getBornes()).isEqualTo(tabBornes);

    }

    @DisplayName("Test recuperer les bornes")
    @Test
    public void getBornesTest(){
        Borne borne1 = new Borne(1);
        Borne borne2 = new Borne(2);
        Parc parc = new Parc();
        ArrayList<Borne> tabBornes= new ArrayList<>();
        tabBornes.add(borne1);
        tabBornes.add(borne2);
        parc.setBornes(tabBornes);
        assertThat(parc.getBornes()).isEqualTo(tabBornes);

    }

    @DisplayName("Test recuperer les disponibilités des bornes")
    @Test
    public void getDispBornesTest(){
        Borne borne3 = new Borne(3);
        Borne borne4 = new Borne(4);
        Parc parc = new Parc();
        ArrayList<Borne> tabBornes= new ArrayList<>();
        tabBornes.add(borne3);
        tabBornes.add(borne4);
        parc.setBornes(tabBornes);
        parc.getDispBornes("01/08", "03");
    }

    @DisplayName("Test ajouter une borne au parc")
    @Test
    public void getAddBorne(){
        Borne borne1 = new Borne(1);
        Borne borne2 = new Borne(2);
        Borne borne3 = new Borne(3);
        Parc parc = new Parc();
        ArrayList<Borne> tabBornes= new ArrayList<>();
        tabBornes.add(borne1);
        tabBornes.add(borne2);
        parc.setBornes(tabBornes);
        assertThat(parc.getBornes()).isEqualTo(tabBornes);
        parc.addBorne(borne3);
        tabBornes.add(borne3);
        assertThat(parc.getBornes()).isEqualTo(tabBornes);
    }

    @DisplayName("Test de loadFileClients")
    @Test
    public void testLoadClients(){
        Parc parc = new Parc();
        Adresse adresse = new Adresse(123, "Rue de la Paix", "75001", "Paris", "France");
        Vehicule vehicule = new Vehicule("AB-123-CD","marque","modele",2022);
        Client c1 = new Client("Dupont", "Jean", adresse, "1234567890", "jean.dupont@example.com", "1234567812345678", vehicule);
        Client c2 = new Client("Bedier", "Janick", adresse, "1234567890", "jean.dupont@example.com", "1234567812345678", vehicule);
        Client c3 = new Client("Barale", "Monique", adresse, "1234567890", "jean.dupont@example.com", "1234567812345678", vehicule);
        ArrayList<Client> clients = new ArrayList<>();
        clients.add(c1);
        clients.add(c2);
        clients.add(c3);
        parc.setClients(clients);
        try {
            parc.loadFileClients();
        } catch (IOException e){
            System.out.println("Probleme dans la creation du fichier "+parc.getFilenameClients()+" : "+e.getMessage());
        }
    }

    @DisplayName("Test de getClientsFromFile")
    @Test
    public void testGetClientsFromFile(){
        Parc parc = new Parc();
        Adresse adresse = new Adresse(123, "Rue de la Paix", "75001", "Paris", "France");
        Vehicule vehicule = new Vehicule("AB-123-CD","marque","modele",2022);
        Client c1 = new Client("Dupont", "Jean", adresse, "1234567890", "jean.dupont@example.com", "1234567812345678", vehicule);
        Client c2 = new Client("Bedier", "Janick", adresse, "1234567890", "jean.dupont@example.com", "1234567812345678", vehicule);
        ArrayList<Client> clients = new ArrayList<>();
        clients.add(c1);
        clients.add(c2);
        parc.setClients(clients);
        try {
            System.out.println("On load le fichier clients");
            parc.loadFileClients();
            parc.setClients(new ArrayList<>());
        } catch (IOException e){
            System.out.println("Probleme dans la creation du fichier "+parc.getFilenameClients()+" : "+e.getMessage());
        }
        try {
            parc.getClientsFromFile();
        } catch (IOException e){
            System.out.println("Probleme dans la creation du fichier "+parc.getFilenameClients()+" : "+e.getMessage());
        }


        assert(parc.getClients().get(0).getNom().equals(clients.get(0).getNom()));
        assert(parc.getClients().get(0).getPrenom().equals(clients.get(0).getPrenom()));
        assert(parc.getClients().get(0).getNumeroTelephone().equals(clients.get(0).getNumeroTelephone()));
        assert(parc.getClients().get(0).getEmail().equals(clients.get(0).getEmail()));
        assert(parc.getClients().get(0).getNumeroCarteDebit().equals(clients.get(0).getNumeroCarteDebit()));
        assert(parc.getClients().get(0).getVehicule().getMarque().equals(clients.get(0).getVehicule().getMarque()));
        assert(parc.getClients().get(0).getVehicule().getAnneeFabrication() == clients.get(0).getVehicule().getAnneeFabrication());
        assert(parc.getClients().get(0).getVehicule().getModele().equals(clients.get(0).getVehicule().getModele()));
        assert(parc.getClients().get(0).getVehicule().getPlaque().getLettresAvant().equals(clients.get(0).getVehicule().getPlaque().getLettresAvant()));
        assert(parc.getClients().get(0).getVehicule().getPlaque().getLettresApres().equals(clients.get(0).getVehicule().getPlaque().getLettresApres()));
        assert(parc.getClients().get(0).getVehicule().getPlaque().getChiffres() == clients.get(0).getVehicule().getPlaque().getChiffres());

        assert(parc.getClients().get(1).getNom().equals(clients.get(1).getNom()));
        assert(parc.getClients().get(1).getPrenom().equals(clients.get(1).getPrenom()));
        assert(parc.getClients().get(1).getNumeroTelephone().equals(clients.get(1).getNumeroTelephone()));
        assert(parc.getClients().get(1).getEmail().equals(clients.get(1).getEmail()));
        assert(parc.getClients().get(1).getNumeroCarteDebit().equals(clients.get(1).getNumeroCarteDebit()));
        assert(parc.getClients().get(1).getVehicule().getMarque().equals(clients.get(1).getVehicule().getMarque()));
        assert(parc.getClients().get(1).getVehicule().getAnneeFabrication() == clients.get(1).getVehicule().getAnneeFabrication());
        assert(parc.getClients().get(1).getVehicule().getModele().equals(clients.get(1).getVehicule().getModele()));
        assert(parc.getClients().get(1).getVehicule().getPlaque().getLettresAvant().equals(clients.get(1).getVehicule().getPlaque().getLettresAvant()));
        assert(parc.getClients().get(1).getVehicule().getPlaque().getLettresApres().equals(clients.get(1).getVehicule().getPlaque().getLettresApres()));
        assert(parc.getClients().get(1).getVehicule().getPlaque().getChiffres() == clients.get(1).getVehicule().getPlaque().getChiffres());

    }

    @DisplayName("Test de getAdressesFromFile")
    @Test
    public void testGetAdressesFromFile(){
        Parc parc = new Parc();
        Adresse a1 = new Adresse(123, "Rue de la Paix", "75001", "Paris", "France");
        Adresse a2 = new Adresse(18, "Route des eucalyptus", "97417", "Saint-Denis", "France");

        ArrayList<Adresse> adresses = new ArrayList<>();
        adresses.add(a1);
        adresses.add(a2);
        parc.setAdresses(adresses);
        try {
            System.out.println("On load le fichier adresses");
            parc.loadFileAdresses();
            parc.setAdresses(new ArrayList<>());
        } catch (IOException e){
            System.out.println("Probleme dans la creation du fichier "+parc.getFilenameAdresses()+" : "+e.getMessage());
        }
        try {
            parc.getAdressesFromFile();
        } catch (IOException e){
            System.out.println("Probleme dans la creation du fichier "+parc.getFilenameAdresses()+" : "+e.getMessage());
        }

        assert(parc.getAdresses().get(0).getNumeroHabitation() == adresses.get(0).getNumeroHabitation());
        assert(parc.getAdresses().get(0).getCodePostal().equals(adresses.get(0).getCodePostal()));
        assert(parc.getAdresses().get(0).getNomPays().equals(adresses.get(0).getNomPays()));
        assert(parc.getAdresses().get(0).getNomRue().equals(adresses.get(0).getNomRue()));
        assert(parc.getAdresses().get(0).getNomVille().equals(adresses.get(0).getNomVille()));

        assert(parc.getAdresses().get(1).getNumeroHabitation() == adresses.get(1).getNumeroHabitation());
        assert(parc.getAdresses().get(1).getCodePostal().equals(adresses.get(1).getCodePostal()));
        assert(parc.getAdresses().get(1).getNomPays().equals(adresses.get(1).getNomPays()));
        assert(parc.getAdresses().get(1).getNomRue().equals(adresses.get(1).getNomRue()));
        assert(parc.getAdresses().get(1).getNomVille().equals(adresses.get(1).getNomVille()));


    }

    @DisplayName("Test de getImmatriculationsFromFile")
    @Test
    public void testGetImmatriculationsFromFile(){
        Parc parc = new Parc();
        Immatriculation i1 = new Immatriculation("AA-838-DQ");
        Immatriculation i2 = new Immatriculation("EA-176-SS");

        ArrayList<Immatriculation> immatriculations = new ArrayList<>();
        immatriculations.add(i1);
        immatriculations.add(i2);
        parc.setImmatriculations(immatriculations);
        try {
            System.out.println("On load le fichier immatriculations");
            parc.loadFileImmatriculations();
            parc.setImmatriculations(new ArrayList<>());
        } catch (IOException e){
            System.out.println("Probleme dans la creation du fichier "+parc.getFilenameImmatriculations()+" : "+e.getMessage());
        }
        try {
            parc.getImmatriculationsFromFile();
        } catch (IOException e){
            System.out.println("Probleme dans la creation du fichier "+parc.getFilenameImmatriculations()+" : "+e.getMessage());
        }


        assert(parc.getImmatriculations().get(0).getChiffres() == immatriculations.get(0).getChiffres());
        assert(parc.getImmatriculations().get(0).getLettresAvant().equals(immatriculations.get(0).getLettresAvant()));
        assert(parc.getImmatriculations().get(0).getLettresApres().equals(immatriculations.get(0).getLettresApres()));

        assert(parc.getImmatriculations().get(1).getChiffres() == immatriculations.get(1).getChiffres());
        assert(parc.getImmatriculations().get(1).getLettresAvant().equals(immatriculations.get(1).getLettresAvant()));
        assert(parc.getImmatriculations().get(1).getLettresApres().equals(immatriculations.get(1).getLettresApres()));



    }

    @DisplayName("Test de getImmatriculationsFromFile")
    @Test
    public void testGetVehiculeFromFile(){
        Parc parc = new Parc();
        //Immatriculation i1 = new Immatriculation("AA-838-DQ");
        Immatriculation i2 = new Immatriculation("EA-176-SS");
        Vehicule v1 = new Vehicule("AA-838-DQ", "Pontiac","Firebird",1967);
        Vehicule v2 = new Vehicule("EA-176-SS", "Honda","Nsx",2015);

        ArrayList<Vehicule> vehicules = new ArrayList<>();
        vehicules.add(v1);
        vehicules.add(v2);
        parc.setVehicules(vehicules);
        try {
            System.out.println("On load le fichier vehicules");
            parc.loadFileVehicules();
            parc.setVehicules(new ArrayList<>());
        } catch (IOException e){
            System.out.println("Probleme dans la creation du fichier "+parc.getFilenameVehicules()+" : "+e.getMessage());
        }
        try {
            parc.getVehiculesFromFile();
        } catch (IOException e){
            System.out.println("Probleme dans la creation du fichier "+parc.getFilenameVehicules()+" : "+e.getMessage());
        }


        assert(parc.getVehicules().get(0).getPlaque().equals(vehicules.get(0).getPlaque()));
        assert(parc.getVehicules().get(0).getModele().equals(vehicules.get(0).getModele()));
        assert(parc.getVehicules().get(0).getMarque().equals(vehicules.get(0).getMarque()));
        assert(parc.getVehicules().get(0).getAnneeFabrication() == vehicules.get(0).getAnneeFabrication());

        assert(parc.getVehicules().get(1).getPlaque().equals(vehicules.get(1).getPlaque()));
        assert(parc.getVehicules().get(1).getModele().equals(vehicules.get(1).getModele()));
        assert(parc.getVehicules().get(1).getMarque().equals(vehicules.get(1).getMarque()));
        assert(parc.getVehicules().get(1).getAnneeFabrication() == vehicules.get(1).getAnneeFabrication());

    }
}
