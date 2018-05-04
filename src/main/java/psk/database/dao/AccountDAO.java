package psk.database.dao;

import org.primefaces.model.SortOrder;
import psk.database.entities.Account;

import javax.enterprise.context.RequestScoped;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;
import javax.transaction.Transactional;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

@SessionScoped
@Named
public class AccountDAO implements Serializable {
    @Inject
    private EntityManager em;

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
        Predicate filterCondition = cb.conjunction();
        String wildCard = "%";
        for (Map.Entry<String, Object> filter : filters.entrySet()) {
            String value = wildCard + filter.getValue() + wildCard;
            if (!filter.getValue().equals("")) {
                javax.persistence.criteria.Path<String> path = myObj.get(filter.getKey());
                filterCondition = cb.and(filterCondition, cb.like(path, value));
            }
        }
        return filterCondition;
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
        if (sortField != null) {
            if (sortOrder == SortOrder.ASCENDING) {
                cq.orderBy(cb.asc(myObj.get(sortField)));
            } else if (sortOrder == SortOrder.DESCENDING) {
                cq.orderBy(cb.desc(myObj.get(sortField)));
            }
        }
        return em.createQuery(cq).setFirstResult(first).setMaxResults(pageSize).getResultList();
    }
}
