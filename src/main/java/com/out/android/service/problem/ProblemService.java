package com.out.android.service.problem;

import com.out.android.domain.request.problem.MakeProblemDto;
import com.out.android.domain.response.problem.GetProblemsResponse;
import java.util.List;

public interface ProblemService {
	public void makeProblem(Long userIdx, MakeProblemDto makeProblemDto);
	List<GetProblemsResponse> getProblems();
	GetProblemsResponse getProblem(Long problemId);
}
