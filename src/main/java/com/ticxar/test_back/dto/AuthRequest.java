package com.ticxar.test_back.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class AuthRequest {

	private String username;
	private String password;
	
	public AuthRequest(String username, String password) {
		super();
		this.username = username;
		this.password = password;
	}

	
	
}
