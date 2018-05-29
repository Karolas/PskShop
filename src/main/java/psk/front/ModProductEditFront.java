package psk.front;

import lombok.Getter;
import lombok.Setter;
import org.primefaces.event.FileUploadEvent;
import psk.businessLogic.productLogic.ImageUtility;
import psk.businessLogic.productLogic.LocalImageProvider;
import psk.businessLogic.productLogic.ProductUtility;
import psk.database.entities.Product;
import psk.database.entities.ProductImage;

import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.util.*;

@ViewScoped
@Named
public class ModProductEditFront implements Serializable {

    @Inject
    private ImageUtility imageUtility;

    @Inject
    private ProductUtility productUtility;

    @Inject
    private LocalImageProvider localImageProvider;

    @Getter
    @Setter
    private Product selectedProduct;

    @Getter
    @Setter
    private List<ProductImage> imagesToBeRemoved = new ArrayList<>();

    @Getter
    @Setter
    private ProductImage imageToBeMain;

    @Getter
    @Setter
    private List<byte[]> imagesBytes = new ArrayList<>();

    @Getter
    @Setter
    private Integer mainImageLocal = -1;

    @Getter
    @Setter
    private Integer mainImage = -1;

    @Getter
    @Setter
    private long lastModified;

    @Getter
    @Setter
    private boolean isNewProduct;

    @PostConstruct
    public void init() {
        lastModified = System.currentTimeMillis();

        localImageProvider.clear();
        imagesBytes = localImageProvider.getImages();

        FacesContext fc = FacesContext.getCurrentInstance();
        Map<String, String> params = fc.getExternalContext().getRequestParameterMap();
        if(params.size() > 0){
            Integer productId = new Integer(params.get("productId"));
            selectedProduct = productUtility.getProduct(productId);
            if(selectedProduct.getMainImageId() != null) {
                mainImage = selectedProduct.getMainImageId();
            }
            isNewProduct = false;
        } else {
            selectedProduct = new Product();
            isNewProduct = true;
        }
    }

    public void fileUpload(FileUploadEvent event) throws IOException {
        lastModified = System.currentTimeMillis();

        InputStream is = event.getFile().getInputstream();
        ByteArrayOutputStream out = new ByteArrayOutputStream();//FileOutputStream(file);
        byte buf[] = new byte[1024];
        int len;
        while ((len = is.read(buf)) > 0)
            out.write(buf, 0, len);
        imagesBytes.add(out.toByteArray());
        is.close();
        out.close();
    }

    public Integer[] getImageIds() {
        return localImageProvider.getImageIds();
    }

    public String updateProduct() {
        productUtility.updateProduct(selectedProduct);

        for(int i = 0; i < imagesBytes.size(); i++){
            if(i == mainImageLocal) {
                productUtility.addMainImageToProduct(selectedProduct, imagesBytes.get(i));
            } else {
                productUtility.addImageToProduct(selectedProduct, imagesBytes.get(i));
            }
        }

        for (ProductImage productImage: imagesToBeRemoved) {
            productUtility.removeImageFromProduct(productImage);
        }

        imageUtility.setModified();

        return "/admin/products.xhtml";
    }

    public String createProduct() {
        productUtility.createProduct(selectedProduct);
        for(int i = 0; i < imagesBytes.size(); i++){
            if(i == mainImageLocal) {
                productUtility.addMainImageToProduct(selectedProduct, imagesBytes.get(i));
            } else {
                productUtility.addImageToProduct(selectedProduct, imagesBytes.get(i));
            }
        }

        imageUtility.setModified();

        return "/admin/products.xhtml";
    }

    public void markAsMainImage(ProductImage productImage){
        selectedProduct.setMainImageId(productImage.getId());
        mainImage = productImage.getId();

        mainImageLocal = -1;
    }

    public void makeAsMainImageLocal(Integer imageId){
        mainImageLocal = imageId;

        selectedProduct.setMainImageId(null);
        mainImage = -1;
    }

    public void deleteImage(ProductImage productImage){
        imagesToBeRemoved.add(productImage);
        if(selectedProduct.getMainImageId().equals(productImage.getId())) {
            mainImageLocal = null;
        }
    }

    public void keepImage(ProductImage productImage) {
        imagesToBeRemoved.remove(productImage);
    }

    public boolean isMarkedForRemoval(ProductImage productImage) {
        if(imagesToBeRemoved.contains(productImage)) return true;
        else return false;
    }

    public void deleteImageLocal(Integer imageId) {
        lastModified = System.currentTimeMillis();

        localImageProvider.removeImage(imageId);

        if(mainImageLocal.equals(imageId)) {
            mainImageLocal = 0;
        }
    }
}
