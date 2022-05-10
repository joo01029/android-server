package com.out.android.service.problem;

import com.out.android.domain.entity.Problem;
import com.out.android.domain.entity.User;
import com.out.android.domain.repo.ProblemRepo;
import com.out.android.domain.request.problem.MakeProblemDto;
import com.out.android.util.AuthUser;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProblemServiceImpl implements ProblemService{
	private final AuthUser authUser;
	private final ProblemRepo problemRepo;
	@Override
	public void makeProblem(Long userIdx, MakeProblemDto makeProblemDto) {
		try {
			User user = authUser.getUser(userIdx);
			Problem problem = Problem.builder()
					.content(makeProblemDto.getContent())
					.correctAnswer(makeProblemDto.getCorrectAnswer())
					.user(user)
					.build();

			problemRepo.save(problem);
		}catch (Exception e){
			throw e;
		}
	}
}
