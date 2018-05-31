package psk.front;

import lombok.Getter;
import lombok.Setter;
import psk.Utilities.MessageHandler;
import psk.businessLogic.productLogic.ProductUtility;
import psk.database.entities.ProductCategory;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;

@ViewScoped
@Named
public class ModCategoriesFront implements Serializable {

    @Inject
    private MessageHandler messageHandler;

    @Getter
    @Setter
    private List<ProductCategory> productCategoryList;

    @Getter
    @Setter
    private List<ProductCategory> topLevelProductCategoryList;

    @Inject
    private ProductUtility productUtility;

    @Inject
    private LayoutFront layoutFront;

    @Getter
    @Setter
    private ProductCategory productCategory = new ProductCategory();


    @PostConstruct
    public void init(){
        productCategoryList = productUtility.getAllProductCategories();
        topLevelProductCategoryList = productUtility.getAllTopLevelCategories();
    }

    public void addCategory(){
        productCategoryList.add(productCategory);
        productUtility.createCategory(productCategory);
        productCategory = new ProductCategory();
        messageHandler.addMessage("Successful", null);
    }

    public void deleteCategory(ProductCategory productCategory){
        productCategoryList.remove(productCategory);
        layoutFront.getCats().remove(productCategory);
        productUtility.deleteCategory(productCategory);
        messageHandler.addMessage("Successful", productCategory.getName() + " was deleted!");
    }
}
