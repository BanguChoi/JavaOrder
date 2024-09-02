package com.javaOrder.admin.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.javaOrder.admin.repository.AdminRepository;
import com.javaOrder.admin.vo.Admin;
import com.javaOrder.common.service.IdGenerationService;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AdminServiceImpl implements AdminService {

	private final AdminRepository adminRepository;
	private final IdGenerationService idGenerationService;
	
	@Transactional
	@Override
	public void createAdmin(Admin admin) {
		String adminCode = idGenerationService.generateId("A", "admin_seq");
		admin.setAdminCode(adminCode);
		adminRepository.save(admin);
	}

	@Override
	public List<Admin> adminList(Admin admin) {
		List<Admin> adminList = adminRepository.findAll();
		return adminList;
	}

}
