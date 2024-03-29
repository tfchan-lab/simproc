public class Sharpen implements Command {
    public void exec(String[] args) {
        Reader r = new Reader(args[1]);
        if (!r.check()) {
            System.exit(0);
        }
        Image img = r.createImage();
        img.printInfo();

        Kernel_Laplacian l = new Kernel_Laplacian();
        l.createKernel(Double.parseDouble(args[2]));
        Image_LS newImg = new Image_LS();
        Image.transfer(img, newImg);
        newImg.printInfo();
        newImg.applySharpen(l.getSize(), l.getKernel());
        newImg.write(r.getFilename());
    }
}