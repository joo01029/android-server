package com.out.android.service.auth;

import com.out.android.domain.entity.User;
import com.out.android.domain.repo.UserRepo;
import com.out.android.domain.request.auth.LoginDto;
import com.out.android.domain.request.auth.RegisterDto;
import com.out.android.domain.response.auth.LoginResponse;
import com.out.android.exception.CustomException;
import com.out.android.util.JwtProvider;
import com.out.android.util.UserUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService{
	private final UserUtil userUtil;
	private final JwtProvider jwtProvider;
	@Override
	@Transactional
	public void register(RegisterDto registerDto) {
		if(userUtil.checkUserExist(registerDto.getId())){
			throw new CustomException(HttpStatus.FORBIDDEN, "이미 유저가 존재합니다.");
		}

		User user = User
				.builder()
				.id(registerDto.getId())
				.password(registerDto.getPassword())
				.name(registerDto.getName())
				.score(0)
				.build();

		userUtil.saveUser(user);
	}

	@Override
	public LoginResponse login(LoginDto loginDto) {
		User user = userUtil.getUserById(loginDto.getId());
		userUtil.checkUserPassword(user, loginDto.getPassword());

		return new LoginResponse(jwtProvider.encodingToken(user.getIdx()));
	}
}
