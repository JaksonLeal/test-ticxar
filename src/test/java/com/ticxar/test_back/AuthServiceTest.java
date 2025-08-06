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

		// se establecen datos de prueba
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
    public void testLoginUser_Flow() {
        
        when(dummyJsonClient.login(authRequest)).thenReturn(authResponse);
        when(dummyJsonClient.getAuthenticatedUser("Bearer "+authResponse.getAccessToken())).thenReturn(expectedUser);

        //debera llamar a los metodos del flujo de logueo
        UserDTO currentUser = authService.loginUser(authRequest);
        
        assertNotNull(currentUser);
        assertEquals(expectedUser.getUsername(), currentUser.getUsername());

        //verificar que si se hayan llamado los objetos
        verify(dummyJsonClient).login(authRequest);
        verify(dummyJsonClient).getAuthenticatedUser("Bearer "+authResponse.getAccessToken());
        verify(loginLogRepository).save(any(LoginLog.class));
    
	}
	
}
