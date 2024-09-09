package com.javaOrder.admin.notices.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.javaOrder.admin.notices.domain.Notices;

public interface NoticesRepository extends JpaRepository<Notices,Long> {
	// 가장 최근 게시물 조회
    @Query("SELECT n FROM Notices n ORDER BY n.regDate DESC")
    Optional<Notices> findLatestNotice();
}
