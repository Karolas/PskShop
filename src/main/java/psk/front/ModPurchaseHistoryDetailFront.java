package psk.front;

import jdk.nashorn.internal.objects.annotations.Getter;
import jdk.nashorn.internal.objects.annotations.Setter;
import org.omnifaces.cdi.Param;
import psk.database.entities.Cart;
import psk.database.entities.CartProducts;
import psk.database.entities.Order;
import psk.database.entities.Product;

import javax.annotation.PostConstruct;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.IOException;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

@ViewScoped
@Named
public class ModPurchaseHistoryDetailFront implements Serializable {

    @Inject
    @Param(name="orderId")
    private Integer orderId;

}
