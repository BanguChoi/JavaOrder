package com.javaOrder.admin.product.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.javaOrder.admin.product.DTO.ProductDTO;
import com.javaOrder.admin.product.entity.Product;
import com.javaOrder.admin.product.repository.ProductRepository;

import java.util.Date;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Override
    public Page<ProductDTO> getAllProducts(Pageable pageable) {
        return productRepository.findAll(pageable)
                .map(this::convertEntityToDto);
    }

    @Override
    public ProductDTO getProductById(String productId) {
        return productRepository.findById(productId)
                .map(this::convertEntityToDto)
                .orElse(null);
    }

    @Override
    public void addProduct(ProductDTO productDTO) {
        // 상품 등록 일자 설정
        productDTO.setProductDate(new Date());

        // 카테고리 코드 + 시퀀스 번호로 상품 코드 생성
        String newProductId = generateProductId(productDTO.getCategoryCode());
        productDTO.setProductId(newProductId);

        Product product = convertDtoToEntity(productDTO);
        productRepository.save(product);
    }

    @Override
    public void updateProduct(ProductDTO productDTO) {
        if (productDTO.getProductId() == null) {
            throw new IllegalArgumentException("Product ID must not be null");
        }

        Product product = productRepository.findById(productDTO.getProductId())
                .orElseThrow(() -> new IllegalArgumentException("Invalid product ID"));

        // 업데이트 로직
        product.setProductName(productDTO.getProductName());
        product.setProductPrice(productDTO.getProductPrice());
        product.setProductExplain(productDTO.getProductExplain());
        product.setProductSell(productDTO.getProductSell());

        productRepository.save(product);
    }

    @Override
    public void deleteProduct(String productId) {
        productRepository.deleteById(productId);
    }

    private ProductDTO convertEntityToDto(Product product) {
        ProductDTO productDTO = new ProductDTO();
        productDTO.setProductId(product.getProductId());
        productDTO.setCategoryCode(product.getCategoryCode());
        productDTO.setProductOrder(product.getProductOrder());
        productDTO.setProductExplain(product.getProductExplain());
        productDTO.setProductDate(product.getProductDate());
        productDTO.setProductSell(product.getProductSell());
        productDTO.setProductPrice(product.getProductPrice());
        productDTO.setProductName(product.getProductName());
        return productDTO;
    }

    private Product convertDtoToEntity(ProductDTO productDTO) {
        Product product = new Product();
        product.setProductId(productDTO.getProductId());
        product.setCategoryCode(productDTO.getCategoryCode());
        product.setProductOrder(productDTO.getProductOrder());
        product.setProductExplain(productDTO.getProductExplain());
        product.setProductDate(productDTO.getProductDate());
        product.setProductSell(productDTO.getProductSell());
        product.setProductPrice(productDTO.getProductPrice());
        product.setProductName(productDTO.getProductName());
        return product;
    }

    private String generateProductId(String categoryCode) {
        // 해당 카테고리 코드로 시작하는 기존의 최대 상품 코드를 찾음
        String maxProductId = productRepository.findMaxProductIdByCategoryCode(categoryCode);
        
        int newSequence = 1;
        if (maxProductId != null && maxProductId.length() > categoryCode.length()) {
            // 카테고리 코드 길이 이후의 시퀀스 부분을 추출
            String sequenceStr = maxProductId.substring(categoryCode.length());
            try {
                newSequence = Integer.parseInt(sequenceStr) + 1;
            } catch (NumberFormatException e) {
                // 시퀀스 번호를 숫자로 변환하지 못하는 경우, 기본값으로 1을 사용
                newSequence = 1;
            }
        }

        // 시퀀스를 2자리로 맞추기 위해 형식을 지정합니다.
        String formattedSequence = String.format("%02d", newSequence);
        
        return categoryCode + formattedSequence; // 상품 ID 형식: 카테고리 코드 + 시퀀스 번호
    }


}
