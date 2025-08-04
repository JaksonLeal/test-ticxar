package com.ticxar.test_back;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.ticxar.test_back.dto.AuthRequest;
import com.ticxar.test_back.dto.AuthResponse;
import com.ticxar.test_back.dto.UserDTO;
import com.ticxar.test_back.entity.LoginLog;
import com.ticxar.test_back.feign.DummyJsonClient;
import com.ticxar.test_back.repository.LoginLogRepository;
import com.ticxar.test_back.service.AuthService;

public class AuthServiceTest {

	@Mock
	private DummyJsonClient dummyJsonClient;

	@Mock
	private LoginLogRepository loginLogRepository;

	@InjectMocks
	private AuthService authService;

	private AuthRequest authRequest;
	private AuthResponse authResponse;
	private UserDTO expectedUser;

	@BeforeEach
	public void setUp() {
		MockitoAnnotations.openMocks(this);

		authRequest = new AuthRequest("testuser", "password123");

		authResponse = new AuthResponse();
		authResponse.setAccessToken("access-token");
		authResponse.setRefreshToken("refresh-token");
		authResponse.setUsername("testuser");

		expectedUser = new UserDTO();
		expectedUser.setId(1);
		expectedUser.setUsername("testuser");
		expectedUser.setEmail("test@example.com");
		expectedUser.setFirstName("Test");
		expectedUser.setLastName("User");
		expectedUser.setGender("male");
		expectedUser.setImage("image.jpg");
	}

	@Test
    public void testLoginAndFetchUser_successfulFlow() {
        
        when(dummyJsonClient.login(authRequest)).thenReturn(authResponse);
        when(dummyJsonClient.getAuthenticatedUser("Bearer access-token")).thenReturn(expectedUser);

        
        UserDTO actualUser = authService.loginUser(authRequest);

        
        assertNotNull(actualUser);
        assertEquals(expectedUser.getUsername(), actualUser.getUsername());

        verify(dummyJsonClient).login(authRequest);
        verify(dummyJsonClient).getAuthenticatedUser("Bearer access-token");
        verify(loginLogRepository).save(any(LoginLog.class));
    }
}
