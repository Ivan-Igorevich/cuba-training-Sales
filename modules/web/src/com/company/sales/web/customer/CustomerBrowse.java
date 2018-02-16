package com.company.sales.web.customer;

import com.company.sales.entity.Customer;
import com.haulmont.cuba.core.entity.KeyValueEntity;
import com.haulmont.cuba.core.global.DataManager;
import com.haulmont.cuba.core.global.ValueLoadContext;
import com.haulmont.cuba.gui.components.AbstractLookup;
import com.haulmont.cuba.gui.components.GroupTable;

import javax.inject.Inject;
import java.math.BigDecimal;
import java.util.List;

public class CustomerBrowse extends AbstractLookup {
    @Inject private DataManager dataManager;
    @Inject private GroupTable<Customer> customersTable;

    public void btnCountClick() {
        if (customersTable.getSelected() == null)
            return;
        Customer current = (Customer) customersTable.getDatasource().getItem();
        if (current == null)
            return;
        ValueLoadContext context = ValueLoadContext.create()
                .setQuery(ValueLoadContext.createQuery("select sum(o.amount) from sales$Order o where o.customer.id = :cid")
                    .setParameter("cid", current.getId()))
                .addProperty("sum");
        List<KeyValueEntity> result = dataManager.loadValues(context);
        BigDecimal value = result.get(0).getValue("sum");
        showNotification(value != null ? value.toString() : BigDecimal.ZERO.toString());

//        LoadContext<Order> ctx = LoadContext.create(Order.class)
//                .setQuery(LoadContext.createQuery("select o from sales$Order o where o.customer.id = :cid")
//                    .setParameter("cid", current.getId()));
//        List<Order> orders = dataManager.loadList(ctx);
//        int result = 0;
//        for (Order o : orders)
//            result += o.getAmount().intValue();
//        showMessageDialog("sum", Integer.toString(result), MessageType.CONFIRMATION);
    }
}