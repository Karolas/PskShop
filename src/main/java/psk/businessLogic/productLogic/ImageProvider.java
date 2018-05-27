package psk.businessLogic.productLogic;

public interface ImageProvider {
    byte[] getImage(Integer productId, int index);
    void saveImage(byte[] image, Integer productId);
}
