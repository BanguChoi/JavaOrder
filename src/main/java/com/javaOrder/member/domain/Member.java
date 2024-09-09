package com.javaOrder.member.domain;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "Member")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
//@Data
public class Member {
	
	@Id
	@Column(name = "m_code")
	private String memberCode;
	
	@Column(name = "m_name")
	private String memberName;
	
	@Column(name = "m_id")
	private String memberId;
	
	@Column(name = "m_pw")
	private String memberPasswd;
	
	@Column(name = "m_email")
	private String memberEmail;
	
	@Column(name = "m_phone")
	private String memberPhone;
	
	//@Lob
	@Column(name = "m_addr")
	private String memberAddress;
	
	@Column(name = "m_birth")
	private LocalDate memberBirth;
	
	@Column(name = "m_last")
	private LocalDate memberLast;
	
	@Column(name = "m_date")
	private LocalDate memberDate;
	
	@Column(name = "m_status")
	private String memberStatus;
}