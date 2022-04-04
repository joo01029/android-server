package com.out.android.controller;

import com.out.android.domain.request.auth.LoginDto;
import com.out.android.domain.request.auth.RegisterDto;
import com.out.android.domain.response.Response;
import com.out.android.domain.response.ResponseData;
import com.out.android.domain.response.auth.LoginResponse;
import com.out.android.service.auth.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("auth")
public class AuthController {
	private final AuthService authService;

	@PostMapping("/register")
	public Response register(@Valid @RequestBody RegisterDto registerDto){
		System.out.println("aa");
		authService.register(registerDto);
		return new Response();
	}

	@PostMapping("/login")
	public ResponseData<LoginResponse> login(@Valid @RequestBody LoginDto loginDto){
		LoginResponse result = authService.login(loginDto);
		return new ResponseData<>(result);
	}
}
