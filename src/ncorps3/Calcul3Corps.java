package ncorps3;

import java.math.BigDecimal;

import static java.lang.Math.random;

public class Calcul3Corps implements Parametres {


    public final Corps[][] ncorps;

    public Calcul3Corps() {
        CORPS.get().forEach(Calcul3Corps::init);
        ncorps = NCorpsT0;
        T.get().forEach(t -> CORPS.get().forEach(n -> {
            BigDecimal[] f = new BigDecimal[]{new BigDecimal("0.0"), new BigDecimal("0.0"), new BigDecimal("0.0")};
            CORPS.get().filter(m -> n != m).forEach(m -> {
                BigDecimal[] d = new BigDecimal[3];
                CORPS.get().forEach(i -> d[i] = ncorps[n][t].param[i].subtract(ncorps[m][t].param[i]));
                BigDecimal D = d[0].multiply(d[0]).add(d[1].multiply(d[1])).add(d[2].multiply(d[2]));
                BigDecimal Denom = D.sqrt(mc).pow(3, mc);
                CORPS.get().forEach(i -> f[i] = f[i].add(Gm.multiply(d[i].divide(Denom, scale, rnd))));

                Corps corps = new Corps();
                CORPS.get().forEach(i -> corps.param[i] = (new BigDecimal("0.5")).multiply(f[i].multiply(pas.pow(2)))
                        .add((ncorps[n][t].param[i + 3]).multiply(pas)).add(ncorps[n][t].param[i]));
                CORPS.get().forEach(i -> corps.param[i + 3] = f[i].multiply(pas).add(ncorps[n][t].param[i + 3]));

                ncorps[n][t + 1] = corps;
            });

        }));

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

