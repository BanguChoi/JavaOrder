package com.javaOrder.common.kakaopay.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.javaOrder.common.kakaopay.domain.KakaoPayReadyResponse;
import com.javaOrder.common.kakaopay.domain.PayApproveRequest;
import com.javaOrder.common.kakaopay.domain.PayReadyRequestVO;
import com.javaOrder.common.kakaopay.service.KakaoPayService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class KakaoPayController {
	
	private final KakaoPayService payService;

	// 결제 준비
	// agent : [moblie/pc] , openType : [layer/popup]
	@GetMapping("/pay/ready/")
    public String ready(PayReadyRequestVO readyRequest, Model model) {
		// Request param
		readyRequest = PayReadyRequestVO.builder()
				.partnerOrderId("10003")	// 주문번호
				.partnerUserId("M043")		// 회원번호
				.itemName("마스카포네 크림 소라빵")		// 상품명
				.quantity(1)			// 수량
				.totalAmount(7000)		// 총금액
				.taxFreeAmount(0)
				.vatAmount(0)
				.build();
				
        KakaoPayReadyResponse readyResponse = payService.ready(readyRequest);
        /* 모바일
        if (agent.equals("mobile")) {
            return "redirect:" + readyResponse.getNext_redirect_mobile_url();
        }*/

        // PC
        model.addAttribute("response", readyResponse);
        return "/pay/ready";
    }
	
	
	// 결제 승인
	@GetMapping("/pay/approve/")
    public String approve(@RequestParam("pg_token") String pgToken, Model model) {
		PayApproveRequest payApproveRequest = PayApproveRequest.builder()
				.partnerOrderId("10003")
				.partnerUserId("M043")
				.build();
        String approveResponse = payService.approve(pgToken, payApproveRequest);
		
		ObjectMapper objectMapper = new ObjectMapper(); 

		try { 
			// JSON 문자열을 JsonNode로 변환
			JsonNode jsonNode = objectMapper.readTree(approveResponse);
			// "회원번호 추출"
			String mCode = jsonNode.get("partner_user_id").asText();
			// 장바구니 항목 정보 주문내역으로 복사/붙여넣기
			payService.copyCartToOrder(mCode);
		}catch (Exception e) { 
			e.printStackTrace(); 
		}
        
        //model.addAttribute("response", response);
        model.addAttribute("response", approveResponse);
        return "/pay/approve";
    }        
    
	
	
	
	// 주문 취소
    @GetMapping("/pay/cancel/")
    public String cancel() {
        // 주문건이 진짜 취소되었는지 확인 후 취소 처리
        // 결제내역조회(/v1/payment/status) api에서 status를 확인한다.
        // To prevent the unwanted request cancellation caused by attack,
        // the “show payment status” API is called and then check if the status is QUIT_PAYMENT before suspending the payment
        return "/pay/cancel";
    }

    // 주문 실패
    @GetMapping("/pay/fail/")
    public String fail() {
        // 주문건이 진짜 실패되었는지 확인 후 실패 처리
        // 결제내역조회(/v1/payment/status) api에서 status를 확인한다.
        // To prevent the unwanted request cancellation caused by attack,
        // the “show payment status” API is called and then check if the status is FAIL_PAYMENT before suspending the payment
        return"/pay/fail";
    }
}