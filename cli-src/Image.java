import java.io.*;

public class Image {
    protected int w, h, maxInt, cDepth; // Width, Height, Max intensity, Color depth
    protected int[][][] pixels;

    protected enum ImageType {
        image,
        greyscale,
        blur,
        downsample,
        edgedetect,
        sharpen
    }
    protected ImageType type;

    public Image() {
        type = ImageType.image;
    }

    public Image(int[] info, int[][][] pixels) {
        w = info[0];
        h = info[1];
        maxInt = info[2];
        cDepth = info[3];
        this.pixels = pixels;
        type = ImageType.image;
    }

    public void printInfo() {
        String[] types = {
            "original", "greyscaled", "blurred",
            "down-sampled", "edge-detected",
            "sharpened"
        };
        Helper.printSpacer();
        System.out.printf("Size of %s image: %d*%d px\nMaximum brightness value: %d\nColor depth: %d-bit\n",
        types[type.ordinal()], w, h, maxInt, cDepth);
    }

    public static void transfer(Image src, Image desc) {
        desc.w = src.w;
        desc.h = src.h;
        desc.maxInt = src.maxInt;
        desc.cDepth = src.cDepth;
        desc.pixels = src.pixels;
    }

    public void write(String fn) {
        String[] types = {
            "original", "greyscaled", "blurred",
            "down-sampled", "edge-detected",
            "sharpened"
        };

        try {
            PrintWriter out = new PrintWriter(fn + "-" + types[type.ordinal()] + ".ppm");
            out.println("P3");
            out.println(w + " " + h);
            out.println(maxInt);
            for (int i = 0; i < h; i++) {
                for (int j = 0; j < w; j++) {
                    out.print(String.format("%d %d %d\n", pixels[i][j][0], pixels[i][j][1], pixels[i][j][2]));
                }
                Helper.printBar(i, h, "Writing to new file");
            }
            System.out.println();
            System.out.println("Done, file saved as `" + fn + "-" + types[type.ordinal()] + ".ppm`");
            out.close();
        }
        catch (IOException e) {
            System.out.println("There is a problem writing to new file, please check the permission of target directory");
            System.exit(0);
        }
    }
}
