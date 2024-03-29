public class Sobel implements Command {
    public void exec(String[] args) {
        Reader r = new Reader(args[1]);
        if (!r.check()) {
            System.exit(0);
        }
        Image img = r.createImage();
        img.printInfo();

        Kernel_Sobel x = new Kernel_Sobel('x');
        Kernel_Sobel y = new Kernel_Sobel('y');
        x.createKernel();
        y.createKernel();
        Image_ES newImg = new Image_ES();
        Image.transfer(img, newImg);
        newImg.printInfo();
        newImg.applySobel(x, y, Integer.parseInt(args[2]));
        newImg.write(r.getFilename());
    }
}
