package com.javaorder.notices.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.javaorder.notices.domain.Notices;

public interface NoticesRepository extends JpaRepository<Notices,Long> {

}
