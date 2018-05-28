package psk.businessLogic.productLogic;

import psk.database.dao.ProductImageDAO;
import psk.database.entities.Product;
import psk.database.entities.ProductImage;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Alternative;
import javax.imageio.ImageIO;
import javax.inject.Inject;
import javax.inject.Named;
import javax.transaction.Transactional;
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

    private String imageLocation = System.getProperty("image.location");

    public byte[] getImage(Integer imageId) {
        //Path folder = Paths.get(System.getProperty("upload.location"));
        File folder = new File(imageLocation);
        File[] listOfFiles = folder.listFiles();

        if(listOfFiles == null || listOfFiles.length == 0) return new byte[0];

        try {
            File requestedImage = null;

            for(File image: listOfFiles) {
                if(image.getName().equals(imageId.toString())) {
                    requestedImage = image;
                    break;
                }
            }

            if(requestedImage != null) {
                BufferedImage bufferedImage = ImageIO.read(requestedImage);

                ByteArrayOutputStream bos = new ByteArrayOutputStream();
                ImageIO.write(bufferedImage, "png", bos );
                byte [] data = bos.toByteArray();

                bos.close();

                return data;
            } else {
                return new byte[0];
            }

        } catch (Exception e) {
            e.printStackTrace();

            return new byte[0];
        }
    }

    @Transactional
    public void saveImage(byte[] image, Integer imageId) {
        File folder = new File(imageLocation);
        File[] listOfFiles = folder.listFiles();

        if(listOfFiles != null) Arrays.sort(listOfFiles);

        try{
            ByteArrayInputStream bais = new ByteArrayInputStream(image);
            BufferedImage bufferedImage = ImageIO.read(bais);

            if(listOfFiles != null) {
                ImageIO.write(bufferedImage, "png",
                        new File(imageLocation + "\\" + imageId));
            }
            bais.close();
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }



}
