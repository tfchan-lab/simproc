public class Kernel_Gaussian extends Kernel {
    public Kernel_Gaussian(int size) {
        this.size = size;
        type = KernelType.gaussian;
    }

    public void createKernel(double... args) {
        double sd = args[0];
        kernel = new double[size][size];
        double sum = 0.0; // For normalization
    
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                double x = i - size / 2;
                double y = j - size / 2;
                double t = 1 / (2 * Math.PI * Math.pow(sd, 2)) * Math.exp(-(Math.pow(x, 2) + Math.pow(y, 2)) / (2 * Math.pow(sd, 2)));
                kernel[i][j] = t;
                sum += t;
            }
        }
    
        // Normalize the kernel
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                kernel[i][j] /= sum;
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