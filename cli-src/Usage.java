public class Usage {
    private static String[] helpMsg = {
        "Usage:",
        "simgproc-cli ? | Display this message",
        "simgproc-cli greyscale <filename> | Greyscales user-chosen image",
        "simgproc-cli blur <filename> <size> <SD> | Blurs user-chosen image",
        "simgproc-cli sharpen <filename> <intensity> | Sharpens user-chosen image",
        "simgproc-cli downsample <filename> \"max/average\" <multiplier> | Down-samples user-chosen image",
        "simgproc-cli sobel <filename> <threshold> | Detects the edge of user-chosen image with Sobel Operator",
        "simgproc-cli canny <filename> <threshold_low> <threshold_high> | Detects the edge of user-chosen image with Canny Operator"
    };

    public static void printMsg() {
        for (int i = 0; i < helpMsg.length; i++) {
            System.out.println(helpMsg[i]);
        }
    }
}
