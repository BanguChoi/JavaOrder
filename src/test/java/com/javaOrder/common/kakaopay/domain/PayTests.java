package com.javaOrder.common.kakaopay.domain;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.javaOrder.common.orders.repository.OrderItemRepository;
import com.javaOrder.common.orders.repository.OrdersRepository;
import com.javaOrder.common.util.service.IdGenerationService;
import com.javaOrder.member.cart.domain.CartItem;
import com.javaOrder.member.cart.repository.CartItemRepository;
import com.javaOrder.member.cart.repository.CartRepository;
import com.javaOrder.member.repository.MemberRepository;

import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

@SpringBootTest
@Slf4j
public class PayTests {

	@Setter(onMethod_=@Autowired)
	private MemberRepository memberRepository;
	@Setter(onMethod_=@Autowired)
	private CartRepository cartRepository;
	@Setter(onMethod_=@Autowired)
	private OrdersRepository orderRepository;
	@Setter(onMethod_=@Autowired)
	private CartItemRepository cartItemRepository;
	@Setter(onMethod_=@Autowired)
	private OrderItemRepository orderItemRepository;
	@Setter(onMethod_=@Autowired)
	private IdGenerationService idGenerationService;
	
	/*
	 * @Test public void copyCartToOrderTest() { String mCode = "M043"; // 주문한 회원의
	 * 장바구니 조회 Optional<Member> memberOptional = memberRepository.findById(mCode);
	 * Cart cart = cartRepository.findBymemberCode(memberOptional.get());
	 * //.orElseThrow(()-> new IllegalArgumentException("해당 회원의 장바구니가 존재하지 않습니다."));
	 * log.info("============주문한 회원의 장바구니 조회============");
	 * log.info(cart.toString());
	 * 
	 * // 해당 장바구니에 담긴 CartItem 조회 List<CartItem> cartItems =
	 * cartItemRepository.findByCart(cart);
	 * log.info("============해당 장바구니에 담긴 cartItem 조회============"); if(
	 * cartItems.isEmpty() ) throw new IllegalStateException("장바구니가 비어있습니다.");
	 * for(CartItem item:cartItems) { log.info(item.toString()); }
	 * log.info("======================================"); // 첫번째 상품명을 기반으로
	 * orderName 설정 (000외 n개) String orderName = generateOrderName(cartItems);
	 * log.info("상품명 : " + orderName);
	 * 
	 * // 새 주문 생성 Orders newOrder = Orders.builder()
	 * .memberCode(cart.getMemberCode()) .orderDate(LocalDateTime.now())
	 * .orderPrice(cart.getCartPrice()) .orderStatus("W") .orderName(orderName)
	 * .build(); log.info(newOrder.toString()); orderRepository.save(newOrder);
	 * 
	 * String orderItemId =
	 * idGenerationService.generateId(String.valueOf(newOrder.getOrderNumber()),
	 * "order_item_seq", 3); log.info("주문항목 ID: "+orderItemId);
	 * 
	 * // 장바구니 항목을 주문 항목으로 변환 for (CartItem cartItem : cartItems) { OrderItem
	 * newOrderItem = OrderItem.builder() .orderitemId(orderItemId)
	 * .orderNumber(newOrder) .productId(cartItem.getProduct())
	 * .orderitemNumber(cartItem.getItemNum())
	 * .orderitemPrice(cartItem.getItemPrice())
	 * .orderitemShot(cartItem.getOptionShot())
	 * .orderitemSize(cartItem.getOptionSize())
	 * .orderitemTemp(cartItem.getOptionTemperature())
	 * .orderitemSyrup(cartItem.getOptionSyrup()) .orderitemTakeout(0)
	 * .orderitemExfee(0) .build(); log.info(newOrderItem.toString());
	 * orderItemRepository.save(newOrderItem); log.info(newOrderItem.toString()); }
	 * log.info("해당 장바구니 내용 삭제"); cartItemRepository.deleteAll(cartItems); }
	 */
	
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
