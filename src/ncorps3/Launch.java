package ncorps3;

import ncorps3.bigdecimal.Corps;
import ncorps3.bigdecimal.Initialisation;
import ncorps3.bigdecimal.WriteToFile;

import java.io.IOException;
import java.math.BigDecimal;

public class Launch {
    static final int Tmax = 25;
    static final int DimXYZ = 100;
    private static Initialisation init;
    private static WriteToFile W;

    public static void main(String[] argv) throws IOException {
        double gm = -1.0;// gm < 0
        double pas = 0.1;
        BigDecimal GM = new BigDecimal(gm);
        BigDecimal PAS = new BigDecimal(pas);
        init = new Initialisation(GM, Tmax, PAS, DimXYZ);
        init.calculate();
        final String filenameX = "3corpsX.txt";
        final String filenameY = "3corpsY.txt";
        final String filenameZ = "3corpsZ.txt";
        final String pathname = "C:\\Users\\gille\\IdeaProjects\\3 Corps V2\\";
        W = new WriteToFile(pathname, filenameX);
        toFile(0);
        W.writter.close();
        W = new WriteToFile(pathname, filenameY);
        toFile(1);
        W.writter.close();
        W = new WriteToFile(pathname, filenameZ);
        toFile(2);
        W.writter.close();
        System.out.println("fin d'écriture dans les fichiers");
    }
    private static void toFile(int coord) throws IOException {
        // Xcorps1 Xcorps2 Xcorps3  -> t=0
        // ....
        // Xcorps1 Xcorps2 Xcorps3  -> t=Tmax
        Corps[][] I = init.NCorpsT0;
        for (int i = 0; i < Tmax; i++) {
            for (int j = 0; j < 3; j++) {
                W.write(I[j][i].param[coord]);W.write(";");
            }
            W.write("\n");
        }
    }


//    private static void toFile(int coord) throws IOException {
//        // Xcorps1 Xcorps2 Xcorps3  -> t=0
//        // ....
//        // Xcorps1 Xcorps2 Xcorps3  -> t=Tmax
//        Corps[][] I = init.NCorpsT0;
//        for (int i = 0; i < Tmax; i++) {
//            for (int j = 0; j < 3; j++) {
//                W.write(I[j][i].param[coord]);W.write(";");
//            }
//            W.write("\n");
//        }
//    }


//
//    private static void toFile() throws IOException {
//        Corps[][] I = init.NCorpsT0;
//        for (int i = 0; i < Tmax; i++) {
//            for (int n = 0; n < 3; n++) {
//                BigDecimal[] param = I[n][i].param;
//                W.write(param[0]);
//                W.write(";");
//                W.write(param[1]);
//                W.write(";");
//                W.write(param[2]);
//                W.write(";");
//            }
//            W.write("\n");
//        }
//
//    }

    //    private static void print(Initialisation init) {
//        Corps[][] I = init.NCorpsT0;
//        for (int i = 0; i < Tmax; i++) {
//            for (int n = 0; n < 3; n++) {
//                BigDecimal[] param = I[n][i].param;
//                System.out.print(param[0]);
//                System.out.print(" ; ");
//                System.out.print(param[1]);
//                System.out.print(" ; ");
//                System.out.print(param[2]);
//                System.out.println(" ; ");
//            }System.out.println();
//        }
//
//    }


}

