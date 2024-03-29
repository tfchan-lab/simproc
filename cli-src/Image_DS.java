import java.lang.Math;

public class Image_DS extends Image {
    private int ratio;

    public Image_DS() {
        type = ImageType.downsample;
    }

    public Image_DS(int[] info, int[][][] pixels) {
        super(info, pixels);
        type = ImageType.downsample;
    }

    public void updateDimension(int ratio) {
        this.ratio = ratio;
        w = w / ratio;
        h = h / ratio;
    }

    public void applyMaxPool() {
        Helper.printSpacer();
        int[][][] newPixels = new int[h][w][3];
        for (int i = 0; i < h; i++) {
            for (int j = 0; j < w; j++) {
                for (int m = 0; m < 3; m++) {
                    int max = 0;
                    for (int k = 0; k < ratio; k++) {
                        for (int l = 0; l < ratio; l++) {
                            if ((i * ratio + k) < (h * ratio) && (j * ratio + l) < (w * ratio)) {
                                max = Math.max(max, pixels[i*ratio+k][j*ratio+l][m]);
                            }
                        }
                    }
                    newPixels[i][j][m] = max;
                }
            }
            Helper.printBar(i, h, "Applying max pooling");
        }
        pixels = newPixels;
        System.out.println();
    }

    public void applyAvgPool() {
        Helper.printSpacer();
        for (int i = 0; i < h; i++) {
            for (int j = 0; j < w; j++) {
                for (int m = 0; m < 3; m++) {
                    int sum = 0;
                    for (int k = 0; k < ratio; k++) {
                        for (int l = 0; l < ratio; l++) {
                            if ((i * ratio + k) < (h * ratio) && (j * ratio + l) < (w * ratio)) {
                                sum += pixels[i*ratio+k][j*ratio+l][m];
                            }
                        }
                    }
                    pixels[i][j][m] = sum / ratio / ratio;
                }
            }
            Helper.printBar(i, h, "Applying average pooling");
        }
        System.out.println();
    }
}
