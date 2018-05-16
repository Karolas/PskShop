package psk;

import org.primefaces.model.SortOrder;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.Map;

import static org.primefaces.component.datatable.DataTable.PropertyKeys.sortField;

@ApplicationScoped
public class Utils {
    public <T> Predicate getFilterCondition(CriteriaBuilder cb, Root<T> myObj, Map<String, Object> filters) {
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

    public <T> CriteriaQuery<T> getSortOrder(CriteriaQuery<T> cq, CriteriaBuilder cb, Root<T> myObj, SortOrder sortOrder, String sortField) {
        if (sortField != null) {
            if (sortOrder == SortOrder.ASCENDING) {
                cq.orderBy(cb.asc(myObj.get(sortField)));
            } else if (sortOrder == SortOrder.DESCENDING) {
                cq.orderBy(cb.desc(myObj.get(sortField)));
            }
        }
        return cq;
    }
}