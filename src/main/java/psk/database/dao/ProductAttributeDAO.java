package psk.database.dao;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import java.io.Serializable;

@SessionScoped
@Named
public class ProductAttributeDAO implements Serializable {
    @Inject
    private EntityManager em;
}
