package com.javaOrder.common.orders.domain;

import org.hibernate.annotations.ColumnDefault;

import com.javaOrder.admin.product.domain.Product;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

//@SequenceGenerator(
//name="orderItem_generator",
//sequenceName="order_item_seq",
//initialValue = 1,
//allocationSize = 1)

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Table(name="order_item")
public class OrderItem {
	@Id
	@Column(name="oi_id")
	private String orderitemId;	// 주문항목ID
	
	@ManyToOne
	@JoinColumn(name="ord_num")
	private Orders orderNumber;	// 주문번호
	
	@ManyToOne
	@JoinColumn(name="p_id")
	private Product productId;	// 상품번호
	
	@Column(name="oi_num", nullable=false)
	@ColumnDefault("0")
	private Integer orderitemNumber;		// 수량
	
	@Column(name="oi_price", nullable=false)
	private Integer orderitemPrice;	// 항목 단가
	
	@Column(name="oi_shot", nullable=false)
	private Integer orderitemShot;		// 샷여부
	
	@Column(name="oi_size", nullable=false)
	private Integer orderitemSize;		// 사이즈
	
	@Column(name="oi_temp", nullable=false)
	private Integer orderitemTemp;		// 냉온여부
	
	@Column(name="oi_syrup", nullable=true)
	private Integer orderitemSyrup;	// 시럽
	
	
//	@Column(name="oi_takeout", nullable=false)
//	@ColumnDefault("0")
//	private Integer orderitemTakeout;	// 수령방식
	
//	@Column(name="oi_exfee", nullable=false)
//	@ColumnDefault("0")
//	private Integer orderitemExfee;	// 추가비용
	
	
//	public void preOrderId() {
//		this.orderitemId = orderNumber.getOrderNumber() + this.orderItemSeq
//	}
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
//	    oi_exfee NUMBER DEFAULT 0 NOT NULL,
	    CONSTRAINT pk_order_item PRIMARY KEY (oi_id),
	    CONSTRAINT fk_order_item_orders FOREIGN KEY (ord_num) REFERENCES Orders(ord_num),
	    CONSTRAINT fk_order_item_product FOREIGN KEY (p_id) REFERENCES Product(p_id)
	);
 *****************************************************************************************/