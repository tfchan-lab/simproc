public class Kernel_Sobel extends Kernel {
    private int dir;

    public Kernel_Sobel(char dir) {
        this.dir = dir;
        size = 3;
        type = KernelType.sobel;
    }

    public void createKernel(double... args) {
        kernel = new double[size][size];
        switch (dir) {
            case 'x':
                for (int i = 0; i < size; i++) {
                    kernel[0][0] = -1; kernel[0][1] = 0; kernel[0][2] = 1;
                    kernel[1][0] = -2; kernel[1][1] = 0; kernel[1][2] = 2;
                    kernel[2][0] = -1; kernel[2][1] = 0; kernel[2][2] = 1;
                }
                break;
            case 'y':
                for (int i = 0; i < size; i++) {
                    kernel[0][0] = 1; kernel[0][1] = 2; kernel[0][2] = 1;
                    kernel[1][0] = 0; kernel[1][1] = 0; kernel[1][2] = 0;
                    kernel[2][0] = -1; kernel[2][1] = -2; kernel[2][2] = -1;
                }
                break;
        }
    }
}
