package org.m1.electriquePlus;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.platform.commons.function.Try;
import org.m1.electriquePlus.FileManagement;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import static org.assertj.core.api.Assertions.assertThat;


import static org.m1.electriquePlus.FileManagement.*;

public class FileManagementTest {
    @BeforeAll
    public static void setup() {
        try {
            generateFile("output.txt");
            System.out.println("File generated successfully.");
        } catch (IOException e) {
            System.err.println("Error generating file: " + e.getMessage());
        }
    }

    @DisplayName("Test de changeStatus")
    @Test
    public void testChangeStatus(){
        try{
        changeStatus("R", "28/05", "01", "output.txt");
        }catch(IOException e) {
            System.err.println("Error modifying file: " + e.getMessage());
        }
    }

    @DisplayName("Test de checkStatus")
    @Test
    public void testCheckStatus(){
        try{
            changeStatus("R", "29/05", "02", "output.txt");
            assertThat(checkStatus("29/05", "02", "output.txt")).isEqualTo('R');
        }catch(IOException e) {
            System.err.println("Error modifying file: " + e.getMessage());
        }
    }


}
