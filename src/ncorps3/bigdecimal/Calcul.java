package ncorps3.bigdecimal;

import java.math.BigDecimal;

import static java.lang.Math.random;

public class Calcul implements Parametres {

    public final Corps[][] ncorps;

    public Calcul() {
        initialisation();
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

    private void initialisation() {
        for (int n = 0; n < NbCorps; n++) {
            Corps corps = new Corps();
            BigDecimal[] param = new BigDecimal[6];
            for (int c = 0; c < 6; c++) param[c] = new BigDecimal(random() * DimXYZ);
            corps.param = param;
            NCorpsT0[n][0] = corps;
        }
    }
}

