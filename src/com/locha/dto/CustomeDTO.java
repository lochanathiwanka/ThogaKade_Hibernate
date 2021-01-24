package com.locha.dto;

public class CustomeDTO {
    private String code;
    private int qty;
    private double totalPrice;

    public CustomeDTO() {
    }

    public CustomeDTO(String code, int qty, double totalPrice) {
        this.code = code;
        this.qty = qty;
        this.totalPrice = totalPrice;
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

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }
}
