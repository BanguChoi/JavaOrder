package com.javaOrder.member.vo;

import java.time.LocalDate;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MemberDTO {
	private String memberCode;
	private String memberName;
    private String memberId;
    private String memberPasswd;
    private String memberEmail;
    private String memberPhone;
    private String memberAddress;
    private LocalDate memberBirth;
    private LocalDate memberDate;
    private String memberStatus;
}
