package ncorps3;

import ncorps3.bigdecimal.Calcul;
import ncorps3.bigdecimal.Corps;
import ncorps3.bigdecimal.Parametres;
import ncorps3.bigdecimal.WriteToFile;

import java.io.IOException;

public class Launch implements Parametres {

    public static void main(String[] argv) throws IOException {

        Calcul calculs = new Calcul();

        WriteToFile W;
        for (int coord = 0; coord < NbCorps; coord++) {
            W = new WriteToFile(pathname, filename + coord + ".txt");
            toFile(calculs, W, coord);
            W.writter.close();
        }
        System.out.println("fin d'écriture dans les fichiers");
    }

    private static void toFile(Calcul calculs, WriteToFile W, int coord) throws IOException {
        Corps[][] I = calculs.ncorps;
        for (int i = 0; i < Tmax; i++) {
            for (int j = 0; j < 3; j++) {
                W.write(I[j][i].param[coord]);
                W.write(";");
            }
            W.write("\n");
        }
    }
}