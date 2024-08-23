package com.javaOrder.member.domain;

import java.time.LocalDateTime;

import org.hibernate.annotations.ColumnDefault;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@EnableJpaAuditing	// @LastModifiedDate, @CreatedDate 에 필요
@Table(name="member")
public class Member {
	@Id
	@Column(length = 10)
	private String mCode;
	
	@Column(columnDefinition="NVARCHAR2(5)", nullable=false)
	private String mName;
	
	@Column(length = 20, nullable=false)
	private String mId;
	
	@Column(length = 20, nullable=false)
	private String mPw;
	
	@Column(length = 50, nullable=false)
	private String mEmail;
	
	@Column(length = 20, nullable=false)
	private String mPhone;
	
//	@Lob
	@Column(nullable=false)
	private String mAddr;
	
	@Column(nullable=false)
	private LocalDateTime mBirth;
	
	@LastModifiedDate
	@Column(nullable=false)
	private LocalDateTime mLast;
	
	@CreatedDate
	@Column(nullable=false, updatable=false)
	private LocalDateTime mDate;
	
	@Column(columnDefinition = "CHAR(1) DEFAULT 'M'", nullable=false)
	@ColumnDefault("M")
	private String mStatus;
}
