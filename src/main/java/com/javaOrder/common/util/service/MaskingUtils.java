package com.javaOrder.common.util.service;

public class MaskingUtils {

	/**
	 * 
	 * @param value		문자열
	 * @param keepFront	유지 할 앞 부분 길이
	 * @param maskChar	마스킹에 사용할 문자
	 * @return
	 */
	
	private static String maskString(String value, int keepFront, char maskChar) {
        if (value == null || value.length() <= keepFront) return value;
        return value.substring(0, keepFront) + 
        		String.valueOf(maskChar).repeat(value.length() - keepFront);
    }
	
	// 이름 앞에 1자리 남기고 마스킹 처리
	public static String maskName(String name) {
		if(name == null || name.length() < 2) return name;
		return name.charAt(0) + "*".repeat(name.length() - 1); 
	}
	
	// 아이디 4자리 제외 마스킹 처리 
	public static String maskId(String id) {
		if(id == null || id.length() <= 4) return id;
		return id.substring(0, 4) + "*".repeat(id.length() - 4);
	}
	
	// 이메일 앞 3자리 제외 마스킹 처리
	// @뒤에는 마스킹 처리 제외
	public static String maskEmail(String email) {
        if (email == null || !email.contains("@")) return email;
        int atIndex = email.indexOf('@');
        return maskString(email.substring(0, atIndex), 3, '*') + email.substring(atIndex);
    }
	
	// 전화번호 010 같은 부분을 제외하고 마스킹 처리
	public static String maskPhone(String phone) {
		if(phone == null || phone.length() <= 7) return phone;
		String[] parts = phone.split("-");
		if(parts.length == 3) {
			return parts[0] + "-****-****";
		}
		return phone;
	}
	
}
