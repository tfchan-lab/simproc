public abstract class Kernel {
    protected int size;
    protected double[][] kernel;

    protected enum KernelType {
        gaussian,
        sobel,
        sharpen
    }
    protected KernelType type;

    public abstract void createKernel(double... args);
    
    public int getSize() {
        return size;
    }

    public double[][] getKernel() {
        return kernel;
    }
}
