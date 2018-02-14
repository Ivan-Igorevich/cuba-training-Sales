package com.company.sales.entity;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

import com.haulmont.chile.core.annotations.MetaProperty;
import com.haulmont.cuba.core.entity.StandardEntity;
import com.haulmont.chile.core.annotations.NamePattern;
import java.util.List;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

@NamePattern("%s|customer")
@Table(name = "SALES_ORDER")
@Entity(name = "sales$Order")
public class Order extends StandardEntity {
    private static final long serialVersionUID = 1439950398839233456L;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CUSTOMER_ID")
    protected Customer customer;

    @Temporal(TemporalType.DATE)
    @NotNull
    @Column(name = "DATE_", nullable = false)
    protected Date date;

    @Column(name = "AMOUNT")
    protected BigDecimal amount;

    @JoinTable(name = "SALES_ORDER_ITEM_LINK",
        joinColumns = @JoinColumn(name = "ORDER_ID"),
        inverseJoinColumns = @JoinColumn(name = "ITEM_ID"))
    @ManyToMany
    protected List<Item> items;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "SHOP_ID")
    protected Shop shop;

    public void setItems(List<Item> items) {
        this.items = items;
    }

    public List<Item> getItems() {
        return items;
    }

    public void setShop(Shop shop) {
        this.shop = shop;
    }

    public Shop getShop() {
        return shop;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Date getDate() {
        return date;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public BigDecimal getAmount() {
        return amount;
    }

}