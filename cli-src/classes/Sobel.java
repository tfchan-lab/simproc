import java.io.IOException;
import java.io.PrintWriter;
import java.lang.Math;

public class Sobel {
    Image img;
    MaxPool m;
    Kernel x;
    Kernel y;
    double t;
    int[][][] imgpix;
    int[][][] pixels;
    int sizeX, sizeY, colorMax;

    double convolution(int y, int x, int n, Kernel k) {
        double temp = 0;
        for (int i = 0; i < k.kernelSize; i++) {
            for (int j = 0; j < k.kernelSize; j++) {
                if ((y + i - (k.kernelSize - 1) / 2 < 0 || (x + j - (k.kernelSize - 1) / 2) < 0 || (y + i - (k.kernelSize - 1) / 2) >= sizeY || (x + j - (k.kernelSize - 1) / 2) >= sizeX)) {
                    temp += k.kernel[i][j] * 0;
                }
                else {
                    temp += k.kernel[i][j] * imgpix[y + i - (k.kernelSize - 1) / 2][x + j - (k.kernelSize - 1) / 2][n];
                }
            }
        }

        return temp;
    }

    Sobel(Image image, Kernel x, Kernel y, double t) {
        img = image;
        this.x = x;
        this.y = y;
        this.t = t;
        this.sizeX = img.sizeX;
        this.sizeY = img.sizeY;
        this.colorMax = img.colorMax;
        imgpix = image.pixels;
        pixels = new int[sizeY][sizeX][3];

        for (int i = 0; i < sizeY; i++) {
            for (int j = 0; j < sizeX; j++) {
                double pix[] = new double[3];
                for (int n = 0; n < 3; n++) {
                    double gx = convolution(i, j, n, x);
                    double gy = convolution(i, j, n, y);
                    pix[n] = Math.sqrt(gx * gx + gy * gy);
                    pix[n] = Math.min(Math.max(pix[n], 0), colorMax);
                }
                // Intensity Threshold for naive denoising
                if (((pix[0] + pix[1] + pix[2]) / 3) < t) {
                    for (int n = 0; n < 3; n++) {
                        pixels[i][j][n] = 0;
                    }
                }
                else {
                    for (int n = 0; n < 3; n++) {
                        pixels[i][j][n] = (int)pix[n];
                    }
                }
            }
        }
    }

    Sobel(MaxPool m, Kernel x, Kernel y, double t) {
        this.m = m;
        this.x = x;
        this.y = y;
        this.t = t;
        this.sizeX = m.sizeX;
        this.sizeY = m.sizeY;
        this.colorMax = m.colorMax;
        imgpix = m.pixels;
        pixels = new int[sizeY][sizeX][3];

        for (int i = 0; i < sizeY; i++) {
            for (int j = 0; j < sizeX; j++) {
                double pix[] = new double[3];
                for (int n = 0; n < 3; n++) {
                    double gx = convolution(i, j, n, x);
                    double gy = convolution(i, j, n, y);
                    pix[n] = Math.sqrt(gx * gx + gy * gy);
                    pix[n] = Math.min(Math.max(pix[n], 0), colorMax);
                }
                // Intensity Threshold for naive denoising
                if (((pix[0] + pix[1] + pix[2]) / 3) < t) {
                    for (int n = 0; n < 3; n++) {
                        pixels[i][j][n] = 0;
                    }
                }
                else {
                    for (int n = 0; n < 3; n++) {
                        pixels[i][j][n] = (int)pix[n];
                    }
                }
            }
        }
    }

    void applyEdge() throws IOException {
        PrintWriter newImg = new PrintWriter("edge_detected.ppm");
        newImg.println("P3");
        newImg.println(sizeX + " " + sizeY);
        newImg.println(colorMax);
        for (int i = 0; i < sizeY; i++) {
            for (int j = 0; j < sizeX; j++) {
                for (int n = 0; n< 3; n++) {
                    newImg.print((int)pixels[i][j][n] + " ");
                }
                newImg.println();
            }
        }
        // Close PrintWriter to deallocate memory
        newImg.close();
    }
}
