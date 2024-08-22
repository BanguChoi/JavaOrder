package com.javaOrder.client.orders.domain;

import java.time.LocalDateTime;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Id;



public class Orders {
	@Id
	private Long ordNum;
	
	
	private String mCode;
	
	@CreationTimestamp
	private LocalDateTime ordDate;
	
	@Column(nullable=false)
	private int ordPrice;
	@Column(nullable=false)
	@ColumnDefault(value="0")
	private String ordStatus;
}
