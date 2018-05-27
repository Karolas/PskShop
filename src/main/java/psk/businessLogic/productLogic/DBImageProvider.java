package psk.businessLogic.productLogic;

import psk.database.entities.Product;
import psk.database.resources.AsyncComp;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Alternative;
import javax.enterprise.inject.Default;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;

@ApplicationScoped
@Named
@Alternative
public class DBImageProvider implements ImageProvider {

    @Inject
    private ProductUtility productUtility;
//    @Inject
//    @AsyncComp
//    private EntityManager entityManager;

    public byte[] getImage(Integer productId, int index) {
//        return entityManager.find(Product.class, productId).getImage();
        return productUtility.getImage(productId);
    }

    @Transactional
    public void saveImage(byte[] image, Integer productId) {
        productUtility.getProduct(productId).setImage(image);
    }
}
