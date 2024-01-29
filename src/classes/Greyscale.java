import java.io.IOException;
import java.io.PrintWriter;

public class Greyscale {
    Image img;
    int[][][] pixels;

    Greyscale(Image image) {
        img = image;
        pixels = new int[img.sizeY][img.sizeX][3];
        for (int i = 0; i < img.sizeY; i++) {
            for (int j = 0; j < img.sizeX; j++) {
                double avg = 0;
                // Apply the NTSC greyscale formula
                avg += 0.299 * img.pixels[i][j][0] + 0.587 * img.pixels[i][j][1] + 0.114 * img.pixels[i][j][2];
                for (int m = 0; m < 3; m++) {
                    this.pixels[i][j][m] = (int)avg;
                }
            }
        }
    }

    void applyGrey() throws IOException {
        PrintWriter newImg = new PrintWriter("greyscale.ppm");
        newImg.println("P3");
        newImg.println(img.sizeX + " " + img.sizeY);
        newImg.println(img.colorMax);
        for (int i = 0; i < img.sizeY; i++) {
            for (int j = 0; j < img.sizeX; j++) {
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
