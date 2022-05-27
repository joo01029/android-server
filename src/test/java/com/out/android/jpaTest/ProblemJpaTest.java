package com.out.android.jpaTest;

import com.out.android.domain.entity.Problem;
import com.out.android.domain.entity.User;
import com.out.android.domain.repo.ProblemRepo;
import com.out.android.domain.repo.UserRepo;
import com.out.android.exception.CustomException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

@ExtendWith(SpringExtension.class)
@ActiveProfiles("test")
@DataJpaTest
public class ProblemJpaTest {
	@Autowired
	private ProblemRepo problemRepo;
	@Autowired
	private UserRepo userRepo;

	@BeforeEach
	public void before() {
		User user1 = User.builder()
				.id("qwer")
				.name("qwer")
				.password("qwer")
				.score(0)
				.build();
		User user2 = User.builder()
				.id("asdf")
				.name("asdf")
				.password("asdf")
				.score(0)
				.build();
		user1 = userRepo.save(user1);
		user2 = userRepo.save(user2);

		Problem problem1 = Problem.builder()
				.content("qwer")
				.correctAnswer("qwer")
				.user(user1)
				.build();
		Problem problem2 = Problem.builder()
				.content("asdf")
				.correctAnswer("asdf")
				.user(user2)
				.build();

		problemRepo.save(problem1);
		problemRepo.save(problem2);
	}
	private Problem findProblem(Long id) throws CustomException{
		return problemRepo.findById(id)
				.orElseThrow(() -> {
					throw new CustomException(HttpStatus.NOT_FOUND, "문제를 찾을 수 없습니다.");
				});
	}

	@Test
	public void getProblemSuccessTest() {
		try {
			Problem firstProblem = problemRepo.findAll().get(0);
			Problem problem = findProblem(firstProblem.getId());

			assert (problem.getId().equals(firstProblem.getId()));
			assert (problem.getContent().equals(firstProblem.getContent()));
			assert (problem.getCorrectAnswer().equals(firstProblem.getCorrectAnswer()));
		} catch (Exception e) {
			e.printStackTrace();
			assert (false);
		}
	}

	@Test
	public void getProblemFailTest() {
		try {
			Problem lastProblem = problemRepo.findAll(Sort.by(Sort.Direction.DESC, "id"))
					.get(0);
			Problem problem = findProblem(lastProblem.getId()+1);
			assert (false);
		}catch (Exception e){
			e.printStackTrace();
			assert (true);
		}
	}
}
