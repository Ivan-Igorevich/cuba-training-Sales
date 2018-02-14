package com.company.sales.web.order;

import com.company.sales.entity.Order;
import com.haulmont.cuba.gui.components.*;

import javax.inject.Inject;
import java.util.Map;

public class OrderBrowse extends AbstractLookup {
    @Inject
    private GroupTable<Order> ordersTable;

    @Override
    public void init(Map<String, Object> params) {
        super.init(params);
        // при создании программно сгенерированной колонки используем этот интерфейс,
        // чтобы колонка была видна как в выгрузке экселя, так и в модуле отчётов
        ordersTable.addGeneratedColumn("itemsCount", new Table.PrintableColumnGenerator<Order, String>() {
            @Override
            public String getValue(Order item) {
                return Integer.toString(item.getItems().size());
            }

            @Override
            public Component generateCell(Order entity) {
                return new Table.PlainTextCell(Integer.toString(entity.getItems().size()));
            }
        });
        ordersTable.getColumn("itemsCount").setCaption(getMessage("itemsCount"));
    }
}