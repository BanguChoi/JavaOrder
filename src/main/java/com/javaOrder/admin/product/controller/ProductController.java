package com.javaOrder.admin.product.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.javaOrder.admin.product.DTO.ProductDTO;
import com.javaOrder.admin.product.service.ProductService;

@Controller
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    // 루트 경로("/") 요청을 /products로 리다이렉트합니다.
    @GetMapping("/")
    public String rootRedirect() {
        return "redirect:/products";
    }

    @GetMapping
    public String listProducts(Model model, Pageable pageable) {
        PageRequest pageRequest = PageRequest.of(pageable.getPageNumber(), 10);
        Page<ProductDTO> productsPage = productService.getAllProducts(pageRequest);
        model.addAttribute("productsPage", productsPage);
        return "product";
    }

    @GetMapping("/new")
    public String showProductForm(Model model) {
        model.addAttribute("product", new ProductDTO());
        return "productForm";
    }

    @PostMapping("/new")
    public ResponseEntity<String> createProduct(@RequestBody ProductDTO productDTO) {
        productService.addProduct(productDTO);
        return ResponseEntity.ok("상품이 성공적으로 등록되었습니다.");
    }


    @GetMapping("/{id}")
    public String getProduct(@PathVariable("id") String id, Model model) {
        ProductDTO product = productService.getProductById(id);
        if (product == null) {
            return "error/404";
        }
        model.addAttribute("product", product);
        return "productDetail";
    }

    @PostMapping("/{id}/update")
    public ResponseEntity<String> updateProduct(@PathVariable("id") String id, @RequestBody ProductDTO productDTO) {
        try {
            productDTO.setProductId(id);
            productService.updateProduct(productDTO);
            return ResponseEntity.ok("상품이 성공적으로 수정되었습니다.");
        } catch (Exception e) {
            e.printStackTrace(); // 서버 로그에 자세한 오류 메시지를 출력합니다.
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("상품 수정 중 오류가 발생했습니다.");
        }
    }

}
