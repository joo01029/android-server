package com.out.android.service.problem;

import com.out.android.domain.entity.Problem;
import com.out.android.domain.entity.User;
import com.out.android.domain.repo.ProblemRepo;
import com.out.android.domain.request.problem.MakeProblemDto;
import com.out.android.exception.CustomException;
import com.out.android.util.UserUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.out.android.domain.response.problem.GetProblemsResponse;
import org.modelmapper.ModelMapper;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProblemServiceImpl implements ProblemService{
	private final UserUtil userUtil;
	private final ProblemRepo problemRepo;
	private final ModelMapper modelMapper;
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

	@Override
	public List<GetProblemsResponse> getProblems() {
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

	@Override
	public GetProblemsResponse getProblem(Long problemId) {
		try {
			Problem problem = problemRepo.findById(problemId)
					.orElseThrow(() -> {
						throw new CustomException(HttpStatus.NOT_FOUND, "문제를 찾을 수 없습니다.");
					});
			return modelMapper.map(problem, GetProblemsResponse.class);
		}catch (Exception e){
			throw e;
		}
	}
}
