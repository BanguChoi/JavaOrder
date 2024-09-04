package com.javaOrder.common.kakaopay.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.javaOrder.common.kakaopay.domain.KakaoPayReadyResponse;
import com.javaOrder.common.kakaopay.domain.PayApproveRequest;
import com.javaOrder.common.kakaopay.domain.PayReadyRequestVO;
import com.javaOrder.common.kakaopay.service.KakaoPayService;
import com.javaOrder.member.cart.domain.Cart;
import com.javaOrder.member.cart.domain.CartItem;
import com.javaOrder.member.cart.repository.CartItemRepository;
import com.javaOrder.member.cart.repository.CartRepository;
import com.javaOrder.member.domain.Member;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class KakaoPayController {
	
	private final KakaoPayService payService;
	private final CartRepository cartRepository;
	private final CartItemRepository cartItemRepository;
//	private final OrdersRepository orderRepository;
	// 결제 준비
	@GetMapping("/pay/ready/{takeout}")
    public String ready(@PathVariable Integer takeout, HttpSession session,
    		PayReadyRequestVO readyRequest, Model model) {
		Member member = (Member) session.getAttribute("member");
		Cart cart = cartRepository.findByMember_MemberCode(member.getMemberCode());
			//.orElseThrow(()-> new IllegalArgumentException("해당 회원의 장바구니가 존재하지 않습니다."));

		// 해당 장바구니에 담긴 CartItem 조회
		List<CartItem> cartItems = cartItemRepository.findByCart(cart);
		if( cartItems.isEmpty() )
			throw new IllegalStateException("장바구니가 비어있습니다.");
		String itemName = payService.generateOrderName(cartItems);
		
		String memberCode = member.getMemberCode();
		String orderNumber = payService.generateOrderNumber();
		
		// Request param
		readyRequest = PayReadyRequestVO.builder()
				.partnerOrderId(orderNumber)	// 주문번호
				.partnerUserId(memberCode)				// 회원번호
				.itemName(itemName)									// 상품명
				.quantity(cart.getCartItems().size())				// 수량
				.totalAmount(cart.getCartPrice())					// 총금액
				.taxFreeAmount(0)									// 비과세
				.vatAmount(0)										// 부가세
				.takeout(takeout)
				.build();

        KakaoPayReadyResponse readyResponse = payService.ready(readyRequest);

        model.addAttribute("response", readyResponse);
        return "/member/pay/ready";
    }
	
	
	// 결제 승인
	@GetMapping("/pay/approve")
    public String approve(@RequestParam("pg_token") String pgToken, @RequestParam("orderNumber") String orderNumber,
    		@RequestParam("memberCode") String memberCode, Model model) {
		
		if(orderNumber == null || memberCode == null) {
			throw new IllegalStateException("결제 준비 단계에서 정보가 누락되었습니다.");
		}
		
		PayApproveRequest payApproveRequest = PayApproveRequest.builder()
				.partnerOrderId(orderNumber)
				.partnerUserId(memberCode)
				.build();
        String approveResponse = payService.approve(pgToken, payApproveRequest);
		
		ObjectMapper objectMapper = new ObjectMapper(); 

		try { 
			// JSON 문자열을 JsonNode로 변환
			JsonNode jsonNode = objectMapper.readTree(approveResponse);
			// "회원번호 추출"
			String mCode = jsonNode.get("partner_user_id").asText();
			// 장바구니 항목 정보 주문내역으로 복사/붙여넣기
			payService.copyCartToOrder(mCode, orderNumber);
		}catch (Exception e) { 
			e.printStackTrace(); 
		}
		
        model.addAttribute("response", approveResponse);
        return "/member/pay/approve";
    }        
    
	
	
	
	// 주문 취소
    @GetMapping("/pay/cancel/")
    public String cancel() {
        // 주문건이 진짜 취소되었는지 확인 후 취소 처리
        // 결제내역조회(/v1/payment/status) api에서 status를 확인한다.
        // To prevent the unwanted request cancellation caused by attack,
        // the “show payment status” API is called and then check if the status is QUIT_PAYMENT before suspending the payment
        return "/member/pay/cancel";
    }

    // 주문 실패
    @GetMapping("/pay/fail/")
    public String fail() {
        // 주문건이 진짜 실패되었는지 확인 후 실패 처리
        // 결제내역조회(/v1/payment/status) api에서 status를 확인한다.
        // To prevent the unwanted request cancellation caused by attack,
        // the “show payment status” API is called and then check if the status is FAIL_PAYMENT before suspending the payment
        return"/member/pay/fail";
    }
}
