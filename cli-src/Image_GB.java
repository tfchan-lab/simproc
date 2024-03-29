public class Image_GB extends Image{
    public Image_GB() {
        type = ImageType.blur;
    }

    public Image_GB(int[] info, int[][][] pixels) {
        super(info, pixels);
        type = ImageType.blur;
    }

    public void applyBlur(int size, double[][] kernel) {
        Helper.printSpacer();
        for (int i = 0; i < h; i++) {
            for (int j = 0; j < w; j++) {
                for (int m = 0; m < 3; m++) {
                    double temp = 0;
                    for (int k = 0; k < size; k++) {
                        for (int l = 0; l < size; l++) {
                            if (!(i + k - (size - 1) / 2 < 0 || (j + l - (size - 1) / 2) < 0 || (i + k - (size - 1) / 2) >= h || (j + l - (size - 1) / 2) >= w)) {
                                temp += kernel[k][l] * pixels[i + k - (size - 1) / 2][j + l - (size - 1) / 2][m];
                            }
                        }
                    }
                    pixels[i][j][m] = (int)Math.min(Math.max((int)temp, 0), maxInt);
                }
            }
            Helper.printBar(i, h, "Applying Gaussian blur");
        }
        System.out.println();
    }
}
