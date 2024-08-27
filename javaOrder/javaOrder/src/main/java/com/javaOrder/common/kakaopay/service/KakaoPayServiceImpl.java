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
import com.javaOrder.member.cart.repository.CartRepository;
import com.javaOrder.member.cartItem.domain.CartItem;
import com.javaOrder.member.cartItem.repository.CartItemRepository;
import com.javaOrder.member.domain.Member;
import com.javaOrder.member.repository.MemberRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@RequiredArgsConstructor
public class KakaoPayServiceImpl implements KakaoPayService {
	@Value("${kakaopayJavaOrder.kakaopay-secret-key}")
	private String kakaoSecretKey;
	
	@Value("${kakaopayJavaOrder.cid}")
	private String cid;
	
	@Value("${kakaopayJavaOrder.host}")
	private String hostURL;
	
	private String tid;
	
	public KakaoPayReadyResponse ready(PayReadyRequestVO payReadyRequest) {
		// Request Header
		HttpHeaders headers = new HttpHeaders();
		headers.add("Authorization", "DEV_SECRET_KEY "+kakaoSecretKey);
		headers.setContentType(MediaType.APPLICATION_JSON);

		//		// Request param
//		KakaoPayReadyRequest readyRequest = KakaoPayReadyRequest.builder()
//				.cid(cid)
//				.partnerOrderId("1")
//				.partnerUserId("1")
//				.itemName("상품명")
//				.quantity(1)
//				.totalAmount(1100)
//				.taxFreeAmount(0)
//				.vatAmount(100)
//				.approvalUrl(hostURL+"/approve/"+agent+"/"+openType)
//				.cancelUrl(hostURL+"/cancel/"+agent+"/"+openType)
//				.failUrl(hostURL+"/fail/"+agent+"/"+openType)
//				.build();
		KakaoPayReadyRequest readyRequest = KakaoPayReadyRequest.builder()
			.cid(cid)
			.partnerOrderId("10003")
			.partnerUserId(payReadyRequest.getPartnerUserId())
			.itemName(payReadyRequest.getItemName())
			.quantity(payReadyRequest.getQuantity())
			.totalAmount(payReadyRequest.getTotalAmount())
			.taxFreeAmount(payReadyRequest.getTaxFreeAmount())
			.vatAmount(payReadyRequest.getVatAmount())
			.approvalUrl(hostURL+"/pay/approve/")
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
		
		// 주문번호와 TID 매핑해서 저장
		// Mapping TID with partner_order_id than save it to use for approval request.
		this.tid = readyResponse.getTid();
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
                .tid(tid)
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
	public void copyCartToOrder(String mCode) {
		// 주문한 회원의 장바구니 조회
		Optional<Member> memberOptional = memberRepository.findById(mCode);
		Cart cart = cartRepository.findBymemberCode(memberOptional.get());
				//.orElseThrow(()-> new IllegalArgumentException("해당 회원의 장바구니가 존재하지 않습니다."));
		
		// 해당 장바구니에 담긴 CartItem 조회
		List<CartItem> cartItems = cartItemRepository.findByCart(cart);
		if( cartItems.isEmpty() )
			throw new IllegalStateException("장바구니가 비어있습니다.");
		
		// 첫번째 상품명을 기반으로 orderName 설정 (000외 n개)
		String orderName = generateOrderName(cartItems);
		
		// 새 주문 생성
		Orders newOrder = Orders.builder()
				.memberCode(cart.getMemberCode())
				.orderDate(LocalDateTime.now())
				.orderPrice(cart.getCartPrice())
				.orderStatus("W")
				.orderName(orderName)
				.build();
		
		orderRepository.save(newOrder);
		
		// 주문항목 ID 생성
		String orderItemId = idGenerationService.generateId(String.valueOf(newOrder.getOrderNumber()), "order_item_seq", 3);
		
		// 장바구니 항목을 주문 항목으로 변환
		for (CartItem cartItem : cartItems) {
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
					.orderitemTakeout(0)
					.orderitemExfee(0)
					.build();
			
			orderItemRepository.save(newOrderItem);
		}
		
		// 해당 장바구니 내용 삭제
		cartItemRepository.deleteAll(cartItems);
		
//		return newOrder;
	}
	
	
	// 장바구니 총 계산
	// ?
	
	// 장바구니 항목들의 이름을 기반으로 주문명 생성 (예: "상품명 외 n건")
	private String generateOrderName(List<CartItem> cartItems) {
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