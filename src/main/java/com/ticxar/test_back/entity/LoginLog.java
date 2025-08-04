package com.ticxar.test_back.entity;

import java.time.LocalDateTime;
import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class LoginLog {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private UUID id;
	private String username;
	private LocalDateTime loginTime;
	@Column(length = 1000)
	private String accessToken;
	@Column(length = 1000)
	private String refreshToken;

	public LoginLog() {
		super();
	}

	public LoginLog(UUID id, String username, LocalDateTime loginTime, String accessToken, String refreshToken) {
		super();
		this.id = id;
		this.username = username;
		this.loginTime = loginTime;
		this.accessToken = accessToken;
		this.refreshToken = refreshToken;
	}

	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public LocalDateTime getLoginTime() {
		return loginTime;
	}

	public void setLoginTime(LocalDateTime loginTime) {
		this.loginTime = loginTime;
	}

	public String getAccessToken() {
		return accessToken;
	}

	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}

	public String getRefreshToken() {
		return refreshToken;
	}

	public void setRefreshToken(String refreshToken) {
		this.refreshToken = refreshToken;
	}

}
