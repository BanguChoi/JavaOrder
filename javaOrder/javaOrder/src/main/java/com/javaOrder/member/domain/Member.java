package com.javaOrder.member.domain;

import java.time.LocalDate;
import java.time.LocalDateTime;

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
	private String membercode;
	
	@Column(name = "m_name")
	private String membername;
	
	@Column(name = "m_id")
	private String memberid;
	
	@Column(name = "m_pw")
	private String memberpasswd;
	
	@Column(name = "m_email")
	private String memberemail;
	
	@Column(name = "m_phone")
	private String memberphone;
	
	//@Lob
	@Column(name = "m_addr")
	private String memberaddress;
	
	@Column(name = "m_birth")
	private LocalDate memberbirth;
	
	@Column(name = "m_last")
	private LocalDateTime memberlast;
	
	@Column(name = "m_date")
	private LocalDate memberdate;
	
	@Column(name = "m_status")
	private String memberstatus;
}