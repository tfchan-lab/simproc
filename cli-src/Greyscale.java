public class Greyscale implements Command {
    public void exec(String[] args) {
        Reader r = new Reader(args[1]);
        if (!r.check()) {
            System.exit(0);
        }
        Image img = r.createImage();
        img.printInfo();

        Image_GS newImg = new Image_GS();
        Image.transfer(img, newImg);
        newImg.printInfo();
        newImg.applyGrey();
        newImg.write(r.getFilename());
    }
}
