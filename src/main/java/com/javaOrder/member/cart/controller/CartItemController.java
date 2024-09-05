package com.javaOrder.member.cart.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.javaOrder.admin.product.domain.Product;
import com.javaOrder.admin.product.service.ProductService;
import com.javaOrder.member.cart.domain.CartItem;
import com.javaOrder.member.cart.service.CartItemService;
import com.javaOrder.member.cart.service.CartService;
import com.javaOrder.member.domain.Member;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Controller
@RequestMapping("/cart/*")
@RequiredArgsConstructor
public class CartItemController {
	
	private final CartItemService cartItemService;
	
	@Setter(onMethod_ = @Autowired)
	private CartService cartService;
	
	@Setter(onMethod_ = @Autowired)
	private ProductService productService;

	/* 카트에 담긴 아이템 리스트 */
	@GetMapping("/cartItemList")
	public String cartItemList(HttpSession session, Model model) {
	    // 세션에서 로그인된 사용자 정보 가져오기
	    Member member = (Member) session.getAttribute("loggedInMember");

	    if (member != null) {
	        // 로그인된 사용자가 있는 경우, 장바구니 아이템 목록을 가져온다
	        List<CartItem> cartItemList = cartItemService.cartItemList(member);
	        model.addAttribute("cartItemList", cartItemList); // 속성 이름을 명시적으로 설정
	    }
		return "/member/cart/cartItemList";
	}
	
	
	/* 카트에 아이템 추가, 옵션값 적용 */
	@PostMapping("/insertCartItem")
	@ResponseBody
	public ResponseEntity<?> insertCartItem(@RequestBody Map<String, Object> payload) {
	    try {
	        String cartId = (String) payload.get("cartId");
	        Map<String, Object> cartItemData = (Map<String, Object>) payload.get("cartItem");

	        String productId = (String) cartItemData.get("productId");
	        Product product = productService.getProductById(productId);
	        
	        CartItem cartItem = convertToCartItem(cartItemData);
	        cartItem.setProduct(product);
	        
	        cartItemService.insertCartItem(cartId, cartItem);
	        
	        return ResponseEntity.ok("Cart item added successfully");
	    } catch (Exception e) {
	        return ResponseEntity.badRequest().body("Failed to add cart item: " + e.getMessage());
	    }
	}

	private CartItem convertToCartItem(Map<String, Object> cartItemData) {
	    CartItem cartItem = new CartItem();
	    
	    cartItem.setItemNum(Integer.parseInt(cartItemData.get("count").toString()));
	    
	    int optionShot = (int) cartItemData.get("optionShot");
	    cartItem.setOptionShot(optionShot);
	    
	    cartItem.setOptionSize(Integer.parseInt(cartItemData.get("optionSize").toString()));
	    cartItem.setOptionTemperature(Integer.parseInt(cartItemData.get("optionTemperature").toString()));
	    
	    List<String> syrupOptions = (List<String>) cartItemData.get("optionSyrup");
	    int syrupCount = 0;
	    if (syrupOptions != null) {
	        syrupCount = syrupOptions.size();
	    }
	    cartItem.setOptionSyrup(syrupCount);;

	    int totalPrice = Integer.parseInt(cartItemData.get("totalPrice").toString().replaceAll(",", ""));
	    cartItem.setItemPrice(totalPrice);

	    return cartItem;
	}

	

	/* 카트에 담긴 아이템 옵션 변경 업데이트 */
	@PostMapping("/updateCartItem")
	@ResponseBody
	public void updateCartItem(@RequestParam String itemId, int itemNum) {
		cartItemService.updateCartItem(itemId, itemNum);
	}

	
	/* 카트에 담긴 아이템 삭제 */
	@PostMapping("/deleteCartItem")
	@ResponseBody
	public Map<String, Object> deleteCartItem(@RequestParam String itemId) {
	    cartItemService.deleteCartItem(itemId);
	    Map<String, Object> response = new HashMap<>();
	    response.put("success", true);
	    response.put("message", "Item deleted successfully");
	    return response;
	}
}
