package com.javaOrder.admin.domain;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Product {

    @Id
    @Column(name = "p_id", length = 10)
    private String productId;

    @ManyToOne
    @JoinColumn(name = "cate_code", nullable = false)
    private Category category;  // 카테고리 코드와의 관계

    @Column(name = "p_order", nullable = false)
    private int productOrder;

    @Column(name = "p_ex", length = 500)
    private String productExplain;

    @Temporal(TemporalType.DATE)
    @Column(name = "p_date")
    private Date productDate = new Date();

    @Column(name = "p_sell", length = 10)
    private String productSell;

    @Column(name = "p_price", nullable = true)  // 가격 필드를 nullable로 설정
    private Integer productPrice;

    @Column(name = "p_name", nullable = false, length = 50)
    private String productName;

    @Column(name = "p_image_path", length = 255)
    private String productImage;
}
