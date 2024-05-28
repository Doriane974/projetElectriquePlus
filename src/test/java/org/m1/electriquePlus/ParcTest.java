package org.m1.electriquePlus;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

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
        parc.getDispBornes("01/06", "03");
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
}