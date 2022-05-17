package com.out.android.service;

import com.out.android.domain.entity.Problem;
import com.out.android.domain.entity.User;
import com.out.android.domain.repo.ProblemRepo;
import com.out.android.domain.request.problem.MakeProblemDto;
import com.out.android.domain.response.problem.GetProblemsResponse;
import com.out.android.service.problem.ProblemServiceImpl;
import com.out.android.util.UserUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.parameters.P;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;

@ExtendWith(MockitoExtension.class)
public class ProblemServiceTest {
	@InjectMocks
	private ProblemServiceImpl problemService;

	@Mock
	private UserUtil userUtil;

	@Mock
	private ProblemRepo problemRepo;
	@Spy
	private ModelMapper modelMapper;

	@BeforeEach
	public void before(){
		User user = User.builder()
				.id("asdf")
				.password("asdf")
				.name("asdf")
				.idx(1L)
				.build();

		Problem problem1 = Problem.builder()
				.id(1L)
				.content("qwer")
				.correctAnswer("qwer")
				.user(user)
				.build();

		Problem problem2 = Problem.builder()
				.id(2L)
				.content("asdf")
				.correctAnswer("asdf")
				.user(user)
				.build();

		List<Problem> problems = new ArrayList<>();
		problems.add(problem1);
		problems.add(problem2);

		Mockito.lenient().when(userUtil.getUserByIdx(1L))
				.thenReturn(user);
		Mockito.lenient().when(problemRepo.save(any()))
				.thenReturn(null);
		Mockito.lenient().when(problemRepo.findAll())
				.thenReturn(problems);
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

	@Test
	public void getProblemTest(){
		try{
			List<GetProblemsResponse> result = problemService.getProblem();
			assert(result.get(0).getId().equals(1L));
			assert(result.get(0).getContent().equals("qwer"));
			assert(result.get(1).getContent().equals("asdf"));
		}catch (Exception e){
			e.printStackTrace();
			assert (false);
		}
	}

}
