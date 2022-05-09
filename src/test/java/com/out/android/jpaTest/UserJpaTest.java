package com.out.android.jpaTest;

import com.out.android.domain.entity.User;
import com.out.android.domain.repo.UserRepo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

@ExtendWith(SpringExtension.class)
@ActiveProfiles("test")
@DataJpaTest
public class UserJpaTest {
	@Autowired
	private UserRepo userRepo;

	@BeforeEach
	public void before(){
		User user = User.builder()
				.id("qwer")
				.password("qwer")
				.name("qwer")
				.score(0)
				.build();
		userRepo.save(user);
	}

	@Test
	@Transactional
	public void successSave(){
		try {
			User user = User.builder()
					.id("asdf")
					.password("asdf")
					.name("asdf")
					.score(0)
					.build();
			userRepo.save(user);
			assert (true);
		}catch (Exception e){
			e.printStackTrace();
			assert (false);
		}
	}

	@Test
	@Transactional
	public void failSaveWhenIdSame(){
		try {

			User user = User.builder()
					.id("qwer")
					.password("qwer")
					.name("qwer")
					.score(0)
					.build();
			userRepo.save(user);
			assert (false);
		}catch (Exception e){
			e.printStackTrace();
			assert (true);
		}
	}

	@Test
	public void getUserDataSuccessful(){
		try{
			User user = userRepo.findById("qwer").orElseGet(()->{
				assert (false);
				return null;
			});
			assert (user.getPassword().equals("qwer"));
		}catch (Exception e){
			e.printStackTrace();
			assert (false);
		}
	}
	@Test
	public void getUserDataFail(){
		try{
			User user = userRepo.findById("asdf").orElse(
				User.builder()
					.id("asdf")
					.password("asdf")
					.name("asdf")
					.build()
			);
			assert (user.getId().equals("asdf"));
		}catch (Exception e){
			e.printStackTrace();
			assert (false);
		}
	}
}
