package com.javaOrder.admin.notices.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.javaOrder.admin.notices.domain.Notices;

public interface NoticesRepository extends JpaRepository<Notices,Long> {

}
