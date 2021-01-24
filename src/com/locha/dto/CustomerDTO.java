package com.locha.dto;

import com.locha.entity.Orders;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.List;

public class CustomerDTO {
    private String cid;
    private String name;
    private String address;

    public CustomerDTO() {
    }

    public CustomerDTO(String cid, String name, String address) {
        this.cid = cid;
        this.name = name;
        this.address = address;
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
}
