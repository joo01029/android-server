package com.out.android.controller;

import com.out.android.domain.request.problem.MakeProblemDto;
import com.out.android.domain.response.Response;
import com.out.android.service.problem.ProblemService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import com.out.android.domain.response.ResponseData;
import com.out.android.domain.response.problem.GetProblemsResponse;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("problem")
public class ProblemController {
	private final ProblemService problemService;

	@PostMapping
	public Response makeProblem(@Valid @RequestBody MakeProblemDto makeProblemDto, HttpServletRequest request){
		Long userIdx = Long.valueOf(String.valueOf(request.getAttribute("idx")));
		problemService.makeProblem(userIdx, makeProblemDto);
		return new Response();
	}

	@GetMapping
	public ResponseData<List<GetProblemsResponse>> getProblems(){
		List<GetProblemsResponse> problems = problemService.getProblem();
		return new ResponseData<>(problems);
	}
}
