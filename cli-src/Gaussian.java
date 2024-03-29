public class Gaussian implements Command {
    public void exec(String[] args) {
        Reader r = new Reader(args[1]);
        if (!r.check()) {
            System.exit(0);
        }
        Image img = r.createImage();
        img.printInfo();

        Kernel_Gaussian g = new Kernel_Gaussian(Integer.parseInt(args[2]));
        g.createKernel(Double.parseDouble(args[3]));
        Image_GB newImg = new Image_GB();
        Image.transfer(img, newImg);
        newImg.printInfo();
        newImg.applyBlur(g.getSize(), g.getKernel());
        newImg.write(r.getFilename());
    }
}
