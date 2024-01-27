import java.io.IOException;
import java.io.PrintWriter;

public class Gaussian {
    Image img;
    Kernel knl;
    
    Gaussian(Image i, Kernel k) {
        img = i;
        knl = k;
    }

    void applyBlur() throws IOException {
        PrintWriter newImg = new PrintWriter("blurred.ppm");
        newImg.println("P3");
        newImg.println(img.sizeX + " " + img.sizeY);
        newImg.println(img.colorMax);
        for (int i = 0; i < img.sizeY; i++) {
            for (int j = 0; j < img.sizeX; j++) {
                for (int m = 0; m < 3; m++) {
                    double temp = 0;
                    for (int k = 0; k < knl.kernelSize; k++) {
                        for (int l = 0; l < knl.kernelSize; l++) {
                            if ((i + k - (knl.kernelSize-1) / 2 < 0 || (j + l - (knl.kernelSize - 1) / 2) < 0 || (i + k - (knl.kernelSize - 1) / 2) >= img.sizeY || (j + l - (knl.kernelSize - 1) / 2) >= img.sizeX)) {
                                temp += knl.kernel[k][l] * 0;
                            } else {
                                temp += knl.kernel[k][l] * img.pixels[i + k - (knl.kernelSize - 1) / 2][j + l - (knl.kernelSize-1) / 2][m];
                            }
                        }
                    }
                    temp = Math.min(Math.max((int)temp, 0), img.colorMax);
                    newImg.print((int)temp + " ");
                }
                newImg.println();
            }
        }
        // Close PrintWriter to deallocate memory
        newImg.close();
    }
}
