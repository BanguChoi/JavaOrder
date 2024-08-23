package com.javaOrder.common.orders.orderitem.domain;

import org.hibernate.annotations.ColumnDefault;

import com.javaOrder.common.orders.domain.Orders;
import com.javaOrder.common.product.domain.Product;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Entity
@Table(name="orderitem")
public class OrderItem {
	@Id
	@Column(name="oi_id")
	private String oiId;	// 주문항목ID
	
	@ManyToOne
	@JoinColumn(name="ord_num")
	private Orders ordNum;	// 주문번호
	
	@OneToOne
	@JoinColumn(name="p_id")
	private Product pId;	// 상품번호
	
	@Column(name="oi_num", nullable=false)
	@ColumnDefault("0")
	private int oiNum;		// 수량
	
	@Column(name="oi_price", nullable=false)
	private int oiPrice;	// 항목 단가
	
	@Column(name="oi_shot", nullable=false)
	private int oiShot;		// 샷여부
	
	@Column(name="oi_size", nullable=false)
	private int oiSize;		// 사이즈
	
	@Column(name="oi_temp", nullable=false)
	private int oiTemp;		// 냉온여부
	
	@Column(name="oi_syrup", nullable=true)
	private int oiSyrup;	// 시럽
	
	@Column(name="oi_takeout", nullable=false)
	private int oiTakeout;	// 수령방식
	
	@Column(name="oi_exfee", nullable=false)
	@ColumnDefault("0")
	private int oiExfee;	// 추가비용
}


/****************************************************************************************
 * [DDL 쿼리문]
 * CREATE TABLE ORDER_ITEM (
	    oi_id VARCHAR2(20) NOT NULL,
	    ord_num NUMBER NOT NULL,
	    p_id VARCHAR2(20) NOT NULL,
	    oi_quantity NUMBER DEFAULT 0 NOT NULL,
	    oi_unit_price NUMBER NOT NULL,
	    oi_shot NUMBER NOT NULL,
	    oi_size NUMBER NOT NULL,
	    oi_temp NUMBER NOT NULL,
	    oi_syrup NUMBER,
	    oi_takeout NUMBER NOT NULL,
	    oi_exfee NUMBER DEFAULT 0 NOT NULL,
	    CONSTRAINT pk_order_item PRIMARY KEY (oi_id),
	    CONSTRAINT fk_order_item_orders FOREIGN KEY (ord_num) REFERENCES Orders(ord_num),
	    CONSTRAINT fk_order_item_product FOREIGN KEY (p_id) REFERENCES Product(p_id)
	);
 *****************************************************************************************/