public class Usage {
    private static String[] helpMsg = {
        "Usage:",
        "simproc-cli ? | Display this message",
        "simproc-cli greyscale <filename> | Greyscales user-chosen image",
        "simproc-cli blur <filename> <size> <SD> | Blurs user-chosen image",
        "simproc-cli sharpen <filename> <intensity> | Sharpens user-chosen image",
        "simproc-cli downsample <filename> \"max/average\" <multiplier> | Down-samples user-chosen image",
        "simproc-cli sobel <filename> <threshold> | Detects the edge of user-chosen image with Sobel Operator",
        "simproc-cli canny <filename> <threshold_low> <threshold_high> | Detects the edge of user-chosen image with Canny Operator"
    };

    public static void printMsg() {
        for (int i = 0; i < helpMsg.length; i++) {
            System.out.println(helpMsg[i]);
        }
    }
}
