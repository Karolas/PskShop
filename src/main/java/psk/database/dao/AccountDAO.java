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

    public Integer getAccountCount(){
        return (Integer) em.createNamedQuery("Account.findAllCount").getSingleResult();
    }

    public List<Account> getAll(){
        return em.createNamedQuery("Account.selectAll").getResultList();
    }
//    public List<Account> getResultList(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, String> filters) {
//        List<Account> all = new ArrayList<Account>();
//        all.addAll(this.baseService.getSiteDAO().getAll(first,pageSize,sortField,sortOrder,filters));
//        return all;
//    }
//    public int count(String sortField, SortOrder sortOrder, Map<String, String> filters) {
//        return this.baseService.getSiteDAO().getAll(-1,-1,null,null,filters).size();
//    }
//    public Collection<Account> getAll(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, String> filters) {
//        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
//        CriteriaQuery<Account> q = cb.createQuery(Site.class);
//        Root<Account> site = q.from(Site.class);
//        Join<Account,SiteType> siteType = site.join(Site_.siteType);
//        q.select(site);
//
//        Path<?> path = getPath(sortField, site, siteType);
//        if (sortOrder == null){
//            //just don't sort
//        }else if (sortOrder.equals(SortOrder.ASCENDING)){
//            q.orderBy(cb.asc(path));
//        }else if (sortOrder.equals(SortOrder.DESCENDING)){
//            q.orderBy(cb.asc(path));
//        }else if (sortOrder.equals(SortOrder.UNSORTED)){
//            //just don't sort
//        }else{
//            //just don't sort
//        }
//
//        //filter
//        Predicate filterCondition = cb.conjunction();
//        for (Map.Entry<String, String> filter : filters.entrySet()) {
//            if (!filter.getValue().equals("")) {
//                //try as string using like
//                Path<String> pathFilter = getStringPath(filter.getKey(), site, siteType);
//                if (pathFilter != null){
//                    filterCondition = cb.and(filterCondition, cb.like(pathFilter, "%"+filter.getValue()+"%"));
//                }else{
//                    //try as non-string using equal
//                    Path<?> pathFilterNonString = getPath(filter.getKey(), site, siteType);
//                    filterCondition = cb.and(filterCondition, cb.equal(pathFilterNonString, filter.getValue()));
//                }
//            }
//        }
//        q.where(filterCondition);
//
//        //pagination
//        TypedQuery<Account> tq = em.createQuery(q);
//        if (pageSize >= 0){
//            tq.setMaxResults(pageSize);
//        }
//        if (first >= 0){
//            tq.setFirstResult(first);
//        }
//        return tq.getResultList();
//    }

//    public List<Account> getAccountList(int start, int size,
//                                         Map<String, Object> filters) {
//        CriteriaBuilder cb = em.getCriteriaBuilder();
//        CriteriaQuery<Account> criteriaQuery = cb.createQuery(Account.class);
//        Root<Account> root = criteriaQuery.from(Account.class);
//        CriteriaQuery<Account> select = criteriaQuery.select(root);
//
//        if (filters != null && filters.size() > 0) {
//            List<Predicate> predicates = new ArrayList<>();
//            for (Map.Entry<String, Object> entry : filters.entrySet()) {
//                String field = entry.getKey();
//                Object value = entry.getValue();
//                if (value == null) {
//                    continue;
//                }
//
//                Expression<String> expr = root.get(field).as(String.class);
//                Predicate p = cb.like(cb.lower(expr),
//                        "%" + value.toString().toLowerCase() + "%");
//                predicates.add(p);
//            }
//            if (predicates.size() > 0) {
//                criteriaQuery.where(cb.and(predicates.toArray
//                        (new Predicate[predicates.size()])));
//            }
//        }
//
//        TypedQuery<Account> query = em.createQuery(select);
//        query.setFirstResult(start);
//        query.setMaxResults(size);
//        List<Account> list = query.getResultList();
//        return list;
//    }
//
//    public int getFilteredRowCount(Map<String, Object> filters) {
//        CriteriaBuilder cb = em.getCriteriaBuilder();
//        CriteriaQuery<Long> criteriaQuery = cb.createQuery(Long.class);
//        Root<Account> root = criteriaQuery.from(Account.class);
//        CriteriaQuery<Long> select = criteriaQuery.select(cb.count(root));
//
//        if (filters != null && filters.size() > 0) {
//            List<Predicate> predicates = new ArrayList<>();
//            for (Map.Entry<String, Object> entry : filters.entrySet()) {
//                String field = entry.getKey();
//                Object value = entry.getValue();
//                if (value == null) {
//                    continue;
//                }
//
//                Expression<String> expr = root.get(field).as(String.class);
//                Predicate p = cb.like(cb.lower(expr),
//                        "%" + value.toString().toLowerCase() + "%");
//                predicates.add(p);
//            }
//            if (predicates.size() > 0) {
//                criteriaQuery.where(cb.and(predicates.toArray
//                        (new Predicate[predicates.size()])));
//            }
//        }
//        Long count = em.createQuery(select).getSingleResult();
//        return count.intValue();
//    }
}
