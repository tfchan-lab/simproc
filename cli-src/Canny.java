public class Canny implements Command{
    public void exec(String[] args) {
        Reader r = new Reader(args[1]);
        if (!r.check()) {
            System.exit(0);
        }
        Image img = r.createImage();
        img.printInfo();

        Image_EC newImg = new Image_EC();
        Image.transfer(img, newImg);
        newImg.printInfo();
        newImg.applyCanny(Integer.parseInt(args[2]), Integer.parseInt(args[3]));
        newImg.write(r.getFilename());
    }
}
