import java.io.IOException;
import java.io.PrintWriter;
import java.lang.Math;

public class Sobel {
    MaxPool m;
    Kernel x;
    Kernel y;
    double t;

    Sobel(MaxPool m, Kernel x, Kernel y, double t) {
        this.m = m;
        this.x = x;
        this.y = y;
        this.t = t;
    }

    double convolution(int y, int x, int n, Kernel k) {
        double temp = 0;

        for (int i = 0; i < k.kernelSize; i++) {
            for (int j = 0; j < k.kernelSize; j++) {
                if ((y + i - (k.kernelSize - 1) / 2 < 0 || (x + j - (k.kernelSize - 1) / 2) < 0 || (y + i - (k.kernelSize - 1) / 2) >= m.sizeY || (x + j - (k.kernelSize - 1) / 2) >= m.sizeX)) {
                    temp += k.kernel[i][j] * 0;
                }
                else {
                    temp += k.kernel[i][j] * m.pixels[y + i - (k.kernelSize - 1) / 2][x + j - (k.kernelSize - 1) / 2][n];
                }
            }
        }

        return temp;
    }

    void applyEdge() throws IOException {
        PrintWriter newImg = new PrintWriter("edge_detected.ppm");
        newImg.println("P3");
        newImg.println(m.sizeX + " " + m.sizeY);
        newImg.println(m.colorMax);
        for (int i = 0; i < m.sizeY; i++) {
            for (int j = 0; j < m.sizeX; j++) {
                double pix[] = new double[3];
                for (int n = 0; n < 3; n++) {
                    double gx = convolution(i, j, n, x);
                    double gy = convolution(i, j, n, y);
                    pix[n] = Math.sqrt(gx * gx + gy * gy);
                    pix[n] = Math.min(Math.max(pix[n], 0), m.colorMax);
                }
                // Intensity Threshold for naive denoising
                if (((pix[0] + pix[1] + pix[2]) / 3) < t) {
                    newImg.print("0 0 0");
                }
                else {
                    newImg.print((int)pix[0] + " " + (int)pix[1] + " " + (int)pix[2]);
                }
                newImg.println();
            }
        }
        // Close PrintWriter to deallocate memory
        newImg.close();
    }
}
