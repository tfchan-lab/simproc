import java.io.IOException;
import java.io.PrintWriter;
import java.lang.Math;

public class MaxPool {
    int sizeX, sizeY, colorMax;
    int[][][] pixels;

    MaxPool(Image img, int r) {
        this.sizeX = img.sizeX / r;
        this.sizeY = img.sizeY / r;
        this.colorMax = img.colorMax;
        pixels = new int[this.sizeY][this.sizeX][3];
        for (int i = 0; i < this.sizeY; i++) {
            for (int j = 0; j < this.sizeX; j++) {
                for (int m = 0; m < 3; m++) {
                    int maxVal = 0;
                    for (int k = 0; k < r; k++) {
                        for (int l = 0; l < r; l++) {
                            if ((i * r + k) < img.sizeY && (j * r + l) < img.sizeX) {
                                maxVal = Math.max(maxVal, img.pixels[i * r + k][j * r + l][m]);
                            }
                        }
                    }
                    pixels[i][j][m] = maxVal;
                }
            }
        }
    }

    void applyPool() throws IOException {
        PrintWriter newImg = new PrintWriter("down_sampled.ppm");
        newImg.println("P3");
        newImg.println(sizeX + " " + sizeY);
        newImg.println(colorMax);
        for (int i = 0; i < sizeY; i++) {
            for (int j = 0; j < sizeX; j++) {
                for (int m = 0; m < 3; m++) {
                    newImg.print(pixels[i][j][m] + " ");
                }
                newImg.println();
            }
        }
        // Close PrintWriter to deallocate memory
        newImg.close();
    }
}