import java.io.IOException;

public class Main {
    public static void usage() {
        System.out.println("Usage:");
        System.out.println("simgproc-gui ? | Displays this message");
        System.out.println("simgproc-gui <filename> | Process user-chosen image with GUI");
        System.exit(0);
    }
    public static void main(String[] args) throws IOException {
        if (args.length == 1) {
            if ((args[0].equals("?"))) {
                usage();
            }
            else {
                // File extension check
                if (!args[0].substring(args[0].length() - 4, args[0].length()).equals(".ppm")) {
                    System.out.println("The file is not PPM.");
                    System.exit(1);
                }
                Renderer R = new Renderer(args[0]);
            }
        }
        else {
            usage();
        }
        
    }
}