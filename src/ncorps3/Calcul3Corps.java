package ncorps3;

import java.math.BigDecimal;
import java.util.function.BiFunction;
import java.util.function.Function;

import static java.lang.Math.random;

public class Calcul3Corps implements Parametres {


    public final Corps[][] ncorps;
    BigDecimal[] d = new BigDecimal[3];
    Function<Integer, BigDecimal> c = j -> d[j].pow(2, mc);
    BiFunction<Integer, Corps,BigDecimal> NC = (i,x) -> x.param[i];

    BigDecimal zero = new BigDecimal("0.0");
    BigDecimal demi = new BigDecimal("0.5");

    public Calcul3Corps() {
        CORPS.get().forEach(Calcul3Corps::init);
        ncorps = NCorpsT0;
        T.get().forEach(t -> CORPS.get().forEach(n -> {
            BigDecimal[] f = new BigDecimal[]{zero, zero, zero};
            CORPS.get().filter(m -> n != m).forEach(m -> {
                Corps corpsn = ncorps[n][t];
                Corps corpsm = ncorps[m][t];

                CORPS.get().forEach(i -> d[i] = corpsn.param[i].subtract(corpsm.param[i]));
                BigDecimal Denom = c.apply(0).add(c.apply(1)).add(c.apply(2)).sqrt(mc).pow(3, mc);
                CORPS.get().forEach(i -> f[i] = f[i].add(Gm.multiply(d[i].divide(Denom, scale, rnd))));

                Corps corps = new Corps();

                CORPS.get().forEach(i -> corps.param[i] = demi.multiply(f[i].multiply(pas.pow(2)))
                        .add((corpsn.param[i + 3]).multiply(pas)).add(corpsn.param[i]));
                CORPS.get().forEach(i -> corps.param[i + 3] = f[i].multiply(pas).add(corpsn.param[i + 3]));

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

