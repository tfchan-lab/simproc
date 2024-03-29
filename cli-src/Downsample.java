public class Downsample implements Command {
    public void exec(String[] args) {
        Reader r = new Reader(args[1]);
        if (!r.check()) {
            System.exit(0);
        }
        Image img = r.createImage();
        img.printInfo();

        Image_DS newImg = new Image_DS();
        Image.transfer(img, newImg);
        newImg.updateDimension(Integer.parseInt(args[3]));
        newImg.printInfo();
        switch (args[2]) {
            case "max":
                newImg.applyMaxPool();
                break;
            case "average":
                newImg.applyAvgPool();
                break;
            default:
                newImg.applyMaxPool();
                break;
        }
        newImg.write(r.getFilename());
    }
}
