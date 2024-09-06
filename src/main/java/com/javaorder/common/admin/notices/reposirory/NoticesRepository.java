package com.javaorder.common.admin.notices.reposirory;

import org.springframework.data.jpa.repository.JpaRepository;

import com.javaorder.common.admin.notices.domain.Notices;

public interface NoticesRepository extends JpaRepository<Notices,Long> {

}
