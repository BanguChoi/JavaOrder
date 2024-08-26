package com.javaOrder.manage.product.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.javaOrder.manage.product.DTO.ProductDTO;
import com.javaOrder.manage.product.service.ProductService;

@Controller
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping
    public String listProducts(Model model) {
        List<ProductDTO> products = productService.getAllProducts();
        model.addAttribute("products", products);
        return "product";
    }

    @GetMapping("/{id}")
    public String getProduct(@PathVariable("id") String id, Model model) {
        ProductDTO product = productService.getProductById(id);
        if (product == null) {
            return "error/404"; // 제품이 없는 경우 404 페이지로 리다이렉트
        }
        model.addAttribute("product", product);
        return "productDetail"; // 템플릿 파일 이름
    }

    @PostMapping("/{id}/update")
    public String updateProduct(@PathVariable("id") String id, @ModelAttribute("product") ProductDTO productDTO) {
        productDTO.setProductId(id);  // productId를 DTO에 설정
        productService.updateProduct(productDTO);
        return "redirect:/products/" + id;
    }
}
