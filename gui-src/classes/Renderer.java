import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.FileNotFoundException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Renderer extends JFrame {
    private JLabel imageLabel;
    private JSlider blurKS, blurSD, sobelTH, cannyLO, cannyHI;
    private JButton greyButton, blurButton, sobelButton, cannyButton;
    private Image image;

    public Renderer(String s) {
        try {
            image = new Image(s);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        imageLabel = new JLabel(new ImageIcon(convertToBufferedImage(image)));

        blurKS = new JSlider(JSlider.HORIZONTAL, 3, 15, 3);
        blurKS.setMajorTickSpacing(2);
        blurKS.setPaintTicks(true);
        blurKS.setPaintLabels(true);

        blurSD = new JSlider(JSlider.HORIZONTAL, 0, 100, 0);
        blurSD.setMajorTickSpacing(10);
        blurSD.setPaintTicks(true);
        blurSD.setPaintLabels(true);

        sobelTH = new JSlider(JSlider.HORIZONTAL, 0, 255, 60);
        sobelTH.setMajorTickSpacing(15);
        sobelTH.setPaintTicks(true);
        sobelTH.setPaintLabels(true);

        cannyLO = new JSlider(JSlider.HORIZONTAL, 0, 255, 25);
        cannyLO.setMajorTickSpacing(15);
        cannyLO.setPaintTicks(true);
        cannyLO.setPaintLabels(true);

        cannyHI = new JSlider(JSlider.HORIZONTAL, 0, 255, 225);
        cannyHI.setMajorTickSpacing(15);
        cannyHI.setPaintTicks(true);
        cannyHI.setPaintLabels(true);
        
        greyButton = new JButton("Greyscale");
        greyButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Greyscale grey = new Greyscale(image);
                image.pixels = grey.pixels;
                imageLabel.setIcon(new ImageIcon(convertToBufferedImage(image)));
            }
        });

        blurButton = new JButton("Gaussian Blur");
        blurButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Kernel g = new Kernel();
                g.createKernel("gaussian", blurKS.getValue(), blurSD.getValue());
                Gaussian blur = new Gaussian(image, g);
                image.pixels = blur.pixels;
                imageLabel.setIcon(new ImageIcon(convertToBufferedImage(image)));
            }
        });

        sobelButton = new JButton("Edge Detect (Sobel)");
        sobelButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Kernel x = new Kernel();
                x.createKernel("sobel_x");
                Kernel y = new Kernel();
                y.createKernel("sobel_y");
                Sobel sobel = new Sobel(image, x, y, sobelTH.getValue());
                image.pixels = sobel.pixels;
                imageLabel.setIcon(new ImageIcon(convertToBufferedImage(image)));
            }
        });

        cannyButton = new JButton("Edge Detect (Canny)");
        cannyButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Canny c = new Canny(image);
                c.NMS();
                c.HT(cannyLO.getValue(), cannyHI.getValue());
                c.BR();
                image.pixels = c.pixels;
                imageLabel.setIcon(new ImageIcon(convertToBufferedImage(image)));
            }
        });

        this.setLayout(new BorderLayout());
        JPanel blurSliders = new JPanel(new GridLayout(1, 2));
        blurSliders.add(blurKS);
        blurSliders.add(blurSD);
        JPanel cannySliders = new JPanel(new GridLayout(1, 2));
        cannySliders.add(cannyLO);
        cannySliders.add(cannyHI);
        JPanel bottom = new JPanel(new GridLayout(7, 1));
        bottom.add(greyButton);
        bottom.add(blurSliders);
        bottom.add(blurButton);
        bottom.add(sobelTH);
        bottom.add(sobelButton);
        bottom.add(cannySliders);
        bottom.add(cannyButton);
        this.add(bottom, BorderLayout.SOUTH);
        this.add(imageLabel, BorderLayout.CENTER);

        this.pack();
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
    }

    private BufferedImage convertToBufferedImage(Image img) {
        Dimension size = Toolkit.getDefaultToolkit().getScreenSize(); 
        int width = (int)size.getWidth(); 
        int height = (int)size.getHeight();
        if (width >= image.sizeX) {
            width = image.sizeX;
            height = image.sizeY;
        }
        if (width < image.sizeX) {
            int i = 2;
            while (image.sizeX / i > width) {
                i++;
            }
            width = image.sizeX / i;
            height = image.sizeY / i;
            MaxPool m = new MaxPool(image, i);
            image.pixels = m.pixels;
            image.sizeX = m.sizeX;
            image.sizeY = m.sizeY;
        }

        BufferedImage bufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                int r = img.pixels[y][x][0];
                int g = img.pixels[y][x][1];
                int b = img.pixels[y][x][2];
                bufferedImage.setRGB(x, y, (r << 16) | (g << 8) | b);
            }
        }
        return bufferedImage;
    }
}
