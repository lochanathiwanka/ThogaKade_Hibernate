package com.locha.entity;

import javax.persistence.*;
import java.util.List;

@Entity
public class Customer {
    @Id
    private String cid;
    private String name;
    private String address;
    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL)
    private List<Orders> ordersList;

    public Customer() {
    }

    public Customer(String cid, String name, String address, List<Orders> ordersList) {
        this.cid = cid;
        this.name = name;
        this.address = address;
        this.ordersList = ordersList;
    }

    public String getCid() {
        return cid;
    }

    public void setCid(String cid) {
        this.cid = cid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public List<Orders> getOrdersList() {
        return ordersList;
    }

    public void setOrdersList(List<Orders> ordersList) {
        this.ordersList = ordersList;
    }
}
