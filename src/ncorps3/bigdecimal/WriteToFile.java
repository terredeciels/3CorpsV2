package ncorps3.bigdecimal;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigDecimal;

public class WriteToFile {

    public final FileWriter writter;

    public WriteToFile(String pathname, String filename) throws IOException {
        File toFile = new File(pathname + filename);
        // if (toFile.createNewFile()) System.out.println("Fichier: " + toFile.getName());
        // else System.out.println("Le fichier existe");
        boolean existe = toFile.createNewFile();
        writter = new FileWriter(filename);
        // writter.close();
        // System.out.println("Succes");
    }

    public void write(BigDecimal bd) throws IOException {

        writter.write(String.valueOf(bd));

    }

    public void write(String s) throws IOException {

        writter.write(s);

    }

}
