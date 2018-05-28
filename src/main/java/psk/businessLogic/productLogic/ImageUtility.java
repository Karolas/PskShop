package psk.businessLogic.productLogic;

import org.omnifaces.cdi.GraphicImageBean;

import javax.imageio.ImageIO;
import javax.inject.Inject;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

@GraphicImageBean
public class ImageUtility {
    private static Dimension MAX_DIMENSION = new Dimension(200, 200);
    private static Dimension MAX_DIMENSION_FULL = new Dimension(1200, 800);

    @Inject
    private ImageProvider imageProvider;

    public byte[] getImage(Integer imageId) {

        byte[] imageBytes = imageProvider.getImage(imageId);
        if(imageBytes == null) return new byte[0];

        return rescaleImage(imageBytes, MAX_DIMENSION);
    }

    public byte[] getImageFull(Integer imageId) {
        byte[] imageBytes = imageProvider.getImage(imageId);
        if(imageBytes == null) return new byte[0];

        return rescaleImage(imageBytes, MAX_DIMENSION_FULL);
    }

    private byte[] rescaleImage(byte[] imageBytes, Dimension dimension) {
        try {
            ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(imageBytes);

            BufferedImage image = ImageIO.read(byteArrayInputStream);

            Dimension scaledImageDimension = getScaledDimension(new Dimension(image.getWidth(), image.getHeight()), dimension);

            BufferedImage scaledImage =
                    toBufferedImage(image.getScaledInstance((int)scaledImageDimension.getWidth(), (int)scaledImageDimension.getHeight(), Image.SCALE_DEFAULT));

            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ImageIO.write( scaledImage, "png", baos );
            baos.flush();
            byte[] imageInByte = baos.toByteArray();
            baos.close();

            return imageInByte;
        } catch (IOException e) {
            e.printStackTrace();
            return new byte[0];
        }
    }

    private Dimension getScaledDimension(Dimension imgSize, Dimension boundary) {

        int original_width = imgSize.width;
        int original_height = imgSize.height;
        int bound_width = boundary.width;
        int bound_height = boundary.height;
        int new_width = original_width;
        int new_height = original_height;

        // first check if we need to scale width
        if (original_width > bound_width) {
            //scale width to fit
            new_width = bound_width;
            //scale height to maintain aspect ratio
            new_height = (new_width * original_height) / original_width;
        }

        // then check if we need to scale even with the new height
        if (new_height > bound_height) {
            //scale height to fit instead
            new_height = bound_height;
            //scale width to maintain aspect ratio
            new_width = (new_height * original_width) / original_height;
        }

        return new Dimension(new_width, new_height);
    }

    private BufferedImage toBufferedImage(Image img)
    {
        if (img instanceof BufferedImage)
        {
            return (BufferedImage) img;
        }

        // Create a buffered image with transparency
        BufferedImage bimage = new BufferedImage(img.getWidth(null), img.getHeight(null), BufferedImage.TYPE_INT_ARGB);

        // Draw the image on to the buffered image
        Graphics2D bGr = bimage.createGraphics();
        bGr.drawImage(img, 0, 0, null);
        bGr.dispose();

        // Return the buffered image
        return bimage;
    }
}
