package com.javaOrder.member.main.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.javaOrder.admin.product.domain.Product;
import com.javaOrder.admin.product.service.ProductService;
import com.javaOrder.common.util.vo.ProductPageRequestDTO;
import com.javaOrder.common.util.vo.ProductPageResponseDTO;
import com.javaOrder.member.cart.domain.Cart;
import com.javaOrder.member.cart.domain.CartItem;
import com.javaOrder.member.cart.service.CartItemService;
import com.javaOrder.member.cart.service.CartService;
import com.javaOrder.member.domain.Member;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Controller
@RequiredArgsConstructor
public class MainController {
	
	/* 메인 화면에 productList, cart, cartList를 볼 수 있도록 해야 해서 service 추가 */
	@Setter(onMethod_ = @Autowired)
	private ProductService productService;

	@Setter(onMethod_ = @Autowired)
	private CartService cartService;
	
	@Setter(onMethod_ = @Autowired)
	private CartItemService cartItemService;
	
	private static final Logger logger = LoggerFactory.getLogger(MainController.class);
	
	@GetMapping("/")
	public String main(Model model, HttpSession session) {

		if(session.getAttribute("member") != null) {
			Member loggedInMember = (Member) session.getAttribute("member");
			
			/*
			try {
	        	Cart cart = cartService.getCartByMemberCode(loggedInMember.getMemberCode());
	            model.addAttribute("cart", cart);
	        } catch (Exception e) {
	        	logger.error("Error retrieving cart: {}", e.getMessage());
	            model.addAttribute("cartError", "장바구니를 찾을 수 없습니다.");
	        }
	        */
		   
			model.addAttribute("member", loggedInMember);

			Cart cart = cartService.getCartByMemberCode(loggedInMember.getMemberCode());
	        model.addAttribute("cart", cart);
			
	        List<CartItem> cartItemList = cartItemService.cartItemList(loggedInMember);
	        model.addAttribute("cartItemList", cartItemList);
	        
	        
		}
		/* 로그인 전에는 제품리스트만 출력 */
		ProductPageRequestDTO productPageRequestDTO = new ProductPageRequestDTO();
		ProductPageResponseDTO<Product> productList = productService.productList(productPageRequestDTO, session);
		model.addAttribute("productList", productList);
		
		return "member/main";
	}

}



/* 테스트서버를 위한 가짜 로그인 
@GetMapping("/fakeLogin")
public String fakeLogin(HttpSession session) {
    Optional<Member> memberOptional = memberRepository.findByMemberCode("M0001");

    if (memberOptional.isPresent()) {
        Member fakeMember = memberOptional.get();
        session.setAttribute("loggedInMember", fakeMember);
        return "redirect:/member/main";
    } else {
        return "error/404";
    }
}
*/
