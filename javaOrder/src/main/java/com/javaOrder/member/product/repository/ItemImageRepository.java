package com.javaOrder.member.product.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.javaOrder.member.product.domain.ItemImage;

public interface ItemImageRepository extends JpaRepository<ItemImage, String> {
	
}
