public class Image_EC extends Image {
    public Image_EC() {
        type = ImageType.edgedetect;
    }

    public Image_EC(int[] info, int[][][] pixels) {
        super(info, pixels);
        type = ImageType.edgedetect;
    }

    private void NMS() { // Non-Maximum Suppression
        for (int i = 1; i < h - 1; i++) {
            for (int j = 1; j < w - 1; j++) {
                // 0°, 45°, 90°, 135° Suppressions
                if ((pixels[i][j][0] < pixels[i][j-1][0]) || (pixels[i][j][0] < pixels[i][j+1][0])
                 || (pixels[i][j][0] < pixels[i-1][j][0]) || (pixels[i][j][0] < pixels[i+1][j][0])
                 || (pixels[i][j][0] < pixels[i-1][j+1][0]) || (pixels[i][j][0] < pixels[i+1][j-1][0])
                 || (pixels[i][j][0] < pixels[i-1][j-1][0]) || (pixels[i][j][0] < pixels[i+1][j+1][0])) {
                    for (int m = 0; m < 3; m++) {
                        pixels[i][j][m] = 0;
                    }
                }
            }
            Helper.printBar(i, 2 * h - 2, "Applying Canny edge detection");
        }
    }

    private void HT(int low, int high) { // Hysteresis Thresholding
        for (int i = 1; i < h - 1; i++) {
            for (int j = 1; j < w - 1; j++) {
                if ((pixels[i][j][0] <= low)) {
                    for (int m = 0; m < 3; m++) {
                        pixels[i][j][m] = 0;
                    }
                }
                if ((pixels[i][j][0] > low) && (pixels[i][j][0] < high)) { // Non-"Strong" pixels
                    if ((pixels[i-1][j-1][0] >= high) || (pixels[i-1][j][0] >= high)
                     || (pixels[i-1][j+1][0] >= high) || (pixels[i][j-1][0] >= high)
                     || (pixels[i][j+1][0] >= high) || (pixels[i+1][j-1][0] >= high)
                     || (pixels[i+1][j][0] >= high) || (pixels[i+1][j+1][0] >= high)) {
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
            Helper.printBar(h - 1 + i, 2 * h - 2, "Applying Canny edge detection");
        }
        System.out.println();
    }

    private void BR() { // Border Removal
        for (int i = 0; i < h; i++) {
            for (int m = 0; m < 3; m++) {
                pixels[i][0][m] = 0;
                pixels[i][w-1][m] = 0;
            }
        }
        for (int i = 0; i < w; i++) {
            for (int m = 0; m < 3; m++) {
                pixels[0][i][m] = 0;
                pixels[h-1][i][m] = 0;
            }
        }
    }

    public void applyCanny(int low, int high) {
        Helper.printSpacer();
        NMS();
        HT(low, high);
        BR();
    }
}
