package psk.businessLogic.productLogic;

public interface ImageProvider {
    byte[] getImage(Integer index);
    void saveImage(byte[] image, Integer imageId);
}
