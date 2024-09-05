
package com.javaOrder.admin.product.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.javaOrder.admin.product.domain.Product;

public interface ProductRepository extends JpaRepository<Product, String> {

    @Query("SELECT MAX(p.productId) FROM Product p WHERE p.category.code = :cateCode")
    String findMaxProductCodeByCategoryCode(@Param("cateCode") String cateCode);
}
