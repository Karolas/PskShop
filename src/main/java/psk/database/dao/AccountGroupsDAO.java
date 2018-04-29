package psk.database.dao;

import psk.database.entities.Account;
import psk.database.entities.accountGroup.AccountGroups;
import psk.database.entities.accountGroup.AccountGroupsPK;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import java.io.Serializable;
import java.util.List;

@SessionScoped
@Named
public class AccountGroupsDAO implements Serializable {
    @Inject
    private EntityManager em;

    public List<AccountGroups> selectGroupsById(Integer id) {
        return em.createQuery("SELECT a FROM AccountGroups a WHERE a.id.accoundId = :accId", AccountGroups.class)
                .setParameter("accId", id)
                .getResultList();
    }

    public void insertUserAccess(Integer id) {
        AccountGroups accountGroups = new AccountGroups();
        AccountGroupsPK accountGroupsPK = new AccountGroupsPK();

        accountGroupsPK.setAccoundId(id);
        accountGroupsPK.setGroupName("User");

        accountGroups.setId(accountGroupsPK);

        em.persist(accountGroups);
    }

    public void insertBlockedAccess(Integer id) {
        AccountGroups accountGroups = new AccountGroups();
        AccountGroupsPK accountGroupsPK = new AccountGroupsPK();

        accountGroupsPK.setAccoundId(id);
        accountGroupsPK.setGroupName("Blocked");

        accountGroups.setId(accountGroupsPK);

        em.persist(accountGroups);
    }

    public void deleteUserAccess(Integer id) {
        AccountGroupsPK accountGroupsPK = new AccountGroupsPK();

        accountGroupsPK.setAccoundId(id);
        accountGroupsPK.setGroupName("User");

        AccountGroups accountGroups = em.find(AccountGroups.class, accountGroupsPK);
        em.remove(accountGroups);
    }

    public void deleteBlockedAccess(Integer id) {
        AccountGroupsPK accountGroupsPK = new AccountGroupsPK();

        accountGroupsPK.setAccoundId(id);
        accountGroupsPK.setGroupName("Blocked");

        AccountGroups accountGroups = em.find(AccountGroups.class, accountGroupsPK);
        em.remove(accountGroups);
    }
}
