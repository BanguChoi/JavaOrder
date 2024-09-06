package com.javaorder.common.admin.promotion.reposirory;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.javaorder.common.admin.promotion.domain.Promotion;


public interface PromotionRepository extends JpaRepository<Promotion, Long> {
	
//검색용인데 나중에...
	Promotion findByPromTitle(String promTitle);
	List<Promotion> findByPromTitleContaining(String promTitle);
	List<Promotion> findByPromContentContaining(String promContent);
	List<Promotion> findByRegDateBetween(LocalDateTime startDate, LocalDateTime endDate);
	
//	List<Promotion> findByOrderByNoDesc(); //얘 뭐야 왜 얘 있으면 에러생겨??
	
}
