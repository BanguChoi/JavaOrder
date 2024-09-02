package com.javaOrder.product.entity;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

@Entity
@Table(name = "Product")
public class Product {

    @Id
    @Column(name = "p_id", length = 20)
    private String p_id;

    @Column(name = "cate_code", length = 50, nullable = false)
    private String cate_code;

    @Column(name = "p_order", nullable = false)
    private int p_order;

    @Column(name = "p_ex", length = 100)
    private String p_ex;

    @Temporal(TemporalType.DATE)
    @Column(name = "p_date", nullable = false)
    private Date p_date;

    @Column(name = "p_sell", length = 10)
    private String p_sell;

    @Column(name = "p_price")
    private double p_price;

    @Column(name = "p_name", length = 50, nullable = false)
    private String p_name;

    // Default constructor
    public Product() {
    }

    // Parameterized constructor
    public Product(String p_id, String cate_code, int p_order, String p_ex, Date p_date, String p_sell, double p_price, String p_name) {
        this.p_id = p_id;
        this.cate_code = cate_code;
        this.p_order = p_order;
        this.p_ex = p_ex;
        this.p_date = p_date;
        this.p_sell = p_sell;
        this.p_price = p_price;
        this.p_name = p_name;
    }

    // Getters and Setters
    public String getP_id() {
        return p_id;
    }

    public void setP_id(String p_id) {
        this.p_id = p_id;
    }

    public String getCate_code() {
        return cate_code;
    }

    public void setCate_code(String cate_code) {
        this.cate_code = cate_code;
    }

    public int getP_order() {
        return p_order;
    }

    public void setP_order(int p_order) {
        this.p_order = p_order;
    }

    public String getP_ex() {
        return p_ex;
    }

    public void setP_ex(String p_ex) {
        this.p_ex = p_ex;
    }

    public Date getP_date() {
        return p_date;
    }

    public void setP_date(Date p_date) {
        this.p_date = p_date;
    }

    public String getP_sell() {
        return p_sell;
    }

    public void setP_sell(String p_sell) {
        this.p_sell = p_sell;
    }

    public double getP_price() {
        return p_price;
    }

    public void setP_price(double p_price) {
        this.p_price = p_price;
    }

    public String getP_name() {
        return p_name;
    }

    public void setP_name(String p_name) {
        this.p_name = p_name;
    }
}
