import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        if (args.length > 0) {
            switch (args[0]) {
                case "?":
                    Usage.printMsg();
                    break;
                case "greyscale":
                    new Greyscale().exec(args);
                    break;
                case "blur":
                    new Gaussian().exec(args);
                    break;
                case "sharpen":
                    new Sharpen().exec(args);
                    break;
                case "downsample":
                    new Downsample().exec(args);
                    break;
                case "sobel":
                    new Sobel().exec(args);
                    break;
                case "canny":
                    new Canny().exec(args);
                    break;
                default:
                    Usage.printMsg();
                    break;
            }
        }
        else {
            Usage.printMsg();
        }
    }
}