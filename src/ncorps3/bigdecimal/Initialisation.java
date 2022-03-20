package ncorps3.bigdecimal;

import java.math.BigDecimal;

import static java.lang.Math.random;

public class Initialisation {

    public Corps[][] NCorpsT0;
    int DimXYZ;
    BigDecimal Gm;
    BigDecimal pas;
    int Tmax;

    public Initialisation(BigDecimal gm, int tmax, BigDecimal pas, int dimXYZ) {
        DimXYZ = dimXYZ;
        Gm = gm;
        this.pas = pas;
        this.Tmax = tmax;
        NCorpsT0 = new Corps[3][Tmax];


        for (int n = 0; n < 3; n++) {
            Corps corps = new Corps();
            BigDecimal[] param = new BigDecimal[6];
            for (int c = 0; c < 6; c++) {
                param[c] = new BigDecimal(random() * DimXYZ);// 0 a 1 ? //random non BigDecimal ?
                //System.out.print(param[c]);
                //preciser les domaines
            }
            corps.param = param;
            NCorpsT0[n][0] = corps;
        }

    }

    public void calculate() {
        new Calcul(this);
    }
}
