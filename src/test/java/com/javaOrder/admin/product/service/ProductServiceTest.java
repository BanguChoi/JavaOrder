package com.javaOrder.admin.product.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import com.javaOrder.admin.product.domain.Category;
import com.javaOrder.admin.product.domain.Product;
import com.javaOrder.admin.product.repository.CategoryRepository;
import com.javaOrder.admin.product.repository.ProductRepository;

@SpringBootTest
@Transactional
@Rollback(value = false)
public class ProductServiceTest {

    @Autowired
    private ProductServiceImpl productService;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private ProductRepository productRepository;

/*    
    @BeforeEach
    void setUp() {
        // 카테고리 데이터를 사전에 저장
        categoryRepository.save(new Category("A01", "콜드 브루 커피"));
        categoryRepository.save(new Category("A02", "브루드 커피"));
        categoryRepository.save(new Category("A03", "에스프레소"));
        categoryRepository.save(new Category("A04", "프라푸치노"));
        categoryRepository.save(new Category("A05", "블렌디드"));
        categoryRepository.save(new Category("A06", "리프레셔"));
        categoryRepository.save(new Category("A07", "피지오"));
        categoryRepository.save(new Category("A08", "티"));
        categoryRepository.save(new Category("A09", "기타 제조 음료"));
        categoryRepository.save(new Category("A10", "주스"));
        categoryRepository.save(new Category("B01", "브레드"));
        categoryRepository.save(new Category("B02", "케이크"));
        categoryRepository.save(new Category("B03", "샌드위치"));
        categoryRepository.save(new Category("B04", "수프"));
        categoryRepository.save(new Category("B05", "과일, 요거트"));
        categoryRepository.save(new Category("B06", "스낵, 미니 디저트"));
        categoryRepository.save(new Category("B07", "아이스크림"));
    }

    @Test
    void testCreateProductsInCategoryA01() {
        // given
        String categoryCode = "A01";
        int price = 0;
        String[] productNames = {
            "나이트로 바닐라 크림", "돌체 콜드 브루", "리저브 나이트로", "리저브 콜드 브루", "막걸리향 크림 콜드 브루",
            "민트 콜드 브루", "바닐라 크림 콜드 브루"
        };

        // when
        for (int i = 0; i < productNames.length; i++) {
            String productName = productNames[i];
            Product product = productService.createProduct(categoryCode, productName, price);

            // then
            assertNotNull(product);
            assertEquals(String.format("A01%02d", i + 1), product.getProductId());
            assertEquals(productName, product.getProductName());
            assertEquals(0, product.getProductPrice());

            // 데이터베이스에 실제로 저장되었는지 확인
            Product savedProduct = productRepository.findById(product.getProductId()).orElse(null);
            assertNotNull(savedProduct);
            assertEquals(String.format("A01%02d", i + 1), savedProduct.getProductId());
            assertEquals(0, savedProduct.getProductPrice());
        }
    }

    @Test
    void testCreateProductsInCategoryA02() {
        // given
        String categoryCode = "A02";
        int price = 0;
        String[] productNames = {"아이스 커피", "오늘의 커피"};

        // when
        for (int i = 0; i < productNames.length; i++) {
            String productName = productNames[i];
            Product product = productService.createProduct(categoryCode, productName, price);

            // then
            assertNotNull(product);
            assertEquals(String.format("A02%02d", i + 1), product.getProductId());
            assertEquals(productName, product.getProductName());
            assertEquals(0, product.getProductPrice());

            // 데이터베이스에 실제로 저장되었는지 확인
            Product savedProduct = productRepository.findById(product.getProductId()).orElse(null);
            assertNotNull(savedProduct);
            assertEquals(String.format("A02%02d", i + 1), savedProduct.getProductId());
            assertEquals(0, savedProduct.getProductPrice());
        }
    }

    @Test
    void testCreateProductsInCategoryA03() {
        // given
        String categoryCode = "A03";
        int price = 0;
        String[] productNames = {
            "펌킨 스파이스 오트 아이스 쉐이큰 에스프레소", "에스프레소 마키아또", "카페 아메리카노", "카라멜 마키아또", "카푸치노",
            "바닐라 빈 라떼", "카페 모카"
        };

        // when
        for (int i = 0; i < productNames.length; i++) {
            String productName = productNames[i];
            Product product = productService.createProduct(categoryCode, productName, price);

            // then
            assertNotNull(product);
            assertEquals(String.format("A03%02d", i + 1), product.getProductId());
            assertEquals(productName, product.getProductName());
            assertEquals(0, product.getProductPrice());

            // 데이터베이스에 실제로 저장되었는지 확인
            Product savedProduct = productRepository.findById(product.getProductId()).orElse(null);
            assertNotNull(savedProduct);
            assertEquals(String.format("A03%02d", i + 1), savedProduct.getProductId());
            assertEquals(0, savedProduct.getProductPrice());
        }
    }
    
    @Test
    void testCreateProductsInCategoryA04() {
        // given
        String categoryCode = "A04";
        int price = 0;
        String[] productNames = {
            "자몽 망고 코코 프라푸치노", "더블 에스프레소 칩 프라푸치노", "에스프레소 프라푸치노", "자바 칩 프라푸치노", "카라멜 프라푸치노",
            "제주 말차 크림 프라푸치노", "초콜릿 크림 칩 프라푸치노"
        };

        // when
        for (int i = 0; i < productNames.length; i++) {
            String productName = productNames[i];
            Product product = productService.createProduct(categoryCode, productName, price);

            // then
            assertNotNull(product);
            assertEquals(String.format("A04%02d", i + 1), product.getProductId());
            assertEquals(productName, product.getProductName());
            assertEquals(0, product.getProductPrice());

            // 데이터베이스에 실제로 저장되었는지 확인
            Product savedProduct = productRepository.findById(product.getProductId()).orElse(null);
            assertNotNull(savedProduct);
            assertEquals(String.format("A04%02d", i + 1), savedProduct.getProductId());
            assertEquals(0, savedProduct.getProductPrice());
        }
    }
    
    @Test
    void testCreateProductsInCategoryA05() {
        // given
        String categoryCode = "A05";
        int price = 0;
        String[] productNames = {
            "망고 패션 티 블렌디드", "북한산 레몬 얼 그레이 블렌디드", "여수 바다 유자 블렌디드", "딸기 딜라이트 요거트 블렌디드", "망고 바나나 블렌디드",
            "제주 팔삭 자몽 허니 블렌디드", "코튼 스카이 요거트 블렌디드"
        };

        // when
        for (int i = 0; i < productNames.length; i++) {
            String productName = productNames[i];
            Product product = productService.createProduct(categoryCode, productName, price);

            // then
            assertNotNull(product);
            assertEquals(String.format("A05%02d", i + 1), product.getProductId());
            assertEquals(productName, product.getProductName());
            assertEquals(0, product.getProductPrice());

            // 데이터베이스에 실제로 저장되었는지 확인
            Product savedProduct = productRepository.findById(product.getProductId()).orElse(null);
            assertNotNull(savedProduct);
            assertEquals(String.format("A05%02d", i + 1), savedProduct.getProductId());
            assertEquals(0, savedProduct.getProductPrice());
        }
    }
    
    @Test
    void testCreateProductsInCategoryA06() {
        // given
        String categoryCode = "A06";
        int price = 0;
        String[] productNames = {
            "청포도 유자 레모네이드 리프레셔", "딸기 아사이 레모네이드 스타벅스 리프레셔", "망고 용과 레모네이드 스타벅스 리프레셔"
        };

        // when
        for (int i = 0; i < productNames.length; i++) {
            String productName = productNames[i];
            Product product = productService.createProduct(categoryCode, productName, price);

            // then
            assertNotNull(product);
            assertEquals(String.format("A06%02d", i + 1), product.getProductId());
            assertEquals(productName, product.getProductName());
            assertEquals(0, product.getProductPrice());

            // 데이터베이스에 실제로 저장되었는지 확인
            Product savedProduct = productRepository.findById(product.getProductId()).orElse(null);
            assertNotNull(savedProduct);
            assertEquals(String.format("A06%02d", i + 1), savedProduct.getProductId());
            assertEquals(0, savedProduct.getProductPrice());
        }
    }
    
    @Test
    void testCreateProductsInCategoryA07() {
        // given
        String categoryCode = "A07";
        int price = 0;
        String[] productNames = {
            "리버 피치 피지오", "여수 바다 자몽 피지오", "쿨 라임 피지오"
        };

        // when
        for (int i = 0; i < productNames.length; i++) {
            String productName = productNames[i];
            Product product = productService.createProduct(categoryCode, productName, price);

            // then
            assertNotNull(product);
            assertEquals(String.format("A07%02d", i + 1), product.getProductId());
            assertEquals(productName, product.getProductName());
            assertEquals(0, product.getProductPrice());

            // 데이터베이스에 실제로 저장되었는지 확인
            Product savedProduct = productRepository.findById(product.getProductId()).orElse(null);
            assertNotNull(savedProduct);
            assertEquals(String.format("A07%02d", i + 1), savedProduct.getProductId());
            assertEquals(0, savedProduct.getProductPrice());
        }
    }
    
    @Test
    void testCreateProductsInCategoryA08() {
        // given
        String categoryCode = "A08";
        int price = 0;
        String[] productNames = {
            "얼 그레이 티", "유스베리 티", "잉글리쉬 브렉퍼스트 티", "자몽 허니 블랙 티", "제주 유기농 녹차로 만든 티",
            "캐모마일 블렌드 티", "히비스커스 블렌드 티"
        };

        // when
        for (int i = 0; i < productNames.length; i++) {
            String productName = productNames[i];
            Product product = productService.createProduct(categoryCode, productName, price);

            // then
            assertNotNull(product);
            assertEquals(String.format("A08%02d", i + 1), product.getProductId());
            assertEquals(productName, product.getProductName());
            assertEquals(0, product.getProductPrice());

            // 데이터베이스에 실제로 저장되었는지 확인
            Product savedProduct = productRepository.findById(product.getProductId()).orElse(null);
            assertNotNull(savedProduct);
            assertEquals(String.format("A08%02d", i + 1), savedProduct.getProductId());
            assertEquals(0, savedProduct.getProductPrice());
        }
    }
    
    @Test
    void testCreateProductsInCategoryA09() {
        // given
        String categoryCode = "A09";
        int price = 0;
        String[] productNames = {
            "딸기 콜드폼 초콜릿", "시그니처 핫 초콜릿", "티라미수 초콜릿", "딸기 콜드폼 딸기 라떼", "스타벅스 슬래머"
        };

        // when
        for (int i = 0; i < productNames.length; i++) {
            String productName = productNames[i];
            Product product = productService.createProduct(categoryCode, productName, price);

            // then
            assertNotNull(product);
            assertEquals(String.format("A09%02d", i + 1), product.getProductId());
            assertEquals(productName, product.getProductName());
            assertEquals(0, product.getProductPrice());

            // 데이터베이스에 실제로 저장되었는지 확인
            Product savedProduct = productRepository.findById(product.getProductId()).orElse(null);
            assertNotNull(savedProduct);
            assertEquals(String.format("A09%02d", i + 1), savedProduct.getProductId());
            assertEquals(0, savedProduct.getProductPrice());
        }
    }
    
    @Test
    void testCreateProductsInCategoryA10() {
        // given
        String categoryCode = "A10";
        int price = 0;
        String[] productNames = {
            "ABC 클렌즈 190ML", "레몬 진저 클렌즈 190ML", "케일 클렌즈 190ML", "딸기주스 190ML", "망고주스 190ML",
            "유기농 오렌지 100% 주스 190ML", "한라봉주스 190ML"
        };

        // when
        for (int i = 0; i < productNames.length; i++) {
            String productName = productNames[i];
            Product product = productService.createProduct(categoryCode, productName, price);

            // then
            assertNotNull(product);
            assertEquals(String.format("A10%02d", i + 1), product.getProductId());
            assertEquals(productName, product.getProductName());
            assertEquals(0, product.getProductPrice());

            // 데이터베이스에 실제로 저장되었는지 확인
            Product savedProduct = productRepository.findById(product.getProductId()).orElse(null);
            assertNotNull(savedProduct);
            assertEquals(String.format("A10%02d", i + 1), savedProduct.getProductId());
            assertEquals(0, savedProduct.getProductPrice());
        }
    }

    @Test
    void testCreateProductsInCategoryB01() {
        // given
        String categoryCode = "B01";
        int price = 0;
        String[] productNames = {
            "탕종 플레인 베이글", "클래식 스콘", "마스카포네 크림 소라빵", "미니 리프 파이", "뺑 오 쇼콜라", 
            "소금빵", "소시지 프레첼 소금빵" 
        };

        // when
        for (int i = 0; i < productNames.length; i++) {
            String productName = productNames[i];
            Product product = productService.createProduct(categoryCode, productName, price);

            // then
            assertNotNull(product);
            assertEquals(String.format("B01%02d", i + 1), product.getProductId());
            assertEquals(productName, product.getProductName());
            assertEquals(0, product.getProductPrice());

            // 데이터베이스에 실제로 저장되었는지 확인
            Product savedProduct = productRepository.findById(product.getProductId()).orElse(null);
            assertNotNull(savedProduct);
            assertEquals(String.format("B01%02d", i + 1), savedProduct.getProductId());
            assertEquals(0, savedProduct.getProductPrice());
        }
    }
    
    @Test
    void testCreateProductsInCategoryB02() {
        // given
        String categoryCode = "B02";
        int price = 0;
        String[] productNames = {
            "7 레이어 가나슈 케이크", "The 촉촉 초콜릿 생크림 케이크", "마스카포네 티라미수 케이크", "부드러운 생크림 카스텔라", "생크림 크레이프 롤", 
            "스틱 에그 타르트", "돔 피칸 타르트" 
        };

        // when
        for (int i = 0; i < productNames.length; i++) {
            String productName = productNames[i];
            Product product = productService.createProduct(categoryCode, productName, price);

            // then
            assertNotNull(product);
            assertEquals(String.format("B02%02d", i + 1), product.getProductId());
            assertEquals(productName, product.getProductName());
            assertEquals(0, product.getProductPrice());

            // 데이터베이스에 실제로 저장되었는지 확인
            Product savedProduct = productRepository.findById(product.getProductId()).orElse(null);
            assertNotNull(savedProduct);
            assertEquals(String.format("B02%02d", i + 1), savedProduct.getProductId());
            assertEquals(0, savedProduct.getProductPrice());
        }
    }
    
    @Test
    void testCreateProductsInCategoryB03() {
        // given
        String categoryCode = "B03";
        int price = 0;
        String[] productNames = {
            "멜팅 치즈 햄, 어니언 샌드위치", "BELT샌드위치", "단호박 에그 샐러드 샌드위치", "베이컨 치즈 토스트", "에그에그 샌드위치", 
            "치즈 포크 커틀릿 샌드위치", "치킨 클래식 샌드위치" 
        };

        // when
        for (int i = 0; i < productNames.length; i++) {
            String productName = productNames[i];
            Product product = productService.createProduct(categoryCode, productName, price);

            // then
            assertNotNull(product);
            assertEquals(String.format("B03%02d", i + 1), product.getProductId());
            assertEquals(productName, product.getProductName());
            assertEquals(0, product.getProductPrice());

            // 데이터베이스에 실제로 저장되었는지 확인
            Product savedProduct = productRepository.findById(product.getProductId()).orElse(null);
            assertNotNull(savedProduct);
            assertEquals(String.format("B03%02d", i + 1), savedProduct.getProductId());
            assertEquals(0, savedProduct.getProductPrice());
        }
    }
    
    @Test
    void testCreateProductsInCategoryB04() {
        // given
        String categoryCode = "B04";
        int price = 0;
        String[] productNames = {
            "트러플 머쉬룸 수프", "플랜트 미트볼 수프"
        };

        // when
        for (int i = 0; i < productNames.length; i++) {
            String productName = productNames[i];
            Product product = productService.createProduct(categoryCode, productName, price);

            // then
            assertNotNull(product);
            assertEquals(String.format("B04%02d", i + 1), product.getProductId());
            assertEquals(productName, product.getProductName());
            assertEquals(0, product.getProductPrice());

            // 데이터베이스에 실제로 저장되었는지 확인
            Product savedProduct = productRepository.findById(product.getProductId()).orElse(null);
            assertNotNull(savedProduct);
            assertEquals(String.format("B04%02d", i + 1), savedProduct.getProductId());
            assertEquals(0, savedProduct.getProductPrice());
        }
    }
    
    @Test
    void testCreateProductsInCategoryB05() {
        // given
        String categoryCode = "B05";
        int price = 0;
        String[] productNames = {
            "하루 한 컵 RED+", "오가닉 그릭 요커트 플레인", "밀크 푸딩", "제주 녹차 푸딩", "초콜릿 푸딩"
        };

        // when
        for (int i = 0; i < productNames.length; i++) {
            String productName = productNames[i];
            Product product = productService.createProduct(categoryCode, productName, price);

            // then
            assertNotNull(product);
            assertEquals(String.format("B05%02d", i + 1), product.getProductId());
            assertEquals(productName, product.getProductName());
            assertEquals(0, product.getProductPrice());

            // 데이터베이스에 실제로 저장되었는지 확인
            Product savedProduct = productRepository.findById(product.getProductId()).orElse(null);
            assertNotNull(savedProduct);
            assertEquals(String.format("B05%02d", i + 1), savedProduct.getProductId());
            assertEquals(0, savedProduct.getProductPrice());
        }
    }
    
    @Test
    void testCreateProductsInCategoryB06() {
        // given
        String categoryCode = "B06";
        int price = 0;
        String[] productNames = {
            "다크 초콜릿 카우보이 쿠키", "초콜릿 월넛 돔 쿠키", "까망베르 치즈 피낭시에", "바닐라 마카롱", "블루베리 마카롱", 
            "버터바", "쁘띠 까눌레" 
        };

        // when
        for (int i = 0; i < productNames.length; i++) {
            String productName = productNames[i];
            Product product = productService.createProduct(categoryCode, productName, price);

            // then
            assertNotNull(product);
            assertEquals(String.format("B06%02d", i + 1), product.getProductId());
            assertEquals(productName, product.getProductName());
            assertEquals(0, product.getProductPrice());

            // 데이터베이스에 실제로 저장되었는지 확인
            Product savedProduct = productRepository.findById(product.getProductId()).orElse(null);
            assertNotNull(savedProduct);
            assertEquals(String.format("B06%02d", i + 1), savedProduct.getProductId());
            assertEquals(0, savedProduct.getProductPrice());
        }
    }
    
    @Test
    void testCreateProductsInCategoryB07() {
        // given
        String categoryCode = "B07";
        int price = 0;
        String[] productNames = {
            "바닐라 빈 젤라또", "파베 초콜릿 젤라또", "딸기 요거트 젤라또", "요거트 젤라또", "피스타치오 젤라또"
        };

        // when
        for (int i = 0; i < productNames.length; i++) {
            String productName = productNames[i];
            Product product = productService.createProduct(categoryCode, productName, price);

            // then
            assertNotNull(product);
            assertEquals(String.format("B07%02d", i + 1), product.getProductId());
            assertEquals(productName, product.getProductName());
            assertEquals(0, product.getProductPrice());

            // 데이터베이스에 실제로 저장되었는지 확인
            Product savedProduct = productRepository.findById(product.getProductId()).orElse(null);
            assertNotNull(savedProduct);
            assertEquals(String.format("B07%02d", i + 1), savedProduct.getProductId());
            assertEquals(0, savedProduct.getProductPrice());
        }
    }*/
    
    
    public Product saveProduct(Product product) {
        if (!"Y".equals(product.getProductSell()) && !"N".equals(product.getProductSell())) {
            throw new IllegalArgumentException("Invalid value for productSell: must be 'Y' or 'N'");
        }
        return productRepository.save(product);
    }
}
