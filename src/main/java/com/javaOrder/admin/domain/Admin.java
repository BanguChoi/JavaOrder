package com.javaOrder.admin.domain;

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
@Table(name = "Admin")
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Setter
@Getter
//@Data
public class Admin {
	@Id
	@Column(name = "a_code")
	private String adminCode;
	
	@Column(name = "a_name")
	private String adminName;
	
	@Column(name = "a_id")
	private String adminId;
	
	@Column(name = "a_pw")
	private String adminPasswd;
	
	@Column(name = "a_email")
	private String adminEmail;
	
	@Column(name = "a_phone")
	private String adminPhone;
	
	@Column(name = "a_date")
	private LocalDateTime adminDate;
}
