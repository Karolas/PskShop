package psk.businessLogic.productLogic;

import java.util.List;

public interface LocalImageProvider {
    byte[] getImage(Integer index);
    List<byte[]> getImages();
    void clear();
    Integer[] getImageIds();
    void removeImage(Integer index);
}
