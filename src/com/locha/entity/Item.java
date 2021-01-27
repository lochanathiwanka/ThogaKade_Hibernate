package com.locha.entity;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Item implements SuperEntity {
    @Id
    private String code;
    private String description;
    private int qty;
    private double price;

    public Item() {
    }

    public Item(String code, String description, int qty, double price) {
        this.code = code;
        this.description = description;
        this.qty = qty;
        this.price = price;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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
