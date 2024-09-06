package com.javaOrder.admin.reply.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.javaOrder.admin.reply.domain.Reply;

public interface ReplyRepository extends JpaRepository<Reply, Long> {

	/* JPQL 적용
	 * JPA에서 사용하는 객체지향 쿼리 언어
	 * DB SQL 쿼리 언어와 유사하지만 테이블과 컬럼 이름 대신 매핑한 엔티티 이름과 속성 이름을 사용한다.
	 * 기본 형식 : SELECT 별칭 FROM 엔티티 이름 AS 별칭
	 * */
	@Query("SELECT r FROM Reply r WHERE r.board.boardNo = ?1")//boardNo 대소문자 구분 확인 필요
	List<Reply> findByBoardNo(Long boardNo);
	List<Reply> findByReplyName(String replyName);
}
