package com.javaOrder.product.DTO;

import java.util.Date;

public class ProductDTO {
    private String p_id;
    private String cate_code;
    private int p_order;
    private String p_ex;
    private Date p_date;
    private String p_sell;
    private double p_price;
    private String p_name;

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
