import java.io.IOException;
import java.io.PrintWriter;

public class Canny {
    int sizeX, sizeY, colorMax;
    int pixels[][][];

    Canny(Image i) {
        this.sizeX = i.sizeX;
        this.sizeY = i.sizeY;
        this.colorMax = i.colorMax;
        this.pixels = i.pixels;
    }

    void NMS() { // Non-maximum Suppression
        for (int i = 1; i < sizeY - 1; i++) {
            for (int j = 1; j < sizeX - 1; j++) {
                // 0° Suppression
                if ((pixels[i][j][0] < pixels[i][j-1][0]) || (pixels[i][j][0] < pixels[i][j+1][0] )) {
                    for (int m = 0; m < 3; m++) {
                        pixels[i][j][m] = 0;
                    }
                }
                // 90° Suppression
                else if ((pixels[i][j][0] < pixels[i-1][j][0]) || (pixels[i][j][0] < pixels[i+1][j][0])) {
                    for (int m = 0; m < 3; m++) {
                        pixels[i][j][m] = 0;
                    }
                }
                // 45° Suppression
                else if ((pixels[i][j][0] < pixels[i-1][j+1][0]) || (pixels[i][j][0] < pixels[i+1][j-1][0])) {
                    for (int m = 0; m < 3; m++) {
                        pixels[i][j][m] = 0;
                    }
                }
                // 135° Suppression
                else if ((pixels[i][j][0] < pixels[i-1][j-1][0]) || (pixels[i][j][0] < pixels[i+1][j+1][0])) {
                    for (int m = 0; m < 3; m++) {
                        pixels[i][j][m] = 0;
                    }
                }
            }
        }
    }

    void HT(int l, int h) { // Hysteresis Thresholding
        for (int i = 1; i < sizeY -1; i++) {
            for (int j = 1; j < sizeX - 1; j++) {
                if ((pixels[i][j][0] <= l)) {
                    for (int m = 0; m < 3; m++) {
                        pixels[i][j][m] = 0;
                    }
                }
                if ((pixels[i][j][0] > l) && (pixels[i][j][0] < h)) { // Non-"Strong" pixels
                    if ((pixels[i-1][j-1][0] >= h) || (pixels[i-1][j][0] >= h) || (pixels[i-1][j+1][0] >= h) || (pixels[i][j-1][0] >= h) || (pixels[i][j+1][0] >= h) || (pixels[i+1][j-1][0] >= h) || (pixels[i+1][j][0] >= h) || (pixels[i+1][j+1][0] >= h)) {
                        for (int m = 0; m < 3; m++) {
                            pixels[i][j][m] = 255; // "Weak" pixels
                        }
                    }
                    else {
                        for (int m = 0; m < 3; m++) {
                            pixels[i][j][m] = 0; // "Invalid" pixels
                        }
                    }
                }
            }
        }
    }
    
    void BR() { // Border Removal
        for (int i = 0; i < sizeY; i++) {
            for (int m = 0; m < 3; m++) {
                pixels[i][0][m] = 0;
                pixels[i][sizeX - 1][m] = 0;
            }
        }
        for (int i = 0; i < sizeX; i++) {
            for (int m = 0; m < 3; m++) {
                pixels[0][i][m] = 0;
                pixels[sizeY - 1][i][m] = 0;
            }
        }
    }

    void applyCanny() throws IOException {
        PrintWriter newImg = new PrintWriter("clean_edge_detected.ppm");
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