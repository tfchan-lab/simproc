import java.io.IOException;

public class Main {
    public static void usage() {
        System.out.println("Usage:");
        System.out.println("simgproc-cli ? | Displays this message");
        System.out.println("simgproc-cli <filename> greyscale | Greyscales user-chosen image");
        System.out.println("simgproc-cli <filename> blur <size> <SD> | Blurs user-chosen image");
        System.out.println("simgproc-cli <filename> downsample <multiplier> | Down-samples user-chosen image");
        System.out.println("simgproc-cli <filename> sobel <threshold> | Detects the edge of user-chosen image with Sobel Operator");
        System.out.println("simgproc-cli <filename> canny <threshold_low> <threshold_high> | Detects the edge of user-chosen image with Canny Operator");
        System.exit(0);
    }
    public static void main(String[] args) throws IOException {
        if (args.length >= 1) {
            if ((args[0].equals("?"))) {
                usage();
            }
            else if (args.length >= 2) {
                // File extension check
                if (!args[0].substring(args[0].length() - 4, args[0].length()).equals(".ppm")) {
                    System.out.println("The file is not PPM.");
                    System.exit(1);
                }
                Image img = new Image(args[0]);

                if (args[1].equals("greyscale")) {
                    Greyscale grey = new Greyscale(img);
                    grey.applyGrey();
                }
                else if (args[1].equals("blur") && args.length == 4) {
                    Kernel kernel = new Kernel();
                    kernel.createKernel("gaussian", Double.parseDouble(args[2]), Double.parseDouble(args[3]));
                    Gaussian blur = new Gaussian(img, kernel);
                    blur.applyBlur();
                }
                else if (args[1].equals("downsample") && args.length == 3 ) {
                    MaxPool m = new MaxPool(img, Integer.parseInt(args[2]));
                    m.applyPool();
                }
                else if (args[1].equals("sobel") && args.length == 3) {
                    Greyscale grey = new Greyscale(img);
                    //grey.applyGrey();
                    img.pixels = grey.pixels;
                    Kernel kernel = new Kernel();
                    kernel.createKernel("gaussian", 7, 10);
                    Gaussian blur = new Gaussian(img, kernel);
                    //blur.applyBlur();
                    img.pixels = blur.pixels;
                    Kernel x = new Kernel();
                    x.createKernel("sobel_x");
                    Kernel y = new Kernel();
                    y.createKernel("sobel_y");
                    MaxPool m = new MaxPool(img, 2);
                    Sobel sobel = new Sobel(m, x, y, Double.parseDouble(args[2]));
                    sobel.applyEdge();
                }
                else if (args[1].equals("canny") && args.length == 4) {
                    Greyscale grey = new Greyscale(img);
                    //grey.applyGrey();
                    img.pixels = grey.pixels;
                    Kernel kernel = new Kernel();
                    kernel.createKernel("gaussian", 7, 10);
                    Gaussian blur = new Gaussian(img, kernel);
                    //blur.applyBlur();
                    img.pixels = blur.pixels;
                    Kernel x = new Kernel();
                    x.createKernel("sobel_x");
                    Kernel y = new Kernel();
                    y.createKernel("sobel_y");
                    MaxPool m = new MaxPool(img, 2);
                    Sobel sobel = new Sobel(m, x, y, 60);
                    //sobel.applyEdge();
                    img.pixels = sobel.pixels;
                    img.sizeX = m.sizeX;
                    img.sizeY = m.sizeY;
                    Canny canny = new Canny(img);
                    canny.NMS();
                    canny.HT(Integer.parseInt(args[2]), Integer.parseInt(args[3]));
                    canny.BR();
                    canny.applyCanny();
                }
            }
        }
        else {
            usage();
        }
    }
}