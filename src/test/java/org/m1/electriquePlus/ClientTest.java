package org.m1.electriquePlus;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.m1.electriquePlus.Client;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;


@DisplayName("Tests sur la classe Client")
public class ClientTest {

    // Ici le but est de faire un test aux limites : il faudrait tester que un tableau d'une taille trop grande revoie bien une ecxeption
//    @DisplayName("Test de somme nombre très grand negatif")
//    @Test
//    public void testEnormeSommeSansRetenue() {
//        var ln = new ListesNumeriques();
//        List<Integer> nb1 = new ArrayList<>(Integer.MAX_VALUE );
//        List<Integer> nb2 = new ArrayList<>(Integer.MAX_VALUE );
//
//        assertThat(res).isEqualTo(ln.ajoute(nb1, nb2));
//   }

    @DisplayName("dummy test")
    @Test
    public void DummyTest() {
        int un = 1;
        int deux = 3-2;
        assertThat(un).isEqualTo(deux);

    }


}

