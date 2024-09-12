package com.javaOrder.admin.service;

import java.util.List;

import com.javaOrder.admin.domain.Admin;

public interface AdminService {
	public void createAdmin(Admin admin);
	public List<Admin> adminList(Admin admin);
	public Admin Login(String adminId, String adminPassword); 
}

