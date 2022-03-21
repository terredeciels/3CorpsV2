package ncorps3;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;

public interface Parametres {
    int NbCorps = 3;
    int Tmax = 200;
    int DimXYZ = 100;
    BigDecimal Gm = new BigDecimal("-1.0");
    BigDecimal pas = new BigDecimal("0.1");

    int scale = 16;
    int precision = 16;
    MathContext mc = new MathContext(precision);
    RoundingMode rnd = RoundingMode.HALF_DOWN;

    Calcul3Corps.Corps[][] NCorpsT0 = new Calcul3Corps.Corps[3][Tmax];

    String pathname = "C:\\Users\\gille\\IdeaProjects\\3 Corps V2\\";
    String filename = "3corpsX";

}
