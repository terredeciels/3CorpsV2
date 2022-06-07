package ncorps3;

import java.math.BigDecimal;

import static java.lang.Math.random;

public class Calcul3Corps implements Parametres {


    public final Corps[][] ncorps;

    public Calcul3Corps() {
        CORPS.get().forEach(Calcul3Corps::init);
        ncorps = NCorpsT0;
        T.get().forEach(t -> {
            CORPS.get().forEach(n -> {
                BigDecimal[] f = {new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0")};
                CORPS.get().filter(m -> n != m).forEach(m -> {
                    BigDecimal dx = ncorps[n][t].param[0].subtract(ncorps[m][t].param[0]);
                    BigDecimal dy = ncorps[n][t].param[1].subtract(ncorps[m][t].param[1]);
                    BigDecimal dz = ncorps[n][t].param[2].subtract(ncorps[m][t].param[2]);
                    BigDecimal D = dx.multiply(dx).add(dy.multiply(dy)).add(dz.multiply(dz));
                    BigDecimal Denom = D.sqrt(mc).pow(3, mc);
                    f[0] = f[0].add(Gm.multiply(dx.divide(Denom, scale, rnd)));
                    f[1] = f[1].add(Gm.multiply(dy.divide(Denom, scale, rnd)));
                    f[2] = f[2].add(Gm.multiply(dz.divide(Denom, scale, rnd)));
                    BigDecimal[] param = new BigDecimal[6];
                    param[0] = (new BigDecimal("0.5")).multiply(f[0].multiply(pas.pow(2)))
                            .add((ncorps[n][t].param[3]).multiply(pas)).add(ncorps[n][t].param[0]);
                    param[1] = (new BigDecimal("0.5")).multiply(f[1].multiply(pas.pow(2)))
                            .add((ncorps[n][t].param[4]).multiply(pas)).add(ncorps[n][t].param[1]);
                    param[2] = (new BigDecimal("0.5")).multiply(f[2].multiply(pas.pow(2)))
                            .add((ncorps[n][t].param[5]).multiply(pas)).add(ncorps[n][t].param[2]);
                    param[3] = f[0].multiply(pas).add(ncorps[n][t].param[3]);
                    param[4] = f[1].multiply(pas).add(ncorps[n][t].param[4]);
                    param[5] = f[2].multiply(pas).add(ncorps[n][t].param[5]);
                    Corps corps = new Corps();
                    corps.param = param;
                    ncorps[n][t + 1] = corps;
                });

            });

        });

    }


    private static void init(int n) {
        Corps corps = new Corps();
        COORD.get().forEach(c -> corps.param[c] = new BigDecimal(random() * DimXYZ));
        VIT.get().forEach(c -> corps.param[c] = new BigDecimal(0));
        NCorpsT0[n][t0] = corps;
    }


    public static class Corps {

        public BigDecimal[] param;

        public Corps() {
            param = new BigDecimal[6];
        }

    }
}

