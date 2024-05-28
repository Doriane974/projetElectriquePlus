package org.m1.electriquePlus;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
//import org.m1.electriquePlus.Borne;

import static org.assertj.core.api.Assertions.assertThat;

public class BorneTest {
    @DisplayName("Test création d'une Borne avec numero")
    @Test
    public void testCreationBorne(){
        Borne borne1 = new Borne(1);
        assertThat(borne1.getNumero()).isEqualTo(1);
    }

    @DisplayName("Test checkDisponibilite")
    @Test
    public void testCheckDisponibilites(){
        Borne borne1 = new Borne(1);
        assertThat(borne1.checkDisponibilites("01/06", "05")).isEqualTo('D');
    }

    @DisplayName("Test changeStatusBorne")
    @Test
    public void testChangeStatusBorne(){
        Borne borne1 = new Borne(1);

        assertThat(borne1.changeStatusBorne("02/06", "05", "OCCUPE")).isEqualTo(0);
        assertThat(borne1.checkDisponibilites("02/06", "05")).isEqualTo('O');
    }

    @DisplayName("Test changeStatusBorne with wrong status")
    @Test
    public void testChangeStatusBorneFail(){
        Borne borne1 = new Borne(1);

        assertThat(borne1.changeStatusBorne("02/06", "05", "PAS DISPONIBLE")).isEqualTo(1);
        assertThat(borne1.checkDisponibilites("02/06", "05")).isEqualTo('D');
    }





}
