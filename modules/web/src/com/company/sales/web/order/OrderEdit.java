package com.company.sales.web.order;

import com.company.sales.entity.Item;
import com.company.sales.entity.Shop;
import com.company.sales.service.AmountService;
import com.google.common.collect.ImmutableMap;
import com.haulmont.cuba.gui.WindowManager;
import com.haulmont.cuba.gui.components.AbstractEditor;
import com.company.sales.entity.Order;
import com.haulmont.cuba.gui.components.TextField;
import com.haulmont.cuba.gui.data.CollectionDatasource;
import com.haulmont.cuba.gui.data.Datasource;

import javax.inject.Inject;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class OrderEdit extends AbstractEditor<Order> {
    @Inject
    private CollectionDatasource<Item, UUID> itemsDs;
    @Inject
    private Datasource<Order> orderDs;
    @Inject
    private AmountService amountService;

    @Override
    public void init(Map<String, Object> params) {
        super.init(params);
//        orderDs.addItemPropertyChangeListener(new Datasource.ItemPropertyChangeListener<Order>() {
//            @Override
//            public void itemPropertyChanged(Datasource.ItemPropertyChangeEvent<Order> e) {
//                if (!"items".equals(e.getProperty())) return;
//                if (e.getItem().getItems() == null) return;
////         если слушаем общий датасорс - в сервис передаём не заказ, а вложенный датасорс товаров
//                getItem().setAmount(amountService.countAmount(e.getItem()));
//            }
//        });
        itemsDs.addCollectionChangeListener(new CollectionDatasource.CollectionChangeListener<Item, UUID>() {
            @Override
            public void collectionChanged(CollectionDatasource.CollectionChangeEvent<Item, UUID> e) {
                if (getItem().getItems() == null) return;
                getItem().setAmount(amountService.countAmount(getItem()));
            }
        });
    }

    public void onBtnAddClick() {
        if (getItem().getShop() == null) {
            showNotification(getMessage("pickShop"));
            return;
        }

        openLookup("sales$Item.browse", items -> {
                for (Object i : items) {
                    Item curr = (Item) i;
                    itemsDs.addItem(curr);
                }
            }, WindowManager.OpenType.DIALOG, ImmutableMap.of("store", getItem().getShop())
        );
    }
}