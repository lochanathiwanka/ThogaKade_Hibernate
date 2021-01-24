package com.locha.dto;

import com.locha.entity.CompositeKey;
import com.locha.entity.Item;
import com.locha.entity.Orders;

import javax.persistence.*;

public class OrderDetailDTO {
    private String oid;
    private String code;
    private int qty;
    private double price;

    public OrderDetailDTO() {
    }

    public OrderDetailDTO(String oid, String code, int qty, double price) {
        this.oid = oid;
        this.code = code;
        this.qty = qty;
        this.price = price;
    }

    public String getOid() {
        return oid;
    }

    public void setOid(String oid) {
        this.oid = oid;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
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
