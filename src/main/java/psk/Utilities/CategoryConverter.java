package psk.Utilities;

import psk.database.dao.ProductCategoryDAO;
import psk.database.entities.ProductCategory;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.inject.Inject;

public class CategoryConverter implements Converter {

    @Inject
    ProductCategoryDAO productCategoryDAO;

    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        // Convert the unique String representation of Foo to the actual Foo object.
        return productCategoryDAO.getCategoryByName(value);
    }

    public String getAsString(FacesContext context, UIComponent component, Object value) {
        // Convert the Foo object to its unique String representation.
        return value == null ? ""  : ((ProductCategory) value).getName();
    }
}
