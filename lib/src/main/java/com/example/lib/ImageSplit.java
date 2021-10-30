package com.nhatminhnguyen.lib;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;

import javax.imageio.ImageIO;

public class ImageSplit {

    static void splitImage(String imagePath, String ImageFile, String imageName) throws Exception {
        // get image file
        File f = new File(imagePath + ImageFile);

        FileInputStream fis = new FileInputStream(f);
        BufferedImage image = ImageIO.read(fis); //reading the image file

        int rows = 3; //You should decide the values for rows and cols variables
        int cols = 3;
        int chunks = rows * cols;

        int chunkWidth = image.getWidth() / cols; // determines the chunk width and height
        int chunkHeight = image.getHeight() / rows;
        int count = 0;
        BufferedImage imgs[] = new BufferedImage[chunks]; //Image array to hold image chunks
        for (int x = 0; x < rows; x++) {
            for (int y = 0; y < cols; y++) {
                //Initialize the image array with image chunks
                imgs[count] = new BufferedImage(chunkWidth, chunkHeight, image.getType());

                // draws the image chunk
                Graphics2D gr = imgs[count++].createGraphics();
                gr.drawImage(image, 0, 0, chunkWidth, chunkHeight, chunkWidth * y, chunkHeight * x, chunkWidth * y + chunkWidth, chunkHeight * x + chunkHeight, null);
                gr.dispose();
            }
        }
        System.out.println("Splitting done");
        //Creating a folder using mkdir() method

        //writing mini images into image files
        for (int i = 0; i < imgs.length; i++) {
            File output = new File(imagePath + imageName + i + ".jpg");
            ImageIO.write(imgs[i], "jpg", output);
            System.out.println(imgs[i]);
        }
        System.out.println("Mini images created");
    }

    public static void main(String[] args) throws Exception {
        String imagePath = "D:\\T\\ảnh\\bear_image\\";
        String imageNameFile = "ursidae.png";
        String imageName = "ursidae";
        splitImage(imagePath, imageNameFile, imageName);
    }
}
