import java.util.*;
import java.io.*;

public class Image {
    int sizeX, sizeY, colorMax;
    int[][][] pixels;
    Scanner sc = new Scanner(System.in);
    
    Image() {}
    Image(String s) throws FileNotFoundException {
        String filename = s;
        Scanner img = new Scanner(new File(filename));
        // Plain PPM check
        String p3check = img.next();
        if (p3check.equals("P3")) {
            System.out.println("The file is Plain PPM.");
        } else {
            System.out.println("The file is not Plain PPM.");
            System.exit(1);
        }
        // Image information
        sizeX = img.nextInt();
        sizeY = img.nextInt();
        System.out.println("Size of image: " + sizeX + "*" + sizeY + " px");
        colorMax = img.nextInt();
        System.out.println("Maximum brightness value: " + colorMax);
        int temp = colorMax;
        int colorDepth = 0;
        while (temp > 0) {
            temp = temp / 2;
            colorDepth++;
        }
        System.out.println("Color depth: " + colorDepth + "-bit");
        // RGB Array
        pixels = new int[sizeY][sizeX][3];
        for (int i = 0; i < sizeY; i++) {
            for (int j = 0; j < sizeX; j++) {
                pixels[i][j][0] = img.nextInt();
                pixels[i][j][1] = img.nextInt();
                pixels[i][j][2] = img.nextInt();
            }
        }
    }
}
