package com.ticxar.test_back.feign;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

import com.ticxar.test_back.dto.AuthRequest;
import com.ticxar.test_back.dto.AuthResponse;
import com.ticxar.test_back.dto.UserDTO;

@FeignClient(name = "dummyJsonClient", url = "https://dummyjson.com")
public interface DummyJsonClient {

	@PostMapping(value = "/auth/login", consumes = "application/json")
	AuthResponse login(@RequestBody AuthRequest authRequest);

	@GetMapping("/auth/me")
	UserDTO getAuthenticatedUser(@RequestHeader("Authorization") String bearerToken);

	@GetMapping("/users")
	List<UserDTO> getUsers();
}
