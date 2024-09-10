package com.javaOrder.admin.promotion.repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.javaOrder.admin.promotion.domain.Promotion;


public interface PromotionRepository extends JpaRepository<Promotion, Long> {
	
//검색용인데 나중에...
	Promotion findByPromotionTitle(String promotionTitle);
	List<Promotion> findByPromotionTitleContaining(String promotionTitle);
//	List<Promotion> findByPromotionContentContaining(String promotionContent);
	List<Promotion> findByRegDateBetween(LocalDateTime startDate, LocalDateTime endDate);
	
	// 제목에 포함되는 검색어 조회
	Page<Promotion> findByPromotionTitleContaining(String keyword, Pageable pageable);
	// 진행상태 조회
	Page<Promotion> findByPromotionStatus(String status, Pageable pageable);
	// 지정한 기간 중 진행하는 프로모션 조회
	Page<Promotion> findByPromotionStartDateLessThanEqualAndPromotionEndDateGreaterThanEqual(LocalDate startDate,
			LocalDate endDate, Pageable pageable);
	
	@Query("SELECT p FROM Promotion p WHERE p.promotionStatus = 'P'")
    List<Promotion> findOngoingPromotions();
//	List<Promotion> findByOrderByNoDesc(); //얘 뭐야 왜 얘 있으면 에러생겨??
	
}
