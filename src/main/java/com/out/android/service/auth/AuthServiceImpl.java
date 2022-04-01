package com.out.android.service.auth;

import com.out.android.domain.entity.User;
import com.out.android.domain.repo.UserRepo;
import com.out.android.domain.request.auth.RegisterDto;
import com.out.android.exception.CustomException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService{
	private final UserRepo userRepo;

	@Override
	@Transactional
	public void register(RegisterDto registerDto) {
		if(userRepo.findById(registerDto.getId()).isPresent()){
			throw new CustomException(HttpStatus.FORBIDDEN, "이미 유저가 존재합니다.");
		}

		User user = User
				.builder()
				.id(registerDto.getId())
				.password(registerDto.getPassword())
				.name(registerDto.getName())
				.build();

		userRepo.save(user);
	}
}
