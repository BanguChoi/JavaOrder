package com.javaOrder.kakaopay.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import com.javaOrder.kakaopay.model.KakaoPayReadyResponseVO;
import com.javaOrder.kakaopay.service.KakaoPayService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class KakaoPayController {
	
	private final KakaoPayService payService;
	
	// 결제 준비
	@GetMapping("/ready/{agent}/{openType}")
    public String ready(@PathVariable String agent, @PathVariable String openType, Model model) {
        KakaoPayReadyResponseVO readyResponse = payService.ready(agent, openType);
        // 모바일
        if (agent.equals("mobile")) {
            return "redirect:" + readyResponse.getNext_redirect_mobile_url();
        }

        // PC
        model.addAttribute("response", readyResponse);
        return agent + "/" + openType + "/ready";
    }
	
	
	// 결제 승인
	@GetMapping("/approve/{agent}/{openType}")
    public String approve(@PathVariable String agent, @PathVariable String openType, @RequestParam("pg_token") String pgToken, Model model) {
        String approveResponse = payService.approve(pgToken);
		/*
		ObjectMapper objectMapper = new ObjectMapper(); 
		JsonNode response = null;

		try { response = objectMapper.readTree(approveResponse); 
		}catch (Exception e)
		{ e.printStackTrace(); }
		 */
        
        //model.addAttribute("response", response);
        model.addAttribute("response", approveResponse);
        return agent + "/" + openType + "/approve";
    }

	// 주문 취소
    @GetMapping("/cancel/{agent}/{openType}")
    public String cancel(@PathVariable String agent, @PathVariable String openType) {
        // 주문건이 진짜 취소되었는지 확인 후 취소 처리
        // 결제내역조회(/v1/payment/status) api에서 status를 확인한다.
        // To prevent the unwanted request cancellation caused by attack,
        // the “show payment status” API is called and then check if the status is QUIT_PAYMENT before suspending the payment
        return agent + "/" + openType + "/cancel";
    }

    // 주문 실패
    @GetMapping("/fail/{agent}/{openType}")
    public String fail(@PathVariable String agent, @PathVariable String openType) {
        // 주문건이 진짜 실패되었는지 확인 후 실패 처리
        // 결제내역조회(/v1/payment/status) api에서 status를 확인한다.
        // To prevent the unwanted request cancellation caused by attack,
        // the “show payment status” API is called and then check if the status is FAIL_PAYMENT before suspending the payment
        return agent + "/" + openType + "/fail";
    }
}
