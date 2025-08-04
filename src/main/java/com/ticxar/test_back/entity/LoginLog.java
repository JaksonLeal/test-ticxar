package com.ticxar.test_back.entity;

import java.time.LocalDateTime;
import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
public class LoginLog {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private UUID id;

	private String username;

	private LocalDateTime loginTime;

	@Column(length = 1000) // para que postgresql acepte grandes string
	private String accessToken;

	@Column(length = 1000)
	private String refreshToken;

}
