package com.javaOrder.member.product.domain;

import java.sql.Clob;
import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;

@Entity
@SequenceGenerator(
		name = "itemImage_generator",
		sequenceName = "img_id_seq",
		initialValue = 1000,
		allocationSize = 1)
public class ItemImage {
	
	@Id
	@GeneratedValue(
			strategy = GenerationType.SEQUENCE,
			generator = "itemImage_generator")
	private String imageId;
	
	@ManyToOne
	@JoinColumn(name = "p_id")
	private Product product;
	
	@Column(name = "img_fileName", nullable = false)
	private Clob imageFileName;
	
	@Column(name = "img_regdate", nullable = false)
	private Date ImageRegDate;
	
}
