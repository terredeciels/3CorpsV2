package ncorps3;

import java.math.BigDecimal;
import java.util.function.Supplier;
import java.util.stream.IntStream;

import static java.lang.Math.random;
import static java.util.stream.IntStream.range;

public class Calcul3Corps implements Parametres {


    public final Corps[][] ncorps;
    public IntStream INCorps = range(0, NbCorps);
    static Supplier<IntStream> ICoord = () -> range(0, 3);
    static Supplier<IntStream> IVit = () -> range(3, 6);


    public Calcul3Corps() {
        // coord aléatoires, vitesses nulles à t=0
        INCorps.forEach(Calcul3Corps::init);
        ncorps = NCorpsT0;
        for (int k = 0; k < Tmax - 1; k++)
            for (int n = 0; n < 3; n++) { //corps n
                BigDecimal fx = new BigDecimal("0.0");
                BigDecimal fy = new BigDecimal("0.0");
                BigDecimal fz = new BigDecimal("0.0");
                for (int m = 0; m < 3; m++)
                    if (n != m) {

                        BigDecimal dx = ncorps[n][k].param[0].subtract(ncorps[m][k].param[0]);
                        BigDecimal dy = ncorps[n][k].param[1].subtract(ncorps[m][k].param[1]);
                        BigDecimal dz = ncorps[n][k].param[2].subtract(ncorps[m][k].param[2]);

                        // VOIR MathContext mc de pow
                        BigDecimal D = dx.multiply(dx).add(dy.multiply(dy)).add(dz.multiply(dz));

                        BigDecimal Denom = D.sqrt(mc).pow(3, mc);

                        fx = fx.add(Gm.multiply(dx.divide(Denom, scale, rnd)));
                        fy = fy.add(Gm.multiply(dy.divide(Denom, scale, rnd)));
                        fz = fz.add(Gm.multiply(dz.divide(Denom, scale, rnd)));

                        BigDecimal[] param = new BigDecimal[6];
                        param[0] = (new BigDecimal("0.5")).multiply(fx.multiply(pas.pow(2)))
                                .add((ncorps[n][k].param[3]).multiply(pas)).add(ncorps[n][k].param[0]);
                        param[1] = (new BigDecimal("0.5")).multiply(fy.multiply(pas.pow(2)))
                                .add((ncorps[n][k].param[4]).multiply(pas)).add(ncorps[n][k].param[1]);
                        param[2] = (new BigDecimal("0.5")).multiply(fz.multiply(pas.pow(2)))
                                .add((ncorps[n][k].param[5]).multiply(pas)).add(ncorps[n][k].param[2]);

                        param[3] = fx.multiply(pas).add(ncorps[n][k].param[3]);
                        param[4] = fy.multiply(pas).add(ncorps[n][k].param[4]);
                        param[5] = fz.multiply(pas).add(ncorps[n][k].param[5]);


                        Corps corps = new Corps();
                        corps.param = param;
                        ncorps[n][k + 1] = corps;
                    }
            }
    }


    private static void init(int n) {
        Corps corps = new Corps();
        ICoord.get().forEach(c -> corps.param[c] = new BigDecimal(random() * DimXYZ));
        IVit.get().forEach(c -> corps.param[c] = new BigDecimal(0));
        NCorpsT0[n][t0] = corps;
    }


    public static class Corps {

        public BigDecimal[] param;

        public Corps() {
            param = new BigDecimal[6];
        }

    }
}

