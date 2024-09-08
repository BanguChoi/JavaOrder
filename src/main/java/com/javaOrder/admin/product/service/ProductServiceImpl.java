package com.javaOrder.admin.product.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.javaOrder.admin.product.domain.Category;
import com.javaOrder.admin.product.domain.Product;
import com.javaOrder.admin.product.repository.CategoryRepository;
import com.javaOrder.admin.product.repository.ProductRepository;
import com.javaOrder.common.util.vo.ProductPageRequestDTO;
import com.javaOrder.common.util.vo.ProductPageResponseDTO;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public Product createProduct(String categoryCode, String productName, Integer price, String productExplain) {
        // 카테고리 코드로 카테고리 엔티티 조회
        Category category = categoryRepository.findById(categoryCode)
                .orElseThrow(() -> new IllegalArgumentException("Invalid category code: " + categoryCode));

        // 해당 카테고리 코드로 시작하는 상품 코드 중 가장 큰 값을 조회
        String maxProductCode = productRepository.findMaxProductCodeByCategoryCode(categoryCode);

        // 새로운 상품 코드 생성
        String newProductCode;
        if (maxProductCode == null) {
            newProductCode = categoryCode + "01";  // 첫 번째 상품 코드
        } else {
            // 마지막 상품 코드의 숫자 부분을 증가시킴
            int codeNumber = Integer.parseInt(maxProductCode.substring(categoryCode.length())) + 1;
            newProductCode = String.format("%s%02d", categoryCode, codeNumber);
        }

        // 새로운 상품 엔티티 생성 및 저장
        Product product = new Product();
        product.setProductId(newProductCode);
        product.setCategory(category);
        product.setProductName(productName);
        product.setProductPrice(price);
        product.setProductExplain(productExplain);  // 설명 추가

        return productRepository.save(product);
    }

    @Override
    public Page<Product> getProducts(Pageable pageable) {
        return productRepository.findAll(pageable);
    }

    @Override
    public Product getProductById(String productId) {
        return productRepository.findById(productId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid product ID: " + productId));
    }

    @Override
    public Product saveProduct(Product product) {
        return productRepository.save(product);
    }

    // 검색 기능 구현
    @Override
    public Page<Product> findByProductNameContaining(String productName, Pageable pageable) {
        return productRepository.findByProductNameContaining(productName, pageable);
    }

    @Override
    public Page<Product> findByCategoryCode(String categoryCode, Pageable pageable) {
        return productRepository.findByCategory_Code(categoryCode, pageable);
    }

    @Override
    public Page<Product> findByProductDate(LocalDate productDate, Pageable pageable) {
        return productRepository.findByProductDate(productDate, pageable);
    }
    
    
    
    
    
    
    
    
    /* 제품 리스트 + 페이징 + 검색기능 */
    @Override
   	public ProductPageResponseDTO<Product> productList(ProductPageRequestDTO productPageRequestDTO) {
   		Pageable pageable = PageRequest.of(
   				productPageRequestDTO.getPage()-1, 
   				productPageRequestDTO.getSize(), Sort.by("productDate").descending());
   		
   		Page<Product> result;
   		String keyWord = productPageRequestDTO.getKeyword();
   		String categoryCode = productPageRequestDTO.getCategory();
   		
   		if (keyWord != null && !keyWord.isEmpty()) {
   	        if (categoryCode != null && !categoryCode.isEmpty()) {
   	            result = productRepository.findByProductNameContainingAndCategory_Code(keyWord, categoryCode, pageable);
   	        } else {
   	            result = productRepository.findByProductNameContaining(keyWord, pageable);
   	        }
   	    } else {
   	        if (categoryCode != null && !categoryCode.isEmpty()) {
   	            result = productRepository.findByCategory_Code(categoryCode, pageable);
   	        } else {
   	            result = productRepository.findAll(pageable);
   	        }
   	    }
   		
   		List<Product> productList = result.getContent();
   		long totalCount = result.getTotalElements();

   		ProductPageResponseDTO<Product> responseDTO = ProductPageResponseDTO.<Product>withAll()
   				.dtoList(productList)
   				.productPageRequestDTO(productPageRequestDTO)
   				.totalCount(totalCount)
   				.build();
   		
   		return responseDTO;
   	}
}
