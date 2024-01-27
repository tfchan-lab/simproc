import java.io.IOException;
import java.io.PrintWriter;

public class Greyscale {
    int sizeX, sizeY, colorMax;
    double[][][] pixels;

    Greyscale(Image img) {
        this.sizeX = img.sizeX;
        this.sizeY = img.sizeY;
        this.colorMax = img.colorMax;
        pixels = new double[this.sizeY][this.sizeX][3];
        for (int i = 0; i < this.sizeY; i++) {
            for (int j = 0; j < this.sizeX; j++) {
                double avg = 0;
                // Apply the NTSC greyscale formula
                avg += 0.299 * img.pixels[i][j][0] + 0.587 * img.pixels[i][j][1] + 0.114 * img.pixels[i][j][2];
                for (int m = 0; m < 3; m++) {
                    this.pixels[i][j][m] = avg;
                }
            }
        }
    }

    void applyGrey() throws IOException {
        PrintWriter newImg = new PrintWriter("greyscale.ppm");
        newImg.println("P3");
        newImg.println(sizeX + " " + sizeY);
        newImg.println(colorMax);
        for (int i = 0; i < sizeY; i++) {
            for (int j = 0; j < sizeX; j++) {
                for (int m = 0; m < 3; m++) {
                    newImg.print((int)pixels[i][j][m] + " ");
                }
                newImg.println();
            }
        }
        // Close PrintWriter to deallocate memory
        newImg.close();
    }
}
