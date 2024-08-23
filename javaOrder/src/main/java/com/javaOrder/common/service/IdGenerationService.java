package com.javaOrder.common.service;

import org.springframework.stereotype.Service;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@Service
public class IdGenerationService {
	@PersistenceContext
    private EntityManager entityManager;

    public String generateId(String prefix, String sequenceName) {
        String sql = "SELECT " + sequenceName + ".NEXTVAL FROM DUAL";
        Number sequenceValue = (Number) entityManager.createNativeQuery(sql).getSingleResult();
        return prefix + String.format("%04d", sequenceValue.longValue());
    }
}