package com.out.android.utilTest;

import com.out.android.domain.entity.User;
import com.out.android.domain.repo.UserRepo;
import com.out.android.exception.CustomException;
import com.out.android.util.CheckUser;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class CheckUserTest {
	@InjectMocks
	private CheckUser checkUser;

	@Mock
	private UserRepo userRepo;

	@BeforeEach
	public void before(){
		User user = User.builder()
				.idx(1L)
				.id("qwer")
				.password("qwer")
				.name("qwer")
				.build();

		Mockito.lenient().when(userRepo.findByIdx(1L))
				.thenReturn(Optional.ofNullable(user));
		Mockito.lenient().when(userRepo.findByIdx(2L))
				.thenReturn(Optional.empty());
	}

	@Test
	public void checkUserSuccessTest(){
		try{
			User user = checkUser.getUser(1L);
			assert (user.getIdx() == 1);
			assert (user.getName().equals("qwer"));
		}catch (Exception e){
			e.printStackTrace();
			assert (false);
		}
	}

	@Test
	public void checkUserFailTest(){
		try{
			User user = checkUser.getUser(2L);
			assert (false);
		}catch (CustomException e){
			e.printStackTrace();
			assert (e.getMessage().equals("유저를 찾을 수 없습니다."));
		}
	}
}
