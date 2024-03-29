public class Image_GS extends Image {

    public Image_GS() {
        type = ImageType.greyscale;
    }

    public Image_GS(int[] info, int[][][] pixels) {
        super(info, pixels);
        type = ImageType.greyscale;
    }
    
    public void applyGrey() {
        Helper.printSpacer();
        for (int i = 0; i < h; i++) {
            for (int j = 0; j < w; j++) {
                double avg = 0.299 * pixels[i][j][0] + 0.587 * pixels[i][j][1] + 0.114 * pixels[i][j][2];
                for (int k = 0; k < 3; k++) {
                    pixels[i][j][k] = (int)avg;
                }
            }
            Helper.printBar(i, h, "Applying greyscale");
        }
        System.out.println();
    }
}
