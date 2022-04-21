package com.out.android.service.auth;

import com.out.android.domain.entity.User;
import com.out.android.domain.repo.UserRepo;
import com.out.android.domain.request.auth.LoginDto;
import com.out.android.domain.request.auth.RegisterDto;
import com.out.android.domain.response.auth.LoginResponse;
import com.out.android.exception.CustomException;
import com.out.android.util.JwtProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService{
	private final UserRepo userRepo;
	private final JwtProvider jwtProvider;
	@Override
	@Transactional
	public void register(RegisterDto registerDto) {
		if(checkUserExist(registerDto.getId()).isPresent()){
			throw new CustomException(HttpStatus.FORBIDDEN, "이미 유저가 존재합니다.");
		}

		User user = User
				.builder()
				.id(registerDto.getId())
				.password(registerDto.getPassword())
				.name(registerDto.getName())
				.score(0)
				.build();

		userRepo.save(user);
	}

	@Override
	public LoginResponse login(LoginDto loginDto) {
		Optional<User> user = checkUserExist(loginDto.getId());
		if(!user.isPresent() && user.get().getPassword().equals(loginDto.getPassword())){
			throw new CustomException(HttpStatus.BAD_REQUEST, "아이디나 비밀번호가 다릅니다.");
		}

		return new LoginResponse(jwtProvider.encodingToken(user.get().getIdx()));
	}

	private Optional<User> checkUserExist(String id){
		return userRepo.findById(id);
	}
}
