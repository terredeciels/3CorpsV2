package ncorps3;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigDecimal;

public class Main implements Parametres {

    public static void main(String[] argv) throws IOException {

        Calcul3Corps calculs = new Calcul3Corps();

        WriteToFile W;
        for (int coord = 0; coord < NbCorps; coord++) {
            W = new WriteToFile(pathname, filename + coord + ".txt");
            toFile(calculs, W, coord);
            W.writter.close();
        }
        System.out.println("fin d'écriture dans les fichiers");
    }

    private static void toFile(Calcul3Corps calculs, WriteToFile W, int coord) throws IOException {
        Calcul3Corps.Corps[][] I = calculs.ncorps;
        for (int i = 0; i < Tmax; i++) {
            for (int j = 0; j < 3; j++) {
                W.write(I[j][i].param[coord]);
                W.write(";");
            }
            W.write("\n");
        }
    }

    public static class WriteToFile {

        public final FileWriter writter;

        public WriteToFile(String pathname, String filename) throws IOException {
            File toFile = new File(pathname + filename);
            boolean existe = toFile.createNewFile();
            if (existe) System.out.println("Le fichier existe");
            writter = new FileWriter(filename);
        }

        public void write(BigDecimal bd) throws IOException {
            writter.write(String.valueOf(bd));
        }

        public void write(String s) throws IOException {
            writter.write(s);
        }

    }

}