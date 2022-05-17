package com.out.android.util;

import com.out.android.domain.entity.User;
import com.out.android.domain.repo.UserRepo;
import com.out.android.exception.CustomException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserUtil {
	private final UserRepo userRepo;
	public User getUserByIdx(Long idx){
		return userRepo.findByIdx(idx)
				.orElseThrow(
						()->new CustomException(HttpStatus.NOT_FOUND, "유저를 찾을 수 없습니다.")
				);
	}
}
