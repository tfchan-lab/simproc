import java.lang.Math;

public class Kernel {
    int kernelSize;
    double[][] kernel;

    void createKernel(String s, double... args) {
        if (s.equals("gaussian")) {
            kernelSize = (int)args[0];
            double sd = args[1];
            kernel = new double[kernelSize][kernelSize];
            double sum = 0.0; // For normalization
        
            for (int i = 0; i < kernelSize; i++) {
                for (int j = 0; j < kernelSize; j++) {
                    double x = i - kernelSize / 2;
                    double y = j - kernelSize / 2;
                    double t = 1 / (2 * Math.PI * Math.pow(sd, 2)) * Math.exp(-(Math.pow(x, 2) + Math.pow(y, 2)) / (2 * Math.pow(sd, 2)));
                    kernel[i][j] = t;
                    sum += t;
                }
            }
        
            // Normalize the kernel
            for (int i = 0; i < kernelSize; i++) {
                for (int j = 0; j < kernelSize; j++) {
                    kernel[i][j] /= sum;
                }
            }
        }
        else if (s.equals("sobel_x")) { // Effectively 0°
            kernelSize = 3;
            kernel = new double[kernelSize][kernelSize];
            for (int i = 0; i < kernelSize; i++) {
                kernel[0][0] = -1; kernel[0][1] = 0; kernel[0][2] = 1;
                kernel[1][0] = -2; kernel[1][1] = 0; kernel[1][2] = 2;
                kernel[2][0] = -1; kernel[2][1] = 0; kernel[2][2] = 1;
            }
        }
        else if (s.equals("sobel_y")) { // Effectively 90°
            kernelSize = 3;
            kernel = new double[kernelSize][kernelSize];
            for (int i = 0; i < kernelSize; i++) {
                kernel[0][0] = 1; kernel[0][1] = 2; kernel[0][2] = 1;
                kernel[1][0] = 0; kernel[1][1] = 0; kernel[1][2] = 0;
                kernel[2][0] = -1; kernel[2][1] = -2; kernel[2][2] = -1;
            }
        }
        else { // Failsafe condition
            System.exit(1);
        }
    }
}
