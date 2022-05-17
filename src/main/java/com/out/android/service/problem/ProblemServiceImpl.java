package com.out.android.service.problem;

import com.out.android.domain.entity.Problem;
import com.out.android.domain.entity.User;
import com.out.android.domain.repo.ProblemRepo;
import com.out.android.domain.request.problem.MakeProblemDto;
<<<<<<< Updated upstream
import com.out.android.util.UserUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

=======
import com.out.android.domain.response.problem.GetProblemsResponse;
import com.out.android.util.UserUtil;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

>>>>>>> Stashed changes
@Service
@RequiredArgsConstructor
public class ProblemServiceImpl implements ProblemService{
	private final UserUtil userUtil;
	private final ProblemRepo problemRepo;
<<<<<<< Updated upstream
=======
	private final ModelMapper modelMapper;
>>>>>>> Stashed changes
	@Override
	public void makeProblem(Long userIdx, MakeProblemDto makeProblemDto) {
		try {
			User user = userUtil.getUserByIdx(userIdx);
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
<<<<<<< Updated upstream
=======

	@Override
	public List<GetProblemsResponse> getProblem() {
		try{
			return problemRepo.findAll().stream()
					.map((problem)->
						modelMapper.map(problem, GetProblemsResponse.class)
					)
					.collect(Collectors.toList());
		}catch (Exception e){
			throw e;
		}
	}
>>>>>>> Stashed changes
}
