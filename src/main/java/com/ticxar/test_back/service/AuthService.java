package com.ticxar.test_back.service;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ticxar.test_back.dto.AuthRequest;
import com.ticxar.test_back.dto.AuthResponse;
import com.ticxar.test_back.dto.UserDTO;
import com.ticxar.test_back.entity.LoginLog;
import com.ticxar.test_back.feign.DummyJsonClient;
import com.ticxar.test_back.repository.LoginLogRepository;

@Service
public class AuthService {

	private final DummyJsonClient dummyJsonClient;
	private final LoginLogRepository loginLogRepository;

	@Autowired
	public AuthService(DummyJsonClient dummyJsonClient, LoginLogRepository loginLogRepository) {
		this.dummyJsonClient = dummyJsonClient;
		this.loginLogRepository = loginLogRepository;
	}

	public UserDTO loginAndFetchUser(AuthRequest request) {
		// 1. Autenticarse con DummyJSON
		AuthResponse authResponse = dummyJsonClient.login(request);

		// 2. Obtener el usuario autenticado con el accessToken
		System.out.println("el token es: " + authResponse.getAccessToken());
		String bearer = "Bearer " + authResponse.getAccessToken();
		UserDTO user = dummyJsonClient.getAuthenticatedUser(bearer);

		// 3. Registrar el login exitoso
		LoginLog log = new LoginLog();
		log.setUsername(authResponse.getUsername());
		log.setLoginTime(LocalDateTime.now());
		log.setAccessToken(authResponse.getAccessToken());
		log.setRefreshToken(authResponse.getRefreshToken());

		loginLogRepository.save(log);

		return user;
	}
}
