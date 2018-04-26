package psk.database.dao;

import psk.database.entities.Account;

import javax.enterprise.context.RequestScoped;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import java.io.Serializable;
import java.math.BigDecimal;

@SessionScoped
@Named
public class AccountDAO implements Serializable {
    @Inject
    private EntityManager em;

    public Account selectAccountByEmail(String email) {
        return em.createQuery("SELECT a FROM Account a WHERE a.email = :accEmail", Account.class)
                .setParameter("accEmail", email)
                .getSingleResult();
    }

    public void insertAccount(Account account) {
        em.persist(account);
    }

    public void updateAccount(Account account) {
        Account account1 = em.find(Account.class, account.getId());

        em.getTransaction().begin();
        if (!account1.getFirstName().equals(account.getFirstName()))
            account1.setFirstName(account.getFirstName());

        if (!account1.getLastName().equals(account.getLastName()))
            account1.setLastName(account.getLastName());

        if (!account1.getAddress().equals(account.getAddress()))
            account1.setAddress(account.getAddress());

        if (!account1.getPostalNr().equals(account.getPostalNr()))
            account1.setPostalNr(account.getPostalNr());

        if (!account1.getTelephoneNr().equals(account.getTelephoneNr()))
            account1.setTelephoneNr(account.getTelephoneNr());
        em.getTransaction().commit();
    }
}
