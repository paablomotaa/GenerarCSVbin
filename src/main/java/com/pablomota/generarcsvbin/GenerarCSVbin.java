package com.pablomota.generarcsvbin;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class GenerarCSVbin {
    public static final String NOM_FICH_SALIDA = "prod.dat";
    public static final String NOM_FICH_CSV = "salida.csv";
    //He creado varios métodos y una clase abstracta por facilitarme un poco. :)
    public static void main(String[] args) throws IOException {
        GenerarDAT();
        GenerarCSV();
    }
    public static void GenerarDAT(){
        Object[][] datosProductos = {
            {5, "LAPIZ", 0.62},
            {10, "CUADERNO", 3.40}
        };
        GenerarDat.GenerateDAT(datosProductos);
        GenerarDat.ReadDAT();
    }
    
    public static void GenerarCSV(){ //Implementación para la creación del csv
        try (FileInputStream is = new FileInputStream(NOM_FICH_SALIDA);
             DataInputStream dis = new DataInputStream(is);
             BufferedWriter writer = new BufferedWriter(new FileWriter(NOM_FICH_CSV))) {

            for (int i = 0; dis.available() > 0; i++) {
                int cod = dis.readInt();
                String descr = dis.readUTF();
                double prUnit = dis.readDouble();
                writer.write(cod + "|" + descr + "|" + prUnit);
                writer.newLine();
            }
        } catch (EOFException ex) {
            System.out.println("ERROR: Fin de fichero alcanzado");
        } catch (IOException ex) {
            System.out.printf("ERROR: %s.\n", ex.getMessage());
        }
    }
}
