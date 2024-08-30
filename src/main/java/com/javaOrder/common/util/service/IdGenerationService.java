package com.javaOrder.common.util.service;

import org.springframework.stereotype.Service;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@Service
public class IdGenerationService {
	@PersistenceContext
    private EntityManager entityManager;

    public String generateId(String prefix, String sequenceName, int digit) {
        String sql = "SELECT " + sequenceName + ".NEXTVAL FROM DUAL";
        Number sequenceValue = (Number) entityManager.createNativeQuery(sql).getSingleResult();
        
        String format = "%0" + digit + "d";
        return prefix + String.format(format, sequenceValue.longValue());
    }
}