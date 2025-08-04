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

	@Autowired
	private DummyJsonClient dummyJsonClient;
	@Autowired
	private LoginLogRepository loginLogRepository;

	public UserDTO loginUser(AuthRequest authRequest) {

		// se autentica con DummyJSON
		AuthResponse authResponse = dummyJsonClient.login(authRequest);

		// obtiene el usuario autenticado con el accessToken
		String bearer = "Bearer " + authResponse.getAccessToken();
		UserDTO user = dummyJsonClient.getAuthenticatedUser(bearer);

		// guarda en postgresql si el logueo es exitoso
		if (user != null)
			saveAuth(authResponse);

		return user;
	}

	private void saveAuth(AuthResponse authResponse) {

		LoginLog log = new LoginLog();
		log.setUsername(authResponse.getUsername());
		log.setLoginTime(LocalDateTime.now());
		log.setAccessToken(authResponse.getAccessToken());
		log.setRefreshToken(authResponse.getRefreshToken());
		loginLogRepository.save(log);

	}
}
