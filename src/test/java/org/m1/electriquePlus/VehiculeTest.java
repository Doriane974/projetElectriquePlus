package org.m1.electriquePlus;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;


@DisplayName("Tests sur la classe Vehicule")
public class VehiculeTest {

    @DisplayName("Test création de véhicule avec des informations valides")
    @Test
    public void testVehiculeValide() {
        Vehicule vehicule = new Vehicule("AB-123-CD", "Tesla", "Model S", 2020);
        assertThat(vehicule.getPlaque().toString()).isEqualTo("AB-123-CD");
        assertThat(vehicule.getMarque()).isEqualTo("Tesla");
        assertThat(vehicule.getModele()).isEqualTo("Model S");
        assertThat(vehicule.getAnneeFabrication()).isEqualTo(2020);
    }

}

