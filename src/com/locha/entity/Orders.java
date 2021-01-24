package com.locha.entity;
import javax.persistence.*;
import java.util.List;

@Entity
public class Orders {
    @Id
    private String oid;
    private String date;
    @ManyToOne
    @JoinColumn(name = "cid")
    private Customer customer;

    @OneToMany(mappedBy = "orders", cascade = CascadeType.ALL)
    private List<OrderDetail> orderDetailList;

    public Orders() {
    }

    public Orders(String oid, String date, Customer customer, List<OrderDetail> orderDetailList) {
        this.oid = oid;
        this.date = date;
        this.customer = customer;
        this.orderDetailList = orderDetailList;
    }

    public String getOid() {
        return oid;
    }

    public void setOid(String oid) {
        this.oid = oid;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public List<OrderDetail> getOrderDetailList() {
        return orderDetailList;
    }

    public void setOrderDetailList(List<OrderDetail> orderDetailList) {
        this.orderDetailList = orderDetailList;
    }
}
