/*
 * Javier Matilla Martin DNI:43840766-Y
 * Universidad de Salamanca (maatii at usal)
 * Copyright (C). All rights reserved.
 */
package apppracticafinal;

import static com.coti.tools.DiaUtil.showFinalTime;
import java.io.FileNotFoundException;
import static java.lang.System.out;
import view.View;

/**
 *
 * @author javier
 */
public class AppPracticaFinal {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws InterruptedException, FileNotFoundException, ClassNotFoundException{
        View v = new View();
        out.printf("\n----------------------------------\n");
        out.printf("\nPractica final de Programacion III\n");
        out.printf("\n----------------------------------\n");
        Thread.sleep(3000);
        out.printf("\nSe procede a intentar cargar los datos de disco...\n\n");
        v.importarTodasLasCosas();
        v.runMenuMain("\n1.- Generacion aleatoria"
                + "\n2.- Archivos"
                + "\n3.- Album"
                + "\n4.- Artista"
                + "\n5.- PlayList"
                + "\n6.- Canciones"
                + "\nh.- Menu de ayuda sobre las opciones"
                + "\nq.- Salir de la app");
        v.guardaTodo();
        out.printf("\nFinalizacion normal de programa...\n\n");
        showFinalTime();
    }//End of main()
}//End of class AppPracticaFinal
