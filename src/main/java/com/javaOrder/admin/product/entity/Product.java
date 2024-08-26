package com.javaOrder.admin.product.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import java.util.Date;

@Entity
@Table(name = "Product")
public class Product {

    @Id
    @Column(name = "p_id", length = 20)
    private String productId;

    @Column(name = "cate_code", length = 50, nullable = false)
    private String categoryCode;

    @Column(name = "p_order", nullable = false)
    private int productOrder;

    @Column(name = "p_ex", length = 500)
    private String productExplain;

    @Temporal(TemporalType.DATE)
    @Column(name = "p_date", nullable = false)
    private Date productDate;

    @Column(name = "p_sell", length = 10)
    private String productSell;

    @Column(name = "p_price")
    private Integer productPrice;

    @Column(name = "p_name", length = 50, nullable = false)
    private String productName;

    // Default constructor
    public Product() {
    }

    // Parameterized constructor
    public Product(String productId, String categoryCode, int productOrder, String productExplain,
                   Date productDate, String productSell, Integer productPrice, String productName) {
        this.productId = productId;
        this.categoryCode = categoryCode;
        this.productOrder = productOrder;
        this.productExplain = productExplain;
        this.productDate = productDate;
        this.productSell = productSell;
        this.productPrice = productPrice;
        this.productName = productName;
    }

    // Getters and Setters
    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getCategoryCode() {
        return categoryCode;
    }

    public void setCategoryCode(String categoryCode) {
        this.categoryCode = categoryCode;
    }

    public int getProductOrder() {
        return productOrder;
    }

    public void setProductOrder(int productOrder) {
        this.productOrder = productOrder;
    }

    public String getProductExplain() {
        return productExplain;
    }

    public void setProductExplain(String productExplain) {
        this.productExplain = productExplain;
    }

    public Date getProductDate() {
        return productDate;
    }

    public void setProductDate(Date productDate) {
        this.productDate = productDate;
    }

    public String getProductSell() {
        return productSell;
    }

    public void setProductSell(String productSell) {
        this.productSell = productSell;
    }

    // Null 체크를 포함한 getProductPrice 메서드
    public int getProductPrice() {
        return productPrice != null ? productPrice : 0;  // Null이면 기본값 0을 반환
    }

    public void setProductPrice(Integer productPrice) {
        if (productPrice == null) {
            this.productPrice = 0;  // 기본값으로 0을 설정
        } else {
            this.productPrice = productPrice;
        }
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }
}
