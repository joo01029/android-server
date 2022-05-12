package com.out.android.service;

import com.out.android.domain.entity.User;
import com.out.android.domain.repo.ProblemRepo;
import com.out.android.domain.request.problem.MakeProblemDto;
import com.out.android.service.problem.ProblemServiceImpl;
import com.out.android.util.AuthUser;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.ArgumentMatchers.any;

@ExtendWith(MockitoExtension.class)
public class ProblemServiceTest {
	@InjectMocks
	private ProblemServiceImpl problemService;

	@Mock
	private AuthUser authUser;

	@Mock
	private ProblemRepo problemRepo;

	@BeforeEach
	public void before(){
		User user = User.builder()
				.id("asdf")
				.password("asdf")
				.name("asdf")
				.idx(1L)
				.build();

		Mockito.lenient().when(authUser.getUser(1L))
				.thenReturn(user);
		Mockito.lenient().when(problemRepo.save(any()))
				.thenReturn(null);
	}

	@Test
	public void saveProblemTest(){
		try {
			MakeProblemDto makeProblemDto = new MakeProblemDto();
			makeProblemDto.setContent("문제1");
			makeProblemDto.setCorrectAnswer("정답");

			problemService.makeProblem(1L, makeProblemDto);
			assert (true);
		}catch (Exception e){
			e.printStackTrace();
			assert (false);
		}
	}

}
