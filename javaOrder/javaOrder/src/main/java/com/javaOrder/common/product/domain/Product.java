package com.javaOrder.common.product.domain;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
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
    private int productPrice;

    @Column(name = "p_name", length = 50, nullable = false)
    private String productName;
}
