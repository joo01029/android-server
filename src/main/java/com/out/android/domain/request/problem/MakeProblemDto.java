package com.out.android.domain.request.problem;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class MakeProblemDto {
	@NotBlank(message = "문제는 공백이 불가능합니다.")
	private String content;

	@NotBlank(message = "정답은 공백이 불가능합니다.")
	private String correctAnswer;
}
