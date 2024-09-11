
package com.javaOrder.admin.product.domain;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
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
    private Category category;

    @Column(name = "p_order", nullable = false)
    private int productOrder;

    @Lob
    @Column(name = "p_ex")
    private String productExplain;

    @Temporal(TemporalType.DATE)
    @Column(name = "p_date")
    private Date productDate = new Date();

    @Column(name = "p_sell", length = 1, nullable = false, columnDefinition = "CHAR(1) DEFAULT 'Y'")
    private String productSell = "Y";

    @Column(name = "p_price", nullable = true)
    private Integer productPrice;

    @Column(name = "p_name", nullable = false, length = 50)
    private String productName;

    @Column(name = "p_image_path", length = 255)
    private String productImage;
}
