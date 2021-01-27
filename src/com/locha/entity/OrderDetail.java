package com.locha.entity;

import javax.persistence.*;

@Entity
public class OrderDetail implements SuperEntity {
    @EmbeddedId
    private CompositeKey compositeKey = new CompositeKey();

    @ManyToOne
    @JoinColumn(name = "oid", referencedColumnName = "oid", insertable = false, updatable = false)
    private Orders orders;

    @ManyToOne
    @JoinColumn(name = "code", referencedColumnName = "code", insertable = false, updatable = false)
    private Item item;

    private int qty;
    private double price;

    public OrderDetail() {
    }

    public OrderDetail(String oid, String code, int qty, double price) {
        this.compositeKey = new CompositeKey(oid, code);
        this.qty = qty;
        this.price = price;
    }

    public OrderDetail(CompositeKey compositeKey, Orders orders, Item item, int qty, double price) {
        this.compositeKey = compositeKey;
        this.orders = orders;
        this.item = item;
        this.qty = qty;
        this.price = price;
    }

    public CompositeKey getCompositeKey() {
        return compositeKey;
    }

    public void setCompositeKey(CompositeKey compositeKey) {
        this.compositeKey = compositeKey;
    }

    public Orders getOrders() {
        return orders;
    }

    public void setOrders(Orders orders) {
        this.orders = orders;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
