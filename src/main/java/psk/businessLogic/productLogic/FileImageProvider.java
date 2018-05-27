package psk.businessLogic.productLogic;

import psk.database.entities.Product;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Alternative;
import javax.imageio.ImageIO;
import javax.inject.Named;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.awt.image.WritableRaster;
import java.io.*;
import java.nio.file.*;
import java.util.Arrays;

@ApplicationScoped
@Named
@Alternative
public class FileImageProvider implements ImageProvider {

    String imageLocation = System.getProperty("image.location");

    public byte[] getImage(Integer productId, int index) {
        //Path folder = Paths.get(System.getProperty("upload.location"));
        File folder = new File(imageLocation + "\\product" + productId);
        File[] listOfFiles = folder.listFiles();

        if(listOfFiles != null && listOfFiles.length == 0) return new byte[0];

        try {
            BufferedImage bufferedImage = ImageIO.read(listOfFiles[index]);

            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            ImageIO.write(bufferedImage, "png", bos );
            byte [] data = bos.toByteArray();

            bos.close();

            return data;
        } catch (Exception e) {
            e.printStackTrace();

            return new byte[0];
        }
    }

    public void saveImage(byte[] image, Integer productId) {

        String productImageLocation = imageLocation + "\\product" + productId;

        File folder = new File(productImageLocation);
        if (! folder.exists()){
            folder.mkdir();
            // If you require it to make the entire directory path including parents,
            // use directory.mkdirs(); here instead.
        }

        File[] listOfFiles = folder.listFiles();

        if(listOfFiles != null) Arrays.sort(listOfFiles);

        try{
            ByteArrayInputStream bais = new ByteArrayInputStream(image);
            BufferedImage bufferedImage = ImageIO.read(bais);
            if(listOfFiles != null) {
                ImageIO.write(bufferedImage, "png", new File(imageLocation + "\\" + listOfFiles.length));
            } else {
                ImageIO.write(bufferedImage, "png", new File(imageLocation + "\\0"));
            }
            bais.close();
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }



}
