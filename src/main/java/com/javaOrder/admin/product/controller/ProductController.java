package com.javaOrder.admin.product.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.javaOrder.admin.product.domain.Category;
import com.javaOrder.admin.product.domain.Product;
import com.javaOrder.admin.product.service.CategoryService;
import com.javaOrder.admin.product.service.ProductService;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@Controller
@RequestMapping("/admin/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @Autowired
    private CategoryService categoryService;

    private final Path imageUploadPath = Paths.get("C:/uploads/images"); // 이미지 저장 경로

    @GetMapping
    public String listProducts(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "productId") String sortBy,  // 정렬 기준
            @RequestParam(defaultValue = "asc") String direction,     // 정렬 방향
            Model model) {

        // 정렬 방향 설정
        Sort.Direction sortDirection = direction.equalsIgnoreCase("asc") ? Sort.Direction.ASC : Sort.Direction.DESC;

        // 정렬 기준과 방향을 포함한 PageRequest 생성
        PageRequest pageRequest = PageRequest.of(page, size, Sort.by(sortDirection, sortBy));

        // 정렬된 페이지 데이터 가져오기
        Page<Product> productsPage = productService.getProducts(pageRequest);
        model.addAttribute("productsPage", productsPage);

        return "product/product";
    }

    @GetMapping("/{id}")
    public String showProductDetails(@PathVariable("id") String productId, Model model) {
        if ("new".equals(productId)) {
            return "redirect:/admin/products/new";
        }

        Product product = productService.getProductById(productId);
        model.addAttribute("product", product);
        return "product/productDetail";
    }

    @PutMapping("/{id}")
    @ResponseBody
    public ResponseEntity<String> updateProduct(@PathVariable("id") String productId, @RequestBody Product product) {
        Product existingProduct = productService.getProductById(productId);
        if (existingProduct != null) {
            existingProduct.setProductName(product.getProductName());
            existingProduct.setProductPrice(product.getProductPrice());
            existingProduct.setProductExplain(product.getProductExplain());
            existingProduct.setProductSell(product.getProductSell());
            productService.saveProduct(existingProduct);
            return ResponseEntity.ok("상품 정보가 수정되었습니다.");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("상품을 찾을 수 없습니다.");
        }
    }

    @GetMapping("/new")
    public String showProductForm(Model model) {
        List<Category> categories = categoryService.getAllCategories();
        model.addAttribute("categories", categories);
        return "product/productForm";
    }

    @PostMapping
    @ResponseBody
    public ResponseEntity<String> createProduct(@RequestBody Product product) {
        if (product.getCategory() == null || product.getCategory().getCode() == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("카테고리 정보가 없습니다.");
        }

        productService.createProduct(product.getCategory().getCode(), product.getProductName(), product.getProductPrice());
        return ResponseEntity.ok("상품이 성공적으로 등록되었습니다.");
    }

    @PostMapping("/uploadImage")
    @ResponseBody
    public ResponseEntity<String> uploadProductImage(@RequestParam("image") MultipartFile image, @RequestParam("productId") String productId) {
        if (image.isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("이미지가 선택되지 않았습니다.");
        }

        try {
            // 이미지 파일 이름을 설정 (productId.확장자)
            String imageName = productId + "." + getExtension(image.getOriginalFilename());
            Path imagePath = imageUploadPath.resolve(imageName);

            // 이미지 저장
            image.transferTo(imagePath.toFile());

            // 상품 이미지 경로 업데이트
            Product product = productService.getProductById(productId);
            product.setProductImage(imageName); // 이미지 경로 저장
            productService.saveProduct(product);

            return ResponseEntity.ok("이미지가 성공적으로 업로드되었습니다.");
        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("이미지 업로드 중 오류가 발생했습니다.");
        }
    }

    @PostMapping("/deleteImage")
    @ResponseBody
    public ResponseEntity<String> deleteProductImage(@RequestParam("productId") String productId) {
        try {
            // 상품 정보 가져오기
            Product product = productService.getProductById(productId);

            if (product.getProductImage() == null || product.getProductImage().isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("이미지가 없습니다.");
            }

            // 이미지 경로 설정
            Path imagePath = imageUploadPath.resolve(product.getProductImage());

            // 이미지 파일 삭제
            File imageFile = imagePath.toFile();
            if (imageFile.exists()) {
                imageFile.delete();
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("이미지 파일을 찾을 수 없습니다.");
            }

            // 상품 이미지 정보 초기화
            product.setProductImage(null);
            productService.saveProduct(product);

            return ResponseEntity.ok("이미지가 성공적으로 삭제되었습니다.");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("이미지 삭제 중 오류가 발생했습니다.");
        }
    }

    // 파일 이름에서 확장자 추출 메서드
    private String getExtension(String filename) {
        return filename.substring(filename.lastIndexOf(".") + 1);
    }
}
