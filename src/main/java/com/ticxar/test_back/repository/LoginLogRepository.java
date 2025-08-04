package com.ticxar.test_back.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ticxar.test_back.entity.LoginLog;

public interface LoginLogRepository extends JpaRepository<LoginLog, UUID> {
}
