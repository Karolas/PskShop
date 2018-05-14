package psk.database.dao;

import org.primefaces.model.SortOrder;
import psk.Utils;
import psk.database.entities.Account;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.criteria.*;
import javax.transaction.Transactional;
import java.io.Serializable;
import java.util.List;
import java.util.Map;

@SessionScoped
@Named
public class AccountDAO implements Serializable {
    @Inject
    private EntityManager em;

    @Inject
    private Utils utils;

    @Transactional
    public Account selectAccountByEmail(String email) {
        return em.createQuery("SELECT a FROM Account a WHERE a.email = :accEmail", Account.class)
                .setParameter("accEmail", email)
                .getSingleResult();
    }

    @Transactional
    public boolean isAccountExists(String email) {
        return em.createQuery("SELECT a FROM Account a WHERE a.email = :accEmail", Account.class)
                .setParameter("accEmail", email)
                .getResultList().size() != 0;
    }

    @Transactional
    public void insertAccount(Account account) {
        em.persist(account);
    }

    @Transactional
    public void updateAccount(Account account) {
        Account account1 = em.find(Account.class, account.getId());

//        if (!account1.getFirstName().equals(account.getFirstName()))
            account1.setFirstName(account.getFirstName());
//
//        if (!account1.getLastName().equals(account.getLastName()))
            account1.setLastName(account.getLastName());
//
//        if (!account1.getAddress().equals(account.getAddress()))
            account1.setAddress(account.getAddress());
//
//        if (!account1.getPostalNr().equals(account.getPostalNr()))
            account1.setPostalNr(account.getPostalNr());
//
//        if (!account1.getTelephoneNr().equals(account.getTelephoneNr()))
            account1.setTelephoneNr(account.getTelephoneNr());
//
//        if (!account1.getRole().equals(account.getRole()))
            account1.setRole(account.getRole());
    }

    @Transactional
    public void updatePassword(Account account, String hashedPassword) {
        Account account1 = em.find(Account.class, account.getId());

        account1.setHashedPassword(hashedPassword);
    }

    private Predicate getFilterCondition(CriteriaBuilder cb, Root<Account> myObj, Map<String, Object> filters) {
        return utils.getFilterCondition(cb, myObj, filters);
    }

    public int count(Map<String, Object> filters) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<Account> myObj = cq.from(Account.class);
        Predicate filterCondition = this.getFilterCondition(cb, myObj, filters);
        if(filterCondition.getExpressions().size() != 0) {
            cq.where(filterCondition);
        }
        cq.select(cb.count(myObj));
        int a = em.createQuery(cq).getSingleResult().intValue();
        return a;
    }

    public List<Account> getResultList(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, Object> filters) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Account> cq = cb.createQuery(Account.class);
        Root<Account> myObj = cq.from(Account.class);
        Predicate filterCondition = this.getFilterCondition(cb, myObj, filters);
        if(filterCondition.getExpressions().size() != 0) {
            cq.where(filterCondition);
        }
        cq = utils.getSortOrder(cq, cb, myObj, sortOrder, sortField);
        return em.createQuery(cq).setFirstResult(first).setMaxResults(pageSize).getResultList();
    }
}
