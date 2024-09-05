package com.javaOrder.common.kakaopay.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;

import com.javaOrder.common.kakaopay.domain.ApproveRequest;
import com.javaOrder.common.kakaopay.domain.KakaoPayReadyRequest;
import com.javaOrder.common.kakaopay.domain.KakaoPayReadyResponse;
import com.javaOrder.common.kakaopay.domain.PayApproveRequest;
import com.javaOrder.common.kakaopay.domain.PayReadyRequestVO;
import com.javaOrder.common.orders.domain.OrderItem;
import com.javaOrder.common.orders.domain.Orders;
import com.javaOrder.common.orders.repository.OrderItemRepository;
import com.javaOrder.common.orders.repository.OrdersRepository;
import com.javaOrder.common.util.service.IdGenerationService;
import com.javaOrder.member.cart.domain.Cart;
import com.javaOrder.member.cart.domain.CartItem;
import com.javaOrder.member.cart.repository.CartItemRepository;
import com.javaOrder.member.cart.repository.CartRepository;
import com.javaOrder.member.domain.Member;
import com.javaOrder.member.repository.MemberRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@Transactional
@RequiredArgsConstructor
public class KakaoPayServiceImpl implements KakaoPayService {
	@Value("${kakaopayJavaOrder.kakaopay-secret-key}")
	private String kakaoSecretKey;
	
	@Value("${kakaopayJavaOrder.cid}")
	private String cid;
	
	@Value("${kakaopayJavaOrder.host}")
	private String hostURL;
	
	// 결제 요청
	public KakaoPayReadyResponse ready(PayReadyRequestVO payReadyRequest) {
		// Request Header
		HttpHeaders headers = new HttpHeaders();
		headers.add("Authorization", "DEV_SECRET_KEY "+kakaoSecretKey);
		headers.setContentType(MediaType.APPLICATION_JSON);

		String orderNumber = payReadyRequest.getPartnerOrderId();
		String memberCode = payReadyRequest.getPartnerUserId();
		
		KakaoPayReadyRequest readyRequest = KakaoPayReadyRequest.builder()
			.cid(cid)
			.partnerOrderId(payReadyRequest.getPartnerOrderId())
			.partnerUserId(payReadyRequest.getPartnerUserId())
			.itemName(payReadyRequest.getItemName())
			.quantity(payReadyRequest.getQuantity())
			.totalAmount(payReadyRequest.getTotalAmount())
			.taxFreeAmount(payReadyRequest.getTaxFreeAmount())
			.vatAmount(payReadyRequest.getVatAmount())
			.approvalUrl(hostURL+"/pay/approve?orderNumber="+orderNumber+"&memberCode="+memberCode)
			.cancelUrl(hostURL+"/pay/cancel/")
			.failUrl(hostURL+"/pay/fail/")
			.build();

		// Send request
		HttpEntity<KakaoPayReadyRequest> entityMap = new HttpEntity<>(readyRequest, headers);
		ResponseEntity<KakaoPayReadyResponse> response = new RestTemplate().postForEntity(
					"https://open-api.kakaopay.com/online/v1/payment/ready",
					entityMap,
					KakaoPayReadyResponse.class
		);
		KakaoPayReadyResponse readyResponse = response.getBody();
		
		Orders order = Orders.builder()
				.orderNumber(Long.valueOf(orderNumber))
				.memberCode(memberRepository.findById(memberCode).get())
				.orderPrice(0)
				.orderStatus("W")
				.orderName("상품명")
				.tid(readyResponse.getTid())
				.orderTakeout(payReadyRequest.getTakeout())
				.build();
		
		orderRepository.save(order);
		
		return readyResponse;
	}
	
	
	public String approve(String pgToken, PayApproveRequest payApproveRequest) {
        // ready할 때 저장해놓은 TID로 승인 요청
        // Call “Execute approved payment” API by pg_token, TID mapping to the current payment transaction and other parameters.
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "SECRET_KEY " + kakaoSecretKey);
        headers.setContentType(MediaType.APPLICATION_JSON);
        
        // Request param
        ApproveRequest approveRequest = ApproveRequest.builder()
                .cid(cid)
                .tid(orderRepository.findByOrderNumber(Long.valueOf(payApproveRequest.getPartnerOrderId())).getTid())
                .partnerOrderId(payApproveRequest.getPartnerOrderId())
                .partnerUserId(payApproveRequest.getPartnerUserId())
                .pgToken(pgToken)
                .build();

