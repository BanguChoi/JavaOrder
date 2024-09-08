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
        int[] prices = {6100, 6000, 7500, 6500, 9000, 8000, 5800};  // 가격 배열
        String[] explanations = {
            "부드러운 목넘김의 나이트로 커피와 바닐라 크림의 매력을 한번에 느껴보세요!\r\n"
            + "제품 영양 정보 : 1회 제공량(kcal) 80 / 나트륨(mg) 40 / 포화지방(g) 2 / 당류(g) 10 / 단백질(g) 1 / 카페인(mg) 232\r\n"
            + "알레르기 유발요인 : 우유",
            "무더운 여름철, 동남아 휴가지에서 즐기는 커피를 떠로으게 하는 스타벅스 음료의 베스트 X 베스트 조합인 돌체 콜드 브루를 만나보세요!\r\n"
            + "제품 영양 정보 : 1회 제공량(kcal) 265 / 나트륨(mg) 130 / 포화지방(g) 9 / 당류(g) 29 / 단백질(g) 8 / 카페인(mg) 155\r\n"
            + "알레르기 유발요인 : 우유",
            "나이트로 커피 정통의 캐스케이딩과 부드러운 콜드 크레마! 부드러운 목 넘김과 완벽한 밸런스에 커피 본연의 단맛을 경험할 수 있습니다.\r\n"
            + "제품 영양 정보 : 1회 제공량(kcal) 5 / 나트륨(mg) 0 / 포화지방(g) 0 / 당류(g) 0 / 단백질(g) 0 / 카페인(mg) 190",
            "리저브 커피 마스터의 정성으로 차갑게 추출한 깊고 부드러운 풍미의 커피\r\n"
            + "제품 영양 정보 : 1회 제공량(kcal) 5 / 나트륨(mg) 0 / 포화지방(g) 0 / 당류(g) 0 / 단백질(g) 0 / 카페인(mg) 190",
            "산에서 만날 수 있는 특별한 경험을 모티브로 해 달콤, 향긋한 비알코올 막걸리 향 크림과 고소한 쌀 토핑으로 즐길 수 있는 콜드 브루\r\n"
            + "제품 영양 정보 : 1회 제공량(kcal) 300 / 나트륨(mg) 65 / 포화지방(g) 13 / 당류(g) 32 / 단백질(g) 3 / 카페인(mg) 53\r\n"
            + "알레르기 유발요인 : 우유",
            "상쾌한 민트향 시럽과 잘게 갈린 얼음이 어우러져 시원함이 강렬하게 느껴지는 리저브만의 콜드 브루 음료\r\n"
            + "제품 영양 정보 : 1회 제공량(kcal) 100 / 나트륨(mg) 0 / 포화지방(g) 0 / 당류(g) 23 / 단백질(g) 0 / 카페인(mg) 415",
            "콜드 브루에 더해진 바닐라 크림으로 깔끔하면서 달콤한 콜드 브루를 새롭게 즐길 수 있는 음료입니다.\r\n"
            + "제품 영양 정보 : 1회 제공량(kcal) 125 / 나트륨(mg) 58 / 포화지방(g) 6 / 당류(g) 11 / 단백질(g) 3 / 카페인(mg) 155\r\n"
            + "알레르기 유발요인 : 우유"
        };
        String[] productNames = {
            "나이트로 바닐라 크림", "돌체 콜드 브루", "리저브 나이트로", "리저브 콜드 브루", "막걸리향 크림 콜드 브루",
            "민트 콜드 브루", "바닐라 크림 콜드 브루"
        };

        // when
        for (int i = 0; i < productNames.length; i++) {
            String productName = productNames[i];
            int price = prices[i];
            String explain = explanations[i];

            Product product = productService.createProduct(categoryCode, productName, price, explain);

            // then
            assertNotNull(product);
            assertEquals(String.format("A01%02d", i + 1), product.getProductId());
            assertEquals(productName, product.getProductName());
            assertEquals(price, product.getProductPrice());
            assertEquals(explain, product.getProductExplain());

            // 데이터베이스에 실제로 저장되었는지 확인
            Product savedProduct = productRepository.findById(product.getProductId()).orElse(null);
            assertNotNull(savedProduct);
            assertEquals(String.format("A01%02d", i + 1), savedProduct.getProductId());
            assertEquals(price, savedProduct.getProductPrice());
            assertEquals(explain, savedProduct.getProductExplain());
        }
    }

    @Test
    void testCreateProductsInCategoryA02() {
        // given
        String categoryCode = "A02";
        int[] prices = {4500, 4200};  // 가격 배열
        String[] explanations = {
            "시즌에 어울리는 원두 종류를 선정하여 드립 방식으로 추출한 후 얼음과 함께 제공하는 커피로, 원두 커피의 풍부하고 깔끔한 맛을 느끼실 수 있습니다.\r\n"
            + "제품 영양 정보 : 1회 제공량(kcal) 5 / 나트륨(mg) 10 / 포화지방(g) 0 / 당류(g) 0 / 단백질(g) 0 / 카페인(mg) 140",
            "시즌에 어울리는 원두 종류를 선정하여 신선하게 브루드 되어 제공되는 드립커피로, 원두 커피의 풍부한 맛과 향을 따뜻하게 즐기실 수 있습니다.\r\n"
            + "제품 영양 정보 : 1회 제공량(kcal) 5 / 나트륨(mg) 15 / 포화지방(g) 0 / 당류(g) 0 / 단백질(g)01 / 카페인(mg) 260"
        };
        String[] productNames = {
            "아이스 커피", "오늘의 커피"
        };

        // when
        for (int i = 0; i < productNames.length; i++) {
            String productName = productNames[i];
            int price = prices[i];
            String explain = explanations[i];

            Product product = productService.createProduct(categoryCode, productName, price, explain);

            // then
            assertNotNull(product);
            assertEquals(String.format("A02%02d", i + 1), product.getProductId());
            assertEquals(productName, product.getProductName());
            assertEquals(price, product.getProductPrice());
            assertEquals(explain, product.getProductExplain());

            // 데이터베이스에 실제로 저장되었는지 확인
            Product savedProduct = productRepository.findById(product.getProductId()).orElse(null);
            assertNotNull(savedProduct);
            assertEquals(String.format("A02%02d", i + 1), savedProduct.getProductId());
            assertEquals(price, savedProduct.getProductPrice());
            assertEquals(explain, savedProduct.getProductExplain());
        }
    }
    
    @Test
    void testCreateProductsInCategoryA03() {
        // given
        String categoryCode = "A03";
        int[] prices = {6300, 3700, 4500, 5900, 5000, 7000, 5500};  // 가격 배열
        String[] explanations = {
            "스타벅스 글로벌 시즌 플레이버 펌킨 스파이스! 달콤한 호박과 이국적인 스파이스 풍미가 커피와 부드럽게 어우러진 쉐이큰 에스프레소 음료\r\n"
            + "제품 영양 정보 : 1회 제공량(kcal) 175 / 나트륨(mg) 115 / 포화지방(g) 0.8 / 당류(g) 30 / 단백질(g) 2 / 카페인(mg) 170\r\n"
            + "알레르기 유발요인 : 대두 / 우유",
            "신선한 에스프레소 샷에 우유 커품을 살짝 얹은 커피 음료로써, 강렬한 에스프레소의 맛과 우유의 부드러움을 같이 즐길 수 있는 커피 음료\r\n"
            + "제품 영양 정보 : 1회 제공량(kcal) 10 / 나트륨(mg) 0 / 포화지방(g) 0 / 당류(g) 0 / 단백질(g) 1 / 카페인(mg) 75\r\n"
            + "알레르기 유발요인 : 우유",
            "진한 에스프레소와 뜨거운 물을 섞어 스타벅스의 깔끔하고 강렬한 에스프레소를 가장 부드럽게 잘 느낄 수 있는 커피\r\n"
            + "제품 영양 정보 : 1회 제공량(kcal) 10 / 나트륨(mg) 5 / 포화지방(g) 0 / 당류(g) 0 / 단백질(g) 1 / 카페인(mg) 150",
            "향극한 바닐라 시럽과 따뜻한 스팀 밀크 위에 풍성한 우유 거품을 얹고 점을 찍듯이 에스프레소를 부은 후 벌집 모양으로 카라멜 드리즐을 올린 달콤한 커피 음료\r\n"
            + "제품 영양 정보 : 1회 제공량(kcal) 200 / 나트륨(mg) 130 / 포화지방(g) 5 / 당류(g) 22 / 단백질(g) 8 / 카페인(mg) 75\r\n"
            + "알레르기 유발요인 : 대두 / 우유",
            "풍부하고 진한 에스프레소에 따뜻한 우유와 벨벳 같은 우유 거품이 1:1 비율로 어우러져 마무리된 커피 음료\r\n"
            + "제품 영양 정보 : 1회 제공량(kcal) 110 / 나트륨(mg) 70 / 포화지방(g) 3 / 당류(g) 8 / 단백질(g) 6 / 카페인(mg) 75\r\n"
            + "알레르기 유발요인 : 우유",
            "리저브만을 위한 바닐라 빈 시럽이 부드럽게 어우러진 카페 라떼\r\n"
            + "제품 영양 정보 : 1회 제공량(kcal) 245 / 나트륨(mg) 150 / 포화지방(g) 6 / 당류(g) 27 / 단백질(g) 9 / 카페인(mg) 210\r\n"
            + "알레르기 유발요인 : 우유",
            "진한 초콜릿 모카 시럽과 풍부한 에스프레소를 스팀 밀크와 섞어 휘핑크림으로 마무리한 음료로 진한 에스프레소와 초콜릿 맛이 어우러진 커피\r\n"
            + "제품 영양 정보 : 1회 제공량(kcal) 290 / 나트륨(mg) 105 / 포화지방(g) 9 / 당류(g) 25 / 단백질(g) 10 / 카페인(mg) 95\r\n"
            + "알레르기 유발요인 : 우유"
        };
        String[] productNames = {
            "펌킨 스파이스 오트 아이스 쉐이큰 에스프레소	", "에스프레소 마키아또", "카페 아메리카노", "카라멜 마키아또", "카푸치노",
            "바닐라 빈 라떼", "카페 모카"
        };

        // when
        for (int i = 0; i < productNames.length; i++) {
            String productName = productNames[i];
            int price = prices[i];
            String explain = explanations[i];

            Product product = productService.createProduct(categoryCode, productName, price, explain);

            // then
            assertNotNull(product);
            assertEquals(String.format("A03%02d", i + 1), product.getProductId());
            assertEquals(productName, product.getProductName());
            assertEquals(price, product.getProductPrice());
            assertEquals(explain, product.getProductExplain());

            // 데이터베이스에 실제로 저장되었는지 확인
            Product savedProduct = productRepository.findById(product.getProductId()).orElse(null);
            assertNotNull(savedProduct);
            assertEquals(String.format("A03%02d", i + 1), savedProduct.getProductId());
            assertEquals(price, savedProduct.getProductPrice());
            assertEquals(explain, savedProduct.getProductExplain());
        }
    }
    
    @Test
    void testCreateProductsInCategoryA04() {
        // given
        String categoryCode = "A04";
        int[] prices = {7100, 6300, 5500, 6300, 5900, 6300, 6000};  // 가격 배열
        String[] explanations = {
            "코코넛 베이스에 상큼한 자몽과 망고를 담은 프라푸치노\r\n"
            + "제품 영양 정보 : 1회 제공량(kcal) 255 / 나트륨(mg) 210 / 포화지방(g) 6 / 당류(g) 34 / 단백질(g) 2 / 카페인(mg) 0\r\n"
            + "알레르기 유발요인 : 대두 / 우유 / 달걀",
            "리시트레토 에스프레소 2샷과 에스프레소 칩, 하프앤하프가 진하게 어우러진 커피의 기본에 충실한 프라푸치노\r\n"
            + "제품 영양 정보 : 1회 제공량(kcal) 290 / 나트륨(mg) 140 / 포화지방(g) 13 / 당류(g) 31 / 단백질(g) 4 / 카페인(mg) 130\r\n"
            + "알레르기 유발요인 : 우유",
            "에스프레소 샷의 강렬함과 약간의 닷맛이 어우러진 프라푸치노\r\n"
            + "제품 영양 정보 : 1회 제공량(kcal) 145 / 나트륨(mg) 115 / 포화지방(g) 1.1 / 당류(g) 29 / 단백질(g) 2 / 카페인(mg) 120\r\n"
            + "알레르기 유발요인 : 우유",
            "커피, 모카소스, 진한 초콜릿 칩이 입안 가득 느껴지는 스타벅스에서만 맛볼 수 있는 프라푸치노\r\n"
            + "제품 영양 정보 : 1회 제공량(kcal) 340 / 나트륨(mg) 180 / 포화지방(g) 9 / 당류(g) 42 / 단백질(g) 6 / 카페인(mg) 100\r\n"
            + "알레르기 유발요인 : 대두 / 우유 / 밀",
            "카라멜과 커피가 어우러진 프라푸치노\r\n"
            + "제품 영양 정보 : 1회 제공량(kcal) 300 / 나트륨(mg) 190 / 포화지방(g) 7 / 당류(g) 39 / 단백질(g) 4 / 카페인(mg) 85\r\n"
            + "알레르기 유발요인 : 대두 / 우유",
            "깊고 진한 말차 본연의 맛과 향을 시원하고 부드럽게 즐길 수 있는 프라푸치노\r\n"
            + "제품 영양 정보 : 1회 제공량(kcal) 230 / 나트륨(mg) 150 / 포화지방(g) 7 / 당류(g) 28 / 단백질(g) 5 / 카페인(mg) 60\r\n"
            + "알레르기 유발요인 : 우유",
            "모카 소스와 진한 초콜릿 칩. 초콜릿 드리즐이 올라간 달콤한 크림 프라푸치노\r\n"
            + "제품 영양 정보 : 1회 제공량(kcal) 300 / 나트륨(mg) 160 / 포화지방(g) 7 / 당류(g) 40 / 단백질(g) 6 / 카페인(mg) 10 \r\n"
            + "알레르기 유발요인 : 대두 / 우유 / 밀"
        };
        String[] productNames = {
            "자몽 망고 코코 프라푸치노", "더블 에스프레소 칩 프라푸치노", "에스프레소 프라푸치노", "자바 칩 프라푸치노", "카라멜 프라푸치노",
            "제주 말차 크림 프라푸치노", "초콜릿 크림 칩 프라푸치노"
        };

        // when
        for (int i = 0; i < productNames.length; i++) {
            String productName = productNames[i];
            int price = prices[i];
            String explain = explanations[i];

            Product product = productService.createProduct(categoryCode, productName, price, explain);

            // then
            assertNotNull(product);
            assertEquals(String.format("A04%02d", i + 1), product.getProductId());
            assertEquals(productName, product.getProductName());
            assertEquals(price, product.getProductPrice());
            assertEquals(explain, product.getProductExplain());

            // 데이터베이스에 실제로 저장되었는지 확인
            Product savedProduct = productRepository.findById(product.getProductId()).orElse(null);
            assertNotNull(savedProduct);
            assertEquals(String.format("A04%02d", i + 1), savedProduct.getProductId());
            assertEquals(price, savedProduct.getProductPrice());
            assertEquals(explain, savedProduct.getProductExplain());
        }
    }
    
    @Test
    void testCreateProductsInCategoryA05() {
        // given
        String categoryCode = "A05";
        int[] prices = {5400, 9000, 9500, 6800, 6300, 6500, 9800};  // 가격 배열
        String[] explanations = {
            "망고 패션 프루트 주스와 패션 탱고 티가 상큼하게 어우러진 과일 블렌디드\r\n"
            + "제품 영양 정보 : 1회 제공량(kcal) 150 / 나트륨(mg) 105 / 포화지방(g) 0 / 당류(g) 29 / 단백질(g) 2 / 카페인(mg) 0\r\n"
            + "알레르기 유발요인 : 대두",
            "꼬냑 향을 가미한 상큼한 레모네이드와 은은한 얼 그레이 티가 어우러진 블렌디드 음료\r\n"
            + "제품 영양 정보 : 1회 제공량(kcal) 110 / 나트륨(mg) 10 / 포화지방(g) 0.1 / 당류(g) 25 / 단백질(g) 0 / 카페인(mg) 8",
            "맑고 깨끗한 여수 경도의 낮 바다 풍경을 형상화한 음료로 상큼하게 즐길 수 있는 유자 블렌디드 음료\r\n"
            + "제품 영양 정보 : 1회 제공량(kcal) 235 / 나트륨(mg) 10 / 포화지방(g) 0 / 당류(g) 57 / 단백질(g) 0 / 카페인(mg) 0",
            "유산균이 살아있는 리얼 요거트와 풍성한 딸기 과육이 더욱 상큼하게 어우러진 과일 요거트 블렌디드\r\n"
            + "제품 영양 정보 : 1회 제공량(kcal) 310 / 나트륨(mg) 110 / 포화지방(g) 4.3 / 당류(g) 43 / 단백질(g) 10 / 카페인(mg) 0\r\n"
            + "알레르기 유발요인 : 우유",
            "달콤한 망고 패션 프루트 주스에 바나나 1개가 통째로 들어간 신선한 블렌디드\r\n"
            + "제품 영양 정보 : 1회 제공량(kcal) 290 / 나트륨(mg) 130 / 포화지방(g) 0.9 / 당류(g) 45 / 단백질(g) 4 / 카페인(mg) 0\r\n"
            + "알레르기 유발요인 : 대두 / 우유",
            "23년 여름 베스트셀러 자몽 허니 레몬 블렌디드의 업그레이드 버전 음료! 제주 팔삭과 자몽이 어우러진 달콤 쌉싸름한 맛이 매력적인 블렌디드\r\n"
            + "제품 영양 정보 : 1회 제공량(kcal) 172 / 나트륨(mg) 10 / 포화지방(g) 0 / 당류(g) 39 / 단백질(g) 1 / 카페인(mg) 0\r\n"
            + "알레르기 유발요인 : 대두",
            "고층 매장에서 느낄  수 있는 드넓은 전경을 형상화한 음료로, 붉게 노을지는 하늘을 표현한 솜사탕 토핑과 함께 즐길 수 있는 피나콜라다 컨셉의 음료.\r\n"
            + "제품 영양 정보 : 1회 제공량(kcal) 270 / 나트륨(mg) 65 / 포화지방(g) 1.8 / 당류(g) 41 / 단백질(g) 4 / 카페인(mg) 0\r\n"
            + "알레르기 유발요인 : 우유"
        };
        String[] productNames = {
            "망고 패션 티 블렌디드", "북한산 레몬 얼 그레이 블렌디드", "여수 바다 유자 블렌디드", "딸기 딜라이트 요거트 블렌디드", 
            "망고 바나나 블렌디드", "제주 팔삭 자몽 허니 블렌디드", "코튼 스카이 요거트 블렌디드"
        };

        // when
        for (int i = 0; i < productNames.length; i++) {
            String productName = productNames[i];
            int price = prices[i];
            String explain = explanations[i];

            Product product = productService.createProduct(categoryCode, productName, price, explain);

            // then
            assertNotNull(product);
            assertEquals(String.format("A05%02d", i + 1), product.getProductId());
            assertEquals(productName, product.getProductName());
            assertEquals(price, product.getProductPrice());
            assertEquals(explain, product.getProductExplain());

            // 데이터베이스에 실제로 저장되었는지 확인
            Product savedProduct = productRepository.findById(product.getProductId()).orElse(null);
            assertNotNull(savedProduct);
            assertEquals(String.format("A05%02d", i + 1), savedProduct.getProductId());
            assertEquals(price, savedProduct.getProductPrice());
            assertEquals(explain, savedProduct.getProductExplain());
        }
    }
    
    @Test
    void testCreateProductsInCategoryA06() {
        // given
        String categoryCode = "A06";
        int[] prices = {5900, 5900, 5900};  // 가격 배열
        String[] explanations = {
            "청포도의 상픔함과 유자청의 달콤쌉싸름한 맛의 조화로 에너지 부스트를 주는 청량한 과일 리프레셔 음료 취향에 따라 플래식 시럽을 무료로 추가 하실 수 있습니다.\r\n"
            + "제품 영양 정보 : 1회 제공량(kcal) 140 / 나트륨(mg) 25 / 포화지방(g) 0 / 당류(g) 32 / 단백질(g) 0 / 카페인(mg) 24",
            "딸기, 아사이베리 주스와 레모네이드가 달콤 상큼하게 조화된 맛에 가볍게 에너지 부스팅을 할 수 있는 리프레셔 음료\r\n"
            + "제품 영양 정보 : 1회 제공량(kcal) 105 / 나트륨(mg) 0 / 포화지방(g) 0 / 당류(g) 24 / 단백질(g) 1 / 카페인(mg) 30",
            "망고 용과와 레모네이드가 달콤 상큼하게 조화된 맛에 가볍게 에너지 부스팅을 할 수 있는 음료\r\n"
            + "제품 영양 정보 : 1회 제공량(kcal) 95 / 나트륨(mg) 35 / 포화지방(g) 0 / 당류(g) 23 / 단백질(g) 0 / 카페인(mg) 25"
        };
        String[] productNames = {
            "청포도 유자 레모네이드 리프레셔", "딸기 아사이 레모네이드 스타벅스 리프레셔", "망고 용과 레모네이드 스타벅스 리프레셔"
        };

        // when
        for (int i = 0; i < productNames.length; i++) {
            String productName = productNames[i];
            int price = prices[i];
            String explain = explanations[i];

            Product product = productService.createProduct(categoryCode, productName, price, explain);

            // then
            assertNotNull(product);
            assertEquals(String.format("A06%02d", i + 1), product.getProductId());
            assertEquals(productName, product.getProductName());
            assertEquals(price, product.getProductPrice());
            assertEquals(explain, product.getProductExplain());

            // 데이터베이스에 실제로 저장되었는지 확인
            Product savedProduct = productRepository.findById(product.getProductId()).orElse(null);
            assertNotNull(savedProduct);
            assertEquals(String.format("A06%02d", i + 1), savedProduct.getProductId());
            assertEquals(price, savedProduct.getProductPrice());
            assertEquals(explain, savedProduct.getProductExplain());
        }
    }
    
    @Test
    void testCreateProductsInCategoryA07() {
        // given
        String categoryCode = "A07";
        int[] prices = {5700, 9500, 5900};  // 가격 배열
        String[] explanations = {
            "길고 깊은 강의 모습과 달콤한 복숭아가 만나 강의 뷰를 즐기며 힐링할 수 있는 상큼한 피지오\r\n"
            + "제품 영양 정보 : 1회 제공량(kcal) 155 / 나트륨(mg) 45 / 포화지방(g) 0 / 당류(g) 22 / 단백질(g) 0 / 카페인(mg) 0",
            "여수 돌산대교의 밤바다 풍경을 형상화한 음료로 알록달록 생상 조명의 토핑과 함께 여수의 풍경을 바라보며 즐길 수 있는 트로피컬 맛의 음료\r\n"
            + "제품 영양 정보 : 1회 제공량(kcal) 250 / 나트륨(mg) 105 / 포화지방(g) 0 / 당류(g) 45 / 단백질(g) 1 / 카페인(mg) 0",
            "그린 빈 추출액이 들어간 라임 베이스에 건조된 라임 슬라이스를 넣고 스파클링한 시원하고 청랑감 있는 음료입니다. (카페인이 함유된 탄산음료입니다)\r\n"
            + "제품 영양 정보 : 1회 제공량(kcal) 105 / 나트륨(mg) 20 / 포화지방(g) 0 / 당류(g) 25 / 단백질(g) 0 / 카페인(mg) 110"
        };
        String[] productNames = {
            "리버 피치 피지오", "여수 바다 자몽 피지오", "쿨 라임 피지오"
        };

        // when
        for (int i = 0; i < productNames.length; i++) {
            String productName = productNames[i];
            int price = prices[i];
            String explain = explanations[i];

            Product product = productService.createProduct(categoryCode, productName, price, explain);

            // then
            assertNotNull(product);
            assertEquals(String.format("A07%02d", i + 1), product.getProductId());
            assertEquals(productName, product.getProductName());
            assertEquals(price, product.getProductPrice());
            assertEquals(explain, product.getProductExplain());

            // 데이터베이스에 실제로 저장되었는지 확인
            Product savedProduct = productRepository.findById(product.getProductId()).orElse(null);
            assertNotNull(savedProduct);
            assertEquals(String.format("A07%02d", i + 1), savedProduct.getProductId());
            assertEquals(price, savedProduct.getProductPrice());
            assertEquals(explain, savedProduct.getProductExplain());
        }
    }
    
    @Test
    void testCreateProductsInCategoryA08() {
        // given
        String categoryCode = "A08";
        int[] prices = {4100, 4100, 4100, 4100, 4900, 4100, 4100};  // 가격 배열
        String[] explanations = {
            "꽃향 가득한 라벤더와 베르가못 향이 진한 홍차와 블렌딩된 향긋한 블랙 티\r\n"
            + "제품 영양 정보 : 1회 제공량(kcal) 0 / 나트륨(mg) 0 / 포화지방(g) 0 / 당류(g) 0 / 단백질(g) 0 / 카페인(mg) 70",
            "제주산 찻잎으로 만든 황차에 사과, 망고, 파인애플, 히비스커스, 로즈힙 등이 블렌딩되어 핑크빛 컬러가 감도는 수색과 베리류의 새콤함을 느낄 수 있는 옐로우 티\r\n"
            + "제품 영양 정보 : 1회 제공량(kcal) 0 / 나트륨(mg) 0 / 포화지방(g) 0 / 당류(g) 0 / 단백질(g) 0 / 카페인(mg) 20",
            "인도 아삼, 제주도 유기농 홍차가 블렌딩되어 진한 벌꿀향과 그윽한 몰트향이 특징인 블랙 티\r\n"
            + "제품 영양 정보 : 1회 제공량(kcal) 0 / 나트륨(mg) 0 / 포화지방(g) 0 / 당류(g) 0 / 단백질(g) 0 / 카페인(mg) 70",
            "새콤한 자몽과 달콤한 꿀이 깊고 그윽한 풍미의 스타벅스 티바나 블랙 티의 조화\r\n"
            + "제품 영양 정보 : 1회 제공량(kcal) 125 / 나트륨(mg) 5 / 포화지방(g) 0 / 당류(g) 30 / 단백질(g) 0 / 카페인(mg) 70",
            "유기농 녹차 티백만을 100%(물 제외) 사용한 티로 맑은 수색과 고유의 풍미가 뛰어난 녹차\r\n"
            + "제품 영양 정보 : 1회 제공량(kcal) 0 / 나트륨(mg) 0 / 포화지방(g) 0 / 당류(g) 0 / 단백질(g) 0 / 카페인(mg) 16",
            "캐모마일과 레몬 그라스, 레몬밤, 히비스커스 등 블렌딩되어 은은하고 차분한 향이 기분을 좋게하는 허브 티\r\n"
            + "제품 영양 정보 : 1회 제공량(kcal) 0 / 나트륨(mg) 0 / 포화지방(g) 0 / 당류(g) 0 / 단백질(g) 0 / 카페인(mg) 0",
            "히비스커스, 사과. 파파야, 망고, 레몬그라스 등이 블렌딩된 상큼한 허브 티\r\n"
            + "제품 영양 정보 : 1회 제공량(kcal) 0 / 나트륨(mg) 0 / 포화지방(g) 0 / 당류(g) 0 / 단백질(g) 0 / 카페인(mg) 0"
        };
        String[] productNames = {
            "얼 그레이 티", "유스베리 티", "잉글리쉬 브렉퍼스트 티", "자몽 허니 블랙 티", "제주 유기농 녹차로 만든 티",
            "캐모마일 블렌드 티", "히비스커스 블렌드 티"
        };

        // when
        for (int i = 0; i < productNames.length; i++) {
            String productName = productNames[i];
            int price = prices[i];
            String explain = explanations[i];

            Product product = productService.createProduct(categoryCode, productName, price, explain);

            // then
            assertNotNull(product);
            assertEquals(String.format("A08%02d", i + 1), product.getProductId());
            assertEquals(productName, product.getProductName());
            assertEquals(price, product.getProductPrice());
            assertEquals(explain, product.getProductExplain());

            // 데이터베이스에 실제로 저장되었는지 확인
            Product savedProduct = productRepository.findById(product.getProductId()).orElse(null);
            assertNotNull(savedProduct);
            assertEquals(String.format("A08%02d", i + 1), savedProduct.getProductId());
            assertEquals(price, savedProduct.getProductPrice());
            assertEquals(explain, savedProduct.getProductExplain());
        }
    }
    
    @Test
    void testCreateProductsInCategoryA09() {
        // given
        String categoryCode = "A09";
        int[] prices = {8400, 5700, 9400, 8400, 6500};  // 가격 배열
        String[] explanations = {
            "딸기 풍미 가득한 스타벅스만의 딸기 콜드폼과 은은한 초콜릿의 향으로 남녀노소 즐길 수 있는 음료\r\n"
            + "제품 영양 정보 : 1회 제공량(kcal) 255 / 나트륨(mg) 110 / 포화지방(g) 2 / 당류(g) 45 / 단백질(g) 7 / 카페인(mg) 5\r\n"
            + "알레르기 유발요인 : 우유",
            "깊고 진한 초콜릿과 부드러운 휘핑크림이 입안에서 사르르 녹는 초콜릿 음료\r\n"
            + "제품 영양 정보 : 1회 제공량(kcal) 410 / 나트륨(mg) 135 / 포화지방(g) 15 / 당류(g) 39 / 단백질(g) 13 / 카페인(mg) 15\r\n"
            + "알레르기 유발요인 : 우유",
            "리저브 다크 초콜릿을 활용하여 초콜릿 풍미 가득한 디저트 타입의 티라미수 초콜릿\r\n"
            + "제품 영양 정보 : 1회 제공량(kcal) 420 / 나트륨(mg) 200 / 포화지방(g) 14 / 당류(g) 41 / 단백질(g) 11 / 카페인(mg) 10\r\n"
            + "알레르기 유발요인 : 우유",
            "딸기 풍미 가득한 스타벅스만의 딸기 콜드폼과 딸기 본연의 맛을 즐길 수 있는 음료\r\n"
            + "제품 영양 정보 : 1회 제공량(kcal) 235 / 나트륨(mg) 110 / 포화지방(g) 2 / 당류(g) 41 / 단백질(g) 6 / 카페인(mg) 0\r\n"
            + "알레르기 유발요인 : 우유",
            "스트로베리와 아사이베리의 상큼, 달콤한 맛이 톡톡! 시원하고 통쾌한 그랜드 슬램을 위한 에너지 부스팅 음료~!\r\n"
            + "제품 영양 정보 : 1회 제공량(kcal) 235 / 나트륨(mg) 0 / 포화지방(g) 0 / 당류(g) 55 / 단백질(g) 0 / 카페인(mg) 0"
        };
        String[] productNames = {
            "딸기 콜드폼 초콜릿", "시그니처 핫 초콜릿", "티라미수 초콜릿", "딸기 콜드폼 딸기 라떼", "스타벅스 슬래머"
        };

        // when
        for (int i = 0; i < productNames.length; i++) {
            String productName = productNames[i];
            int price = prices[i];
            String explain = explanations[i];

            Product product = productService.createProduct(categoryCode, productName, price, explain);

            // then
            assertNotNull(product);
            assertEquals(String.format("A09%02d", i + 1), product.getProductId());
            assertEquals(productName, product.getProductName());
            assertEquals(price, product.getProductPrice());
            assertEquals(explain, product.getProductExplain());

            // 데이터베이스에 실제로 저장되었는지 확인
            Product savedProduct = productRepository.findById(product.getProductId()).orElse(null);
            assertNotNull(savedProduct);
            assertEquals(String.format("A09%02d", i + 1), savedProduct.getProductId());
            assertEquals(price, savedProduct.getProductPrice());
            assertEquals(explain, savedProduct.getProductExplain());
        }
    }
    
    @Test
    void testCreateProductsInCategoryA10() {
        // given
        String categoryCode = "A10";
        int[] prices = {3800, 4300, 4300, 3800, 4500, 4500, 4300};  // 가격 배열
        String[] explanations = {
            "과일 및 채소를 착즙하여 만든 주스! 사과, 비트, 당근이 들어가 과일 & 채소를 달콤하게 즐길 수 있는 음료\r\n"
            + "제품 영양 정보 : 1회 제공량(kcal) 90 / 나트륨(mg) 45 / 탄수화물(g) 20 / 당류(g) 16 / 지방(g) 0.7 / 트랜스지방(g) 0 / 포화지방(g) 0.1 / 콜레스테롤(mg) 0 / 단백질(g) 1",
            "과일 및 채소를 착즙하여 만든 주스! 레몬, 생강이 들어가 과일 & 채소를 상큼하게 즐길 수 있는 음료\r\n"
            + "1회 제공량(kcal) 100 / 나트륨(mg) 0 / 탄수화물(g) 22 / 당류(g) 16 / 지방(g) 0.9 / 트랜스지방(g) 0 / 포화지방(g) 0.1 / 콜레스테롤(mg) 0 / 단백질(g) 1",
            "과일 및 채소를 착즙하여 만든 주스! 케일, 셀러리, 오이가 들어가 과일 & 채소를 건강하게 즐길 수 있는 음료\r\n"
            + "1회 제공량(kcal) 90 / 나트륨(mg) 15 / 탄수화물(g) 19 / 당류(g) 15 / 지방(g) 0.9 / 트랜스지방(g) 0 / 포화지방(g) 0.2 / 콜레스테롤(mg) 0 / 단백질(g) 1",
            "달콤한 국내산 딸기의 과육이 듬뿍 느껴지는 주스\r\n"
            + "1회 제공량(kcal) 110 / 나트륨(mg) 5 / 탄수화물(g) 27 / 당류(g) 25 / 지방(g) 0 / 트랜스지방(g) 0 / 포화지방(g) 0 / 콜레스테롤(mg) 0 / 단백질(g) 1",
            "노랗게 익은 열대과일 망고가 입안 가득 느껴지는 주스\r\n"
            + "1회 제공량(kcal) 100 / 나트륨(mg) 0 / 탄수화물(g) 22 / 당류(g) 16 / 지방(g) 0.9 / 트랜스지방(g) 0 / 포화지방(g) 0.1 / 콜레스테롤(mg) 0 / 단백질(g) 1",
            "프리미엄 유기농 오렌지 주스로 스페인산 오렌지 착즙! 오렌지 그대로 100% 담긴 3개 분량 물, 설탕, 첨가물 없는 오렌지 그대로의 맛\r\n"
            + "1회 제공량(kcal) 90 / 나트륨(mg) 0 / 탄수화물(g) 20 / 당류(g) 19 / 지방(g) 0.6 / 트랜스지방(g) 0 / 포화지방(g) 0 / 콜레스테롤(mg) 0 / 단백질(g) 1",
            "새콤달콤한 황금빛 제주 한라봉을 그대로 담아낸 주스\r\n"
            + "1회 제공량(kcal) 88 / 나트륨(mg) 10 / 탄수화물(g) 21 / 당류(g) 20 / 지방(g) 0 / 트랜스지방(g) 0 / 포화지방(g) 0.1 / 콜레스테롤(mg) 0 / 단백질(g) 1"
        };
        String[] productNames = {
            "ABC 클렌즈 190ML", "레몬 진저 클렌즈 190ML", "케일 클렌즈 190ML", "딸기주스 190ML", "망고주스 190ML",
            "유기농 오렌지 100% 주스 190ML", "한라봉주스 190ML"
        };

        // when
        for (int i = 0; i < productNames.length; i++) {
            String productName = productNames[i];
            int price = prices[i];
            String explain = explanations[i];

            Product product = productService.createProduct(categoryCode, productName, price, explain);

            // then
            assertNotNull(product);
            assertEquals(String.format("A10%02d", i + 1), product.getProductId());
            assertEquals(productName, product.getProductName());
            assertEquals(price, product.getProductPrice());
            assertEquals(explain, product.getProductExplain());

            // 데이터베이스에 실제로 저장되었는지 확인
            Product savedProduct = productRepository.findById(product.getProductId()).orElse(null);
            assertNotNull(savedProduct);
            assertEquals(String.format("A10%02d", i + 1), savedProduct.getProductId());
            assertEquals(price, savedProduct.getProductPrice());
            assertEquals(explain, savedProduct.getProductExplain());
        }
    }
    
    @Test
    void testCreateProductsInCategoryB01() {
        // given
        String categoryCode = "B01";
        int[] prices = {3300, 3300, 4500, 3900, 5100, 3400, 5800};  // 가격 배열
        String[] explanations = {
            "탕종법으로 반죽한 후 고온에서 데치고, 짧게 구워 내 쫄깃하고 촉촉한 식감이 특징인 플레인 베이글입니다.\r\n"
            + "제품 영양 정보 : 1회 제공량(kcal) 90 / 나트륨(mg) 600 / 탄수화물(g) 45 / 지방(g) 3.0 / 트렌스지방(g) 0.5 / 포화지방(g) 1.9 / 콜레스테롤(mg) 0 / 단백질(g) 7\r\n"
            + "알레르기 유발요인 : 대두 / 우유 / 밀",
            "프상스산 고급 버터로 만든 담백한 스콘입니다.\r\n"
            + "제품 영양 정보 : 1회 제공량(kcal) 481 / 나트륨(mg) 448 / 탄수화물(g) 57 / 지방(g) 25 / 트렌스지방(g) 0.6 / 포화지방(g) 16 / 콜레스테롤(mg) 68 / 단백질(g) 7\r\n"
            + "알레르기 유발요인 : 달걀 / 우유 / 밀",
            "돌돌 말아 올린 버터 풍미 가득한 빵에 부드러운 바닐라 마스카포네 크림을 넣어 시원하게 즐기는 소라빵입니다.\r\n"
            + "제품 영양 정보 : 1회 제공량(kcal) 218 / 나트륨(mg) 188 / 탄수화물(g) 28 / 당류(g) 8 / 지방(g) 10 / 트렌스지방(g) 0.2 / 포화지방(g) 6 / 콜레스테롤(mg) 35 / 단백질(g) 4\r\n"
            + "알레르기 유발요인 : 달걀 / 우유 / 밀",
            "버터 풍미가 가득한 나뭇잎 모양의 바삭 바삭한 파이입니다.\r\n"
            + "제품 영양 정보 : 1회 제공량(kcal) 212 / 나트륨(mg) 131 / 탄수화물(g) 30 / 당류(g) 15 / 지방(g) 9 / 트렌스지방(g) 0.3 / 포화지방(g) 5 / 콜레스테롤(mg) 17 / 단백질(g) 2\r\n"
            + "알레르기 유발요인 : 대두 / 우유 / 밀",
            "바삭하면서도 속은 부드러운 데니쉬 안에 초콜릿 필링을 넣어 담백하면서도 달콤한 맛이 느껴지는 빵입니다.\r\n"
            + "제품 영양 정보 : 1회 제공량(kcal) 295 / 나트륨(mg) 380 / 탄수화물(g) 28 / 당류(g) 8 / 지방(g) 18 / 트렌스지방(g) 0.5 / 포화지방(g) 10 / 콜레스테롤(mg) 37 / 단백질(g) 5\r\n"
            + "알레르기 유발요인 : 달걀 / 우유 / 밀",
            "버터를 롤링한 반죽 위에 소금이 뿌려져 있어 고소하면서도 짭짤한 맛을 동시에 느끼실 수 있으며 쫄깃한 식감이 매력적인 빵입니다.\r\n"
            + "제품 영양 정보 : 1회 제공량(kcal) 213 / 나트륨(mg) 496 / 탄수화물(g) 28 / 당류(g) 4 / 지방(g) 9 / 트렌스지방(g) 0.0 / 포화지방(g) 5 / 콜레스테롤(mg) 13 / 단백질(g) 5\r\n"
            + "알레르기 유발요인 : 우유 / 밀",
            "버터 풍미 가득한 프레첼 소금빵에 훈제된 도톰한 소시지와 홀그레인 머스터드가 맛의 조화를 이룬 든든한 간식용 소시지 브레드 입니다.\r\n"
            + "제품 영양 정보 : 1회 제공량(kcal) 473 / 나트륨(mg) 1147 / 탄수화물(g) 38 / 당류(g) 3 / 지방(g) 29 / 트렌스지방(g) 0.4 / 포화지방(g) 12 / 콜레스테롤(mg) 36 / 단백질(g) 15\r\n"
            + "알레르기 유발요인 : 달걀 /  대두 / 우유 / 밀"
        };
        String[] productNames = {
            "탕종 플레인 베이글", "클래식 스콘", "마스카포네 크림 소라빵", "미니 리프 파이", "뺑 오 쇼콜라",
            "소금빵", "소시지 프레첼 소금빵"
        };

        // when
        for (int i = 0; i < productNames.length; i++) {
            String productName = productNames[i];
            int price = prices[i];
            String explain = explanations[i];

            Product product = productService.createProduct(categoryCode, productName, price, explain);

            // then
            assertNotNull(product);
            assertEquals(String.format("B01%02d", i + 1), product.getProductId());
            assertEquals(productName, product.getProductName());
            assertEquals(price, product.getProductPrice());
            assertEquals(explain, product.getProductExplain());

            // 데이터베이스에 실제로 저장되었는지 확인
            Product savedProduct = productRepository.findById(product.getProductId()).orElse(null);
            assertNotNull(savedProduct);
            assertEquals(String.format("B01%02d", i + 1), savedProduct.getProductId());
            assertEquals(price, savedProduct.getProductPrice());
            assertEquals(explain, savedProduct.getProductExplain());
        }
    }
    
    @Test
    void testCreateProductsInCategoryB02() {
        // given
        String categoryCode = "B02";
        int[] prices = {5700, 5900, 5900, 4500, 7500, 4600, 36500};  // 가격 배열
        String[] explanations = {
            "초콜릿, 가나슈, 모캌로 만든 시트와 크림이 7개의 층을 이루어 모양부터 매력적인 케이크입니다.\r\n"
            + "제품 영양 정보 : 1회 제공량(kcal) 576 / 나트륨(mg) 163 / 탄수화물(g) 47 / 당류(g) 34 / 지방(g) 40 / 트렌스지방(g) 0.6 / 포화지방(g) 22\r\n"
            + "알레르기 유발요인 : 달걀 / 대두 / 우유 / 밀",
            "달콤한 초콜릿 케이크 시트에 진한 가나슈 생크림을 넣고 측면에 다크 초콜릿을 듬뿍 토핑한 달콤하고 촉촉한 초콜릿 케이크입니다.\r\n"
            + "제품 영양 정보 : 1회 제공량(kcal) 456 / 나트륨(mg) 140 / 탄수화물(g) 35 / 당류(g) 23 / 지방(g) 32 / 트렌스지방(g) 0.3 / 포화지방(g) 16 / 콜레스테롤(mg) 127 / 단백질(g) 7\r\n"
            + "알레르기 유발요인 : 달걀 / 대두 / 우유 / 밀",
            "고소한 마스카포네 치즈 크림에 촉촉한 커피 시트가 입안을 감싸는 기분 좋은 느낌의 떠먹는 티라미수입니다.\r\n"
            + "제품 영양 정보 : 1회 제공량(kcal) 218 / 나트륨(mg) 188 / 탄수화물(g) 28 / 당류(g) 8 / 지방(g) 10 / 트렌스지방(g) 0.2 / 포화지방(g) 6 / 콜레스테롤(mg) 35 / 단백질(g) 4\r\n"
            + "알레르기 유발요인 : 달걀 / 우유 / 밀",
            "부드러운 생크림이 듬뿍 들어있는 촉촉한 카스텔라입니다.\r\n"
            + "제품 영양 정보 : 1회 제공량(kcal) 544 / 나트륨(mg) 215 / 탄수화물(g) 65 / 당류(g) 42 / 지방(g) 28 / 트렌스지방(g) 0.5 / 포화지방(g) 13 / 콜레스테롤(mg) 174 / 단백질(g) 8\r\n"
            + "알레르기 유발요인 : 달걀 / 대두 / 우유 / 밀",
            "얇게 구워낸 크레이프에 달콤하고 고소한 커스터드 생크림을 얇게 발라 겹겹이 쌓은 후 돌돌 말아낸 크레이프 롤 케이크입니다.\r\n"
            + "제품 영양 정보 : 1회 제공량(kcal) 432 / 나트륨(mg) 205 / 탄수화물(g) 39 / 당류(g) 21 / 지방(g) 28 / 트렌스지방(g) 0.4 / 포화지방(g) 18 / 콜레스테롤(mg) 131 / 단백질(g) 6\r\n"
            + "알레르기 유발요인 : 달걀 /  대두 /우유 / 밀",
            "간편하게 즐길 수 있는 얇고 길쭉한 형태의 달콤하고 고소한 맛의 메그 타르트입니다.\r\n"
            + "제품 영양 정보 : 1회 제공량(kcal) 313 / 나트륨(mg) 168 / 탄수화물(g) 34 / 당류(g) 8 / 지방(g) 17 / 트렌스지방(g) 0.4 / 포화지방(g) 10 / 콜레스테롤(mg) 97 / 단백질(g) 6\r\n"
            + "알레르기 유발요인 : 달걀 / 우유 / 밀",
            "타르트 시트에 달콤한 필링과 고소한 피칸을 돔 형태로 듬뿍 올려 구워낸 피칸 타르트 입니다.(지름 15cm / 중량 630g)\r\n"
            + "제품 영양 정보 : 1회 제공량(kcal) 435 / 나트륨(mg) 160 / 탄수화물(g) 47 / 당류(g) 29 / 지방(g) 25 / 트렌스지방(g) 0.5 / 포화지방(g) 6 / 콜레스테롤(mg) 70 / 단백질(g) 6\r\n"
            + "알레르기 유발요인 : 달걀 / 우유 / 밀"
        };
        String[] productNames = {
            "7 레이어 가나슈 케이크", "The 촉촉 초콜릿 생크림 케이크", "마스카포네 티라미수 케이크", "부드러운 생크림 카스텔라", "생크림 크레이프 롤",
            "스틱 에그 타르트", "돔 피칸 타르트"
        };

        // when
        for (int i = 0; i < productNames.length; i++) {
            String productName = productNames[i];
            int price = prices[i];
            String explain = explanations[i];

            Product product = productService.createProduct(categoryCode, productName, price, explain);

            // then
            assertNotNull(product);
            assertEquals(String.format("B02%02d", i + 1), product.getProductId());
            assertEquals(productName, product.getProductName());
            assertEquals(price, product.getProductPrice());
            assertEquals(explain, product.getProductExplain());

            // 데이터베이스에 실제로 저장되었는지 확인
            Product savedProduct = productRepository.findById(product.getProductId()).orElse(null);
            assertNotNull(savedProduct);
            assertEquals(String.format("B02%02d", i + 1), savedProduct.getProductId());
            assertEquals(price, savedProduct.getProductPrice());
            assertEquals(explain, savedProduct.getProductExplain());
        }
    }
    
    @Test
    void testCreateProductsInCategoryB03() {
        // given
        String categoryCode = "B03";
        int[] prices = {6900, 5900, 5500, 4900, 4400, 6200, 6900};  // 가격 배열
        String[] explanations = {
            "바삭하게 구워진 사워도우 사이 진한 브라운 소스에 버무린 케러멜라이징 어니언에 고소함을 더해줄 우유와 버터를 끓여 만든 화이트 베샤멜 소스를 바르고 햄과 모짜렐라 치즈로 감칠맛을 더한 샌드위치입니다.\r\n"
            + "제품 영양 정보 : 1회 제공량(kcal) 439 / 나트륨(mg) 831 / 탄수화물(g) 47 / 당류(g) 5 / 지방(g) 9 / 트렌스지방(g) 0.3 / 포화지방(g) 4.7 / 콜레스테롤(mg) 32 / 단백질(g) 20\r\n"
            + "알레르기 유발요인 : 달걀 /  대두 / 우유 / 밀",
            "주 재료인 베이컨, 계란, 로메인 상추, 토마토의 각각의 머리 글자를 따온 이름의 샌드위치입니다.\r\n"
            + "제품 영양 정보 : 1회 제공량(kcal) 448 / 나트륨(mg) 976 / 탄수화물(g) 47 / 당류(g) 5 / 지방(g) 20 / 트렌스지방(g) 0.2 / 포화지방(g) 7 / 콜레스테롤(mg) 41 / 단백질(g) 20\r\n"
            + "알레르기 유발요인 : 달걀 /  대두 /우유 / 밀",
            "곡물 식빵에 달콤하고 고소한 단호박 에그 샐러드와 신선한 로메인, 양상추를 더해 가볍고 건강하게 즐기는 샌드위치입니다.\r\n"
            + "제품 영양 정보 : 1회 제공량(kcal) 510 / 나트륨(mg) 630 / 탄수화물(g) 60 / 당류(g) 8 / 지방(g) 24 / 트렌스지방(g) 0 / 포화지방(g) 7 / 콜레스테롤(mg) 85 / 단백질(g) 14\r\n"
            + "알레르기 유발요인 : 달걀 / 대두 / 우유 / 밀",
            "계란과 우유를 적신 빵에 베이컨과 치즈, 에그 스프레드를 넣어 구운 프렌치 토스트 타입 샌드위치입니다.\r\n"
            + "제품 영양 정보 : 1회 제공량(kcal) 337 / 나트륨(mg) 635 / 탄수화물(g) 23 / 당류(g) 9 / 지방(g) 21 / 트렌스지방(g) 0.2 / 포화지방(g) 8 / 콜레스테롤(mg) 109 / 단백질(g) 14\r\n"
            + "알레르기 유발요인 : 달걀 / 대두 / 우유 / 밀",
            "화이트 식빵 사이에 고소한 에그 스프레드를 넣은 부드러운 샌드위치입니다.\r\n"
            + "제품 영양 정보 : 1회 제공량(kcal) 364 / 나트륨(mg) 657 / 탄수화물(g) 31 / 당류(g) 5 / 지방(g) 20 / 트렌스지방(g) 0.1 / 포화지방(g) 5 / 콜레스테롤(mg) 277 / 단백질(g) 15\r\n"
            + "알레르기 유발요인 : 달걀 / 대두 / 우유 / 밀",
            "겉 테두리가 없는 식빵 사이에 모차렐라 치즈와 국내산 포크 커틀릿을 넣어 만든 샌드위치입니다.\r\n"
            + "제품 영양 정보 : 1회 제공량(kcal) 729 / 나트륨(mg) 1412 / 탄수화물(g) 56 / 당류(g) 11 / 지방(g) 45 / 트렌스지방(g) 0.3 / 포화지방(g) 12 / 콜레스테롤(mg) 49 / 단백질(g) 25\r\n"
            + "알레르기 유발요인 : 달걀 / 대두 / 우유 / 밀",
            "닭가슴살, 베이컨, 계란, 토마토를 넣어 든든하게 즐길 수 있는 클래식한 샌드위치입니다.\r\n"
            + "제품 영양 정보 : 1회 제공량(kcal) 411 / 나트륨(mg) 993 / 탄수화물(g) 36 / 당류(g) 2 / 지방(g) 19 / 트렌스지방(g) 0.1 / 포화지방(g) 6 / 콜레스테롤(mg) 61 / 단백질(g) 24\r\n"
            + "알레르기 유발요인 : 달걀 / 대두 / 우유 / 밀"
        };
        String[] productNames = {
            "멜팅 치즈 햄, 어니언 샌드위치", "BELT샌드위치", "단호박 에그 샐러드 샌드위치", "베이컨 치즈 토스트", "에그에그 샌드위치",
            "치즈 포크 커틀릿 샌드위치", "치킨 클래식 샌드위치"
        };

        // when
        for (int i = 0; i < productNames.length; i++) {
            String productName = productNames[i];
            int price = prices[i];
            String explain = explanations[i];

            Product product = productService.createProduct(categoryCode, productName, price, explain);

            // then
            assertNotNull(product);
            assertEquals(String.format("B03%02d", i + 1), product.getProductId());
            assertEquals(productName, product.getProductName());
            assertEquals(price, product.getProductPrice());
            assertEquals(explain, product.getProductExplain());

            // 데이터베이스에 실제로 저장되었는지 확인
            Product savedProduct = productRepository.findById(product.getProductId()).orElse(null);
            assertNotNull(savedProduct);
            assertEquals(String.format("B03%02d", i + 1), savedProduct.getProductId());
            assertEquals(price, savedProduct.getProductPrice());
            assertEquals(explain, savedProduct.getProductExplain());
        }
    }
    
    @Test
    void testCreateProductsInCategoryB04() {
        // given
        String categoryCode = "B04";
        int[] prices = {4200, 4500};  // 가격 배열
        String[] explanations = {
            "송로 버섯의 향과 양송이 버섯의 진한 풍미가 조화로운 수프입니다. *수프 구매 시 통밀 토스트칩이 함께 제공됩니다.\r\n"
            + "제품 영양 정보 : 1회 제공량(kcal) 164 / 나트륨(mg) 422 / 탄수화물(g) 11 / 당류(g) 3 / 지방(g) 12 / 트렌스지방(g) 0.1 / 포화지방(g) 8 / 콜레스테롤(mg) 9 / 단백질(g) 3\r\n"
            + "알레르기 유발요인 : 우유 / 밀 / 땅콩",
            "잘게 다진 양파와 식물성 재료로 만든 플랜트 미트볼이 틀어있는 새콤달콤한 맛의 토마토 수프입니다. *수프 구매 시 통밀 토스트칩이 함께 제공됩니다.\r\n"
            + "제품 영양 정보 : 1회 제공량(kcal) 222 / 나트륨(mg) 930 / 탄수화물(g) 16 / 당류(g) 7 / 지방(g) 14 / 트렌스지방(g) 0 / 포화지방(g) 1.9 / 콜레스테롤(mg) 0 / 단백질(g) 8\r\n"
            + "알레르기 유발요인 : 대두 / 우유 / 밀"
        };
        String[] productNames = {
            "트러플 머쉬룸 수프", "플랜트 미트볼 수프"
        };

        // when
        for (int i = 0; i < productNames.length; i++) {
            String productName = productNames[i];
            int price = prices[i];
            String explain = explanations[i];

            Product product = productService.createProduct(categoryCode, productName, price, explain);

            // then
            assertNotNull(product);
            assertEquals(String.format("B04%02d", i + 1), product.getProductId());
            assertEquals(productName, product.getProductName());
            assertEquals(price, product.getProductPrice());
            assertEquals(explain, product.getProductExplain());

            // 데이터베이스에 실제로 저장되었는지 확인
            Product savedProduct = productRepository.findById(product.getProductId()).orElse(null);
            assertNotNull(savedProduct);
            assertEquals(String.format("B04%02d", i + 1), savedProduct.getProductId());
            assertEquals(price, savedProduct.getProductPrice());
            assertEquals(explain, savedProduct.getProductExplain());
        }
    }
    
    @Test
    void testCreateProductsInCategoryB05() {
        // given
        String categoryCode = "B05";
        int[] prices = {2600, 4200, 3300, 3300, 3300};  // 가격 배열
        String[] explanations = {
            "국내산 사과와 대추 방울 토마토를 한 컵에 담아 언제 어디서나 편하게 즐길 수 있습니다.",
            "유기농 우유와 유산균으로 만든 정통 그릭 방식의 플레인 요거트입니다.\r\n"
            + "제품 영양 정보 : 1회 제공량(kcal) 93.5 / 나트륨(mg) 63.3 / 탄수화물(g) 7.9 / 당류(g) 4.5 / 지방(g) 4.6 / 트렌스지방(g) 0 / 포화지방(g) 3.2 / 콜레스테롤(mg) 20.3 / 단백질(g) 5.1 / 칼슘(mg)178.7\r\n"
            + "알레르기 유발요인 : 우유",
            "신선한 우유와 국내산 달걀을 넣어 만든 부드럽고 달콤한 밀크 푸딩입니다. 냉장보관이 필요한 상품입니다.\r\n"
            + "제품 영양 정보 : 1회 제공량(kcal) 127 / 나트륨(mg) 55 / 탄수화물(g) 11 / 당류(g) 10 / 지방(g) 7 / 트렌스지방(g) 0 / 포화지방(g) 5 / 콜레스테롤(mg) 32 / 단백질(g) 5\r\n"
            + "알레르기 유발요인 : 달걀 / 대두 / 우유",
            "제주 녹차의 깊은 맛을 부드럽게 즐길 수 있는 녹차 푸딩입니다. 냉장 보관이 필요한 상품입니다.\r\n"
            + "제품 영양 정보 : 1회 제공량(kcal) 139 / 나트륨(mg) 56 / 탄수화물(g) 19 / 당류(g) 16 / 지방(g) 5 / 트렌스지방(g) 0 / 포화지방(g) 4 / 콜레스테롤(mg) 19 / 단백질(g) 5\r\n"
            + "알레르기 유발요인 : 달걀 / 대두 / 우유",
            "벨기에 초콜릿과 국내산 달걀, 신선한 우유로 만든 진한 초콜릿 푸딩입니다. 냉장보관이 필요한 상품입니다.\r\n"
            + "제품 영양 정보 : 1회 제공량(kcal) 182 / 나트륨(mg) 37 / 탄수화물(g) 18 / 당류(g) 15 / 지방(g) 10 / 트렌스지방(g) 0 / 포화지방(g) 6 / 콜레스테롤(mg) 46 / 단백질(g) 5\r\n"
            + "알레르기 유발요인 : 달걀 / 대두 / 우유"
        };
        String[] productNames = {
            "하루 한 컵 RED+", "오가닉 그릭 요커트 플레인", "밀크 푸딩", "제주 녹차 푸딩", "초콜릿 푸딩"
        };

        // when
        for (int i = 0; i < productNames.length; i++) {
            String productName = productNames[i];
            int price = prices[i];
            String explain = explanations[i];

            Product product = productService.createProduct(categoryCode, productName, price, explain);

            // then
            assertNotNull(product);
            assertEquals(String.format("B05%02d", i + 1), product.getProductId());
            assertEquals(productName, product.getProductName());
            assertEquals(price, product.getProductPrice());
            assertEquals(explain, product.getProductExplain());

            // 데이터베이스에 실제로 저장되었는지 확인
            Product savedProduct = productRepository.findById(product.getProductId()).orElse(null);
            assertNotNull(savedProduct);
            assertEquals(String.format("B05%02d", i + 1), savedProduct.getProductId());
            assertEquals(price, savedProduct.getProductPrice());
            assertEquals(explain, savedProduct.getProductExplain());
        }
    }
    
    @Test
    void testCreateProductsInCategoryB06() {
        // given
        String categoryCode = "B06";
        int[] prices = {2800, 3500, 3300, 2700, 2700, 3800, 1100};  // 가격 배열
        String[] explanations = {
            "진한 다크 초콜릿과 고소한 피칸이 들어있는 쿠키입니다.\r\n"
            + "제품 영양 정보 : 1회 제공량(kcal) 465\r\n"
            + "알레르기 유발요인 : 달걀 / 대두 / 우유 / 밀",
            "다크 초콜릿과 구운 호두가 듬뿍 들어있는 쿠키에 아몬드 크림을 한번 덧 씌워 볼록하게 올라온 외형이 마치 돔과 같은 모습을 가졌습니다.\r\n"
            + "제품 영양 정보 : 1회 제공량(kcal) 441 / 나트륨(mg) 300 / 탄수화물(g) 40 / 당류(g) 34 / 지방(g) 29 / 트렌스지방(g) 0.2 / 포화지방(g) 12 / 콜레스테롤(mg) 33 / 단백질(g) 5\r\n"
            + "알레르기 유발요인 : 달걀 / 대두 / 우유 / 밀",
            "까망베르 치즈, 체다치즈와 버터의 고소한 풍미가 가득한 피낭시에입니다. 상단의 블랙 페퍼 토핑으로 더욱 깊은 풍미를 느껴보세요.\r\n"
            + "제품 영양 정보 : 1회 제공량(kcal) 172 / 나트륨(mg) 75 / 탄수화물(g) 15 / 당류(g) 11 / 지방(g) 11 / 트렌스지방(g) 0.2 / 포화지방(g) 6 / 콜레스테롤(mg) 22 / 단백질(g) 4\r\n"
            + "알레르기 유발요인 : 달걀 / 우유 / 밀",
            "겉은 바삭하고 속은 쫄깃한 달콤한 바닐라 맛의 마카롱입니다.\r\n"
            + "제품 영양 정보 : 1회 제공량(kcal) 134 / 나트륨(mg) 13 / 탄수화물(g) 17 / 당류(g) 14 / 지방(g) 6 / 트렌스지방(g) 0.1 / 포화지방(g) 1.9 / 콜레스테롤(mg) 5 / 단백질(g) 3\r\n"
            + "알레르기 유발요인 : 달걀 / 우유 / 밀",
            "겉은 바삭하고 속은 쫄깃한 새콤 달콤 블루베리 맛의 마카롱입니다.\r\n"
            + "제품 영양 정보 : 1회 제공량(kcal) 156 / 나트륨(mg) 28 / 탄수화물(g) 18 / 당류(g) 16 / 지방(g) 8 / 트렌스지방(g) 0.1 / 포화지방(g) 1.8 / 콜레스테롤(mg) 6 / 단백질(g) 3\r\n"
            + "알레르기 유발요인 : 달걀 / 우유 / 밀",
            "입안을 가득 채우는 풍성한 버터 풍미가 느껴지는 스틱 형태의 디저트 버터바입니다.\r\n"
            + "제품 영양 정보 : 1회 제공량(kcal) 303 / 나트륨(mg) 145 / 탄수화물(g) 31 / 당류(g) 14 / 지방(g) 19 / 트렌스지방(g) 0.4 / 포화지방(g) 11 / 콜레스테롤(mg) 66 / 단백질(g) 2\r\n"
            + "알레르기 유발요인 : 달걀 / 대두 / 우유 / 밀",
            "자연방사 유정란과 바닐라 럼을 활용하는 프랑스 보르도 지역의 전통 레시피로 만들어져 풍성한 바닐라 향과 쫀득한 식감이 특징인 프랑스 전통 디저트 쁘띠 까눌레입니다.\r\n"
            + "제품 영양 정보 : 1회 제공량(kcal) 295 / 나트륨(mg) 35 / 탄수화물(g) 62 / 당류(g) 41 / 지방(g) 3.1 / 트렌스지방(g) 0 / 포화지방(g) 1.1 / 콜레스테롤(mg) 38 / 단백질(g) 5.2\r\n"
            + "알레르기 유발요인 : 달걀 / 우유 / 밀"
        };
        String[] productNames = {
            "다크 초콜릿 카우보이 쿠키", "초콜릿 월넛 돔 쿠키", "까망베르 치즈 피낭시에", "바닐라 마카롱", "블루베리 마카롱",
            "버터바", "쁘띠 까눌레"
        };

        // when
        for (int i = 0; i < productNames.length; i++) {
            String productName = productNames[i];
            int price = prices[i];
            String explain = explanations[i];

            Product product = productService.createProduct(categoryCode, productName, price, explain);

            // then
            assertNotNull(product);
            assertEquals(String.format("B06%02d", i + 1), product.getProductId());
            assertEquals(productName, product.getProductName());
            assertEquals(price, product.getProductPrice());
            assertEquals(explain, product.getProductExplain());

            // 데이터베이스에 실제로 저장되었는지 확인
            Product savedProduct = productRepository.findById(product.getProductId()).orElse(null);
            assertNotNull(savedProduct);
            assertEquals(String.format("B06%02d", i + 1), savedProduct.getProductId());
            assertEquals(price, savedProduct.getProductPrice());
            assertEquals(explain, savedProduct.getProductExplain());
        }
    }
    
    @Test
    void testCreateProductsInCategoryB07() {
        // given
        String categoryCode = "B07";
        int[] prices = {5300, 5300, 5300, 5300, 5300};  // 가격 배열
        String[] explanations = {
            "마다가스카르산 바닐라 빈이 들어있는 부드럽고 깔끔한 맛의 젤라또입니다.\r\n"
            + "제품 영양 정보 : 1회 제공량(kcal) 200 / 나트륨(mg) 40 / 탄수화물(g) 38 / 당류(g) 31 / 지방(g) 3.9 / 트렌스지방(g) 0 / 포화지방(g) 2.6 / 콜레스테롤(mg) 20 / 단백질(g) 3\r\n"
            + "알레르기 유발요인 : 우유",
            "쫀득한 식감의 파베 초콜릿이 들어있는 달콤한 맛의 초콜릿 젤라또입니다.\r\n"
            + "제품 영양 정보 : 1회 제공량(kcal) 225 / 나트륨(mg) 35 / 탄수화물(g) 36 / 당류(g) 29 / 지방(g) 7 / 트렌스지방(g) 0 / 포화지방(g) 3.3 / 콜레스테롤(mg) 20 / 단백질(g) 5\r\n"
            + "알레르기 유발요인 : 대두 / 우유",
            "산뜻한 요거트 맛과 달콤한 딸기 맛이 어우러진 젤라또입니다.\r\n"
            + "제품 영양 정보 : 1회 제공량(kcal) 145 / 나트륨(mg) 35 / 탄수화물(g) 25 / 당류(g) 19 / 지방(g) 3.1 / 트렌스지방(g) 0 / 포화지방(g) 1.9 / 콜레스테롤(mg) 9 / 단백질(g) 4\r\n"
            + "알레르기 유발요인 : 우유",
            "산뜻한 요거트 맛이 느껴지는 젤라또입니다.\r\n"
            + "제품 영양 정보 : 1회 제공량(kcal) 153 / 나트륨(mg) 56 / 탄수화물(g) 28 / 당류(g) 20 / 지방(g) 2.8 / 트렌스지방(g) 0 / 포화지방(g) 1.7 / 콜레스테롤(mg) 9 / 단백질(g) 4\r\n"
            + "알레르기 유발요인 : 대두 / 우유",
            "고소한 피스타치오 맛이 느껴지는 젤라또입니다.\r\n"
            + "제품 영양 정보 : 1회 제공량(kcal) 238 / 나트륨(mg) 53 / 탄수화물(g) 22 / 당류(g) 17 / 지방(g) 14 / 트렌스지방(g) 0 / 포화지방(g) 4.7 / 콜레스테롤(mg) 21 / 단백질(g) 6\r\n"
            + "알레르기 유발요인 : 우유"
        };
        String[] productNames = {
            "바닐라 빈 젤라또", "파베 초콜릿 젤라또", "딸기 요거트 젤라또", "요거트 젤라또", "피스타치오 젤라또"
        };

        // when
        for (int i = 0; i < productNames.length; i++) {
            String productName = productNames[i];
            int price = prices[i];
            String explain = explanations[i];

            Product product = productService.createProduct(categoryCode, productName, price, explain);

            // then
            assertNotNull(product);
            assertEquals(String.format("B07%02d", i + 1), product.getProductId());
            assertEquals(productName, product.getProductName());
            assertEquals(price, product.getProductPrice());
            assertEquals(explain, product.getProductExplain());

            // 데이터베이스에 실제로 저장되었는지 확인
            Product savedProduct = productRepository.findById(product.getProductId()).orElse(null);
            assertNotNull(savedProduct);
            assertEquals(String.format("B07%02d", i + 1), savedProduct.getProductId());
            assertEquals(price, savedProduct.getProductPrice());
            assertEquals(explain, savedProduct.getProductExplain());
        }
    }
    
    public Product saveProduct(Product product) {
        if (!"Y".equals(product.getProductSell()) && !"N".equals(product.getProductSell())) {
            throw new IllegalArgumentException("Invalid value for productSell: must be 'Y' or 'N'");
        }
        return productRepository.save(product);
    }
}
