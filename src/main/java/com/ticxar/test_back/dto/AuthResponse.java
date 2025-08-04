package com.ticxar.test_back.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class AuthResponse {

	private int id;
	private String accessToken;
	private String refreshToken;
	private String username;
	private String email;
	private String firstName;
	private String lastName;
	private String gender;
	private String image;

}