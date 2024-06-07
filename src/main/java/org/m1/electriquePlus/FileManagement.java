package org.m1.electriquePlus;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


/**
 * La classe {@code FileManagement} gère les opérations de génération, de lecture et de modification de fichiers.
 */
public class FileManagement {

    /**
     * Génère un fichier avec des données de bornes pour une période de 3 mois à partir de la date actuelle.
     *
     * @param filename Le nom du fichier à générer.
     * @throws IOException Si une erreur d'entrée/sortie se produit.
     */
    public static void generateFileBorne(String filename) throws IOException {
        LocalDate startDate = LocalDate.now();
        LocalDate endDate = startDate.plusMonths(3);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM");

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
            for (LocalDate date = startDate; date.isBefore(endDate); date = date.plusDays(1)) {
                StringBuilder line = new StringBuilder(date.format(formatter));
                for (int hour = 0; hour < 24; hour++) {
                    line.append("\t").append(String.format("%02dD", hour));
                }
                writer.write(line.toString());
                writer.newLine();
            }
        }
    }

    /*
    public static void loadFileClient(String filename){}

    public static void generateFileVehicule(String filename){}
    public static void loadFileVehicule(){}

    public static void generateFileReservation(String filename){}
    public static void loadFileReservation(){}

    public static void generateFileAddresse(){}
    public static void loadFileAddresse(){}
    */







    /**
     * Change le statut d'une borne à une date et heure spécifiées dans un fichier.
     *
     * @param statusToChange Le statut à changer.
     * @param date           La date pour laquelle changer le statut (format "dd/MM").
     * @param hour           L'heure pour laquelle changer le statut (de 0 à 23).
     * @param filename       Le nom du fichier contenant les données des bornes.
     * @throws IOException Si une erreur d'entrée/sortie se produit.
     */
    public static void changeStatus(String statusToChange, String date, String hour, String filename) throws IOException {
        // Lire toutes les lignes du fichier
        List<String> lines = Files.readAllLines(Paths.get(filename));
        final String changedStatus = hour+statusToChange;

        // Préparer le nouveau contenu
        List<String> updatedLines = lines.stream()
                .map(line -> {
                    if (line.startsWith(date)) {
                        System.out.println("on a trouvé la date voulue");
                        String[] parts = line.split("\t"); //on a chaque 03D par exemple dans parts
                        int hourIndex = Integer.parseInt(hour) + 1; // +1 car le premier élément est la date
                        System.out.println("l'heure a modifié est à l'emplacement  : "+hourIndex);
                        if (hourIndex < parts.length) {
                            System.out.println("l'heure a modifié est : "+parts[hourIndex]);
                            parts[hourIndex] = changedStatus;
                            System.out.println("on le modifie en : "+changedStatus);
                        }
                        return String.join("\t", parts);
                    } else {
                        return line;
                    }
                })
                .collect(Collectors.toList());

        // Réécrire le fichier avec le contenu mis à jour
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
            for (String updatedLine : updatedLines) {
                writer.write(updatedLine);
                writer.newLine();
            }
        }
    }

    /**
     * Vérifie le statut d'une borne à une date et heure spécifiées dans un fichier.
     *
     * @param date     La date pour laquelle vérifier le statut (format "dd/MM").
     * @param hour     L'heure pour laquelle vérifier le statut (de 0 à 23).
     * @param filename Le nom du fichier contenant les données des bornes.
     * @return Le statut de la borne à la date et heure spécifiées.
     * @throws IOException                Si une erreur d'entrée/sortie se produit.
     * @throws IllegalArgumentException   Si la date ou l'heure n'est pas valide.
     */
    public static char checkStatus(String date, String hour, String filename) throws IOException {
        List<String> lines = Files.readAllLines(Paths.get(filename));

        for (String line : lines) {
            if (line.startsWith(date)) {
                String[] parts = line.split("\t");
                int hourIndex = Integer.parseInt(hour) + 1; // +1 car le premier élément est la date
                if (hourIndex < parts.length) {
                    return parts[hourIndex].charAt(parts[hourIndex].length() - 1);
                } else {
                    throw new IllegalArgumentException("Invalid hour index: " + hour);
                }
            }
        }
        throw new IllegalArgumentException("Date not found: " + date);
    }
}

