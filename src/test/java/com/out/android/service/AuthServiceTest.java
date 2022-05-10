package com.out.android.service;

import com.out.android.domain.entity.User;
import com.out.android.domain.repo.UserRepo;
import com.out.android.domain.request.auth.LoginDto;
import com.out.android.domain.request.auth.RegisterDto;
import com.out.android.domain.response.auth.LoginResponse;
import com.out.android.service.auth.AuthServiceImpl;
import com.out.android.util.JwtProvider;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;
import static org.mockito.Matchers.any;

@ExtendWith(MockitoExtension.class)
public class AuthServiceTest {
	@InjectMocks
	private AuthServiceImpl authService;

	@Mock
	private UserRepo userRepo;

	@Mock
	private JwtProvider jwtProvider;

	private String tokenMessage = "token create success";
	@BeforeEach
	public void saveEntity(){
		User user = User.builder()
				.id("asdf")
				.password("asdf")
				.name("asdf")
				.idx(1L)
				.build();
		//userRepo
		Mockito.lenient().when(userRepo.save(any()))
				.thenReturn(null);
		Mockito.lenient().when(userRepo.findById("asdf"))
				.thenReturn(Optional.ofNullable(user));
		Mockito.lenient().when(userRepo.findById("qwer"))
				.thenReturn(Optional.empty());

		//jwtProvider
		Mockito.lenient().when(jwtProvider.encodingToken(1L))
				.thenReturn(tokenMessage);
	}
	@Test
	public void registerSuccessTest(){
		RegisterDto registerDto = new RegisterDto();
		registerDto.setId("qwer");
		registerDto.setPassword("qwer");
		registerDto.setName("qwer");
		try {
			authService.register(registerDto);
			assert (true);
		}catch (Exception e){
			e.printStackTrace();
			assert (false);
		}

	}
	@Test
	public void registerFailTest(){
		RegisterDto registerDto = new RegisterDto();
		registerDto.setId("asdf");
		registerDto.setPassword("qwer");
		registerDto.setName("qwer");
		try {
			authService.register(registerDto);
			assert (false);
		}catch (Exception e){
			e.printStackTrace();
			assert (true);
		}
	}

	@Test
	public void loginSuccessTest(){
		LoginDto loginDto = new LoginDto();
		loginDto.setId("asdf");
		loginDto.setPassword("asdf");
		try{
			LoginResponse response = authService.login(loginDto);
			assert(response.getToken().length() > 0);
		}catch (Exception e){
			e.printStackTrace();
			assert(false);
		}
	}
	@Test
	public void loginFailTest(){
		LoginDto loginDto = new LoginDto();
		loginDto.setId("qwer");
		loginDto.setPassword("asdf");
		try{
			LoginResponse response = authService.login(loginDto);
			assert(false);
		}catch (Exception e){
			e.printStackTrace();
			assert(true);
		}
	}
}
