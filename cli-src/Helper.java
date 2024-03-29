public class Helper {
    // Progress bar
    public static void printBar(int current, int total, String message) {
        System.out.printf("\r%s: |%-20s|%d%%",
            message,
            new String(new char[(int)Math.floor((double)(current+1) / total * 20)]).replace('\0', '■'),
            (int)Math.floor((double)(current+1) / total * 100)
        );
    }
    // Spacer
    public static void printSpacer() {
        System.out.println("----------------------------------------");
    }
}
