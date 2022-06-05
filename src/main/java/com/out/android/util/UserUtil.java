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

	public boolean  checkUserExist(String id){
		return userRepo.findById(id).isPresent();
	}

	public User getUserByIdx(Long idx){
		return userRepo.findByIdx(idx)
				.orElseThrow(
						()->new CustomException(HttpStatus.NOT_FOUND, "유저를 찾을 수 없습니다.")
				);
	}

	public User getUserById(String id){
		return userRepo.findById(id)
				.orElseThrow(
						()->new CustomException(HttpStatus.NOT_FOUND, "유저를 찾을 수 없습니다.")
				);
	}

	public void checkUserPassword(User user, String password){
		if (!user.getPassword().equals(password)) {
			throw new CustomException(HttpStatus.BAD_REQUEST, "아이디나 비밀번호가 다릅니다.");
		}
	}


	public void saveUser(User user){
		userRepo.save(user);
	}
}
