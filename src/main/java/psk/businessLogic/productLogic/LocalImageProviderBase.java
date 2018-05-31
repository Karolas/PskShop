package psk.businessLogic.productLogic;

import lombok.Getter;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.Conversation;
import javax.enterprise.context.ConversationScoped;
import javax.enterprise.context.SessionScoped;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
@Named
public class LocalImageProviderBase implements Serializable, LocalImageProvider {

    @Getter
    private List<byte[]> images = new ArrayList<>();

    public byte[] getImage(Integer index) {
        try{
            return images.get(index);
        } catch (Exception e) {
            return new byte[0];
        }
    }

    public void clear() {
        images.clear();
    }

    public Integer[] getImageIds() {
        int imageSize = images.size();
        Integer[] indexArray = new Integer[imageSize];

        for(int i = 0; i < imageSize; i++) {
            indexArray[i] = i;
        }

        return indexArray;
    }

    public void removeImage(Integer index) {
        images.remove(images.get(index));
    }
}
