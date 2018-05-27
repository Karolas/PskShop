package psk.database.resources;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.Dependent;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Default;
import javax.enterprise.inject.Disposes;
import javax.enterprise.inject.Produces;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;
import javax.persistence.SynchronizationType;

@ApplicationScoped
public class ResourcesEMF {
    @PersistenceUnit
    private EntityManagerFactory emf;

    @Produces
    @Default
    @RequestScoped
    private EntityManager createJTAEntityManager() {
        return emf.createEntityManager(SynchronizationType.SYNCHRONIZED);
    }

    @Produces
    @AsyncComp // Savo susikurta anotacija (Qualifier), skirta: 1. Asinchroniniams komponentams 2. JPA klaidoms apdoroti (pvz.: OptimisticLockException)
    @Dependent
    private EntityManager createJTATransactionalEntityManager() {
        return emf.createEntityManager(SynchronizationType.SYNCHRONIZED);
    }

    private void closeUnsynchronizedEntityManager(@Disposes EntityManager em) {
        em.close();
    }

    private void closeSynchEntityManager(@Disposes @AsyncComp EntityManager em) {
        em.close();
    }
}
