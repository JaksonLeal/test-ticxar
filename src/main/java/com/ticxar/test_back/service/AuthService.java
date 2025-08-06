package com.ticxar.test_back.service;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.ticxar.test_back.dto.AuthRequest;
import com.ticxar.test_back.dto.AuthResponse;
import com.ticxar.test_back.dto.UserDTO;
import com.ticxar.test_back.entity.LoginLog;
import com.ticxar.test_back.exception.LoginException;
import com.ticxar.test_back.feign.DummyJsonClient;
import com.ticxar.test_back.repository.LoginLogRepository;

@Service
public class AuthService {

	@Autowired
	private DummyJsonClient dummyJsonClient;
	@Autowired
	private LoginLogRepository loginLogRepository;

	public ResponseEntity<?> loginUser(AuthRequest authRequest) {

		try {

			AuthResponse authResponse = dummyJsonClient.login(authRequest);

			String bearer = "Bearer " + authResponse.getAccessToken();

			UserDTO user = dummyJsonClient.getAuthenticatedUser(bearer);

			if (user != null) {
				saveAuth(authResponse);
			}
			
			return ResponseEntity.status(HttpStatus.OK).body(user);
		
		} catch (Exception e) {
			System.out.println("El error al intentar loguearse es: " + e);
			return ResponseEntity.status(HttpStatus.CONFLICT).body(new LoginException("Credenciales Incorrectas"));
		}

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
