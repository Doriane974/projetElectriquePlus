package org.m1.electriquePlus;

import java.io.IOException;

import static org.m1.electriquePlus.FileManagement.*;

public class Borne {
    private int numero;
    private String timetableFileName;

    public Borne(int numero) {
        this.numero = numero;
        this.timetableFileName = "timeTableBorne"+numero;
        try {
            generateFile(timetableFileName);
        }catch (IOException e){
            System.out.println("Erreur dans la generation de l'emploi du temps de la borne "+numero+" : "+e.getMessage());
        }
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public char checkDisponibilites(String date, String hour){
        char dispos = '_';
        try {
            dispos = checkStatus(date, hour, this.timetableFileName);
            String status ="";
            switch (dispos){
                case 'D':
                    status = "Disponible";
                    break;
                case 'R':
                    status = "Reserve";
                    break;
                case 'i':
                    status = "Indisponible";
                    break;
                case 'O':
                    status = "Occupe";
                    break;
                default :
                    System.out.println("Erreur dans le fichier "+this.timetableFileName+", il y a ecrit :"+dispos);
                    break;
            }
            if(!status.equals("")){
                System.out.println("La borne "+this.numero+" est "+status);
            }

        }catch(IOException e){
            System.out.println("Erreur dans la lecture du fichier "+this.timetableFileName+" : "+e.getMessage());
        }
        return dispos;
    }

    public int changeStatusBorne(String date, String hour, String status){
        try{
            if (status.equals("DISPONIBLE")||status.equals("INDISPONIBLE")||status.equals("RESERVE")||status.equals("OCCUPE")) {
                changeStatus("" + status.charAt(0), date, hour, this.timetableFileName);
                return 0;
            }
            else{
                System.out.println("Le status "+status+" n'est pas reconnu");
                return 1;
            }
        }catch(IOException e){
            System.out.println("Errreur dans la modification du status de la borne "+this.numero+" : "+e.getMessage());
        }
        return 1;
    }
}
