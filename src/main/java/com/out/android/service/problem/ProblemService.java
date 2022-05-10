package com.out.android.service.problem;

import com.out.android.domain.request.problem.MakeProblemDto;

public interface ProblemService {
	public void makeProblem(Long userIdx, MakeProblemDto makeProblemDto);
}
