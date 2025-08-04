package com.ticxar.test_back.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UserDTO {

	private int id;
	private String username;
	private String email;
	private String firstName;
	private String lastName;
	private String gender;
	private String image;

}
