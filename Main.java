import java.io.IOException;

public class Main {
    public static void usage() {
        System.out.println("Usage:");
        System.out.println("simgproc ? | Displays this message");
        System.out.println("simgproc <filename> greyscale | Greyscales user-chosen image");
        System.out.println("simgproc <filename> blur <size> <SD> | Blurs user-chosen image");
        System.out.println("simgproc <filename> downsample <multiplier> | Down-samples user-chosen image");
        System.out.println("simgproc <filename> sobel <threshold> | Detects the edge of user-chosen image with Sobel Operator");
        System.out.println("simgproc <filename> canny <threshold_low> <threshold_high>| Detects the edge of user-chosen image with Canny Operator");
        System.exit(0);
    }
    public static void main(String[] args) throws IOException {
        if (args.length >= 1) {
            if ((args[0].equals("?"))) {
                usage();
            }
            else if (args.length >= 2) {
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
                    Kernel x = new Kernel();
                    x.createKernel("sobel_x");
                    Kernel y = new Kernel();
                    y.createKernel("sobel_y");
                    MaxPool m = new MaxPool(img, 1);
                    Sobel sobel = new Sobel(m, x, y, Double.parseDouble(args[2]));
                    sobel.applyEdge();
                }
                else if (args[1].equals("canny") && args.length == 4) {
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
        /*
        Greyscale grey = new Greyscale(img);
        grey.applyGrey();
        */
        /*
        Kernel kernel = new Kernel();
        kernel.createKernel("gaussian");
        Gaussian blur = new Gaussian(img, kernel);
        blur.applyBlur();
        */
        /*
        Kernel x = new Kernel();
        x.createKernel("sobel_x");
        Kernel y = new Kernel();
        y.createKernel("sobel_y");
        MaxPool m = new MaxPool(img, 2);
        Sobel edge = new Sobel(m, x, y, 60);
        edge.applyEdge();
        */
        /*
        Canny edge = new Canny(img);
        edge.NMS();
        edge.HT(25, 255);
        edge.BR();
        edge.applyCanny();
        */
    }
}