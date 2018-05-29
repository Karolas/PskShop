package psk.front;

import lombok.Getter;
import lombok.Setter;
import org.primefaces.model.menu.DefaultMenuItem;
import org.primefaces.model.menu.DefaultMenuModel;
import org.primefaces.model.menu.DefaultSubMenu;
import org.primefaces.model.menu.MenuModel;
import psk.database.dao.ProductCategoryDAO;
import psk.database.entities.Product;
import psk.database.entities.ProductCategory;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.IOException;
import java.util.List;

@Named
@ApplicationScoped
public class LayoutFront {
    @Getter
    @Setter
    private List<String> categories;
    @Getter
    @Setter
    private List<String> subCategories;

    @Getter
    List<ProductCategory> cats;
    @Getter
    List<ProductCategory> subs;
    @Getter
    @Setter
    private MenuModel menuModel;

    @Inject
    ProductCategoryDAO productCategoryDAO;
    @PostConstruct
    private void init(){
        menuModel = new DefaultMenuModel();
        cats = productCategoryDAO.getAllCategories();
        for(int i=0; i<cats.size(); i++) {
            DefaultSubMenu subMenu = new DefaultSubMenu(cats.get(i).getName());
            subs = productCategoryDAO.getSubCategorieByParent(cats.get(i).getId());
            for (int j = 0; j < subs.size(); j++) {
                DefaultMenuItem item = new DefaultMenuItem( subs.get(j).getName());
                item.setIcon("ui-icon-battery-0");
                item.setCommand("#{layoutFront.redirectToProductSearchWithCategory(" + subs.get(j).getId() + ")}");
                subMenu.addElement(item);
            }
            menuModel.addElement(subMenu);
        }
    }

    public List<ProductCategory> getSubcategories(Integer parentId){
        return productCategoryDAO.getSubCategorieByParent(parentId);
    }

    public void redirectToProductSearchWithCategory(Integer categoryId) {
        ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();

        try {
            ec.redirect("index.xhtml?categoryId=" + categoryId);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
