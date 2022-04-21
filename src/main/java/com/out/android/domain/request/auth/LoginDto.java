package com.out.android.domain.request.auth;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class LoginDto {
	@Length(min = 4, max = 20, message = "아이디의 범위는 4~20글자 사이입니다.")
	@NotBlank(message = "아이디는 공백이 불가능합니다.")
	private String id;
	@Length(min = 8, max = 40, message = "비밀번호의 범위는 8~40글자 사이입니다.")
	@NotBlank(message = "비밀번호는 공백이 불가능합니다.")
	private String password;
}
