package com.javaOrder.admin.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.javaOrder.admin.vo.Admin;

public interface AdminRepository extends JpaRepository<Admin, String>{

}
