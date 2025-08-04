package com.ticxar.test_back.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ticxar.test_back.dto.AuthRequest;
import com.ticxar.test_back.dto.UserDTO;
import com.ticxar.test_back.service.AuthService;

@RestController
@RequestMapping("/api")
public class AuthController {

	private final AuthService authService;

	@Autowired
	public AuthController(AuthService authService) {
		this.authService = authService;
	}

	@PostMapping("/login")
	public ResponseEntity<UserDTO> login(@RequestBody AuthRequest authRequest) {
		UserDTO user = authService.loginAndFetchUser(authRequest);
		return ResponseEntity.ok(user);
	}

}
