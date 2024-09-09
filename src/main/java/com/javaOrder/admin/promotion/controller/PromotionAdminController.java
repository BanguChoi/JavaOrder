package com.javaOrder.admin.promotion.controller;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.javaOrder.admin.promotion.domain.Promotion;
import com.javaOrder.admin.promotion.service.PromotionAdminService;
import com.javaOrder.common.util.vo.PageRequestDTO;
import com.javaOrder.common.util.vo.PageResponseDTO;

import lombok.Setter;

@Controller
@RequestMapping("/admin/promotion/*")
public class PromotionAdminController {
	
    private final Path imageUploadPath = Paths.get("C:/uploads/images/promotion"); // 이미지 저장 경로

    @Setter(onMethod_=@Autowired)
	private PromotionAdminService service;

	@GetMapping("/promotionList")
	public String promotionList(Promotion promotion, PageRequestDTO pageRequestDTO, Model model) {
		PageResponseDTO<Promotion> promotionList = service.list(pageRequestDTO);
		model.addAttribute("promotionList", promotionList);
		return "admin/promotion/promotionList";
	}
	
	@GetMapping("/insertForm")
	public String insertForm(Promotion promotion, Model model) {
		return "admin/promotion/insertForm";
	}
	
	@PostMapping("/promotionInsert")
	public String promotionInsert(Promotion promotion) {
		service.promotionInsert(promotion);
		return "redirect:/admin/promotion/promotionList";
	}
	
	// 프로모션 이미지 추가
	@PostMapping
    @ResponseBody
    public ResponseEntity<String> createPromotion(@RequestBody Promotion promotion) {
        service.createPromotion(promotion);
        return ResponseEntity.ok("상품이 성공적으로 등록되었습니다.");
    }
	
	// 이미지 저장
	@PostMapping("/admin/promotion/uploadImage")
    @ResponseBody
    public ResponseEntity<String> uploadProductImage(@RequestParam("image") MultipartFile image, @RequestParam("promotionCode") Long promotionCode) {
        if (image.isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("이미지가 선택되지 않았습니다.");
        }

        try {
            // 이미지 파일 이름을 설정 (productId.확장자)
            String imageName = promotionCode + "." + getExtension(image.getOriginalFilename());
            Path imagePath = imageUploadPath.resolve(imageName);

            // 이미지 저장
            image.transferTo(imagePath.toFile());

            // 상품 이미지 경로 업데이트
            Promotion updatePromotion = service.getPromotionByCode(promotionCode);
            updatePromotion.setPromotionImage(imageName); // 이미지 경로 저장
            service.savePromotion(updatePromotion);

            return ResponseEntity.ok("이미지가 성공적으로 업로드되었습니다.");
        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("이미지 업로드 중 오류가 발생했습니다.");
        }
    }
	
	// 파일 이름에서 확장자 추출 메서드
    private String getExtension(String filename) {
        return filename.substring(filename.lastIndexOf(".") + 1);
    }
	
	// 이미지 파일 삭제
	@PostMapping("/admin/promotion/deleteImage")
    @ResponseBody
    public ResponseEntity<String> deletePromotionImage(@RequestParam("promotionCode") Long promotionCode) {
        try {
            // 상품 정보 가져오기
            Promotion promotion = service.getPromotionByCode(promotionCode);

            if (promotion.getPromotionImage() == null || promotion.getPromotionImage().isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("이미지가 없습니다.");
            }

            // 이미지 경로 설정
            Path imagePath = imageUploadPath.resolve(promotion.getPromotionImage());

            // 이미지 파일 삭제
            File imageFile = imagePath.toFile();
            if (imageFile.exists()) {
                imageFile.delete();
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("이미지 파일을 찾을 수 없습니다.");
            }

            // 상품 이미지 정보 초기화
            promotion.setPromotionImage(null);
            service.savePromotion(promotion);

            return ResponseEntity.ok("이미지가 성공적으로 삭제되었습니다.");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("이미지 삭제 중 오류가 발생했습니다.");
        }
    }
	
	@GetMapping("/{promCode}")
	public String promotionDetail(@PathVariable Long promCode, Promotion promotion, Model model) {
		promotion.setPromotionCode(promCode);
		Promotion detail = service.promotionDetail(promotion);
		model.addAttribute("detail",detail);
		
		String newLine = System.getProperty("line.separator").toString();
		model.addAttribute("newLine", newLine);
		return "admin/promotion/promotionDetail";
	}
	
	@PostMapping("/promotionUpdate")
	public String promotionUpdate(@ModelAttribute Promotion promotion) {
		
		service.promotionUpdate(promotion);
		return "redirect:/admin/promotion/"+promotion.getPromotionCode();//이동이 안될시 확인 확인 성공
	}
	
	// 주문상태 수정 (주문대기(W) / 주문접수(S) / 주문준비완료(P) / 픽업완료(E) / 주문취소(C) : 주문취소는 일단 보류
		@GetMapping("/updateOrders/{promCode}/{status}")
		public String updateOrder(@PathVariable Long promCode, @PathVariable String status, Promotion promotion) {
			promotion.setPromotionCode(promCode);
			promotion.setPromotionStatus(status);
			
			service.updatePromotion(promotion);
			return "redirect:/admin/promotion/promotionList";
		}
	
	@PostMapping("/promotionDelete")
	public String promotionDelete(Promotion promotion) {
		service.promotionDelete(promotion);
		return "redirect:/admin/promotion/promotionList"; //이동이 안될시 확인 성공
	}
	
	
	// 프로모션 메인에 출력
	@GetMapping("/ongoing")
    public ResponseEntity<List<Promotion>> getOngoingPromotions() {
        List<Promotion> ongoingPromotions = service.getOngoingPromotions();
        return ResponseEntity.ok(ongoingPromotions);
    }
}