        // Send Request
        HttpEntity<ApproveRequest> entityMap = new HttpEntity<>(approveRequest, headers);
        try {
            ResponseEntity<String> response = new RestTemplate().postForEntity(
                    "https://open-api.kakaopay.com/online/v1/payment/approve",
                    entityMap,
                    String.class
            );

            // 승인 결과를 저장한다.
            // save the result of approval
            String approveResponse = response.getBody();
            return approveResponse;
        } catch (HttpStatusCodeException ex) {
            return ex.getResponseBodyAsString();
        }
    }
	
	
	
	private final MemberRepository memberRepository;
	private final CartRepository cartRepository;
	private final OrdersRepository orderRepository;
	private final CartItemRepository cartItemRepository;
	private final OrderItemRepository orderItemRepository;
	private final IdGenerationService idGenerationService;
	
	@Override
	public void copyCartToOrder(String mCode, String orderNumber) {
		// 주문한 회원의 장바구니 조회
		Optional<Member> memberOptional = memberRepository.findById(mCode);
		Cart cart = cartRepository.findByMember_MemberCode(memberOptional.get().getMemberCode());
				//.orElseThrow(()-> new IllegalArgumentException("해당 회원의 장바구니가 존재하지 않습니다."));
		
		// 해당 장바구니에 담긴 CartItem 조회
		List<CartItem> cartItems = cartItemRepository.findByCart(cart);
		if( cartItems.isEmpty() )
			throw new IllegalStateException("장바구니가 비어있습니다.");
		
		// 첫번째 상품명을 기반으로 orderName 설정 (000외 n개)
		String orderName = generateOrderName(cartItems);
		
		// 주문 불러오기
		Orders newOrder = orderRepository.findByOrderNumber(Long.valueOf(orderNumber));

		// 주문 내용 갱신
		newOrder.setMemberCode(cart.getMember());
		newOrder.setOrderDate(LocalDateTime.now());
		newOrder.setOrderPrice(cart.getCartPrice());
		newOrder.setOrderStatus("W");
		newOrder.setOrderName(orderName);
		
		orderRepository.save(newOrder);
		

		
		// 장바구니 항목을 주문 항목으로 변환
		for (CartItem cartItem : cartItems) {
			// 주문항목 ID 생성
			String orderItemId = idGenerationService.generateId(String.valueOf(newOrder.getOrderNumber()), "order_item_seq", 5);
			
			OrderItem newOrderItem = OrderItem.builder()
					.orderitemId(orderItemId)
					.orderNumber(newOrder)
					.productId(cartItem.getProduct())
					.orderitemNumber(cartItem.getItemNum())
					.orderitemPrice(cartItem.getItemPrice())
					.orderitemShot(cartItem.getOptionShot())
					.orderitemSize(cartItem.getOptionSize())
					.orderitemTemp(cartItem.getOptionTemperature())
					.orderitemSyrup(cartItem.getOptionSyrup())
					.build();
			
			orderItemRepository.save(newOrderItem);
		}
		
		// 해당 장바구니 내용 삭제
//		cartItemRepository.deleteAll(cartItems);
		cartItemRepository.deleteByCart(cart);
	}
	
	
	// 주문번호 생성
	@Override
	public String generateOrderNumber() {
		String orderNumber = idGenerationService.generateId("", "orders_seq", 5);
		return orderNumber;
	}
	
	// 장바구니 총 계산
	// ?
	
	// 장바구니 항목들의 이름을 기반으로 주문명 생성 (예: "상품명 외 n건")
	@Override
	public String generateOrderName(List<CartItem> cartItems) {
	    if (cartItems.isEmpty()) {
	        return "주문 상품 없음";
	    }
	    String firstItemName = cartItems.get(0).getProduct().getProductName();  // 첫 번째 상품명
	    int remainingItems = cartItems.size() - 1;  // 나머지 상품 개수
	    if (remainingItems > 0) {
	        return firstItemName + " 외 " + remainingItems + "건";
	    } else {
	        return firstItemName;  // 상품이 1개인 경우
	    }
	}
	
}
