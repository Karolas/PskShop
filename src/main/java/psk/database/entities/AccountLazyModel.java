package psk.database.entities;

import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;
import psk.database.dao.AccountDAO;

import javax.inject.Inject;
import java.lang.reflect.Field;
import java.util.*;

public class AccountLazyModel extends LazyDataModel<Account> {

    private List<Account> datasource;

    public AccountLazyModel(List<Account> datasource) {
        this.datasource = datasource;
    }
    @Inject
    AccountDAO accountDAO;
//    public AccountLazyModel() {
//        this.setRowCount(accountDAO.getAccountCount());
//    }
    @Override
    public List<Account> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String,Object> filters) {
        List<Account> data = new ArrayList<>();

        //filter
        for(Account account : datasource) {
            boolean match = true;

            if (filters != null) {
                for (Iterator<String> it = filters.keySet().iterator(); it.hasNext();) {
                    try {
                        String filterProperty = it.next();
                        Object filterValue = filters.get(filterProperty);
                        Field field =  account.getClass().getDeclaredField(filterProperty);
                        field.setAccessible(true);
                        String fieldValue = String.valueOf(field.get(account));

                        if(filterValue == null || fieldValue.startsWith(filterValue.toString())) {
                            match = true;
                        }
                        else {
                            match = false;
                            break;
                        }
                    } catch(Exception e) {
                        match = false;
                    }
                }
            }

            if(match) {
                data.add(account);
            }
        }

        //rowCount
        int dataSize = data.size();
        this.setRowCount(dataSize);

        //paginate
        if(dataSize > pageSize) {
            try {
                return data.subList(first, first + pageSize);
            }
            catch(IndexOutOfBoundsException e) {
                return data.subList(first, first + (dataSize % pageSize));
            }
        }
        else {
            return data;
        }
    }
//    @Override
//    public List<Account> load(int first, int pageSize, String sortField,
//                              SortOrder sortOrder, Map<String, Object> filters) {
//
//        List<Account> list = accountDAO.getAccountList(first, pageSize, filters);
//        if (filters != null && filters.size() > 0) {
//            //otherwise it will still show all page links; pages at end will be empty
//            this.setRowCount(accountDAO.getFilteredRowCount(filters));
//        }
//        return list;
//    }
}
