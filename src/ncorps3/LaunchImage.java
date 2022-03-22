package ncorps3;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.*;
import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;

import static java.awt.image.Raster.createInterleavedRaster;

public class LaunchImage implements Parametres {

    private  static Calcul3Corps calculs;
    private static boolean[][] coord;

    public static void main(String[] argv) throws IOException {

        calculs = new Calcul3Corps();
        coord = new boolean[DimXYZ][DimXYZ];
        for (int k = 0; k < Tmax; k++) {
             convert3DTo2DToInt(k);
        }

        image(preimg());
    }

    private static void convert3DTo2DToInt( int k) throws IOException {
        Calcul3Corps.Corps[][] I = calculs.ncorps;

        for (int n = 0; n < NbCorps; n++) {
            BigDecimal[] param = I[n][k].param;
//            int X = (int) (param[0] / param[2]);
//            int Y = (int) (param[1] / param[2]);
            int X = param[0].intValue();
            int Y = param[1].intValue();
            System.out.print(X);
            System.out.print(" ; ");
            System.out.print(Y);
            System.out.println(" ; ");
           if (X<100 && Y<100 && X>0 && Y>0) coord[X][Y] = true;

        }

    }

    private static byte[] preimg() {
        byte[] preimg = new byte[3 * DimXYZ * DimXYZ];
        int k = 0;
        for (int i = 0; i < DimXYZ; i++) {
            for (int j = 0; j < DimXYZ; j++) {
                if (!coord[i][j]) {
                    preimg[k] = (byte) 0xffff;
                    preimg[k++] = (byte) 0xffff;
                    preimg[k++] = (byte) 0xffff;
                } else {
                    preimg[k] = 0x0;
                    preimg[k++] = 0x0;
                    preimg[k++] = 0x0;
                }
                k++;
            }

        }
        return preimg;
    }

    private static void image(byte[] preimg) throws IOException {
        int width = DimXYZ;
        int height = DimXYZ;

        DataBuffer buffer = new DataBufferByte(preimg, preimg.length);
        //3 bytes per pixel: red, green, blue
        WritableRaster raster = createInterleavedRaster(buffer, width, height,
                3 * width, 3, new int[]{0, 1, 2}, (Point) null);
        ColorModel cm = new ComponentColorModel(ColorModel.getRGBdefault().getColorSpace(), false,
                true, Transparency.OPAQUE, DataBuffer.TYPE_BYTE);
        BufferedImage image = new BufferedImage(cm, raster, true, null);
        ImageIO.write(image, "png", new File("image.png"));
        // ImageIO.write(image, "png", new File("image.png"));
    }


}
