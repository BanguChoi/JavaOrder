package com.javaOrder.member.main.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.javaOrder.member.cart.domain.Cart;
import com.javaOrder.member.cart.domain.CartItem;
import com.javaOrder.member.cart.service.CartItemService;
import com.javaOrder.member.cart.service.CartService;
import com.javaOrder.member.domain.Member;
import com.javaOrder.member.product.domain.Product;
import com.javaOrder.member.product.service.ProductService;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Controller
@RequiredArgsConstructor
@RequestMapping("/member/*")
public class MainController {
	
	/* 메인 화면에 productList, cart, cartList를 볼 수 있도록 해야 해서 service 추가 */
	@Setter(onMethod_ = @Autowired)
	private ProductService productService;

	@Setter(onMethod_ = @Autowired)
	private CartService cartService;
	
	@Setter(onMethod_ = @Autowired)
	private CartItemService cartItemService;
	
	private static final Logger logger = LoggerFactory.getLogger(MainController.class);
	
	@GetMapping("/main")
	public String main(Model model, HttpSession session) {
								    // : 사용자가 로그인한 후 현재 세션에서 그 사용자를 식별하는 데 사용

		Member loggedInMember = (Member) session.getAttribute("member");
		
		// 임시 로그인 사용자 생성 (테스트용, 실제 사용자 객체로 대체 필요)
		if (loggedInMember == null) {
            loggedInMember = new Member();
            loggedInMember.setMemberCode("M0007");
            loggedInMember.setMemberName("홍길동");
            session.setAttribute("member", loggedInMember);
        } try {
        	Cart cart = cartService.getCartByMemberCode(loggedInMember.getMemberCode());
            logger.info("Retrieved cart: {}", cart);
            logger.info("Cart ID: {}", cart.getCartId());
            logger.info("Cart Price: {}", cart.getCartPrice());
            logger.info("Cart Items: {}", cart.getCartItems());
            logger.info("Cart Items Size: {}", cart.getCartItems() != null ? cart.getCartItems().size() : "null");
            model.addAttribute("cart", cart);
        } catch (Exception e) {
        	logger.error("Error retrieving cart: {}", e.getMessage());
            model.addAttribute("cartError", "장바구니를 찾을 수 없습니다.");
        }
	   
		model.addAttribute("member", loggedInMember);


		/* 로그인 전에는 제품리스트만 출력 */
		List<Product> productList = productService.productList(new Product());
		model.addAttribute("productList", productList);	
		
		Cart cart = cartService.getCartByMemberCode(loggedInMember.getMemberCode());
        model.addAttribute("cart", cart);
		
        List<CartItem> cartItemList = cartItemService.cartItemList(loggedInMember);
        model.addAttribute("cartItemList", cartItemList);

        
		return "/member/main";
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
