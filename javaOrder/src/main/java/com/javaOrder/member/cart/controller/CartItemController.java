package com.javaOrder.member.cart.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.javaOrder.member.cart.domain.CartItem;
import com.javaOrder.member.cart.service.CartItemService;
import com.javaOrder.member.product.service.ProductService;

import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Controller
@RequestMapping("/cart/*")
@RequiredArgsConstructor
public class CartItemController {

	private final CartItemService cartItemService;
	
	@Setter(onMethod_ = @Autowired)
	private ProductService productService;

	/* 카트에 담긴 아이템 리스트 */
	@GetMapping("/cartItemList")
	public String cartItemList(CartItem cartItem, Model model) {
		List<CartItem> cartItemList = cartItemService.cartItemList(cartItem);
		model.addAttribute(cartItemList);
		return "javaOrder/member/cart/cartItemList";
	}
	
	/* 카트에 아이템 추가, 옵션값 적용, */
	@PostMapping("/insertCartItem")
	public String insertCartItem(CartItem cartItem, Model model) {
		
		CartItem item = new CartItem();
		item.setOptionShot(cartItem.getOptionShot());
		item.setOptionSize(cartItem.getOptionSize());
		item.setOptionTemperature(cartItem.getOptionTemperature());
		item.setOptionSyrup(cartItem.getOptionSyrup());
		cartItemService.insertCartItem(item);
		
		return "redirect:javaOrder/member/main";
	}
	
	/* 문자열을 int로 변환하는 메서드
	 * productDetail에서 1가지 옵션만 선택할 수 있도록 radio 타입을 사용하였는데,
	 * radio는 string을 반환하기에, 추가된 옵션값을 계산하려면 int변환 필요! 
	 * 
	private int parseOptionValue(String value) {
	    try {
	        return Integer.parseInt(value);
	    } catch (NumberFormatException e) {
	        return 0; // 변환 실패 시 기본값으로 0 반환
	    }
	}
	*/
	
	
	/* 장바구니 아이템 수정 페이지 */
	@GetMapping("/updateCartItemForm")
	public String updateCartItemForm() {
		
		return "javaOrder/member/cart/updateCartItemForm";
	}
	
	

	/* 카트에 담긴 아이템 옵션 변경 업데이트 */
	@PostMapping("/updateCartItem")
	public String updateCartItem(CartItem cartItem) {
		cartItemService.updateCartItem(cartItem);
		return "redirect:member/cart/" + cartItem.getItemId();
	}

	/* 카트에 담긴 아이템 삭제 */
	@DeleteMapping("/{itemId}")
	public String deleteCartItem(@PathVariable String itemId, CartItem cartItem) {
		cartItem.setItemId(itemId);
		cartItemService.deleteCartItem(cartItem);
		return "redirect:member/cart/cartItemList";
	}








}
