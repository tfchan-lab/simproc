import java.lang.Math;

public class Image_ES extends Image {
    public Image_ES() {
        type = ImageType.edgedetect;
    }

    public Image_ES(int[] info, int[][][] pixels) {
        super(info, pixels);
        type = ImageType.edgedetect;
    }

    private double convolve(int y, int x, int n, Kernel k) {
        double temp = 0;
        double[][] kernel = k.getKernel();
        for (int i = 0; i < k.getSize(); i++) {
            for (int j = 0; j < k.getSize(); j++) {
                if (!((y + i - (k.getSize() - 1) / 2) < 0 || (x + j - (k.getSize() - 1) / 2) < 0 || (y + i - (k.getSize() - 1) / 2) >= h || (x + j - (k.getSize() - 1) / 2) >= w)) {
                    temp += kernel[i][j] * pixels[y + i - (k.getSize() - 1) / 2][x + j - (k.getSize() - 1) / 2][n];
                }
            }
        }
        return temp;
    }

    public void applySobel(Kernel x, Kernel y, int threshold) {
        Helper.printSpacer();
        int[][][] newPixels = new int[h][w][3];
        for (int i = 0; i < h; i++) {
            for (int j = 0; j < w; j++) {
                double pix[] = new double[3];
                for (int n = 0; n < 3; n++) {
                    double gx = convolve(i, j, n, x);
                    double gy = convolve(i, j, n, y);
                    pix[n] = Math.sqrt(gx * gx + gy * gy);
                    pix[n] = Math.min(Math.max(pix[n], 0), maxInt);
                }
                // Intensity Threshold for naive denoising
                if (((pix[0] + pix[1] + pix[2]) / 3) < threshold) {
                    for (int n = 0; n < 3; n++) {
                        newPixels[i][j][n] = 0;
                    }
                }
                else {
                    for (int n = 0; n < 3; n++) {
                        newPixels[i][j][n] = (int)pix[n];
                    }
                }
            }
            Helper.printBar(i, h, "Applying Sobel edge detection");
        }
        pixels = newPixels;
        System.out.println();
    }
}
