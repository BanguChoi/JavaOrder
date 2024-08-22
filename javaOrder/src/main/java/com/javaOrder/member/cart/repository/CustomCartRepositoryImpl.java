package com.javaOrder.member.cart.repository;

import com.javaOrder.member.cart.vo.Cart;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

public class CustomCartRepositoryImpl implements CustomCartRepository {
	
	@PersistenceContext
	private EntityManager entityManager;
	
	@Override
	public Cart saveWithFeneratedId(Cart cart) {
		// TODO Auto-generated method stub
		return null;
	}

}
