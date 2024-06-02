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
        Adresse adresse = new Adresse(123, "Rue de la Paix", 75001, "Paris", "France");
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
        Adresse adresse = new Adresse(123, "Rue de la Paix", 75001, "Paris", "France");
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
        assert(parc.getClients().get(1).getNom().equals(clients.get(1).getNom()));
        assert(parc.getClients().get(2).getNom().equals(clients.get(2).getNom()));
        //assertThat(parc.getClients().get(1)).isEqualTo(clients.get(1));
        //assertThat(parc.getClients().get(2)).isEqualTo(clients.get(2));
    }
}
