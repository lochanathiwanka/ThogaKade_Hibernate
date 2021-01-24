package com.locha.dto;
import com.locha.entity.Customer;
import com.locha.entity.OrderDetail;

import javax.persistence.*;
import java.util.List;

public class OrdersDTO {
    private String oid;
    private String date;
    private String cid;

    public OrdersDTO() {
    }

    public OrdersDTO(String oid, String date, String cid) {
        this.oid = oid;
        this.date = date;
        this.cid = cid;
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

    public String getCid() {
        return cid;
    }

    public void setCid(String cid) {
        this.cid = cid;
    }
}
