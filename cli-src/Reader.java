import java.util.*;
import java.io.*;

public class Reader {
    private String fn;

    public Reader(String fn) {
        this.fn = fn;
    }

    public boolean extensionCheck() {
        if (fn.substring(fn.length() - 4, fn.length()).equals(".ppm")) {
            return true;
        }
        System.out.println("File is not in `.ppm` format");
        return false;
    }

    public boolean filepathCheck() {
        try (Scanner file = new Scanner(new File(fn))) {
            if (file.hasNext()) {
                compressionCheck(file.next());
                file.close();
                return true;
            }
            file.close();
        }
        catch (FileNotFoundException e) {
            System.out.println("File is not found, please ensure correct filepath");
        }
        return false;
    }

    public boolean compressionCheck(String pString) {
        if (pString.equals("P3")) {
            return true;
        }
        System.out.println("File is not in P3 mode, please ensure the first line is \"P3\"");
        return false;
    }

    public boolean check() {
        if (extensionCheck() && filepathCheck()) {
            return true;
        }
        return false;
    }

    public Image createImage() {
        int[] info = new int[4];
        try (Scanner file = new Scanner(new File(fn))) {
            if (file.hasNext()) {
                file.next(); // Skip first line
            }
            else {
                System.out.println("File may be corrupted");
                System.exit(0);
            }

            for (int i = 0; i < 3; i++) { // Load Width, Height, Max intensity, Color depth
                if (file.hasNextInt()) {
                    info[i] = file.nextInt();
                }
                else {
                    System.out.println("File may be corrupted");
                    System.exit(0);
                }
            }

            int temp = info[2];
            info[3] = 0;
            while (temp > 0) { // Calculate Color depth
                temp = temp / 2;
                info[3]++;
            }

            int[][][] pixels = new int[info[1]][info[0]][3]; // Indexes: Height * Width * RGB
            for (int i = 0; i < info[1]; i++) { // Load the pixels
                for (int j = 0; j < info[0]; j++) {
                    for (int k = 0; k < 3; k++) {
                        if (file.hasNextInt()) {
                            pixels[i][j][k] = file.nextInt();
                        }
                        else {
                            System.out.println("File may be corrupted");
                            System.exit(0);
                        }
                    }
                }
                Helper.printBar(i, info[1], "Loading from file");
                System.out.printf("\rLoading from file: |%-20s|%d%%",
                    new String(new char[(int)Math.floor((double)(i+1) / info[1] * 20)]).replace('\0', '■'),
                    (int)Math.floor((double)(i+1) / info[1] * 100)
                );
            }
            System.out.println();

            file.close();

            return new Image(info, pixels);
        }
        catch (FileNotFoundException e) {
            System.out.println("File is not found, please ensure correct filepath");
        }
        return null;
    }

    public String getFilename() {
        return fn.substring(0, fn.length() - 4);
    }
}
