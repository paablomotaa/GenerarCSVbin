package com.pablomota.generarcsvbin;

import static com.pablomota.generarcsvbin.GenerarCSVbin.NOM_FICH_SALIDA;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

abstract class GenerarDat {
    
    static void GenerateDAT(Object[][] datosProductos){
        try (FileOutputStream os = new FileOutputStream(NOM_FICH_SALIDA);
        DataOutputStream dos = new DataOutputStream(os)) {
        for (Object[] datosUnProd : datosProductos) {
            dos.writeInt((Integer) datosUnProd[0]);
            dos.writeUTF((String) datosUnProd[1]);
            dos.writeDouble((Double) datosUnProd[2]);
        }
        } catch (IOException ex) {
        System.out.printf("ERROR: escribiendo a fichero: %s\n", ex.getMessage());
        }
    }
    public static void ReadDAT(){ //Leer un dat original
        try(FileInputStream is = new FileInputStream(NOM_FICH_SALIDA);
        DataInputStream dis = new DataInputStream(is)) {

        for(int i=0; dis.available() > 0; i++) {
        int cod = dis.readInt();
        String descr = dis.readUTF();
        double prUnit = dis.readDouble();
        System.out.printf("Prod. %d: %d,\"%s\", %f\n", i, cod, descr, prUnit);
        }
        } catch (EOFException ex) {
        System.out.println("ERROR: Fin de fichero alcanzado");
        } catch (IOException ex) {
        System.out.printf("ERROR: %s.\n", ex.getMessage());
        }
    }
}
