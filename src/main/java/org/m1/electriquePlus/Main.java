package org.m1.electriquePlus;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    private static Scanner scanner = new Scanner(System.in);
    private static List<Gestionnaire> gestionnaires = new ArrayList<>();
    private static List<Client> clients = new ArrayList<>();

    public static void main(String[] args){
        //creation de fred, le gestionnaire
        Gestionnaire fred = new Gestionnaire("Dupont", "fred");
        gestionnaires.add(fred);

        //création d'un client lambda
        Adresse adressePaul = new Adresse(71, "Rue du bois", 54000, "Nancy", "France" );
        Client paul = new Client("Dupont", "Paul", adressePaul, "0601020304", "Dupont.Fred@ElectriquePlus.fr", "1234567890");
        clients.add(paul);

        //création de l'application en ligne de commande

        while (true) {
                System.out.println("1. Se connecter en tant que gestionnaire");
                System.out.println("2. Se connecter en tant que client");
                System.out.println("3. Quitter");
                int choix = scanner.nextInt();
                scanner.nextLine();

                switch (choix) {
                    case 1:
                        gestionnaireMenu();
                        break;
                    case 2:
                        //clientMenu();
                        break;
                    case 3:
                        System.exit(0);
                        break;
                    default:
                        System.out.println("Choix invalide");
                }
            }
        }

    private static void gestionnaireMenu() {
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
            return;
        }

        System.out.println("Bienvenue, " + gestionnaire.getNom() + " " + gestionnaire.getPrenom());
        System.out.println("Voici la liste des clients inscrit dans le parc:");
        for (Client c : clients) {
            System.out.println(c.toString());
        }
    }




}
