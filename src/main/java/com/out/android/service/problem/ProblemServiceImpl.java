package com.out.android.service.problem;

import com.out.android.domain.request.problem.MakeProblemDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProblemServiceImpl implements ProblemService{
	@Override
	public void makeProblem(Long userIdx, MakeProblemDto makeProblemDto) {

	}
}
