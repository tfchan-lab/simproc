public class Kernel_Laplacian extends Kernel {
    public Kernel_Laplacian() {
        size = 3;
        type = KernelType.sharpen;
    }

    public void createKernel(double... args) {
        double intensity = args[0] * -1 ;
        kernel = new double[size][size];
        double anchor = intensity * -4 + 1; // Laplacian filter

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (i == 1 && j == 1) {
                    kernel[i][j] = anchor;
                }
                else if (i >= 1 && i <= 2 && j >= 1 && j <= 2) {
                    kernel[i][j] = intensity;
                }
                else {
                    kernel[i][j] = 0;
                }
            }
        }
    }

    public int getSize() {
        return size;
    }

    public double[][] getKernel() {
        return kernel;
    }
}